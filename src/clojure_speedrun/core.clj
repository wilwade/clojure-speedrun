(ns clojure-speedrun.core
  (:require
    [clojure.string]
    [clojure-speedrun.state :as state]
    [clojure-speedrun.turn :as t]))

(comment
  Here is a very simplified version of Blackjack. You vs the dealer, just hit or
  stand. Dealer must hit until more than 17. Aces worth just 1.
  There are a lot of other rules so feel free to implement one yourself!
)

; You can run this in the REPL. Here's how to get it going, just skip #_
#_ (load "/clojure_speedrun/core")
#_ (in-ns 'clojure-speedrun.core)

; Start a hand:
#_ (take-turn)
; Hit:
#_ (take-turn :hit)
; Stand:
#_ (take-turn :stand)
; End Round:
#_ (take-turn :end)

; See the destructuring in the let statement?
(defn first-turn
  "The first turn is special"
  []
  (let [[ds ps] (partition 2 (state/draw-cards! 4))]
    {:dealer ds
     :player ps}))

; Cond is far more common than nested if statements which are rare
(defn do-turn
  "Any turn that is not the first one"
  [hands hit]
  (cond
    (= 0 @state/turn) (first-turn)
    hit (t/hit-me hands state/draw-card!)
    ; :else. Keywords are always true, so :else is an easy to read catch all
    :else (t/stand-me hands state/draw-card!)))

(defn format-card
  [card]
  (let [suite (-> card
                  :suite
                  name
                  clojure.string/capitalize)]
    (str (:name card) " of " suite " ")))

(defn format-cards
  [hand]
  (->> hand
       (map format-card)
       (reduce str)))

(defn lose
  [hands]
  (state/new-round!)
  (println (str "You have lost: " (format-cards (:player hands)))))

(defn print-turn-results
  [hands]
  (swap! state/turn inc)
  (let [d-sum (t/sum-hand (:dealer hands))
        p-sum (t/sum-hand (:player hands))]
    (println (str "This is turn number " @state/turn))
    (if (< 21 p-sum)
      (lose hands)
      (println (str "Your hand: " (format-cards (:player hands)))))))

(defn print-final-results
  [hands]
  (let [d-sum (t/sum-hand (:dealer hands))
        p-sum (t/sum-hand (:player hands))]
    (println (str "Your final hand: " (format-cards (:player hands))))
    (println (str "Dealer final hand: " (format-cards (:dealer hands))))
    (cond
      (< 21 p-sum) (println "You have lost!")
      (< 21 d-sum) (println "You have won!")
      (< p-sum d-sum) (println "You have lost!")
      :else (println "You have won!"))))

(defn take-turn
  "Take an action. Only currently supported action is :hit (otherwise stand)"
  [& [action]]
  (if (= :end action)
    (do
      ; Do blocks allow you to run several lines together, but return the last
      (print-final-results @state/hands)
      (state/new-round!))
    (print-turn-results (swap! state/hands do-turn (= action :hit)))))

(comment
  Continue your speedrun in test/clojure-speedrun/core_test.clj
)

(ns clojure-speedrun.turn)

(defn sum-hand
  "Return the value of the cards"
  [cards]
  (let [values (map :value cards)
        sum (apply + values)
        ace-count (count (filter #(= 1 %1) values))]
    (cond ; Handle the aces
      (= 0 ace-count) sum
      (< sum 11) (+ sum 10)
      :else sum)))

(defn hit
  "Add a card to the player's hand"
  [hands player draw-card-f]
  (let [new-hand (-> hands
                    (get player)
                    (conj (draw-card-f)))]
    (assoc hands player new-hand)))

(defn dealer-turn
  "Dealer takes a hit or not based on a simple sum"
  [hands]
  (let [dealer-hand (:dealer hands)
        dealer-sum (sum-hand dealer-hand)]
    (if (<= dealer-sum 17)
      (do
        (println "Dealer takes a hit")
        (hit hands :dealer))
      hands)))

(defn hit-me
  "I take a card"
  [hands draw-card-f]
  (println "You take a hit")
  (-> hands
      (hit :player draw-card-f)
      (dealer-turn)))

(defn stand-me
  "I don't take a card"
  [hands]
  (println "You stand")
  (dealer-turn hands))

(comment
  Continue your speedrun in test/clojure-speedrun/turn_test.clj
)

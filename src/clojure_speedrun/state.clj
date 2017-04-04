(ns clojure-speedrun.state
  (:require [clojure-speedrun.deck :as d]))

; Atoms can hold state and are thread safe
(def the-deck
  "Create the shuffled deck"
  (atom (shuffle (d/deck))))

(def hands
  "The hands of the players"
  (atom {:dealer []
         :player []}))

(def turn
  "Turn counter"
  (atom 0))

(defn new-round!
  "Reset the atoms for a new round"
  []
  (reset! turn 0)
  (reset! hands {:dealer []
                 :player []}))

(defn restart!
  "Reset the atom to a newly shuffled deck"
  []
  (reset! the-deck (shuffle (d/deck)))
  (new-round!))

(defn draw-card!
  "Take one card from the top of the deck"
  []
  (let [card (first @the-deck)]
    (swap! the-deck rest)
    card))

(defn draw-cards!
  [c]
  (->> draw-card!
       repeatedly
       (take c)))

(comment
  Continue your speedrun in core.clj
)

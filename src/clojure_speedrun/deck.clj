(ns clojure-speedrun.deck)

(comment
  Welcome to your first namespaced file! The ns declaration at the top
)

(defn card
  "Build a single card map"
  ([value suite] (card value (str value) suite))
  ([value name suite]
    {:value value
     :name name
     :suite suite}))

(defn gen-suite-cards
  "Generate the cards needed for a single suite"
  [suite]
  (->>
    (range 2 11)
    (map #(card %1 (str %1) suite))
    (concat
      [(card 1 "Ace" suite)
       (card 10 "Jack" suite)
       (card 10 "Queen" suite)
       (card 10 "King" suite)])))

(defn deck
  "Build a deck of the four suites"
  []
  (->>
    [:hearts :clubs :spades :diamonds]
    (map gen-suite-cards)
    (apply concat)))


(comment
  Continue your speedrun in turn.clj
)

(ns clojure-speedrun.turn-test
  (:require [clojure.test :refer :all]
            [clojure-speedrun.turn :refer :all]
            [clojure-speedrun.deck :refer [card]]))

(comment
  Here is a set of simple unit tests. See that important :require clojure.test
  :refer :all That brings in all of clojure.test ready to be used inside this
  namespace. It is also common on unit tests to refer all the file that is being
  unit tested.
)

(deftest sum-hand-test
  (testing "Hand should sum to 12"
    (let [c1 (card 4 :hearts)
          c2 (card 8 :hearts)]
      (is (= 12 (sum-hand [c1 c2])))))
  (testing "Hand should sum to 17"
    (let [c1 (card 10 "King" :hearts)
          c2 (card 7 :hearts)]
      (is (= 17 (sum-hand [c1 c2])))))
  (testing "Ace should go to 11"
    (let [c1 (card 1 "Ace" :hearts)
          c2 (card 7 :hearts)]
      (is (= 18 (sum-hand [c1 c2])))))
  (testing "Ace should stay at 1"
    (let [c1 (card 1 "Ace" :hearts)
          c2 (card 7 :hearts)
          c3 (card 5 :hearts)]
      (is (= 13 (sum-hand [c1 c2 c3]))))))

(comment
  Continue your speedrun in src/clojure_speedrun/state.clj
)

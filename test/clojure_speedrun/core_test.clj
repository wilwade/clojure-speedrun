(ns clojure-speedrun.core-test
  (:require [clojure.test :refer :all]
            [clojure-speedrun.core :refer :all]
            [clojure-speedrun.deck :refer [deck]]))

(comment
  Another simple unit test, but to keep it clean, we don't want to rely on any
  state. One way we can do that with tests that have an external api or anything
  we don't want to test, is through with-redefs. We can redef the function to
  something else entirely! In this case we just redef it to take 4 from the
  deck. constantly constructs a function that will always return the same thing.
)

(deftest first-turn-test
  (testing "each player should start with 2 cards"
    (with-redefs [clojure-speedrun.state/draw-cards! (constantly (take 4 (deck)))]
      (let [h (first-turn)
            c1 (count (:player h))
            c2 (count (:dealer h))]
        (is (= 2 c1 c2))))))

(comment
  This is the end of the speedrun for now. Congratulations!
)

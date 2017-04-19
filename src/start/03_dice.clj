(str "Dice Time")

(comment
  Time to start building with Clojure. How about we play with some dice?
  Not just any dice, but dice that contain all the sides.
)

(def simple-d6 (map inc (range 6)))

simple-d6


(str "Threading Macros")

^{:p "(def better-d6
  (->> 6
       range
       (map inc)))"}
(def better-d6
  (->> 6
       range
       (map inc)))

better-d6

(comment
  Macros are bits of code that run at compile time, or before the rest of the
  code.

  Threading macros take a value and "thread" it through the list of functions.
  ->> takes each evaluated expression and passes it into the LAST argument of
  the next one.
)

(= (->> 6 range) (range 6))

(= (->> 6 range (take 2)) (take 2 (range 6)))

(comment
  See how our die is not a defn, but just a def? That is ok because it
  uses no external data.

  Let's make a generic die instead that can have any number of sides.
)

(str "Multi-die")

^{:p "(defn any-d [d]
  (range 1 (inc d)))"}
(defn any-d [d]
  (range 1 (inc d)))

(any-d 20)

^{:p "(defn roll [d]
  (->> d
       any-d
       rand-nth))"}
(defn roll [d]
  (->> d
       any-d
       rand-nth))

(roll 20)

(roll 20)


(comment
  Couldn't we have just used the rand function? Yes. Remember this isn't about
  the best way to do something, just a way to learn stuff.

  Let's make some custom dice. Perhaps solar system generating dice?)


(str "Custom Die")

^{:p "(def space-die
  (list :star :planet :gas-giant :comet :astroid-belt :dwarf-planet))"}
(def space-die
  (list :star :planet :gas-giant :comet :astroid-belt :dwarf-planet))

^{:p "(defn roll-space-die []
  (rand-nth space-die))"}
(defn roll-space-die []
  (rand-nth space-die))

(roll-space-die)
(roll-space-die)


(str "Lazy is a Virtue")

(comment
  That's nice and all but I think it is time to get fancy with some laziness.

  Clojure has lazy sequences. These are sequences that do not resolve until they
  have to. So we can create things like infinite series of space dice results!
)

(take 3 (repeatedly roll-space-die))
(take 5 (repeatedly roll-space-die))

(comment
  So we just took three off of a list of infinite length! We can do some more
  transformations on the infinite list however...
)

^{:p "(->> roll-space-die
     repeatedly
     (map name)
     (take 5))"}
(->> roll-space-die
     repeatedly
     (map name)
     (take 5))

; Fancier? How about we skip the roll part?
^{:p "(->> (fn [] space-die)
     repeatedly
     (map rand-nth)
     (map name)
     (map clojure.string/capitalize)
     (take 5))"}
(->> (fn [] space-die)
     repeatedly
     (map rand-nth)
     (map name)
     (map clojure.string/capitalize)
     (take 5))

(comment
  The power of lazy sequences is that you can operate on the entire sequence
  without needing to worry about it.

  Clojure also allows for context neutral transducers. Here are some you might
  recognize.
)


(str "Transducers")

(map rand-nth)
(take 5)
(filter :star)

(comment
  Look at that. Simple things. Transducers allow us to create "algorithmic
  transformations". While those collectionless map, take, filter and such are
  already transducers, we can also easily compose them using the compose
  function "comp".
)

^{:p "(def roll-for-str
  (comp
    (map rand-nth)
    (map name)))"}
(def roll-for-str
  (comp
    (map rand-nth)
    (map name)))

(comment
  See how this is independent of the input and the output? It does require some
  things to be so as this is going to need a list of lists, but feel free to
  imagine more.

  Many different methods work well with transducers - often referred to as xf,
  but here are a few common ones.
)

; sequence is lazy
(sequence roll-for-str [space-die space-die space-die])

; Not lazy, but into a vector instead of the list as above
(into [] roll-for-str [space-die space-die space-die])

; Not lazy, but takes a reducing function such as str a string concatenator
(transduce roll-for-str str [space-die space-die space-die])

(comment
  How much do you write a def'd transducer? Depends. Often you write them
  without thinking about them as transducers.

  P.S. Transducers can also be applied to async channels!
)


(comment
  Continue your speedrun with src/clojure_speedrun/deck.clj
)

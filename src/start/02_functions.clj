(str "Functions")

(comment
  Functions are first class citizens in Clojure, so you can pass them around as
  much as you want by name.
)

(defn add [a b] (+ a b))

(add 1 2)

(add 1 2 3)

(reduce add [1 2 3 4 5 6])


(str "Reducers")

(comment
  Let's pause a second and remember our first experience with "+". "+" was
  already a reducer. We are close now to rebuilding the Clojure "+" so that our
  simple binary "add" function could also take any number of arguments!

  Remember how a reducer works? Process each value of the list with a binary
  function.
)

; "1 + 2 + 3 + 4 + 5 = 15"
(+ (+ (+ (+ 1 2) 3) 4) 5) ; Do (+ 1 2) = 3
(+ (+ (+ 3 3) 4) 5) ; Pass result into the next step
(+ (+ 6 4) 5)
(+ 10 5)
15

(comment
  There is a simple way we can have the function take a list. "& var" will give
  us back any other arguments in a vector, which just happens to be one of the
  things that reduce can take.
)

(defn sum [& a]
  (reduce add a))

(sum 1 2 3 4 5 6)


(str "Destructuring")

(comment
  Combinations of the two and many other options are available.

  Destructuring is also allowed, which is quite useful with maps or vectors.
)

; [{value "key" value2 :key ...}]
(defn add-map [{val-a "a" b :b}]
  (add val-a b))

(add-map {"a" 1 :b 2 :c "something else"})

; [[one two three]]
(defn add-vec [[a b]]
  (add a b))

(add-vec [4 2 87])

(comment
  The other common thing you will see in Clojure functions are multi-arity
  functions.
)

(defn add-me
  ([one] (+ 100 one))
  ([one two] (add-me one two 3))
  ([one two three] (+ one two three)))

(= 101 (add-me 1))
(= 6 (add-me 1 2))
(= 12 (add-me 1 4 7))


(str "Lambdas")

(comment
  Many times you just want a short lambda function, and there are two short ways
  to write it. #() with %1, %2,... being parameters and the slightly longer form
  which uses (fn [a b] expression).
)

(map inc [1 2 3])
(map #(+ 2 %1) [1 2 3])
(map (fn [a] (+ 3 a)) [1 2 3])
(map (fn add-four [a] (+ 4 a)) [1 2 3])
; Destructuring even works here
(map (fn add-tuples [[a b]] (+ a b)) [[1 2] [2 3] [3 4]])


(str "Map vs Reducer")

(comment
  What is the difference between map and a reducer? A map is a
  shortcut to a reducer that doesn't care about what has come before.

  Maps and reducers are so important let's recreate a simplified map
  function through a reduce function just to see.
)

(defn my-map [f xs]
  (reduce ; Here's that reduce
    ; conj appends things to a list or in this case a vector
    #(conj %1 (f %2)) ; Remember %1 is our accumulator and %2 is the next value
    [] ; Starting accumulator
    xs))

(= (map inc [1 2 3]) (my-map inc [1 2 3]))

(comment
  One other note is that you might see defn- instead of just defn. These are
  "private" functions. You might also see def which is useful for defining
  things you want to refer to by name.)

(str "Just def it")

(def config {
  :twilio {
    :key "sdfsdfiewnfesifn"
    :account "acbcorp"
  }
  :more :config
  :enabled? false
})

(:enabled? config)

(get-in config [:twilio :key] :default)

(get-in config [:twilio :endpoint] :default)


(comment
  Continue your speedrun with 03_dice.clj
)

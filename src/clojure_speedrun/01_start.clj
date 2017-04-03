; Clojure Speedrun
(comment
  Clojure is a LISP language used mostly on top of the Java Virtual Machine.

  This will get you started with the most *common* way of doing most things. Not
  always the best way and never the only way.

  1. Get and Install lein through https://leiningen.org
  2. cd to the root of this git repository
  3. run `lein repl`
)





; REPL?
(comment
  So now you have a Read-Evaluate-Print-Loop running! Congradulations! You are
  now running Clojure!

  Not impressed yet?

  For most of the rest of this, you can copy and paste any line or group of lines
  into the REPL and it will run it -- even the comments.

  Let's try addition:
)

1 + 1

2 + 5


; That's a problem we can't even add!


; Remember LISP?
(comment
  So what is LISP?
  LISt Processor

  So most everything is dealt with as a list.

  Clojure has many types of lists, but let's start with just the basic one: "()"

  Function call and parameters are also lists, just at the start.

  "(function arg1 arg2 arg3)"

  So we can finally get to some addition...
)

(+ 1 1)

(+ 2 5)

; But wait, what about adding three numbers?

(+ 3 (+ 5 2))

; Nasty, but it works :( Think more in a list...

(+ 3 5 2)

(comment
  That's right. + can take any number of arguments. This is common in Clojure.
  A function processes a list of any number of arguments
)

(+ 3 5 2 23 5342 534 6232 534 243356 34343 46354 34)


; Let's try some other stuff we would expect to work...

(- 1 25)

(= 2 2 2 2 2 2)

(= 2 1 2 2 2)

(< 1 2 3 4 5)

(< 1 1 3 4 5)

(comment
  So we have basic math. Time to move on to more exciting things like creating a
  real list. Can you guess the function name?)

(list 1 2 3 4 5)

; or the short hand way using a quote which we'll talk about later

'(1 2 3 4 5)

(= (list 1 2 3 4 5) '(1 2 3 4 5))

(comment
  Lists are great for lots of things, but it is rare that you declare them in
  this way. More common to declare are vectors. Vectors also have a function and
  a short hand:)

(vector 1 2 3 4)

[1 2 3 4]

(= (vector 1 2 3 4) [1 2 3 4])

(comment
  I think you might be ready for some variables.
  Let's talk local scoped variables first with the let block.

  Let blocks use a vector to declare the local variables and then can use them
  inside the block.
  (let [var val var val ...] do stuff with vars)
)

(let [a 5
      b 6]
  (+ a b))

; A let block also allows the first thing defined to be used in defining more

(let [a 5
      b (+ a 1)
      c (+ a b 1)]
  (= c 12))

(comment
  Now while the REPL prints out the result for us, sometimes we want to see a
  result part way through. Time to do a true print "Hello, World")

(println "Hello, World")

(comment
  Finally we are getting somewhere! Time to pause however to talk immutability.
  Immutability means that you can never change a variable. Results of functions
  are always NEW things.)

(let [a 5]
  (println a)
  (println (inc a)) ; the same as (+ a 1)
  (println (dec a)) ; the same as (- a 1)
  (println a))

(comment
  The a varaible was never changed by the methods. This is core to Clojure.
  Doesn't mean that you cannot re-let a name however, but it is not pointing to
  the same thing.)

(let [a 5
      a 6]
  a)


(comment
  Tired of just numbers? Me too. Time to get to something cool: :keywords

  Keywords in Clojure are "symbolic identifiers that evaluate to themselves"

  Just play with them...)

(= (keyword "test") :test)

(first [:a :b :c])

(last [:a :b :c])

(rand-nth [:a :b :c])

(comment
  Keywords get more useful once we have a map/hash, so off to cartography school!

  Maps are another core data structure. Want a key value pair? You want a hash
  map or a sorted map or a set, but start with a hash map.)

(= {:a 2 :b 3 :c 4} (hash-map :a 2 :b 3 :c 4))

(assoc {:a 2} :b 3 :c 4) ; Remember most things can take many things

; Keywords can also be functions...

(:a {:a 2 :b 3})

(:c {:a 2 :b 3})

(comment
  What was that? You can't go getting on to nils before you are ready! Well if
  you think you are.

  Clojure has one null and that is nil. And nil is falsey)

(if nil
  (println "True!")
  (println "False!"))

(comment
  While we are on the topic of falsey, nothing else but false is false.)

(or nil false)

(or nil false [])


(comment
  Continue your speedrun with 02_functions.clj)

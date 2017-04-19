(comment
  This file contains an easy way to step through the speedrun for presentational
  purposes. The stuff in here should not be used otherwise, as it does stuff
  unsafe for anything except a presentation.
)

(def files [
  "src/start/01_start.clj"
  "src/start/02_functions.clj"
  "src/start/03_dice.clj"
])

(defn read-all
  [file]
  (let [rdr (-> file
                clojure.java.io/reader
                java.io.PushbackReader.)]
    (loop [forms []]
      (let [form
            (try
              (binding [*read-eval* false]
                (read rdr))
            (catch Exception e nil))
            print (:p (meta form))]
        (cond
          (and print form)
            (recur (conj forms {:form form :print print}))
          form
            (recur (conj forms form))
          :else
            forms)))))

(def parts
  (->>
    files
    (map read-all)
    (apply concat)
    (remove #(= "(comment" (apply str (take 8 (str %1)))))
    (cons (quote (str "Welcome to Clojure Speedrun!")))))

(def parts-count
  (count parts))

(defn nth-parts
  [n]
  (if (< n parts-count)
    (nth parts (max 0 n))
    (quote (str "Application Demo Time!"))))

(def page (atom -1))

(def pre (atom true))

(defn do-page-print
  [form print]
  (println print)
  (eval form))

(defn do-page
  [n]
  (let [form (nth-parts n)]
    (if (map? form)
      (do-page-print (:form form) (:print form))
      (do-page-print form form))))

(defn f []
  (swap! page #(min (inc %1) parts-count))
  (do-page @page))

(defn b []
  (swap! page #(max 0 (dec %1)))
  (do-page @page))

(defn c []
  (do-page @page))

(defn preview []
  (-> @page
      inc
      (min parts-count)
      (max 0)
      nth-parts
      read-string
      println))

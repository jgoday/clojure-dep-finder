(ns clojure-dep-finder.ask)

(defn- doseq-indexed [f values]
  ""
  (doseq
      [[i v] (map-indexed (fn [i v] [i v]) values)]
    (f i v)))

(defn- print-list [values]
  ""
  (doseq-indexed
   (fn [i v] (println (inc i) ")" v))
   values))

(defrecord Err [msg])
(defn- err? [e] (instance? Err e))

(defn- read-number []
  ""
  (try
    (let [data (read-string (read-line))]
      (if (number? data)
        data
        (->Err (str "Invalid number: " data))))
    (catch NumberFormatException e
      (->Err (str "Invalid number: " (.getMessage e))))))

(defn ask-for-number [options]
  ""
    (print-list options)
    (print (str "Select one library: "))
    (flush)
    (let [n (read-number)]
      (if (err? n)
        (do
          (println (:msg n))
          (println "Please, select again")
          (ask-for-number options))
        (dec n))))

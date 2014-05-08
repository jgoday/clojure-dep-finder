(ns clojure-dep-finder.library
  (use [clojure-dep-finder.ascii :only [wrap-in-color]]))

(defrecord Library [name version desc repo])

(defmulti name-and-version class)
(defmethod name-and-version Library [l]
  (str
   (wrap-in-color (:name l) :blue)
   " ("
   (wrap-in-color (:version l) :green)
   ") "
   (wrap-in-color (:repo l) :grey)))

(defmulti complete-desc class)
(defmethod complete-desc Library [l]
  (str (name-and-version l) "\n\t" (:desc l)))


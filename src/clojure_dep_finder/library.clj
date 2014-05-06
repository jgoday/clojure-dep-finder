(ns clojure-dep-finder.library)

(defrecord Library [name version desc])

(defmulti name-and-version class)
(defmethod name-and-version Library [l]
  (str (:name l) " (" (:version l) ")"))

(defmulti complete-desc class)
(defmethod complete-desc Library [l]
  (str (name-and-version l) "\n\t" (:desc l)))


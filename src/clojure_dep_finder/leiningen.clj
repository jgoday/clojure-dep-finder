(ns clojure-dep-finder.leiningen
  (:require [clojure-dep-finder.library]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import [clojure_dep_finder.library Library]))

(defmulti leiningen-dep class)
(defmethod leiningen-dep Library [l]
  (str "[" (:name l) " \"" (:version l) "\"]"))

(defn- write-to [filename data]
  (with-open [wrt (io/writer filename)]
    (.write wrt data)))

(defn- read-project []
  (slurp "project.clj"))

;; Replace this function with more sotisficated one
(defn- add-dependency [lib]
  (let [proj (read-project)
        to-replace ":dependencies ["
        replace-with (str to-replace (leiningen-dep lib) "\n\t\t\t ")
        new-proj (str/replace proj to-replace replace-with)]
        (write-to "project.clj" new-proj)))

(defn show-howto [lib]
  ""
  (do
    (println "Add this to your leiningen project.clj file: ")
    (println (leiningen-dep lib))))

(defn install-dependency [lib]
  ""
  (do
    (add-dependency lib)
    (println (leiningen-dep lib) "added to project.clj .")))

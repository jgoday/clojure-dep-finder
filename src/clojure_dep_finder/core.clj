(ns clojure-dep-finder.core
  (:require [clojure-dep-finder.clojars :as clojars]
            [clojure-dep-finder.leiningen :as lein]
            [clojure-dep-finder.ask :refer [ask-for-number]]
            [clojure.tools.cli :refer [cli]])
  (:use [clojure-dep-finder.library])
  (:gen-class))

(def no-search-msg "Error: You must enter a library name")

(defn- arguments [args]
  (cli args
       ["-h" "--help" "Print this help" :default false :flag true]
       ["-n" "-no-write" "Dont write into project.clj" :default false :flag true]
       ["-d" "--desc" "Show description" :default false :flag true]))

(defn- create-jar-list [options show-desc]
  (if show-desc
    (map complete-desc options)
    (map name-and-version options)))

(defn- do-install [lib no-write]
  (if no-write
    (lein/show-howto lib)
    (lein/install-dependency lib)))

(defn- do-search
  ([name]
     (do-search name false false))
  ([name no-write show-desc]
     (let [values (clojars/search name)
           descs (create-jar-list values show-desc)
           number (ask-for-number descs)]
       (do-install (nth values number) no-write))))

(defn -main [& args]
  (let [[opts args banner] (arguments args)]
    (when (:help opts)
      (println banner))
    (if (nil? (first args))
      (println no-search-msg)
      (do-search (first args) (:no-write opts) (:desc opts)))))

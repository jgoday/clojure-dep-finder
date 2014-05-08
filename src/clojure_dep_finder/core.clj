(ns clojure-dep-finder.core
  (:require [clojure-dep-finder.clojars :as clojars]
            [clojure-dep-finder.mavendotorg :as mvn]
            [clojure-dep-finder.leiningen :as lein]
            [clojure-dep-finder.ask :refer [ask-for-number]]
            [clojure.tools.cli :refer [cli]]
            [clojure.core.async :refer
             [chan timeout go >! alts!! <!! <!]])
  (:use [clojure-dep-finder.debug :only [enable-debug info]]
        [clojure-dep-finder.library])
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

(defn- do-search [name]
  (let [c (chan)]
    (go (>! c (clojars/search name)))
    (go (>! c (mvn/search name)))

    (flatten
     (reduce (fn [results _] (conj results (<!! (go (<! c)))))
             []
             (range 2)))))

(defn- init-search
  ([name]
     (do-search name false false))
  ([name no-write show-desc]
     (let [values (do-search name)
           descs (create-jar-list values show-desc)
           number (ask-for-number descs)]
       (do-install (nth values number) no-write))))

(defn -main [& args]
  (let [[opts args banner] (arguments args)]
    (when (:help opts)
      (println banner))
    (enable-debug)
    (if (nil? (first args))
      (println no-search-msg)
      (init-search (first args) (:no-write opts) (:desc opts)))))

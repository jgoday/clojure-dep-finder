(ns clojure-dep-finder.mavendotorg
  (:require [clj-http.client :as http])
  (:use [clojure-dep-finder.debug :only [info]]
        [clojure-dep-finder.library :only [->Library]]
        [clojure.string :only [trim]]))

(def max-rows 30)

(defn- search-url [name]
  (str
   "http://search.maven.org/solrsearch/select?q="
   name "&rows=" max-rows "&wt=json"))

(defn- parse-result [node]
  (->Library
   (str (:g node) "/" (:a node))
   (:latestVersion node)
   (str "")
   "maven"))

(defn- get-results [res]
  (get-in res [:body :response :docs]))

(defn search [name]
  (map
   parse-result
   (get-results
    (do
      (info "Searching in maven.org")
      (http/get (search-url name) {:as :json})))))

(ns clojure-dep-finder.clojars
  (:require [net.cgrand.enlive-html :as html])
  (:use [clojure-dep-finder.debug :only [info]]
        [clojure-dep-finder.library :only [->Library]]
        [clojure.string :only [trim]]))

(def search-url "https://clojars.org/search?q=")

(defn- make-url [name] (str search-url name))

(defn- safe-string [s]
  (trim (apply str s)))

(defn- parse-result [node]
  (->Library
     (safe-string (html/select node [:li :a :> html/text-node]))
     (safe-string (html/select node [:li :> html/text-node]))
     (safe-string (html/select node [:li :span.desc :> html/text-node]))
     "clojars"))

(defn search [name]
  (do
    (info "Searching in clojars.org")
    (let [url (java.net.URL. (make-url name))
          data (html/html-resource url)]
      (map parse-result (html/select data [:li.search-results])))))

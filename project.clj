(defproject clojure-dep-finder "0.1"
  :description "Little script to find clojure dependencies in clojars"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main clojure-dep-finder.core
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 [org.clojure/tools.namespace "0.2.3"]
                 [clj-http "0.9.1"]
                 [enlive "1.1.5"]
                 [org.clojure/tools.cli "0.2.4"]])

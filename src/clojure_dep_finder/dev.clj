(ns clojure-dep-finder.dev
  (require [clojure.tools.namespace.repl :refer (refresh)]
           [clojure.repl :refer :all]))

(defn reset []
  (stop)
  (refresh))

(ns clojure-dep-finder.ascii)

(def grey-color "\u001B[30m")
(def yellow-color "\u001B[33m")
(def blue-color "\u001B[34m")
(def green-color "\u001B[32m")
(def default-color "\u001B[m")

(def colors {:grey grey-color
             :blue blue-color
             :green green-color
             :yellow yellow-color
             :default default-color})

(defn wrap-in-color [msg color]
  (str
   (get colors color :default)
   msg
   default-color))


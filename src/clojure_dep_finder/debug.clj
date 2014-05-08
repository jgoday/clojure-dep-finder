(ns clojure-dep-finder.debug
  (use [clojure-dep-finder.ascii :only [wrap-in-color]]))

(def debug-enabled (atom false))

(defn enable-debug []
  (swap! debug-enabled (fn [_] true)))


(defn disable-debug []
  (swap! debug-enabled (fn [_] false)))

(defn info [& msg]
  (if @debug-enabled
    (println
     (wrap-in-color
      (str " .. " msg)
      :yellow))))

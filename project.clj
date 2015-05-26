(defproject maltose "0.1.0-SNAPSHOT"
  :description "A collection of methods to help devise brewing recipes."
  :url "https://github.com/dogonthehorizon/maltose"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0-RC1"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace  "0.2.10"]]
                   :source-paths ["dev/"]}})

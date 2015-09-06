(ns maltose.test-util
  "Provides useful methods during testing."
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn apply-permutations
  "Given a function and a collection of values, apply that function
  to all permutations of the given collection."
  [f coll]
  (doall ;Force evaluation for testing purposes
    (map #(apply f %) (permutations coll))))


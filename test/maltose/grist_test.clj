(ns maltose.grist-test
  (:require [clojure.test :refer :all]
            [clojure.math.combinatorics :refer [permutations]]
            [maltose.grist :refer :all]
            [maltose.test-util :refer [apply-permutations]]))

(def sample-grist
  {:2-row 0.95
   :munich 0.05})

(deftest total-gravity-functionality
  (testing "Given a non-float value for either argument to this method, it
           should throw an AssertionError."
    (is (thrown? AssertionError
                 (apply-permutations total-gravity [1.050 50]))))
  (testing "Total gravity should be the product of gravity units and final
           volume"
    (is (= 150.0 (total-gravity 1.050 3.0)))))

(deftest ingredient-gravity-functionality
  (testing "Should throw an AssertionError if grist is not a map"
    (is (thrown? AssertionError (ingredient-gravity nil 50.0))))
  (testing "Should thrown an AssertionError if grist is an empty map"
    (is (thrown? AssertionError (ingredient-gravity {} 50.0))))
  (testing "Should throw an AssertionError if total-extract is not a float"
    (is (thrown? AssertionError (ingredient-gravity {} 50))))
  (testing "Should return the gravity points that each fermentable should
           provide"
    (is (= {:2-row 142.5 :munich 7.5} (ingredient-gravity sample-grist 150.0)))))

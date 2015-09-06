(ns maltose.core-test
  (:require [clojure.test :refer :all]
            [maltose.core :refer :all]))

(def grist
  {:2-row 8.962962962962953
   :toasted-malt 1.1203703703703691
   :crystal-60 0.5601851851851846
   :flaked-wheat 0.5601851851851846})

(def grist-percent
  {:2-row 0.7999999999999999
   :toasted-malt 0.09999999999999999
   :crystal-60 0.049999999999999996
   :flaked-wheat 0.049999999999999996})

(def grist-malformed
  {:foo 'bar
   :jee-whiz \1})

(deftest grist-percentages-functionality
  (testing "Should return a map of grain keys and percentage values."
    (is (= grist-percent (grist-percentages grist))))
  (testing "Should throw an AssertionError if non-float map values are given."
    (is (thrown? AssertionError (grist-percentages grist-malformed)))))

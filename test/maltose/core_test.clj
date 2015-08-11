(ns maltose.core-test
  (:require [clojure.test :refer :all]
            [maltose.core :refer :all]))

(def bh
  "A sample brewhouse."
  (->Brewhouse 0.75))

(def r
  "A sample recipe."
  (->Recipe
    5.5
    1.055
    1.012
    {:2-row 0.80
     :toasted-malt 0.10
     :crystal-60 0.05
     :flaked-wheat 0.05}
    {}))

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

(deftest gravity-points-functionality
  (testing "Should return the gravity points of a given recipe."
    (is (= (int (gravity-points r)) 302))))

(deftest grist-weight-functionality
  (testing "Should return total weight of grain bill given a recipe
           and brewhouse."
    (is (= (int (grist-weight bh r)) 11))))

(deftest grist-amounts-functionality
  (testing "Should return a map of grain keys and weight values (in pounds)."
    (is (= (grist-amounts bh r) grist))))

(deftest grist-percentages-functionality
  (testing "Should return a map of grain keys and percentage values."
    (is (= grist-percent (grist-percentages grist))))
  (testing "Should throw an AssertionError if non-float map values are given."
    (is (thrown? AssertionError (grist-percentages grist-malformed)))))

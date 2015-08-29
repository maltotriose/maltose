(ns maltose.mash-test
  (:require [clojure.test :refer :all]
            [maltose.mash :refer :all]))

(deftest initial-infusion-functionality
  (testing "Passing in a non-float value for any arg should throw an
           AssertionError"
    (is (thrown? AssertionError (initial-infusion 1.0 1.0 1)))
    (is (thrown? AssertionError (initial-infusion 1 1.0 1.0)))
    (is (thrown? AssertionError (initial-infusion 1.0 1 1.0))))
  (testing "Omitting the optional grain-to-water-ratio should result in a
           ratio of 1.0"
    (is (= 1.0 (initial-infusion 1.0 1.0))))
  (testing "Providing a grain-to-water-ratio should result in a strike water
           temperature above 1.0 degrees Farenheit"
    (is (= 11.8 (initial-infusion 1.0 10.0)))))

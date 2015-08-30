(ns maltose.mash-test
  (:require [clojure.test :refer :all]
            [clojure.math.combinatorics :refer [permutations]]
            [maltose.mash :refer :all]))

(deftest initial-infusion-functionality

  (testing "Passing in a non-float value for any arg should throw an
           AssertionError"
    (is (thrown? AssertionError (doall (map #(apply initial-infusion %)
                                            ;; Generate a list of lists with
                                            ;; the Integer in a different
                                            ;; position each time
                                            (permutations [1 2.0 3.0]))))))

  (testing "Omitting the optional grain-to-water-ratio should result in a
           ratio of 1.0"
    (is (= 1.0 (initial-infusion 1.0 1.0))))

  (testing "Providing a grain-to-water-ratio should result in a strike water
           temperature above 1.0 degrees Farenheit"
    (is (= 11.8 (initial-infusion 1.0 10.0)))))


(deftest mash-infusion-functionality

  (testing "Passing in a non-float value for any arg should throw an
           AssertionError"
    (is (thrown? AssertionError (doall
                                  (map #(apply mash-infusion %)
                                       (permutations [1 2.0 3.0 4.0 5.0]))))))

  (testing "Omitting the optional strike-water-temperature arg should default
           to boiling (212.0)"
    (is (= 0.05346534653465346 (mash-infusion 1.0 10.0 1.0 1.0))))

  (testing "Providing a value for strike-water-temperature arg should prefer
           that value over the default 212.0"
    (is (not (= 0.05346534653465346 (mash-infusion 1.0 10.0 1.0 1.0 1.0))))))

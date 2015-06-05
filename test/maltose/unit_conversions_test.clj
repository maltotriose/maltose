(ns maltose.unit-conversions-test
  (:require [clojure.test :refer :all]
            [maltose.unit-conversions :refer :all]))

(deftest ->Plato-functionality
  (testing "Given a specific gravity, it should return the equivalent
           degrees Plato."
    (let [sg 1.052]
      (is (= 13.00000000000001 (->Plato sg)))
      (is (= sg (->SpecificGravity (->Plato sg))))))
  (testing "Given an input that is not a number, it should throw an
           AssertionError"
    (is (thrown? AssertionError (->Plato nil)))
    (is (thrown? AssertionError (->Plato "asdf")))))

(deftest ->SpecificGravity-functionality
  (testing "Given a measurement of degrees Plato, it should return the
           equivalent specific gravity."
    (let [p 13.00]
      (is (= 1.052 (->SpecificGravity p)))
      (is (= 13 (int (->Plato (->SpecificGravity p)))))))
  (testing "Given an input that is not a number, it should throw an
           AssertionError"
    (is (thrown? AssertionError (->SpecificGravity nil)))
    (is (thrown? AssertionError (->SpecificGravity "sweet lion of zion")))))

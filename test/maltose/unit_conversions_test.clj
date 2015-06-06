(ns maltose.unit-conversions-test
  (:require [clojure.test :refer :all]
            [maltose.unit-conversions :refer :all]))

(deftest ->Plato-functionality
  (testing "Given a specific gravity, it should return the equivalent
           degrees Plato."
    (let [sg 1.052]
      (is (= 12.861598442175875 (->Plato sg)))))
  (testing "Given an input that is not a number, it should throw an
           AssertionError"
    (is (thrown? AssertionError (->Plato nil)))
    (is (thrown? AssertionError (->Plato "asdf")))))

(deftest ->specific-gravity-functionality
  (testing "Given a measurement of degrees Plato, it should return the
           equivalent specific gravity."
    (let [p 13.00]
      (is (= 1.0525847139070577 (->specific-gravity p)))))
  (testing "Given an input that is not a number, it should throw an
           AssertionError"
    (is (thrown? AssertionError (->specific-gravity nil)))
    (is (thrown? AssertionError (->specific-gravity "sweet lion of zion")))))

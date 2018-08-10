(ns minesweeper.core-test
  (:require [clojure.test :refer :all]
            [minesweeper.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest create-board-test
  (testing "creates a board of size 2 and 3"
    (is (= [[0 0 0] [0 0 0]] (create-board 2 3)))))

(deftest get-collection-of-random-coordinates-test
  (testing "returns a list of random coordinates of size 2"
    (is (= 2 (count (get-collection-of_random-coordinates 2))))))

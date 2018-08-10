(ns minesweeper.core-test
  (:require [clojure.test :refer :all]
            [minesweeper.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest create-board-test
  (testing "creates a board of size 2 and 3"
    (is (= [[0 0 0] [0 0 0]] (create-board 2 3)))))

(deftest n-bombs-test
  (testing "returns a list of random coordinates of size 2"
    (is (= 2 (count (n-bombs 10 10 2))))))

(deftest place-bomb-in-row-test
  (let [row [1 2 3]]
  (testing "returns a vec with bomb placed at 2"
    (is (= [1 2 :b] (place-bomb-in-row 2 row))))))

(deftest place-bomb-in-board-test
  (let [board [[1 2]
               [3 4]]]
    (testing "returns the board with bomb placed at 0, 0"
      (is (= [[:b 2] [3 4]] (place-bomb-in-board 0 0 board))))

    (testing "returns the board with bomb placed at 1, 0"
      (is (= [[1 2] [:b 4]] (place-bomb-in-board 1 0 board))))))

(deftest generate-board-with-bombs-test
  (let [board [[1 2 3][4 5 6][7 8 9]]
        ordns [[1 1][1 2][2 0]]]
    (testing "returns the board with all bombs placed as per ordns"
      (is (= [[1 2 3][4 :b :b][:b 8 9]] (generate-board-with-bombs board ordns))))))

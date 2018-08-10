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

(deftest within-bounds-test
  (testing "returns true if x and y are between 0 inclusive & 10 exclusive"
    (is (= true (within-bounds? 1 4)))
    (is (= true (within-bounds? 0 9)))
    (is (= true (within-bounds? 5 5))))

  (testing "returns false if x & y are out of bounds of 0 & 10"
    (is (= false (within-bounds? 0 10)))
    (is (= false (within-bounds? 1 11)))))

(deftest get-neighbours-test
  (testing "returns collection of neighbours within bounds for p & q"
    (is (= [[0 1][1 0][1 1]] (get-neighbours 0 0)))
    (is (= [[4 4][4 5][4 6][5 4][5 6][6 4][6 5][6 6]] (get-neighbours 5 5)))
    (is (= [[4 0][4 1][5 1][6 0][6 1]] (get-neighbours 5 0)))))

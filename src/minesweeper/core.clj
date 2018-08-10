(ns minesweeper.core
  (:require [clojure.pprint :as pp])
  (:gen-class))

(defn create-board [m n]
  (into [] (repeat m (into [] (repeat n 0)))))

(defn n-bombs [m n x]
  (into [] (take x (repeatedly (fn [] [(rand-int m) (rand-int n)])))))

(defn place-bomb-in-row [x row]
  (into [] (map-indexed (fn [i item] (if (= x i) :b item)) row)))

(defn place-bomb-in-board [x y board]
  (into [] (map-indexed (fn [y row] (if (= 0 y) (place-bomb-in-row x row) row)) board)))

(defn generate-board-with-bombs [board ordinates]
  (first (map-indexed (fn [k coord] (place-bomb-in-board (first coord) (last coord) board)) ordinates)))

(defn within-bounds? [x y]
  (and (and (>= x 0) (< x 10)) (and (>= y 0) (< y 10))))

(defn get-neighbours [p q]
    (for [x (range (dec p) (+ p 2)) y (range (dec q) (+ q 2)) :when (and (not= [p q] [x y]) (within-bounds? x y))] [x y]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (pp/pprint (generate-board-with-bombs (create-board 10 10) (n-bombs 10 10 3))))

(ns minesweeper.core
  (:require [clojure.pprint :as pp])
  (:gen-class))

(defn create-board [m n]
  (into [] (repeat m (into [] (repeat n 0)))))

(defn n-bombs [m n x]
  (into [] (take x (repeatedly (fn [] [(rand-int m) (rand-int n)])))))

(defn place-bomb-in-row [y row]
  (into [] (map-indexed (fn [i item] (if (= y i) :b item)) row)))

(defn place-bomb-in-board [x y board]
  (into [] (map-indexed (fn [i row] (if (= x i) (place-bomb-in-row y row) row)) board)))

(defn generate-board-with-bombs [board ordinates]
  (reduce (fn [b locn] (assoc-in b locn :b)) board ordinates))

(defn within-bounds? [x y]
  (and (and (>= x 0) (< x 10)) (and (>= y 0) (< y 10))))

(defn get-neighbours [p q]
  (for [x (range (dec p) (+ p 2)) y (range (dec q) (+ q 2)) :when (and (not= [p q] [x y]) (within-bounds? x y))] [x y]))

(defn -main
  [& args]
  (pp/pprint (generate-board-with-bombs (create-board 10 10) (n-bombs 10 10 3))))

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
  (map-indexed (fn [k coord] (place-bomb-in-board (first coord) (last coord) board)) ordinates))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (pp/pprint (create-board 10 10)))

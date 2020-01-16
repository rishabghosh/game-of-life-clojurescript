(ns game-of-life.core
  (:require
    [reagent.core :as r]
    [clojure.string :as str]
    )
  )

(def split str/split)
;; -------------------------
;; Views



;(defn unit-repeater [create-unit unit-count]
;  (loop [index 0 result ()]
;    (if (>= index unit-count)
;      result
;      (let [unit (create-unit index)]
;        (recur (inc index) (conj result unit)))
;      )
;    )
;  )



;(def cell-dimension 50)
;(def cell-border 1)
;(defn calculate-row-height [cell-dimension, cell-border]
;
;  )


;; ------- style ----------

(def playground {
                 :background "red",
                 :border     "2px solid black",
                 :width      "500px",
                 :height     "510px"
                 })

(def row-style {
                :height     "51px"
                :background "blue"
                :display    "flex"
                })

(def cell-style {
                 :border     "1px solid black"
                 :height     "50px"
                 :width      "50px"
                 :background "green"
                 })

;; --------- utils -----------

(defn generate-id [row-no cell-no]
  (str "r" row-no "c" cell-no))

(defn parse-id [id]
  (let [cell-coordinate (rest (split id #"r|c"))]
    (hash-map :row (first cell-coordinate), :cell (second cell-coordinate))
    )
  )

(defn log-target []
  (println (parse-id event.target.id))
  )


;; --------- components -----------


(defn create-single-cell [id]
  [:div
   {:id id, :style cell-style, :on-click log-target}
   ])

(defn cell-repeater [row-no cell-count]
  (loop [index 0 result ()]
    (if (>= index cell-count)
      result
      (let [id (generate-id row-no index)
            cell (create-single-cell id)]
        (recur (inc index) (conj result cell)))
      )
    )
  )

(defn create-row [row-no cell-count]
  [:div
   {:style row-style}
   (cell-repeater row-no cell-count)
   ])

(defn row-repeater [row-count cell-count]
  (loop [index 0 result ()]
    (if (>= index row-count)
      result
      (let [row (create-row index cell-count)]
        (recur (inc index) (conj result row)))
      )
    )
  )

(defn home-page []
  [:div
   {:style playground}
   (row-repeater 10 10)
   ])



;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))

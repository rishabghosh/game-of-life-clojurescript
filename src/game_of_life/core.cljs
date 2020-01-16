(ns game-of-life.core
  (:require
    [reagent.core :as r]))

;; -------------------------
;; Views

;; ------- style ----------

;(def cell-dimension 50)
;(def cell-border 1)
;(defn calculate-row-height [cell-dimension, cell-border]
;
;  )

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

;; --------- components -----------



(defn log-random []
  (println "a cell has been clicked"))

(defn generate-id [row-no cell-no]
  (str "r" row-no "c" cell-no))

(defn create-single-cell [id]
  [:div
   {:id id, :style cell-style}
   ])

(defn create-single-row [row-no cell-count]
  [:div
   {:style row-style}
   (loop [index 0 result ()]
     (if (>= index cell-count)
       result
       (let [id (generate-id row-no index)
             cell (create-single-cell id)]
         (recur (inc index) (conj result cell)))
       )
     )
   ])

(defn create-rows [row-count cell-count]
  (loop [index 0 result ()]
    (if (>= index row-count)
      result
      (let [row (create-single-row index cell-count)]
        (recur (inc index) (conj result row)))
      )
    )
  )

(defn home-page []
  [:div
   {:style playground}
   (create-rows 10 10)
   ])



;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))

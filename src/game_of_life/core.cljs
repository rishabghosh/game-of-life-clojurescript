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

(def single-cell [:div
                  {:style cell-style :on-click log-random}
                  ])

(defn create-single-row [cell-count]
  [:div
   {:style row-style}
   (repeat cell-count single-cell)
   ])

(defn create-rows [row-count cell-count]
  (repeat row-count (create-single-row cell-count))
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

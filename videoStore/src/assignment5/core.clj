(ns assignment5.core
  (:gen-class)
  (:require [assignment5.videoStore :as videoStore])
  (:require [assignment5.gui :as gui]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (gui/display (gui/main-ui))
  (println "\n")
  ;(println @videoStore/movies"\n")
  ;(println @videoStore/renters"\n")
  ;(videoStore/add-movie "movie4" 4.50 8)
  ;(println (videoStore/get-price :id 2)"\n")
  ;(println (videoStore/get-qty :id 2)"\n")
  ;(videoStore/change-price 2 4.0)
  (videoStore/return-movie 1)
  ;(println @videoStore/movies"\n")
  ;(println @videoStore/renters"\n")
  )


(ns assignment5.test
  (:gen-class)
  (:require [clj-time.core :as time])
  (:require [clj-time.format :as time-format])
  (:require [assignment5.videoStore :as videoStore]))





(def custom-formatter (time-format/formatter "MM-dd-yyyy"))
(time-format/unparse custom-formatter (time/plus (time/now) (time/weeks 2)))

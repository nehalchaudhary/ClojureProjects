; Name   : Nehal Chaudhary
; Red ID : 817368221
; Functional programming Assignment 4


(ns assignment4.core
  (:gen-class)
  (:require [clojure.tools.trace :refer :all])
  (:require [assignment4.node :as node])
  (:require [assignment4.heap :as heap])
  (:require [clojure.zip :as zip]))

(defn -main
  [& args]
  (heap/heap-print-preorder ["boring" ["hi" ["man" nil nil] ["meaning" nil nil]] ["lame" ["tieing" nil nil] nil]])
  (heap/heap-print-preorder-ing ["boring" ["hi" ["man" nil nil] ["meaning" nil nil]] ["lame" ["tieing" nil nil] nil]])
)

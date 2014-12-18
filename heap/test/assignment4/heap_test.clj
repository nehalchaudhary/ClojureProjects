; Name   : Nehal Chaudhary
; Red ID : 817368221
; Functional programming Assignment 4
; Contains all the test-cases related to the heap

(ns assignment4.heap-test
  (:require [clojure.test :refer :all]
            [assignment4.core :refer :all]
            [assignment4.node :refer :all]
            [assignment4.heap :refer :all]
            [clojure.zip :as zip]))


(deftest test-heap-insert
  (testing "Testing inserting in a min-heap")
  (are [min-heap node new-tree] (= new-tree (heap-insert min-heap node))
       ["hi" nil nil] "hello" ["hello" ["hi" nil nil] nil]
       nil "hi" ["hi" nil nil]
       ["lame" ["car" ["am" nil nil] ["honey" nil nil]] ["me" nil nil]] "bye" ["bye" ["car" ["am" nil nil] ["honey" nil nil]] ["lame" ["me" nil nil] nil]]
       ["hi" nil nil] "hi" ["hi" ["hi" nil nil] nil]
       ["hi" nil nil] "" ["" ["hi" nil nil] nil]))

(deftest test-heap-pre-order
  (testing "Pre-order display")
  (are [min-heap pre-order-display] (= pre-order-display (heap-preorder min-heap))
       ["hi" ["hea" ["you" nil nil] ["what" nil nil]] ["me" nil nil]] '("hi" "hea" "you" "what" "me")
       nil nil)
       ["hi" ["hea" nil nil] ["me" nil nil]] '("hi" "hea" "me"))

(deftest test-heap-pre-order-ing
  (testing "Pre-order display")
  (are [min-heap pre-order-display] (= pre-order-display (heap-preorder-ing min-heap))
       ["hi" ["boring" ["you" nil nil] ["swimming" nil nil]] ["me" nil nil]] '("boring" "swimming")
       nil nil)
       ["hi" ["hea" nil nil] ["me" nil nil]] nil)




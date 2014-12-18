; Name   : Nehal Chaudhary
; Red ID : 817368221
; Functional programming Assignment 4
; Contains all the functions related to the node


(ns assignment4.node
  (:gen-class)
  (:require [clojure.tools.trace :refer :all])
  (:require [clojure.zip :as zip]))


;------------------------------------- Returns left sub-tree -------------------------------------

(defn zipper->left-child
 [zipper]
 (-> zipper zip/down zip/right))

;------------------------------------- Returns right sub-tree -------------------------------------

(defn zipper->right-child
 [zipper]
 (-> zipper zip/down zip/rightmost))

;------------------------------------- Returns Value of the node -------------------------------------

(defn zipper->value
 [zipper]
 (if (zip/node zipper)
 (-> zipper zip/down zip/node)
 nil))

;--------------------------- Replaces root of the zipper with new node ---------------------------------

(defn replace-node
 [zipper replacement]
 (let [location (zip/node zipper)
 node (zip/make-node zipper location [replacement nil nil])]
 (-> zipper (zip/replace node) zip/root)))

;------------------------- Replaces value of the root with new value -----------------------------------

(defn replace-value
  [zipper x]
  (-> zipper
      zip/down
     (zip/replace x)
     zip/root))

;----------------------------------- Check if the tree is empty ----------------------------------------

(defn tree-empty?
 [zipper]
 (not (zip/node zipper)))

;------------------------------------ Calculate the height of the node ---------------------------------------

;Assume the height of the leaf elements is 0

(defn height
  [zipper]
  (if (empty? zipper)
    -1
  (inc (max (height (zipper->left-child zipper)) (height (zipper->right-child zipper))))))


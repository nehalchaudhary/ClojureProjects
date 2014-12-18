; Name   : Nehal Chaudhary
; Red ID : 817368221
; Functional programming Assignment 4
; Contains all the functions related to the heap


(ns assignment4.heap
  (:gen-class)
  (:require [assignment4.node :as node])
  (:require [clojure.zip :as zip]))



;-------------------------------------------------------- Heap Insert -------------------------------------------------------------------

(defn heap-zipper-insert
 [zipper value-to-insert]
 (let [value-of-node (node/zipper->value zipper)
      left-height (node/height (node/zipper->left-child zipper))
      right-height (node/height (node/zipper->right-child zipper))]
   (cond
    (node/tree-empty? zipper) ;Make the new node the root if heap is empty
      (node/replace-node zipper value-to-insert)
    (neg? (compare value-to-insert value-of-node)) ;Replace the value of the node with the new value if the new value is less than the value of the node
      (recur (zip/vector-zip(node/replace-value zipper value-to-insert)) value-of-node)
    (> left-height right-height) ;Insert new value to the right-child of the node if the (height of the left sub-tree) > (height of right sub tree)
      (recur (node/zipper->right-child zipper) value-to-insert)
    (or (= left-height right-height) (< left-height right-height)) ;Insert new value to the left-child of the node otherwise
      (recur (node/zipper->left-child zipper) value-to-insert))))

(defn heap-insert ;Convert the min-heap to vector and call the insert function
 [min-heap value-to-insert]
 (heap-zipper-insert (zip/vector-zip min-heap) value-to-insert))

;-------------------------------------------------------- Traversing heap in pre-order ---------------------------------------------------

(defn heap-zipper-preorder
  [zipper preorder-list]
  (if (node/tree-empty? zipper) ;Return the preorder-list if there are no more nodes
     preorder-list
    (do
      (let [preorder-list (conj preorder-list (node/zipper->value zipper)) ;Add the current node value to the pre-order list
           preorder-list (heap-zipper-preorder (node/zipper->left-child zipper) preorder-list)] ;Traverse to the left subtree of the node
           (heap-zipper-preorder (node/zipper->right-child zipper) preorder-list))))) ;Traverse to the right subtree of the node

;Converts the given Min-Heap to vector, calls the pre-order functions and returns the output in repl
(defn heap-preorder
  [min-heap]
  (let [preorder-list nil]
 (if (node/tree-empty? (zip/vector-zip min-heap)) ;Check if the Min-heap is empty else call the pre-order function
   nil
  (reverse (heap-zipper-preorder (zip/vector-zip min-heap) preorder-list)))))

;Prints the min-heap in pre-order to the console
(defn heap-print-preorder
  [min-heap]
  (println "The pre-order traversal of the Min-Heap: "(heap-preorder min-heap)))


;-------------------------------------------------------- Print words from the min-heap ending in "ing" ---------------------------------------------------


(defn heap-zipper-preorder-ing
  [zipper preorder-list]
  (if (node/tree-empty? zipper)
    preorder-list
    (do
      (let [preorder-list (if (.endsWith (clojure.string/lower-case (node/zipper->value zipper)) "ing")
                            (conj preorder-list (node/zipper->value zipper))
                            preorder-list)
      preorder-list (heap-zipper-preorder-ing (node/zipper->left-child zipper) preorder-list)]
      (heap-zipper-preorder-ing (node/zipper->right-child zipper) preorder-list)))))

;Converts the given Min-Heap to vector, calls the pre-order-ing functions and returns the output in repl
(defn heap-preorder-ing
  [min-heap]
  (let [preorder-list nil]
 (if (node/tree-empty? (zip/vector-zip min-heap)) ;Check if the Min-heap is empty else call the pre-order-ing function
    nil
   (reverse (heap-zipper-preorder-ing (zip/vector-zip min-heap) preorder-list)))))

;Prints the words ending in "ing" from the min-heap in pre-order to the console
(defn heap-print-preorder-ing
  [min-heap]
  (println "The pre-order display of the heap: "(heap-preorder-ing min-heap)))

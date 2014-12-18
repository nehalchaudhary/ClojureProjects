; Name   : Nehal Chaudhary
; Red ID : 817368221
; Functional programming Assignment 4
; Contains all the test-cases related to the node



(ns assignment4.node-test
  (:require [clojure.test :refer :all]
            [assignment4.core :refer :all]
            [assignment4.node :refer :all]
            [clojure.zip :as zip]))


(deftest test-left-child
  (testing "Get left sub-tree"
    (are [node left_child] (= left_child (zipper->left-child (zip/vector-zip node)))
         ["hi" nil "hello"] '(nil {:l ["hi"], :pnodes [["hi" nil "hello"]], :ppath nil, :r ("hello")})
         [nil] nil
         [nil nil nil] '(nil {:l [nil], :pnodes [[nil nil nil]], :ppath nil, :r (nil)}))))

(deftest test-right-child
  (testing "Get right sub-tree"
    (are [node right_child] (= right_child (zipper->right-child (zip/vector-zip node)))
         ["hello" nil "hi"] '("hi" {:l ["hello" nil], :pnodes [["hello" nil "hi"]], :ppath nil, :r nil})
         [nil nil nil] '(nil {:l [nil nil], :pnodes [[nil nil nil]], :ppath nil, :r nil})
         [nil] '(nil {:l [], :pnodes [[nil]], :ppath nil, :r nil})
         nil nil
         ["hello" "hi" nil] '(nil {:l ["hello" "hi"], :pnodes [["hello" "hi" nil]], :ppath nil, :r nil})
         ["hello" ["hi" nil nil] [1"hi" nil nil]] '([1"hi" nil nil] {:l ["hello" ["hi" nil nil]], :pnodes [["hello" ["hi" nil nil] [1"hi" nil nil]]], :ppath nil, :r nil}))))

(deftest test-value
  (testing "Get value of node"
    (are [node value](= value (zipper->value (zip/vector-zip node)))
         ["hello" nil "hi"] "hello"
         [nil] nil
         ["hello" "hi" "hi"] "hello")))

(deftest test-replace-node
  (testing "Check replace node"
    (are [tree toInsert new_tree] (= new_tree (replace-node (zip/vector-zip tree) toInsert))
         nil 11 [11 nil nil]
         ["hello" nil nil] "hi" ["hi" nil nil])))

(deftest test-replace-value
  (testing "Check replacing value of a node")
  (are [tree replacement new_tree] (= new_tree (replace-value (zip/vector-zip tree) replacement))
       ["hello" ["hi" "me" nil] "there"] "you" ["you" ["hi" "me" nil] "there"]))

(deftest test-tree-empty
  (testing "Testing if tree is empty"
    (are [tree is_empty] (= is_empty (tree-empty? (zip/vector-zip tree)))
         nil true
         ["hello"] false
         ["hello" nil nil] false)))

(deftest test-height
  (testing "Calculate height of a node"
    (are [node node_height] (= node_height (height (zip/vector-zip node)))
         nil 0
         ["hello" nil nil] 1
         ["hello" "hi" 1"hi"] 1
         ["hello" ["hi" [1 nil nil] [8 nil nil]] [20 nil nil]] 3)))

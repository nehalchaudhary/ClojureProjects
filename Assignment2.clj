; Name   : Nehal Chaudhary
; Red ID : 817368221
; Functional programming assignment 2




; -----------------------------------------------------------------------------------
;Question 1
; -----------------------------------------------------------------------------------


(defn sdsu-nth
  [list1 n]
  (last (take (+ n 1) list1)))

(= (sdsu-nth '(6 5 4 3) 2) 4)
(= (sdsu-nth [:a :b :c] 1) :b)
(= (sdsu-nth '([1 2] [3 4] [5 6]) 2) [5 6])

; -----------------------------------------------------------------------------------
;Question 2
; -----------------------------------------------------------------------------------

(defn r-sdsu-nth
 [list1 n]
 (if (= n 0)
 (first list1)
 (r-sdsu-nth (rest list1) (dec n))))

(r-sdsu-nth [1 2 3 4 ] 2)
(r-sdsu-nth '(5 6 7 8 ) 2)
(= (r-sdsu-nth [:a :b :c] 1) :b)
(= (r-sdsu-nth '([1 2] [3 4] [5 6]) 2) [5 6])

; -----------------------------------------------------------------------------------
;Question 3
; -----------------------------------------------------------------------------------

(defn- reverse-iter
 [list1 list2]
 (if (= (count list1) 0)
   list2
   (reverse-iter (rest list1) (conj list2 (first list1)))))

(defn sdsu-reverse
 [list1]
  (let [list2 nil]
 (reverse-iter list1 list2)))

(sdsu-reverse '(1 2 3 4))
(sdsu-reverse '([1 2] [3 4]))

; -----------------------------------------------------------------------------------
;Question 4
; -----------------------------------------------------------------------------------

(defn- sdsu-dup-iter
  [list1 list2]
  (if(= (count list1) 0)
  (sdsu-reverse list2)
  (sdsu-dup-iter (rest list1) (conj list2 (first list1) (first list1)))))

(defn sdsu-dup
  [list1]
  (let [list2 nil]
  (sdsu-dup-iter list1 list2)))

(= (sdsu-dup [1 2 3]) '(1 1 2 2 3 3))
(= (sdsu-dup [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (sdsu-dup [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

; -----------------------------------------------------------------------------------
;Question 5
; -----------------------------------------------------------------------------------

(defn- sdsu-no-dup-iter
  [list1 list2]
   (if(= (count list1) 0)
     (sdsu-reverse list2)
    (if(= (first list1) (first(rest list1)))
      (sdsu-no-dup-iter (rest (rest list1)) (conj list2 (first list1)))
     (sdsu-no-dup-iter (rest list1) (conj list2 (first list1))))))


(defn sdsu-no-dup
  [list1]
    (let [list2 nil]
  (sdsu-no-dup-iter list1 list2)))

(= (sdsu-no-dup [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (sdsu-no-dup [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

; -----------------------------------------------------------------------------------
;Question 6
; -----------------------------------------------------------------------------------


(defn- sdsu-pack-iter
  [list1 list2]
  (if(= (count list1) 0)
    list2
    (if(= (last (last list2)) (first list1))
      (sdsu-pack-iter (rest list1) (conj (pop list2) (conj (last list2) (first list1))))
    (sdsu-pack-iter (rest list1) (conj list2 [(first list1)])))))

(defn sdsu-pack
  [list1]
   (let [list2 [[(first list1)]]]
    (sdsu-pack-iter (rest list1) list2)))

(= (sdsu-pack [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (sdsu-pack [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))

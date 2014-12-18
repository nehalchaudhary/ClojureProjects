; Name   : Nehal Chaudhary
; Red ID : 817368221
; Functional programming assignment 3




; -----------------------------------------------------------------------------------
;Question 1
; -----------------------------------------------------------------------------------


(defn- sdsu-rotate-iter
  [current n original_list rotated_list]
  (if(= current (- n 1))
    (reverse (conj rotated_list (nth original_list current)))
  (sdsu-rotate-iter (mod (+ current 1) (count original_list)) n original_list (conj rotated_list (nth original_list current)))))


(defn sdsu-rotate
  [n original_list]
  (if (zero? n)
    (seq original_list)
  (let [rotated_list nil
    current (mod n (count original_list))]
    (sdsu-rotate-iter current current original_list rotated_list))))


(sdsu-rotate 2 [1 2 3 4 5])
(sdsu-rotate 0 [1 2 3 4 5])


; -----------------------------------------------------------------------------------
;Question 2
; -----------------------------------------------------------------------------------


(defn sdsu-sum
  [n1 n2 max]
(reduce + (for [x (range max)
      :when(or (zero? (mod x n1)) (zero? (mod x n2)))]
  x)))


(sdsu-sum 4 2 10)
(sdsu-sum 2 5 10)


; -----------------------------------------------------------------------------------
;Question 3
; -----------------------------------------------------------------------------------


(defn- isPalindrome
  [n]
  (if(= (str n) (clojure.string/reverse (str n)) )
  true
    false))

(defn exp [x n]
  (reduce * (repeat n x)))

(defn sdsu-palindome
  [n]
  (let [i (exp 10 (- n 1))
    j (exp 10 n)]
  (apply max (for [x (range i j) y (range i j) :when(isPalindrome (* x y))]
            (* x y)))))

(sdsu-palindome 2)
(sdsu-palindome 3)


; -----------------------------------------------------------------------------------
;Question 4
; -----------------------------------------------------------------------------------


(defn sdsu-fibonacci
  [n]
  (loop [prev 1 sum 2 count (- n 1) result 0]
    (if (<= count 0)
      result
      (recur sum (+ prev sum) (dec count) (if(even? sum)(+ result sum) result)))))

(sdsu-fibonacci 11)
(sdsu-fibonacci 8)
(sdsu-fibonacci 0)
(sdsu-fibonacci 1)
(sdsu-fibonacci 2)
(sdsu-fibonacci 10)


; -----------------------------------------------------------------------------------
;Question 5
; -----------------------------------------------------------------------------------


(defn sdsu-dna-count
 [dna]
  (clojure.string/replace (frequencies dna) #"\\" ":"))

(sdsu-dna-count "ATGCTTC")
(sdsu-dna-count "ACTGCGATAGACAGATAGC")


; -----------------------------------------------------------------------------------
;Question 6
; -----------------------------------------------------------------------------------


(defn sdsu-digits
  [n base]
  (if(= n 0)
    [0]
    (if(<= base 1)
      "Invalid base"
    (loop [number n digits nil]
      (if(= number 0)
        digits
      (recur(quot number base) (conj digits (rem number base))))))))

(quot 10 3)

(sdsu-digits 12348012 10)

(sdsu-digits 0 9)

(sdsu-digits 140 8)

(sdsu-digits 110 16)

(sdsu-digits 11 1)


; -----------------------------------------------------------------------------------
;Question 7
; -----------------------------------------------------------------------------------


(defn sdsu-roman-numeral
  [n]
  (loop [number n roman ""]
    (if (<= number 0)
      roman
    (recur (cond (>= number 1000) (- number 1000)
                 (>= number 900) (- number 900)
                 (>= number 500) (- number 500)
                 (>= number 400) (- number 400)
                 (>= number 100) (- number 100)
                 (>= number 90) (- number 90)
                 (>= number 50) (- number 50)
                 (>= number 40) (- number 40)
                 (>= number 10) (- number 10)
                 (>= number 9) (- number 9)
                 (>= number 5) (- number 5)
                 (>= number 4) (- number 4)
                 (>= number 1) (- number 1))
           (cond (>= number 1000) (str roman \M)
                 (>= number 900) (str roman \C \M)
                 (>= number 500) (str roman \D)
                 (>= number 400) (str roman \C \D)
                 (>= number 100) (str roman \C)
                 (>= number 90) (str roman \X \C)
                 (>= number 50) (str roman \L)
                 (>= number 40) (str roman \X \L)
                 (>= number 10) (str roman \X)
                 (>= number 9) (str roman \I \X)
                 (>= number 5) (str roman \V )
                 (>= number 4) (str roman \I \V)
                 (>= number 1) (str roman \I)
                 )))))


(sdsu-roman-numeral 1230)
(sdsu-roman-numeral 1904)








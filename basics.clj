;; Nehal Chaudhary
;; 817368221
;; Assignment 1

 (= true true)

 (= 2 (+ 1 1))

 (= (+ 3 5) 8 (+ 2 6))

 (= 2 2/1 4/2)

 (= false (= 2 2.0))

 (== 3.0 3 9/3)

 (not= :ﬁll-in-the-blank :something)

 (= '(1 2 3 4 5) (list 1 2 3 4 5))

 (= 2 (first '(2 3 4 5)))

 (= '(3 4 5) (rest '(2 3 4 5)))

 (= () (rest '(10)))

 (= '(:a :b :c :d :e) (cons :a '(:b :c :d :e)))

 (= '(0 :a :b :c :d :e) (conj '(:a :b :c :d :e) 0))

 (= :a (peek '(:a :b :c :d :e)))

 (= '(:b :c :d :e) (pop '(:a :b :c :d :e)))

 (= "No dice!" (try
 (pop '())
 (catch IllegalStateException e "No dice!")))

 (= () (try
 (rest '())
 (catch IllegalStateException e "No dice!")))

 (= 1 (count [42]))

 (= [] (vec nil))

 (= [1] (vec '(1)))

 (= [nil] (vector nil))

 (= [1 2] (vec '(1 2)))

 (= [333] (conj (vec nil) 333))

 (= :peanut (first [:peanut :butter :and :jelly]))

 (= :jelly (last [:peanut :butter :and :jelly]))

 (= :jelly (nth [:peanut :butter :and :jelly] 3))

 (= [:butter :and] (subvec [:peanut :butter :and :jelly] 1 3))

 (= (list 1 2 3) (vector 1 2 '3))

 (= #{} (set ()))

 (= 3 (count #{1 2 3}))

 (= #{1 2 3 4 5} (set '(1 1 2 2 3 3 4 4 5 5)))

 (= #{1 2 3 4 5} (clojure.set/union #{1 2 3 4} #{2 3 5}))

 (= #{2 3} (clojure.set/intersection #{1 2 3 4} #{2 3 5}))

 (= #{1 4} (clojure.set/difference #{1 2 3 4 5} #{2 3 5}))

 (= {} (hash-map))

 (= 0 (count (hash-map)))

 (= {:a 1} (hash-map :a 1))

 (= 2 (count {:a 1 :b 2}))

 (= 2 (get {:a 1 :b 2} :b))

 (= 1 ({:a 1 :b 2} :a))

 (= 1 (:a {:a 1 :b 2}))

 (= "Vancouver" ({2006 "Torino" 2010 "Vancouver" 2014 "Sochi"} 2010))

 (= nil (get {:a 1 :b 2} :c))

 (= :key-not-found (get {:a 1 :b 2} :c :key-not-found))

 (= true (contains? {:a nil :b nil} :b))

 (= false (contains? {:a nil :b nil} :c))

 (= {1 "January" 2 "February"} (assoc {1 "January" } 2 "February"))

 (= {1 "January"} (dissoc {1 "January" 2 "February"} 2))

 (= (list 2006 2010 2014)
 (sort (keys {2006 "Torino" 2010 "Vancouver" 2014 "Sochi"})))

 (= (list "Sochi" "Torino" "Vancouver")
 (sort (vals {2006 "Torino" 2010 "Vancouver" 2014 "Sochi"})))

 (defn multiply-by-ten [n]
  (* 10 n))

 (defn square [n] (* n n))

 (= 20 (multiply-by-ten 2))

 (= 4 ((fn [n] (* n n)) 2))

 (= 75 (#(* 15 %) 5))

 (= 15 (#(+ %1 %2 %3) 4 5 6))

 (= 20 ((fn []
 ((fn [a b] (* a b))
 4 5))))

 (def func
  (fn [n] (* n n)))
 (= 25 (func 5))

 (= 25 ( square 5))

(defn explain-defcon-level [exercise-term]
 (case exercise-term
 :fade-out :you-and-what-army
 :double-take :call-me-when-its-important
 :round-house :o-rly
 :fast-pace :thats-pretty-bad
 :cocked-pistol :sirens
 :say-what?))

 (= :a (if (false? (= 4 5))
 :a
 :b))

 (= [] (if (> 4 3)
 []))

 (= (if (nil? 0)
 [:a :b :c]))

 (= :glory (if (not (empty? ()))
 :doom
 (or :glory)))

 (let [x 5]
 (= :your-road (cond (= x 2) :road-not-taken
 (= x 3) :another-road-not-taken
 :else :your-road)))

 (= 'doom (if-not (zero? 0) 'doom
 'doom))

 (= :sirens
 (explain-defcon-level :cocked-pistol))

 (= :say-what?
 (explain-defcon-level :yo-mama))

 (= [4 8 12] (map (fn [x] (* 4 x)) [1 2 3]))

 (= [1 4 9 16 25] (map (fn [x] (* x x)) [1 2 3 4 5]))

 (= (map nil? [:a :b nil :c :d]))

 (= () (filter (fn [x] false) '(:anything :goes :here)))

 (= '(:anything :goes :here) (filter (fn [x] true) '(:anything :goes :here)))

 (= [10 20 30] (filter (fn [x] (< x 31)) [10 20 30 40 50 60 70 80]))

 (= [10 20 30] (map (fn [x] (* 10 x)) (filter (fn [x] (< x 4)) [1 2 3 4 5 6 7 8])))

 (= 24 (reduce (fn [a b] (* a b)) [1 2 3 4]))

 (= 2400 (reduce (fn [a b] (* a b)) 100 [1 2 3 4]))

 (= "longest" (reduce (fn [a b]
 (if (< a) b a))
 ["which" "word" "is" "longest"]))

(ns assignment5.videoStore
  (:gen-class)
  (:require [clj-time.core :as time])
  (:require [clj-time.format :as time-format]))

; Reads the movies and the renters data into memory as global variables
(def movies (atom (read-string (slurp "doc/movies.clj"))))
(def renters (atom (read-string (slurp "doc/renters.clj"))))

(defn available-movies
  "Movies available for rent are the the movies whose quantity is greater than zero"
  []
  (filter #(> (:qty %) 0) @movies))

(defn- write-to-file
  "Updates the data files"
  [file]
  (if (= file "movies")
    (spit "doc/movies.clj" (pr-str @movies))
  (spit "doc/renters.clj" (pr-str @renters))))

(defn- get-movie
  "Gets the movie by id/name which is given by key and the value is the id/name of the key"
  [key value]
  (let [index (count (take-while #(not= value (key %)) @movies))]
    (if (= index (count @movies))
         nil
         index)))

(defn- get-renter
  "Gets the index of the renter in the renter table with the renter-id"
  [renter-id]
  (let [index (count (take-while #(not= renter-id (:id %)) @renters))]
    (if (= index (count @renters))
         nil
         index)))

(defn- add-copy
  "Adds a copy of a movie and writes to the data file"
  [movie-name]
  (swap! movies update-in [(get-movie :name movie-name) :qty] inc)
  (write-to-file "movies"))

(defn get-price
  "Get price of a movie by id/name given by key and the the id/name given by the value"
  [key value]
  (get-in @movies [(get-movie key value) :price]))

(defn get-qty
  "Get quantity of a movie by id/name given by key and the the id/name given by the value"
  [key value]
  (get-in @movies [(get-movie key value) :qty]))

(defn change-price
  "Change the price of a movie by its id"
  [movie-id price]
  (swap! movies assoc-in [(get-movie :id movie-id) :price] price)
  (write-to-file "movies"))

(defn remove-movie
  "Remove a copy of a movie"
  [movie-id]
  (swap! movies update-in [(get-movie :id movie-id) :qty] dec)
  (write-to-file "movies"))

(defn rent-movie
  "Removes a copy of a movie and add it to rented movies along with the renter-name"
  [movie-id renter-name]
  (let [movie-name (get-in @movies [(get-movie :id movie-id) :name])
        renter-id (inc (:id (last @renters)))
        date-formatter (time-format/formatter "MM-dd-yyyy")
        date (time-format/unparse date-formatter (time/plus (time/now) (time/weeks 2)))]
    (if (nil? movie-name)
      nil
      (do
        (swap! movies update-in [(get-movie :id movie-id) :qty] dec)
        (write-to-file "movies")
        (swap! renters conj {:id renter-id, :name movie-name, :renter renter-name, :date date})
        (write-to-file "renters")))))

(defn return-movie
  "Removes the entry from the data file and adds a copy of that movie"
  [renter-id]
  (let [movie-name (get-in @renters [(get-renter renter-id) :name])]
    (if (nil? movie-name)
      nil
    (do
      (reset! renters (remove #(= renter-id (:id %)) @renters))
      (write-to-file "renters")
      (add-copy movie-name)))))

(defn add-movie
  "Adds a new movie into the database"
  [name price qty]
  (let [id (inc (:id (last @movies)))]
  (swap! movies conj {:id id :name name, :price price, :qty qty})
    (write-to-file "movies")))

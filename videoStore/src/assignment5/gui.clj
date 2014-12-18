(ns assignment5.gui
  (:gen-class)
  (:require [seesaw.core :as seesaw])
  (:require [seesaw.swingx :as swingx])
  (:require [assignment5.videoStore :as video-store]))


(def movies
  "Reads the movies from the database"
  (swingx/table-x
   :horizontal-scroll-enabled? true
   :id :movies
   :model [
   :columns [{:key :name, :text "Name"} :price :qty]
   :rows (video-store/available-movies)]))

(def renters
  "Reads the Renters from the database"
  (seesaw/table
   :id :renters
   :model [
   :columns [{:key :name, :text "Name"} :renter :date]
   :rows @video-store/renters]))

(defn- refresh-movies
  "Refreshes the content of the movies table"
  []
  (seesaw/config! movies :model [:columns [{:key :name, :text "Name"} :price :qty]
                                 :rows (video-store/available-movies)]))

(defn- refresh-renters
  "Refreshes the content of the renters table"
  []
  (seesaw/config! movies :model [:columns [{:key :name, :text "Name"} :renter :date]
                                 :rows @video-store/renters]))

(defn display
  "Displays a window"
 [content]
 (let [window (seesaw/frame :title "Video Store"
                            :width 400
                            :height 400)]
 (-> window
 (seesaw/config! :content content)
 (seesaw/show!)
  (seesaw/move! :by [450 200]))))

(defn- rent-movie
  "Takes the renters name as input and and calls the rent-movie funtion in the backend"
  [index]
  (if (nil? index)
    (seesaw/alert "Please select a movie to rent")
  (let [renter-name (seesaw/input "Enter your name: ")
        movie-id (get-in @video-store/movies [index :id])]
      (if-not (nil? renter-name)
        (video-store/rent-movie movie-id renter-name)))))

(defn- remove-movie
  "Calculates the movie id from the index and calls the remove-movie function in the backnend"
  [index]
  (if (nil? index)
    (seesaw/alert "Please select a movie to remove")
  (let [movie-id (get-in @video-store/movies [index :id])]
    (video-store/remove-movie movie-id)
    (seesaw/alert "A copy of the movie has been removed"))))

(defn- return-movie
  "Calculates the movie id from the index and calls the return-movie function in the backnend"
  [index]
  (if (nil? index)
    (seesaw/alert "Please select a movie to return")
  (let [renter-id (get-in @video-store/renters [index :id])]
    (video-store/return-movie renter-id)))
    (seesaw/alert "This movie has been returned"))

(defn- display-movies
  "The user can view available movies and rent/remove them"
  []
  (let [remove (seesaw/button
             :text "Remove a copy of selected movie"
             :listen [:action (fn [e]
                                (let [root (seesaw/to-frame e)]
                                  (remove-movie (seesaw/selection (seesaw/select root [:#movies])))
                                  (refresh-movies)))])
       rent (seesaw/button
             :text "Rent selected movie"
             :listen [:action (fn [e]
                                (let [root (seesaw/to-frame e)]
                                  (rent-movie (seesaw/selection (seesaw/select root [:#movies])))
                                  (refresh-movies)))])]
  (display (seesaw/top-bottom-split (refresh-movies) (seesaw/top-bottom-split remove rent)))))

(defn- display-renters
  "The user can view rented movies and return them"
  []
  (let [return (seesaw/button
             :text "Return selected movie"
             :listen [:action (fn [e]
                                (let [root (seesaw/to-frame e)]
                                  (return-movie (seesaw/selection (seesaw/select root [:#movies])))
                                  (refresh-renters)))])]
  (display (seesaw/top-bottom-split (refresh-renters) return))))

(defn main-ui
  "The main UI contains buttons to rent/return movies"
  []
  (seesaw/grid-panel
   :columns 1
   :items [(seesaw/button
             :text "Available Movies"
             :listen [:action (fn [e] (display-movies))])
           (seesaw/button
             :text "Rented Movies"
             :listen [:action (fn [e] (display-renters))])]))



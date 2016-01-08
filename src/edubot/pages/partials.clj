(ns edubot.pages.partials
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn page-head []
  [:head
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:link {:href "https://fonts.googleapis.com/css?family=Lato|Dosis:700" :type "text/css" :rel "stylesheet"}]
   (include-css "css/main.css")])

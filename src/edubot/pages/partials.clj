(ns edubot.pages.partials
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn page-head []
  [:head
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   (include-css "css/main.css")]
  )

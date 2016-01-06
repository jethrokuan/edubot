(ns edubot.pages.partials
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]))

(defn page-head []
  (include-css "css/main.css")
  )

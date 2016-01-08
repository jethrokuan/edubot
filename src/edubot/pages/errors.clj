(ns edubot.pages.errors
  (:require [hiccup.core :refer :all]
            [edubot.pages.partials :refer [page-head]]))

(defn- error-page [text]
  (html
   (page-head)
   [:body
    [:div.container
     [:div.hero
      [:div#logo
       [:img {:src "img/logo-cry.png"}]]
      [:div#hero-text.flow-text
       [:h1 "Oops!"]
       [:p text]
       [:a.btn-large.error {:href "javascript:history.back()"} "Go Back"]]]]]))

(defn already-invited [] (error-page "You're already invited. Please check your email again."))

(defn already-in-team [] (error-page "You're already in Slack. You can sign in <a href=\"http://tk.sg/slack\">here</a> with your credentials."))



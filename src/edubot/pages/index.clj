(ns edubot.pages.index
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [edubot.pages.partials :refer :all]))

(defn index-page []
  (html
   (page-head)
   [:body
    [:div.container
     [:div.hero
      [:div#logo
       [:img {:src "img/logo-with-text.png"}]]
      [:div#welcome-text.flow-text
       [:h1 "Welcome to Tinkercademy!"]
       [:form {:action "/signup"}
        [:input {:type "text" :name "email" :placeholder "Email"}]
        [:button {:class "btn-large" :type "submit"}
         "Get your Slack Invite!"]]]]]]))
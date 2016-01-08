(ns edubot.pages.signup
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [edubot.pages.partials :refer :all]))

(defn signup-page [channel]
  (html
   (page-head)
   [:body
    [:div.container
     [:div.hero
      [:div#logo
       [:img {:src "img/logo-with-text.png"}]]
      [:div#welcome-text.flow-text
       [:h1 "Welcome!"]
       [:form {:action "/signup"}
        [:input {:type "text" :name "email" :placeholder "Email"}]
        [:input {:type "hidden" :name "channel" :placeholder "Channel" :value channel}]
        [:button {:class "btn-large" :type "submit"}
         "Get your Slack Invite!"]]]]]]))

(defn signup-complete-page []
  (html
   (page-head)
   [:body
    [:div.container
     [:div.hero
      [:div#logo
       [:img {:src "img/logo-with-text.png"}]]
      [:div#hero-text.flow-text
       [:h1 "Success!"]
       [:p "Check your email to verify your invite."]]]]]))

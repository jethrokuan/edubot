(ns edubot.handler
  (:require [compojure.core :refer [defroutes POST GET]]
            [compojure.route :as route]
            [edubot.slack :refer [user-id-from-email send-inv process-signup]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [edubot.pages.signup :refer [signup-page]]))

(defroutes routes
  (GET "/" {params :params} [] (let [channel (:channel params)]
                                 (signup-page channel)))
  (GET "/signup" {params :params} [] (let [email (:email params)
                                           codeword (:codeword params)]
                                       (process-signup email codeword)))
  (route/resources "/")
  (GET "/userid" [] (str (user-id-from-email "steven@tinkertanker.com")))
  #_(GET "/test" [] (signup-complete-page))
  (POST "/" [r] (send-inv r)))

(def handler
  (wrap-defaults routes (assoc-in site-defaults [:security :anti-forgery] false)))

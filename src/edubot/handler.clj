(ns edubot.handler
  (:require [compojure.core :refer [defroutes POST GET]]
            [compojure.route :as route]
            [edubot.slack :refer [user-id-from-email send-inv process-signup]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [edubot.pages.index :refer [index-page]]))

(defroutes routes
  (GET "/" [] (index-page))
  (GET "/signup" {params :params} [] (let [email (:email params)
                                           codeword (:codeword params)]
                                       (process-signup email codeword)))
  (route/resources "/")
  (GET "/userid" [] (str (user-id-from-email "steven@tinkertanker.com")))
  (POST "/" [r] (send-inv r)))

(def handler
  (wrap-defaults routes (assoc-in site-defaults [:security :anti-forgery] false)))

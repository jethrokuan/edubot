(ns edubot.handler
  (:require [compojure.core :refer [defroutes POST GET]]
            [compojure.route :as route]
            [edubot.slack :refer [user-id-from-email send-inv]]
            [edubot.pages.index :refer [index-page]]))

(defroutes handler
  (GET "/" [] (index-page))
  (route/resources "/")
  (GET "/userid" [] (str (user-id-from-email "steven@tinkertanker.com")))
  (POST "/" [r] (send-inv r)))

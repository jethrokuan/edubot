(ns edubot.handler
  (:require [compojure.core :refer [defroutes POST GET]]
            [edubot.slack :refer [user-id-from-email send-inv]]))

(defroutes handler
  (GET "/" [] (str (user-id-from-email "steven@tinkertanker.com")))
  (POST "/" [r] (send-inv r)))

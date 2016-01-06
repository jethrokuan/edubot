(ns edubot.slack
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [compojure.core :refer [GET]]))

(def user-list-url "https://slack.com/api/users.list?token=xoxp-4246027421-17745065153-17744148659-c770e016e4")

(defn user-id-from-email [email]
  (let [res (client/get user-list-url)
        rvec (json/read-str (:body res) :key-fn keyword)
        members (val (second rvec))
        user (first (filter #(= email (get-in % [:profile :email]))
                            members))
        user-id (:id user)]
    user-id))

(defn send-inv [r]
  "Hello World")



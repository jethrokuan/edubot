(ns edubot.slack
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [environ.core :refer [env]]
            [compojure.core :refer [GET]]))

(def user-list-url "https://slack.com/api/users.list?token=")
(def user-list-url-with-token (str user-list-url (env :slack-web-token)))

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



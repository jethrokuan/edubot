(ns edubot.slack
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [environ.core :refer [env]]
            [clojure.data.json :as json]
            [compojure.core :refer [GET]]
            [compojure.core :refer [GET]]))

(def team-invite-url "https://tinkercademy.slack.com/api/users.admin.invite")
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

(defn send-inv [email]
  (client/post team-invite-url {:form-params {:token (env :slack-web-token)
                                              :email email
                                              :set_active true}}))

(defn process-signup [email codeword]
  (let [res (send-inv email)
        body (-> res
                 :body
                 (json/read-str :key-fn keyword))]
    (if (:ok body)
      "true"
      (str body))))

(ns edubot.slack
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [environ.core :refer [env]]
            [compojure.core :refer [GET]]
            [edubot.pages.errors :as errors]
            [edubot.pages.signup :refer [signup-page signup-complete-page]]))

(def TEAM-INVITE-URL "https://tinkercademy.slack.com/api/users.admin.invite")

(def user-list-url-with-token (str "https://slack.com/api/users.list?token=" (env :slack-web-token)))

(defn user-id-from-email [email]
  (let [res (client/get user-list-url-with-token)
        rvec (json/read-str (:body res) :key-fn keyword)
        members (val (second rvec))
        user (first (filter #(= email (get-in % [:profile :email]))
                            members))
        user-id (:id user)]
    user-id))

(defn send-inv [email]
  (client/post TEAM-INVITE-URL {:form-params {:token (env :slack-web-token)
                                              :email email
                                              :set_active true}}))

(defn- dispatch-page-by-error [error]
  (condp = error
    "already_invited" (errors/already-invited)
    "already_in_team" (errors/already-in-team)
    error)
  )

(defn process-signup [email codeword]
  (let [res (send-inv email)
        body (-> res
                 :body
                 (json/read-str :key-fn keyword))]
    (if (:ok body)
      (signup-complete-page)
      (dispatch-page-by-error (:error body))
      )))

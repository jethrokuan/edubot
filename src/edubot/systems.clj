(ns edubot.systems
  (:require [system.core :refer [defsystem]]
            (system.components
             [immutant-web :refer [new-web-server]])
            [edubot.handler :refer [handler]]
            [environ.core :refer [env]]))

(defsystem dev-system
  [:web (new-web-server (env :http-port) handler)])

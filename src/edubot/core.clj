(ns edubot.core
  (:gen-class)
  (:require [reloaded.repl :refer [start go]]
            [edubot.systems :refer [dev-system prod-system]]))

(defn -main
  "Start the Application"
  []
  (reloaded.repl/set-init! prod-system)
  (go))

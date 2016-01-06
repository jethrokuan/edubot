(ns edubot.core
  (:gen-class)
  (:require [reloaded.repl :refer [start go]]
            [edubot.systems :refer [dev-system]]))

(defn -main
  "Start the Application"
  []
  (reloaded.repl/set-init! dev-system)
  (go))

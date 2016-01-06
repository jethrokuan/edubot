(set-env!
 :source-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.7.0"]
                 [org.danielsz/system "0.2.0"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [environ "1.0.1"]
                 [boot-environ "1.0.1"]

                 ;; Server
                 [org.immutant/web "2.1.1"]
                 [compojure "1.4.0"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.json "0.2.6"]
                 ])

(require '[edubot.systems :refer [dev-system]]
         '[reloaded.repl :refer [init start stop go reset]]
         '[environ.boot :refer [environ]]
         '[system.boot :refer [system]])

(deftask dev []
  (comp
   (environ :env {:http-port "3000"
                  :slack-web-token slack-web-token})
   (watch :verbose true)
   (system :sys #'dev-system :auto-start true :hot-reload true :files ["handler.clj"])
   #_(reload)
   (repl :server true)))

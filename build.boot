(set-env!
 :source-paths #{"src"}
 :resource-paths #{"resources"}
 :dependencies '[[org.clojure/clojure "1.7.0" :scope "provided"]
                 [org.danielsz/system "0.2.0"]
                 [org.clojure/tools.nrepl "0.2.12"]                
                 [environ "1.0.1"]
                 [boot-environ "1.0.1"]
                 [ring/ring-defaults "0.1.5"]

                 ;; Server
                 [hiccup "1.0.5"]
                 [org.immutant/web "2.1.1"]
                 [compojure "1.4.0"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.json "0.2.6"]

                 ;; Assets
                 [mathias/boot-sassc "0.1.5"]])

(require '[edubot.systems :refer [dev-system]]
         '[reloaded.repl :refer [init start stop go reset]]
         '[environ.boot :refer [environ]]
         '[system.boot :refer [system]]
         '[mathias.boot-sassc :refer [sass]]
         '[boot.util :refer [dosh]])

(deftask dev []
  (comp
   (environ :env {:http-port "3000"
                  :slack-web-token slack-web-token})
   (watch :verbose true)
   (system :sys #'dev-system :auto-start true :hot-reload true :files ["handler.clj"])
   #_(reload)
   (repl :server true)
   (target :dir #{"target"})))

(deftask scss []
  (comp
   (watch)
   (sass :sass-file "sass/main.scss"
         :output-dir "../css/"
         :source-map true)
   (target :dir #{"target"})))

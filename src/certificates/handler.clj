(ns certificates.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.etag :refer [wrap-etag]]
            [ring.util.response :refer [response]]
            [ring.adapter.jetty :refer [run-jetty]]
						[clojure.string :refer [split-lines]]
						[clojure.java.io :refer [file]]
            [net.cgrand.enlive-html :as html]))

(html/deftemplate certificate (file "static/sample.html")
  [award]
  [:#awardTitle] (html/content (:title award)))

(defn static-file
  [filename]
  (slurp (str "static/" filename)))

(defn awards
  []
  (split-lines (static-file "awardTitles.txt")))

(defroutes app-routes
  (GET "/meta.json" []
       (static-file "meta.json"))
  (GET "/sample" []
       (apply str (certificate {:title "Most Fabulous Printer User"})))
  (GET "/award" []
       (rand-nth (awards)))
 	(GET "/clojure-version" []
  		 (clojure-version))
  (route/files "/" {:root "static"})
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
  		(wrap-etag)))

(defn -main []
  (let [port-str (System/getenv "PORT")
        port (if port-str (Integer/parseInt port-str) 3000)]
    (run-jetty app {:port port})))

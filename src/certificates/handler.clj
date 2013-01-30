(ns certificates.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [certificates.etag :refer [wrap-etag]]
            [clojure.string :refer [split-lines]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defn static-file
  [filename]
  (slurp (str "static/" filename)))

(defn wrap-log [app]
  (fn [req]
    (println req)
    (app req)))

(defn awards
  []
  (split-lines (static-file "awardTitles.txt")))

(defroutes app-routes
  (GET "/meta.json" []
       (static-file "meta.json"))
  (GET "/sample" []
       (static-file "sample.html"))
  (GET "/award" []
       (rand-nth (awards)))
  (route/files "/" {:root "static"})
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
  		(wrap-etag)
  		(wrap-log)))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port})))

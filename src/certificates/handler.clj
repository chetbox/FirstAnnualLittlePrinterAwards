(ns certificates.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn static-file
  [filename]
  (slurp (str "static/" filename)))

(defroutes app-routes
  (GET "/meta.json" [] (static-file "meta.json"))
  (GET "/sample" [] (static-file "sample.html"))
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

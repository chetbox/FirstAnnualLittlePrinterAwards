(defproject certificates "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [enlive "1.0.1"]]
  :plugins [[lein-ring "0.8.2"]]
  :main "certificates.handler"
  :ring {:handler certificates.handler/app}
  :resources-path "static"
  :profiles {:dev {:dependencies [[ring-mock "0.1.3"]]}})

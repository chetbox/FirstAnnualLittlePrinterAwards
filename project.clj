(defproject certificates "0.1.0-SNAPSHOT"
  :description "First Annual Little Printer Awards"
  :url "http://sarahrules.co.uk/falpa"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]
                 [ring/ring-core "1.1.8"]
                 [ring/ring-jetty-adapter "1.1.8"]
								 [ring.middleware.etag "1.0.0-SNAPSHOT"]
                 [enlive "1.0.1"]]
  :plugins [[lein-ring "0.8.2"]]
  :main "certificates.handler"
  :ring {:handler certificates.handler/app}
  :resources-path "static"
  :profiles {:dev {:dependencies [[ring-mock "0.1.3"]]}})

(ns hpl-website.config
  (:require [environ.core :refer [env]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.middleware.logger :refer [wrap-with-logger]]))

(defn config []
  {:http-port  (new Integer (or (env :port) 10555))
   :middleware [[wrap-defaults api-defaults]
                wrap-with-logger
                wrap-gzip]})

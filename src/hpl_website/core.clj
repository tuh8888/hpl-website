(ns hpl-website.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [hpl-website.handler :as handler]))

(defn -main
  "Web server"
  [port]
  (-> #'handler/app
      (wrap-reload)
      (jetty/run-jetty {:port (Integer. ^String port)})))

(comment
  (let [port 3000]
    (-> #'handler/app
        (wrap-reload)
        (jetty/run-jetty {:port  (Integer. port)
                          :join? false}))))


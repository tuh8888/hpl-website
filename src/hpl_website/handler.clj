(ns hpl-website.handler
  (:require [compojure.core :as compojure]
            [compojure.route :as route]
            [hpl-website.about :as about]
            [hpl-website.index :as index]
            [hpl-website.blog :as blog]
            [ring.middleware.defaults :as ring-defaults]
            [ring.middleware.resource :as ring-resource]))

(compojure/defroutes app-routes
  (compojure/GET "/" [] index/index)
  (compojure/GET "/index" [] index/index)
  about/about-routes
  (compojure/GET "/contact" [] index/contact)
  (compojure/GET "/blog" [date] (blog/index date))
  (route/not-found "Not Found"))

(def app (-> app-routes
             (ring-resource/wrap-resource "")
             (ring-defaults/wrap-defaults ring-defaults/site-defaults)))

(ns hpl-website.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [hpl-website.subs-evts]
            [hpl-website.views :as views]
            [hpl-website.config :as config]
            [reitit.frontend.easy :as rfe]
            [reitit.frontend :as rf]
            [reitit.coercion.spec :as rss]))

(enable-console-print!)

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(def router
  (rf/router
    views/routes
    {:data {:coercion rss/coercion}}))

(defn init-routes! []
  (println "initializing routes")
  (rfe/start!
    router
    views/on-navigate
    {:use-fragment true}))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (init-routes!)
  (reagent/render [views/router-component {:router router}]
                  (.getElementById js/document "app")))

(defn render []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))

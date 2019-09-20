(ns hpl-website.about
  (:require [compojure.core :as compojure]
            [hpl-website.util :as util]))

(def page-title "About")

(defn index
  "About Root"
  [request]
  (util/hpl-page page-title
    [:div]))

(defn research
  "About Root"
  [request]
  (util/hpl-page page-title
    [:div]))

(defn music
  "About Root"
  [request]
  (util/hpl-page page-title
    [:div]))

(compojure/defroutes about-routes
  (compojure/context "/about" []
    (compojure/GET "/" [] index)
    (compojure/GET "/research" [] research)
    (compojure/GET "/music" [] music)))

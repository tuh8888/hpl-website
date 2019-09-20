(ns hpl-website.about
  (:require [hpl-website.util :refer [hpl-page]]
            [compojure.core :as compojure]))

(def page-title "About")

(defn index
  "About Root"
  [request]
  (hpl-page  page-title
    [:div]))

(defn research
  "About Root"
  [request]
  (hpl-page page-title
    [:div]))

(defn music
  "About Root"
  [request]
  (hpl-page  page-title
    [:div]))

(compojure/defroutes about-routes
  (compojure/context "/about" []
    (compojure/GET "/" [] index)
    (compojure/GET "/research" [] research)
    (compojure/GET "/music" [] music)))

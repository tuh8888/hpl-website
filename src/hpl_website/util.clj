(ns hpl-website.util
  (:require [hiccup.page :as page]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def my-info (-> "my-info.edn"
                 (io/resource)
                 (io/file)
                 (slurp)
                 (edn/read-string)))
(def sub-page "%s | %s")
(def site-title (:name my-info))

(def nav
  [:ul
   [:li [:a {:href "index"} "Home"]]
   [:li [:a {:href "about"} "About"]]
   [:li [:a {:href "contact"} "Contact"]]])

(defn hpl-page
  [page-title content]
  (page/html5 {:lang "en"}
              [:head [:title (if page-title
                               (format sub-page site-title page-title)
                               site-title)]
               (page/include-css "css/index.css")]
              [:body
               [:div [:h1 site-title]]
               [:div#nav nav]
               [:div [:h2 (when page-title page-title)]]
               [:div content]]))

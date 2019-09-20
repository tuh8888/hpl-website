(ns hpl-website.util
  (:require [hiccup.page :as page]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [hiccup.element :as el]
            [util :as hpl-util]))

(def my-info (-> "my-info.edn"
                 (io/resource)
                 (io/file)
                 (slurp)
                 (edn/read-string)))
(def sub-page "%s | %s")
(def site-title (:name my-info))

(defn linkify
  "Convert named urls to list of links."
  [links]
  (map (fn [[name url]]
         (el/link-to url name))
       links))

(defn nav-drop-down
  "Dropdown menu"
  [parent-url parent-content children]
  [:div.dropdown
   [:span (el/link-to parent-url parent-content)]
   (->> children
        (hpl-util/map-kv #(format "%s/%s" parent-url %))
        (linkify)
        (into [:div.dropdown-content]))])

(def nav
  (el/unordered-list [(el/link-to "/index" "Home")
                      (nav-drop-down "/about" "About"
                        {"Research" "research"
                         "Music"    "music"})
                      (el/link-to "/contact" "Contact")
                      (el/link-to "/blog" "Blog")]))

(defn hpl-page
  [page-title content]
  (page/html5 {:lang "en"}
              [:head [:title (if page-title
                               (format sub-page site-title page-title)
                               site-title)]
               (page/include-css "/css/index.css")]
              [:body
               [:div [:h1 site-title]]
               [:div#nav nav]
               [:div [:h2 (when page-title page-title)]]
               [:div content]]))

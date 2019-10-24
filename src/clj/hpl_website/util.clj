(ns hpl-website.util
  (:require [hiccup.page :as page]
            [hiccup.element :as el]
            [medley.core :as med])
  (:import (java.text SimpleDateFormat)))

(def sub-page "%s | %s")
(def site-title (:name ""#_my-info))

(defn url-friendly-date
  [date]
  (.format (SimpleDateFormat. "MM-dd-yyyy") date))

(def display-date-format (SimpleDateFormat. "MMMM dd, yyyy"))

(defn style [& info]
  {:style (.trim (apply str (map #(let [[kwd val] %]
                                    (str (name kwd) ":" val "; "))
                                 (apply hash-map info))))})

(defn =param?
  "Filter if a parameter is present"
  [param f k m]
  (or (not param)
      (= param (f (k m)))))

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
        (med/map-vals #(format "%s/%s" parent-url %))
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
  ([page-title head content]
   (page/html5 {:lang "en"}
               (into [:head [:title (if page-title
                                      (format sub-page site-title page-title)
                                      site-title)]]
                     head)
               [:body
                [:div#site-title [:h1 site-title]]
                [:div#nav nav]
                [:div#page-title [:h2 (when page-title page-title)]]
                [:div#content content]]))
  ([page-title content]
   (hpl-page page-title nil content)))

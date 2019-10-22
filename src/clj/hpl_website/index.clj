(ns hpl-website.index
  (:require [hpl-website.util :refer [my-info hpl-page]]
            [hiccup.element :refer [link-to]]))

(defn index
  "Home Page"
  [request]
  (hpl-page nil
    [:div
     [:p (format "Hello, my name is %s." (:name my-info))]
     [:p "I am a PhD. student at the "
      (link-to (:school-link my-info) (:school my-info))]]))

(defn contact
  "Contact Page"
  [request]
  (hpl-page "Contact"
    [:div
     [:ul
      (map
        (fn [[source info]] [:li source ": " (link-to info info)])
        (:contact-info my-info))]]))

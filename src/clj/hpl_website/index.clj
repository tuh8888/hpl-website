(ns hpl-website.index
  (:require [hpl-website.util :refer [my-info hpl-page]]
            [hiccup.element :refer [link-to]]))


(defn contact
  "Contact Page"
  [_]
  (hpl-page "Contact"
    [:div
     [:ul
      (map
        (fn [[source info]] [:li source ": " (link-to info info)])
        (:contact-info my-info))]]))

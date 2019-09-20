(ns hpl-website.blog
  (:require [hpl-website.util :as util]
            [hiccup.element :as el]))

(def page-title "Blog")

(defn index
  [date]
  (let [content (get-in util/my-info [:blog date]
                        (->> util/my-info
                             (:blog)
                             (keys)
                             (map #(el/link-to (format "/blog?date=%s" %) %))
                             (el/ordered-list)))]
    (util/hpl-page page-title
      [:div
       [:h3 date]
       content])))

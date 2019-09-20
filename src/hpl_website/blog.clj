(ns hpl-website.blog
  (:require [hpl-website.util :as util]
            [hiccup.element :as el]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [markdown.core :as md]
            [hiccup.util :as hiccup-util])
  (:import (java.text SimpleDateFormat)
           (java.io File)))

(def page-title "Blog")

(defn read-post
  [f]
  (-> f
      (slurp)
      (edn/read-string)
      (assoc :date (.lastModified ^File f))))

(def display-date-format (SimpleDateFormat. "MMMM dd, yyyy"))

(defn blog-url
  "Unique link for a particular blog entry."
  [{:keys [date title]}]
  (hiccup-util/url "/blog" {:date  (util/url-friendly-date date)
                            :title title}))

(defn display-post
  "Display blog"
  [{:keys [date format title content] :as blog}]
  [:div.post
   [:h3 (el/link-to (blog-url blog) title)]
   [:p [:i (.format ^SimpleDateFormat display-date-format date)]]
   [:div.post-content
    (case format
      "html" content
      "md" (md/md-to-html-string content)
      :else content)]
   [:hr]])

(defn index
  [date title]
  (util/hpl-page page-title
    [:div#blog
     (->> "blog"
          (io/resource)
          (io/file)
          (file-seq)
          (rest)
          (map read-post)
          (sort-by :date)
          (reverse)
          (filter #(or (not title)
                       (= title (hiccup-util/escape-html (:title %)))))
          (filter #(or (not date)
                       (= date (util/url-friendly-date (:date %)))))
          (map display-post))]))


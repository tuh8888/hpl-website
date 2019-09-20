(ns hpl-website.blog
  (:require [hpl-website.util :as util]
            [hiccup.element :as el]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [markdown.core :as md]
            [hiccup.util :as hiccup-util])
  (:import (java.text SimpleDateFormat)
           (java.util Date)))

(def page-title "Blog")

(def blogs (->> "blog"
                (io/resource)
                (io/file)
                (file-seq)
                (rest)
                (map slurp)
                (map edn/read-string)
                (map (fn [blog] (update blog :date #(Date. ^String %))))
                (sort-by :date)))

(def display-date-format (SimpleDateFormat. "MMMM dd, yyyy"))

(defn blog-url
  "Unique link for a particular blog entry."
  [{:keys [date title]}]
  (hiccup-util/url "/blog" {:date  (util/url-friendly-date date)
                            :title title}))

(defn display-blog
  "Display blog"
  [{:keys [date format title content] :as blog}]
  [:div.blog
   [:h3 (el/link-to (blog-url blog) title)]
   [:p [:i (.format ^SimpleDateFormat display-date-format date)]]
   [:div.blog-content
    (case format
      "html" content
      "md" (md/md-to-html-string content)
      :else content)]
   [:hr]])

(defn index
  [date title]
  (let [blogs (->> blogs
                   (filter #(or (not title)
                                (= title (hiccup-util/escape-html (:title %)))))
                   (filter #(or (not date)
                                (= date (util/url-friendly-date (:date %))))))]
    (util/hpl-page page-title
      [:div#blog
       (map display-blog blogs)])))


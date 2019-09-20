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

(defn read-blog-posts
  "Read the current blog posts"
  []
  (->> "blog"
       (io/resource)
       (io/file)
       (file-seq)
       (rest)
       (map read-post)
       (sort-by :date)
       (reverse)))

(defn blog-url
  "Unique link for a particular blog entry."
  [{:keys [date title]}]
  (hiccup-util/url "/blog" {:date  (util/url-friendly-date date)
                            :title title}))

(defn display-post
  "Display blog"
  [hide-overflow? {:keys [date format title content] :as blog}]
  [:div.post
   (when hide-overflow?
     (util/style :height "300px"
                 :overflow "hidden"))
   [:h3 (el/link-to (blog-url blog) title)]
   [:p [:i (.format ^SimpleDateFormat util/display-date-format date)]]
   [:div.post-content
    (case format
      "html" content
      "md" (md/md-to-html-string content)
      content)]
   (when hide-overflow? [:hr])])

(defn index
  [date title]
  (util/hpl-page page-title
    [:div#blog
     (let [blogs (->> (read-blog-posts)
                      (filter #(util/=param? title hiccup-util/escape-html :title %))
                      (filter #(util/=param? date util/url-friendly-date :date %)))]
       (if (= 1 (count blogs))
         (display-post false (first blogs))
         (map #(display-post true %) blogs)))]))


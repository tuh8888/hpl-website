(ns hpl-website.routes
  (:require [clojure.java.io :as io]
            [compojure.core :refer [ANY GET PUT POST DELETE routes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [response]]
            [clojure.edn :as edn]
            #_[markdown.core :as md]
            [ring.middleware.transit :refer [wrap-transit-response]])
  (:import [java.io File]
           [java.util Date]))

(defn read-post
  [f]
  (-> f
      (slurp)
      (edn/read-string)
      (assoc :date (Date. (.lastModified ^File f)))))

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

(defn home-routes [_]
  (routes
   (GET "/" _
     (-> "public/index.html"
         io/resource
         io/input-stream
         response
         (assoc :headers {"Content-Type" "text/html; charset=utf-8"})))
   (resources "/")
   (-> "/get-blogs/"
       (GET _ (-> (read-blog-posts) (response)))
       (wrap-transit-response))))

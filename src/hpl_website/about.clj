(ns hpl-website.about
  (:require [compojure.core :as compojure]
            [hpl-website.util :as util]
            [hiccup.element :as el]))

(def page-title "About")

(compojure/defroutes about-routes
  (compojure/context "/about" []
    (compojure/GET "/" []
      (util/hpl-page page-title
        [:div
         [:p "The code for this site can be found "
          (el/link-to "https://github.com/tuh8888/hpl-website" "here"
                      ".")]
         [:p "Built entirely in Clojure. Tools/packages used:"
          (el/unordered-list
            (util/linkify
              {"Ring" "https://github.com/ring-clojure/ring"
               "Compojure" "https://github.com/weavejester/compojure"
               "Hiccup" "https://github.com/weavejester/hiccup"
               "Timbre" "https://github.com/ptaoussanis/timbre"}))]]))


    (compojure/GET "/research" []
      (util/hpl-page page-title
        [:div]))
    (compojure/GET "/music" []
      (util/hpl-page page-title
        [:div]))))

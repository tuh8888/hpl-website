(ns hpl-website.about
  (:require [compojure.core :as compojure]
            [hpl-website.util :as util]
            [hiccup.element :as el]
            [clojure.data.json :as json]
            [hiccup.page :as page]))

(def page-title "About")

(def tools {"Ring"         "https://github.com/ring-clojure/ring"
            "Compojure"    "https://github.com/weavejester/compojure"
            "Hiccup"       "https://github.com/weavejester/hiccup"
            "Timbre"       "https://github.com/ptaoussanis/timbre"
            "markdown-clj" "https://github.com/yogthos/markdown-clj"})

(def code {:owner "tuh8888"
           :repo  "hpl-website"
           :ref   "master"
           :embed [{:path "README.md"}
                   {:path "src/hpl_website/core.clj"}
                   {:path "src/hpl_website/about.clj"}]})

(compojure/defroutes about-routes
  (compojure/context "/about" []
    (compojure/GET "/" []
      (util/hpl-page page-title
        [(page/include-css "/css/github-embed.css")]
        [:div
         [:p "The code for this site is shown below."]
         [:p "Built entirely in Clojure. Tools/packages used:"]
         (el/unordered-list (util/linkify tools))
         [:div#settings-object (util/style :height "500px"
                                           :margin-bottom "2rem")]
         [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.min.js"}]
         [:script {:src "/js/github-embed.min.js"}]
         [:script (format "githubEmbed('#settings-object', %s);" (json/write-str code))]]))

    (compojure/GET "/research" []
      (util/hpl-page page-title
        [:div]))
    (compojure/GET "/music" []
      (util/hpl-page page-title
        [:div]))))

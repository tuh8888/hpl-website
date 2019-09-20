(ns hpl-website.about
  (:require [compojure.core :as compojure]
            [hpl-website.util :as util]
            [hiccup.element :as el]
            [clojure.data.json :as json]))

(def page-title "About")

(compojure/defroutes about-routes
  (compojure/context "/about" []
    (compojure/GET "/" []
      (util/hpl-page page-title
        [:div
         [:p "The code for this site can be found "
          (el/link-to "https://github.com/tuh8888/hpl-website" "here"
                      ".")]
         [:p "Built entirely in Clojure. Tools/packages used:"]
         (el/unordered-list
           (util/linkify
             {"Ring"         "https://github.com/ring-clojure/ring"
              "Compojure"    "https://github.com/weavejester/compojure"
              "Hiccup"       "https://github.com/weavejester/hiccup"
              "Timbre"       "https://github.com/ptaoussanis/timbre"
              "markdown-clj" "https://github.com/yogthos/markdown-clj"}))
         [:div#settings-object
          (util/style :height "500px"
                      :margin-bottom "2rem")]
         [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.min.js"}]
         [:script {:src "/js/github-embed.min.js"}]
         (let [settings {"owner" "tuh8888"
                         "repo"  "hpl-website"
                         "ref"   "master"
                         "embed" [{:path "src/hpl_website/index.clj"}
                                  {:path "README.md"}]}]
           [:script (format "githubEmbed('#settings-object', %s);"
                            (json/write-str settings))])]))

    (compojure/GET "/research" []
      (util/hpl-page page-title
        [:div]))
    (compojure/GET "/music" []
      (util/hpl-page page-title
        [:div]))))

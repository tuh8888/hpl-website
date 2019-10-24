(ns hpl-website.views
  (:require [hpl-website.util :refer [<sub >evt] :as util]
            [hpl-website.subs-evts :as se]
            [reitit.core :as r]
            [reitit.frontend.easy :as rfe]
            [clojure.string :as str]
            [data-table.views :as dt]))

(defn embed-github
  []
  [:div
   [:div#settings-object {:style {:height        "500px"
                                  :margin-bottom "2rem"}}]
   [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/6.23.0/polyfill.min.js"}]
   [:script {:src "/js/github-embed.min.js"}]
   [:script (str "githubEmbed('#settings-object', "
                 (->> (<sub [::se/code-used])
                      (clj->js)
                      (js/JSON.stringify))
                 ");")]])

(defn proficiencies-header-render-fn
  [pro]
  (fn [_]
    [:div {:on-click #(>evt [::se/toggle-sort-pros pro])}
     pro]))

(defn about-this-site
  []
  [:div
   [:p "Built entirely in Clojure. Tools/packages used:"]
   #_[embed-github]
   [:ul
    (for [{:keys [name url]} (<sub [::se/tools-used])]
      ^{:key (str (random-uuid))}
      [:li
       [:a {:href url}
        name]])]])

(defn tool-table
  "Table of my tools"
  []
  [:div
   [dt/data-table [::se/my-proficiencies]
    (for [pro (<sub [::se/proficiencies-vals])]
      {:col-key              [pro]
       :col-header-render-fn (proficiencies-header-render-fn pro)
       :col-header-options   {:class (str/join " "
                                               ["sorted-by"
                                                (if (<sub [::se/sorted-pro? pro])
                                                  "asc"
                                                  "desc")])}
       :render-fn            #(case pro
                                :url [:a {:href %}
                                      %]
                                %)})

    {:row-options (fn [_]
                    {})
     :header      (fn [_]
                    {:on-click #()})}]])

(defn about
  []
  [:div
   ;; TODO Add some d3 tables summarizing tables below
   ;; TODO Add ability to search.
   [tool-table]
   [about-this-site]])

(defn music
  [])

(defn research
  [])

(defn contact
  []
  [:div
   [:ul
    (for [[source info] (<sub [::se/contact-info])]
      ^{:key (random-uuid)}
      [:li source ": "
       [:a {:href info}
        info]])]])

(defn blog
  []
  (let [blogs (<sub [::se/blogs])]
    [:div#blog

     (->> (for [{:keys [date title format content url]} blogs]
            ^{:key (str (random-uuid))}
            [:div.post
             [:h3
              [:a {:href url}]
              title]
             [:p [:i (util/format-date date)]]
             (let [[content content'] (case format
                                        :html [nil content]
                                        :md [nil (util/html->md content)]
                                        :hiccup [content]
                                        [content])]
               [:div.post-content
                (merge (when (< 1 (count blogs))
                         {:style {:height   "100px"
                                  :overflow :hidden}})
                       (when content' {:dangerouslySetInnerHTML {:__html content'}}))
                content])
             [:hr]]))]))

(defn single-blog
  [])

(defn home
  []
  [:div#index
   [:p "Hello, my name is " (<sub [::se/name]) "."]
   [:p "I am a PhD. student at the "
    [:a {:href (<sub [::se/school-link])}
     (<sub [::se/school])]]])

(defn marked-link
  [current-route route]
  (let [route-name (get-in route [:data :name])
        text       (get-in route [:data :link-text])]
    [:a {:href (rfe/href route-name nil nil)}
     (when (= route-name (-> current-route :data :name)) "> ")
     text]))

(defn nav-drop-down
  [current-route parent-route child-routes]
  [:div.dropdown
   [:span
    [marked-link current-route parent-route]]
   [:div.dropdown-content
    (for [route child-routes]
      ^{:key (str (random-uuid))}
      [marked-link current-route route])]])

(def nav-routes
  [[""
    {:name      ::home
     :view      home
     :link-text "Home"}]
   ["about"
    [""
     {:name      ::about
      :link-text "About"
      :view      about}]
    ["/research"
     {:name      ::research
      :link-text "Research"
      :view      research}]
    ["/music"
     {:name      ::music
      :link-text "Music"
      :view      music}]]
   ["blogs"
    {:name        ::blogs
     :link-text   "Blog"
     :view        blog
     :controllers [{:start #(>evt [::se/cache-blogs])}]}]
   ["contact"
    {:name      ::contact
     :link-text "Contact"
     :view      contact}]])

(defn nav
  [{:keys [router current-route]}]
  [:div#nav
   [:ul
    ;;TODO fix navigation.
    (for [route (->> nav-routes
                     (map rest)
                     (map (fn [v]
                            (if (= (count v) 1)
                              (->> v (first) (:name) (r/match-by-name router))
                              (map #(->> % (second) :name (r/match-by-name router))
                                   v)))))]
      ^{:key (str (random-uuid))}
      [:li
       (if (map? route)
         [marked-link current-route route]
         [nav-drop-down current-route
          (first route)
          (rest route)])])]])

(defn title
  []
  [:div#site-title>h1
   (<sub [::se/name])])

(defn body
  [route]
  (when route
    [(-> route :data :view)]))

(defn router-component
  [{:keys [router]}]
  (let [current-route (<sub [::se/current-route])]
    [:div
     [title]
     [nav {:router router :current-route current-route}]
     [body current-route]]))

(def routes
  ["/"
   [nav-routes]
   ["blog/:id"
    {:name ::blog
     :view single-blog}]])


(defn on-navigate [new-match]
  (when new-match
    (>evt [::se/navigated new-match])))


(ns hpl-website.views
  (:require [hpl-website.util :refer [<sub >evt]]
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

(defn about
  []
  [:div
   [:p "The code for this site is shown below."]
   [:p "Built entirely in Clojure. Tools/packages used:"]
   [:ul
    (for [[name url] (<sub [::se/tools-used])]
      ^{:key (str (random-uuid))}
      [:li
       [:a {:href url}
        name]])]
   #_[embed-github]
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
     :header      (fn [i]
                    {:on-click #(println i)})}]])

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
  [:div#blog
   (let [blogs (<sub [::se/blogs])]
     (->> (for [{:keys [date title content url]} blogs]
            [:div.post (when (= 1 (count blogs)) {:height "300px"}
                                                 :overflow :hidden)
             [:h3
              [:a {:href url}]
              title]
             [:p [:i date]]
             [:div.post-content
              content]])                                    ;;TODO Format md as html
          (interpose [:hr])))])

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

(defn recur-group-by
  [f i v]
  (if (>= i (max (->> v
                      (map f)
                      (map count)
                      (apply max))))
    v
    (let [grouped (group-by #(nth (f %) i "") v)]
      (zipmap (keys grouped)
              (->> grouped
                   (vals)
                   (map #(recur-group-by f (inc i) %)))))))

(defn group-routes
  [router]
  (->> router
       (r/route-names)
       (map #(r/match-by-name router %))
       (map #(update % :path str/split #"/"))
       (recur-group-by :path 0)
       (vals)
       (first)
       (vals)))

(defn nav
  [{:keys [router current-route]}]
  [:div#nav
   [:ul
    ;;TODO fix navigation.
    (for [routes (group-routes router)]
      ^{:key (str (random-uuid))}
      [:li
       (if (vector? routes)
         (for [route routes]
           ^{:key (str (random-uuid))}
           [marked-link current-route route])
         [nav-drop-down current-route (first (get routes ""))
          (->> (dissoc routes "") (vals) (map first))])])]])

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
   [""
    {:name      ::home
     :view      home
     :link-text "Home"
     :controllers
                [{:start (fn [& _] (println "Entering home page"))
                  :stop  (fn [& _] (println "Leaving home page"))}]}]
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
   ["blog"
    {:name      ::blog
     :link-text "Blog"
     :view      blog}]
   ["contact"
    {:name      ::contact
     :link-text "Contact"
     :view      contact}]])

(defn on-navigate [new-match]
  (when new-match
    (>evt [::se/navigated new-match])))


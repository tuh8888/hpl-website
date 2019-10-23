(ns hpl-website.views
  (:require [hpl-website.util :refer [<sub >evt]]
            [hpl-website.subs-evts :as se]
            [reitit.core :as r]
            [reitit.frontend.easy :as rfe]
            [clojure.string :as str]))

(defn about
  [])

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
  [])

(defn home
  []
  [:div#index
   [:p "Hello, my name is " (<sub [::se/name]) "."]
   [:p "I am a PhD. student at the "
    [:a {:href (<sub [::se/school-link])}
     (<sub [::se/school])]]])

(defn nav-drop-down
  [parent-url parent-content children]
  [:div.dropdown
   [:span
    [:a {:href parent-url}
     parent-content]]
   [:div.dropdown-content
    (for [[content link] children]
      ^{:key (str (random-uuid))}
      [:a {:href (str parent-url "/" link)}
       content])]])

(defn recur-group-by
  [i v]
  (if (>= i (max (->> v
                      (map :path)
                      (map count)
                      (apply max))))
    v
    (let [grouped (group-by #(nth (:path %) i "") v)]
      (zipmap (keys grouped)
              (->> grouped
                   (vals)
                   (map #(recur-group-by (inc i) %)))))))

(comment
    (let [routes (->> hpl-website.core/router
                      (r/route-names)
                      (map #(r/match-by-name hpl-website.core/router %)))
          routes (map #(update % :path str/split #"/") routes)]
      routes
      (recur-group-by 0 routes #_[[0 1 2] [0 1 3] [0 2 2]])))

(defn nav
  [{:keys [router current-route]}]
  [:div#nav
   [:ul
    ;;TODO fix navigation.
    (for [route-name (r/route-names router)
          :let [route (r/match-by-name router route-name)
                text  (-> route :data :link-text)]]
      ^{:key route-name}
      [:li
       [:a {:href (rfe/href route-name nil nil)}
        (when (= route-name (-> current-route :data :name)) "> ")
        text]])]])

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
  ;;TODO add these routes
  ;[[:a {:href "/index"} "Home"]
  ; [nav-drop-down "/about" "About"
  ;  {"Research" "research"
  ;   "Music"    "music"}]
  ; [:a {:href "/contact"} "Contact"]
  ; [:a {:href "/blog"} "Blog"]]
  ["/"
   [""
    {:name      ::home
     :view      home
     :link-text "Home"
     :controllers
                [{:start (fn [& params] (println "Entering home page"))
                  :stop  (fn [& params] (println "Leaving home page"))}]}]
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


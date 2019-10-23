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


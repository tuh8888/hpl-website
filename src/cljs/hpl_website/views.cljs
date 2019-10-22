(ns hpl-website.views
  (:require [hpl-website.util :refer [<sub >evt]]
            [hpl-website.subs-evts :as se]))


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

(defn nav
  []
  [:div#nav
   [:ul
    (for [item [[:a {:href "/index"} "Home"]
                [nav-drop-down "/about" "About"
                 {"Research" "research"
                  "Music"    "music"}]
                [:a {:href "/contact"} "Contact"]
                [:a {:href "/blog"} "Blog"]]]
      ^{:key (str (random-uuid))}
      [:li item])]])


(defn main-panel []
  [:div
   [:div#site-title>h1 (<sub [::se/name])]
   [nav]

   [:p "Hello, my name is " (<sub [::se/name]) "."]
   [:p "I am a PhD. student at the "
    [:a {:href (<sub [::se/school-link])}
     (<sub [::se/school])]]])

(ns hpl-website.styles
  (:require [garden-watcher.def :refer [defstyles]]))

(defstyles style
  [:#nav
   [:ul {:list-style-type  :none
         :margin           0
         :padding          0
         :overflow         :hidden
         :background-color "#333"}]
   [:li {:float :left}
    [:a {:display         :block
         :color           :white
         :text-align      :center
         :padding         "14px 16px"
         :text-decoration :none}
     [:&:hover {:background-color "#111"}]]]]
  [:li.dropdown {:display :inline-block}]
  [:.dropdown-content {:display :none
                       :position :absolute
                       :min-width "160px"
                       :box-shadow "0 8px 16px 0 rgba(0,0,0,0.2)"
                       :z-index 1}
   [:a {:color :black
        :background-color "#6f6f6f"
        :padding "12px 16px"
        :display :block
        :text-align :left}
    [:&:hover {:background-color :black}]]]
  [:.dropdown:hover
   [:.dropdown-content {:display :block}]]

  ;;; BLOG
  [:#blog
   [:.post {:margin "0 auto"}]
   [:.post-content {:margin-left "50px"}]]

  ;;; TABLE
  [:div.ui.list
   [:div.item.active {:font-weight :bold}]
   [:div.item {:padding ".4em !important"}
    [:&:hover {:cursor :pointer
               :background-color "#eee"}]]]
  [:.muted {:color "grey !important"}]
  [:table>thead
   [:th.sorted-by
    [:&:after {:display :inline-block}]]
   [:th.sorted-by.desc:after {:content "\\f0d7"}]
   [:th.sorted-by.asc:after {:content "\\f0d8"}]]
  [:table>thead
   [:th:after {:display :none
               :font-family [:Icons :serif]
               :margin-left ".5em"}]]
  [:tbody>tr.selected {:background-color :aqua}])




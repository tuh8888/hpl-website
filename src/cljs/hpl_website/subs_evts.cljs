(ns hpl-website.subs-evts
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [reg-event-db reg-event-fx
                                   reg-sub subscribe
                                   reg-fx]]
            [hpl-website.db :as db]
            [reitit.frontend.controllers :as rfc]
            [reitit.frontend.easy :as rfe]))

(reg-event-db :initialize-db
  (fn [_ _]
    db/default-db))

(reg-event-fx ::navigate
  (fn [_ [_ & route]]
    {::navigate! route}))

(reg-event-db ::navigated
  (fn [db [_ new-match]]
    (let [old-match   (:current-route db)
          controllers (rfc/apply-controllers (:controllers old-match) new-match)]
      (assoc db :current-route (assoc new-match :controllers controllers)))))

(reg-fx
  ::navigate!
  (fn [route]
    (apply rfe/push-state route)))

(reg-sub
  ::current-route
  (fn [db]
    (:current-route db)))

(reg-sub ::name
  (fn [db]
    (get-in db [:my-info :name])))

(reg-sub ::school-link
  (fn [db]
    (get-in db [:my-info :school-link])))

(reg-sub ::school
  (fn [db]
    (get-in db [:my-info :school])))

(reg-sub ::contact-info
  (fn [db]
    (get-in db [:my-info :contact-info])))

(reg-sub ::tools-used
  (fn [db]
    (get-in db [:about :tools])))

(reg-sub ::code-used
  (fn [db]
    (get-in db [:about :code])))

(reg-sub ::my-proficiencies
  (fn [db]
    (get-in db [:my-info :proficiencies])))

(reg-sub ::sorted-pros
  (fn [db]
    (get-in db [:sorted-pros])))

(reg-sub ::sorted-pro?
  (fn [_ _]
    (subscribe [::sorted-pros]))
  (fn [roles [_ _ role]]
    (contains? roles role)))

(reg-event-db ::toggle-sort-pros
  (fn [db [_ pro]]
    (let [sorted? (get-in db [:sorted-pros pro])]
      (-> db
          (update-in [:sorted-pros] #(if sorted?
                                       (disj % pro)
                                       (conj (or % #{}) pro)))
          (update-in [:my-info :proficiencies]
                     (fn [data]
                       (cond->> data
                                true (sort-by #(let [v (get-in % [pro])]
                                                 (if (coll? v)
                                                   (first v)
                                                   v)))
                                sorted? (reverse)
                                true (vec))))))))

(defn unique-keys
  [m]
  (->> m
       (mapcat keys)
       (distinct)
       (sort)))

(reg-sub ::proficiencies-vals
  :<- [::my-proficiencies]
  unique-keys)

(reg-sub ::blogs
  (fn [db]
    ()))
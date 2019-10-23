(ns hpl-website.subs-evts
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [reg-event-db reg-event-fx
                                   reg-sub
                                   reg-fx]]
            [hpl-website.db :as db]
            [reitit.frontend.controllers :as rfc]
            [reitit.frontend.easy :as rfe]))

(reg-event-db :initialize-db
 (fn  [_ _]
   db/default-db))

(reg-event-fx ::navigate
  (fn [_ [_ & route]]
    {::navigate! route}))

(reg-event-db ::navigated
  (fn [db [_ new-match]]
    (let [old-match (:current-route db)
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
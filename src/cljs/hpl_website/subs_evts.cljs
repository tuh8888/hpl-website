(ns hpl-website.subs-evts
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [reg-event-db reg-event-fx
                                   reg-sub]]
            [hpl-website.db :as db]))

(reg-event-db :initialize-db
 (fn  [_ _]
   db/default-db))

(reg-sub ::name
  (fn [db]
    (get-in db [:my-info :name])))

(reg-sub ::school-link
  (fn [db]
    (get-in db [:my-info :school-link])))

(reg-sub ::school
  (fn [db]
    (get-in db [:my-info :school])))
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
    (:name db)))

(reg-sub ::school-link
  (fn [db]
    (:school-link db)))

(reg-sub ::school
  (fn [db]
    (:school db)))
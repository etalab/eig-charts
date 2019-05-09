(ns eig.core
  (:require [reagent.core :as reagent]
            [eig.views :as views]
            [bidi.bidi :as bidi]
            [accountant.core :as accountant]))

(def app-routes
  ["/" {""               :a-propos
        "a-propos"       :a-propos
        "carte"          :carte
        "promo"          :promo
        "selection"      :selection
        "competences"    :competences
        "genre"          :genre
        "parcours"       :parcours
        "financement"    :financement
        "depenses"       :depenses
        "accompagnement" :accompagnement
        "communication"  :communication}])

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (reset! views/current-page (:handler (bidi/match-route app-routes path))))
    :path-exists?
    (fn [path] (boolean (bidi/match-route app-routes path)))})
  (accountant/dispatch-current!)
  (mount-root))

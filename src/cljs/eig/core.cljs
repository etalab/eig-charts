(ns eig.core
  (:require [reagent.core :as reagent]
            [eig.views :as views]
            [accountant.core :as accountant]
            [secretary.core :as secretary :include-macros true]))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(secretary/defroute "/#a-propos" []
  (reset! views/current-page "a-propos"))
(secretary/defroute "/#carte" []
  (reset! views/current-page "carte"))
(secretary/defroute "/#promo" []
  (reset! views/current-page "promo"))
(secretary/defroute "/#selection" []
  (reset! views/current-page "selection"))
(secretary/defroute "/#competences" []
  (reset! views/current-page "competences"))
(secretary/defroute "/#genres" []
  (reset! views/current-page "genres"))
(secretary/defroute "/#parcours" []
  (reset! views/current-page "parcours"))
(secretary/defroute "/#financement" []
  (reset! views/current-page "financement"))
(secretary/defroute "/#depenses" []
  (reset! views/current-page "depenses"))
(secretary/defroute "/#accompagnement" []
  (reset! views/current-page "accompagnement"))
(secretary/defroute "/#communication" []
  (reset! views/current-page "communication"))

(defn ^:export init []
  (accountant/configure-navigation!
   {:nav-handler  (fn [path] (secretary/dispatch! path))
    :path-exists? (fn [path] (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))

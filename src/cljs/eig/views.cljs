(ns eig.views
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [accountant.core :as accountant]
            [eig.charts :as charts]
            [eig.report :as report]))

(def current-page (reagent/atom "a-propos"))

(defn layout-content-area-wrapper []
  (let [content (get charts/explications @current-page)]
    [ant/layout-content
     [ant/row
      [ant/col {:span 18}
       [:div {:class "content-area"}
        [:div {:style {:padding ".2em 1em"}}
         [:h1 (:title content)]
         [(:component content)]]]]
      (when-let [ctx (:context content)]
        [ant/col {:span 6}
         [ant/card {:style {:margin "10px" :font-size "1.4em"}}
          [:p ctx]]])]]))

(defn side-menu []
  [ant/menu {:mode     "inline" :theme "dark" :style {:height "100%"}
             :on-click (fn [e] (accountant/navigate! (.-key e)))}
   [ant/menu-item {:key "#promo"} "Promotions EIG"]
   [ant/menu-item {:key "#selection"} "Sélection"]
   [ant/menu-sub-menu {:title "Profils EIG"}
    [ant/menu-item {:key "#competences"} "Compétences"]
    [ant/menu-item {:key "#genre"} "Genre"]
    [ant/menu-item {:key "#parcours"} "Parcours"]]
   [ant/menu-item {:key "#financement"} "Financement"]
   [ant/menu-item {:key "#depenses"} "Dépenses"]
   [ant/menu-item {:key "#accompagnement"} "Accompagnement"]
   [ant/menu-item {:key "#communication"} "Communication"]
   [ant/menu-item {:key "#carte"} "Carte des défis"]
   [ant/menu-item {:key "#a-propos"} "À propos"]])

(defn main-panel []
  [ant/locale-provider {:locale (ant/locales "fr_FR")}
   [ant/layout
    [ant/layout-sider [side-menu]]
    [ant/layout
     [layout-content-area-wrapper]
     [ant/layout-footer
      [:p
       [:a {:href "https://entrepreneur-interet-general.etalab.gouv.fr/"} "Entrepreneurs d'intérêt général"] " - "
       [:a {:href "https://www.etalab.gouv.fr"} "Etalab"] " / "
       [:a {:href "https://www.numerique.gouv.fr/"} "DINSIC"]]]]]])

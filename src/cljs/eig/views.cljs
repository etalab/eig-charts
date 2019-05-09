(ns eig.views
  (:require [reagent.core :as reagent]
            [antizer.reagent :as ant]
            [accountant.core :as accountant]
            [eig.charts :as charts]
            [eig.report :as report]))

(defn a-propos []
  [:div {:style {:padding "2em"}}
   [:h1 "Présentation des données"]
   [:p "Ce site présente quelques données liées au programme "
    [:a {:href "https://entrepreneur-interet-general.etalab.gouv.fr/"} "Entrepreneurs d'intérêt général."]]
   [:p "Les données exposées ici sous forme de graphiques sont téléchargeables au format .ods."] ; FIXME
   [:h1 "Code source et licence"]
   [:p "Le code source de ce site est "
    [:a {:href "http://github.com/etalab/eig-charts"} "public"] "."]
   [:p "Si vous souhaitez corriger une erreur, merci de le signaler "
    [:a {:href "http://github.com/etalab/eig-charts/issues"} "ici"] " ou par email."]
   [:h1 "Contact"]
   [:p "Pour toute question sur son contenu site, merci d'écrire à "
    [:span.email "entrepreneur-interet-general AT data.gouv.fr"] "."]
   [:p "Pour toute question sur le code source de ce site, merci d'écrire à "
    [:span.email "bastien.guerry AT data.gouv.fr"] "."]])

(defn layout-content-area-wrapper [title explanation component]
  `[:div {:style {:padding ".2em 1em"}}
    [:h1 ~title]
    [:p ~explanation]
    [~component]])

(def current-page (reagent/atom "a-propos"))

(defn layout-content-view [view-name]
  [ant/layout-content {:class "content-area"}
   (case view-name
     :a-propos       [a-propos]
     :carte          (layout-content-area-wrapper "Carte des défis" "Explication optionnelle" charts/chartjs-map) 
     :promo          (layout-content-area-wrapper "Les promotions EIG" "Explication optionnelle" charts/chartjs-promo)
     :selection      (layout-content-area-wrapper "La sélection des EIG" "Explication optionnelle" charts/chartjs-selection)
     :competences    (layout-content-area-wrapper "Les compétences des EIG" "Explication optionnelle" charts/chartjs-competences)
     :genre          (layout-content-area-wrapper "La répartition par genres" "Explication optionnelle" charts/chartjs-genre)
     :parcours       (layout-content-area-wrapper "Les parcours des EIG" "Explication optionnelle" charts/chartjs-parcours)
     :financement    (layout-content-area-wrapper "Le financement du programme EIG" "Explication optionnelle" charts/chartjs-financement)
     :depenses       (layout-content-area-wrapper "Les dépenses du programme EIG" "Explication optionnelle" charts/chartjs-depenses)
     :accompagnement (layout-content-area-wrapper "Le programme d'accompagnement" "Explication optionnelle" charts/chartjs-accompagnement)
     :communication  (layout-content-area-wrapper "La communication du programme EIG" "Explication optionnelle" charts/chartjs-communication)
     [a-propos])])

(defn side-menu []
  [ant/menu {:mode     "inline" :theme "dark" :style {:height "100%"}
             :on-click (fn [e] (accountant/navigate! (.-key e)))}
   [ant/menu-item {:key "/promo"} "Promotions EIG"]
   [ant/menu-item {:key "/selection"} "Sélection"]
   [ant/menu-sub-menu {:title "Profils EIG"}
    [ant/menu-item {:key "/competences"} "Compétences"]
    [ant/menu-item {:key "/genre"} "Genre"]
    [ant/menu-item {:key "/parcours"} "Parcours"]]
   [ant/menu-item {:key "/financement"} "Financement"]
   [ant/menu-item {:key "/depenses"} "Dépenses"]
   [ant/menu-item {:key "/accompagnement"} "Accompagnement"]
   [ant/menu-item {:key "/communication"} "Communication"]
   [ant/menu-item {:key "/carte"} "Carte des défis"]
   [ant/menu-item {:key "/a-propos"} "À propos"]])

(defn main-panel []
  [ant/locale-provider {:locale (ant/locales "fr_FR")}
   [ant/layout
    [ant/layout-sider [side-menu]]
    [ant/layout-content {:style {:padding "20px 20px" :margin "auto"}}
     [layout-content-view @current-page]
     [ant/layout-footer
      [:p
       [:a {:href "https://entrepreneur-interet-general.etalab.gouv.fr/"} "Entrepreneurs d'intérêt général"]
       " - "
       [:a {:href "https://www.etalab.gouv.fr"} "Etalab"]
       " / "
       [:a {:href "https://www.numerique.gouv.fr/"} "DINSIC"]]]]]])

(ns eig.charts
  (:require [reagent.core :as reagent]
            [eig.report :as report]
            [eig.colors :as color]
            [cljsjs.chartjs]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn map-render []
  [:div#map {:style {:height "700px" :margin "20px 0px"}}])

(def admins_locations
  (flatten
   (map (fn [[k v]]
          (for [admin v] (conj admin {:tutelle k})))
        report/administrations_map)))

(defn map-did-mount []
  (let [lmap  (.setView (.map js/L "map") #js [48.8503 2.30831] 7)
        items admins_locations]
    (.addTo (.tileLayer
             js/L "https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
             (clj->js {:attribution
                       "&copy; Openstreetmap France | &copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors"
                       :maxZoom 18}))
            lmap)
    (doseq [{:keys [lat lon admin tutelle eig2017 eig2018 eig2019]} items]
      (.addTo (.bindPopup (.marker js/L (clj->js
                                         (vector (js/parseFloat lon)
                                                 (js/parseFloat lat))))
                          (str "<h1>" tutelle "</h1>"
                               (when-not (= admin tutelle)
                                 (str "<h2>" admin "</h2><br/>"))
                               "<p>2017: " (or eig2017 0) " défi EIG </p>"
                               "2018: " (or eig2018 0) " défi EIG </p>"
                               "2019: " (or eig2019 0) " défi EIG </p>"))
              lmap))))

(defn chartjs-map []
  (reagent/create-class {:reagent-render      map-render
                         :component-did-mount map-did-mount}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn financement []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "bar"
         :options {:title      {:display nil :text "Financement du programme EIG"}
                   :responsive "true"
                   :scales     {:yAxes [{:ticks {:callback (fn [v _ _] (str v "€"))}}]}}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data            (get report/financement "Enveloppe maximale allouée par le PIA")
                               :label           "Enveloppe allouée par le PIA"
                               :backgroundColor color/blue}
                              {:data            (get report/financement "Salaires pris en charge par PIA")
                               :label           "Salaires pris en charge par le financement PIA"
                               :backgroundColor color/green}
                              {:data            (get report/financement "Coût total salaires EIG")
                               :label           "Coût total des salaires des EIG"
                               :backgroundColor color/orange}
                              {:data            (get report/financement "Coût total du programme")
                               :label           "Coût total du programme"
                               :backgroundColor color/red}]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn promotion []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "line"
         :options {:title      {:display nil :text "3 promotions EIG"}
                   :elements   {:line {:tension 0}}
                   :responsive "true"}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data             (get report/programme "Nombre d'EIG")
                               :label            "Nombre d'EIG"
                               :pointRadius      10
                               :pointHoverRadius 15
                               :backgroundColor  color/blue
                               :fill             "boundary"}
                              {:data             (get report/programme "Nombre de mentors")
                               :label            "Nombre de mentors"
                               :pointRadius      10
                               :pointHoverRadius 15
                               :backgroundColor  color/green
                               :fill             nil}
                              {:data             (get report/programme "Nombre de défis")
                               :label            "Nombre de défis"
                               :backgroundColor  color/orange
                               :pointRadius      10
                               :pointHoverRadius 15
                               :fill             nil}]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn selection []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "line"
         :options {:title      {:display nil :text "Sélections des EIG"}
                   :elements   {:line {:tension 0}}
                   :responsive "true"}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data             (get report/selection_des_candidats "Candidats")
                               :label            "Candidats"
                               :pointRadius      10
                               :pointHoverRadius 15
                               :backgroundColor  color/orange
                               :fill             "boundary"}
                              {:data             (get report/selection_des_candidats "Passages devant un jury")
                               :label            "Passages devant un jury"
                               :pointRadius      10
                               :pointHoverRadius 15
                               :backgroundColor  color/blue
                               :fill             nil}
                              {:data             (get report/selection_des_candidats "Lauréats")
                               :label            "Lauréats"
                               :backgroundColor  color/green
                               :pointRadius      10
                               :pointHoverRadius 15
                               :fill             nil}]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn depenses []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "bar"
         :options {:title      {:display nil :text "Dépenses EIG"}
                   :scales     {:xAxes [{:stacked true}]
                                :yAxes [{:stacked true
                                         :ticks   {:callback (fn [v _ _] (str v "%"))}}]}
                   :responsive "true"}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data             (get report/financement "Part des salaires dans le coût total du programme")
                               :label            "Part des salaires"
                               :pointRadius      10
                               :pointHoverRadius 15
                               :backgroundColor  color/blue
                               :fill             "boundary"}
                              {:data             (get report/financement "Part du programme d'accompagnement dans le coût total du programme")
                               :label            "Part du programme d'accompagnement"
                               :pointRadius      10
                               :pointHoverRadius 15
                               :backgroundColor  color/green
                               :fill             nil}
                              {:data             (get report/financement "Part de la communication dans le coût total du programme")
                               :label            "Part de la communication"
                               :backgroundColor  color/red
                               :pointRadius      10
                               :pointHoverRadius 15
                               :fill             nil}
                              {:data             (get report/financement "Part de la recherche dans le coût total du programme")
                               :label            "Part de la recherche"
                               :backgroundColor  color/orange
                               :pointRadius      10
                               :pointHoverRadius 15
                               :fill             nil}
                              ]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def competences-keys
  ["Part des développeurs parmi les EIG"                
   "Part des data scientists parmi les EIG"             
   "Part des designers parmi les EIG"])

(def competences-data
  (into [] (vals (select-keys report/eig competences-keys))))

(defn competences []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "bar"
         :options {:title      {:display nil :text "3 types de compétences parmi les EIG"}
                   :responsive "true"
                   :scales     {:xAxes [{:stacked true}]
                                :yAxes [{:stacked true
                                         :ticks   {:callback (fn [v _ _] (str v "%"))}}]}}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data            (get competences-data 0)
                               :label           "Pourcentage de développeurs au sein de la promotion"
                               :backgroundColor color/blue}
                              {:data            (get competences-data 1)
                               :label           "Pourcentage de Datascientistes au sein de la promotion"
                               :backgroundColor color/green}
                              {:data            (get competences-data 2)
                               :label           "Pourcentage de Designers au sein de la promotion"
                               :backgroundColor color/orange}]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def genre-keys
  ["Pourcentage de femmes parmi les développeurs"
   "Pourcentage de femmes parmi les data scientists"
   "Pourcentage de femmes parmi les designers"
   "Pourcentage de femmes parmi les EIG"])

(def genre-data
  (into [] (vals (select-keys report/eig genre-keys))))

(defn genre []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "bar"
         :options {:title      {:display nil :text "Proportion de femmes par type de compétences"}
                   :responsive "true"
                   :scales     {:yAxes [{:ticks {:callback (fn [v _ _] (str v "%"))}}]}}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data            (get genre-data 0)
                               :label           "Pourcentage de femmes parmi les développeurs"
                               :backgroundColor color/blue}
                              {:data            (get genre-data 1)
                               :label           "Pourcentage de femmes parmi les data scientists"
                               :backgroundColor color/green}
                              {:data            (get genre-data 2)
                               :label           "Pourcentage de femmes parmi les designers"
                               :backgroundColor color/orange}
                              {:data            (get genre-data 3)
                               :label           "Pourcentage de femmes parmi les EIG"
                               :backgroundColor color/red}]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn parcours []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "bar"
         :options {:title      {:display nil :text "Proportion de femmes par type de compétences"}
                   :responsive "true"}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data            (get report/parcours "Sont restés dans la fonction publique")
                               :label           "Sont restés dans la fonction publique"
                               :backgroundColor color/blue}
                              {:data            (get report/parcours "Sont partis en thèse")
                               :label           "Sont partis en thèse"
                               :backgroundColor color/green}
                              {:data            (get report/parcours "Sont partis dans le privé")
                               :label           "Sont partis dans le privé"
                               :backgroundColor color/orange}
                              {:data            (get report/parcours "Se sont mis en freelance/indépendant")
                               :label           "Se sont mis en freelance/indépendant"
                               :backgroundColor color/red}
                              {:data            (get report/parcours "Autres")
                               :label           "Autres"
                               :backgroundColor color/grey}]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn accompagnement []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "bar"
         :options {:title      {:display nil :text "Un programme d'accompagnement"}
                   :responsive "true"}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data            (get report/accompagnement "Sessions d'accompagnement")
                               :label           "Sessions d'accompagnement"
                               :backgroundColor color/blue}
                              {:data            (get report/accompagnement "Semaine de lancement de promotion")
                               :label           "Semaine de lancement de promotion"
                               :backgroundColor color/green}
                              {:data            (get report/accompagnement "Sessions hors-les-murs")
                               :label           "Sessions hors-les-murs"
                               :backgroundColor color/orange}]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn communication []
  (let [context (.getContext (.getElementById js/document "chartjs") "2d")
        chart-data
        {:type    "bar"
         :options {:title      {:display nil :text "Communication autour du programme"}
                   :responsive "true"
                   :scales     {:xAxes [{:stacked true}]
                                :yAxes [{:stacked true}]}}
         :data    {:labels   ["2017" "2018" "2019"]
                   :datasets [{:data            (get report/communication "Articles dans la presse")
                               :label           "Articles dans la presse"
                               :backgroundColor color/green}
                              {:data            (get report/communication "Relais administratifs (numerique.gouv, modernisation.gouv …)")
                               :label           "Articles sur d'autres sites administratifs (numerique.gouv, modernisation.gouv …)"
                               :backgroundColor color/orange}
                              {:data            (get report/communication "Blog Etalab")
                               :label           "Articles sur le blog Etalab"
                               :backgroundColor color/red}
                              {:data            (get report/communication "Articles sur le blog EIG")
                               :label           "Articles sur le blog EIG"
                               :backgroundColor color/blue}]}}]
    (js/Chart. context (clj->js chart-data))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn chartjs-financement
  []
  (reagent/create-class
   {:component-did-mount #(financement)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

(defn chartjs-promo
  []
  (reagent/create-class
   {:component-did-mount #(promotion)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

(defn chartjs-selection
  []
  (reagent/create-class
   {:component-did-mount #(selection)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

(defn chartjs-depenses
  []
  (reagent/create-class
   {:component-did-mount #(depenses)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

(defn chartjs-competences
  []
  (reagent/create-class
   {:component-did-mount #(competences)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

(defn chartjs-genre
  []
  (reagent/create-class
   {:component-did-mount #(genre)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

(defn chartjs-parcours
  []
  (reagent/create-class
   {:component-did-mount #(parcours)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

(defn chartjs-accompagnement
  []
  (reagent/create-class
   {:component-did-mount #(accompagnement)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

(defn chartjs-communication
  []
  (reagent/create-class
   {:component-did-mount #(communication)
    :display-name        "chartjs-component"
    :reagent-render      (fn [] [:canvas {:id "chartjs"}])})) 

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def explications
  {"a-propos"       {:component report/a-propos}
   "carte"          {:title     "Carte des défis"
                     :component chartjs-map}
   "promo"          {:title     "Les promotions EIG"
                     :context   "Les promotions du programme sont composées d’entrepreneurs d’intérêt général et d’agents publics « mentors » qui forment la communauté EIG et travaillent ensemble au sein de défis."
                     :component chartjs-promo}
   "selection"      {:title     "La sélection des EIG"
                     :context   [:div [:p "L’appel à candidatures des EIG est lancé après un appel à projets auprès des administrations."] [:br] [:p "La sélection s’opère en deux temps : les candidats retenus après l’appel à candidatures passent devant un jury composé des mentors des défis et de personnalités issues de l’écosystème du numérique afin de choisir les lauréats qui deviendront des EIG."]]
                     :component chartjs-selection}
   "competences"    {:title     "Les compétences des EIG"
                     :context   [:div [:p "Les EIG amènent dans leurs administrations trois types de compétences : développeurs et développeuses, data scientists et designers."] [:br] [:p "Ce graphe indique la part de chacune de ces compétences dans les trois promotions EIG."]]
                     :component chartjs-competences}
   "genre"          {:title     "La répartition par genres"
                     :context   "La part des femmes au sein des EIG reste minoritaire parmi les participants, mais correspond au taux de candidatures féminines."
                     :component chartjs-genre}
   "parcours"       {:title     "Les parcours des EIG"
                     :context   "À la suite des deux premières promotions, près de la moitié des EIG ont prolongé, sous différentes formes, leurs parcours dans l’administrations. Les données sur les EIG 3 seront disponibles à la fin de leurs défis en novembre 2019."
                     :component chartjs-parcours}
   "financement"    {:title     "Le financement du programme EIG"
                     :context   "Le programme EIG est financé par le Programme d’investissements d’avenir. Depuis la troisième promotion, les administrations participantes cofinancent le salaires des EIG qu’elles accueillent."
                     :component chartjs-financement}
   "depenses"       {:title     "Les dépenses du programme EIG"
                     :context   [:div [:p "Le budget du programme EIG est réparti sur quatre postes de dépenses : les salaires des EIG sont cofinancés par les administrations d’accueil et le PIA, le programme d’accompagnement participe à la réussite des défis, la communication permet d’assurer la notoriété du programme et d’attirer des compétences de pointe dans l’administrations, tandis que les liens avec la recherche permettent un retour sur la dimension de transformation numérique permise par le programme."]
                                 [:br]
                                 [:p "Ce graphe présente la part de chacun de ces postes de dépenses pour le programme EIG. Les dépenses indiquées pour les promotions 1 et 2 correspondent aux dépenses réelles, et celles de la promotion 3, toujours en cours, correspondent aux dépenses prévisionnelles."]]
                     :component chartjs-depenses}
   "accompagnement" {:title     "Le programme d'accompagnement"
                     :context   [:div [:p "Le programme d’" [:a {:href "https://entrepreneur-interet-general.etalab.gouv.fr/accompagnement.html"} "accompagnement"] " du programme EIG se décline sous différentes composantes : un accompagnement collectif des EIG et des mentors, un accompagnement individuel des défis, une mise en relation avec des acteurs pertinents pour la réussite des défis, une médiation entre les EIG et les mentors."]
                                 [:br]
                                 [:p "Ce graphe présente l’accompagnement collectif du programme qui prend la forme de sessions d’accompagnement, de semaine de lancement de la promotion et de sessions hors-les-murs."]]
                     :component chartjs-accompagnement}
   "communication"  {:title     "La communication du programme EIG"
                     :context   "Le programme EIG communique via différents canaux : dans la presse, via les blogs Etalab et EIG et via d’autres sites administratifs."
                     :component chartjs-communication}})

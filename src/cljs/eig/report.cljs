(ns eig.report)

(def administrations_map
  {"Ministère de l'Intérieur"
   [{:admin "Direction de la Gendarmerie Nationale" :eig2018 1 :lon 48.8149 :lat 2.2693}
    {:admin "Direction Interministérielle à l'accueil et à l'intégration des réfugiés" :eig2019 1 :lon 48.8491 :lat 2.40674}
    {:admin "Mission de gouvernance ministérielle des SIC" :eig2017 2 :eig2018 1 :lon 48.8305 :lat 2.38702}
    {:admin "En partenariat avec l'ANTAI" :eig2019 1 :lon 48.1133 :lat -1.71577}]
   "Ministère des Armées"
   [{:admin "Service Hydrographique et Océanographique de la Marine" :eig2018 1 :lon 48.4095 :lat -4.50673}]
   "Ministère de l'Éducation Nationale"
   [{:admin "Numérilab de l'Education Nationale" :eig2018 1 :lon 48.8572 :lat 2.31844}] ;; FIXME: eig2018 = 2?
   "Ministère de l'Enseignement Supérieur, de la Recherche et de l'Innovation"
   [{:admin "Ministère de l'Enseignement Supérieur, de la Recherche et de l'Innovation" :eig2017 1 :eig2018 1 :lon 48.8478 :lat 2.34861}]
   "Direction interministérielle du Numérique et du Système d'Information et de Communication"
   [{:admin "DINSIC" :eig2018 1 :eig2019 1 :lon 48.8503 :lat 2.30831} ;; FIXME: eig2019 = 2 ?
    {:admin "En partenariat avec l'Assemblée Nationale" :eig2019 1 :lon 48.8608 :lat 2.31858}]
   "Ministère de la Culture"
   [{:admin "Bibliothèque Nationale de France" :eig2017 1 :lon 48.8335 :lat 2.37576}
    {:admin "En partenariat avec Orgues en France" :eig2017 1 :lon 48.8624 :lat 2.33889}
    {:admin "En partenariat avec le Mobilier National" :eig2018 1 :lon 48.8333 :lat 2.35120}]
   "Ministère de la Cohésion des Territoires"
   [{:admin "Commisariat Général à l'Egalité des Territoires" :eig2018 1 :lon 48.8507 :lat 2.30835}]
   "Ministère de l'Economie, des Finances, de l'Action et des Comptes Publics"
   [{:admin "Ministère de l'Action et des Comptes Publics" :eig2018 1 :eig2019 1 :lon 48.841 :lat 2.37797}
    {:admin "Ministère de l'Economie et des Finances" :eig2018 1 :lon 48.843 :lat 2.37797}
    {:admin "Direction Générale des Finances Publiques" :eig2017 1 :lon 48.841 :lat 2.37797}
    {:admin "Direction générale de la concurrence, de la consommation et de la répression des fraudes" :eig2019 1 :lon 48.8351 :lat 2.36915}]
   "Ministère de la Transition Ecologique et Solidaire"
   [{:admin "Direction des Affaires Maritimes" :eig2018 1 :eig2019 1 :lon 48.8939 :lat 2.23965}]
   "Ministère de la Justice"
   [{:admin "Direction des affaires civiles et du sceau" :eig2019 1 :lon 48.8678 :lat 2.32874}]
   "Cour des Comptes"
   [{:admin "Cour des Comptes" :eig2017 1 :eig2019 1 :lon 48.8669 :lat 2.32569}]
   "Ministère de la Santé"
   [{:admin "Direction de la recherche, des études, de l’évaluation et des statistiques" :eig2018 1 :eig2019 1 :lon 48.8385 :lat 2.31763}]
   "Secrétariat Général des Ministères Sociaux"
   [{:admin "Secrétariat Général des Ministères Sociaux" :eig2018 1 :lon 48.8521 :lat 2.30908}
    {:admin "En partenariat avec l'ARS Occitanie" :eig2017 1 :lon 43.60806 :lat 3.918406}]
   "Cour de Cassation"
   [{:admin "Service de documentation, des études et du rapport (SDER)" :eig2019 1 :lon 48.8543446 :lat 2.3448946}]
   "Ministère du Travail"
   [{:admin "Direction Générale du Travail" :eig2019 1 :lon 48.8468 :lat 2.27908}]
   "Groupement d'Intérêt Public"
   [{:admin "Agence Française pour le Développement et la Promotion de l'Agriculture Biologique" :eig2019 1 :lon 48.8536727 :lat 2.4186221}]
   "Établissement public à caractère industriel et commercial"
   [{:admin "Agence Française de Développement" :eig2017 1 :lon 48.8438 :lat 2.37905}]
   "Établissement à caractère administratif"
   [{:admin "Agence centrale des organismes de sécurité sociale" :eig2019 1 :lon 48.8510153 :lat 2.4208701}]
   "Autorité Administratives Indépendantes"
   [{:admin "Autorité de régulation des communications électroniques et des postes" :eig2019 1 :lon 48.8358 :lat 2.38847}]})

(def financement
  {"Enveloppe maximale allouée par le PIA"                                         [800000.00 2500000.00 1500000.00]
   "Salaires pris en charge par PIA"                                               [688702.68 1600984.00 1167560.00]    
   "Salaires pris en charge par les administrations bénéficiant de co-financement" [0 0 456624.00]
   "Salaires pour les défis auto-financés"                                         [67850.70 200342.00 476180.09]
   "Coût total salaires EIG"                                                       [756553.38 1801326.00 2100364.09]
   "Communication"                                                                 [0 74209.00 80000.00]
   "Coût total communication"                                                      [0 74209.00 80000.00]
   "Accompagnement et mentoring"                                                   [25000 34808.00 50000.00]    
   "Environnement technique"                                                       [0 2298.00 20000.00]
   "Hébergement par un tiers-lieu"                                                 [0 0.00 75000.00]
   "Développement et amélioration"                                                 [0 0 75000.00]
   "Coût total accompagnement"                                                     [25000.00 37106.00 220000.00]
   "Liens avec la recherche"                                                       [10000.00 0.00 30000.00]
   "Total recherche"                                                               [10000.00 0.00 30000.00]
   "Coût total du programme"                                                       [766553.38 1912641.00 2430364.09]
   "Part des salaires dans le coût total du programme"                             [95.58 94.18 86.42]   
   "Part de la recherche dans le coût total du programme"                          [1.26 0.00 1.23]   
   "Part du programme d'accompagnement dans le coût total du programme"            [3.16 1.94 9.05]
   "Part de la communication dans le coût total du programme"                      [0 3.88 3.29]
   "Part des salaires pris en charge par le PIA"                                   [91.03 88.88 55.59]   
   "Part des défis autofinancés dans le total des salaires"                        [8.97 11.12 22.67]   
   "Part prise en charge par les administrations en co-financement"                [0.00 0.00 21.74]})

(def programme
  {"Nombre d'EIG"                        [11 28 32]
   "Dont nouveaux EIG"                   [11 27 30]
   "Nombre de mentors"                   [6 18 21]
   "Nombre de défis"                     [9 13 15]
   "Nombre de défis totalement financés" [9 11 0]
   "Nombre de défis co-financés"         [0 0 12]
   "Nombre de défis auto-financés"       [0 2 3]})

(def eig
  {"Nombre de développeurs"                             [8 11 9]
   "Nombre de data scientists"                          [3 14 18]
   "Nombre de designers"                                [0 3 5]
   "Part des développeurs parmi les EIG"                [73 39 28]
   "Part des data scientists parmi les EIG"             [27 50 56]
   "Part des designers parmi les EIG"                   [0 11 16]
   "Nombre de femmes"                                   [2 7 6]
   "Nombre d'hommes"                                    [9 21 26]
   "Nombre de femmes développeuses"                     [0 2 1]
   "Nombre de femmes datascientists"                    [2 3 3]
   "Nombre de femmes designers"                         [0 2 2]
   "Pourcentage de femmes parmi les EIG"                [18 25 19]
   "Pourcentage de femmes parmi les développeurs"       [0 18 11]
   "Pourcentage de femmes parmi les data scientists"    [67 21 17]
   "Pourcentage de femmes parmi les designers"          [0 67 40]
   "Pourcentage de femmes développeuses parmi les EIG"  [0 7 3]
   "Pourcentage de femmes data-scientist parmi les EIG" [18 11 9]
   "Pourcentage de femmes designers parmi les EIG"      [0 7 6]})

(def parcours
  {"Sont restés dans la fonction publique" [5 14 0]
   "Sont partis en thèse"                  [1 1 0]
   "Sont partis dans le privé"             [1 6 0]
   "Se sont mis en freelance/indépendant"  [2 4 0]
   "Autres"                                [2 3 0]})

(def selection_des_candidats
  {"Candidats"                                                           [115 208 170]
   "Passages devant un jury"                                             [30 84 86]
   "Lauréats"                                                            [11 28 32]
   "Moyenne d'âge"                                                       [31 30 27]
   "Taux de candidatures féminines"                                      [12 30 20]
   "Part des candidats passés devant un jury sur le total des candidats" [26 40 51]
   "Taux de sélectivité sur le total des candidats"                      [10 13 19]})

(def administrations
  {"Nombre d'administrations qui ont accueilli un défi par promotion" [8 12 13]
   "Nombre de Ministères accueillants au moins un défi"               [4 9 6]
   "Nombre de défis à Paris"                                          [8 11 15]
   "Nombre de défis hors de Paris"                                    [1 2 0]})

(def communication
  {"Articles dans la presse"                                      [14 13 24]
   "Relais administratifs (numerique.gouv, modernisation.gouv …)" [15 1 8]
   "Blog Etalab"                                                  [8 8 3]
   "Articles sur le blog EIG"                                     [0 25 3]
   "Vidéos produites"                                             [45 15 1]
   "Visiteurs uniques sur le site internet EIG"                   [10218 21346 7145]})

(def accompagnement
  {"Sessions d'accompagnement"         [10 18 10]
   "Semaine de lancement de promotion" [0 1 1]
   "Sessions hors-les-murs"            [0 1 2]})

(def encadrement
  {"Nombre de membres de l'équipe d'encadrement du programme" [1 3 4]
   "Nombre d'EIG Link"                                        [0 1 2]})

(defn a-propos []
  [:div {:style {:padding "2em"}}
   [:h1 "Présentation des données"]
   [:p "Ce site présente quelques données liées au programme "
    [:a {:href "https://entrepreneur-interet-general.etalab.gouv.fr/"} "Entrepreneurs d'intérêt général."]]
   ;; [:p "Les données exposées ici sous forme de graphiques sont téléchargeables au format .ods."] ; FIXME
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

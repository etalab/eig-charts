(defproject eig "0.2.0"
  :description "EIG Show: displays graphics for EIG"
  :url "https://entrepreneur-interet-general.etalab.gouv.fr/"
  :license {:name "Eclipse Public License - v 2.0"
            :url  "http://www.eclipse.org/legal/epl-v20.html"}
  :dependencies [[antizer "0.3.1"]
                 [cljsjs/leaflet "1.4.0-0"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]
                 [reagent "0.8.1"]
                 [reagent-utils "0.3.3"]
                 [bidi "2.1.6"]
                 [cljsjs/chartjs "2.8.0-0"]
                 [venantius/accountant "0.2.4"]]
  :target-path "target/%s"
  :resource-paths ["resources"]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  :figwheel {:css-dirs ["resources/public/css"]}
  :profiles
  {:dev {:dependencies [[binaryage/devtools "0.9.10"]
                        [figwheel-sidecar "0.5.18"]]
         :plugins      [[lein-figwheel "0.5.18"]]}}
  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "eig.core/mount-root"}
     :compiler     {:main                 eig.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}
    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            eig.core
                    :externs         ["externs.js"]
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]})

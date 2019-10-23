(ns hpl-website.db)

(def default-db
  {:current-route nil
   :about         {:code  {:owner "tuh8888"
                           :repo  "hpl-website"
                           :ref   "master"
                           :embed [{:path "README.md"}
                                   {:path "src/cljs/hpl_website/views.clj"}]}
                   :tools {:Ring         "https://github.com/ring-clojure/ring"
                           :Compojure    "https://github.com/weavejester/compojure"
                           :Hiccup       "https://github.com/weavejester/hiccup"
                           :Timbre       "https://github.com/ptaoussanis/timbre"
                           :markdown-clj "https://github.com/yogthos/markdown-clj"
                           :re-frame     "https://github.com/Day8/re-frame"
                           :garden       "https://github.com/noprompt/garden"}}
   :my-info       {:name          "Harrison Pielke-Lombardo"
                   :school        "University of Colorado, Anschutz Medical Campus"
                   :school-link   "http://www.ucdenver.edu/academics/colleges/Graduate-School/academic-programs/computational-bioscience/Pages/home.aspx"
                   :contact-info  {"GitHub"   "https://github.com/tuh8888"
                                   "Email"    "Harrison.Pielke-Lombardo@cuanschutz.edu"
                                   "LinkedIn" "https://www.linkedin.com/in/tuh8888/"}
                   :proficiencies [{:name   :clojure
                                    :type   :programming-language
                                    :parent [:Java :Lisp]}
                                   {:name   :clojurescript
                                    :type   :programming-language
                                    :parent [:clojure
                                             :javascript]}
                                   {:name   :Hiccup
                                    :type   :package
                                    :parent [:clojure :HTML]
                                    :use    :markup}
                                   {:name   :reagent
                                    :type   :web
                                    :parent [:clojurescript
                                             :react]}
                                   {:name   :re-frame
                                    :type   :package
                                    :use    :web
                                    :url    "https://github.com/Day8/re-frame"
                                    :parent :reagent}
                                   {:name   :garden
                                    :type   :package
                                    :use    :web
                                    :url    "https://github.com/noprompt/garden"
                                    :parent [:clojurescript
                                             :CSS]}
                                   {:name   :react
                                    :type   :framework
                                    :use    :web
                                    :parent :javascript}
                                   {:name :python
                                    :type :programming-language}
                                   {:name   :nltk
                                    :type   :package
                                    :use    :natural-language-processing
                                    :parent :python}
                                   {:name   :scikit-learn
                                    :type   :package
                                    :use    :machine-learning
                                    :parent :python}
                                   {:name :r
                                    :type :programming-language}
                                   {:name :Java
                                    :type :programming-language}
                                   {:name :javascript
                                    :type :programming-language}
                                   {:name :bash
                                    :type :programming-language
                                    :use  [:shell :script]}
                                   {:name :zsh
                                    :use  :shell
                                    :type :programming-language}
                                   {:name :git
                                    :type :version-control
                                    :use  :version-control}
                                   {:name :vim
                                    :type :editor}
                                   {:name :emacs
                                    :type [:editor :IDE]
                                    :use  :Lisp}
                                   {:name   :IntelliJIDEA
                                    :type   :IDE
                                    :use    [:Java :javascript :clojure]
                                    :parent :JetBrains}
                                   {:name   :PyCharm
                                    :use    :python
                                    :type   :IDE
                                    :parent :JetBrains}
                                   {:name :Lisp
                                    :type :programming-language}
                                   {:name   :Allegro-CL
                                    :type   :programming-language
                                    :parent :Lisp}
                                   {:name   :SBCL
                                    :type   :programming-language
                                    :parent :Lisp}
                                   {:name :HTML
                                    :use  :web
                                    :type :markup}
                                   {:name :markdown
                                    :type :markup}
                                   {:name   :Jupyter
                                    :type   :notebook
                                    :parent :python
                                    :use    [:python
                                             :clojure
                                             :Java
                                             :javascript
                                             :r]}
                                   {:name :CSS
                                    :type :stylesheet
                                    :use  :web}
                                   {:name   :leiningen
                                    :type   :package-manager
                                    :url    "https://github.com/technomancy/leiningen"
                                    :parent :clojure}
                                   {:name   :boot
                                    :type   :package-manager
                                    :use    :script
                                    :url    "https://github.com/boot-clj/boot"
                                    :parent :clojure}
                                   {:name   :deps
                                    :type   :package-manager
                                    :parent :clojure}
                                   {:name   :maven
                                    :type   :package-manager
                                    :parent :Java}
                                   {:name   :osgi
                                    :type   :framework
                                    :parent :Java}
                                   {:name   :edn
                                    :type   :file-format
                                    :parent :clojure}
                                   {:name :CSV
                                    :type :file-format}
                                   {:name   :XML
                                    :type   :file-format
                                    :parent :HTML}
                                   {:name   :JSON
                                    :type   :file-format
                                    :parent :javascript}
                                   {:name   :d3
                                    :type   :package
                                    :use    [:graph
                                             :network
                                             :GUI
                                             :visualization]
                                    :parent :javascript}
                                   {:name   :jGraphX
                                    :url    "https://github.com/jgraph/jgraphx"
                                    :type   :package
                                    :use    [:network
                                             :GUI
                                             :visualization]
                                    :parent :Java}
                                   {:name   :Swing
                                    :type   :package
                                    :use    :GUI
                                    :parent :Java}
                                   {:name :docker
                                    :type :container}
                                   {:name :SQL
                                    :type [:query-language
                                           :database]}
                                   {:name :RDF
                                    :type :file-format}
                                   {:name   :SPARQL
                                    :type   :query-language
                                    :parent :RDF}
                                   {:name   :RDFS
                                    :parent :RDF
                                    :type   :database}
                                   {:name   :OWL
                                    :parent :RDF
                                    :url    "https://github.com/owlcs/owlapi"
                                    :type   :ontology-language}
                                   {:name   :Protege
                                    :parent :ontology
                                    :use    :OWL
                                    :type   [:GUI :IDE]}
                                   {:name   :figwheel
                                    :type   :package
                                    :parent :clojurescript}
                                   {:name :linux
                                    :type :OS}
                                   {:name   :Windows
                                    :type   :OS
                                    :parent :Microsoft}
                                   {:name   :MacOS
                                    :type   :OS
                                    :parent :linux}
                                   {:name   :Android
                                    :type   :OS
                                    :parent [:linux
                                             :Google]}
                                   {:name   :VisualVM
                                    :type   :profiler
                                    :parent :Java}
                                   {:name :ssh
                                    :type :protocol}
                                   {:name :HTTP
                                    :type :protocol}
                                   {:name   :timbre
                                    :type   :package
                                    :use    :logging
                                    :url    "https://github.com/ptaoussanis/timbre"
                                    :parent :clojure}
                                   {:name   :log-4j
                                    :type   :package
                                    :use    :logging
                                    :parent :Java}
                                   {:name   :Ubergraph
                                    :use    [:visualization
                                             :network]
                                    :url    "https://github.com/Engelberg/ubergraph"
                                    :parent :Loom
                                    :type   :package}
                                   {:name   :Loom
                                    :url    "https://github.com/aysylu/loom"
                                    :use    [:visualization
                                             :network]
                                    :type   :package
                                    :parent :clojure}
                                   {:name :GSEA
                                    :type :analysis}
                                   {:name   :Reactome
                                    :type   :GUI
                                    :parent :Gene-Ontology}
                                   {:name   :Gene-Ontology
                                    :type   :ontology
                                    :parent :OWL}
                                   {:name :Google
                                    :type :application-suite}
                                   {:name   :Scholar
                                    :type   :search
                                    :use    :citation
                                    :parent :Google}
                                   {:name   :Docs
                                    :parent :Google
                                    :type   [:editor
                                             :GUI]
                                    :use    :document}
                                   {:name   :Sheets
                                    :parent :Google
                                    :type   [:editor :GUI]
                                    :use    :spreadsheet}
                                   {:name   :Slides
                                    :parent :Google
                                    :type   [:editor :GUI]
                                    :use    :presentation}
                                   {:name   :GitHub
                                    :parent :git
                                    :type   :repository
                                    :use    :code}
                                   {:name :SublimeText
                                    :type [:editor :IDE]}
                                   {:name   :biology
                                    :type   :academic-subject
                                    :parent :chemistry}
                                   {:name   :chemistry
                                    :type   :academic-subject
                                    :parent [:physics
                                             :mathematics]}
                                   {:name   :biochemistry
                                    :type   :academic-subject
                                    :parent [:chemistry
                                             :biology]}
                                   {:name   :computer-science
                                    :type   :academic-subject
                                    :parent :mathematics}
                                   {:name :mathematics
                                    :type :academic-subject}
                                   {:name   :statistics
                                    :type   :academic-subject
                                    :parent :mathematics}
                                   {:name   :biostatistics
                                    :type   :academic-subject
                                    :parent [:statistics
                                             :biology]}
                                   {:name   :bayesian-statistics
                                    :type   :academic-subject
                                    :parent :statistics}
                                   {:name   :symbolic-logic
                                    :type   :academic-subject
                                    :parent :mathematics}
                                   {:name   :artificial-intelligence
                                    :type   :academic-subject
                                    :parent [:symbolic-logic
                                             :machine-learning]}
                                   {:name   :machine-learning
                                    :type   :academic-subject
                                    :parent :statistics}
                                   {:name :natural-language-processing
                                    :type :academic-subject}
                                   {:name :LaTeX
                                    :type :markup}
                                   {:name   :relation-extraction
                                    :parent [:information-extraction
                                             :natural-language-processing]}
                                   {:name :Microsoft
                                    :type :application-suite}
                                   {:name   :word
                                    :type   [:editor
                                             :GUI]
                                    :parent :Microsoft
                                    :use    :document}
                                   {:name   :Excel
                                    :type   [:editor :GUI]
                                    :parent :Microsoft
                                    :use    :spreadsheet}
                                   {:name   :Powerpoint
                                    :type   [:editor :GUI]
                                    :parent :Microsoft
                                    :use    :presentation}
                                   {:name   :neovim
                                    :type   [:editor :GUI]
                                    :parent :vim}
                                   {:name   :Ubuntu
                                    :type   :OS
                                    :parent :linux}
                                   {:name   :Kubuntu
                                    :type   :OS
                                    :parent :Ubuntu}
                                   {:name   :KDE
                                    :type   :desktop-environment
                                    :parent :Kubuntu}
                                   {:name   :kwin
                                    :type   :window-manager
                                    :parent :Kubuntu}
                                   {:name   :Plasma
                                    :type   :desktop-environment
                                    :parent :KDE}
                                   {:name :Mega
                                    :type :cloud-storage}
                                   {:name   :Drive
                                    :parent :Google
                                    :type   :cloud-storage}
                                   {:name :Reddit
                                    :type [:website :forum]}
                                   {:name   :Keep
                                    :type   [:application :note
                                             :GUI]
                                    :parent :Google}
                                   {:name   :Incanter
                                    :use    [:statistics
                                             :graph]
                                    :type   :package
                                    :url    "https://github.com/incanter/incanter"
                                    :parent :clojure}
                                   {:name   :Ring
                                    :use    :web
                                    :type   :package
                                    :url    "https://github.com/ring-clojure/ring"
                                    :parent :clojure}
                                   {:name   :Compojure
                                    :use    :web
                                    :type   :package
                                    :parent :Ring}
                                   {:name :knowledge-base
                                    :type :database}
                                   {:name   :deep-learning
                                    :parent :machine-learning}
                                   {:name   :GPT-2
                                    :type   :language-model
                                    :url    "https://github.com/openai/gpt-2"
                                    :parent :transformer}
                                   {:name   :transformer
                                    :parent :deep-learning}
                                   {:name   :chestnut
                                    :type   :template
                                    :parent :leiningen
                                    :url    "https://github.com/plexus/chestnut"
                                    :use    :web}
                                   {:name   :rid3
                                    :type   :package
                                    :parent :reagent
                                    :url    "https://github.com/gadfly361/rid3"
                                    :use    :d3f}
                                   {:name   :REBL
                                    :type   [:repl :GUI]
                                    :parent :clojure}
                                   {:name   :Ajax
                                    :type   :protocol
                                    :parent :HTTP}
                                   {:name   :cljs-ajax
                                    :type   :package
                                    :url    "https://github.com/JulianBirch/cljs-ajax"
                                    :parent [:Ajax :clojure]}
                                   {:name :max-sat
                                    :type :reasoner}
                                   {:name   :SATNet
                                    :type   :reasoner
                                    :url    "https://github.com/locuslab/SATNet"
                                    :parent [:deep-learning
                                             :max-sat]}
                                   {:name :parallelization
                                    :type :programming-technique}
                                   {:name   :carmine
                                    :type   :package
                                    :url    "https://github.com/ptaoussanis/carmine"
                                    :parent :redis}
                                   {:name   :tac-self-attention
                                    :type   :model-architecture
                                    :url    "https://github.com/ivan-bilan/tac-self-attention"
                                    :parent :attention}
                                   {:name   :attention
                                    :parent :deep-learning}
                                   {:name :Abra-Collaboratory
                                    :type :collaboration
                                    :url  "https://github.com/callahantiff/Abra-Collaboratory"}
                                   {:name :beakerX
                                    :type :notebook-kernel
                                    :url  "https://github.com/twosigma/beakerx"}
                                   {:name   :claypoole
                                    :type   :package
                                    :use    :parallelization
                                    :parent :clojure
                                    :url    "https://github.com/TheClimateCorporation/claypoole"}
                                   {:name   :BioSentVec
                                    :type   :data
                                    :use    :natural-language-processing
                                    :parent :word2vec
                                    :url    "https://github.com/ncbi-nlp/BioSentVec"}
                                   {:name   :neanderthal
                                    :type   :package
                                    :parent :clojure
                                    :url    "https://github.com/uncomplicate/neanderthal"
                                    :use    [:parallelization
                                             :performance]}
                                   {:name   :TensorFlow
                                    :type   :package
                                    :parent :python
                                    :url    "https://github.com/tensorflow/models"
                                    :use    :deep-learning}
                                   {:name   :nippy
                                    :use    :serialization
                                    :type   :package
                                    :parent :clojure
                                    :url    "https://github.com/ptaoussanis/nippy"}
                                   {:name   :Cursive
                                    :type   :IDE
                                    :use    :clojure
                                    :url    "https://github.com/cursive-ide/cursive"
                                    :parent :IntelliJIDEA}
                                   {:name   :MSPrep
                                    :type   :package
                                    :use    :metabolomics
                                    :parent :r
                                    :url    "https://github.com/KechrisLab/MSPrep"}]}})




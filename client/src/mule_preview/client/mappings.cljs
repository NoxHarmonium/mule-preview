(ns mule-preview.client.mappings
  "Mappings that declare how to render Mule components according to their tag name."
  (:require-macros [mule-preview.client.macros :as m]))

(def root-container
  "The root element that every Mule file should have. There should only be one of these per file."
  "mule")

(def error-handler-container-list
  "Elements that are a container, but can also have error handling"
  #{"flow" "transactional"})

(def vertical-container-list
  "Containers that lay out their children vertically"
  #{"scatter-gather" "choice" "composite-source" "round-robin"})

(def horizontal-container-list
  "Containers that lay out their children horizontally"
  #{"flow" "sub-flow" "async" "batch:job"
    "batch:step" "batch:commit" "ee:cache" "foreach"
    "enricher" "poll" "request-reply"
    "until-successful" "when" "otherwise" "processor-chain"})

(def error-handler-component-list
  "Elements that can be in the error section of an error handler container"
  #{"catch-exception-strategy" "choice-exception-strategy"
    "exception-strategy" "rollback-exception-strategy"})

(def element-to-icon-map
  "Mappings to provide icons for each component.
   Generated by a command line tool in this repo."
  (m/get-data "src/mule_preview/client/mappings.json"))
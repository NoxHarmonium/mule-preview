(ns mule-preview.client.react
  "Functions to convert MAST data structures to a structure suitable for reagent (react)"
  (:require
   [clojure.walk :refer [prewalk]]
   [mule-preview.client.mappings :refer [root-container horizontal-container-list
                                         vertical-container-list error-handler-component-list
                                         error-handler-container-list]]
   [mule-preview.client.components :refer [mule-component mule-container]]))

(defn- attributes-to-css [attributes]
  (clojure.string/join " " (map name attributes)))

(defn- create-mule-component [node content-root]
  (let [{:keys [tag-name description attributes]} node]
    (mule-component tag-name description (attributes-to-css attributes) content-root)))

(defn- create-mule-container-component [node content-root]
  (let [{:keys [tag-name description content attributes]} node]
    (mule-container tag-name description content (attributes-to-css attributes) content-root)))

(defn- process-error-container [node content-root]
  (let [{:keys [tag-name description attributes content]} node
        wrapped-content [(create-mule-container-component (first content) content-root)
                         (create-mule-container-component (second content) content-root)]]
    (mule-container tag-name description wrapped-content (attributes-to-css attributes) content-root)))

(defn- transform-tag [node content-root]
  (let [type (:type node)]
    (case type
      :error-container (process-error-container node content-root)
      :container (create-mule-container-component node content-root)
      :component (create-mule-component node content-root))))

(defn- transform-fn [content-root node]
  (if (contains? node :type)
    (let [transformed-tag (transform-tag node content-root)]
      transformed-tag)
    node))

(defn mast->react [xml content-root]
  (prewalk (partial transform-fn content-root) xml))
(ns maltose.core)

(defrecord Brewhouse
  [efficiency])

(defrecord Recipe
  [batch-size og fg grist hop-schedule])

(defn grist-percentages
  "Given a map of grist weights, return a map of grist percentages."
  [grist]
   ;; All map values should be numbers
  {:pre [(every? number? (vals grist))]
   ;; Ensure that the resulting values always add to 1.
   :post [(= 1.0 (reduce + (vals %)))]}
  (let [total-weight (reduce + (vals grist))]
    (into {} (map (fn [[k v]] [k (/ v total-weight)]) grist))))

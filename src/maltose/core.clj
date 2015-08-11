(ns maltose.core)

(defrecord Brewhouse
  [efficiency])

(defrecord Recipe
  [batch-size og fg grist hop-schedule])

(defn gravity-points
  "Calculate the gravity points for a given recipe."
  [recipe]
  (* (* (- (:og recipe) 1) 1000) (:batch-size recipe)))

(defn grist-weight
  "Returns the total weight of a recipe's grist."
  [brewhouse recipe]
  (/ (gravity-points recipe) (* (:efficiency brewhouse) 36)))

(defn grist-amounts
  "Given a brewhouse and recipe, determine the weights of malts in the grist."
  [brewhouse recipe]
  (let [total-weight (grist-weight brewhouse recipe)]
    (into {} (map (fn [[k v]] [k (* total-weight v)]) (:grist recipe)))))

(defn grist-percentages
  "Given a map of grist weights, return a map of grist percentages."
  [grist]
   ;; All map values should be numbers
  {:pre [(every? number? (vals grist))]
   ;; Ensure that the resulting values always add to 1.
   :post [(= 1.0 (reduce + (vals %)))]}
  (let [total-weight (reduce + (vals grist))]
    (into {} (map (fn [[k v]] [k (/ v total-weight)]) grist))))

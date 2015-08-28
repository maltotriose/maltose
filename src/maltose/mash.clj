(ns maltose.mash)

;; TODO: refactor to one function with initial infusion providing defaults
(defn initial-infusion
  "Calculates the temperature of strike water before it hits the mash tun."
  [grain-temperature target-temperature & [grain-to-water-ratio]]
  {:pre [(or (nil? grain-to-water-ratio) (float? grain-to-water-ratio))]}
  (let [r (or grain-to-water-ratio 1.0)]
    (+ (* (/ 0.20 r)
          (- target-temperature grain-temperature))
       target-temperature)))

(defn mash-infusion
  "Calculates the amount of boiling water required to raise mash temperature
  to some target temperature."
  [current-temperature target-temperature current-volume grain-weight
   & [strike-water-temperature]]
  {:pre [(or (nil? strike-water-temperature) (float? strike-water-temperature))]}
  (let [swp (or strike-water-temperature 212.0)]
    (/  (* (- target-temperature current-temperature)
           (+  (* 0.20 grain-weight) current-volume))
       (- swp target-temperature))))

;; TODO: figure out how to update the recipe model to include water/grain ratio
;; TODO: rethink this implementation
(defn mash-schedule
  "Determine initial strike water temperature and subsequent boiling water
  infusions to achieve the given mash rests."
  [recipe grain-temperature first-rest & rests]
  (let [grist-weight (reduce + (vals (:grist recipe)))]
    {:strike-water-temperature (initial-infusion grain-temperature first-rest)
     :rests rests}))

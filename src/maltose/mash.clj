(ns maltose.mash)

(defn initial-infusion
  "Calculates the temperature of strike water before it hits the mash tun."
  [grain-temperature target-temperature & [grain-to-water-ratio]]
  {:pre [(or (nil? grain-to-water-ratio) (float? grain-to-water-ratio))
         (every? float? [grain-temperature target-temperature])]}
  (let [r (or grain-to-water-ratio 1.0)]
    (+ (* (/ 0.20 r)
          (- target-temperature grain-temperature))
       target-temperature)))

(defn mash-infusion
  "Calculates the amount of boiling water required to raise mash temperature
  to some target temperature."
  [current-temperature target-temperature current-volume grain-weight
   & [strike-water-temperature]]
  ;; Make sure everything is a float, so we get consistent results.
  ;; TODO: Investigate if core.typed makes this easier to read.
  {:pre [(or (nil? strike-water-temperature) (float? strike-water-temperature))
         (every? float? [current-temperature target-temperature
                         current-volume grain-weight])]}
  (let [swp (or strike-water-temperature 212.0)]
    (/  (* (- target-temperature current-temperature)
           (+  (* 0.20 grain-weight) current-volume))
       (- swp target-temperature))))

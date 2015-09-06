(ns maltose.unit-conversions
  "Conversions for various units of measure.")

(defn ->Plato
  "Given a specific gravity, return the equivalent degrees Plato.

  Note: this is not *exactly* inversely related to ->specific-gravity.

  Found in Eq. 4.7 of A.J. DeLange's 'Specific Gravity Measurement Methods and
  Applications in Brewing'"
  [sg]
  {:pre [(number? sg)]}
  (+ -616.868
     (*  1111.14 sg)
     (* -630.272 (Math/pow sg 2))
     (*  135.997 (Math/pow sg 3))))

(defn ->specific-gravity
  "Given a measurement of degrees Plato, return the equivalent
  specific gravity.

  Note: this is not *exactly* inversely related to ->Plato.

  Found in Eq. 4.69 of A.J. DeLange's 'Specific Gravity Measurement Methods and
  Applications in Brewing."
  [p]
  {:pre [(number? p)]}
  (/ (- 668
        (Math/sqrt (- (Math/pow 668 2)
                      (* 820 (+ 463 p)))))
     410))

(defn ->gravity-units
  "Given a specific gravity, return its corresponding gravity units."
  [sg]
  {:pre [(float? sg) (> sg 1.0)]}
  (Math/floor
    (* 1000 (- sg 1))))

(ns maltose.unit-conversions)

(defn ->Plato
  "Given a specific gravity, return the equivalent degrees Plato."
  [sg]
  {:pre [(number? sg)]}
  (* 250 (- sg 1)))

(defn ->SpecificGravity
  "Given a measurement of degrees Plato, return the equivalent
  specific gravity."
  [p]
  {:pre [(number? p)]}
  (+ (/ p 250) 1))

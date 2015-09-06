(ns maltose.grist
  "Provides functions for determining grist composition.

  In order for these things to be calculated we must know the following:
      - Target Gravity: given value for the attempted style
      - Finished Volume: the finished volume of beer that will be produced
      - Fermentables: list of malts and fermentables and their proportions
      - Brewhouse Efficiency: the effeciency of your equipement"
  (:require [maltose.unit-conversions :refer [->gravity-units]]))

(defn total-gravity
  "Determines how much extract must be removed from fermentables in the recipe."
  [specific-gravity final-volume]
  {:pre [(every? float? [specific-gravity final-volume])]}
  (Math/floor
    (* final-volume
       (->gravity-units specific-gravity))))

(defn ingredient-gravity
  "Determines amount of extract that should come from each fermentable source."
  [grist total-extract]
  {:pre [(and (map? grist) (not (empty? grist))) (float? total-extract)]}
  (->> grist
       (map (fn [[malt proportion]] [malt (* proportion total-extract)]))
       (into {})))

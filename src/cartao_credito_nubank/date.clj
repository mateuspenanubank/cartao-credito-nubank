(ns cartao-credito-nubank.date
  (:require [java-time]))

(def date-now java-time/local-date)

(defn date-parse
  [date] (java-time/local-date date))

(defn date-format
  ([local-date]
   (date-format local-date "dd/MM/YYYY"))
  ([local-date format]
   (java-time/format (java-time/formatter format) local-date)))

(-> (date-now)
    println)
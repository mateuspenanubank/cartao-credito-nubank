(ns cartao-credito-nubank.date
  (:require [java-time]))

(def date-now java-time/local-date)

(defn local-date-to-instant [local-date]
  (java.util.Date/from (.toInstant (.atStartOfDay local-date (java.time.ZoneId/of "America/Bahia")))))

(defn instant-to-local-date [instant]
  (.toLocalDate (.atZone (.toInstant instant) (java.time.ZoneId/of "America/Bahia"))))

(defn date-parse
  [date] (java-time/local-date date))

(defn date-format
  ([local-date]
   (date-format local-date "dd/MM/YYYY"))
  ([local-date format]
   (java-time/format (java-time/formatter format) local-date)))
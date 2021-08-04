(ns cartao-credito-nubank.core
  (:require [schema.core :as s])
  (:require [cartao-credito-nubank.db :as db])
  (:require [cartao-credito-nubank.logic :as logic]))

(s/set-fn-validation! true)

(-> db/mateus
    logic/exibe-compras-do-cliente)

(-> db/mateus
    logic/exibe-gastos-agrupados-do-cliente)

(-> db/mateus
    logic/exibe-fatura-mes-atual)

(-> db/mateus
    (logic/exibe-busca-de-compras "Ubereats"))

;(-> db/mateus
;    (logic/exibe-busca-de-compras 80.0))
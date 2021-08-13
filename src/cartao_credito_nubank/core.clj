(ns cartao-credito-nubank.core
  (:use clojure.pprint)
  (:require [schema.core :as s])
  (:require [cartao-credito-nubank.date :as c.date])
  (:require [cartao-credito-nubank.db :as c.db])
  (:require [cartao-credito-nubank.logic :as c.logic]
            [cartao-credito-nubank.model :as c.model]
            [datomic.api :as d]))

(s/set-fn-validation! true)

(let [nome "Mateus"
      cpf "111111111111"
      email "mateus.pena@nubank.com.br"
      numero "4272 9041 9812 7063"
      cvv "321"
      validade "07/2025"
      limite 1000M]
  (println (c.logic/salva-cliente-com-cartao nome cpf email numero cvv validade limite)) )












;
;(-> db/mateus
;    logic/exibe-compras-do-cliente)

;(-> db/mateus
;    logic/exibe-gastos-agrupados-do-cliente)
;
;(-> db/mateus
;    logic/exibe-fatura-mes-atual)
;
;(-> db/mateus
;    (logic/exibe-busca-de-compras "Ubereats"))

;(-> db/mateus
;    (logic/exibe-busca-de-compras 80.0))

;(println (logic/adiciona-nova-compra-cliente db/jessica
;                                             {:data (date/date-parse "2021-08-18"),
;                                              :valor 800.0,
;                                              :estabelecimento "Dentista",
;                                              :categoria "Saúde"} ) )

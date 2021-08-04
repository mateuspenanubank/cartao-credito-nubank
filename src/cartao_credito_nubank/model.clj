(ns cartao-credito-nubank.model
  (:require [schema.core :as s])
  (:require [java-time]))

(s/def Compra {:data            java.time.LocalDate,
               :valor           s/Num,
               :estabelecimento s/Str,
               :categoria       s/Str })

(s/def Compras [ Compra ])

(s/def Cartao {:numero   s/Str,
               :cvv      s/Str,
               :validade s/Str,
               :limite   s/Num,
               :compras  Compras})

(s/def Cliente {:nome   s/Str,
                :cpf    s/Str,
                :email  s/Str,
                :cartao Cartao })


;(s/set-fn-validation! true)
;
;(s/defn verifica-cliente [cliente :- Cliente]
;  (pprint cliente))
;
;(verifica-cliente db/mateus)
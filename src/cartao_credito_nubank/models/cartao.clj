(ns cartao-credito-nubank.models.cartao
  (:require [schema.core :as s]
            [cartao-credito-nubank.model :as model]
            [cartao-credito-nubank.models.compra :as c.models.compra]))

(s/def Cartao {:cartao/id       s/Uuid,
               :cartao/numero   s/Str,
               :cartao/cvv      s/Str,
               :cartao/validade s/Str,
               :cartao/limite   s/Num,
               :cartao/compras  c.models.compra/Compras})

(s/defn novo-cartao :- Cartao
  ([numero :- s/Str, cvv :- s/Str, validade :- s/Str, limite :- s/Num]
   (novo-cartao numero cvv validade limite []))
  ([numero :- s/Str, cvv :- s/Str, validade :- s/Str, limite :- s/Num, compras :- c.models.compra/Compras]
   (novo-cartao (model/uuid) numero cvv validade limite compras))
  ([id :- s/Uuid, numero :- s/Str, cvv :- s/Str, validade :- s/Str, limite :- s/Num, compras :- c.models.compra/Compras]
   {:cartao/id       id
    :cartao/numero   numero
    :cartao/cvv      cvv
    :cartao/validade validade
    :cartao/limite   limite
    :cartao/compras  compras}))

(ns cartao-credito-nubank.models.compra
  (:require [schema.core :as s]
            [cartao-credito-nubank.models.estabelecimento :as c.models.estabelecimento]
            [cartao-credito-nubank.models.categoria :as c.models.categoria]
            [cartao-credito-nubank.model :as c.model] )
  (:import (java.time LocalDate)))

(s/def Compra {:compra/id              s/Uuid
               :compra/data            LocalDate,
               :compra/valor           s/Num,
               :compra/estabelecimento c.models.estabelecimento/Estabelecimento,
               :compra/categoria       c.models.categoria/Categoria} )

(s/def Compras [ Compra ])

(s/defn nova-compra
  ([data :- LocalDate, valor :- s/Num, estabelecimento :- c.models.estabelecimento/Estabelecimento, categoria :- c.models.categoria/Categoria]
   (nova-compra (c.model/uuid) data valor estabelecimento categoria))
  ([id :- s/Uuid, data :- LocalDate, valor :- s/Num, estabelecimento :- c.models.estabelecimento/Estabelecimento, categoria :- c.models.categoria/Categoria]
   {:compra/id id
    :compra/data data
    :compra/valor valor
    :compra/categoria categoria
    :compra/estabelecimento estabelecimento} ) )

(s/defn acrescenta-compra
  [compras compra]
  (conj compras compra))
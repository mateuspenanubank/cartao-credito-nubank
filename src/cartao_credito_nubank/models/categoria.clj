(ns cartao-credito-nubank.models.categoria
  (:require [schema.core :as s]
            [cartao-credito-nubank.model :as model]))

(s/def Categoria {:categoria/id     s/Uuid,
                  :categoria/nome   s/Str } )

(s/defn nova-categoria
        ([nome :- s/Str]
         (nova-categoria (uuid) nome) )
        ([id :- s/Uuid, nome :- s/Str]
         {:categoria/id    id,
          :categoria/nome nome} ) )

(ns cartao-credito-nubank.models.estabelecimento
  (:require [schema.core :as s]
            [cartao-credito-nubank.model :as model]))

(s/def Estabelecimento {:estabelecimento/id     s/Uuid,
                        :estabelecimento/nome   s/Str } )

(s/defn novo-estabelecimento
        ([nome :- s/Str]
         (novo-estabelecimento (model/uuid) nome))
        ([id :- s/Uuid, nome :- s/Str]
         {:estabelecimento/id id,
          :estabelecimento/nome nome}))
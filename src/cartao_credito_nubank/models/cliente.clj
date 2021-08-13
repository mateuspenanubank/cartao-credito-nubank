(ns cartao-credito-nubank.models.cliente
  (:require [schema.core :as s]
            [cartao-credito-nubank.models.cartao :as c.models.cartao]
            [cartao-credito-nubank.model :as model]))

(s/def Cliente {:cliente/id     s/Uuid,
                :cliente/nome   s/Str,
                :cliente/cpf    s/Str,
                :cliente/email  s/Str,
                :cliente/cartao c.models.cartao/Cartao } )

(s/defn novo-cliente :- Cliente
        ([nome :- s/Str, cpf :- s/Str, email :- s/Str, cartao :- c.models.cartao/Cartao]
         (novo-cliente (model/uuid) nome cpf email cartao))
        ([id :- s/Uuid, nome :- s/Str, cpf :- s/Str, email :- s/Str, cartao :- c.models.cartao/Cartao]
         {:cliente/id id
          :cliente/nome nome,
          :cliente/cpf cpf,
          :cliente/email email,
          :cliente/cartao cartao }) )
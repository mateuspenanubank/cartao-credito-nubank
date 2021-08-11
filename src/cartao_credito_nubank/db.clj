(ns cartao-credito-nubank.db
  (:require [cartao-credito-nubank.date :as date]
            [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/cartao-credito-nubank")

(def schema [; Clientes
             {:db/ident       :cliente/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}
             {:db/ident       :cliente/nome
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cliente/cpf
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cliente/email
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             ; Cartão
             {:db/ident       :cartao/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}
             {:db/ident       :cartao/numero
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cartao/cvv
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cartao/validade
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :cartao/limite
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one}

             ;Compra
             {:db/ident       :compra/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}
             {:db/ident       :compra/data
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}
             ])




(defn abre-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco []
  (d/delete-database db-uri))





;(def mateus {:nome   "Mateus"
;             :cpf    "111.111.111-11"
;             :email  "mateus.pena@nubank.com.br"
;             :cartao {:numero   "4272 9041 9812 7063"
;                      :cvv      "342"
;                      :validade "27/12/2021"
;                      :limite   1000.0
;                      :compras  [{:data (date/date-parse "2021-06-13"), :valor 58.7, :estabelecimento "Mercado", :categoria "Alimentação"}
;                                 {:data (date/date-parse "2021-06-27"), :valor 11.7, :estabelecimento "Ubereats", :categoria "Alimentação"}
;                                 {:data (date/date-parse "2021-07-17"), :valor 30.0, :estabelecimento "Ubereats", :categoria "Alimentação"}
;                                 {:data (date/date-parse "2021-07-23"), :valor 80.0, :estabelecimento "Ubereats", :categoria "Alimentação"}
;                                 {:data (date/date-parse "2021-07-05"), :valor 21.7, :estabelecimento "Udemy", :categoria "Educação"}
;                                 {:data (date/date-parse "2021-08-05"), :valor 120.0, :estabelecimento "Oftalmologista", :categoria "Saúde"}
;                                 {:data (date/date-parse "2021-08-18"), :valor 80.0, :estabelecimento "Dentista", :categoria "Saúde"}]}})
;
;(def jessica {:nome   "Jéssica"
;              :cpf    "111.111.111-11"
;              :email  "jessica.lameri@nubank.com.br"
;              :cartao {:numero   "5272 9041 9812 7063"
;                       :cvv      "342"
;                       :validade "27/12/2021"
;                       :limite   1500.0
;                       :compras  [{:data (date/date-parse "2021-05-21"), :valor 18.7, :estabelecimento "Mercado", :categoria "Alimentação"}
;                                  {:data (date/date-parse "2021-06-11"), :valor 210.7, :estabelecimento "Faculdade", :categoria "Educação"}
;                                  {:data (date/date-parse "2021-06-28"), :valor 91.7, :estabelecimento "Alura", :categoria "Educação"}
;                                  {:data (date/date-parse "2021-07-02"), :valor 32.7, :estabelecimento "Nutricionista", :categoria "Saúde"}]}})
;
;
;(defn todos-os-clientes []
;  [mateus jessica])


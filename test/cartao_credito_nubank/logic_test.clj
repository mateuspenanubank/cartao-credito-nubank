(ns cartao-credito-nubank.logic-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [cartao-credito-nubank.logic :refer :all]
            [cartao-credito-nubank.db :as db]
            [cartao-credito-nubank.date :as date]
            [cartao-credito-nubank.model :as model] ))

;
; Teste da função que adiciona uma compra na lista de compras realizadas;
;
(deftest test-adiciona-nova-compra-cliente
  (let [cliente db/jessica,
        nova-compra-valida {:data            (date/date-parse "2021-08-18"),
                            :valor           800.0,
                            :estabelecimento "Dentista",
                            :categoria       "Saúde"},
        nova-compra-invalida {:valor           800.0,
                              :estabelecimento "Dentista",
                              :categoria       "Saúde"}]
    (testing "Testar incluisão de compra válida a lista de compras do cliente."
      (let [cliente-atualizado (adiciona-nova-compra-cliente cliente nova-compra-valida)]
        (is (not= nil (some #{ nova-compra-valida } (get-in cliente-atualizado [:cartao :compras])))) ) )

    (testing "Testar inclusão de compra inválida a lista de compras do cliente."
      (is (try (s/validate model/Cliente (adiciona-nova-compra-cliente cliente nova-compra-invalida)) false
               (catch clojure.lang.ExceptionInfo e (= :schema.core/error (:type (ex-data e)))) ) ) ) ) )

;
; Teste da função que lista as compras realizadas;
;
(deftest test-compras-cartao-cliente
  (testing "Testar estrutura de listagem de compras de um cliente."
    (is (try (s/validate {:cliente-nome s/Str, :cliente-compras model/Compras} (compras-cartao-cliente db/mateus)) true
             (catch clojure.lang.ExceptionInfo e (not (= :schema.core/error (:type (ex-data e))))) )) ) )

;
; Teste da função que realiza o cálculo dos gastos agrupados por categoria.
;
(deftest test-gastos-agrupados-por-categoria
  (testing "Testar listagem de gastos agrupados por categoria."
    (let [cliente db/mateus
          gastos-por-categoria-mateus [{:categoria "Alimentação", :total 180.4},
                                       {:categoria "Educação", :total 21.7},
                                       {:categoria "Saúde", :total 200.0}] ]
      (is  (= gastos-por-categoria-mateus (gastos-agrupados-do-cliente cliente)) ) ) )

  (testing "Testar listagem com 1 item a menos."
    (let [gastos-por-categoria-mateus [{:categoria "Alimentação", :total 180.4},
                                       {:categoria "Educação", :total 21.7}]]
      (is (not (= gastos-por-categoria-mateus (:gastos-agrupados-categoria (gastos-agrupados-do-cliente (compras-cartao-cliente db/mateus)))))) ) )

  (testing "Testar listagem com 1 item a mais."
    (let [gastos-por-categoria-mateus [{:categoria "Alimentação", :total 180.4},
                                       {:categoria "Educação", :total 21.7},
                                       {:categoria "Saúde", :total 200.0},
                                       {:categoria "Lazer", :total 321.0}]]
      (is (not (= gastos-por-categoria-mateus (:gastos-agrupados-categoria (gastos-agrupados-do-cliente (compras-cartao-cliente db/mateus)))))) ) ) )
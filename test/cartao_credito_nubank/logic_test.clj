(ns cartao-credito-nubank.logic-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [cartao-credito-nubank.logic :refer :all]
            [cartao-credito-nubank.db :as db]
            [cartao-credito-nubank.date :as date]
            [cartao-credito-nubank.model :as model] ))

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
    (let [gastos-por-categoria-mateus [{:categoria "Alimentação", :total 180.4},
                                       {:categoria "Educação", :total 21.7},
                                       {:categoria "Saúde", :total 200.0}]]
      (is (->> db/mateus
               compras-cartao-cliente
               gastos-agrupados-do-cliente
               :gastos-agrupados-categoria
               (= gastos-por-categoria-mateus)) ) ) )

  (testing "Testar listagem desordenada de gastos agrupados por categoria."
    (let [gastos-por-categoria-mateus [{:categoria "Alimentação", :total 180.4},
                                       {:categoria "Saúde", :total 200.0},
                                       {:total 21.7 :categoria "Educação"}]]
      (is
        (->> db/mateus
             compras-cartao-cliente
             gastos-agrupados-do-cliente
             :gastos-agrupados-categoria
             (reduce (fn [vetor, agrupamento] (conj vetor (some #{agrupamento} gastos-por-categoria-mateus))) [])
             (filter not-empty)
             count
             (= (count gastos-por-categoria-mateus)))) ) )

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
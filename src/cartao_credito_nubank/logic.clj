(ns cartao-credito-nubank.logic
  (:use clojure.pprint)
  (:require [schema.core :as s])
  (:require [cartao-credito-nubank.model :as model])
  (:require [cartao-credito-nubank.date :as date]))

;
; Listagem de compras realizadas (data, valor, estabelecimento, categoria);
;
(defn extrai-compra-cartao
  [compra]
  (update-in compra [:data] (fn [valor] (date/date-format valor))))

(defn listar-compras-cartao [cartao]
  (->> cartao
       :compras))

(defn compras-cartao-cliente
  [cliente]
  {:cliente-nome    (:nome cliente)
   :cliente-compras (listar-compras-cartao (:cartao cliente)) })

(s/defn exibe-compras-do-cliente
  [cliente :- model/Cliente]
  (let [cliente-compras (compras-cartao-cliente cliente)]
    (println "\nCompras do cartão de crédito de" (:cliente-nome cliente-compras))
    (pprint (mapv extrai-compra-cartao (:cliente-compras cliente-compras))) ))

;
; Valor dos gastos agrupados por categoria;
;
(defn calcula-gastos-categorias
  [ [categoria, compras] ]
  { :categoria categoria
   :total (reduce (fn [valor, compra] (+ (:valor compra) valor)) 0 compras) })

(s/defn gastos-agrupados-por-categoria
  [compras :- model/Compras]
  (->> compras
       (group-by :categoria)
       (map calcula-gastos-categorias)))

(s/defn gastos-agrupados-do-cliente
  [cliente :- model/Cliente]
  (let [cliente-compras (compras-cartao-cliente cliente)]
    (gastos-agrupados-por-categoria (:cliente-compras cliente-compras)) ))

(s/defn exibe-gastos-agrupados-do-cliente
  [cliente :- model/Cliente]
  (let [gastos-agrupados (gastos-agrupados-do-cliente cliente)]
    (println "\nGastos por categoria de" (:nome cliente))
    (pprint gastos-agrupados) ) )

;
; Cálculo do valor da fatura do mês (opcional);
;
(defn determina-mes-ano-da-compra
  [compra]
  (assoc compra :month-year (date/date-format (:data compra) "LLLL/YYYY"))
  )

(defn mes-ano-atual? [[key, _]]
  (= key (date/date-format (date/date-now) "LLLL/YYYY")))

(defn monta-fatura-mes-atual
  [total]
  {:mes (date/date-format (date/date-now) "LLLL")
   :ano (date/date-format (date/date-now) "YYYY")
   :total total})

(defn calcula-fatura-mes-atual [cliente]
  (->> cliente
       compras-cartao-cliente
       :cliente-compras
       (map determina-mes-ano-da-compra)
       (group-by :month-year)
       (filter mes-ano-atual?)
       vals
       first
       (map :valor)
       (reduce + 0)))

(s/defn exibe-fatura-mes-atual
  [cliente :- model/Cliente]
  (println "\nFatura do mes atual de" (:nome cliente))
  (pprint (monta-fatura-mes-atual (calcula-fatura-mes-atual cliente))))

;
; Busca de compras pelo valor ou estabelecimento (opcional).
;
(defn busca-compra
  [cliente termo]
  (->> cliente
       compras-cartao-cliente
       :cliente-compras
       (filter (fn [compra] (or (= termo (:estabelecimento compra))
                                (= termo (:valor compra)))))
       (map (fn [compra] (update compra :data #(date/date-format %1) )))))

(s/defn exibe-busca-de-compras
  [cliente :- model/Cliente, termo :- s/Str]
  (println "\nCompras de" (:nome cliente))
  (pprint (busca-compra cliente termo)))


;
; Adicionar uma compra na lista de compras do cliente (extra).
;
(s/defn adiciona-nova-compra-cliente :- model/Cliente
  [cliente :- model/Cliente, compra :- model/Compra]
  (update-in cliente [:cartao :compras] conj compra) )


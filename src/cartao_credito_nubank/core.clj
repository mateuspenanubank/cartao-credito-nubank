(ns cartao-credito-nubank.core
  (:require [cartao-credito-nubank.db]))

(def todos-os-clientes cartao-credito-nubank.db/todos-os-clientes)

;
; Listagem de compras realizadas (data, valor, estabelecimento, categoria);
;
(defn listar-compras-cartao [cartao]
  (->> cartao
       :compras))

(defn compras-cartao-cliente
  [cliente]
  {:cliente-nome    (:nome cliente)
   :cliente-compras (listar-compras-cartao (:cartao cliente)) })

(->> (todos-os-clientes)
  (map compras-cartao-cliente)
  println)

;
; Valor dos gastos agrupados por categoria;
;
(defn calcula-gastos-categorias
  [ [categoria, compras] ]
  { :categoria categoria
    :total (reduce (fn [valor, compra] (+ (:valor compra) valor)) 0 compras) })

(defn gastos-agrupados-por-categoria
  [compras]
  (->> compras
       (group-by :categoria)
       (map calcula-gastos-categorias)))

(defn gastos-agrupados-do-cliente
  [cliente]
  {:cliente-nome (:cliente-nome cliente)
   :gastos-agrupados-categoria (gastos-agrupados-por-categoria (:cliente-compras cliente))
   })

(defn agrupar-gastos-por-cliente
  [clientes]
  (->> clientes
       (map compras-cartao-cliente)
       (map gastos-agrupados-do-cliente)))

(println (agrupar-gastos-por-cliente (todos-os-clientes)))



;(defn listagem-compras
;  [cliente]
;  (get-in cliente [:cartao :compras]))
;
;(println (->> (todos-os-clientes)
;              ;(map :nome)
;              (map listagem-compras)
;              println
;              ))





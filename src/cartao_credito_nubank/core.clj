(ns cartao-credito-nubank.core)

(def clientes { :1 {:nome "Mateus"
                    :cpf "111.111.111-11"
                    :email "mateus.pena@nubank.com.br"
                    :cartao { :numero "5272 9041 9812 7063"
                              :cvv "342"
                              :validade "27/12/2021"
                              :limite 1000.0
                              :compras [{:data "27/02/2021", :valor 58.7, :estabelecimento "Mercado", :categoria "Alimentação"}
                                        {:data "27/02/2021", :valor 11.7, :estabelecimento "Ubereats", :categoria "Alimentação"}
                                        {:data "27/02/2021", :valor 21.7, :estabelecimento "Udemy", :categoria "Educação"}
                                        {:data "27/03/2021", :valor 80.0, :estabelecimento "Dentista", :categoria "Saúde"}] } }
               :2 {:nome "Jéssica"
                   :cpf "111.111.111-11"
                   :email "jessica.lameri@nubank.com.br"
                   :cartao { :numero "5272 9041 9812 7063"
                            :cvv "342"
                            :validade "27/12/2021"
                            :limite 1500.0
                            :compras [{:data "27/02/2021", :valor 18.7, :estabelecimento "Mercado", :categoria "Alimentação"}
                                      {:data "27/02/2021", :valor 210.7, :estabelecimento "Faculdade", :categoria "Educação"}
                                      {:data "27/02/2021", :valor 91.7, :estabelecimento "Alura", :categoria "Educação"}
                                      {:data "27/03/2021", :valor 32.7, :estabelecimento "Clinica", :categoria "Saúde"}] } }})

;(defn total-por-categoria
;  [cliente-id]
;  )

;(println (get-in clientes [ :1 :nome ]))

(def aux { "Alimentaçã" 0 })

(if (not (nil? (get aux "Alimentação")))
  (do (def aux (update aux "Alimentação" #(+ %1 5))))
  (do (def aux (assoc aux "Alimentação" 5))))

(println aux)

;(println (update aux "Alimentação" #(+ %1 5)))

;(defn agrupa-categorias
;    [compra]
;    (-> aux
;        (-> compra :categoria)
;        (update-in aux (-> compra :categoria))
;        ))
;
;(->> clientes
;    :1
;    :cartao
;    :compras
;    (map agrupa-categorias)
;    println)
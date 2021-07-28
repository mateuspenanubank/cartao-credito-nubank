(ns cartao-credito-nubank.db)

(def mateus {:nome   "Mateus"
             :cpf    "111.111.111-11"
             :email  "mateus.pena@nubank.com.br"
             :cartao {:numero   "4272 9041 9812 7063"
                      :cvv      "342"
                      :validade "27/12/2021"
                      :limite   1000.0
                      :compras  [{:data "27/02/2021", :valor 58.7, :estabelecimento "Mercado", :categoria "Alimentação"}
                                 {:data "27/02/2021", :valor 11.7, :estabelecimento "Ubereats", :categoria "Alimentação"}
                                 {:data "27/02/2021", :valor 21.7, :estabelecimento "Udemy", :categoria "Educação"}
                                 {:data "27/03/2021", :valor 80.0, :estabelecimento "Dentista", :categoria "Saúde"}]}})

(def jessica {:nome   "Jéssica"
              :cpf    "111.111.111-11"
              :email  "jessica.lameri@nubank.com.br"
              :cartao {:numero   "5272 9041 9812 7063"
                       :cvv      "342"
                       :validade "27/12/2021"
                       :limite   1500.0
                       :compras  [{:data "27/02/2021", :valor 18.7, :estabelecimento "Mercado", :categoria "Alimentação"}
                                  {:data "27/02/2021", :valor 210.7, :estabelecimento "Faculdade", :categoria "Educação"}
                                  {:data "27/02/2021", :valor 91.7, :estabelecimento "Alura", :categoria "Educação"}
                                  {:data "27/03/2021", :valor 32.7, :estabelecimento "Clinica", :categoria "Saúde"}]}})


(defn todos-os-clientes []
  [mateus, jessica])


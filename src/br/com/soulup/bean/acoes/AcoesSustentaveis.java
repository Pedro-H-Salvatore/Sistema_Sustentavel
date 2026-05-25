// Cauã De Souza - RM573342
// Leonardo De Souza Bernard - RM570951
// Luigi Tormim Carqueijeiro - RM572424
// Pedro Henrique Salvatore - RM569497

package br.com.soulup.bean.acoes;

import br.com.soulup.bean.ExibirInformacoes;

import java.time.LocalDate;

public class AcoesSustentaveis implements ExibirInformacoes {
    //atributos

    private String tipoDeAcao;
    private int pontosAcao;
    private String descricao;
    private LocalDate dataAcao;
    private int vezesQueFezAcao;

    //constructors


    public AcoesSustentaveis() {
    }


    //getters/setters


    public String getTipoDeAcao() {
        return tipoDeAcao;
    }

    public void setTipoDeAcao(String tipoDeAcao) {
        this.tipoDeAcao = tipoDeAcao;
    }

    public int getPontosAcao() {
        return pontosAcao;
    }

    public void setPontosAcao(int pontosAcao) {
        this.pontosAcao = pontosAcao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(LocalDate dataAcao) {
       try {
           LocalDate dataHoje = LocalDate.now().plusDays(1);
           LocalDate dataLimite = LocalDate.now().minusWeeks(1);
           if (dataAcao.isBefore(dataHoje) && dataAcao.isAfter(dataLimite)) {
               this.dataAcao = dataAcao;
           }else {
               throw new Exception("Não é possível registrar uma ação Sustentável em um dia que não aconteceu ou que passou de 1 semana");
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }

    public int getVezesQueFezAcao() {
        return vezesQueFezAcao;
    }

    public void setVezesQueFezAcao(int vezesQueFezAcao) {
        try {
            if (vezesQueFezAcao > 0) {
                this.vezesQueFezAcao = vezesQueFezAcao;
            } else {
                throw new Exception("Não é possível registrar uma ação com uma quantidade negativa de vezes");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //metodos

    public int calcularPontuacao(){
        return pontosAcao = vezesQueFezAcao*10;
    }

    public String exibirDetalhes(){
        return String.format("Detalhes da ação realizada:\nTipo da Ação: %s\nDescrição da Ação: %s\nPontos acumulados: %d", tipoDeAcao, descricao, pontosAcao);
    }
}

// Cauã De Souza - RM573342
// Leonardo De Souza Bernard - RM570951
// Luigi Tormim Carqueijeiro - RM572424
// Pedro Henrique Salvatore - RM569497

package br.com.soulup.bean.usuarios;

import br.com.soulup.bean.acoes.AcoesSustentaveis;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioPremium extends Usuario {
    //atributos
    private LocalDate dataDeVencimento;
    private LocalDate dataDeAssinatura;
    private int numeroCartao;
    private int codigoDeSeguranca;
    private LocalDate vencimentoCartao;

    //constructors

    public UsuarioPremium() {
    }
    //getters e setters

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public LocalDate getDataDeAssinatura() {
        return dataDeAssinatura;
    }

    public void setDataDeAssinatura(LocalDate dataDeAssinatura) {
       try {
           LocalDate dataDeHoje = LocalDate.now().plusDays(1);
           if (dataDeAssinatura.isBefore(dataDeHoje)) {
               this.dataDeAssinatura = dataDeAssinatura;
           }else{
               throw new Exception("Não é possível assinar em um dia que ainda não passou");
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public int getCodigoDeSeguranca() {
        return codigoDeSeguranca;
    }

    public void setCodigoDeSeguranca(int codigoDeSeguranca) {
        this.codigoDeSeguranca = codigoDeSeguranca;
    }

    public LocalDate getVencimentoCartao() {
        return vencimentoCartao;
    }

    public void setVencimentoCartao(LocalDate vencimentoCartao) {
       try {
           LocalDate dataHoje = LocalDate.now().plusDays(1);
           if (vencimentoCartao.isAfter(dataHoje)){
               this.vencimentoCartao = vencimentoCartao;
           }else{
               throw new Exception("O cartão já venceu e não pode ser registrado!");
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
    //métodos
    public int calcularIdade() {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(super.getDataDeNascimento(), hoje);
        if (periodo.getYears() < 18) {
            System.out.println("Não é possível ter uma conta premium sendo menor de idade!");

        }
        return periodo.getYears();
    }

    public float converterPontosEmCreditos(AcoesSustentaveis pontos) {
        //pelo motivo do usuário ser premium, ele converte 20% dos pontos em créditos
        return pontos.getPontosAcao()*0.2f;
    }


    public String exibirDetalhes() {
        return String.format("Dados do usuário premium:\nNome do Usuário: %s\nEmail: %s\nIdade: %d\nNível: %s\nData de Assinatura: %s\n Data de vencimento: %s", super.getNome(),super.getEmail(),calcularIdade(),super.getNivel(), dataDeAssinatura,dataDeVencimento);
    }

    public float calcularNivel(AcoesSustentaveis pontos){
        //a cada 40 pontos o usuario sobe 1 nivel;
        float nivel = pontos.getPontosAcao()*0.04f;
        return nivel = super.getNivel();
    }



}

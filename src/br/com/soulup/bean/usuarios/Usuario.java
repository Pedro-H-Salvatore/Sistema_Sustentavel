// Cauã De Souza - RM573342
// Leonardo De Souza Bernard - RM570951
// Luigi Tormim Carqueijeiro - RM572424
// Pedro Henrique Salvatore - RM569497

package br.com.soulup.bean.usuarios;

import br.com.soulup.bean.ExibirInformacoes;
import br.com.soulup.bean.acoes.AcoesSustentaveis;

import java.time.LocalDate;
import java.time.Period;

public class Usuario implements ExibirInformacoes {
    //atributos

    private String nome;
    private String email;
    private float nivel;
    private LocalDate dataDeNascimento;
    private int pontos;
    private float creditos;

    //constructors

    public Usuario() {
    }

    //getters/setters


    public float getCreditos() {
        return creditos;
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getNivel() {
        return nivel;
    }

    public void setNivel(float nivel) {
        try {
            if (nivel >=0) {
                this.nivel = nivel;
            }else {
                throw new Exception("Seu Nível está abaixo do permitido");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        try {
            LocalDate hoje = LocalDate.now().plusDays(1);
            LocalDate dataLimite = LocalDate.parse("1900-01-01").minusDays(1);
            if (dataDeNascimento.isAfter(dataLimite) && dataLimite.isBefore(hoje)) {
                this.dataDeNascimento = dataDeNascimento;
            }else {
                throw new Exception("Data limite excedida! (Min:01/01/1900 Max: Dias Atuais)");
            }
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
    }
    //Métodos
    public int calcularIdade(){
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataDeNascimento, hoje);
        return periodo.getYears();
    }
    public float converterPontosEmCreditos(AcoesSustentaveis pontos){
        //Para um número X de pontos, 10% será convertido em créditos
        return pontos.getPontosAcao()*0.1f;
    }


    public String exibirDetalhes() {
        return String.format("Dados Do Usuário:\nNome do Usuário: %s\nEmail: %s\nIdade: %d\nNível: %s", nome,email,calcularIdade(),nivel);
    }

    public float calcularNivel(AcoesSustentaveis pontos){
        //a cada 60 pontos o usuario sobe 1 nivel;
        return nivel = pontos.getPontosAcao()*0.06f;

    }
}
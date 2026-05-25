// Cauã De Souza - RM573342
// Leonardo De Souza Bernard - RM570951
// Luigi Tormim Carqueijeiro - RM572424
// Pedro Henrique Salvatore - RM569497

package br.com.soulup.bean.acoes;

public class AcaoDeReciclagem extends AcoesSustentaveis {
    //Atributos

    private String material;
    private float quantidade;
    //constructors

    public AcaoDeReciclagem() {
    }

    //getters/setters


    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        try {
            if (quantidade > 0) {
                this.quantidade = quantidade;
            }else{
                throw new Exception("Não E possivel registrar quantidade negativa");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Métodos

    public int calcularPontuacao(){
        //Ações de reciclagem valem 15 pontos
        return super.getVezesQueFezAcao()*15;

    }

    public String exibirDetalhes(){
        return String.format("Detalhes da ação realizada:\nTipo da Ação: %s\nDescrição da Ação: %s\nPontos acumulados: %d\nTipo do material Reciclado: %s\nQuantidade de lixo reciclado: %.2f Kg", super.getTipoDeAcao(), super.getDescricao(), super.getPontosAcao(), material, quantidade);
    }


}

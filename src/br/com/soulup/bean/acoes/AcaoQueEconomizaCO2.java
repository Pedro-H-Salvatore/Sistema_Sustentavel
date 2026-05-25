// Cauã De Souza - RM573342
// Leonardo De Souza Bernard - RM570951
// Luigi Tormim Carqueijeiro - RM572424
// Pedro Henrique Salvatore - RM569497

package br.com.soulup.bean.acoes;

public class AcaoQueEconomizaCO2 extends AcoesSustentaveis {
    //atributos

    private float quantidadeDeCO2Economizada;
    private String meioDeTransporte;
    private float distanciaPercorrida;
    private String transporteRenovavel;


    //constructors


    public AcaoQueEconomizaCO2() {

    }

    //getters/setters

    public float getQuantidadeDeCo2Economizada() {
        return quantidadeDeCO2Economizada;
    }

    public void setQuantidadeDeCo2Economizada(float quantidadeDeCo2Economizada) {
        this.quantidadeDeCO2Economizada = quantidadeDeCo2Economizada;
    }

    public String getMeioDeTransporte() {
        return meioDeTransporte;
    }

    public void setMeioDeTransporte(String meioDeTransporte) {
        this.meioDeTransporte = meioDeTransporte;
    }

    public float getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(float distanciaPercorrida) {
        try {
            if (distanciaPercorrida > 0) {
                this.distanciaPercorrida = distanciaPercorrida;
            }else {
                throw new Exception("Não é possível registrar uma distância negativa");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getMeioDeTransporteRenovavel() {
        return transporteRenovavel;
    }

    public void setMeioDeTransporteRenovavel(String meioDeTransporteRenovavel) {
        this.transporteRenovavel = meioDeTransporteRenovavel;
    }

    //Métodos

    public float calcularCO2Economizado() {

        float emissao;

        if (meioDeTransporte.equalsIgnoreCase("carro")) {
            emissao = 0.31f;

        } else if (meioDeTransporte.equalsIgnoreCase("caminhonete")) {
            emissao = 0.46f;

        } else if (meioDeTransporte.equalsIgnoreCase("moto")) {
            emissao = 0.21f;
        }else {
            emissao = 0;
        }

        //CO2 emitido = Distância x Fator de emissão

        float CO2FInicial = distanciaPercorrida * emissao;

        if (transporteRenovavel.equalsIgnoreCase("bicicleta")) {
            emissao = 0;

        } else if (transporteRenovavel.equalsIgnoreCase("caminhada")) {
            emissao = 0;

        } else if (transporteRenovavel.equalsIgnoreCase("Transporte Coletivo")) {
            emissao = 0.11f;

        }else {
            emissao = 0;
        }

        float CO2Final = distanciaPercorrida * emissao;

        //CO2 Economizado = CO2 incial - CO2Final
        return CO2FInicial - CO2Final;

    }

    public int calcularPontuacao() {
        // Açãao que economiza a emissão de CO2 vale 20 pontos
        return super.getVezesQueFezAcao()*20;
    }

    public String exibirDetalhes() {
        return String.format("Detalhes da ação realizada:\nTipo da Ação: %s\nDescrição da Ação: %s\nPontos acumulados: %d\nTransporte Renovável utilizado: %s\nCO2 economizado: %.2f Kg/Km", super.getTipoDeAcao(), super.getDescricao(), super.getPontosAcao(), transporteRenovavel,calcularCO2Economizado());
    }
}

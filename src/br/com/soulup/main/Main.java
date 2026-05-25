// Cauã De Souza - RM573342
// Leonardo De Souza Bernard - RM570951
// Luigi Tormim Carqueijeiro - RM572424
// Pedro Henrique Salvatore - RM569497

package br.com.soulup.main;

import br.com.soulup.bean.acoes.AcaoDeReciclagem;
import br.com.soulup.bean.acoes.AcaoQueEconomizaCO2;
import br.com.soulup.bean.acoes.AcoesSustentaveis;
import br.com.soulup.bean.usuarios.Usuario;
import br.com.soulup.bean.usuarios.UsuarioPremium;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Variáveis
        String nome, email, tipoAcao, nascimento, descricao, dataAcaoStr, escolha = "sim";
        int tipoConta, opcaoMenu, opcaoAcao, vezesAcao, pontosTotais = 0;
        LocalDate dataNascimento, dataAcao;
        float nivelTotal = 0, creditosTotais = 0;

        try {

            System.out.println("============================================\n         BEM-VINDO AO SISTEMA SOULPOINTS\n============================================\nEscolha o tipo de conta:\n1 - Conta Grátis\n2 - Conta Premium");
            tipoConta = scan.nextInt();

            if (tipoConta == 1) {

                // CADASTRO - CONTA GRÁTIS

                Usuario usuarioGratis = new Usuario();

                System.out.println("\n--- Cadastro de Conta Grátis ---");

                System.out.println("Digite seu nome");
                nome = scan.next();
                usuarioGratis.setNome(nome);

                System.out.println("Digite seu e-mail");
                email = scan.next();
                usuarioGratis.setEmail(email);

                System.out.println("Digite sua data de nascimento (dia/mês/ano)");
                nascimento = scan.next();
                dataNascimento = LocalDate.parse(nascimento, dtf);
                usuarioGratis.setDataDeNascimento(dataNascimento);

                if (usuarioGratis.getDataDeNascimento() == null) {
                    System.out.println("\nRegistro Cancelado");
                }else {
                    System.out.println("\nConta Grátis criada com sucesso!");


                    // MENU PRINCIPAL - CONTA GRÁTIS

                    AcoesSustentaveis acaoSustentavel = new AcoesSustentaveis();

                    while (escolha.equalsIgnoreCase("sim")) {
                        System.out.println("\n============================================\n              MENU PRINCIPAL\n============================================\nDigite o número da ação que deseja executar.\n1 - Ver informações da conta\n2 - Registrar uma ação\n3 - Converter pontos em créditos");
                        opcaoMenu = scan.nextInt();

                        switch (opcaoMenu) {

                            case 1:

                                // INFORMAÇÕES DA CONTA

                                System.out.printf("\n%s\nPontos totais   : %d\nNível atual     : %.1f\nCréditos totais : R$ %.2f%n",
                                        usuarioGratis.exibirDetalhes(), pontosTotais, nivelTotal, creditosTotais);
                                break;

                            case 2:

                                // REGISTRAR AÇÃO

                                System.out.println("\n--- Tipo de Ação ---\nDigite o número da ação que deseja executar.\n1 - Ação de Reciclagem\n2 - Ação que Economiza CO2\n3 - Outra Ação");
                                opcaoAcao = scan.nextInt();

                                if (opcaoAcao == 1) {

                                    // AÇÃO DE RECICLAGEM

                                    AcaoDeReciclagem acaoDeReciclagem = new AcaoDeReciclagem();
                                    String material;
                                    float quantidadeMaterial;

                                    acaoDeReciclagem.setTipoDeAcao("Ação de Reciclagem");

                                    System.out.println("Digite a descrição do que foi feito ");
                                    descricao = scan.next();
                                    acaoDeReciclagem.setDescricao(descricao);

                                    System.out.println("Digite a quantidade de vezes que realizou a ação");
                                    vezesAcao = scan.nextInt();
                                    acaoDeReciclagem.setVezesQueFezAcao(vezesAcao);

                                    if(acaoDeReciclagem.getVezesQueFezAcao() == 0){
                                        System.out.println("Registro cancelado!");

                                    }else {
                                        System.out.println("Data em que foi realizada (dia/mês/ano)");
                                        dataAcaoStr = scan.next();
                                        dataAcao = LocalDate.parse(dataAcaoStr, dtf);
                                        acaoDeReciclagem.setDataAcao(dataAcao);

                                        if (acaoDeReciclagem.getDataAcao() == null) {
                                            System.out.println("Registro Cancelado!");

                                        } else {
                                            System.out.println("Digite o tipo do material reciclado: ");
                                            material = scan.next();
                                            acaoDeReciclagem.setMaterial(material);

                                            System.out.println("Digite a quantidade do material reciclado (kg/unidades): ");
                                            quantidadeMaterial = scan.nextFloat();
                                            acaoDeReciclagem.setQuantidade(quantidadeMaterial);

                                            if (acaoDeReciclagem.getQuantidade() == 0) {
                                                System.out.println("Registro cancelado!");

                                            }else {
                                                acaoDeReciclagem.setPontosAcao(acaoDeReciclagem.calcularPontuacao());
                                                pontosTotais += acaoDeReciclagem.getPontosAcao();
                                                nivelTotal += usuarioGratis.calcularNivel(acaoDeReciclagem);
                                                usuarioGratis.setNivel(nivelTotal);

                                                System.out.printf("\n%s\nNível atualizado: %.1f%n", acaoDeReciclagem.exibirDetalhes(), nivelTotal);
                                            }
                                        }
                                    }

                                } else if (opcaoAcao == 2) {

                                    // AÇÃO QUE ECONOMIZA CO2

                                    AcaoQueEconomizaCO2 acaoCO2 = new AcaoQueEconomizaCO2();
                                    String transporteAntes, transporteAgora;
                                    float distancia;
                                    int opcaoTransporte;

                                    acaoCO2.setTipoDeAcao("Ação que Economiza CO2");

                                    System.out.println("Digite a descrição do que foi feito ");
                                    descricao = scan.next();
                                    acaoCO2.setDescricao(descricao);

                                    System.out.println("Digite a quantidade de vezes que realizou a ação");
                                    vezesAcao = scan.nextInt();
                                    acaoCO2.setVezesQueFezAcao(vezesAcao);

                                    if (acaoCO2.getVezesQueFezAcao() == 0) {
                                        System.out.println("Registro cancelado!");

                                    }else{
                                        System.out.println("Data em que foi realizada (dia/mês/ano)");
                                        dataAcaoStr = scan.next();
                                        dataAcao = LocalDate.parse(dataAcaoStr, dtf);
                                        acaoCO2.setDataAcao(dataAcao);

                                        if (acaoCO2.getDataAcao() == null) {
                                            System.out.println("Registro Cancelado!");
                                        } else {
                                            System.out.println("Meio de transporte que você utilizava antes:\n1 - Carro\n2 - Caminhonete\n3 - Moto");
                                            opcaoTransporte = scan.nextInt();
                                            if (opcaoTransporte == 1) {
                                                transporteAntes = "carro";
                                            } else if (opcaoTransporte == 2) {
                                                transporteAntes = "caminhonete";
                                            } else {
                                                transporteAntes = "moto";
                                            }
                                            acaoCO2.setMeioDeTransporte(transporteAntes);

                                            System.out.println("Meio de transporte renovável que passou a utilizar:\n1 - Bicicleta\n2 - Caminhada\n3 - Transporte Coletivo");
                                            opcaoTransporte = scan.nextInt();
                                            if (opcaoTransporte == 1) {
                                                transporteAgora = "bicicleta";
                                            } else if (opcaoTransporte == 2) {
                                                transporteAgora = "caminhada";
                                            } else {
                                                transporteAgora = "Transporte Coletivo";
                                            }
                                            acaoCO2.setMeioDeTransporteRenovavel(transporteAgora);
                                            if (acaoCO2.getDistanciaPercorrida() == 0) {
                                                acaoCO2.setPontosAcao(0);
                                            }

                                            System.out.println("Distância percorrida (km)");
                                            distancia = scan.nextFloat();
                                            acaoCO2.setDistanciaPercorrida(distancia);

                                            if (acaoCO2.getDistanciaPercorrida() == 0) {
                                                System.out.println("Registro cancelado!");

                                            }else{
                                                acaoCO2.setPontosAcao(acaoCO2.calcularPontuacao());
                                                pontosTotais += acaoCO2.getPontosAcao();
                                                nivelTotal += usuarioGratis.calcularNivel(acaoCO2);
                                                usuarioGratis.setNivel(nivelTotal);

                                                System.out.printf("\n%s\nNível atualizado: %.1f%n", acaoCO2.exibirDetalhes(), nivelTotal);
                                            }


                                        }
                                    }



                                } else if (opcaoAcao == 3) {

                                    // OUTRA AÇÃO

                                    AcoesSustentaveis outraAcao = new AcoesSustentaveis();

                                    System.out.println("Que tipo de ação você realizou: ");
                                    tipoAcao = scan.next();
                                    outraAcao.setTipoDeAcao(tipoAcao);

                                    System.out.println("Digite a deescrição do que foi feito");
                                    descricao = scan.next();
                                    outraAcao.setDescricao(descricao);

                                    System.out.println("Digite a quantidade de vezes que realizou a ação");
                                    vezesAcao = scan.nextInt();
                                    outraAcao.setVezesQueFezAcao(vezesAcao);

                                    if (outraAcao.getVezesQueFezAcao() == 0) {
                                        System.out.println("Registro cancelado!");

                                    }else {
                                        System.out.print("Digite a data em que foi realizada (dia/mês/ano)");
                                        dataAcaoStr = scan.next();
                                        dataAcao = LocalDate.parse(dataAcaoStr, dtf);
                                        outraAcao.setDataAcao(dataAcao);

                                        if (outraAcao.getDataAcao() == null) {
                                            System.out.println("Registro Cancelado!");

                                        } else {
                                            outraAcao.setPontosAcao(outraAcao.calcularPontuacao());
                                            pontosTotais += outraAcao.getPontosAcao();
                                            nivelTotal += usuarioGratis.calcularNivel(outraAcao);
                                            usuarioGratis.setNivel(nivelTotal);

                                            System.out.printf("\n%s\nNível atualizado: %.1f%n", outraAcao.exibirDetalhes(), nivelTotal);
                                        }
                                    }
                                } else {
                                    System.out.println("Opção Inválida!");
                                }
                                break;

                            case 3:

                                // CONVERTER PONTOS EM CRÉDITOS

                                if (pontosTotais == 0) {
                                    System.out.println("\nVocê não possui pontos contabilizados. Por favor, registre uma ação.");
                                } else {
                                    acaoSustentavel.setPontosAcao(pontosTotais);
                                    float creditos = usuarioGratis.converterPontosEmCreditos(acaoSustentavel);
                                    creditosTotais += creditos;
                                    usuarioGratis.setNivel(nivelTotal);
                                    System.out.printf("\n--- Conversão de Pontos em Créditos ---\nPontos acumulados     : %d\nCréditos gerados      : R$ %.2f\nCréditos totais       : R$ %.2f%n",
                                            pontosTotais, creditos, creditosTotais);
                                    pontosTotais = 0;
                                    acaoSustentavel.setPontosAcao(0);
                                }
                                break;

                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }

                        if (escolha.equalsIgnoreCase("sim")) {
                            System.out.println("\nDeseja continuar utilizando o sistema? (sim/não) ");
                            escolha = scan.next();
                        }
                    }
                }



            } else if (tipoConta == 2) {

                // CADASTRO - CONTA PREMIUM

                UsuarioPremium usuarioPremium = new UsuarioPremium();

                System.out.println("\n--- Cadastro de Conta Premium ---");

                System.out.println("Digite seu nome");
                nome = scan.next();
                usuarioPremium.setNome(nome);

                System.out.println("Digite seu e-mail");
                email = scan.next();
                usuarioPremium.setEmail(email);

                System.out.println("Digite sua data de nascimento (dia/mês/ano)");
                nascimento = scan.next();
                dataNascimento = LocalDate.parse(nascimento, dtf);
                usuarioPremium.setDataDeNascimento(dataNascimento);

                if (usuarioPremium.calcularIdade() < 18) {
                    System.out.println("Registro cancelado!");

                } else {
                    System.out.println("\n--- Registro de Cartão de Crédito ---");

                    System.out.println("Digite o número do cartão");
                    int numeroCartao = scan.nextInt();
                    usuarioPremium.setNumeroCartao(numeroCartao);

                    System.out.println("Digite o código de segurança (CVV)");
                    int codigoDeSeguranca = scan.nextInt();
                    usuarioPremium.setCodigoDeSeguranca(codigoDeSeguranca);

                    System.out.println("Digite o vencimento do cartão (dia/mês/ano)");
                    LocalDate vencimentoCartao = LocalDate.parse(scan.next(), dtf);
                    usuarioPremium.setVencimentoCartao(vencimentoCartao);

                    if (usuarioPremium.getVencimentoCartao() == null) {
                        System.out.println("Registro cancelado!");

                    }else {
                        LocalDate dataAssinatura = LocalDate.now();
                        usuarioPremium.setDataDeAssinatura(dataAssinatura);

                        LocalDate dataVencimento = LocalDate.now().plusMonths(1);
                        usuarioPremium.setDataDeVencimento(dataVencimento);

                        System.out.println("\nConta Premium criada com sucesso! Cartão registrado."
                                + "\nData de assinatura : " + dataAssinatura.format(dtf)
                                + "\nData de vencimento : " + dataVencimento.format(dtf));

                        // MENU PRINCIPAL - CONTA PREMIUM

                        AcoesSustentaveis acaoSustentavelPremium = new AcoesSustentaveis();
                        int pontosTotaisPremium = 0;
                        float nivelTotalPremium = 0, creditosTotaisPremium = 0;

                        while (escolha.equalsIgnoreCase("sim")) {
                            System.out.println("\n============================================\n              MENU PRINCIPAL\n============================================\n1 - Ver informações da conta\n2 - Registrar uma ação\n3 - Converter pontos em créditos");
                            opcaoMenu = scan.nextInt();

                            switch (opcaoMenu) {

                                case 1:

                                    // INFORMAÇÕES DA CONTA
                                    // Dados do cartão NÃO são exibidos!

                                    System.out.printf("\n%s\nPontos totais   : %d\nNível atual     : %.1f\nCréditos totais : R$ %.2f%n",usuarioPremium.exibirDetalhes(), pontosTotaisPremium, nivelTotalPremium, creditosTotaisPremium);
                                    break;

                                case 2:

                                    // REGISTRAR AÇÃO

                                    System.out.println("\n--- Tipo de Ação ---\nDigite o número da ação que deseja executar.\n1 - Ação de Reciclagem\n2 - Ação que Economiza CO2\n3 - Outra Ação");
                                    opcaoAcao = scan.nextInt();

                                    if (opcaoAcao == 1) {

                                        // AÇÃO DE RECICLAGEM

                                        AcaoDeReciclagem acaoDeReciclagem = new AcaoDeReciclagem();
                                        String material;
                                        float quantidadeMaterial;

                                        acaoDeReciclagem.setTipoDeAcao("Ação de Reciclagem");

                                        System.out.println("Digite a descrição do que foi feito ");
                                        descricao = scan.next();
                                        acaoDeReciclagem.setDescricao(descricao);

                                        System.out.println("Digite a quantidade de vezes que realizou a ação");
                                        vezesAcao = scan.nextInt();
                                        acaoDeReciclagem.setVezesQueFezAcao(vezesAcao);

                                        if (-acaoDeReciclagem.getVezesQueFezAcao() == 0) {
                                            System.out.println("Registro cancelado");

                                        }else {

                                            System.out.println("Data em que foi realizada (dia/mês/ano)");
                                            dataAcaoStr = scan.next();
                                            dataAcao = LocalDate.parse(dataAcaoStr, dtf);
                                            acaoDeReciclagem.setDataAcao(dataAcao);

                                            if (acaoDeReciclagem.getDataAcao() == null) {
                                                System.out.println("Registro Cancelado!");

                                            } else {
                                                System.out.println("Digite o tipo do material reciclado: ");
                                                material = scan.next();
                                                acaoDeReciclagem.setMaterial(material);

                                                System.out.println("Digite a quantidade do material reciclado (kg/unidades): ");
                                                quantidadeMaterial = scan.nextFloat();
                                                acaoDeReciclagem.setQuantidade(quantidadeMaterial);

                                                if (acaoDeReciclagem.getQuantidade() == 0) {
                                                    System.out.println("Registro cancelado!");

                                                }else {
                                                    acaoDeReciclagem.setPontosAcao(acaoDeReciclagem.calcularPontuacao());
                                                    pontosTotaisPremium += acaoDeReciclagem.getPontosAcao();
                                                    nivelTotalPremium += usuarioPremium.calcularNivel(acaoDeReciclagem);
                                                    usuarioPremium.setNivel(nivelTotalPremium);

                                                    System.out.printf("\n%s\nNível atualizado: %.1f%n", acaoDeReciclagem.exibirDetalhes(), nivelTotalPremium);
                                                }

                                            }
                                        }


                                    } else if (opcaoAcao == 2) {

                                        // AÇÃO QUE ECONOMIZA CO2

                                        AcaoQueEconomizaCO2 acaoCO2 = new AcaoQueEconomizaCO2();
                                        String transporteAntes, transporteAgora;
                                        float distancia;
                                        int opcaoTransporte;

                                        acaoCO2.setTipoDeAcao("Ação que Economiza CO2");

                                        System.out.println("Digite a descrição do que foi feito ");
                                        descricao = scan.next();
                                        acaoCO2.setDescricao(descricao);

                                        System.out.println("Digite a quantidade de vezes que realizou a ação");
                                        vezesAcao = scan.nextInt();
                                        acaoCO2.setVezesQueFezAcao(vezesAcao);

                                        if (acaoCO2.getVezesQueFezAcao() == 0) {
                                            System.out.println("Registro cancelado!");

                                        }else {
                                            System.out.println("Data em que foi realizada (dia/mês/ano)");
                                            dataAcaoStr = scan.next();
                                            dataAcao = LocalDate.parse(dataAcaoStr, dtf);
                                            acaoCO2.setDataAcao(dataAcao);

                                            if (acaoCO2.getDataAcao() == null) {
                                                System.out.println("Registro Cancelado!");

                                            } else {
                                                System.out.println("Meio de transporte que você utilizava antes:\n1 - Carro\n2 - Caminhonete\n3 - Moto");
                                                opcaoTransporte = scan.nextInt();

                                                if (opcaoTransporte == 1) {
                                                    transporteAntes = "carro";

                                                } else if (opcaoTransporte == 2) {
                                                    transporteAntes = "caminhonete";

                                                } else {
                                                    transporteAntes = "moto";
                                                }
                                                acaoCO2.setMeioDeTransporte(transporteAntes);

                                                System.out.println("Meio de transporte renovável que passou a utilizar:\n1 - Bicicleta\n2 - Caminhada\n3 - Transporte Coletivo");
                                                opcaoTransporte = scan.nextInt();

                                                if (opcaoTransporte == 1) {
                                                    transporteAgora = "bicicleta";

                                                } else if (opcaoTransporte == 2) {
                                                    transporteAgora = "caminhada";

                                                } else {
                                                    transporteAgora = "Transporte Coletivo";
                                                }

                                                acaoCO2.setMeioDeTransporteRenovavel(transporteAgora);

                                                System.out.println("Distância percorrida (km)");
                                                distancia = scan.nextFloat();
                                                acaoCO2.setDistanciaPercorrida(distancia);

                                                if (acaoCO2.getDistanciaPercorrida() == 0) {
                                                    System.out.println("Registro cancelado");

                                                }else {
                                                    acaoCO2.setPontosAcao(acaoCO2.calcularPontuacao());
                                                    pontosTotaisPremium += acaoCO2.getPontosAcao();
                                                    nivelTotalPremium += usuarioPremium.calcularNivel(acaoCO2);
                                                    usuarioPremium.setNivel(nivelTotalPremium);

                                                    System.out.printf("\n%s\nNível atualizado: %.1f%n", acaoCO2.exibirDetalhes(), nivelTotalPremium);
                                                }
                                        }

                                        }

                                    } else if (opcaoAcao == 3) {

                                        // OUTRA AÇÃO

                                        AcoesSustentaveis outraAcao = new AcoesSustentaveis();

                                        System.out.println("Que tipo de ação você realizou: ");
                                        tipoAcao = scan.next();
                                        outraAcao.setTipoDeAcao(tipoAcao);

                                        System.out.println("Digite a descrição do que foi feito");
                                        descricao = scan.next();
                                        outraAcao.setDescricao(descricao);

                                        System.out.println("Digite a quantidade de vezes que realizou a ação");
                                        vezesAcao = scan.nextInt();
                                        outraAcao.setVezesQueFezAcao(vezesAcao);

                                        if (outraAcao.getVezesQueFezAcao() == 0) {
                                            System.out.println("Registro cancelado!");

                                        }else {
                                            System.out.print("Digite a data em que foi realizada (dia/mês/ano)");
                                            dataAcaoStr = scan.next();
                                            dataAcao = LocalDate.parse(dataAcaoStr, dtf);
                                            outraAcao.setDataAcao(dataAcao);

                                            if (outraAcao.getDataAcao() == null) {
                                                System.out.println("Registro Cancelado!");

                                            } else {
                                                outraAcao.setPontosAcao(outraAcao.calcularPontuacao());
                                                pontosTotaisPremium += outraAcao.getPontosAcao();
                                                nivelTotalPremium += usuarioPremium.calcularNivel(outraAcao);
                                                usuarioPremium.setNivel(nivelTotalPremium);

                                                System.out.printf("\n%s\nNível atualizado: %.1f%n", outraAcao.exibirDetalhes(), nivelTotalPremium);
                                        }
                                        }

                                    } else {
                                        System.out.println("Opção Inválida!");
                                    }
                                    break;

                                case 3:
                                    // ----------------------------------------
                                    // CONVERTER PONTOS EM CRÉDITOS
                                    // ----------------------------------------
                                    if (pontosTotaisPremium == 0) {
                                        System.out.println("\nVocê não possui pontos contabilizados. Por favor, registre uma ação.");
                                    } else {
                                        acaoSustentavelPremium.setPontosAcao(pontosTotaisPremium);
                                        float creditosPremium = usuarioPremium.converterPontosEmCreditos(acaoSustentavelPremium);
                                        creditosTotaisPremium += creditosPremium;
                                        System.out.printf("\n--- Conversão de Pontos em Créditos ---\nPontos acumulados     : %d\nCréditos gerados      : R$ %.2f\nCréditos totais       : R$ %.2f%n",
                                                pontosTotaisPremium, creditosPremium, creditosTotaisPremium);
                                        pontosTotaisPremium = 0;
                                        acaoSustentavelPremium.setPontosAcao(0);
                                    }
                                    break;

                                default:
                                    System.out.println("Opção inválida. Tente novamente.");
                            }

                            if (escolha.equalsIgnoreCase("sim")) {
                                System.out.println("\nDeseja continuar utilizando o sistema? (sim/não)");
                                escolha = scan.next();
                            }
                        }
                    }
                }
            } else {
                System.out.println("Opção inválida. Encerrando o sistema.");
            }

            System.out.println("\n============================================\n      Obrigado por usar o SoulPoints!\n   Até a próxima. Juntos pelo planeta!\n============================================");

        } catch (Exception e) {
            System.out.println("Caracter Inválido! encerrando o programa.");
        }
    }
}
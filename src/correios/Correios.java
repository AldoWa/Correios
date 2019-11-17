/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios;

import classes.BancoDeDados;
import java.util.Scanner;

/**
 *
 * @author Aldo
 */
public class Correios {

    // Menu
    static void menu() {
        System.out.println("1 - Atualizar arquivo");
        System.out.println("2 - Visualizar estados");
        System.out.println("3 - Visualizar cidades de um estado");
        System.out.println("4 - Visualizar bairros de uma cidade");
        System.out.println("5 - Visualizar logradouros de um bairro");
        System.out.println("6 - Buscar logradouro por um CEP");
        System.out.println("7 - Buscar logradouro pelo nome");
        System.out.println("8 - Menu de Testes");
        System.out.println("0 - Finalizar programa");
        System.out.println("--------------------------------------------");
        System.out.print("Digite uma opção: ");
    }

    static void menuTestes() {
        System.out.println("\n1 - Testar leitura de arquivos");
        System.out.println("2 - Testar pesquisa por CEP ou Nome da Rua");
        System.out.println("0 - Voltar ao menu anterior");
        System.out.println("--------------------------------------------");
        System.out.print("Digite uma opção: ");
    }

    public static void main(String[] args) {
        // Inicialização do programa
        BancoDeDados bancoDeDados = new BancoDeDados();
        Scanner scan = new Scanner(System.in);
        System.out.println("Selecione o diretório do arquivo que deseja ler:");
        String retorno = bancoDeDados.lerArquivos();
        if (retorno.equals("\nOperação cancelada!")) {
            System.out.println("Programa cancelado!");
            System.exit(0);
        }
        while (retorno.equals("Falha ao ler arquivo!")) {
            retorno = bancoDeDados.lerArquivos();
            if (retorno.equals("\nOperação cancelada!")) {
                System.exit(0);
            }
        }
        while (true) {
            try {
                menu();
                int resp = Integer.parseInt(scan.nextLine());
                while (resp != 0) {
                    switch (resp) {
                        // Ler/atualizar arquivo
                        case 1:
                            System.out.println(bancoDeDados.lerArquivos());
                            break;
                        // Mostrar todas as UFs
                        case 2:
                            System.out.println(bancoDeDados.retornarUFs());
                            break;
                        // Mostrar todas as cidades de uma UF    
                        case 3:
                            System.out.print("\nDigite a UF do estado que deseja visualizar as cidades: ");
                            String ufEstado = scan.nextLine().toUpperCase();
                            System.out.println(bancoDeDados.buscarCidadesDaUF(ufEstado));
                            break;
                        // Mostrar todos os bairros de uma cidade    
                        case 4:
                            System.out.print("\nDigite a UF do estado desejado: ");
                            String uFEstado = scan.nextLine().toUpperCase();
                            System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                            String nomeCidade = scan.nextLine();
                            System.out.println(bancoDeDados.buscarBairrosDaCidade(uFEstado, nomeCidade));
                            break;
                        // Mostrar todos os logradouros de um bairro
                        case 5:
                            System.out.print("\nDigite a UF do estado desejado: ");
                            String UFEstado = scan.nextLine().toUpperCase();
                            System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                            String nameCidade = scan.nextLine();
                            System.out.print("Digite o nome do bairro que deseja ver as ruas: ");
                            String nomeBairro = scan.nextLine();
                            System.out.println(bancoDeDados.buscarLogradourosDoBairro(UFEstado, nameCidade, nomeBairro));
                            break;
                        // Mostrar dados de um logradouro através do CEP
                        case 6:
                            System.out.print("\nDigite um CEP: ");
                            String cepLogradouro = scan.nextLine();
                            System.out.println(bancoDeDados.pegarLogradouroPeloCEP(cepLogradouro));
                            break;
                        // Mostrar dados de um logradouro através do nome
                        case 7:
                            System.out.print("\nDigite o nome do logradouro: ");
                            String nomeLogradouro = scan.nextLine();
                            System.out.println(bancoDeDados.pegarLogradouroPeloNome(nomeLogradouro));
                            break;
                        // Caso escolha um número que não está nas opções
                        case 8:
                            menuTestes();
                            int escolhaMenuTestes = Integer.parseInt(scan.nextLine());
                            while (escolhaMenuTestes != 0) {
                                switch (escolhaMenuTestes) {
                                    case 1:
                                        int cont = 0;
                                        int total = 0;
                                        while (!(cont == 3)) {
                                            long valorinicialTeste = System.currentTimeMillis();
                                            bancoDeDados.lerArquivos();
                                            long valorfinalTeste = System.currentTimeMillis();
                                            long totalTeste = valorfinalTeste - valorinicialTeste;
                                            System.out.println(totalTeste);
                                            total += (totalTeste);
                                            cont++;
                                        }
                                        System.out.println("asdsasdasasaddas" + (total/3));
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.out.println("\nOpção Inválida!");
                                        break;
                                }
                                System.out.println("");
                                menuTestes();
                                escolhaMenuTestes = Integer.parseInt(scan.nextLine());
                            }
                            break;
                        default:
                            System.out.println("\nOpção inválida !");
                            break;
                    }
                    System.out.println("");
                    menu();
                    resp = Integer.parseInt(scan.nextLine());
                }
                break;
            } catch (NumberFormatException exception) {
                // Caso seja digitado algum caractere diferente de número
                System.out.println("\nComando inválido!\n");
            }
        }

    }

}

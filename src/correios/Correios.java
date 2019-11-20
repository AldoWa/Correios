/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios;

import classes.BancoDeDados;
import java.io.IOException;
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
        System.out.println("6 - Buscar logradouro por CEP");
        System.out.println("7 - Buscar logradouro por nome");
        System.out.println("8 - Menu de Testes");
        System.out.println("0 - Finalizar programa");
        System.out.println("--------------------------------------------");
        System.out.print("Digite uma opção: ");
    }

    static void menuTestes() {
        System.out.println("1 - Teste de leitura de arquivos");
        System.out.println("2 - Teste de busca por todos os estados");
        System.out.println("3 - Teste de busca por todas as cidades de um estado");
        System.out.println("4 - Teste de busca por todos os bairros de uma cidade");
        System.out.println("5 - Teste de busca por todos os logradouros de um bairro");
        System.out.println("6 - Teste de busca por logradouro por CEP");
        System.out.println("7 - Teste de busca por logradouro por nome");
        System.out.println("0 - Voltar ao menu anterior");
        System.out.println("--------------------------------------------");
        System.out.print("Digite uma opção: ");
    }

    public static void main(String[] args) {
        // Inicialização do programa
        BancoDeDados bancoDeDados;
        while(true){
            try {
                bancoDeDados = new BancoDeDados();
                break;
            } catch (IOException ex) {
            }
        }
        System.out.println("Programa iniciado com sucesso !\n");
        Scanner scan = new Scanner(System.in);
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
                            System.out.print(bancoDeDados.retornarUFs());
                            break;
                        // Mostrar todas as cidades de uma UF    
                        case 3:
                            System.out.print("\nDigite a UF do estado que deseja visualizar as cidades: ");
                            String ufEstado = scan.nextLine().toUpperCase();
                            System.out.print(bancoDeDados.buscarCidadesDaUF(ufEstado));
                            break;
                        // Mostrar todos os bairros de uma cidade    
                        case 4:
                            System.out.print("\nDigite a UF do estado desejado: ");
                            String uFEstado = scan.nextLine().toUpperCase();
                            System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                            String nomeCidade = scan.nextLine();
                            System.out.print(bancoDeDados.buscarBairrosDaCidade(uFEstado, nomeCidade));
                            break;
                        // Mostrar todos os logradouros de um bairro
                        case 5:
                            System.out.print("\nDigite a UF do estado desejado: ");
                            String UFEstado = scan.nextLine().toUpperCase();
                            System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                            String nameCidade = scan.nextLine();
                            System.out.print("Digite o nome do bairro que deseja ver as ruas: ");
                            String nomeBairro = scan.nextLine();
                            System.out.print(bancoDeDados.buscarLogradourosDoBairro(UFEstado, nameCidade, nomeBairro));
                            break;
                        // Mostrar dados de um logradouro através do CEP
                        case 6:
                            System.out.print("\nDigite um CEP: ");
                            String cepLogradouro = scan.nextLine();
                            System.out.print(bancoDeDados.pegarLogradouroPeloCEP(cepLogradouro));
                            break;
                        // Mostrar dados de um logradouro através do nome
                        case 7:
                            System.out.print("\nDigite o nome do logradouro: ");
                            String nomeLogradouro = scan.nextLine();
                            System.out.print(bancoDeDados.pegarLogradouroPeloNome(nomeLogradouro));
                            break;
                        // Menu de testes
                        case 8:
                            System.out.println("");
                            menuTestes();
                            int escolhaMenuTestes = Integer.parseInt(scan.nextLine());
                            while (escolhaMenuTestes != 0) {
                                switch (escolhaMenuTestes) {
                                    // Teste de leitura de arquivos 
                                    case 1:
                                        System.out.println(bancoDeDados.testeLeituraArquivo());
                                        break;
                                    // Teste de busca por todos os estados
                                    case 2:
                                        System.out.println(bancoDeDados.testeBuscaEstados());
                                        break;
                                    // Teste de busca por todas as cidades de um estado
                                    case 3:
                                        System.out.print("\nDigite a UF do estado : ");
                                        String uf3 = scan.nextLine().toUpperCase();
                                        System.out.println(bancoDeDados.testeBuscaCidadesEstado(uf3));
                                        break;
                                    // Teste de busca por todos os bairros de uma cidade
                                    case 4:
                                        System.out.print("\nDigite a UF do estado : ");
                                        String uf4 = scan.nextLine().toUpperCase();
                                        System.out.print("Digite o nome da cidade : ");
                                        String nomeCidade4 = scan.nextLine();
                                        System.out.println(bancoDeDados.testeBuscaBairrosCidade(uf4, nomeCidade4));
                                        break;
                                    // Teste de busca por todos os logradouros de um bairro
                                    case 5:
                                        System.out.print("\nDigite a UF do estado desejado: ");
                                        String uf5 = scan.nextLine().toUpperCase();
                                        System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                                        String nomeCidade5 = scan.nextLine();
                                        System.out.print("Digite o nome do bairro que deseja ver as ruas: ");
                                        String nomeBairro5 = scan.nextLine();
                                        System.out.println(bancoDeDados.testeLogradourosBairro(uf5, nomeCidade5, nomeBairro5));
                                        break;
                                    // Teste de busca por logradouro por CEP
                                    case 6:
                                        System.out.print("\nDigite um CEP: ");
                                        String cep = scan.nextLine();
                                        System.out.println(bancoDeDados.testeBuscaLogradouroCEP(cep));
                                        break;
                                    // Teste de busca por logradouro por nome
                                    case 7:
                                        System.out.print("\nDigite o nome do logradouro: ");
                                        String logradouro = scan.nextLine();
                                        System.out.println(bancoDeDados.testeBuscaLogradouroNome(logradouro));
                                        break;
                                    // Caso a opção escolhida não esteja no menu
                                    default:
                                        System.out.println("\nOpção Inválida!");
                                        break;
                                }
                                System.out.println("");
                                menuTestes();
                                escolhaMenuTestes = Integer.parseInt(scan.nextLine());
                            }
                            break;
                        // Caso a opção escolhida não esteja no menu
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

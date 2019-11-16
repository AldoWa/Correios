/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios;

import classes.BancoDeDados;
import classes.LeitorDeBairros;
import classes.LeitorDeCidades;
import classes.LeitorDeLogradouros;
import classes.LeitorUnidadeFederal;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Aldo
 */
public class Correios {

    static void menu() {
        System.out.println("1 - Ler arquivo");
        System.out.println("2 - Buscar informações");
        System.out.println("3 - Listar informações");
        System.out.println("0 - Finalizar programa");
        System.out.println("---------------------------");
        System.out.print("Digite uma opção : ");
    }

    static void menuBusca() {
        System.out.println("1 - Buscar Unidade Federativa");
        System.out.println("2 - Buscar Cidade");
        System.out.println("3 - Buscar Bairro");
        System.out.println("4 - Buscar Logradouros");
        System.out.println("0 - Voltar");
        System.out.println("---------------------------");
        System.out.print("Digite uma opção : ");
    }

    static void menuListagem() {
        System.out.println("1 - Listar Unidades Federativas ");
        System.out.println("2 - Listar Cidades");
        System.out.println("3 - Listar Bairros");
        System.out.println("4 - Listar Logradouros");
        System.out.println("0 - Voltar");
        System.out.println("---------------------------");
        System.out.print("Digite uma opção : ");
    }

    public static void main(String[] args) {
        //Menu do programa, para escolher as opções
        BancoDeDados bancoDeDados = new BancoDeDados();
        JFileChooser chooser = new JFileChooser();
        Scanner scan = new Scanner(System.in);
        menu();
        int resp = Integer.parseInt(scan.nextLine());
        int respBusca;
        while (resp != 0) {
            switch (resp) {
                case 1:
                    // Leitura do arquivo
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int retorno = chooser.showOpenDialog(null);
                    if (retorno == JFileChooser.APPROVE_OPTION) {
                        try {
                            File arquivoAtual = chooser.getSelectedFile();
                            LeitorUnidadeFederal leitorUF = new LeitorUnidadeFederal(arquivoAtual);
                            LeitorDeCidades leitorCidade = new LeitorDeCidades(arquivoAtual);
                            LeitorDeBairros leitorBairros = new LeitorDeBairros(arquivoAtual);
                            LeitorDeLogradouros leitorLogradouros = new LeitorDeLogradouros(arquivoAtual, bancoDeDados);
                            leitorUF.lerUF(bancoDeDados);
                            leitorCidade.lerCidade(bancoDeDados);
                            leitorBairros.lerBairro(bancoDeDados);
                            leitorLogradouros.lerLogradouro();

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum diretório foi selecionado pelo usuário!");
                    }
                    break;
                case 2:
                    System.out.println("");
                    menuBusca();
                    respBusca = Integer.parseInt(scan.nextLine());
                    switch (respBusca) {
                        case 1:
                            System.out.print("\nDigite a UF do estado que deseja visualizar as cidades: ");
                            String ufEstado = scan.nextLine().toUpperCase();
                            System.out.println(bancoDeDados.buscarCidadesDaUF(ufEstado));
                            break;
                        case 2:
                            System.out.print("\nDigite a UF do estado desejado: ");
                            String uFEstado = scan.nextLine().toUpperCase();
                            System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                            String nomeCidade = scan.nextLine();
                            System.out.println(bancoDeDados.buscarBairrosDaCidade(uFEstado, nomeCidade));
                            break;
                        case 3:
                            System.out.print("\nDigite a UF do estado desejado: ");
                            String UFEstado = scan.nextLine().toUpperCase();
                            System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                            String nameCidade = scan.nextLine();
                            System.out.print("Digite o nome do bairro que deseja ver as ruas: ");
                            String nomeBairro = scan.nextLine();
                            System.out.println(bancoDeDados.buscarLogradourosDoBairro(UFEstado, nameCidade, nomeBairro));
                            break;
                        case 4:
                            System.out.println("\nDigite um CEP: ");
                            String cepLogradouro = scan.nextLine();
                            System.out.println(bancoDeDados.pegarLogradouroPeloCEP(cepLogradouro));
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    System.out.println("");
                    menuListagem();
                    respBusca = Integer.parseInt(scan.nextLine());
                    switch (respBusca) {
                        case 1:
                            System.out.println("\nSolitação : UF");
                            System.out.println("Saída : Listagem das cidades presentes naquela UF");
                            break;
                        case 2:
                            System.out.println("\nSolitação : UF e nome da cidade");
                            System.out.println("Saída : Listagem dos bairros presentes naquela cidade : ");
                            break;
                        case 3:
                            System.out.println("\nSolicitação : UF, nome da cidade e o nome do bairro ");
                            System.out.println("Saída : Listagem dos logradouros presentes naquele bairro");
                            break;
                        default:
                            break;
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

    }

}

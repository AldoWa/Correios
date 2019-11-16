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
        System.out.println("2 - Visualizar estados");
        System.out.println("3 - Visualizar cidades de um estado");
        System.out.println("4 - Visualizar bairros de uma cidade");
        System.out.println("5 - Visualizar logradouros de um bairro");
        System.out.println("6 - Buscar logradouro por um CEP");
        System.out.println("7 - Buscar logradouro pelo nome");
        System.out.println("0 - Finalizar programa");
        System.out.print("Digite uma opção: ");
    }

    public static void main(String[] args) {
        //Menu do programa, para escolher as opções
        BancoDeDados bancoDeDados = new BancoDeDados();
        JFileChooser chooser = new JFileChooser();
        Scanner scan = new Scanner(System.in);
        menu();
        int resp = Integer.parseInt(scan.nextLine());
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
                            System.out.println("Arquivos lidos!");

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum diretório foi selecionado pelo usuário!");
                    }
                    break;
                case 2:
                    System.out.println(bancoDeDados.retornarUFs());
                    break;
                case 3:
                    System.out.print("\nDigite a UF do estado que deseja visualizar as cidades: ");
                    String ufEstado = scan.nextLine().toUpperCase();
                    System.out.println(bancoDeDados.buscarCidadesDaUF(ufEstado));
                    break;
                case 4:
                    System.out.print("\nDigite a UF do estado desejado: ");
                    String uFEstado = scan.nextLine().toUpperCase();
                    System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                    String nomeCidade = scan.nextLine();
                    System.out.println(bancoDeDados.buscarBairrosDaCidade(uFEstado, nomeCidade));
                    break;
                case 5:
                    System.out.print("\nDigite a UF do estado desejado: ");
                    String UFEstado = scan.nextLine().toUpperCase();
                    System.out.print("Digite o nome da cidade que deseja ver os bairros: ");
                    String nameCidade = scan.nextLine();
                    System.out.print("Digite o nome do bairro que deseja ver as ruas: ");
                    String nomeBairro = scan.nextLine();
                    System.out.println(bancoDeDados.buscarLogradourosDoBairro(UFEstado, nameCidade, nomeBairro));
                    break;
                case 6:
                    System.out.println("\nDigite um CEP: ");
                    String cepLogradouro = scan.nextLine();
                    System.out.println(bancoDeDados.pegarLogradouroPeloCEP(cepLogradouro));
                    break;
                case 7:
                    System.out.println("\nDigite o nome do logradouro: ");
                    String nomeLogradouro = scan.nextLine();
                    System.out.println(bancoDeDados.pegarLogradouroPeloNome(nomeLogradouro));
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

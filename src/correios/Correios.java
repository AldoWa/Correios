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
import classes.Logradouro;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Aldo
 */
public class Correios {

    public static void main(String[] args) {
        BancoDeDados bancoDeDados = new BancoDeDados();
        JFileChooser chooser = new JFileChooser();
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

                ConcurrentHashMap<String, Logradouro> Logradouros = bancoDeDados.getMapDeLogradouros();

//                for (Map.Entry<String, Logradouro> entry : Logradouros.entrySet()) {
//                    System.out.println(entry);
//                }
//                System.out.println(Logradouros.size());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum diretorio foi selecionado pelo usuario");
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios;

import classes.BancoDeDados;
import classes.Cidade;
import classes.Estado;
import classes.LeitorDeCidades;
import classes.LeitorUnidadeFederal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
                leitorUF.lerUF(bancoDeDados);
                leitorCidade.lerCidade(bancoDeDados);
                System.out.println(bancoDeDados.getMapDeEstados());
                HashMap<String, Cidade> cidades = bancoDeDados.getMapDeEstados().get("SE").getCidades();
                for (Map.Entry<String, Cidade> entry : cidades.entrySet()) {
                    System.out.println(entry);
                }
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum diretorio foi selecionado pelo usuario");
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Matheus Nunes
 */
public class LeitorUnidadeFederal {
    
    // ATRIBUTOS
    private ArrayList<File> vetorDeArquivosUF;
    
    // CONSTRUTOR
    public LeitorUnidadeFederal(File pastaDosArquivos) throws IOException {
        this.vetorDeArquivosUF = new ArrayList<>();
        this.filtraArquivosUF(pastaDosArquivos.listFiles());
        if (this.vetorDeArquivosUF.isEmpty()){
            throw new IOException("O Diretorio de unidades federais informado é invalido");
        }
    }
    
    // MÉTODOS
    // Metodo para filtrar os arquivos da pasta somente referentes as unidades federacao
    private void filtraArquivosUF(File[] vetorDeArquivos){
        for (int i = 0; i < vetorDeArquivos.length; i++) {
            if (vetorDeArquivos[i].getName().contains("UNIDADES_FEDERACAO.TXT")){
                this.vetorDeArquivosUF.add(vetorDeArquivos[i]);
            }
        }
    }
    public void lerUF(BancoDeDados bancoDeDados) throws FileNotFoundException, IOException {
        // Percorrendo todos os arquivos das unidades federais
        for (int i = 0; i < this.vetorDeArquivosUF.size(); i++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.vetorDeArquivosUF.get(i)));
            // Pulando a primeira linha que sera o cabecalho
            bufferedReader.readLine();
            String linha = bufferedReader.readLine();
            // O final do arquivo termina com #, portanto o arquivo sera lido ate o respectivo caractere
            while(!linha.equals("#")){
                String siglaDoEstado = linha.substring(3,5).trim();
                String nomeDoEstado = linha.substring(9,81).trim();
                Estado novoEstado = new Estado(siglaDoEstado,nomeDoEstado);
                // Verificando se existem estados de mesmo nome
                if (!bancoDeDados.getMapDeEstados().containsKey(novoEstado.getSiglaEstado())) {
                    bancoDeDados.cadastrarEstado(novoEstado);
                }
                linha = bufferedReader.readLine();
            }
        }
    }
}

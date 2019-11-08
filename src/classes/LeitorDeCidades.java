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
public class LeitorDeCidades {

    private ArrayList<File> vetorDeArquivosCidades;

    public LeitorDeCidades(File pastaDosArquivos) throws IOException {
        this.vetorDeArquivosCidades = new ArrayList<>();
        this.filtraArquivosCidades(pastaDosArquivos.listFiles());
        if (this.vetorDeArquivosCidades.isEmpty()) {
            throw new IOException("O Diretorio de cidades informado é invalido");
        }
    }

    //Metodo para filtrar os arquivos da pasta somente referentes as cidades
    private void filtraArquivosCidades(File[] vetorDeArquivos) {
        for (int i = 0; i < vetorDeArquivos.length; i++) {
            if (vetorDeArquivos[i].getName().contains("LOCALIDADES.TXT")) {
                this.vetorDeArquivosCidades.add(vetorDeArquivos[i]);
            }
        }
    }

    public void lerCidade(BancoDeDados bancoDeDados) throws FileNotFoundException, IOException {
        //Percorrendo todos os arquivos das cidades
        for (int i = 0; i < this.vetorDeArquivosCidades.size(); i++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.vetorDeArquivosCidades.get(i)));
            //Pulando a primeira linha que sera o cabecalho
            bufferedReader.readLine();
            String linha = bufferedReader.readLine();
            //O final do arquivo termina com #, portanto a leitura sera realizada ate que se tenha lido todo o arquivo
            while (!linha.equals("#")) {
                String nomeDaCidade = linha.substring(19, 91).trim();
                Estado estado = bancoDeDados.getMapDeEstados().get(linha.substring(3, 5).trim());
                Cidade novaCidade = new Cidade(nomeDaCidade, estado);
                //Verificando se existem cidades de mesmo nome ja cadastradas no hashMap
                if (!estado.getCidades().containsKey(novaCidade.getNomeCidade())) {
                    estado.getCidades().put(novaCidade.getNomeCidade(), novaCidade);
                }
                linha = bufferedReader.readLine();
            }
        }

    }
}

/**
 *
 * @author Aldo Wanderley Costa Junior
 */
package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorDeLogradouros {

    private ArrayList<File> arquivosLogradouros;

    public LeitorDeLogradouros(File arquivo) throws IOException {
        //Iniciando o vetor
        this.arquivosLogradouros = new ArrayList<>();
        //Filtrando os arquivos
        this.filtrarArquivos(arquivo.listFiles());
        //Checando se o diretorio e valido
        if (this.arquivosLogradouros.isEmpty()) {
            throw new IOException("O Diretorio de Logradouros informado é invalido");
        }
    }

    //Filtrando os arquivos so de LOGRADOUROS
    public void filtrarArquivos(File[] arquivosVetor) {
        for (int i = 0; i < arquivosVetor.length; i++) {
            if (arquivosVetor[i].getName().contains("LOGRADOUROS.TXT")) {
                this.arquivosLogradouros.add(arquivosVetor[i]);
            }
        }
        System.out.println(this.arquivosLogradouros.size());
    }

    //Lendo o logradouro
    public void lerLogradouro(BancoDeDados bancoDeDados) throws FileNotFoundException, IOException {
        //Checando o tamanho de quantos arquivos tem dentro do arrayList
        for (int i = 0; i < this.arquivosLogradouros.size(); i++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.arquivosLogradouros.get(i)));
            //Pulando linha
            bufferedReader.readLine();
            String linha = bufferedReader.readLine();
            while (!linha.equals("#")) {
                String cep = linha.substring(518, 526).trim();
                Estado estado = bancoDeDados.getMapDeEstados().get(linha.substring(1, 3).trim());
                Cidade cidade = estado.getCidades().get(linha.substring(17, 89).trim());
                Bairro bairro = cidade.getBairros().get(linha.substring(102, 174).trim());
                String nome = linha.substring(374, 446).trim();
                Logradouro logradouro = new Logradouro(cep, cidade, estado, bairro, nome);
                
                if(!bancoDeDados.getMapDeLogradouros().containsKey(cep)){
                    bancoDeDados.getMapDeLogradouros().put(cep, logradouro);
                }
                
                
                if (!bairro.getLogradouros().containsKey(logradouro.getCep())) {
                    bairro.getLogradouros().put(cep, logradouro);
                }
                linha = bufferedReader.readLine();
            }
        }
    }
}

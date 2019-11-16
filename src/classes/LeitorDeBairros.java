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

public class LeitorDeBairros {
    // ATRIBUTOS   
    // Para armazenar todos os txt de bairros
    private ArrayList<File> vetorDeBairros;

    // CONSTRUTOR
    public LeitorDeBairros(File arquivos) throws IOException {
        this.vetorDeBairros = new ArrayList();
        this.filtrarArquivosDeBairros(arquivos.listFiles());
        if (this.vetorDeBairros.isEmpty()) {
            throw new IOException("Diretorio de bairros esta vazio");
        }
    }
    
    // MÉTODOS
    // Filtrando os arquivos que so tem BAIRRO
    public void filtrarArquivosDeBairros(File[] arquivosBairros) {
        for (int i = 0; i < arquivosBairros.length; i++) {
            if (arquivosBairros[i].getName().contains("BAIRROS.TXT")) {
                this.vetorDeBairros.add(arquivosBairros[i]);
            }
        }

    }
    public void lerBairro(BancoDeDados bancoDeDados) throws FileNotFoundException, IOException {
        for (int i = 0; i < this.vetorDeBairros.size(); i++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.vetorDeBairros.get(i)));
            //Pular o cabecalho 
            bufferedReader.readLine();
            String linha = bufferedReader.readLine();
            while (!linha.equals("#")) {
                //Armazenando um nomedoBairro
                String nomeBairro = linha.substring(102, 174).trim();
                //Pegando a cidade a qual o bairro pertence
                Cidade cidade = bancoDeDados.getMapDeEstados().get(linha.substring(1, 3).trim()).getCidades().get(linha.substring(17, 89).trim());
                //Criando bairro
                Bairro bairro = new Bairro(nomeBairro);

                if (!cidade.getBairros().containsKey(bairro.getNomeBairro())) {
                    cidade.getBairros().put(bairro.getNomeBairro(), bairro);
                }
                linha = bufferedReader.readLine();
            }
        }
    }
}

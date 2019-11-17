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

/**
 *
 * @author Matheus Nunes
 */
public class RunnableLeitorLogradouro implements Runnable{
    
    // ATRIBUTOS
    private BufferedReader leitor;
    private BancoDeDados bancoDeDados;
    private File arquivoASerLido;
    public RunnableLeitorLogradouro(File arquivoASerLido,BancoDeDados bancoDeDados) throws FileNotFoundException {
        this.arquivoASerLido = arquivoASerLido;
        this.leitor = new BufferedReader(new FileReader(this.arquivoASerLido));
        this.bancoDeDados = bancoDeDados;
    }
        
    // RUN
    @Override
    public void run(){
        try {
            // Pulando linha de cabeçalho
            String linha = this.leitor.readLine();
            linha = leitor.readLine();
            while (!linha.equals("#")) {
                String cep = linha.substring(518, 526).trim();
                Estado estado = this.bancoDeDados.getMapDeEstados().get(linha.substring(1, 3).trim());
                Cidade cidade = estado.getCidades().get(linha.substring(17, 89).trim());
                Bairro bairro = cidade.getBairros().get(linha.substring(102, 174).trim());
                String nome = linha.substring(374, 446).trim();
                Logradouro logradouro = new Logradouro(cep, cidade, estado, bairro, nome);
                if(!this.bancoDeDados.getMapDeLogradouros().containsKey(cep)){
                    this.bancoDeDados.getMapDeLogradouros().put(cep, logradouro);
                }
                if (!bairro.getLogradouros().containsKey(logradouro.getCep())) {
                    bairro.getLogradouros().put(cep, logradouro);
                }
                linha = this.leitor.readLine();
            }
            this.leitor = new BufferedReader(new FileReader(this.arquivoASerLido));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus Nunes
 */
public class BancoDeDados {

    // ATRIBUTOS
    // O mapa de estados ira salvar os estados de acordo com o seus respectivos nomes
    private HashMap<String, Estado> mapDeEstados;
    // O mapa de Logradouros ira salvar cada logradouro de acordo com o cep
    private ConcurrentHashMap<String, Logradouro> mapDeLogradouros;
    private LeitorUnidadeFederal leitorUF;
    private LeitorDeCidades leitorCidade;
    private LeitorDeBairros leitorBairros;
    private LeitorDeLogradouros leitorLogradouros;

    // CONSTRUTOR
    public BancoDeDados() throws IOException {
        this.mapDeEstados = new HashMap<>();
        this.mapDeLogradouros = new ConcurrentHashMap<>();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retorno = chooser.showOpenDialog(null);
        File arquivoAtual = chooser.getSelectedFile();
        if(retorno == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "Programa finalizado!");
            System.exit(0);
        }
        try {
            this.leitorUF = new LeitorUnidadeFederal(arquivoAtual);
            this.leitorCidade = new LeitorDeCidades(arquivoAtual);
            this.leitorBairros = new LeitorDeBairros(arquivoAtual);
            this.leitorLogradouros = new LeitorDeLogradouros(arquivoAtual, this);
            this.lerArquivos();
        } catch (IOException ex) {
            throw ex;
        }

    }

    // GETTERS
    public HashMap<String, Estado> getMapDeEstados() {
        return mapDeEstados;
    }

    public ConcurrentHashMap<String, Logradouro> getMapDeLogradouros() {
        return mapDeLogradouros;
    }

    // MÉTODOS
    public void cadastrarEstado(Estado estado) {
        this.mapDeEstados.put(estado.getSiglaEstado(), estado);
    }

    public String buscarCidadesDaUF(String sigla) {
        StringBuilder string = new StringBuilder();
        string.append("\n");
        Estado estado = this.mapDeEstados.get(sigla);

        if (estado == null) {
            return "\nNão existe estado com essa sigla!\n";
        }
        int cont = 1;
        for (Cidade cidade : estado.getCidades().values()) {
            string.append(Integer.toString(cont)).append(" - ").append(cidade.getNomeCidade()).append("\n");
            cont++;
        }
        return string.toString();
    }

    public String buscarBairrosDaCidade(String UF, String nomeCidade) {
        StringBuilder string = new StringBuilder();
        string.append("\n");

        Estado estado = this.mapDeEstados.get(UF);
        Cidade cidade = null;

        if (estado == null) {
            return "\nNão existe estado com essa sigla!\n";
        } else {
            for (Cidade cidadeNoBanco : estado.getCidades().values()) {
                if (nomeCidade.compareToIgnoreCase(cidadeNoBanco.getNomeCidade()) == 0) {
                    cidade = estado.getCidades().get(cidadeNoBanco.getNomeCidade());
                    break;
                }
            }
            if (cidade == null) {
                return "\nNão existe neste estado cidade com esse nome!\n";
            } else {
                int cont = 1;
                for (Bairro bairro : cidade.getBairros().values()) {
                    string.append(Integer.toString(cont)).append(" - ").append(bairro.getNomeBairro()).append("\n");
                    cont++;
                }
                return string.toString();
            }
        }
    }

    public String buscarLogradourosDoBairro(String UF, String nomeCidade, String nomeBairro) {
        StringBuilder string = new StringBuilder();
        string.append("\n");

        Estado estado = this.mapDeEstados.get(UF);
        Cidade cidade = null;
        Bairro bairro = null;

        if (estado == null) {
            return "\nNão existe estado com essa sigla!\n";
        } else {
            for (Cidade cidadeNoBanco : estado.getCidades().values()) {
                if (nomeCidade.compareToIgnoreCase(cidadeNoBanco.getNomeCidade()) == 0) {
                    cidade = estado.getCidades().get(cidadeNoBanco.getNomeCidade());
                    break;
                }
            }
            if (cidade == null) {
                return "\nNão existe neste estado cidade com esse nome!\n";

            } else {
                for (Bairro bairroNoBanco : cidade.getBairros().values()) {
                    if (nomeBairro.compareToIgnoreCase(bairroNoBanco.getNomeBairro()) == 0) {
                        bairro = cidade.getBairros().get(bairroNoBanco.getNomeBairro());
                        break;
                    }
                }
                if (bairro == null) {
                    return "\nNão existe neste bairro cidade com esse nome!\n";
                } else {

                    int cont = 1;
                    for (Logradouro logradouro : bairro.getLogradouros().values()) {
                        string.append(Integer.toString(cont)).append(" - ").append(logradouro.getNomeLogradouro()).append("\n");
                        cont++;
                    }
                    return string.toString();
                }
            }
        }
    }

    public String pegarLogradouroPeloCEP(String CEP) {
        StringBuilder string = new StringBuilder();
        Logradouro logradouro = this.mapDeLogradouros.get(CEP);

        if (logradouro == null) {
            return "\nNão existe logradouro com esse CEP!\n";
        }

        string.append("\nCEP do logradouro: ").append(logradouro.getCep()).append("\n")
                .append("Nome do logradouro: ").append(logradouro.getNomeLogradouro())
                .append("\n").append("Bairro do logradouro: ").append(logradouro.getBairro())
                .append("\n").append("Cidade do logradouro: ").append(logradouro.getCidade())
                .append("\n").append("Estado do logradouro: ").append(logradouro.getEstado())
                .append("\n");

        return string.toString();
    }

    public String pegarLogradouroPeloNome(String nomeLogradouro) {
        StringBuilder string = new StringBuilder();
        string.append("-----------------------------------------------");
        
        boolean entrou = false;
        for (Logradouro logradouro : this.mapDeLogradouros.values()) {
            if (nomeLogradouro.compareToIgnoreCase(logradouro.getNomeLogradouro()) == 0) {
                entrou = true;
                string.append("\nCEP do logradouro: ").append(logradouro.getCep()).append("\n")
                        .append("Nome do logradouro: ").append(logradouro.getNomeLogradouro())
                        .append("\n").append("Bairro do logradouro: ").append(logradouro.getBairro())
                        .append("\n").append("Cidade do logradouro: ").append(logradouro.getCidade())
                        .append("\n").append("Estado do logradouro: ").append(logradouro.getEstado())
                        .append("\n").append("-----------------------------------------------");
            }
        }
        if (entrou == false) {
            return "\nNão existe logradouro com esse nome!\n";
        }
        string.append("\n");
        return string.toString();
    }

    public String retornarUFs() {
        StringBuilder string = new StringBuilder();
        string.append("\n");
        for (Estado estado : this.mapDeEstados.values()) {
            string.append(estado.getSiglaEstado()).append(" - ")
                    .append(estado.getNome()).append("\n");
        }
        return string.toString();
    }

    public String lerArquivos() {
        try {
            // Leitura do arquivo
            leitorUF.lerUF(this);
            leitorCidade.lerCidade(this);
            leitorBairros.lerBairro(this);
            leitorLogradouros.lerLogradouro();
        } catch (IOException ex) {
            return "\nErro na leitura!";
        }
        return "\nLeitura realizada com sucesso!";
    }

    // Realiza 3 vezes a leitura do arquivo. Ao final do método, é retornada a média do tempo de execução da leitura
    public String testeLeituraArquivo() {
        StringBuilder string = new StringBuilder();
        string.append("\nTempo de teste = ");
        long tempoInicio;
        long tempoFim;
        long tempoTotal = 0;
        for (int i = 0; i < 3; i++) {
            tempoInicio = System.nanoTime();
            this.lerArquivos();
            tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio);
        }
        string.append(tempoTotal/3).append(" nanosegundos");
        return string.toString();
    }
    
    // Realiza 7 vezes uma busca por nome e sigla de cada estado e armazena em uma string. Ao final do método, é retornada a média do tempo de execução da busca
    public String testeBuscaEstados() {
        StringBuilder string = new StringBuilder();
        string.append("\nTempo de teste = ");
        long tempoInicio;
        long tempoFim;
        long tempoTotal = 0;
        for (int i = 0; i < 7; i++) {
            tempoInicio = System.nanoTime();
            this.retornarUFs();
            tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio);
        }
        string.append(tempoTotal/7).append(" nanosegundos");
        return string.toString();
    }
    
    // Realiza 7 vezes uma busca por nome de cada cidade de um estado e armazena em uma string. Ao final do método, é retornada a média do tempo de execução da busca
    public String testeBuscaCidadesEstado(String sigla) {
        StringBuilder string = new StringBuilder();
        string.append("\nTempo de teste = ");
        long tempoInicio;
        long tempoFim;
        long tempoTotal = 0;
        for (int i = 0; i < 7; i++) {
            tempoInicio = System.nanoTime();
            this.buscarCidadesDaUF(sigla);
            tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio);
        }
        string.append(tempoTotal/7).append(" nanosegundos");
        return string.toString();
    }
    
    public String testeBuscaBairrosCidade(String UF, String nomeCidade) {
        StringBuilder string = new StringBuilder();
        string.append("\nTempo de teste = ");
        long tempoInicio;
        long tempoFim;
        long tempoTotal = 0;
        for (int i = 0; i < 7; i++) {
            tempoInicio = System.nanoTime();
            this.buscarBairrosDaCidade(UF, nomeCidade);
            tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio);
        }
        string.append(tempoTotal/7).append(" nanosegundos");
        return string.toString();
    }
    
    public String testeLogradourosBairro(String UF, String nomeCidade, String nomeBairro) {
        StringBuilder string = new StringBuilder();
        string.append("\nTempo de teste = ");
        long tempoInicio;
        long tempoFim;
        long tempoTotal = 0;
        for (int i = 0; i < 7; i++) {
            tempoInicio = System.nanoTime();
            this.buscarLogradourosDoBairro(UF, nomeCidade, nomeBairro);
            tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio);
        }
        string.append(tempoTotal/7).append(" nanosegundos");
        return string.toString();
    }
    
    public String testeBuscaLogradouroCEP(String CEP) {
        StringBuilder string = new StringBuilder();
        string.append("\nTempo de teste = ");
        long tempoInicio;
        long tempoFim;
        long tempoTotal = 0;
        for (int i = 0; i < 7; i++) {
            tempoInicio = System.nanoTime();
            this.pegarLogradouroPeloCEP(CEP);
            tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio);
        }
        string.append(tempoTotal/7).append(" nanosegundos");
        return string.toString();
    }
    
    public String testeBuscaLogradouroNome(String nome) {
        StringBuilder string = new StringBuilder();
        string.append("\nTempo de teste = ");
        long tempoInicio;
        long tempoFim;
        long tempoTotal = 0;
        for (int i = 0; i < 7; i++) {
            tempoInicio = System.nanoTime();
            this.pegarLogradouroPeloNome(nome);
            tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio);
        }
        string.append(tempoTotal/7).append(" nanosegundos");
        return string.toString();
    }
    
    
}

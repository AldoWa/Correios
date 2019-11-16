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

    // CONSTRUTOR
    public BancoDeDados() {
        this.mapDeEstados = new HashMap<>();
        this.mapDeLogradouros = new ConcurrentHashMap<>();
    }

    // GETTERS
    public HashMap<String, Estado> getMapDeEstados() {
        return mapDeEstados;
    }
    public ConcurrentHashMap<String, Logradouro> getMapDeLogradouros() {
        return mapDeLogradouros;
    }

    // M�TODOS
    public void cadastrarEstado(Estado estado) {
        this.mapDeEstados.put(estado.getSiglaEstado(), estado);
    }
    public String buscarCidadesDaUF(String sigla) {
        StringBuilder string = new StringBuilder();
        Estado estado = this.mapDeEstados.get(sigla);

        if (estado == null) {
            return "\nN�o existe estado com essa sigla!";
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

        Estado estado = this.mapDeEstados.get(UF);
        Cidade cidade = null;

        if (estado == null) {
            return "\nN�o existe estado com essa sigla!";
        } else {
            for (Cidade cidadeNoBanco : estado.getCidades().values()) {
                if (nomeCidade.compareToIgnoreCase(cidadeNoBanco.getNomeCidade()) == 0) {
                    cidade = estado.getCidades().get(cidadeNoBanco.getNomeCidade());
                    break;
                }
            }
            if (cidade == null) {
                return "\nN�o existe neste estado cidade com esse nome!";
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

        Estado estado = this.mapDeEstados.get(UF);
        Cidade cidade = null;
        Bairro bairro = null;

        if (estado == null) {
            return "\nN�o existe estado com essa sigla!";
        } else {
            for (Cidade cidadeNoBanco : estado.getCidades().values()) {
                if (nomeCidade.compareToIgnoreCase(cidadeNoBanco.getNomeCidade()) == 0) {
                    cidade = estado.getCidades().get(cidadeNoBanco.getNomeCidade());
                    break;
                }
            }
            if (cidade == null) {
                return "\nN�o existe neste estado cidade com esse nome!";

            } else {
                for (Bairro bairroNoBanco : cidade.getBairros().values()) {
                    if (nomeBairro.compareToIgnoreCase(bairroNoBanco.getNomeBairro()) == 0) {
                        bairro = cidade.getBairros().get(bairroNoBanco.getNomeBairro());
                        break;
                    }
                }
                if (bairro == null) {
                    return "\nN�o existe neste bairro cidade com esse nome!";
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
            return "\nN�o existe logradouro com esse CEP!";
        }

        string.append("CEP do logradouro: ").append(logradouro.getCep()).append("\n")
                .append("Nome do logradouro: ").append(logradouro.getNomeLogradouro())
                .append("\n").append("Bairro do logradouro: ").append(logradouro.getBairro())
                .append("\n").append("Cidade do logradouro: ").append(logradouro.getCidade())
                .append("\n").append("Estado do logradouro: ").append(logradouro.getEstado());

        return string.toString();
    }
    public String pegarLogradouroPeloNome(String nomeLogradouro) {
        StringBuilder string = new StringBuilder();

        for (Logradouro logradouro : this.mapDeLogradouros.values()) {
            if (nomeLogradouro.compareToIgnoreCase(logradouro.getNomeLogradouro()) == 0) {
                string.append("CEP do logradouro: ").append(logradouro.getCep()).append("\n")
                        .append("Nome do logradouro: ").append(logradouro.getNomeLogradouro())
                        .append("\n").append("Bairro do logradouro: ").append(logradouro.getBairro())
                        .append("\n").append("Cidade do logradouro: ").append(logradouro.getCidade())
                        .append("\n").append("Estado do logradouro: ").append(logradouro.getEstado())
                        .append("\n").append("-----------------------------------------------\n");
            }
        }
        return string.toString();
    }
    public String retornarUFs() {
        StringBuilder string = new StringBuilder();
        for (Estado estado : this.mapDeEstados.values()) {
            string.append(estado.getSiglaEstado()).append(" - ")
                    .append(estado.getNome()).append("\n");
        }
        return string.toString();
    }
    public String lerArquivos() {
        // Leitura do arquivo
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retorno = chooser.showOpenDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            try {
                File arquivoAtual = chooser.getSelectedFile();
                LeitorUnidadeFederal leitorUF = new LeitorUnidadeFederal(arquivoAtual);
                LeitorDeCidades leitorCidade = new LeitorDeCidades(arquivoAtual);
                LeitorDeBairros leitorBairros = new LeitorDeBairros(arquivoAtual);
                LeitorDeLogradouros leitorLogradouros = new LeitorDeLogradouros(arquivoAtual, this);
                leitorUF.lerUF(this);
                leitorCidade.lerCidade(this);
                leitorBairros.lerBairro(this);
                leitorLogradouros.lerLogradouro();
                return "\nArquivo lido com sucesso!\n";
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, um diret�rio deve ser selecionado antes de iniciar o programa!");
        }
        return "Falha ao ler arquivo!";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Matheus Nunes
 */
public class BancoDeDados {

    //O mapa de estados ira salvar os estados de acordo com o seus respectivo nome
    private HashMap<String, Estado> mapDeEstados;
    //O mapa de Logradouros ira salvar cada logradouros de acordo com o cep
    private ConcurrentHashMap<String, Logradouro> mapDeLogradouros;

    public BancoDeDados() {
        this.mapDeEstados = new HashMap<>();
        this.mapDeLogradouros = new ConcurrentHashMap<>();
    }

    public HashMap<String, Estado> getMapDeEstados() {
        return mapDeEstados;
    }

    public ConcurrentHashMap<String, Logradouro> getMapDeLogradouros() {
        return mapDeLogradouros;
    }

    public void cadastraEstado(Estado estado) {
        this.mapDeEstados.put(estado.getSiglaEstado(), estado);
    }

    public String buscarCidadesDaUF(String sigla) {
        StringBuilder string = new StringBuilder();
        Estado estado = this.mapDeEstados.get(sigla);

        if (estado == null) {
            return "\nNão existe estado com essa sigla!";
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
            return "\nNão existe estado com essa sigla!";
        } else {
            for (Cidade cidadeNoBanco : estado.getCidades().values()) {
                if (nomeCidade.compareToIgnoreCase(cidadeNoBanco.getNomeCidade()) == 0) {
                    cidade = estado.getCidades().get(cidadeNoBanco.getNomeCidade());
                    break;
                }
            }
            if (cidade == null) {
                return "\nNão existe neste estado cidade com esse nome!";
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
            return "\nNão existe estado com essa sigla!";
        } else {
            for (Cidade cidadeNoBanco : estado.getCidades().values()) {
                if (nomeCidade.compareToIgnoreCase(cidadeNoBanco.getNomeCidade()) == 0) {
                    cidade = estado.getCidades().get(cidadeNoBanco.getNomeCidade());
                    break;
                }
            }
            if (cidade == null) {
                return "\nNão existe neste estado cidade com esse nome!";

            } else {
                for (Bairro bairroNoBanco : cidade.getBairros().values()) {
                    if (nomeBairro.compareToIgnoreCase(bairroNoBanco.getNomeBairro()) == 0) {
                        bairro = cidade.getBairros().get(bairroNoBanco.getNomeBairro());
                        break;
                    }
                }
                if (bairro == null) {
                    return "\nNão existe neste bairro cidade com esse nome!";
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
    
    public String pegarLogradouroPeloCEP(String CEP){
        StringBuilder string = new StringBuilder();
        Logradouro logradouro = this.mapDeLogradouros.get(CEP);

        if (logradouro == null) {
            return "\nNão existe estado com essa sigla!";
        }

        string.append("CEP do logradouro: ").append(logradouro.getCep()).append("\n")
                .append("Nome do logradouro: ").append(logradouro.getNomeLogradouro())
                .append("\n").append("Bairro do logradouro: ").append(logradouro.getBairro())
                .append("\n").append("Cidade do logradouro: ").append(logradouro.getCidade())
                .append("\n").append("Estado do logradouro: ").append(logradouro.getEstado());
        
        return string.toString();
    }
}

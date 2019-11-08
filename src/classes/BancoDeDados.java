/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.HashMap;

/**
 *
 * @author Matheus Nunes
 */
public class BancoDeDados {
    
    //O mapa de estados ira salvar os estados de acordo com o seus respectivo nome
    private HashMap<String,Estado> mapDeEstados;
    //O mapa de Logradouros ira salvar cada logradouros de acordo com o cep
    private HashMap<String,Logradouro> mapDeLogradouros;

    public BancoDeDados() {
        this.mapDeEstados = new HashMap<>();
        this.mapDeLogradouros = new HashMap<>();
    }

    public HashMap<String, Estado> getMapDeEstados() {
        return mapDeEstados;
    }

    public HashMap<String, Logradouro> getMapDeLogradouros() {
        return mapDeLogradouros;
    }
    
    public void cadastraEstado(Estado estado){
        this.mapDeEstados.put(estado.getSiglaEstado(), estado);
    }
}

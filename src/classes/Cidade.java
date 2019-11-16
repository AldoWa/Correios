/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import java.util.HashMap;
import java.util.Objects;
/**
 *
 * @author Rodrigo
 */
public class Cidade {
    // ATRIBUTOS
    private String nomeCidade;
    private Estado estado;
    private HashMap<String,Bairro> bairros;
    
    // CONSTRUTOR
    public Cidade(String nomeCidade, Estado estado) {
        this.nomeCidade = nomeCidade;
        this.estado = estado;
        this.bairros = new HashMap();
    }
    
    // GETTERS E SETTERS
    public String getNomeCidade() {
        return nomeCidade;
    }
    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public HashMap<String, Bairro> getBairros() {
        return bairros;
    }
    public void setBairros(HashMap<String, Bairro> bairros) {
        this.bairros = bairros;
    }

    // MÉTODOS
    @Override
    public String toString() {
        return this.nomeCidade;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.nomeCidade, other.nomeCidade)) {
            return false;
        }
        return true;
    }
}
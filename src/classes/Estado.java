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
public class Estado {
    // ATRIBUTOS
    private String nome;
    private String siglaEstado;
    private HashMap<String, Cidade> cidades;

    // CONSTRUTOR
    public Estado(String siglaEstado, String nome) {
        this.siglaEstado = siglaEstado;
        this.nome = nome;
        this.cidades = new HashMap();
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public HashMap<String, Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(HashMap<String, Cidade> cidades) {
        this.cidades = cidades;
    }

    @Override
    public String toString() {
        return this.nome;
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
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.siglaEstado, other.siglaEstado)) {
            return false;
        }
        return true;
    }    
}
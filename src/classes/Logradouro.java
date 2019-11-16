/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
/**
 *
 * @author Rodrigo
 */
public class Logradouro {
    // ATRIBUTOS
    private String cep;
    private Cidade cidade;
    private Estado estado;
    private Bairro bairro;
    private String nomeLogradouro;
    
    // CONSTRUTOR
    public Logradouro(String cep, Cidade cidade, Estado estado, Bairro bairro, String nomeLogradouro) {
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.nomeLogradouro = nomeLogradouro;
    }
    
    // GETTERS E SETTERS
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public Cidade getCidade() {
        return cidade;
    }
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public Bairro getBairro() {
        return bairro;
    }
    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    public String getNomeLogradouro() {
        return nomeLogradouro;
    }
    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    // MÉTODOS
    @Override
    public String toString() {
        return this.nomeLogradouro;
    }
    
}

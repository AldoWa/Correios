/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;
import java.util.HashMap;
/**
 *
 * @author Rodrigo
 */
public class Bairro {
    // ATRIBUTOS
    private String nomeBairro;
    private HashMap<String,Logradouro> logradouros;
    
    // CONSTRUTOR
    public Bairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
        this.logradouros = new HashMap();
    }
    
    // GETTER E SETTERS
    public String getNomeBairro() {
        return nomeBairro;
    }
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }
    public HashMap<String, Logradouro> getLogradouros() {
        return logradouros;
    }
    public void setLogradouros(HashMap<String, Logradouro> logradouros) {
        this.logradouros = logradouros;
    }
}

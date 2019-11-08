/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Aldo
 */
public class Correios {

     public static void Ler(String caminho){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho));
            while(bufferedReader.ready()){
                String linha = bufferedReader.readLine();
                System.out.println(linha);
            }
            bufferedReader.close();
        }catch(IOException  ex){
            System.out.println(ex.getMessage());
        }
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

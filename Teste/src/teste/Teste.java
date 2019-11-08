/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Aldo
 */
public class Teste {

   
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File newFile = new File("C:\\Users\\Aldo\\Documents\\Fixo");
        String[] nomedosarquivos = newFile.list();
        System.out.println(nomedosarquivos.length);
        ArrayList bairros = new ArrayList<>();
        for (int i = 0; i < nomedosarquivos.length; i++) {
            if(nomedosarquivos[i].contains("BAIRROS.TXT")){
                bairros.add(nomedosarquivos[i]);
            }
            
        }
        System.out.println(bairros);
        BufferedReader bf = new BufferedReader(new FileReader(newFile.getAbsolutePath()+"\\"+ bairros.get(0)));
        
        String linha = bf.readLine() ;
        int contador = 0;
        while(!linha.equals("#")){
           
          
            System.out.println(linha.substring(1, 3));
            contador++;
            linha = bf.readLine();
            
        }
        bf.close();
       
    }
    
}

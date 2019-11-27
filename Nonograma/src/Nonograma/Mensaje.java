/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nonograma;

import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class Mensaje {
    public String cabeza;
    public ArrayList<String> argumentos;
    
    public Mensaje(String cabeza, ArrayList<String> argumentos){
        cabeza= cabeza;
        /*cabeza?*/
    }
    
    public Mensaje(String linea){
        int indice = linea.indexOf('&');
        cabeza = linea.substring(0,indice);
        int siguiente = linea.indexOf('|');
        argumentos = new ArrayList(); 
     
    while (siguiente != -1){
        indice = siguiente;
        siguiente = linea.indexOf('|', indice);
        argumentos.add(linea.substring(indice, siguiente));
        
   }
        String arg= linea.substring(siguiente, linea.length());
        argumentos.add(arg);
        
    }
    
    public String toString(){
        
        String resultado= cabeza +'&'; 
        for ( int i=0; i<argumentos.size() -1; ++i){
            resultado += argumentos.get(i) + '|';          
        }  
        if ( !argumentos.isEmpty())
            resultado += argumentos.get(argumentos.size() -1) ;
     
        return resultado;
    }
    
    
       

    
  
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornonograma;

import Nonograma.Nonograma;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Pablo
 */
public class NonogramaAlmacenado {
    static int numAlmacenados=0;
    String nombre;
    int numFilas;
    int numColumnas;
    String dificultad;
    String archivo;
    int id;
    int intentosCasilla;
    int intentosTablero;
    
    public NonogramaAlmacenado(String s){
        id=numAlmacenados;
        numAlmacenados++;
        
        
        Scanner scanner= new Scanner(s);
        scanner.useDelimiter(",");
        nombre= scanner.nextLine();
        numFilas=scanner.nextInt();
        numColumnas=scanner.nextInt();
        dificultad= scanner.nextLine();
        archivo =  scanner.nextLine();
        intentosCasilla=scanner.nextInt();
        intentosTablero=scanner.nextInt();
        
        
        
    }
    
    public void NonogramaAlmacenadoAnt(String s){
        id=numAlmacenados;
        numAlmacenados++;
        
        
        Scanner scanner= new Scanner(s);
        //scanner.setDelimiter(",");
        int delim=s.indexOf(',')+1;
        nombre= s.substring(0,s.indexOf(','));
        s.indexOf(',');
        String stringFilas=s.substring(delim,s.indexOf(',',delim));
        numFilas=Integer.parseInt(stringFilas);
        delim=s.indexOf(',',delim)+1;
        String stringColumnas=s.substring(delim,s.indexOf(',',delim));
        numColumnas=Integer.parseInt(stringColumnas);
        delim=s.indexOf(',',delim)+1;
        dificultad= s.substring(delim,s.indexOf(',',delim));
        delim=s.indexOf(',',delim)+1;
        archivo =  s.substring(delim,s.length());
        delim=s.indexOf(',',delim)+1;
        intentosCasilla=Integer.parseInt(stringColumnas);
        delim=s.indexOf(',',delim)+1;
        
        
        
        
    }
    
    public String presentacionParaCliente(){
        String resultado=new String();
        resultado="Identificador: "+id+" Nombre: "+nombre+
                " Dimension: "+numFilas+"x"+numColumnas+
                " dificultad"+dificultad+
                "Intentos jugando por casillas: "+intentosCasilla+
                "Intentos jugando por tablero: "+intentosTablero;
        
        return resultado;
    }
    public Nonograma leer() throws IOException{
        BufferedReader lector=Files.newBufferedReader(Paths.get(archivo));
            return Nonograma.leerDeBufferedReader(lector);
            
        

    
    }
    
    
    
}

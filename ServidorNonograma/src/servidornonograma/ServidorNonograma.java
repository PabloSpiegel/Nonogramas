/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornonograma;

import Nonograma.Nonograma;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author Pablo
 */

public class ServidorNonograma {

    /**
     * @param args the command line arguments
     */
    static String archivoPrincipal="lista.lng";
    static ArrayList<NonogramaAlmacenado> listaNonogramas=null;
    public static void leerLista(String archivo){
        listaNonogramas = new ArrayList();
        try(BufferedReader lector=Files.newBufferedReader(Paths.get(archivo))){
            String linea;
            while((linea=lector.readLine())!=null&& linea.length()>2){
                NonogramaAlmacenado nuevo= new NonogramaAlmacenado(linea);
                listaNonogramas.add(nuevo);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        
    }
    public static void main(String[] args) throws IOException {
        
      
      leerLista(archivoPrincipal);
     Nonograma leido=listaNonogramas.get(0).leer();
      
      
        
    }
    
}

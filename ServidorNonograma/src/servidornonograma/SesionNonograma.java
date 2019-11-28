/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornonograma;

import Nonograma.Mensaje;
import Nonograma.Nonograma;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author Pablo
 */

public class SesionNonograma {

    /**
     * @param args the command line arguments
     */
     String archivoPrincipal="lista.lng";
     ArrayList<NonogramaAlmacenado> listaNonogramas=null;
     InterfazConCliente interfaz;

    SesionNonograma(Socket socketServicio) throws IOException {
        interfaz=new InterfazConCliente(socketServicio);
    }

    private void leerLista(String archivo){
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
    
    private ArrayList<String> listarDificultades(){
        ArrayList<String> dificultades=new ArrayList();
        for(NonogramaAlmacenado nono: listaNonogramas){
            if(!dificultades.contains(nono.dificultad)){
                dificultades.add(nono.dificultad());
            }
            
        }
        return listar
    }
    public void empezarSesion() throws IOException {
        
      
      leerLista(archivoPrincipal);
      Mensaje mensajeHola = interfaz.esperarMensaje();
      if(mensajeHola.cabeza.compareTo("Hola")!=0);
      
      
      
      
        
    }
    
}

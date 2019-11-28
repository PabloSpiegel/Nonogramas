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

public class SesionServidor {

    /**
     * @param args the command line arguments
     */
     String archivoPrincipal="lista.lng";
     ArrayList<NonogramaAlmacenado> listaNonogramas=null;
     ArrayList<String> dificultades=null;
     InterfazConCliente interfaz;

    SesionServidor(Socket socketServicio) throws IOException {
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
                dificultades.add(nono.dificultad);
            }
            
        }
        return dificultades;
    }
    
    private ArrayList<String> listar(String dificultad){
        boolean filtrar=!dificultad.isEmpty(); 
        ArrayList<String> lista=new ArrayList();
        for(NonogramaAlmacenado nono: listaNonogramas){
             if(filtrar&& nono.dificultad.compareTo(dificultad)==0){
                     lista.add(nono.presentacionParaCliente());
                 
             }
             if(!filtrar){
                 lista.add(nono.presentacionParaCliente());
             }
         }
        return lista;
    }
    public void empezarSesion() throws IOException {
        
      
      leerLista(archivoPrincipal);
      dificultades=listarDificultades();
      Mensaje mensajeHola = interfaz.esperarMensaje();
      
        
      if(mensajeHola.cabeza.compareTo("Hola")!=0){
          System.out.println("error en el mensaje, se esperaba hola");
          
      interfaz.enviarRespuesta("<rror&");
      }
      interfaz.enviarRespuesta("Buenas&");
      
      boolean seguir=true;
      while(seguir){
          Mensaje mensaje=interfaz.esperarMensaje(), respuesta=new Mensaje();
          switch(mensaje.cabeza){
              case "VerDificultades":
                  respuesta=new Mensaje("ListaDificultades",dificultades);
                  break;
              
              case "Listar":
                  if(mensaje.argumentos.isEmpty()){
                     mensaje.argumentos.add("");
                  }
                  respuesta.cabeza="ListaDeNonogramas";
                  respuesta.argumentos=listar(mensaje.argumentos.get(0));
                  
                  break;
              case "Salir":
                  respuesta=new Mensaje("Adios&");
                  seguir=false;
                  break;
              default:
                  respuesta=new Mensaje("Error&argumentos no esperados");
                  
          }
          
          interfaz.enviarRespuesta(respuesta);
      
      
      
      }
      
      
      
        
    }
    
}

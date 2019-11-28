package servidornonograma;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Nonograma.Mensaje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;

/**
 *
 * @author Pablo
 */

public class InterfazConCliente {
    Socket socketServicio;
    
    BufferedReader lector;
    PrintWriter escritor;
    
    public InterfazConCliente(Socket socket) throws IOException{
    socketServicio=socket;
    lector = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
    escritor = new PrintWriter(socketServicio.getOutputStream(),true);
    
    }
    public void enviarRespuesta(Mensaje respuesta){
       escritor.write(respuesta.toString());
    }
    public void enviarRespuesta(String linea){
        escritor.write(linea);
    }
    public Mensaje esperarMensaje() throws IOException {
       String linea = lector.readLine();
        
        return new Mensaje(linea);
    }
    public Mensaje enviarMensaje(String linea) throws IOException{
        String respuesta=null;
        escritor.write(linea);
        respuesta = lector.readLine();
        
        return new Mensaje(respuesta);
    }
    
}

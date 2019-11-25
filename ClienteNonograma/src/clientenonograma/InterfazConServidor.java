/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenonograma;

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

public class InterfazConServidor {
    Socket socketServicio;
    
    BufferedReader lector;
    PrintWriter escritor;
    
    public InterfazConServidor(Socket socket) throws IOException{
    socketServicio=socket;
    lector = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
    escritor = new PrintWriter(socketServicio.getOutputStream(),true);
    
    }
    public Mensaje enviarMensaje(Mensaje comando) throws IOException{
        String respuesta=null;
        escritor.write(comando.toString()+'\n');
        respuesta = lector.readLine();
        
        return new Mensaje(respuesta);
    }
    public Mensaje enviarMensaje(String linea) throws IOException{
        String respuesta=null;
        escritor.write(linea);
        respuesta = lector.readLine();
        
        return new Mensaje(respuesta);
    }
    
}

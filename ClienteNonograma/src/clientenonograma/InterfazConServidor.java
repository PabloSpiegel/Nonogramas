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
        
        
        return enviarMensaje(comando.toString());
    }
    public Mensaje enviarMensaje(String linea) throws IOException{
        String respuesta=null;
        escritor.print(linea+'\n');
        escritor.flush();
        System.out.println("se entra en enviar mensaje con el mensaje: "+linea);
        //escritor.flush();
        respuesta = lector.readLine();
        System.out.println();
        return new Mensaje(respuesta);
    }
    
}

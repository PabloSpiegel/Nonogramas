/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenonograma;

import Nonograma.Casilla;
import Nonograma.Nonograma;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class ClienteNonograma {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // prueba para github
        //comentario puesto desde github
        PrintWriter outPrinter = null;
        BufferedReader inReader = null;
        
        // Nombre del host donde se ejecuta el servidor:
        String host="localhost";
	// Puerto en el que espera el servidor:
	int port=8989;
		
	// Socket para la conexión TCP
	Socket socketServicio=null;
        
        InterfazConServidor interfaz= null;
        try {
			// Creamos un socket que se conecte a "hist" y "port":
			//////////////////////////////////////////////////////
			// socketServicio= ... (Completar)
			//////////////////////////////////////////////////////			
			socketServicio= new Socket (host, port);
			//InputStream
                        
                        interfaz = new InterfazConServidor(socketServicio);
                        
                        interfaz.enviarMensaje()
                        
			/*for(int i=0;i<bytesLeidos;i++){
				System.out.print((char)buferRecepcion[i]);
			}*/
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			// ... close(); (Completar)
			//////////////////////////////////////////////////////
			socketServicio.close();
			// Excepciones>
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
                        System.err.println("Error concreto: "+e);
		}
	}
    }
    

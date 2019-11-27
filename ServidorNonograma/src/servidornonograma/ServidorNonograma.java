/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornonograma;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pablo
 */
public class ServidorNonograma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port=8989;
        ServerSocket socketServidor;
        
        try{
            
            socketServidor = new ServerSocket(port);
            do {
				
				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				// socketServicio=... (completar)
				//////////////////////////////////////////////////
                                Socket socketServicio = null;
                                try {
                                    socketServicio = socketServidor.accept();
                                } catch (IOException e) {
                                    System.out.println("Error:   no   se   pudo   aceptar   la conexión solicitada");
                                }
                                
				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				SesionNonograma sesion=new SesionNonograma(socketServicio);
				sesion.empezarSesion();
				
			} while (true);
        }catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
                        
		}
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenonograma;

import Nonograma.Casilla;
import Nonograma.Mensaje;
import Nonograma.Nonograma;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Pablo
 */
public class ClienteNonogramaMenu {

    /**
     * @param args the command line arguments
     */
    static Scanner scanner;
    static InterfazConServidor interfaz;
    
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
        
        System.out.println("iniciamos la conexion con el servidor");
        try {
			// Creamos un socket que se conecte a "hist" y "port":
			//////////////////////////////////////////////////////
			// socketServicio= ... (Completar)
			//////////////////////////////////////////////////////			
			socketServicio= new Socket (host, port);
			//InputStream
                        interfaz = new InterfazConServidor(socketServicio);
                        
                        System.out.println("Conexión con el servidor aceptada");
                        
                        Mensaje comando= new Mensaje("Hola",new ArrayList());
                        Mensaje respuesta = interfaz.enviarMensaje(comando);
                        System.out.println("Respuesta: del servidor: "+respuesta.cabeza);
                        Scanner scanner=new Scanner(System.in);
                        
                        boolean seguir = true;
                        BucleMenu();
                        
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
    
    public static boolean BucleMenu( ){
        
        boolean seguir= true;
        boolean jugar = false;
        
    while(seguir){
        
        ArrayList lista= new ArrayList();
        
        lista.add(" Ver dificultades ");
        lista.add(" Listar por dificultad ");
        lista.add(" Listar ");
        lista.add(" Jugar ");
        lista.add(" Salir");
        
        
      int opcion =Menu(lista);
      
        switch (opcion) {
            case 0: 
                verDificultades();
                
                break;
            case 1:
                listarPorDificultades();
                break;
            case 2:
                System.out.println(" Listar");
                break;
            
             case 3:
                System.out.println(" Jugar");
                break;
                
             case 4:
                System.out.println("Salir");
                break;
            default:
                throw new AssertionError();
        }
    }
 
    
    return jugar;
    }
    
    /* scanner.hasNextInt*/ 
    public static int Menu( ArrayList <String> argumento){
       
        int opcion=-1;
        boolean leer= false;
        
        for(int i=0; i<argumento.size();i++){
            System.out.println( i+ ": " + argumento.get(i));
        }
        
        System.out.println("Elige una opcion: ");
        
        if (leer){
        while(scanner.hasNextInt()){
            scanner.next();
                     opcion= scanner.nextInt();
          
        }
        

         return opcion;
        }
    }

    private static void verDificultades() throws IOException {
        Mensaje mensaje = new Mensaje("VerDificultades",new ArrayList());
        Mensaje respuesta = interfaz.enviarMensaje(mensaje);
        ArrayList<String> dificultades = respuesta.argumentos;
        Menu(dificultades);
        
    }

    private static void listarPorDificultades() {
       Mensaje comando= new Mensaje("Listar",dificultad);
    }


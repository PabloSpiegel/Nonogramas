/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenonograma;

import Nonograma.Casilla;
import Nonograma.Nonograma;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class PruebaNonograma {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // prueba para github
        //comentario puesto desde github
        ArrayList<ArrayList<Casilla>> prueba=new ArrayList();
        ArrayList<Casilla> p=new ArrayList();
        p.add(Casilla.PINTADA);
        p.add(Casilla.PINTADA);
        p.add(Casilla.PINTADA);
        p.add(Casilla.VACIA);
        p.add(Casilla.VACIA);
        p.add(Casilla.PINTADA);
        p.add(Casilla.PINTADA);
        p.add(Casilla.VACIA);
        p.add(Casilla.VACIA);
        prueba.add((ArrayList<Casilla>)p.clone());
                
        prueba.add((ArrayList<Casilla>)p.clone());
                p.set(1,Casilla.VACIA);
        prueba.add((ArrayList<Casilla>)p.clone());
                p.set(1,Casilla.VACIA);
        prueba.add((ArrayList<Casilla>)p.clone());
                p.set(1,Casilla.VACIA);
        prueba.add((ArrayList<Casilla>)p.clone());
        
        Nonograma n=new Nonograma(prueba);
        System.out.println(n.stringTablero());
        System.out.println(n.PistasVerticales);
        System.out.println(n.PistasHorizontales);
        
        //comentario puesto después de clonar
    }
    
}

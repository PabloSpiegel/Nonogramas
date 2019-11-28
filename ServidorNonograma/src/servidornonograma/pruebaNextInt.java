/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidornonograma;

import java.util.Scanner;

/**
 *
 * @author Pablo
 */
public class pruebaNextInt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s=new Scanner("id: 3");
        s.skip("id:");
        System.out.println(s.nextInt());
    }
    
}

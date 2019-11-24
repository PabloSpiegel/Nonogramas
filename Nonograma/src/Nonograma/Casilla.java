/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nonograma;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pablo
 */
public enum Casilla {
    VACIA,
    PINTADA,
    TACHADA;
    public static Casilla fromInt(int i){
        switch(i){
            case 0:
                return Casilla.VACIA;
            case 1:
                return Casilla.PINTADA;
            case 2:
                return Casilla.TACHADA;
        }
        return Casilla.VACIA;
    }
    }
            

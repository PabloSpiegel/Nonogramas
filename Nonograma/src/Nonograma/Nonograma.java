/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nonograma;

import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class Nonograma {
     int numFilas;
     int numColumnas;
    public final ArrayList<ArrayList<Integer>> PistasHorizontales;
    public final ArrayList<ArrayList<Integer>> PistasVerticales;
    ArrayList<ArrayList<Casilla>> tablero;
    
    public Nonograma(ArrayList<ArrayList<Casilla>> tablero){
        
        this.tablero=tablero;
        numFilas=tablero.size();
        numColumnas=tablero.get(0).size();
        ArrayList<ArrayList<Integer>> pistasV=new ArrayList();
        ArrayList<ArrayList<Integer>> pistasH=new ArrayList();
        //Calculamos las pistas de las filas
        for(int i=0; i<numFilas;++i){
           ArrayList<Integer> pistas=new ArrayList();
           Integer j=0;
           
                while(j<numColumnas){
                    j=contarGrupoHorizontal(i,j,pistas);
                    
                }
            if(pistas.isEmpty()){
                pistas.add(0);
            }
            
            pistasH.add(pistas);
           }
        PistasHorizontales=pistasH;
        
        //Calculamos las pistas de las columas
        for(int j=0; j<numColumnas;++j){
           ArrayList<Integer> pistas=new ArrayList();
           Integer i=0;
           
                while(i<numFilas){
                    i=contarGrupoVertical(i,j,pistas);
                    
                }
            if(pistas.isEmpty()){
                pistas.add(0);
            }
            
            pistasV.add(pistas);
           }
           
           
        
        PistasVerticales=pistasV;
        
        
    }
    
    private int contarGrupoHorizontal(Integer i, Integer j, ArrayList<Integer> pistas){
        boolean hayGrupo=false, terminado=false;
        
        for(;j<numColumnas && !hayGrupo;++j){
            if(getCasilla(i,j)==Casilla.PINTADA){
                hayGrupo=true;
            }
        }
        if(!hayGrupo){
            return j;
        }
        int numPintadas=1;
        for(;j<numColumnas && !terminado;++j){
            if(getCasilla(i,j)==Casilla.PINTADA){
                numPintadas++;
                
            }
            else{
                terminado=true;
            }
        }
        pistas.add(numPintadas);
        return j;
        
    }
    
    private int contarGrupoVertical(Integer i, Integer j, ArrayList<Integer> pistas){
        boolean hayGrupo=false, terminado=false;
        
        for(;i<numFilas && !hayGrupo;++i){
            if(getCasilla(i,j)==Casilla.PINTADA){
                hayGrupo=true;
            }
        }
        if(!hayGrupo){
            return i;
        }
        int numPintadas=1;
        for(;i<numFilas && !terminado;++i){
            if(getCasilla(i,j)==Casilla.PINTADA){
                numPintadas++;
                
            }
            else{
                terminado=true;
            }
        }
        pistas.add(numPintadas);
        return i;
        
    }
    
    public Casilla getCasilla(int x, int y){
        return tablero.get(x).get(y);
    }
    
    public void setCasilla(int x, int y, Casilla c){
        tablero.get(x).set(y,c);
    }
    
    public int getNumFilas() {
        return numFilas;
    }

    public int getNumColumnas() {
        return numColumnas;
    }
    
    public String stringTablero(){
        String res= "[";
        for(int i=0; i<numFilas; ++i){
        res+= tablero.get(i).toString();
        res+="\n";
        }
        res+="]";
        return res;
    }
    
    public void vaciarTablero(){
        for(int i=0; i<numFilas;++i){
            ArrayList<Casilla> c=tablero.get(i);
            for(int j=0; j<numColumnas;++j){
                c.set(j, Casilla.VACIA);
                
            }
        }
    }
    
    
    
    
    
    
    
}

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
     int NumFilas;
     int NumColumnas;
    public final ArrayList<ArrayList<Integer>> PistasHorizontales;
    public final ArrayList<ArrayList<Integer>> PistasVerticales;
    ArrayList<ArrayList<Casilla>> Tablero;
    
    public Nonograma(ArrayList<ArrayList<Casilla>> tablero){
        
        Tablero=tablero;
        NumFilas=tablero.size();
        NumColumnas=tablero.get(0).size();
        ArrayList<ArrayList<Integer>> pistasV=new ArrayList();
        ArrayList<ArrayList<Integer>> pistasH=new ArrayList();
        //Calculamos las pistas de las filas
        for(int i=0; i<NumFilas;++i){
           ArrayList<Integer> pistas=new ArrayList();
           Integer j=0;
           
                while(j<NumColumnas){
                    j=contarGrupoHorizontal(i,j,pistas);
                    
                }
            if(pistas.isEmpty()){
                pistas.add(0);
            }
            
            pistasH.add(pistas);
           }
        PistasHorizontales=pistasH;
        
        //Calculamos las pistas de las columas
        for(int j=0; j<NumColumnas;++j){
           ArrayList<Integer> pistas=new ArrayList();
           Integer i=0;
           
                while(i<NumFilas){
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
        
        for(;j<NumColumnas && !hayGrupo;++j){
            if(getCasilla(i,j)==Casilla.PINTADA){
                hayGrupo=true;
            }
        }
        if(!hayGrupo){
            return j;
        }
        int numPintadas=1;
        for(;j<NumColumnas && !terminado;++j){
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
        
        for(;i<NumFilas && !hayGrupo;++i){
            if(getCasilla(i,j)==Casilla.PINTADA){
                hayGrupo=true;
            }
        }
        if(!hayGrupo){
            return i;
        }
        int numPintadas=1;
        for(;i<NumFilas && !terminado;++i){
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
        return Tablero.get(x).get(y);
    }
    
    public String stringTablero(){
        String res= "[";
        for(int i=0; i<NumFilas; ++i){
        res+= Tablero.get(i).toString();
        res+="\n";
        }
        res+="]";
        return res;
    }
    
    
    
    
    
    
    
}

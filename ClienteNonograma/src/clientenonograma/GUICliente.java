/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenonograma;

import Nonograma.Casilla;
import Nonograma.Nonograma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class GUICliente implements ActionListener, MouseListener{
    private JFrame screen = null;
    private JPanel composite = new JPanel();
    int filasGUI;
    int columnasGUI;
    Nonograma nono;
    private final int tamBotones;
    private final Dimension dimBotones;
    private final Color colorZonaVacia;
    private final Color colorPistas1;
    private final Color colorPistas2;
    
    
    public GUICliente(Nonograma nono, String nombre) {
        this.nono=nono;
        
        
        tamBotones =60;
        dimBotones=new Dimension(tamBotones,tamBotones);
        colorZonaVacia=Color.GRAY.darker().darker();
        colorPistas1=Color.GRAY;
        colorPistas2=colorPistas1.brighter();
        
        screen = new JFrame("Cliente Nonogramas. Resolviendo" + nombre);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);
        screen.setResizable(false);
        
        composite.setLayout(new BorderLayout());
        configurarBotones();
        screen.add(composite);
        screen.pack();
    
    }
    ArrayList<ArrayList<JButton>> botonesJuego= null;
    ArrayList<ArrayList<JButton>> botonesTablero= null;
    private JPanel tableroGUI=null;
    
    
    private JButton botonVacio(){
        JButton boton= new JButton("");
        boton.setBackground(Color.GRAY);
        boton.setPreferredSize(dimBotones);
        
        return boton;
    }
    

    private void configurarBotonesAnt() {
        botonesJuego = new ArrayList<ArrayList<JButton>>();
        
        if (tableroGUI != null){
            composite.remove(tableroGUI);
            tableroGUI = null;
        
        }
        
        
        tableroGUI = new JPanel();
        tableroGUI.setLayout(new GridLayout(nono.getNumFilas(),nono.getNumColumnas()));
        for(int i=0; i<nono.getNumFilas();++i){
            
            ArrayList<JButton> arrayFilas= new ArrayList();
            for(int j=0; j<nono.getNumColumnas();++j){
                arrayFilas.add(new JButton(""));
                arrayFilas.get(j).setBackground(Color.WHITE);
                arrayFilas.get(j).setPreferredSize(dimBotones);
                arrayFilas.get(j).addActionListener(this);
                arrayFilas.get(j).addMouseListener(this);
                tableroGUI.add(arrayFilas.get(j));

            }
            botonesJuego.add(arrayFilas);
            
        tableroGUI.setVisible(true);
            composite.add(tableroGUI);
        }
       
    }
    
    private boolean estaEnZonaVacia(int x, int y, int ancho, int alto){
        boolean resultado=(x< (alto )) && (y < (ancho));
        return resultado;
        
    }
    
    private boolean estaEnZonaPistas(int x, int y, int ancho, int alto){
        boolean resultado=((x< (alto )) || (y < (ancho))) && (!estaEnZonaVacia(x, y, ancho, alto)); 
        return resultado;
        
    }
    
    private boolean estaEnZonaJuego(int x, int y, int ancho, int alto){
        boolean resultado=(x>= (alto )) && (y >= (ancho));
        return resultado;
    }
    
    private Color colorPistas(int x,int y, int anchoExtra, int altoExtra){
        if(y> anchoExtra-1 ){
            return ( (y%2==0)? colorPistas1:colorPistas2);
        }
        return ( (x%2==0)? colorPistas1:colorPistas2);
}
    private void configurarBotones(){
       botonesJuego = new ArrayList<ArrayList<JButton>>();
        if (tableroGUI != null){
            composite.remove(tableroGUI);
            tableroGUI = null;
        
        }
        
        int anchoExtra=0;
        for(int i=0; i<nono.getPistasHorizontales().size();++i){
            int ancho=nono.getPistasHorizontales().get(i).size();
            if (ancho > anchoExtra){
                anchoExtra = ancho;
            }
        }
        int altoExtra=0;
        for(int i=0; i<nono.getPistasVerticales().size();++i){
            int alto=nono.getPistasVerticales().get(i).size();
            if (alto > altoExtra){
                altoExtra = alto;
            }
        }
        
        
        
        filasGUI=altoExtra+nono.getNumFilas();
        columnasGUI=anchoExtra+nono.getNumColumnas();
        tableroGUI = new JPanel();
        tableroGUI.setLayout(new GridLayout(filasGUI,columnasGUI));
        
        int k=0;
        
         botonesJuego = new ArrayList<ArrayList<JButton>>();
         for(int i=0; i<nono.getNumFilas();++i){
             ArrayList<JButton> arrayFilas= new ArrayList();
             for(int j=0; j<nono.getNumColumnas();++j){
                 arrayFilas.add(null);
             }
             botonesJuego.add(arrayFilas);
         }
         botonesTablero = new ArrayList<ArrayList<JButton>>();
        for(int i=0; i<filasGUI;++i){
            
            ArrayList<JButton> arrayFilas= new ArrayList();
            for(int j=0; j<columnasGUI;++j){
                arrayFilas.add(new JButton(""));
                arrayFilas.get(j).setPreferredSize(dimBotones);
                if(estaEnZonaVacia(i,j,anchoExtra,altoExtra)){
                   arrayFilas.get(j).setBackground(Color.GRAY.darker()); 
                   arrayFilas.get(j).setBorder(null);
                }
                if(estaEnZonaPistas(i,j,anchoExtra,altoExtra)){
                    Color colorElegido=colorPistas(i,j,anchoExtra,altoExtra);
                    arrayFilas.get(j).setBackground(colorElegido);
                   arrayFilas.get(j).setText("");
                   //arrayFilas.get(j).setFont(new Font("Arial", Font.PLAIN, 13));
                   arrayFilas.get(j).setBorder(null);
                   
                }
                if(estaEnZonaJuego(i,j,anchoExtra,altoExtra)){
                   arrayFilas.get(j).setBackground(Color.WHITE);
                    arrayFilas.get(j).addActionListener(this);
                    arrayFilas.get(j).addMouseListener(this);
                    botonesJuego.get(i-altoExtra).set(j-anchoExtra,arrayFilas.get(j));
                    //    arrayFilas.get(j).setFont(new Font("Dialog", Font.BOLD, 10));
                        arrayFilas.get(j).setMargin(new Insets(0, 0, 0, 0));
                        
                    botonesJuego.get(i-altoExtra).set(j-anchoExtra,arrayFilas.get(j));  
                    
                    //arrayFilas.get(j).setFont(new Font("Dialog", Font.PLAIN, 10));
                }
                tableroGUI.add(arrayFilas.get(j));

            }
            botonesTablero.add(arrayFilas);
        
            
            
        }
        
        ponerPistasHorizontales(altoExtra,anchoExtra);
        //botonesTablero.get(altoExtra).get(1).setText("a");
        ponerPistasVerticales(altoExtra,anchoExtra);
        
        tableroGUI.setVisible(true);
        composite.add(tableroGUI);
        Font f=botonesJuego.get(0).get(0).getFont();
        int a;
        
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        for (int i=0; i<nono.getNumFilas();++i){
            for(int j=0; j<nono.getNumColumnas();++j){
                JButton boton=getBoton(i,j);
                if (boton== me.getSource()){
                    
                    
                    Casilla antCasilla=nono.getCasilla(i,j),nuevaCasilla=null;
                    //Determinamos el nuevo valor de la casilla para click izquierdo
                    if(SwingUtilities.isLeftMouseButton(me)){
                        switch (antCasilla) {
                            case VACIA:
                                nuevaCasilla=Casilla.PINTADA;
                                break;
                            case PINTADA:
                                nuevaCasilla=Casilla.VACIA;
                                break;
                            case TACHADA:
                                nuevaCasilla=Casilla.PINTADA;
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                    
                    //Determinamos el nuevo valor de la casilla para click derecho
                    if(SwingUtilities.isRightMouseButton(me)){
                        switch (antCasilla) {
                            case VACIA:
                                nuevaCasilla=Casilla.TACHADA;
                                break;
                            case PINTADA:
                                nuevaCasilla=Casilla.TACHADA;
                                break;
                            case TACHADA:
                                nuevaCasilla=Casilla.VACIA;
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                    
                    //Cambiamos el botón y la casilla
                    
                    if(!(SwingUtilities.isMiddleMouseButton(me))){
                      
                    cambiarEstadoBotonYCasilla(i,j,nuevaCasilla);  
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private JButton getBoton(int x, int y){
        return botonesJuego.get(x).get(y);
    }
    
    private void cambiarEstadoBotonYCasilla(int x, int y, Casilla casilla){
        JButton boton =getBoton(x,y);
        //Deshacemos la acción anterior
        switch (nono.getCasilla(x, y)) {
            case PINTADA:
                boton.setBackground(Color.WHITE);
                break;
            case VACIA:
                
                break;
            case TACHADA:
                boton.setText("");
                break;
            default:
                throw new AssertionError();
        }
        
        //Hacemos la nueva accion
        nono.setCasilla(x,y,casilla);
        
        switch (nono.getCasilla(x, y)) {
            case PINTADA:
                boton.setBackground(Color.BLACK);
                break;
            case VACIA:
                
                break;
            case TACHADA:
                boton.setText("X");
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }

    private void ponerPistasHorizontales(int altoExtra, int anchoExtra) {
        for(int i=altoExtra;i<filasGUI;++i){
            int inicio=anchoExtra-nono.getPistasHorizontales().get(i-altoExtra).size();
            for(int j=inicio;j<anchoExtra;++j){
                int numero=nono.getPistasHorizontales().get(i-altoExtra).get(j-inicio);
                String texto=Integer.toString(numero);
                botonesTablero.get(i).get(j).setText(texto);
            }
            
        }
    }
    
        private void ponerPistasVerticales(int altoExtra, int anchoExtra) {
            for(int j=anchoExtra; j<columnasGUI;++j){
                int inicio=altoExtra-nono.getPistasVerticales().get(j-anchoExtra).size();
                for(int i=inicio;i<altoExtra;++i){
                    
                int numero=nono.getPistasVerticales().get(j-anchoExtra).get(i-inicio);
                String texto=Integer.toString(numero);
                botonesTablero.get(i).get(j).setText(texto);
                }
            }
            
            
            
            
            
            /*for(int i=altoExtra;i<filasGUI;++i){
            int inicio=anchoExtra-nono.getPistasHorizontales().get(i-altoExtra).size();
            for(int j=inicio;j<anchoExtra;++j){
                int numero=nono.getPistasHorizontales().get(i-altoExtra).get(j-inicio);
                String texto=Integer.toString(numero);
                botonesTablero.get(i).get(j).setText(texto);
            }
            
        }*/
    }
    
    
    
    
}

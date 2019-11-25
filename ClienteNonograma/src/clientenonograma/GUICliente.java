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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
    Nonograma nono;
    private final int dimBotones = 64;

    
    
    public GUICliente(Nonograma nono, String nombre) {
        this.nono=nono;
        screen = new JFrame("Cliente Nonogramas. Resolviendo" + nombre);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);
        screen.setResizable(false);
        
        composite.setLayout(new BorderLayout());
        configurarBotones();
        screen.add(composite);
        screen.pack();
    
    }
    ArrayList<ArrayList<JButton>> botonesTablero= null;
    private JPanel tableroGUI=null;
    
    
    

    private void configurarBotones() {
        botonesTablero = new ArrayList<ArrayList<JButton>>();
        boolean starting = true;
        
        if (tableroGUI != null){
            composite.remove(tableroGUI);
            tableroGUI = null;
            starting = false;
        
        }
        
        
        tableroGUI = new JPanel();
        tableroGUI.setLayout(new GridLayout(nono.getNumFilas(),nono.getNumColumnas()));
        for(int i=0; i<nono.getNumFilas();++i){
            
            ArrayList<JButton> arrayFilas= new ArrayList();
            for(int j=0; j<nono.getNumColumnas();++j){
                arrayFilas.add(new JButton(""));
                arrayFilas.get(j).setBackground(Color.WHITE);
                arrayFilas.get(j).setPreferredSize(new Dimension(dimBotones,dimBotones));
                arrayFilas.get(j).addActionListener(this);
                arrayFilas.get(j).addMouseListener(this);
                tableroGUI.add(arrayFilas.get(j));

            }
            botonesTablero.add(arrayFilas);
            
        tableroGUI.setVisible(true);
            composite.add(tableroGUI);
        }
       
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
        return botonesTablero.get(x).get(y);
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
    
    
    
    
}

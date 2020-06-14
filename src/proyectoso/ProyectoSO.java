/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import Ventana.Interfaz;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Jesus Barrios
 */
public class ProyectoSO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        /*BIENVENIDO AL MAIN, para mas info de como funciona este programa
        ir primero a Control, despues de eso se convierte en ir clase
        por clase viendo los comentarios*/
        Control app = new Control();
        new Interfaz(app).setVisible(true);
        app.iniciar();
        
                
    }
    
}

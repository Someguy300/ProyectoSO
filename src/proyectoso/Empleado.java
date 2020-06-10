/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jesus Barrios
 */
public class Empleado extends Thread {
    Estante estante;
    
    Empleado(Estante estante){
        this.estante = estante;
    }
    
    @Override
    public void run(){
        do {            
            if(estante.getNroProd()<7){
                System.out.println("YOOOOOOOOOOO");
                estante.put();
            }
        } while (true);
        
        
        
        
    }

    
    
    
}

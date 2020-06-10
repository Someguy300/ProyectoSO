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
    Semaphore sem;
    
    Empleado(Estante estante){
        this.estante = estante;
        this.sem = null;
    }
    
    @Override
    public void run(){
        System.out.println("EMPLEADO ACTIVO");
        do {            
            try {
                Thread.sleep(1000);
                estante.put(this);
            } catch (InterruptedException ex) {
                System.out.println("ERROR");
            }
        } while (true);
        
        
        
    }

    public Estante getEstante() {
        return estante;
    }

    public void setEstante(Estante estante) {
        this.estante = estante;
    }

    public Semaphore getSem() {
        return sem;
    }

    public void setSem(Semaphore sem) {
        this.sem = sem;
    }

    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Jesus Barrios
 */
public class Cliente extends Thread {
    Semaphore sem;
    int id;
    Cliente(Semaphore sem, int id){
        this.sem = sem; 
        this.id = id;
    }
    
    @Override
    public void run(){
        
    }
}

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
    String id;
    Cliente(Semaphore sem, String id){
        super(id);
        this.sem = sem; 
        this.id = id;
    }
}

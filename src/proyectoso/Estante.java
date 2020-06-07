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
public class Estante {
    int productos,random;
    Semaphore usuario;
    
    public Estante(){
        this.productos = 10;
        usuario = new Semaphore(1, true);
    }
    
    
    
}

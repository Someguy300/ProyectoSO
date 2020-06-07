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
public class Control {
    Semaphore sEstante1,sEstante2,sEstante3, sClientes, sCajeros;
    Estante estante;
    
    public Control(){
        this.sEstante1 = new Semaphore(1,true);
        this.sEstante2 = null;
        this.sEstante3 = null;
        this.sClientes = new Semaphore(10,true);
        this.sCajeros = new Semaphore(1,true);
    }
    
    public void iniciar(){
        int cont = 1;
        Cliente cliente;
        do {
            cliente = new Cliente(cont);
            cliente.setSemC(sClientes);
            cliente.setSemE1(sEstante1);
            cliente.setSemP(sCajeros);
            cliente.start();
            cont++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println("ERROR INICIAR");
            }
        } while (cont<13);
    }
}

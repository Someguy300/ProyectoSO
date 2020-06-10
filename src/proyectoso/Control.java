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
    Semaphore sClientes, sCajeros;
    Estante estante1,estante2,estante3;
    
    
    public Control(){
        this.estante1 = new Estante(10);
        this.estante2 = null;
        this.estante3 = null;
        this.sClientes = new Semaphore(10,true);
        this.sCajeros = new Semaphore(4,true);
    }
    
    public void iniciar(){
        
        int cont = 1;
        Cliente cliente;
        Empleado empleado = new Empleado(estante1);
        empleado.start();
        do {
            cliente = new Cliente(cont);
            cliente.setSemC(sClientes);
            cliente.setEst1(estante1);
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

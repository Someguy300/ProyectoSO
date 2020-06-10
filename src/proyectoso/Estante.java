/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import static java.lang.Math.random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jesus Barrios
 */
public class Estante {
    
    int randomInt, randomCost;
    
    private Empleado empleado;
    
    private Cliente cliente;
    
    private int nroProd;
    
    private Semaphore semS; 
    
    public Estante(int nroProd){
        this.nroProd = nroProd;
        this.cliente = null;
        this.semS = new Semaphore(1);
    }

    

    void get(Cliente cliente) throws InterruptedException {
        if(nroProd!=0){
            semS.acquire();
            setCliente(cliente);
            System.out.println("-----------------");
            System.out.println(cliente.id+" dice: Estoy en el estante");

            
            cliente.sleep(1000);
            

            switch(nroProd){
                case 1:
                    randomInt = (int)(2 * Math.random());
                    cliente.setNroItems(cliente.getNroItems()+randomInt);
                    System.out.println(cliente.id+" dice: agarre "+randomInt+" productos");
                    nroProd = nroProd - randomInt;
                    if(randomInt!=0){
                        randomCost= 1 + ((int)(10 * Math.random()));
                        cliente.setTotal(cliente.getTotal()+randomCost);
                    }
                    break;

                default:
                    randomInt = (int)(3 * Math.random());
                    cliente.setNroItems(cliente.getNroItems()+randomInt);
                    System.out.println(cliente.id+" dice: agarre "+randomInt+" productos");
                    nroProd = nroProd - randomInt;
                    if(randomInt!=0){
                        for (int i = 0; i < randomInt; i++) {
                            randomCost= 1 + ((int)(10 * Math.random()));
                            cliente.setTotal(cliente.getTotal()+randomCost);
                        }
                    }
                    break;
            }

            System.out.println("-----------------");
            System.out.println("PRODUCTOS RESTANTES:"+nroProd);
            semS.release();
            setCliente(null);
            
        } else {
            System.out.println(cliente.id+" dice: Estante vacio,esperare");
            while(nroProd==0){}
            get(cliente);
        }
        
    }

    void put() {
        try {
            semS.acquire();
        } catch (InterruptedException ex) {
            System.out.println("ERROR PUT ESTANTE");
        }
        System.out.println("Empleado dice: voy a reponer mi estante");
        //Meter codigo productor aqui
        nroProd = nroProd+3;
        semS.release();
        
        
    }

    
    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNroProd() {
        return nroProd;
    }

    public void setNroProd(int nroProd) {
        this.nroProd = nroProd;
    }

    public Semaphore getSemS() {
        return semS;
    }

    public void setSemS(Semaphore semS) {
        this.semS = semS;
    }
    
    
    
}

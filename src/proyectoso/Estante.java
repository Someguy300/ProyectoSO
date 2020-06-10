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
    
    private Semaphore semS,semN; 
    
    public Estante(int nroProd){
        this.nroProd = nroProd;
        this.cliente = null;
        this.semS = new Semaphore(1);
        this.semN = new Semaphore(0);
    }

    

    void get(Cliente cliente) throws InterruptedException {
        if(nroProd!=0){
            cliente.setSemE(semS);
            cliente.getSemE().acquire();
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
            cliente.getSemE().release();
            setCliente(null);
            
        } else {
            System.out.println(cliente.id+" dice: Estante vacio,esperare");
            while(nroProd==0){}
            get(cliente);
        }
        
    }

    void put(Empleado empleado) throws InterruptedException {
        if(empleado.getSem()==null){
            empleado.setSem(semS);
        }
        
        if(nroProd<=7){
            System.out.println("Empleado dice: Oh no el estante tiene que ser repuesto");
            Thread.sleep(4000);
            empleado.getSem().acquire();
            System.out.println("Empleado dice: reponiendo estante");
            empleado.sleep(1000);
            System.out.println("Empleado dice: Estante repuesto con 3 productos");
            nroProd = nroProd + 3;
            System.out.println("PRODUCTOS RESTANTES: "+nroProd);
            empleado.getSem().release();
        } 
        
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

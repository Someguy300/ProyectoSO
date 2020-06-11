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
    //Variables
    //ints para darle precio a los productos y cuantos productos
    //se va a agarrar
    int randomInt, randomCost;
    
    private Empleado empleado;
    
    private Cliente cliente;
    
    //numero de productos en el estante y su capacidad
    //en un comienzo tienen el mismo valor
    private int nroProd,cap;
    
    //su semaforo
    private Semaphore semS; 
    
    //el cliente es null porque bueno...el cliente que accede al
    //estante cambia cada rato
    public Estante(int nroProd){
        this.nroProd = nroProd;
        this.cap = nroProd;
        this.cliente = null;
        this.semS = new Semaphore(1);
    }

    
    /*Este deberia ser el get de productor consumidor, por ahora..o para el
    momento de la entrega este proceso raro el cual estoy seguro no aplica 
    productor consumidor*/
    void get(Cliente cliente) throws InterruptedException {
        /*El cliente puede(decidir)agarrar productos(el gafo igual puede decidir
        agarrar 0 productos), si no hay productos ve al "else" de este "if" 
        para mas detalles*/
        if(nroProd!=0){
            //se le asigna al cliente el semaforo de este estante
            cliente.setSemE(semS);
            //Se le pide permiso al semaforo para acceder al estante
            cliente.getSemE().acquire();
            //y le damos a este estante su cliente para modificar su carrito
            //con los productos que agarre y lo que tiene que pagar
            setCliente(cliente);
            
            System.out.println("-----------------");
            System.out.println(cliente.getNro()+" dice: Estoy en el estante");

            //el tiempo que usa el cliente para decidir que va a agarrar
            cliente.sleep(Control.minuto);
            
            //Un switch dependiendo del nro de productos que hay en el estante
            switch(nroProd){
                //no voy a explicar los 2 casos, hacen lo mismo, 
                //solo que el case 1 hace un random entra 0...vea el caso default
                case 1:
                    randomInt = (int)(2 * Math.random());
                    cliente.setNroItems(cliente.getNroItems()+randomInt);
                    System.out.println(cliente.getNro()+" dice: agarre "+randomInt+" productos");
                    nroProd = nroProd - randomInt;
                    if(randomInt!=0){
                        randomCost= 1 + ((int)(10 * Math.random()));
                        cliente.setTotal(cliente.getTotal()+randomCost);
                    }
                    break;

                default:
                    //Se escoge cuandos productos va a agarrar el cliente
                    randomInt = (int)(3 * Math.random());
                    /*se modifica el nro de prod que el cliente tiene
                    ahora que escribo esto lo del nro de prod que el cliente
                    tiene es inutil...¿tiene algo que ver con el enunciado
                    del proyecto?*/
                    cliente.setNroItems(cliente.getNroItems()+randomInt);
                    //sout de verificacion
                    System.out.println(cliente.getNro()+" dice: agarre "+randomInt+" productos");
                    /*Aqui si es importante el randomInt porque se le resta al
                    total de productos que le quedan al estante despues de que
                    el cliente agarro*/
                    nroProd = nroProd - randomInt;
                    //Si el cliente agarro productos aqui se le asigna el 
                    //precio a c/producto que agarro el cliente
                    if(randomInt!=0){
                        for (int i = 0; i < randomInt; i++) {
                            //Se inventa el precio
                            randomCost= 1 + ((int)(10 * Math.random()));
                            //Se suma al total que tiene que pagar el cliente
                            //y por lo tanto lo que iria al contador global
                            cliente.setTotal(cliente.getTotal()+randomCost);
                        }
                    }
                    break;
            }
            //verificacion
            System.out.println("-----------------");
            System.out.println("PRODUCTOS RESTANTES:"+nroProd);
            //el cliente deja ir el semaforo
            cliente.getSemE().release();
            //nos preparamos para aceptar al proximo cliente, no se porque
            //puse esto...igual se cambia al inicio, pero me da miedo borrarlo
            setCliente(null);
            
        } else {
            //Si el estante esta vacio se entra en un loop infinito hasta
            //que el empleado rellene el estante,luego de eso se vuelve a llamar
            //a la funcion
            System.out.println(cliente.getNro()+" dice: Estante vacio,esperare");
            while(nroProd==0){}
            get(cliente);
        }
        
    }
    
    
    /*Este deberia ser el put de productor consumidor, por ahora..o para el
    momento de la entrega este proceso raro el cual estoy seguro no aplica 
    productor consumidor*/
    void put (Empleado empleado) throws InterruptedException {
        //Si el empleado no tiene semaforo asignado, se le asigna el semaforo
        //de su respectivo estante...me da miedo cambiar esto
        if(empleado.getSem()==null){
            empleado.setSem(semS);
        }
        
        //si el numero de productos es lo suficientemente pequeño como
        //para el empleado vaya a buscar una caja se ejecuta esto
        if(nroProd<=(cap-3)){
            //verificando que el thread empleado funcione
            System.out.println("Empleado dice: Oh no el estante tiene que ser repuesto");
            //buscando la caja...
            Thread.sleep(Control.minuto*4);
            //se pide permiso para usar el estante
            empleado.getSem().acquire();
            //El minuto en el que se repone el estante+verificacion por cout
            System.out.println("Empleado dice: reponiendo estante");
            empleado.sleep(Control.minuto);
            //Se repone el estante
            System.out.println("Empleado dice: Estante repuesto con 3 productos");
            nroProd = nroProd + 3;
            //verificacion+se suelta el permiso para usar el semaforo 
            //del estante...me sigo preguntando si eso esta bien dicho
            System.out.println("PRODUCTOS RESTANTES: "+nroProd);
            empleado.getSem().release();
        } 
        
    }

    
    
    //getters y setters
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

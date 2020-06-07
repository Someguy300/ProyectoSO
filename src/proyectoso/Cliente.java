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
    Semaphore semC, semE1, semE2,semE3,semP;
    int id;
    boolean nuevo;
    Cliente(int id){
        this.id = id;
        this.semC = null;
        this.semE1=null;
        this.semE2=null;
        this.semE3=null;
        this.semP=null;
        this.nuevo = true;
    }
    
    @Override
    public void run(){
        try {
                
                /*Se verifica de que haya carritos disponibles, de no
                  haber carritos disponibles el cliente lo indica y espera
                */
                if(semC.availablePermits()==0){
                    System.out.println(this.id+" dice: No hay carritos, voy a esperar");
                }
                //El cliente entra al super y agarra su carrito
                this.entra();
                this.semC.acquire();
                System.out.println(this.id+" dice: agarre un carrito");
                System.out.println("-----------------");

             

                //El cliente va a los estantes
                System.out.println(this.id+" dice: Voy a los estantes");
                System.out.println("-----------------");
                Thread.sleep(5000);
                /*El cliente verifica de que no haya alguien usando el estante
                ,si hay alguien usando el estante el cliente lo indica y espera*/
                if(semE1.availablePermits()==0){
                    System.out.println(this.id+" dice: Hay alguien en el estante, voy a esperar");
                    //Verificacion de que los demas clientes esten esperando su turno
//                    do {                        
//                       System.out.println(this.id+" dice: Espearando...y viendo"); 
//                    } while (semE.availablePermits()==0);
                }
                //El cliente obtiene el permiso de usar el estante
                System.out.println(this.id+" dice: Estoy en un estante");
                System.out.println("-----------------");
                this.semE1.acquire();
                Thread.sleep(1000);
                //El cliente deja el estante
                this.semE1.release();
                System.out.println(this.id+" dice: Sali del estante");
                System.out.println("-----------------");
                
                
                /*Al igual que antes el cliente verifica y si no hay
                permisos disponibles, avisa y espera*/
                System.out.println(this.id+" dice: Voy a caja");
                System.out.println("-----------------");
                Thread.sleep(5000);
                if(semP.availablePermits()==0){
                    System.out.println(this.id+" dice: Hay gente pagando, voy a esperar");
                }
                this.semP.acquire();
                System.out.println(this.id+" dice: estoy pagando");
                System.out.println("-----------------");
                Thread.sleep(500);
                this.semP.release();
                System.out.println(this.id+" dice: ya pague");
                System.out.println("-----------------");
                
                
                //El cliente hace el release del permiso para usar el carrito
                //y por lo tanto deja el super
                System.out.println(this.id+" dice: voy a dejar el carrito");
                System.out.println("-----------------");
                Thread.sleep(2000);
                this.semC.release();
                System.out.println(this.id+" dice: solte el carrito");
                System.out.println("-----------------");
                System.out.println(this.id+" dice: adios");
                System.out.println("-----------------");

              
                
        } catch (InterruptedException e) {
            System.out.println("El cliente se volvió loco.");
        }
    }
    
    
    private void entra() {
        if(this.nuevo){
            System.out.println("-----------------");
            System.out.println("El cliente " + this.id + " entró al sistema.");
            System.out.println("-----------------");
            this.nuevo = false;
        }
    }

    //Getters y setters

    public Semaphore getSemC() {
        return semC;
    }

    public void setSemC(Semaphore semC) {
        this.semC = semC;
    }

    public Semaphore getSemE1() {
        return semE1;
    }

    public void setSemE1(Semaphore semE1) {
        this.semE1 = semE1;
    }

    public Semaphore getSemE2() {
        return semE2;
    }

    public void setSemE2(Semaphore semE2) {
        this.semE2 = semE2;
    }

    public Semaphore getSemE3() {
        return semE3;
    }

    public void setSemE3(Semaphore semE3) {
        this.semE3 = semE3;
    }
    
    

    public Semaphore getSemP() {
        return semP;
    }

    public void setSemP(Semaphore semP) {
        this.semP = semP;
    }

    
    


    public void setId(int id) {
        this.id = id;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }
    
    
    
}

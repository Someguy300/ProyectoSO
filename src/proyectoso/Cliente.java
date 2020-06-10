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
    
    Semaphore semC, semP;
    
    Estante est1,est2,est3;
    
    int id, nroItems,total;
    
    boolean nuevo;
    
    Cliente(int id){
        this.id = id;
        this.nroItems = 0;
        this.total = 0;
        this.semC = null;
        this.est1=null;
        this.est2=null;
        this.est3=null;
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
                
                this.entra();
                this.semC.acquire();
                
                System.out.println(this.id+" dice: agarre un carrito");
                System.out.println("-----------------");
                System.out.println(this.id+" dice: Voy al estante 1");
                
                Thread.sleep(5000);
                
                est1.get(this);
                
                if(est2!=null){
                    System.out.println("-----------------");
                    System.out.println(this.id+" dice: voy al estante 2");
                    Thread.sleep(5000);
                    est2.get(this);
                }
                
                if(est3!=null){
                    System.out.println("-----------------");
                    System.out.println(this.id+" dice: voy al estante 3");
                    Thread.sleep(5000);
                    est3.get(this);
                }
 
                /*Al igual que antes el cliente verifica y si no hay
                permisos disponibles, avisa y espera*/
                System.out.println("-----------------");
                System.out.println(this.id+" dice: Voy a caja");
                System.out.println("-----------------");
                Thread.sleep(5000);
                
                if(semP.availablePermits()==0){
                    System.out.println(this.id+" dice: Hay gente pagando, voy a esperar");
                }
                this.semP.acquire();
                System.out.println(this.id+" dice: estoy pagando mis "+nroItems+" productos");
                System.out.println("-----------------");
                Thread.sleep(nroItems*50);
                Thread.sleep(1000);
                System.out.println(this.id+" dice: pague "+total+"$");
                this.semP.release();
                System.out.println("-----------------");
                
                
                //El cliente hace el release del permiso para usar el carrito
                //y por lo tanto deja el super
                System.out.println(this.id+" dice: ya pague, voy a dejar el carrito");
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    
    
    public Semaphore getSemP() {
        return semP;
    }

    public void setSemP(Semaphore semP) {
        this.semP = semP;
    }


    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public Estante getEst1() {
        return est1;
    }

    public void setEst1(Estante est1) {
        this.est1 = est1;
    }

    public Estante getEst2() {
        return est2;
    }

    public void setEst2(Estante est2) {
        this.est2 = est2;
    }

    public Estante getEst3() {
        return est3;
    }

    public void setEst3(Estante est3) {
        this.est3 = est3;
    }

    public int getNroItems() {
        return nroItems;
    }

    public void setNroItems(int nroItems) {
        this.nroItems = nroItems;
    }
    
    
    
    
}

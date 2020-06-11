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
    /*La clase dios, si algo falla aqui no pasa nada en el programa...bueno
    mentira el gordo del jefe deberia seguir contando sus hora pero no van a 
    haber ganancias*/
    
    /*Semaforos usados por el cliente,
    semC es el de los carritos
    semP es P de pago, el el semeforo de las cajas
    semR es el semaforo para acceder a un estante*/
    Semaphore semC, semP,semE;
    
    //Los estantes creados en Control se le pasan a cliente
    Estante [] estantes;
    
    /*nro es el id del cliente, no le puse id porque hacia problemas con
    el thread id y no me dejaba crear su getter y setter. nroItems es el
    numero de productos que ha agarrado el cliente de los estantes y
    total es lo que tiene que pagar*/
    int nro, nroItems,total;
    /*No le puse id a los estantes asi que esto es el auxiliar por ahora para
    identificar por que estante va el cliente, como los estantes no se borran
    no crear que haya un problema con eso...no se si ponerle id a los estantes, no lo vi necesario*/
    int contE=1;
    
    //Sorry lo use del ejemplo, pense que podia ser util en algun momento,
    //no lo he usado hasta ahora
    boolean nuevo;
    
    
   
    Cliente(int id, Semaphore semC, Semaphore semP, Estante[]estantes){
        this.nro = id;
        this.nroItems = 0;
        this.total = 0;
        this.estantes=estantes;
        this.semC = semC;
        this.semP= semP;
        //Esto es null proque cambia el semaforo dependiendo del estante en
        //el que este el cliente
        this.semE=null;
        this.nuevo = true;
    }
    
    @Override
    public void run(){
        try {
                
            /*Se verifica de que haya carritos disponibles, de no
              haber carritos disponibles el cliente lo indica y espera
            */
            if(semC.availablePermits()==0){
                System.out.println(this.nro+" dice: No hay carritos, voy a esperar");
            }
            //metodo entra, esta debajo de este metodo
            this.entra();
            //el cliente agarra su carrito, si se me olvida comentar algo
            //revisa los sout, eso deberia explicar mas o menos lo que hace el cliente
            semC.acquire();

            System.out.println(this.nro+" dice: agarre un carrito");
            
            
            /*Hace el recorrido por los estantes, el obtener los objetos
            del estante es algo que se hace en la clase Estante, para mas
            detaller ir alla*/
            for (int i = 0; i < estantes.length; i++) {
                if(estantes[i]!=null){
                    System.out.println("-----------------");
                    System.out.println(this.nro+" dice: voy al estante "+contE);
                    Thread.sleep(Control.minuto*5);
                    estantes[i].get(this);
                    contE++;
                }
            }
          
            /*Al igual que antes el cliente verifica y si no hay
            permisos disponibles, avisa y espera*/
            System.out.println("-----------------");
            System.out.println(this.nro+" dice: Voy a caja");
            System.out.println("-----------------");
            Thread.sleep(Control.minuto*5);

            if(semP.availablePermits()==0){
                System.out.println(this.nro+" dice: Hay gente pagando, voy a esperar");
            }
            //El cliente pide el permiso para usar la caja
            semP.acquire();
            System.out.println(this.nro+" dice: estoy pagando mis "+nroItems+" productos");
            System.out.println("-----------------");
            Thread.sleep(nroItems*((Control.minuto/60)/2));
            Thread.sleep(Control.minuto);
            System.out.println(this.nro+" dice: pague "+total+"$");
            //el cliente deja la caja y suelta su permiso para usar la caja
            //lo dije bien? no se si "suelta su permiso" es el termino correcto
            semP.release();
            System.out.println("-----------------");


            //El cliente hace el release del permiso para usar el carrito
            //y por lo tanto deja el super
            System.out.println(this.nro+" dice: ya pague, voy a dejar el carrito");
            System.out.println("-----------------");
            Thread.sleep(Control.minuto*2);
            semC.release();
            System.out.println(this.nro+" dice: solte el carrito,adios");
            System.out.println("-----------------");
            
                
            //Espera hasta que el thread muera
            join();
              
        } catch (InterruptedException e) {
            System.out.println("El cliente se volvió loco.");
        }
    }
    
    /*Si si esto esta a escala 1:1 del ejemplo, puede ser util en algun momento
    peor para el dia 11/06 no le he visto utilidad ademas de decir que 
    entro al sistema...quizas pueda hacer un arrayList con los clientes en el
    sistema...pero eso ya es peo de la clase Control...creo*/
    private void entra() {
        if(this.nuevo){
            System.out.println("El cliente " + this.nro + " entró al sistema.");
            System.out.println("-----------------");
            this.nuevo = false;
        }
    }

    //Getters y setters

    public int getNro() {
        return nro;
    }

    
    
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

    public Semaphore getSemE() {
        return semE;
    }

    public void setSemE(Semaphore semE) {
        this.semE = semE;
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

    public int getNroItems() {
        return nroItems;
    }

    public void setNroItems(int nroItems) {
        this.nroItems = nroItems;
    }
    
    
    
    
}

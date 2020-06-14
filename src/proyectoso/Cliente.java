package proyectoso;

import Ventana.Interfaz;
import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
    /*NOTA: todos los System.out.print en esta clase son para testing
    y debugging...usar la herramienta de debugging es fastidioso
    aunque super util*/
    
    //Semaforo de los carritos
    Semaphore semC;
    /*Semaforos de las cajas, es P de pago porque no queria usar 
    otra variacion con la letra C*/
    Semaphore semP;

    //Semaforo para acceder a X estante
    Semaphore semE;
    
    //Array donde va na estar los estantes iniciales y los creados
    //posteriormente
    Estante[] estantes;

    /*el id del cliente, no de uso como nombre de la variable
    id porque hacia conflicto con el id que trae el Thread*/
    int nro;
    
    /*Cuantos items agarro el cliente de los estantes,
    sirve para calcular el tiempo que tarda el cliente
    en poner sus items del carrito en la caja para pagar*/
    int nroItems;
    
    /*El total que hay que pagar y que se va a sumar al cont global*/
    int total;

    //Variable auxiliar usada en el desarrollo del programa
    //sirve para ver a que estanva va X cliente
    int contE = 1;
    
    
    //Nunca use esto, perdon, pense que seria util cuando lo vi en el ejemplo
    boolean nuevo;

    /**
     * Constructor de la clase cliente
     * @param id El numero del cliente
     * @param semC Semaforo de los carritos. permite acceder al sistema
     * @param semP Semaforo de las cajas para "pagar"
     * @param estantes Array que contiene los estantes creados
     */
    Cliente(int id, Semaphore semC, Semaphore semP, Estante[] estantes) {
        this.nro = id;
        this.nroItems = 0;
        this.total = 0;
        this.estantes = estantes;
        this.semC = semC;
        this.semP = semP;
        this.semE = null;
        this.nuevo = true;
    }
    
    //Le pondira javadoc a esto pero igual lo que se usa es el metodo start()
    public void run() {
      try {
          //Si no hay carritos disponibles para entrar al super, avisa
        if (this.semC.availablePermits() == 0)
          System.out.println(this.nro + " dice: No hay carritos, voy a esperar"); 
        entra();
        //Estoy seguro que hay un metodo mas efectivo para hacer esto pero no se me ocurre
        /*Desde aqui hasta donde dice HASTA AQUI es para modificar los contadores
        de la interfaz*/
        
        //Se modifica el contador de clientes en la clase Interfaz en el paquete Ventana
        Interfaz.contClientes.setText(Integer.toString(this.nro));
        
        
        this.semC.acquire();
        //Se modifica el contador de clientes en el sistema
        //en la clase Interfaz en el paquete Ventana
        Interfaz.contClientesSis
          .setText(Integer.toString(Integer.parseInt(Interfaz.contClientesSis.getText()) + 1));
        Interfaz.clSist
          .setText(Integer.toString(Integer.parseInt(Interfaz.clSist.getText()) + 1));
        Interfaz.contCarritos
          .setText(Integer.toString(Integer.parseInt(Interfaz.contCarritos.getText()) - 1));
        Interfaz.carDisp
          .setText(Integer.toString(Integer.parseInt(Interfaz.carDisp.getText()) - 1));
        //HASTA AQUI HASTA AQUI HASTA AQUI
        System.out.println(this.nro + " dice: agarre un carrito");
        
        //El cliente recorre los estantes uno por uno
        for (int i = 0; i < this.estantes.length; i++) {
            if (this.estantes[i] != null) {
              System.out.println("-----------------");
              System.out.println(this.nro + " dice: voy al estante " + this.contE);
              //Tomando en cuena que tarda 5 min en llegar a cualquier estante
              Thread.sleep((Control.minuto * 5));
              this.estantes[i].get(this);
              this.contE++;
            } else {
                break;
            }
        } 
        
        System.out.println("-----------------");
        System.out.println(this.nro + " dice: Voy a caja");
        System.out.println("-----------------");
        /*Creo que en el enunciado del proyecto nunca de hablo
        de cuanto tarda el cliente en llegar a caja y no queria que
        el cliente llegara de inmediato asi que asumo que tarda lo mismo
        que con lo que los estantes: 5 min en llegar a cualquier lado*/
        Thread.sleep((Control.minuto * 5));
        if (this.semP.availablePermits() == 0)
          System.out.println(this.nro + " dice: Hay gente pagando, voy a esperar"); 
        //Pide permiso para usar el semaforo
        this.semP.acquire();
        System.out.println(this.nro + " dice: estoy pagando mis " + this.nroItems + " productos");
        System.out.println("-----------------");
        //Creo que esta formula esta bien, es el medio segundo que tarda
        //en poner todos sus objetos en el mostrador
        Thread.sleep((this.nroItems * Control.minuto / 60 / 2));
        //y el minuto que tarda el cajero en hacer la factura
        Thread.sleep(Control.minuto);
        System.out.println(this.nro + " dice: pague " + this.total + "$");
        /*A proposito se que es el cajero el que hace esto pero no sabia como
        hacerlo..lo que se me ocurrio es hacer una clase cajero extends Thread
        que hiciera eso pero entonces tendria que hacerlo como hice los estantes
        y me parecio demasiado engorroso, asi que esto es lo que hay*/
        Thread.sleep((Control.minuto / 2));
        //Se modifica el contador de ganancias en la interfaz
        Interfaz.ganancias
          .setText(Integer.toString(Integer.parseInt(Interfaz.ganancias.getText()) + this.total));
        //El cliente deja la caja
        if(semP.availablePermits()<=Control.maxCaj){
           semP.release(); 
        }
        //Se modifican mas contadores en la interfaz
        Interfaz.clDes
          .setText(Integer.toString(Integer.parseInt(Interfaz.clDes.getText()) + 1));
        System.out.println("-----------------");
        System.out.println(this.nro + " dice: ya pague, voy a dejar el carrito");
        System.out.println("-----------------");
        //Los 2 min que tarda el cliente en dejar el carrito
        Thread.sleep((Control.minuto * 2));
        //deja ir el carritos
        if(semC.availablePermits()<=Control.maxCar){
            semC.release();
            //Se modifican mas contadores en la interfaz
            Interfaz.contCarritos
              .setText(Integer.toString(Integer.parseInt(Interfaz.contCarritos.getText()) + 1));
            Interfaz.carDisp
              .setText(Integer.toString(Integer.parseInt(Interfaz.carDisp.getText()) + 1));
        }
        Interfaz.contClientes
              .setText(Integer.toString(Integer.parseInt(Interfaz.contClientes.getText()) - 1));
        
        
        //Verificaciones
        System.out.println(this.nro + " dice: solte el carrito,adios");
        System.out.println("-----------------");
        //Esperamos a que el thread muera
        join();
      } catch (InterruptedException e) {
        System.out.println("El cliente se volviloco.");
      } 
    }
    
    /**
     * @deprecated No tiene uso
     */
    private void entra() {
      if (this.nuevo) {
        System.out.println("El cliente " + this.nro + " entral sistema.");
        System.out.println("-----------------");
        this.nuevo = false;
      } 
    }
    
    
    //Getters y setters
    public int getNro() {
      return this.nro;
    }

    public Semaphore getSemC() {
      return this.semC;
    }

    public void setSemC(Semaphore semC) {
      this.semC = semC;
    }

    public int getTotal() {
      return this.total;
    }

    public void setTotal(int total) {
      this.total = total;
    }

    public Semaphore getSemE() {
      return this.semE;
    }

    public void setSemE(Semaphore semE) {
      this.semE = semE;
    }

    public Semaphore getSemP() {
      return this.semP;
    }

    public void setSemP(Semaphore semP) {
      this.semP = semP;
    }

    public boolean isNuevo() {
      return this.nuevo;
    }

    public void setNuevo(boolean nuevo) {
      this.nuevo = nuevo;
    }

    public int getNroItems() {
      return this.nroItems;
    }

    public void setNroItems(int nroItems) {
      this.nroItems = nroItems;
    }
}
    
    
    

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
    //Variables iniciales
    //Semaforos de carritos y cajeros
    Semaphore sClientes, sCajeros;
    //Array que va a tener los estantes
    Estante [] estantes;
    //Clase que maneja el cargado de datos del archivo txt
    Archivo archivo;
    //Donde se van a guardar los datos del archivo para ejecutar el programa
    Sucursal sucursal;
    //Clase que va a recorrer la sucursal
    Cliente cliente;
    //Clase que vigila los estantes
    Empleado empleado;
    //Clase encargada del contador de horas
    Jefe jefe;
    //Clase encargada de hacer reset al contador de horas y ganancias
    Gerente gerente;
    //Variables auxiliares y ganancias, hay que implmentar el contador global
    int aux,cont,ganancias;
    //Int estatico, es el equivalente a un minuto en tiempo del programa
    //calculado del tiempo en el txt
    static int minuto;
    
    
    public Control() throws InterruptedException{
        //Instanciacion de las clases
        this.jefe = new Jefe();
        this.gerente = new Gerente(jefe);
        this.archivo = new Archivo();
        this.sucursal = archivo.cargarData();
        /*Aqui se realiza el calculo de cuanto es un minuto en tiempo del programa
        Si desde el archivo nos pasan el equivalente en segundos a una hora
        entonces se divide ese tiempo entre 60 para tener un minuto y *1000
        para hacer la transformacion a microsegundos*/
        Control.minuto = (sucursal.getTiempo()/60)*1000;
      
        this.estantes = new Estante[sucursal.getNroEstantesMax()];
        this.sClientes = new Semaphore(sucursal.getNroCarritosMax(),true);
        this.sCajeros = new Semaphore(sucursal.getNroCajasMax(),true);
        
        //Se crean los estantes iniciales de acuerdo a lo establecido en el txt
        for (int i = 0; i < sucursal.getEstantesIni(); i++) {
            estantes[i] = new Estante(sucursal.getCapEstantes());
        }
        //Se bloquea el acceso a los semaforos dependiendo del numero de
        //carritos/cajas iniciales establecidas en el txt
        aux = sucursal.getNroCarritosMax() - sucursal.getCarritosIni();
        sClientes.acquire(aux);
        aux = sucursal.getNroCajasMax() - sucursal.getCajasIni();
        sCajeros.acquire(aux);
        
        //Verificaciones, pueden ser borradas o comentadas
        System.out.println("Carritos disponibles "+sClientes.availablePermits());
        System.out.println("Cajas abiertos "+sCajeros.availablePermits());
        
        
        
    }
    
    
    
    
    public void iniciar(){
        //Auxiliar para darle un id a los clientes que se crean
        cont = 1;
        //Se crean e inician los empleados que van a manejar los estantes.
        for (int i = 0; i < sucursal.getEstantesIni(); i++) {
            empleado = new Empleado(estantes[i]);
            empleado.start();
        }
        // Se inicia al jefe y al gerente para dar inicio al contador de horas
        jefe.start();
        gerente.start();
        //Creacion  y start de los clientes, para mas detalles ir a Cliente
        //es un loop infinito asi que esto no para hasta que se detenga el programa
        do {
            cliente = new Cliente(cont, sClientes, sCajeros, estantes);
            cliente.start();
            cont++;
            try {
                //Los clientes se crean c/5 min
                Thread.sleep(Control.minuto*5);
            } catch (InterruptedException ex) {
                System.out.println("ERROR INICIAR");
            }
            //Pense que seria bonito poner el recolector un recolector de basura aqui...
            System.gc();
        } while (true);
        
    }

    public int getGanancias() {
        return ganancias;
    }

    public void setGanancias(int ganancias) {
        this.ganancias = ganancias;
    }
    
    
    
    
    
   
}

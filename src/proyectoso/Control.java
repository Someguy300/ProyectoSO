/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import Ventana.Interfaz;
import java.util.concurrent.Semaphore;

public class Control {
    /*Clase control, nuestro "main",por asi decirlo,
    el que controla todo detras de la UI*/
    //Semaforo para los carritos
    Semaphore sClientes;

    //Semaforo para las cajas
    Semaphore sCajeros;

    //Donde se van a guardar los estantes creados
    //PD: amen que no hay que borrar estantes
    Estante[] estantes;
    
    //variable para la clase que maneja el cargado de los datos
    //del txt
    Archivo archivo;
    
    //variable para la clase donde se van a cargar los datos
    //extraidos en la clase Archivo
    Sucursal sucursal;
    
    //El cliente
    Cliente cliente;
    
    //El empleado que maneja el estante
    Empleado empleado;
    
    //Jefe que solo modifica las horas
    Jefe jefe;
    
    //Gerente que resetea contadores
    Gerente gerente;
    
    
    //variable auxiliar
    int aux;
    
    //Contador para los id de los clientes
    int cont;
    
    
    //Lo que vale un minuto de acuerdo a lo establecido por el txt
    static int minuto;
    //carritos disponibles 
    static int carDisp;
    //cajas disponibles 
    static int cajDisp;

    
    public Control() throws InterruptedException {
        //se crea el jefe y gerente que van a manejar los contadores globales
        //...aunque no hay una variable ganancias y lo mostramos directamente en 
        //Ventana.Interfaz
        this.jefe = new Jefe();
        this.gerente = new Gerente(this.jefe);
        //El archivo que va a cargar los datos para la sucursal
        this.archivo = new Archivo();
        //La sucursal que va a indicar cuanto es en segundos una hora en el programa
        //los carritos iniciales, la max cantidad de carritos, etc...
        this.sucursal = this.archivo.cargarData();
        //la formula para calcular cuanto es un minuto en segundos
        minuto = this.sucursal.getTiempo() / 60 * 1000;
        //El array en donde van a estar los estantes creados
        //su tamaño se determina de lo extraido del txt
        this.estantes = new Estante[this.sucursal.getNroEstantesMax()];
        //El semaforo que maneja cuantos clientes hay dentro del supermercado
        this.sClientes = new Semaphore(this.sucursal.getNroCarritosMax(), true);
        //El semaforo que maneja cuantas cajas van a haber en operacion
        this.sCajeros = new Semaphore(this.sucursal.getNroCajasMax(), true);
        //Si surge la pregunta de porque se usa el nro maximo de carritos/cajas
        //es porque se van a adquirir los permisos de acuerdo a cuantos
        //carritos cajas hay inicialmente
        
        //Se crean los estantes y se guardan directamente en el array
        for (int i = 0; i < this.sucursal.getEstantesIni(); i++)
          this.estantes[i] = new Estante(this.sucursal.getCapEstantes()); 
        //Esta el la parte que se dijo en donde se adquieren los permisos
        //de acuerdo al nro de carritos iniciales
        carDisp = this.sucursal.getNroCarritosMax() - this.sucursal.getCarritosIni();
        this.sClientes.acquire(carDisp);
        cajDisp = this.sucursal.getNroCajasMax() - this.sucursal.getCajasIni();
        this.sCajeros.acquire(cajDisp);
        //Verificaciones
        System.out.println("Carritos disponibles " + this.sClientes.availablePermits());
        System.out.println("Cajas abiertos " + this.sCajeros.availablePermits());
    }
    
    /**
     * Comienza el programa de verdad
     * crea los clientes y los empleados que manejan los estantes.
     * tambien de inicio a los threads de los clientes,empleados,jefe y gerente
     */
    public void iniciar() {
        //Aunque no es lo correcto no me gusta comenzar con el cliente 0
        this.cont = 1;
        //Se crean e inician los empleados que manejan los estantes
        //inician esta bien dicho? no se...lo voy a seguir usando
        for (int i = 0; i < this.sucursal.getEstantesIni(); i++) {
          this.empleado = new Empleado(this.estantes[i]);
          this.empleado.start();
        } 
        //Se inicia al jefe y al gerente
        this.jefe.start();
        this.gerente.start();
        //loop infinito que crea los clientes
        while (true) {
          try {
            Thread.sleep((minuto * 5));
          } catch (InterruptedException ex) {
            System.out.println("ERROR INICIAR");
          } 
          this.cliente = new Cliente(this.cont, this.sClientes, this.sCajeros, this.estantes);
          this.cliente.start();
          this.cont++;
          updateInterfaz();
          //pense que seria bueno poner un garbage collector aqui...
          System.gc();
        } 
    }
    
    /**
     * Metodo para actualizar el contador de clientes afuera
     * y cajas operativas en la UI
     */
    //Originalmente pense que este metodo actualizaria mas contadores
    public void updateInterfaz() {
      Interfaz.clAf
        .setText(Integer.toString(this.sClientes.getQueueLength()));
      Interfaz.cajOp
        .setText(Integer.toString(this.sCajeros.availablePermits()));
    }
    
    
    //getters y setters
    public Semaphore getsClientes() {
      return this.sClientes;
    }

    public int getCarDisp() {
      return carDisp;
    }

    public void setCarDisp(int carDisp) {
      Control.carDisp = carDisp;
    }

    public int getCajDisp() {
      return cajDisp;
    }

    public void setCajDisp(int cajDisp) {
      Control.cajDisp = cajDisp;
    }

    public void setsClientes(Semaphore sClientes) {
      this.sClientes = sClientes;
    }

    public Semaphore getsCajeros() {
      return this.sCajeros;
    }

    public void setsCajeros(Semaphore sCajeros) {
      this.sCajeros = sCajeros;
    }

    public Estante[] getEstantes() {
      return this.estantes;
    }

    public void setEstantes(Estante[] estantes) {
      this.estantes = estantes;
    }

    public Archivo getArchivo() {
      return this.archivo;
    }

    public void setArchivo(Archivo archivo) {
      this.archivo = archivo;
    }

    public Sucursal getSucursal() {
      return this.sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
      this.sucursal = sucursal;
    }

    public Cliente getCliente() {
      return this.cliente;
    }

    public void setCliente(Cliente cliente) {
      this.cliente = cliente;
    }

    public Empleado getEmpleado() {
      return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
      this.empleado = empleado;
    }

    public Jefe getJefe() {
      return this.jefe;
    }

    public void setJefe(Jefe jefe) {
      this.jefe = jefe;
    }

    public Gerente getGerente() {
      return this.gerente;
    }

    public void setGerente(Gerente gerente) {
      this.gerente = gerente;
    }

    public int getAux() {
      return this.aux;
    }

    public void setAux(int aux) {
      this.aux = aux;
    }

    public int getCont() {
      return this.cont;
    }

    public void setCont(int cont) {
      this.cont = cont;
    }

    public static int getMinuto() {
      return minuto;
    }

    public static void setMinuto(int minuto) {
      Control.minuto = minuto;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import Ventana.Interfaz;
import java.util.concurrent.Semaphore;

public class Control {
  Semaphore sClientes;
  
  Semaphore sCajeros;
  
  Estante[] estantes;
  
  Archivo archivo;
  
  Sucursal sucursal;
  
  Cliente cliente;
  
  Empleado empleado;
  
  Jefe jefe;
  
  Gerente gerente;
  
  int aux;
  
  int cont;
  
  int ganancias;
  
  static int minuto;
  
  static int carDisp;
  
  static int cajDisp;
  
  public Control() throws InterruptedException {
    this.jefe = new Jefe();
    this.gerente = new Gerente(this.jefe);
    this.archivo = new Archivo();
    this.sucursal = this.archivo.cargarData();
    minuto = this.sucursal.getTiempo() / 60 * 1000;
    this.estantes = new Estante[this.sucursal.getNroEstantesMax()];
    this.sClientes = new Semaphore(this.sucursal.getNroCarritosMax(), true);
    this.sCajeros = new Semaphore(this.sucursal.getNroCajasMax(), true);
    for (int i = 0; i < this.sucursal.getEstantesIni(); i++)
      this.estantes[i] = new Estante(this.sucursal.getCapEstantes()); 
    carDisp = this.sucursal.getNroCarritosMax() - this.sucursal.getCarritosIni();
    this.sClientes.acquire(carDisp);
    cajDisp = this.sucursal.getNroCajasMax() - this.sucursal.getCajasIni();
    this.sCajeros.acquire(cajDisp);
    System.out.println("Carritos disponibles " + this.sClientes.availablePermits());
    System.out.println("Cajas abiertos " + this.sCajeros.availablePermits());
  }
  
  public void iniciar() {
    this.cont = 1;
    for (int i = 0; i < this.sucursal.getEstantesIni(); i++) {
      this.empleado = new Empleado(this.estantes[i]);
      this.empleado.start();
    } 
    this.jefe.start();
    this.gerente.start();
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
      System.gc();
    } 
  }
  
  public void updateInterfaz() {
    Interfaz.clAf
      .setText(Integer.toString(this.sClientes.getQueueLength()));
    Interfaz.cajOp
      .setText(Integer.toString(this.sCajeros.availablePermits()));
  }
  
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
  
  public int getGanancias() {
    return this.ganancias;
  }
  
  public void setGanancias(int ganancias) {
    this.ganancias = ganancias;
  }
}

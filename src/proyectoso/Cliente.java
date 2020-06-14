package proyectoso;

import Ventana.Interfaz;
import java.util.concurrent.Semaphore;

public class Cliente extends Thread {
  Semaphore semC;
  
  Semaphore semP;
  
  Semaphore semE;
  
  Estante[] estantes;
  
  int nro;
  
  int nroItems;
  
  int total;
  
  int contE = 1;
  
  boolean nuevo;
  
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
  
  public void run() {
    try {
      if (this.semC.availablePermits() == 0)
        System.out.println(this.nro + " dice: No hay carritos, voy a esperar"); 
      entra();
      Interfaz.contClientes.setText(Integer.toString(this.nro));
      Interfaz.contClientesSis
        .setText(Integer.toString(Integer.parseInt(Interfaz.contClientesSis.getText()) + 1));
      Interfaz.clSist
        .setText(Integer.toString(Integer.parseInt(Interfaz.clSist.getText()) + 1));
      Interfaz.contCarritos
        .setText(Integer.toString(Integer.parseInt(Interfaz.contCarritos.getText()) - 1));
      Interfaz.carDisp
        .setText(Integer.toString(Integer.parseInt(Interfaz.carDisp.getText()) - 1));
      this.semC.acquire();
      System.out.println(this.nro + " dice: agarre un carrito");
      for (int i = 0; i < this.estantes.length; i++) {
        if (this.estantes[i] != null) {
          System.out.println("-----------------");
          System.out.println(this.nro + " dice: voy al estante " + this.contE);
          Thread.sleep((Control.minuto * 5));
          this.estantes[i].get(this);
          this.contE++;
        } 
      } 
      System.out.println("-----------------");
      System.out.println(this.nro + " dice: Voy a caja");
      System.out.println("-----------------");
      Thread.sleep((Control.minuto * 5));
      if (this.semP.availablePermits() == 0)
        System.out.println(this.nro + " dice: Hay gente pagando, voy a esperar"); 
      this.semP.acquire();
      System.out.println(this.nro + " dice: estoy pagando mis " + this.nroItems + " productos");
      System.out.println("-----------------");
      Thread.sleep((this.nroItems * Control.minuto / 60 / 2));
      Thread.sleep(Control.minuto);
      System.out.println(this.nro + " dice: pague " + this.total + "$");
      Thread.sleep((Control.minuto / 2));
      Interfaz.ganancias
        .setText(Integer.toString(Integer.parseInt(Interfaz.ganancias.getText()) + this.total));
      this.semP.release();
      Interfaz.clDes
        .setText(Integer.toString(Integer.parseInt(Interfaz.clDes.getText()) + 1));
      Interfaz.contClientes
        .setText(Integer.toString(Integer.parseInt(Interfaz.contClientes.getText()) - 1));
      System.out.println("-----------------");
      System.out.println(this.nro + " dice: ya pague, voy a dejar el carrito");
      System.out.println("-----------------");
      Thread.sleep((Control.minuto * 2));
      this.semC.release();
      Interfaz.contCarritos
        .setText(Integer.toString(Integer.parseInt(Interfaz.contCarritos.getText()) + 1));
      Interfaz.carDisp
        .setText(Integer.toString(Integer.parseInt(Interfaz.carDisp.getText()) + 1));
      System.out.println(this.nro + " dice: solte el carrito,adios");
      System.out.println("-----------------");
      join();
    } catch (InterruptedException e) {
      System.out.println("El cliente se volviloco.");
    } 
  }
  
  private void entra() {
    if (this.nuevo) {
      System.out.println("El cliente " + this.nro + " entral sistema.");
      System.out.println("-----------------");
      this.nuevo = false;
    } 
  }
  
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
    
    
    

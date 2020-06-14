/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import Ventana.Interfaz;

public class Gerente extends Thread {
  int ganancias;
  
  Jefe jefe;
  
  Gerente(Jefe jefe) {
    this.jefe = jefe;
    this.ganancias = 0;
  }
  
  public void run() {
    System.out.println("GERENTE ACTIVO");
    try {
      while (true) {
        if (this.jefe.getHoras() == 8) {
          this.jefe.setHoras(0);
          Interfaz.horas.setText("0");
          System.out.println("Gerente dice: reset a " + this.jefe.getHoras() + " horas");
          this.ganancias = 0;
        } 
        Thread.sleep(100L);
      } 
    } catch (InterruptedException ex) {
      System.out.println("gerente se dio a la fuga");
      return;
    } 
  }
}

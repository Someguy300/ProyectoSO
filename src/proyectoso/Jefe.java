/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import Ventana.Interfaz;

public class Jefe extends Thread {
  int horas = 0;
  
  public void run() {
    System.out.println("JEFE ACTIVO");
    try {
      while (true) {
        Thread.sleep((Control.minuto * 60));
        System.out.println("Jefe dice: actualizando horas");
        Thread.sleep((Control.minuto + Control.minuto / 2));
        this.horas++;
        Interfaz.horas.setText(Integer.toString(this.horas));
        System.out.println("Jefe dice: horas trabajadas " + this.horas);
      } 
    } catch (Exception e) {
      System.out.println("jefe se escapo");
      return;
    } 
  }
  
  public int getHoras() {
    return this.horas;
  }
  
  public void setHoras(int horas) {
    this.horas = horas;
  }
}

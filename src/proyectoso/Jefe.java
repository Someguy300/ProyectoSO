/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

/**
 *
 * @author Jesus Barrios
 */
public class Jefe extends Thread{
    //Clase jefe con su unica variable horas
    //porque eso es lo unico que hace...el contador de horas
    int horas;
    
    Jefe(){
        this.horas = 0;
    }
    
    @Override
    public void run(){
        //verificacion por sout
        System.out.println("JEFE ACTIVO");
        try {
            do {                
                //La hora que pasa para agregar al contador
                Thread.sleep(Control.minuto*60);
                System.out.println("Jefe dice: actualizando horas");
                //el minuto y medio que tarda en cambiar el contador
                Thread.sleep(Control.minuto+(Control.minuto/2));
                //una hora mas...
                horas++;
                //mas verificacion
                System.out.println("Jefe dice: horas trabajadas "+horas);
            } while (true);
            
            
             
        } catch (Exception e) {
            System.out.println("jefe se escapo");
        }     
    }

    //getter y setter
    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
    
}

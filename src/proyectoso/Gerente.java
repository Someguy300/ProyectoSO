/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jesus Barrios
 */
public class Gerente extends Thread {
    //Papi gerente con su jefe para resetearle su contador
    //y el contador de ganancias que sigo sin saber como aplicar
    int ganancias;
    Jefe jefe;
    
    Gerente(Jefe jefe){
        this.jefe = jefe;
        this.ganancias = 0;
    }
    
    
    @Override
    public void run(){
        //verificacion
        System.out.println("GERENTE ACTIVO");
        //trycatch obligatorio cuando se usa algo de Thread
        try {
            do {                
                //si ya han pasado 8 horas....
                if(jefe.getHoras()==8){
                    //..se resetea
                    jefe.setHoras(0);
                    //verificacion
                    System.out.println("Gerente dice: reset a "+jefe.getHoras()+" horas");
                    ganancias = 0;
                }
                /*al igual que en Empleado, le da una embolia al programa
                si no se pone sleep y el programa deja de correr el thread*/
                Thread.sleep(100); 
            } while (true);
            
            
        } catch (InterruptedException ex) {
            System.out.println("gerente se dio a la fuga");
        }
    }
}

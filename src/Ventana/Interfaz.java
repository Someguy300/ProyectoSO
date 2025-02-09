/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;
import proyectoso.Control;
import proyectoso.Empleado;
import proyectoso.Estante;

/**
 *
 * @author Ismenia Luces
 */
public class Interfaz extends javax.swing.JFrame {
    //Valores auxiliares
    Control app;
    Empleado empleado;
    Semaphore semAux;
    Estante [] estantes;
    int totalEstantes;
    /**
     * Creates new form Ventana
     */
    public Interfaz(Control app) throws InterruptedException{
        this.app = app;
        this.estantes = app.getEstantes();
        initComponents();
        //Asignacion de valores iniciales de los contadores
        contCarritos.setText(Integer.toString(this.app.getsClientes().availablePermits()));
        carDisp.setText(Integer.toString(this.app.getsClientes().availablePermits()));
        contCajas.setText(Integer.toString(this.app.getsCajeros().availablePermits()));
        totalEstantes = 0;
        for (int i = 0; i < estantes.length; i++) {
            if(estantes[i]!=null)totalEstantes++;
        }
        contEstante.setText(Integer.toString(totalEstantes));
        totEst.setText(Integer.toString(totalEstantes));
        totEmp.setText(Integer.toString(totalEstantes));
        clAf.setText
                (Integer.toString(this.app.getsClientes().getQueueLength()));
        cajOp.setText
                (Integer.toString(this.app.getsCajeros().availablePermits()));
        totCar.setText(Integer.toString(this.app.getSucursal().getNroCarritosMax()));
        //Fin de asignacion de valores iniciales de los contadores
        setLocationRelativeTo(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salir = new javax.swing.JButton();
        lessCaja = new javax.swing.JButton();
        plusEstante = new javax.swing.JButton();
        plusCaja = new javax.swing.JButton();
        plusCarrito = new javax.swing.JButton();
        lessCarrito = new javax.swing.JButton();
        contClientes = new javax.swing.JLabel();
        contCarritos = new javax.swing.JLabel();
        contEstante = new javax.swing.JLabel();
        contClientesSis = new javax.swing.JLabel();
        contCajas = new javax.swing.JLabel();
        nota1 = new javax.swing.JLabel();
        jefeReloj = new javax.swing.JLabel();
        gerenteReloj = new javax.swing.JLabel();
        jefeMoney = new javax.swing.JLabel();
        gerenteMoney = new javax.swing.JLabel();
        nota2 = new javax.swing.JLabel();
        clientesWait = new javax.swing.JLabel();
        carritosNum1 = new javax.swing.JLabel();
        carritosDis1 = new javax.swing.JLabel();
        clientesSis1 = new javax.swing.JLabel();
        estantesNum1 = new javax.swing.JLabel();
        empleadosNum1 = new javax.swing.JLabel();
        cajaNum1 = new javax.swing.JLabel();
        clientesListos1 = new javax.swing.JLabel();
        clAf = new javax.swing.JLabel();
        totCar = new javax.swing.JLabel();
        cajOp = new javax.swing.JLabel();
        carDisp = new javax.swing.JLabel();
        clSist = new javax.swing.JLabel();
        totEst = new javax.swing.JLabel();
        totEmp = new javax.swing.JLabel();
        clDes = new javax.swing.JLabel();
        horas = new javax.swing.JLabel();
        ganancias = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        getContentPane().add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, -1, -1));

        lessCaja.setText("-");
        lessCaja.setBorder(null);
        lessCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessCajaActionPerformed(evt);
            }
        });
        getContentPane().add(lessCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 20, -1));

        plusEstante.setText("+");
        plusEstante.setBorder(null);
        plusEstante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusEstanteActionPerformed(evt);
            }
        });
        getContentPane().add(plusEstante, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 20, -1));

        plusCaja.setText("+");
        plusCaja.setBorder(null);
        plusCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusCajaActionPerformed(evt);
            }
        });
        getContentPane().add(plusCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 20, -1));

        plusCarrito.setText("+");
        plusCarrito.setBorder(null);
        plusCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusCarritoActionPerformed(evt);
            }
        });
        getContentPane().add(plusCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 20, -1));

        lessCarrito.setText("-");
        lessCarrito.setBorder(null);
        lessCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessCarritoActionPerformed(evt);
            }
        });
        getContentPane().add(lessCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 20, -1));

        contClientes.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        contClientes.setForeground(new java.awt.Color(102, 102, 102));
        contClientes.setText("0");
        getContentPane().add(contClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, -1, -1));

        contCarritos.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        contCarritos.setForeground(new java.awt.Color(102, 102, 102));
        contCarritos.setText("0");
        getContentPane().add(contCarritos, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        contEstante.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        contEstante.setForeground(new java.awt.Color(102, 102, 102));
        contEstante.setText("0");
        getContentPane().add(contEstante, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, -1, -1));

        contClientesSis.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        contClientesSis.setForeground(new java.awt.Color(102, 102, 102));
        contClientesSis.setText("0");
        getContentPane().add(contClientesSis, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, -1));

        contCajas.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        contCajas.setForeground(new java.awt.Color(102, 102, 102));
        contCajas.setText("0");
        getContentPane().add(contCajas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 430, -1, -1));

        nota1.setFont(new java.awt.Font("Arial Narrow", 2, 16)); // NOI18N
        nota1.setForeground(new java.awt.Color(51, 51, 51));
        nota1.setText("(Reinicio cada 8 horas)");
        getContentPane().add(nota1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, -1, -1));

        jefeReloj.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        jefeReloj.setForeground(new java.awt.Color(51, 51, 51));
        jefeReloj.setText("- JEFE -");
        getContentPane().add(jefeReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, -1, -1));

        gerenteReloj.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        gerenteReloj.setForeground(new java.awt.Color(51, 51, 51));
        gerenteReloj.setText("- GERENTE -");
        getContentPane().add(gerenteReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, -1, -1));

        jefeMoney.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        jefeMoney.setForeground(new java.awt.Color(51, 51, 51));
        jefeMoney.setText("- JEFE -");
        getContentPane().add(jefeMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 370, -1, -1));

        gerenteMoney.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        gerenteMoney.setForeground(new java.awt.Color(51, 51, 51));
        gerenteMoney.setText("- GERENTE -");
        getContentPane().add(gerenteMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, -1, -1));

        nota2.setFont(new java.awt.Font("Arial Narrow", 2, 16)); // NOI18N
        nota2.setForeground(new java.awt.Color(51, 51, 51));
        nota2.setText("(Reinicio cada 8 horas)");
        getContentPane().add(nota2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 430, -1, -1));

        clientesWait.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        clientesWait.setForeground(new java.awt.Color(51, 51, 51));
        clientesWait.setText("Número de clientes esperando afuera: ");
        getContentPane().add(clientesWait, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, -1, -1));

        carritosNum1.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        carritosNum1.setForeground(new java.awt.Color(51, 51, 51));
        carritosNum1.setText("Número total de carritos:");
        getContentPane().add(carritosNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, -1, -1));

        carritosDis1.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        carritosDis1.setForeground(new java.awt.Color(51, 51, 51));
        carritosDis1.setText("Número de carritos disponibles: ");
        getContentPane().add(carritosDis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, -1));

        clientesSis1.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        clientesSis1.setForeground(new java.awt.Color(51, 51, 51));
        clientesSis1.setText("Clientes en sistema Excelsior Gama: ");
        getContentPane().add(clientesSis1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        estantesNum1.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        estantesNum1.setForeground(new java.awt.Color(51, 51, 51));
        estantesNum1.setText("Número total de estantes: ");
        getContentPane().add(estantesNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 310, -1, -1));

        empleadosNum1.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        empleadosNum1.setForeground(new java.awt.Color(51, 51, 51));
        empleadosNum1.setText("Número total de empleados:");
        getContentPane().add(empleadosNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 180, -1));

        cajaNum1.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        cajaNum1.setForeground(new java.awt.Color(51, 51, 51));
        cajaNum1.setText("Número de cajas registradoras operando:");
        getContentPane().add(cajaNum1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, 270, -1));

        clientesListos1.setFont(new java.awt.Font("Arial Narrow", 3, 16)); // NOI18N
        clientesListos1.setForeground(new java.awt.Color(51, 51, 51));
        clientesListos1.setText("Número de clientes despachados:");
        getContentPane().add(clientesListos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 210, -1));

        clAf.setText("0");
        getContentPane().add(clAf, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 30, -1));

        totCar.setText("0");
        getContentPane().add(totCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 40, -1));

        cajOp.setText("0");
        getContentPane().add(cajOp, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 400, 20, -1));

        carDisp.setText("0");
        getContentPane().add(carDisp, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 30, -1));

        clSist.setText("0");
        getContentPane().add(clSist, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 30, -1));

        totEst.setText("0");
        getContentPane().add(totEst, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, 40, -1));

        totEmp.setText("0");
        getContentPane().add(totEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 30, -1));

        clDes.setText("0");
        getContentPane().add(clDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, 30, -1));

        horas.setText("0");
        getContentPane().add(horas, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 30, -1));

        ganancias.setText("0");
        getContentPane().add(ganancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 370, 30, -1));

        jLabel2.setText("horas trabajadas");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, 110, -1));

        jLabel3.setText("$ en ganancias");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 370, 100, -1));

        jLabel1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Visualizador.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    
    /**
     * Metodo que verifica si el array x de estantes esta lleno o no
     * @param x array de estantes a comprobar
     * @return true si esta lleno, false si hay 1 espacio disponible
     *  
     * 
     */
    public boolean isLleno(Estante [] x){
        for (int i = 0; i < x.length; i++) {
            if(x[i]==null)return false;
        }
        return true;
    }
   
    
    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void plusEstanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusEstanteActionPerformed
        // TODO add your handling code here:
        //usamos el array que esta en control
        estantes = app.getEstantes();
        //verificamos que no este lleno el array
        if(!isLleno(estantes)){
            //se busca el primer espacio libre dentro del array
            for (int i = 0; i < estantes.length; i++) {
                if(estantes[i]==null){
                    //se crea el estante y el empleado encargado
                    estantes[i] = new proyectoso.Estante(app.getSucursal().getCapEstantes());
                    empleado = new Empleado(estantes[i]);
                    empleado.start();
                    //salimos del for para evitar iteraciones innecesarias
                    break;
                }
            }
            //se corrige el array en Control con las modificaciones
            app.setEstantes(estantes);
            //modificamos contadores en el UI
            totalEstantes++;
            contEstante.setText(Integer.toString(totalEstantes));
            totEst.setText(Integer.toString(totalEstantes));
            totEmp.setText(Integer.toString(totalEstantes));
           
        } else {
            JOptionPane.showMessageDialog(null, "Se ha llegado a la capacidad\nmáxima de estantes", 
                    "CAP DE ESTANTES ALCANZADA", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_plusEstanteActionPerformed

    private void plusCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusCarritoActionPerformed
        // TODO add your handling code here:  
        if(app.getsClientes().availablePermits()>(app.getSucursal().getNroCarritosMax()-Control.limCar)){
            JOptionPane.showMessageDialog(null, "No hay mas carritos en esta sucursal", 
                    "AVISO", JOptionPane.ERROR_MESSAGE);
        }else{
            app.getsClientes().release();
            contCarritos.setText(Integer.toString(app.getsClientes().availablePermits()));   
            carDisp.setText(Integer.toString(app.getsClientes().availablePermits()));
        }
            
    
    }//GEN-LAST:event_plusCarritoActionPerformed

    private void lessCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessCarritoActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            if(app.getsClientes().availablePermits()<app.getSucursal().getNroCarritosMin()+Control.limCar){
                JOptionPane.showMessageDialog(null, "No puede haber 0 carritos disponibles", 
                    "AVISO", JOptionPane.ERROR_MESSAGE);
            } else {
                app.getsClientes().acquire();
                contCarritos.setText(Integer.toString(app.getsClientes().availablePermits()));
                carDisp.setText(Integer.toString(app.getsClientes().availablePermits()));
            }
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "ERROR boton lessCarrito", 
                    "ERROR DETECTADO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lessCarritoActionPerformed

    private void plusCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusCajaActionPerformed
        // TODO add your handling code here:
        if(app.getsCajeros().availablePermits()>app.getSucursal().getNroCajasMax()-Control.limCaj){
            JOptionPane.showMessageDialog(null, "No hay mas cajas en esta sucursal", 
                    "AVISO", JOptionPane.ERROR_MESSAGE);
        } else{
            app.getsCajeros().release();
            contCajas.setText(Integer.toString(app.getsCajeros().availablePermits()));   
            cajOp.setText(Integer.toString(Integer.parseInt(cajOp.getText())+1));
        }
        
    }//GEN-LAST:event_plusCajaActionPerformed

    private void lessCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessCajaActionPerformed
        // TODO add your handling code here:
        try {
            if (app.getsCajeros().availablePermits()<app.getSucursal().getNroCajasMin()+Control.limCaj) {
                JOptionPane.showMessageDialog(null, "No puede haber 0 cajas disponibles", 
                    "AVISO", JOptionPane.ERROR_MESSAGE);
            }else {
                app.getsCajeros().acquire();
                contCajas.setText(Integer.toString(app.getsCajeros().availablePermits()));
                cajOp.setText(Integer.toString(Integer.parseInt(cajOp.getText())-1));
            }
            
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "ERROR boton lessCaja", 
                    "ERROR DETECTADO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lessCajaActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel cajOp;
    public static javax.swing.JLabel cajaNum1;
    public static javax.swing.JLabel carDisp;
    public static javax.swing.JLabel carritosDis1;
    public static javax.swing.JLabel carritosNum1;
    public static javax.swing.JLabel clAf;
    public static javax.swing.JLabel clDes;
    public static javax.swing.JLabel clSist;
    public static javax.swing.JLabel clientesListos1;
    public static javax.swing.JLabel clientesSis1;
    public static javax.swing.JLabel clientesWait;
    public static javax.swing.JLabel contCajas;
    public static javax.swing.JLabel contCarritos;
    public static javax.swing.JLabel contClientes;
    public static javax.swing.JLabel contClientesSis;
    public static javax.swing.JLabel contEstante;
    public static javax.swing.JLabel empleadosNum1;
    public static javax.swing.JLabel estantesNum1;
    public static javax.swing.JLabel ganancias;
    private javax.swing.JLabel gerenteMoney;
    private javax.swing.JLabel gerenteReloj;
    public static javax.swing.JLabel horas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jefeMoney;
    private javax.swing.JLabel jefeReloj;
    private javax.swing.JButton lessCaja;
    private javax.swing.JButton lessCarrito;
    private javax.swing.JLabel nota1;
    private javax.swing.JLabel nota2;
    private javax.swing.JButton plusCaja;
    private javax.swing.JButton plusCarrito;
    private javax.swing.JButton plusEstante;
    private javax.swing.JButton salir;
    public static javax.swing.JLabel totCar;
    public static javax.swing.JLabel totEmp;
    public static javax.swing.JLabel totEst;
    // End of variables declaration//GEN-END:variables
}

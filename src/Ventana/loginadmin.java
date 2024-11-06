/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventana;
import java.awt.Color;
import javax.swing.JOptionPane;

public class loginadmin extends javax.swing.JFrame {
    
    int xMouse, yMouse;

    public loginadmin() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        iconadmin = new javax.swing.JLabel();
        registrar = new javax.swing.JButton();
        gestionar = new javax.swing.JButton();
        administrar = new javax.swing.JButton();
        cerrar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        Administrador = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        exitxt = new javax.swing.JPanel();
        salirtxt = new javax.swing.JLabel();
        softvets = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconadmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventana/imagenlogo/iconoadmin.png"))); // NOI18N
        jPanel1.add(iconadmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        registrar.setBackground(new java.awt.Color(0, 204, 204));
        registrar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        registrar.setForeground(new java.awt.Color(255, 255, 255));
        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        jPanel1.add(registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 120, 30));

        gestionar.setBackground(new java.awt.Color(0, 204, 204));
        gestionar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        gestionar.setForeground(new java.awt.Color(255, 255, 255));
        gestionar.setText("Gestionar");
        jPanel1.add(gestionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 120, 30));

        administrar.setBackground(new java.awt.Color(0, 204, 204));
        administrar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        administrar.setForeground(new java.awt.Color(255, 255, 255));
        administrar.setText("Administrar personal");
        administrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administrarActionPerformed(evt);
            }
        });
        jPanel1.add(administrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, 30));

        cerrar.setBackground(new java.awt.Color(0, 204, 204));
        cerrar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        cerrar.setForeground(new java.awt.Color(255, 255, 255));
        cerrar.setText("Cerrar sesion");
        jPanel1.add(cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 160, 30));

        salir.setBackground(new java.awt.Color(0, 204, 204));
        salir.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("Salir");
        jPanel1.add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 510, 100, 30));

        Administrador.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Administrador.setForeground(new java.awt.Color(255, 255, 255));
        Administrador.setText("ADMINISTRADOR");
        jPanel1.add(Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventana/imagenlogo/descarga (3).jpeg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 620));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        exitxt.setBackground(new java.awt.Color(255, 255, 255));

        salirtxt.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        salirtxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salirtxt.setText("X");
        salirtxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salirtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirtxtMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salirtxtMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salirtxtMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitxtLayout = new javax.swing.GroupLayout(exitxt);
        exitxt.setLayout(exitxtLayout);
        exitxtLayout.setHorizontalGroup(
            exitxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(salirtxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );
        exitxtLayout.setVerticalGroup(
            exitxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(salirtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 927, Short.MAX_VALUE)
                .addComponent(exitxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 40));

        softvets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventana/imagenlogo/Green Modern Veterinary Clinic Logo (4).png"))); // NOI18N
        jPanel1.add(softvets, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -10, 880, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void administrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_administrarActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registrarActionPerformed

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void salirtxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirtxtMouseClicked
        System.exit(0);
    }//GEN-LAST:event_salirtxtMouseClicked

    private void salirtxtMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirtxtMouseEntered
         exitxt.setBackground(Color.RED);
    }//GEN-LAST:event_salirtxtMouseEntered

    private void salirtxtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirtxtMouseExited
         exitxt.setBackground(Color.WHITE);
    }//GEN-LAST:event_salirtxtMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(loginadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginadmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginadmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Administrador;
    private javax.swing.JButton administrar;
    private javax.swing.JButton cerrar;
    private javax.swing.JPanel exitxt;
    private javax.swing.JButton gestionar;
    private javax.swing.JPanel header;
    private javax.swing.JLabel iconadmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton registrar;
    private javax.swing.JButton salir;
    private javax.swing.JLabel salirtxt;
    private javax.swing.JLabel softvets;
    // End of variables declaration//GEN-END:variables
}

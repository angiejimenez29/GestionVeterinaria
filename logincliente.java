/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventana;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class logincliente extends javax.swing.JFrame {

    int xMouse, yMouse;
    private JPanel contents;
    
    public logincliente() {
        initComponents();
    }
    private void showPanel(JPanel p){
        p.setSize(620, 520);
        p.setLocation(0, 0);
        Show1.removeAll();
        Show1.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,-1,-1));
        Show1.revalidate();
        Show1.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        iconocliente = new javax.swing.JLabel();
        Cliente = new javax.swing.JLabel();
        Historial = new javax.swing.JButton();
        citas = new javax.swing.JButton();
        cerrar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        exitxt = new javax.swing.JPanel();
        salirtxt = new javax.swing.JLabel();
        softvets = new javax.swing.JLabel();
        Show1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(983, 619));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconocliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventana/imagenlogo/ICONCLIENTEF.png"))); // NOI18N
        jPanel1.add(iconocliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        Cliente.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        Cliente.setForeground(new java.awt.Color(255, 255, 255));
        Cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cliente.setText("CLIENTE");
        jPanel1.add(Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        Historial.setBackground(new java.awt.Color(0, 204, 204));
        Historial.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        Historial.setForeground(new java.awt.Color(255, 255, 255));
        Historial.setText("Historial medico");
        Historial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistorialActionPerformed(evt);
            }
        });
        jPanel1.add(Historial, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 140, 30));

        citas.setBackground(new java.awt.Color(0, 204, 204));
        citas.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        citas.setForeground(new java.awt.Color(255, 255, 255));
        citas.setText("Citas");
        citas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citasActionPerformed(evt);
            }
        });
        jPanel1.add(citas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 140, 30));

        cerrar.setBackground(new java.awt.Color(0, 204, 204));
        cerrar.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        cerrar.setForeground(new java.awt.Color(255, 255, 255));
        cerrar.setText("Cerrar sesion");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });
        jPanel1.add(cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 140, 30));

        Salir.setBackground(new java.awt.Color(0, 204, 204));
        Salir.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        Salir.setForeground(new java.awt.Color(255, 255, 255));
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jPanel1.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 500, 140, 30));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventana/imagenlogo/descarga (3).jpeg"))); // NOI18N
        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 620));

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

        salirtxt.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 24)); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitxtLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(salirtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );
        exitxtLayout.setVerticalGroup(
            exitxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitxtLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(salirtxt))
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 977, Short.MAX_VALUE)
                .addComponent(exitxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(exitxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 1030, 30));

        softvets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventana/imagenlogo/Green Modern Veterinary Clinic Logo (4).png"))); // NOI18N
        softvets.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(softvets, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -360, 970, -1));

        Show1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Show1Layout = new javax.swing.GroupLayout(Show1);
        Show1.setLayout(Show1Layout);
        Show1Layout.setHorizontalGroup(
            Show1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        Show1Layout.setVerticalGroup(
            Show1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        jPanel1.add(Show1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 610, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void citasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citasActionPerformed
        contents = new JPanel();
        contents.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(contents);
        CitasClient p5 = new CitasClient();
        showPanel(p5.getCitas());
    }//GEN-LAST:event_citasActionPerformed

    private void HistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistorialActionPerformed
        contents = new JPanel();
        contents.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(contents);
        HistorialMedico p4 = new HistorialMedico();
        showPanel(p4.getHistorial());
    }//GEN-LAST:event_HistorialActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        login2 lg1= new login2();
        lg1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cerrarActionPerformed

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
            java.util.logging.Logger.getLogger(logincliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(logincliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(logincliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(logincliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new logincliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cliente;
    private javax.swing.JButton Historial;
    private javax.swing.JButton Salir;
    private javax.swing.JPanel Show1;
    private javax.swing.JButton cerrar;
    private javax.swing.JButton citas;
    private javax.swing.JPanel exitxt;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel header;
    private javax.swing.JLabel iconocliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel salirtxt;
    private javax.swing.JLabel softvets;
    // End of variables declaration//GEN-END:variables
}

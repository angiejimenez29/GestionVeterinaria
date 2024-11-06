/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventana;

import gestionveterinaria.Usuario;
import java.awt.Color;
import javax.swing.JOptionPane;

public class login2 extends javax.swing.JFrame {
    
    int xMouse, yMouse;
    
    public login2() {
        initComponents();
    }

    public login2(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        bg = new javax.swing.JPanel();
        softvets = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        logo = new javax.swing.JLabel();
        sesion = new javax.swing.JLabel();
        contraseña = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        usuario1 = new javax.swing.JLabel();
        ussertxt = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        passtxt = new javax.swing.JPasswordField();
        botonentrar = new javax.swing.JPanel();
        entrar = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        exitBtn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        salirtxt = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setForeground(new java.awt.Color(153, 153, 153));
        bg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        softvets.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        softvets.setForeground(new java.awt.Color(153, 153, 153));
        softvets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventana/imagenlogo/2222CIRCULOS.png"))); // NOI18N
        softvets.setText("SOFTVETS");
        bg.add(softvets, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 170, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 140, 1010, 10));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ventana/imagenlogo/Green Modern Veterinary Clinic Logo finalojala.png"))); // NOI18N
        logo.setToolTipText("");
        bg.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 510, 130));

        sesion.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        sesion.setText("INICIAR SESION");
        sesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bg.add(sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 180, 30));

        contraseña.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        contraseña.setForeground(new java.awt.Color(0, 153, 153));
        contraseña.setText("CONTRASEÑA");
        contraseña.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bg.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 110, 30));

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 530, 10));

        usuario1.setFont(new java.awt.Font("Segoe UI Historic", 1, 14)); // NOI18N
        usuario1.setForeground(new java.awt.Color(0, 153, 153));
        usuario1.setText("USUARIO");
        usuario1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bg.add(usuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 80, 30));

        ussertxt.setForeground(new java.awt.Color(153, 153, 153));
        ussertxt.setText("Ingrese su nombre de usuario");
        ussertxt.setBorder(null);
        ussertxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ussertxtMousePressed(evt);
            }
        });
        ussertxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ussertxtActionPerformed(evt);
            }
        });
        bg.add(ussertxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 470, 20));

        jSeparator3.setForeground(new java.awt.Color(102, 102, 102));
        bg.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 530, 20));

        passtxt.setForeground(new java.awt.Color(204, 204, 204));
        passtxt.setText("**********");
        passtxt.setBorder(null);
        passtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passtxtMousePressed(evt);
            }
        });
        passtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passtxtActionPerformed(evt);
            }
        });
        bg.add(passtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, -1));

        botonentrar.setBackground(new java.awt.Color(0, 153, 153));

        entrar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        entrar.setForeground(new java.awt.Color(255, 255, 255));
        entrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        entrar.setText("ENTRAR");
        entrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        entrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                entrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                entrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                entrarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout botonentrarLayout = new javax.swing.GroupLayout(botonentrar);
        botonentrar.setLayout(botonentrarLayout);
        botonentrarLayout.setHorizontalGroup(
            botonentrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonentrarLayout.createSequentialGroup()
                .addComponent(entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        botonentrarLayout.setVerticalGroup(
            botonentrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, botonentrarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bg.add(botonentrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 140, 40));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                headerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                headerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                headerMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout exitBtnLayout = new javax.swing.GroupLayout(exitBtn);
        exitBtn.setLayout(exitBtnLayout);
        exitBtnLayout.setHorizontalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        exitBtnLayout.setVerticalGroup(
            exitBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 937, Short.MAX_VALUE)
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bg.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 40));

        salirtxt.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        salirtxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salirtxt.setText("X");
        salirtxt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bg.add(salirtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 43, 34));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 983, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ussertxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ussertxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ussertxtActionPerformed

    private void passtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passtxtActionPerformed

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getXOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void headerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseClicked
        System.exit(0);
    }//GEN-LAST:event_headerMouseClicked

    private void headerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseEntered

        exitBtn.setBackground(Color.red);
        salirtxt.setForeground(Color.white);
    }//GEN-LAST:event_headerMouseEntered

    private void headerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseExited
        exitBtn.setBackground(Color.white);
        salirtxt.setForeground(Color.black);
    }//GEN-LAST:event_headerMouseExited

    private void entrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarMouseEntered
        botonentrar.setBackground(new Color(67, 213, 214 ));
    }//GEN-LAST:event_entrarMouseEntered

    private void entrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarMouseExited
        botonentrar.setBackground(new Color(52, 165, 166 ));

    }//GEN-LAST:event_entrarMouseExited

    private void ussertxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ussertxtMousePressed
        if (ussertxt.getText().equals("Ingrese su nombre de usuario")){
        ussertxt.setText("");
        ussertxt.setForeground(Color.black);
        }
        if (String.valueOf(passtxt.getPassword()).isEmpty()) {
    } else {
            passtxt.setText("**********");
            passtxt.setForeground(Color.gray);
    }//GEN-LAST:event_ussertxtMousePressed
    }
    private void passtxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passtxtMousePressed
        if (String.valueOf(passtxt.getPassword()).equals("**********")){
            passtxt.setText("");
            passtxt.setForeground(Color.black);
        }
        if(ussertxt.getText().isEmpty()){
        ussertxt.setText("Ingrese su nombre de usuario");
        ussertxt.setForeground(Color.gray);
        }
        passtxt.setText("");
        passtxt.setForeground(Color.black);
    }//GEN-LAST:event_passtxtMousePressed

    private void entrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entrarMouseClicked
        javax.swing.JOptionPane.showMessageDialog(this,"Intento de login con los datos:\nUsuario: " + ussertxt.getText() + "\nContraseña: " + String.valueOf(passtxt.getPassword()),"LOGIN", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_entrarMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        this.setLocation(evt.getXOnScreen() - xMouse, evt.getYOnScreen() - yMouse);
    }//GEN-LAST:event_formMouseDragged

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
            java.util.logging.Logger.getLogger(login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel botonentrar;
    private javax.swing.JLabel contraseña;
    private javax.swing.JLabel entrar;
    private javax.swing.JPanel exitBtn;
    private javax.swing.JPanel header;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel logo;
    private javax.swing.JPasswordField passtxt;
    private javax.swing.JLabel salirtxt;
    private javax.swing.JLabel sesion;
    private javax.swing.JLabel softvets;
    private javax.swing.JTextField ussertxt;
    private javax.swing.JLabel usuario1;
    // End of variables declaration//GEN-END:variables
}

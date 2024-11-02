package Ventana;
import gestionveterinaria.Usuario;
import gestionveterinaria.Conexion;
import java.awt.Color;
import javax.swing.JOptionPane;

public class login extends javax.swing.JFrame {
    private Usuario usuario;
    int xMouse, yMouse;
    
    public login(Usuario usuario) {
        this.usuario = usuario;

        initComponents();
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        Salir = new javax.swing.JPanel();
        X = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        UsuarioTitulo = new javax.swing.JLabel();
        Base01 = new javax.swing.JSeparator();
        UsuarioPedir = new javax.swing.JTextField();
        ContraTitulo = new javax.swing.JLabel();
        Base2 = new javax.swing.JSeparator();
        ContraseñaPedir = new javax.swing.JPasswordField();
        EntrarCuadro = new javax.swing.JPanel();
        Entrar = new javax.swing.JLabel();
        Barra = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(30, 18));
        setMinimumSize(new java.awt.Dimension(550, 640));
        setUndecorated(true);
        setResizable(false);

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setMaximumSize(new java.awt.Dimension(570, 640));
        Fondo.setMinimumSize(new java.awt.Dimension(570, 640));
        Fondo.setPreferredSize(new java.awt.Dimension(550, 640));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Salir.setBackground(new java.awt.Color(51, 102, 255));

        X.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        X.setForeground(new java.awt.Color(255, 255, 255));
        X.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        X.setText("X");
        X.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        X.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                XMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                XMouseExited(evt);
            }
        });

        javax.swing.GroupLayout SalirLayout = new javax.swing.GroupLayout(Salir);
        Salir.setLayout(SalirLayout);
        SalirLayout.setHorizontalGroup(
            SalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(X, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        SalirLayout.setVerticalGroup(
            SalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(X, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        Fondo.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 50, 30));

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        Titulo.setFont(new java.awt.Font("Roboto Medium", 1, 48)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("INICIAR SESIÓN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(Titulo)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Fondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 280, 580, -1));

        UsuarioTitulo.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        UsuarioTitulo.setForeground(new java.awt.Color(0, 0, 153));
        UsuarioTitulo.setText("Usuario");
        Fondo.add(UsuarioTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 250, -1));

        Base01.setBackground(new java.awt.Color(204, 204, 204));
        Base01.setForeground(new java.awt.Color(0, 0, 204));
        Fondo.add(Base01, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 450, 10));

        UsuarioPedir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        UsuarioPedir.setForeground(new java.awt.Color(204, 204, 204));
        UsuarioPedir.setText("Ingrese su nombre de usuario");
        UsuarioPedir.setBorder(null);
        UsuarioPedir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UsuarioPedirMousePressed(evt);
            }
        });
        UsuarioPedir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioPedirActionPerformed(evt);
            }
        });
        Fondo.add(UsuarioPedir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 460, 40));

        ContraTitulo.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        ContraTitulo.setForeground(new java.awt.Color(0, 0, 153));
        ContraTitulo.setText("Contraseña");
        Fondo.add(ContraTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 250, -1));

        Base2.setBackground(new java.awt.Color(204, 204, 204));
        Base2.setForeground(new java.awt.Color(0, 0, 204));
        Fondo.add(Base2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 450, 10));

        ContraseñaPedir.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        ContraseñaPedir.setForeground(new java.awt.Color(204, 204, 204));
        ContraseñaPedir.setText("**********");
        ContraseñaPedir.setBorder(null);
        ContraseñaPedir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ContraseñaPedirMousePressed(evt);
            }
        });
        ContraseñaPedir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContraseñaPedirActionPerformed(evt);
            }
        });
        Fondo.add(ContraseñaPedir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 450, -1));

        EntrarCuadro.setBackground(new java.awt.Color(0, 102, 204));
        EntrarCuadro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        EntrarCuadro.setForeground(new java.awt.Color(0, 102, 204));
        EntrarCuadro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EntrarCuadro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntrarCuadroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EntrarCuadroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EntrarCuadroMouseExited(evt);
            }
        });

        Entrar.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        Entrar.setForeground(new java.awt.Color(255, 255, 255));
        Entrar.setText("ENTRAR");

        javax.swing.GroupLayout EntrarCuadroLayout = new javax.swing.GroupLayout(EntrarCuadro);
        EntrarCuadro.setLayout(EntrarCuadroLayout);
        EntrarCuadroLayout.setHorizontalGroup(
            EntrarCuadroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EntrarCuadroLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(Entrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        EntrarCuadroLayout.setVerticalGroup(
            EntrarCuadroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Entrar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        Fondo.add(EntrarCuadro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 560, 280, 50));

        Barra.setBackground(new java.awt.Color(255, 255, 255));
        Barra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarraMouseDragged(evt);
            }
        });
        Barra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarraMousePressed(evt);
            }
        });

        javax.swing.GroupLayout BarraLayout = new javax.swing.GroupLayout(Barra);
        Barra.setLayout(BarraLayout);
        BarraLayout.setHorizontalGroup(
            BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        BarraLayout.setVerticalGroup(
            BarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        Fondo.add(Barra, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 580, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Logo_1.png"))); // NOI18N
        Fondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsuarioPedirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioPedirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioPedirActionPerformed

    private void BarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraMousePressed
        xMouse=evt.getX();
        yMouse=evt.getY();
    }//GEN-LAST:event_BarraMousePressed

    private void BarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraMouseDragged
        int x=evt.getXOnScreen();
        int y=evt.getYOnScreen();
        this.setLocation(x-xMouse, y-yMouse);
       
    }//GEN-LAST:event_BarraMouseDragged

    private void XMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XMouseClicked
        System.exit(0);
    }//GEN-LAST:event_XMouseClicked

    private void XMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XMouseEntered
        Salir.setBackground(Color.red);
        X.setForeground(Color.black);
    }//GEN-LAST:event_XMouseEntered

    private void XMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XMouseExited
        Salir.setBackground(Color.blue);
        X.setForeground(Color.white);
    }//GEN-LAST:event_XMouseExited

    private void EntrarCuadroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarCuadroMouseEntered
        EntrarCuadro.setBackground(new Color(102,102,255));
    }//GEN-LAST:event_EntrarCuadroMouseEntered

    private void EntrarCuadroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarCuadroMouseExited
        EntrarCuadro.setBackground(new Color(0,102,204));
    }//GEN-LAST:event_EntrarCuadroMouseExited

    private void UsuarioPedirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsuarioPedirMousePressed
        if(UsuarioPedir.getText().equals("Ingrese su nombre de usuario")){
            UsuarioPedir.setText("");
            UsuarioPedir.setForeground(Color.black);
        }
        if(String.valueOf(ContraseñaPedir.getPassword()).isEmpty()){
            ContraseñaPedir.setText("**********");
            ContraseñaPedir.setForeground(Color.gray);
        }
       
        
    }//GEN-LAST:event_UsuarioPedirMousePressed

    private void ContraseñaPedirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContraseñaPedirMousePressed
        if(String.valueOf(ContraseñaPedir.getPassword()).equals("**********")){
            ContraseñaPedir.setText("");
            ContraseñaPedir.setForeground(Color.black);
        }
        if(UsuarioPedir.getText().isEmpty()){
            UsuarioPedir.setText("Ingrese su nombre de usuario");
            UsuarioPedir.setForeground(Color.gray);
        }
    }//GEN-LAST:event_ContraseñaPedirMousePressed

    private void EntrarCuadroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrarCuadroMouseClicked
        String usuarioInput = UsuarioPedir.getText();
        String contrasenaInput = new String(ContraseñaPedir.getPassword());

        if (usuario.iniciarSesion(usuarioInput, contrasenaInput)) {
            // Acceso exitoso, redirigir a la siguiente pantalla o mostrar mensaje
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuarioInput);
            // Aquí puedes abrir otra ventana o cerrar la actual
        } else {
            // Acceso fallido
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_EntrarCuadroMouseClicked

    private void ContraseñaPedirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContraseñaPedirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContraseñaPedirActionPerformed

    /**
     * @param args the command line arguments
*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Barra;
    private javax.swing.JSeparator Base01;
    private javax.swing.JSeparator Base2;
    private javax.swing.JLabel ContraTitulo;
    private javax.swing.JPasswordField ContraseñaPedir;
    private javax.swing.JLabel Entrar;
    private javax.swing.JPanel EntrarCuadro;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Salir;
    private javax.swing.JLabel Titulo;
    private javax.swing.JTextField UsuarioPedir;
    private javax.swing.JLabel UsuarioTitulo;
    private javax.swing.JLabel X;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

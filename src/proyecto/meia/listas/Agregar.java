/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.meia.listas;

import clases.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyecto.meia.*;

/**
 *
 * @author AxelAlejandro
 */
public class Agregar extends javax.swing.JFrame {

    /**
     * Creates new form Agregar
     * @throws java.io.IOException
     */
    public Agregar() throws IOException, Exception {
        initComponents();
        this.setLocationRelativeTo(null);
        AccionCerrar closeAction = new AccionCerrar(new Principal(), this);
        this.titleBar1.addCloseAction(closeAction);
        btnCancelar.addActionListener(closeAction);
        if (!"Administrador".equals(Utilidades.usuarioActual.Rol))
        {
            lblUsuarios.setVisible(false);
            cbrUsuarios.setVisible(false);
        }
        int i = 1;
        ManejoArchivosApilo usuarios = new ManejoArchivosApilo(new File(Utilidades.ArchivoUsuarios), new File(Utilidades.ArchivoDescriptorUsuarios));
        int tamanio = Integer.parseInt(usuarios.obtenerDescriptor(6)) + Integer.parseInt(usuarios.obtenerDescriptor(7));
        String[] arrayUsuarios = new String[tamanio];
        i = 1;
        while (i <= tamanio)
        {
            cbrUsuarios.addItem(usuarios.obtener(i).split("\\|")[0]);
            i++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbrUsuarios = new org.edisoncor.gui.comboBox.ComboBoxRound();
        txtNombreLista = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        txtDescripcion = new org.edisoncor.gui.textField.TextFieldRoundIcon();
        lblUsuarios = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCrear = new org.edisoncor.gui.button.ButtonAction();
        btnCancelar = new org.edisoncor.gui.button.ButtonSeven();
        titleBar1 = new org.edisoncor.gui.varios.TitleBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        cbrUsuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbrUsuariosItemStateChanged(evt);
            }
        });

        txtNombreLista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreListaKeyReleased(evt);
            }
        });

        lblUsuarios.setText("Seleccione a un usuario");

        jLabel1.setText("Nombre de la lista");

        jLabel2.setText("Descripción de la lista");

        btnCrear.setText("Crear Lista");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        titleBar1.setTitulo("Agregar Lista");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbrUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsuarios)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(lblUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbrUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        if (cbrUsuarios.getSelectedItem() == null || Utilidades.comprobarCaracteres(txtNombreLista.getText(), "ABCDEFGHIJKLMÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz") < 1 || 
                Utilidades.comprobarCaracteres(txtDescripcion.getText(), "ABCDEFGHIJKLMÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz") < 1)
        {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
        }
        else
        {
            try {
                ManejoArchivosSecuencial manejoLista = new ManejoArchivosSecuencial(new File(Utilidades.ArchivoListasDeDistribucion), new File(Utilidades.ArchivoDescriptorListasDeDistribucion),
                        new File(Utilidades.ArchivoListasDeDistribucionSecuencial), new File(Utilidades.ArchivoDescriptorListasDeDistribucionSecuencial));
                if (!manejoLista.obtener(txtNombreLista.getText(), cbrUsuarios.getSelectedItem().toString()).split("\\|")[0].equals(""))
                {
                    JOptionPane.showMessageDialog(this, "La lista que desea crear la existe");
                }
                else
                {
                    Date fecha = new Date();
                    ListaDeDistribucion lista = new ListaDeDistribucion(txtNombreLista.getText(), cbrUsuarios.getSelectedItem().toString(), txtDescripcion.getText(), 0, 
                            Utilidades.formato.format(fecha), true);
                    manejoLista.insertar(lista);
                    manejoLista.actualizarDescriptor(1, 0, "nombre|usuario|descripcion|numero_usuarios|fecha_creacion|estatus");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex);
                ex.printStackTrace();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex);
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_btnCrearActionPerformed

    private void txtNombreListaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreListaKeyReleased
        try {
            // TODO add your handling code here:
            ManejoArchivosSecuencial manejoLista = new ManejoArchivosSecuencial(new File(Utilidades.ArchivoListasDeDistribucion), new File(Utilidades.ArchivoDescriptorListasDeDistribucion),
                    new File(Utilidades.ArchivoListasDeDistribucionSecuencial), new File(Utilidades.ArchivoDescriptorListasDeDistribucionSecuencial));
            if (!manejoLista.obtener(txtNombreLista.getText(), cbrUsuarios.getSelectedItem().toString()).split("\\|")[0].equals(""))
            {
                txtNombreLista.setForeground(Utilidades.Rojo);
                txtNombreLista.setColorDeBorde(Utilidades.Rojo);
                txtNombreLista.setBackground(Utilidades.RojoClaro);
            }
            else
            {
                txtNombreLista.setForeground(Utilidades.Verde);
                txtNombreLista.setColorDeBorde(Utilidades.Verde);
                txtNombreLista.setBackground(Utilidades.VerdeClaro);
            }
        } catch (IOException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtNombreListaKeyReleased

    private void cbrUsuariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbrUsuariosItemStateChanged
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            ManejoArchivosSecuencial manejoLista = new ManejoArchivosSecuencial(new File(Utilidades.ArchivoListasDeDistribucion), new File(Utilidades.ArchivoDescriptorListasDeDistribucion),
                    new File(Utilidades.ArchivoListasDeDistribucionSecuencial), new File(Utilidades.ArchivoDescriptorListasDeDistribucionSecuencial));
            if (!manejoLista.obtener(txtNombreLista.getText(), cbrUsuarios.getSelectedItem().toString()).split("\\|")[0].equals(""))
            {
                txtNombreLista.setForeground(Utilidades.Rojo);
                txtNombreLista.setColorDeBorde(Utilidades.Rojo);
                txtNombreLista.setBackground(Utilidades.RojoClaro);
            }
            else
            {
                txtNombreLista.setForeground(Utilidades.Verde);
                txtNombreLista.setColorDeBorde(Utilidades.Verde);
                txtNombreLista.setBackground(Utilidades.VerdeClaro);
            }
        } catch (IOException ex) {
            Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbrUsuariosItemStateChanged

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
            java.util.logging.Logger.getLogger(Agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Agregar().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Agregar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonSeven btnCancelar;
    private org.edisoncor.gui.button.ButtonAction btnCrear;
    private org.edisoncor.gui.comboBox.ComboBoxRound cbrUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblUsuarios;
    private org.edisoncor.gui.varios.TitleBar titleBar1;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtDescripcion;
    private org.edisoncor.gui.textField.TextFieldRoundIcon txtNombreLista;
    // End of variables declaration//GEN-END:variables
}

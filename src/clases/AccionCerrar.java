/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author AxelAlejandro
 */
public class AccionCerrar implements ActionListener {
    private JFrame FormAnterior = null;
    private JFrame FormActual = null;
    private ManejoArchivosSecuencial Secuencial = null;
    public AccionCerrar(JFrame formActual)
    {
        FormActual = formActual;
    }
    
    public AccionCerrar(JFrame formAnterior, JFrame formActual)
    {
        FormAnterior = formAnterior;
        FormActual = formActual;
    }
    
    public AccionCerrar(JFrame formAnterior, JFrame formActual, ManejoArchivosSecuencial secuencial)
    {
        FormAnterior = formAnterior;
        FormActual = formActual;
        Secuencial = secuencial;
    }
    
    public AccionCerrar(JFrame formActual, ManejoArchivosSecuencial secuencial)
    {
        FormActual = formActual;
        Secuencial = secuencial;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (FormAnterior == null)
        {
            if (JOptionPane.showConfirmDialog(FormActual, "¿Seguro que desea salir?", "Salir", 0) == 0)
            {
                if (Secuencial != null)
                {
                    try {
                        Secuencial.reorganizar();
                        Utilidades.CantidadActualSecuencial = 0;
                    } catch (IOException ex) {
                        Logger.getLogger(AccionCerrar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.exit(0);
            }
        }
        else
        {
            FormAnterior.show();
            FormActual.dispose();
        }
        
    }
}

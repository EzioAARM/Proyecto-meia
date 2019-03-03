/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import proyecto.meia.Principal;

/**
 *
 * @author AxelAlejandro
 */
public class AccionVerCorreo implements ActionListener {
    List<DatoArbol> Datos = null;
    private Map nota = new HashMap();
    Principal FormPrincipal = null;
    public AccionVerCorreo(List<DatoArbol> datos, Principal formPrincipal)
    {
        FormPrincipal = formPrincipal;
        Datos = datos;
    }
    
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        String[] dato = e.getActionCommand().split("\\_");
        if (dato[0].equals("env"))
        {
            FormPrincipal.lblDeEntrada.setText(Datos.get(Integer.parseInt(dato[1])).UsuarioEmisor);
            FormPrincipal.lblAsuntoEntrada.setText(Datos.get(Integer.parseInt(dato[1])).Asunto);
            FormPrincipal.epMensajeEntrada.setText(Datos.get(Integer.parseInt(dato[1])).Detalle + Utilidades.SaltoLinea + "Adjuntos: (Ubicacion en el equipo): " + 
                    Datos.get(Integer.parseInt(dato[1])).Adjunto);
        }
        else
        {
            FormPrincipal.lblPara.setText(Datos.get(Integer.parseInt(dato[1])).UsuarioReceptor);
            FormPrincipal.lblAsuntoEnviado.setText(Datos.get(Integer.parseInt(dato[1])).Asunto);
            FormPrincipal.epMensajeEnviado.setText(Datos.get(Integer.parseInt(dato[1])).Detalle + Utilidades.SaltoLinea + "Adjuntos: (Ubicacion en el equipo): " + 
                    Datos.get(Integer.parseInt(dato[1])).Adjunto);
        }
    }
}

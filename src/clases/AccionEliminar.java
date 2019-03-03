/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto.meia.Principal;

/**
 *
 * @author AxelAlejandro
 */
public class AccionEliminar implements ActionListener {
    
    List<DatoArbol> Datos = null;
    private Map nota = new HashMap();
    Principal FormPrincipal = null;
    public AccionEliminar(List<DatoArbol> datos, Principal formPrincipal)
    {
        FormPrincipal = formPrincipal;
        Datos = datos;
    }
    
    public void actionPerformed(ActionEvent e) {
        try {
            String comando = e.getActionCommand();
            String[] dato = e.getActionCommand().split("\\_");
            DatoArbol datoEliminar = Datos.get(Integer.parseInt(dato[1]));
            ArbolBinario arbol = new ArbolBinario(new File(Utilidades.ArchivoArbol), new File(Utilidades.ArchivoDescriptorArbol));
            arbol.Eliminar(datoEliminar.UsuarioEmisor + "|" + datoEliminar.UsuarioReceptor + "|" + datoEliminar.FechaTransaccion);
        } catch (Exception ex) {
            Logger.getLogger(AccionEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

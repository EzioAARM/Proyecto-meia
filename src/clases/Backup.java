/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author AxelAlejandro
 */
public class Backup extends ObjetoBase {
    public String FechaBackup;
    public String Ubicacion;
    public String Usuario;
    public boolean Estatus;
    
    public Backup(String fechaBackup, String ubicacion, String usuario, boolean estatus) throws Exception
    {
        super.CantidadCampos = 4;
        FechaBackup = fechaBackup;
        Ubicacion = ubicacion;
        Usuario = usuario;
        Estatus = estatus;
        llenarLista();
    }
    
    public Backup(String[] datos) throws Exception
    {
        super.CantidadCampos = 4;
        FechaBackup = datos[2];
        Ubicacion = datos[0];
        Usuario = datos[1];
        if (datos[3] == "true")
            Estatus = true;
        else
            Estatus = false;
        llenarLista();
    }
    
    @Override
    public void llenarLista() throws Exception
    {
        super.Campos = new ArrayList();
        super.Campos.add(Ubicacion);
        super.Campos.add(Usuario);
        super.Campos.add(FechaBackup);
        super.Campos.add(String.valueOf(Estatus));
    }
}
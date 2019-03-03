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
public class ListaUsuario extends ObjetoBase {
    public String Propietario;
    public String Lista;
    public String Usuario;
    public boolean Estatus;
    
    public ListaUsuario(String propietario, String lista, String usuario, boolean estatus) throws Exception
    {
        Propietario = propietario;
        Lista = lista;
        Usuario = usuario;
        Estatus = estatus;
        llenarLista();
    }
    
    public ListaUsuario(String[] datos) throws Exception
    {
        Propietario = datos[0];
        Lista = datos[1];
        Usuario = datos[2];
        if ("true".equals(datos[3]))
            Estatus = true;
        else
            Estatus = false;
        llenarLista();
    }
    
    @Override
    public void llenarLista() throws Exception
    {
        try
        {
            super.Campos = new ArrayList();
            super.Campos.add(Propietario);
            super.Campos.add(Lista);
            super.Campos.add(Usuario);
            super.Campos.add(String.valueOf(Estatus));
        }
        catch (Exception ex)
        {
            throw new Exception("No se pudo llenar la lista de datos.");
        }
    }
    
    public void llenarCampos(ArrayList<String> datos) throws Exception
    {
        try
        {
            Propietario = datos.get(0);
            Lista = datos.get(1);
            Usuario = datos.get(2);
            if ("true".equals(datos.get(3)))
                Estatus = true;
            else
                Estatus = false;
            
        }
        catch (Exception ex)
        {
            throw new Exception("No se pudo llenar la lista de datos.");
        }
    }
}

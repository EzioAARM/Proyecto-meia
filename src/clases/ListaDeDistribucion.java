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
public class ListaDeDistribucion extends ObjetoBase {
    public String Nombre;
    public String Usuario;
    public String Descripcion;
    public int NumeroUsuarios;
    public String FechaCreacion;
    public boolean Estatus;
    
    public ListaDeDistribucion(String nombre, String usuario, String descripcion, int numeroUsuarios, String fechaCreacion, boolean estatus) throws Exception
    {
        super.CantidadCampos = 6;
        Nombre = nombre;
        Usuario = usuario;
        Descripcion = descripcion;
        NumeroUsuarios = numeroUsuarios;
        FechaCreacion = fechaCreacion;
        Estatus = estatus;
        llenarLista();
    }
    
    public ListaDeDistribucion(String[] datos) throws Exception
    {
        super.CantidadCampos = 6;
        Nombre = datos[0];
        Usuario = datos[1];
        Descripcion = datos[2];
        NumeroUsuarios = Integer.parseInt(datos[3]);
        FechaCreacion = datos[4];
        if (Boolean.valueOf(datos[5]))
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
            super.Campos.add(Nombre);
            super.Campos.add(Usuario);
            super.Campos.add(Descripcion);
            super.Campos.add(String.valueOf(NumeroUsuarios));
            super.Campos.add(String.valueOf(FechaCreacion));
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
            Nombre = datos.get(0);
            Usuario = datos.get(1);
            Descripcion = datos.get(2);
            NumeroUsuarios = Integer.parseInt(datos.get(3));
            FechaCreacion = datos.get(4);
            
        }
        catch (Exception ex)
        {
            throw new Exception("No se pudo llenar la lista de datos.");
        }
    }
}

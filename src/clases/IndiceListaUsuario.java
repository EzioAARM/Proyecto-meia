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
public class IndiceListaUsuario extends ObjetoBase  {
    public ListaUsuario ListaUsuarios;
    public int Linea;
    public int ID;
    public int IDSiguiente;
    public boolean Estatus;
    public String NombreUsuarioPropietario;
    public String NombreLista;
    public String UsuarioAgregado;
    
    public IndiceListaUsuario(ListaUsuario listaUsuarios, int linea, int id, int idSiguiente) throws Exception
    {
        ListaUsuarios = listaUsuarios;
        NombreUsuarioPropietario = listaUsuarios.Propietario;
        NombreLista = listaUsuarios.Lista;
        UsuarioAgregado = listaUsuarios.Usuario;
        Linea = linea;
        ID = id;
        IDSiguiente = idSiguiente;
        Estatus = listaUsuarios.Estatus;
        llenarLista();
    }
    
    public IndiceListaUsuario(String[] datos) throws Exception
    {
        NombreUsuarioPropietario = datos[1];
        NombreLista = datos[2];
        UsuarioAgregado = datos[3];
        Linea = Integer.parseInt(datos[6]);
        ID = Integer.parseInt(datos[0]);
        IDSiguiente = Integer.parseInt(datos[5]);
        if ("true".equals(datos[4]))
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
            super.Campos.add(String.valueOf(ID));
            super.Campos.add(NombreUsuarioPropietario);
            super.Campos.add(NombreLista);
            super.Campos.add(UsuarioAgregado);
            super.Campos.add(String.valueOf(Estatus));
            super.Campos.add(String.valueOf(IDSiguiente));
            super.Campos.add(String.valueOf(Linea));
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
            ID = Integer.parseInt(datos.get(0));
            NombreUsuarioPropietario = datos.get(1);
            NombreLista = datos.get(2);
            UsuarioAgregado = datos.get(3);
            IDSiguiente = Integer.parseInt(datos.get(5));
            Linea = Integer.parseInt(datos.get(6));
            if ("true".equals(datos.get(4)))
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

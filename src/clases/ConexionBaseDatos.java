/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AxelAlejandro
 */
public final class ConexionBaseDatos {
    Connection con = null;
    String host = "ap-cdbr-azure-southeast-b.cloudapp.net";
    
    public ConexionBaseDatos()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Registro exitoso");
        } 
        catch (Exception e) 
        {
            System.out.println(e.toString());
        }
        try 
        {
            String url = "jdbc:mysql://" + host + "/meia";
            con = DriverManager.getConnection(url, "b90be9a1eb15d7","");
            con.close();
        }
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    /*
    * Insertar en la base de datos.
    * - El adjunto se puede dejar en blanco.
    * - El estatus no se inserta, ya que el otro grupo debe actualizar el datos con si/no dependiendo si el usuario existe.
    */
    public boolean insertar(String grupo, String emisor, String receptor, String asunto, String mensaje, String adjunto)
    {
        try
        {
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.util.Date fechaActual = new java.util.Date();
            String fecha = formato.format(fechaActual);
            String url = "jdbc:mysql://" + host + "/meia";
            con = DriverManager.getConnection(url, "b90be9a1eb15d7","3fb490a9");
            Statement cmd = con.createStatement();
            String query = "INSERT INTO " + grupo + " (emisor, receptor, fecha_transaccion, asunto, mensaje, adjunto, estatus) VALUES ('" + emisor + "','" + receptor + "','" + fecha +
                    "','" + asunto + "','" + mensaje + "','" + adjunto + "','')";
            cmd.executeUpdate(query);
            con.close();
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
    /*
    * Obtiene todos los campos de un registro en la base de datos
    * - Busca todos los registros guardados en la base de datos asociados a un receptor en especifico
    * - Devuelve una lista de objetos DatosBD. Las propiedados de DatosBD son los campos de la base de datos.
    *   Para utilizar los datos devueltos por este método primero se llama al método así: List<DatosBD> datos = objeto.obtener(grupo, receptor)
    *   y para utilizar los datos se usa datos.get(i).PROPIEDAD (Revisar las propiedades de la clase)
    */
    public List<DatosBD> obtener(String grupo, String receptor)
    {
        try
        {
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.util.Date fechaActual = new java.util.Date();
            String fecha = formato.format(fechaActual);
            String url = "jdbc:mysql://" + host + "/meia";
            con = DriverManager.getConnection(url, "b90be9a1eb15d7","3fb490a9");
            Statement cmd = con.createStatement();
            ResultSet rs = null;
            String query = "SELECT * FROM " + grupo + " WHERE receptor='" + receptor + "'";
            rs = cmd.executeQuery(query);
            List<DatosBD> datos = new ArrayList();
            while (rs.next())
            {
                datos.add(new DatosBD(rs));
            }
            con.close();
            return datos;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    /*
    * Obtiene todos los campos de un registro en la base de datos
    * - Busca todos los registros guardados en la base de datos asociados a un receptor en especifico
    * - Devuelve una lista de objetos DatosBD. Las propiedados de DatosBD son los campos de la base de datos.
    *   Para utilizar los datos devueltos por este método primero se llama al método así: List<DatosBD> datos = objeto.obtener(grupo, receptor)
    *   y para utilizar los datos se usa datos.get(i).PROPIEDAD (Revisar las propiedades de la clase)
    */
    public List<DatosBD> obtener(String grupo)
    {
        try
        {
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.util.Date fechaActual = new java.util.Date();
            String fecha = formato.format(fechaActual);
            String url = "jdbc:mysql://" + host + "/meia";
            con = DriverManager.getConnection(url, "b90be9a1eb15d7","3fb490a9");
            Statement cmd = con.createStatement();
            ResultSet rs = null;
            String query = "SELECT * FROM " + grupo + " WHERE estatus=''";
            rs = cmd.executeQuery(query);
            List<DatosBD> datos = new ArrayList();
            while (rs.next())
            {
                datos.add(new DatosBD(rs));
            }
            con.close();
            return datos;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    /*
    * Obtiene todos los campos de un registro en la base de datos
    * - Busca todos los registros guardados en la base de datos asociados a un receptor en especifico
    * - Devuelve una lista de objetos DatosBD. Las propiedados de DatosBD son los campos de la base de datos.
    *   Para utilizar los datos devueltos por este método primero se llama al método así: List<DatosBD> datos = objeto.obtener(grupo, receptor)
    *   y para utilizar los datos se usa datos.get(i).PROPIEDAD (Revisar las propiedades de la clase)
    */
    public List<DatosBD> obtener(String grupo, String receptor, String emisor)
    {
        try
        {
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.util.Date fechaActual = new java.util.Date();
            String fecha = formato.format(fechaActual);
            String url = "jdbc:mysql://" + host + "/meia";
            con = DriverManager.getConnection(url, "b90be9a1eb15d7","3fb490a9");
            Statement cmd = con.createStatement();
            ResultSet rs = null;
            String query = "SELECT * FROM " + grupo + " WHERE receptor='" + receptor + "' AND emisor='" + emisor + "'";
            rs = cmd.executeQuery(query);
            List<DatosBD> datos = new ArrayList();
            while (rs.next())
            {
                datos.add(new DatosBD(rs));
            }
            con.close();
            return datos;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    /*
    * Actualiza el estatus de un mensaje, el estatus depende si el usuario receptor existe o no
    * guardar si en caso que el usuario existe, caso contrario guardar no.
    * - El estatus debe ser si o no
    * - Buscar siempre en la tabla del grupo que le corresponde
    * - Ningun campo puede ser vacío
    * - El valor que devuelve la función indica si hubo un error, no si el usuario existe o no.
    * - El grupo receptor deberá encargarse de revisar en la tabla de otros grupos si el dato estatus fue actualizado.
    */
    public boolean actualizarEstatus(String grupo, String estatus, String emisor, String receptor, String fechaTransaccion)
    {
        try
        {
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.util.Date fechaActual = new java.util.Date();
            String fecha = formato.format(fechaActual);
            String url = "jdbc:mysql://" + host + "/meia";
            con = DriverManager.getConnection(url, "b90be9a1eb15d7","3fb490a9");
            Statement cmd = con.createStatement();
            String query = "UPDATE " + grupo + " SET estatus='" + estatus + "' WHERE emisor='" + emisor + "' AND receptor='" + receptor + "' AND fecha_transaccion='" + fechaTransaccion + "'";
            cmd.executeUpdate(query);
            con.close();
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}

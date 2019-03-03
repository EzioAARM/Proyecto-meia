/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author AxelAlejandro
 */
public class DatosBD {
    /*
    * NO MODIFICAR ESTOS DATOS!, SON LOS QUE ESTÁN EN LA TABLA DE LA BASE DE DATOS
    */
    public String Emisor;
    public String Receptor;
    public String FechaTransaccion;
    public String Asunto;
    public String Mensaje;
    public String Adjunto;
    public String Estatus;
    
    public DatosBD(ResultSet rs) throws SQLException
    {
        Emisor = rs.getString("emisor");
        Receptor = rs.getString("receptor");
        FechaTransaccion = rs.getString("fecha_transaccion");
        Asunto = rs.getString("asunto");
        Mensaje = rs.getString("mensaje");
        Adjunto = rs.getString("adjunto");
        Estatus = rs.getString("estatus");
    }
}

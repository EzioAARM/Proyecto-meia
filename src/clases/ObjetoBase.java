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
public abstract class ObjetoBase {
    // Tiene todos los datos que conforman el objeto
    ArrayList<String> Campos;
    // Cantidad de campos
    int CantidadCampos = 0;
    
    // Devuelve la longitud total del objeto
    public int obtenerLongitud() throws Exception
    {
        try
        {
            int longitudTotal = 0;
            for (int i = 0; i < Campos.size(); i++)
            {
                longitudTotal += Campos.get(i).length();
            }
            return longitudTotal + (CantidadCampos - 1);
        }
        catch (Exception ex)
        {
            throw new Exception(ex);
        }
    }
    
    // Devuelve los datos almacenados en la lista Campos como un string para guardarlo en el archivo
    @Override
    public String toString()
    {
        String TextoTamanioFijo = "";
        for (int i = 0; i < Campos.size(); i++)
        {
            if (i == Campos.size() - 1)
                TextoTamanioFijo += (Campos.get(i));
            else
                TextoTamanioFijo += (Campos.get(i)) + Utilidades.Separador;
        }
        return TextoTamanioFijo;
    }
    
    // Guarda los datos de un string formateado en tamaño fijo en la lista Campos
    public void toStringTamanioVariable(String textoTamanioFijo) throws Exception
    {
        try
        {
            for (int i = 0; i <= textoTamanioFijo.split(Utilidades.Separador).length; i++)
            {
                Campos.add(textoTamanioFijo.split(Utilidades.Separador)[i]);
            }
        }
        catch (Exception ex)
        {
            throw new Exception("Hubo un error obteniendo el registro.");
        }
    }
    
    public void llenarLista() throws Exception
    {
    
    }
    
    public String toStringIndice()
    {
        return "";
    }
}

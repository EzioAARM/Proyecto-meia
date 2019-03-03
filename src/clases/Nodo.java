/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author AxelAlejandro
 */
public class Nodo {
    public Nodo Izquierda;
    public Nodo Derecha;
    public Nodo Padre;
    public DatoArbol Dato;
    public String Llave = "";
    
    public Nodo(DatoArbol dato, String llave)
    {
        Dato = dato;
        Llave = llave;
        Izquierda = null;
        Derecha = null;
        Padre = null;
    }
    
    @Override
    public String toString()
    {
        String izquierdo = "-1"; String derecho = "-1"; String padre = "-1";
        try
        {
            if (Padre != null)
                padre = Padre.Llave;
            if (Izquierda != null)
                izquierdo = Izquierda.Llave;
            if (Derecha != null)
                derecho = Derecha.Llave;
        }
        catch (Exception ex)
        {
            izquierdo = "-1"; derecho = "-1"; padre = "-1";
        }
        return padre + Utilidades.SeparadorArbol + izquierdo + Utilidades.SeparadorArbol + derecho + Utilidades.SeparadorArbol + Llave + 
                Utilidades.SeparadorArbol + Dato.toString();
    }
}

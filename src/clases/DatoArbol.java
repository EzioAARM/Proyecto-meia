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
public final class DatoArbol extends ObjetoBase {
    public String UsuarioEmisor;
    public String UsuarioReceptor;
    public String FechaTransaccion;
    public String Asunto;
    public String Detalle;
    public String Adjunto;
    public boolean Estatus;
    
    public DatoArbol(String usuarioEmisor, String usuarioReceptor, String fechaTransaccion, String asunto, String detalle, String adjunto, boolean estatus)
    {
        UsuarioEmisor = usuarioEmisor;
        UsuarioReceptor = usuarioReceptor;
        FechaTransaccion = fechaTransaccion;
        Asunto = asunto;
        Detalle = detalle;
        Adjunto = adjunto;
        Estatus = estatus;
        llenarLista();
    }
    
    public DatoArbol(String[] datos)
    {
        UsuarioEmisor = datos[0];
        UsuarioReceptor = datos[1];
        FechaTransaccion = datos[2];
        Asunto = datos[3];
        Detalle = datos[4];
        Adjunto = datos[5];
        if ("true".equals(datos[6]))
            Estatus = true;
        else
            Estatus = false;
        llenarLista();
    }
    
    public DatoArbol(String cadena)
    {
        String[] datos = cadena.split("\\|");
        UsuarioEmisor = datos[0];
        UsuarioReceptor = datos[1];
        FechaTransaccion = datos[2];
        Asunto = datos[3];
        Detalle = datos[4];
        Adjunto = datos[5];
        if ("true".equals(datos[6]))
            Estatus = true;
        else
            Estatus = false;
        llenarLista();
    }
    
    @Override
    public void llenarLista()
    {
        super.Campos = new ArrayList();
        super.Campos.add(UsuarioEmisor);
        super.Campos.add(UsuarioReceptor);
        super.Campos.add(FechaTransaccion);
        super.Campos.add(Asunto);
        super.Campos.add(Detalle);
        super.Campos.add(Adjunto);
        super.Campos.add(String.valueOf(Estatus));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AxelAlejandro
 */
public class ManejoArchivosIndizado {
    
    public ManejoArchivosApilo Indice;
    public ManejoArchivosApilo Master;
    public File ArchivoIndice;
    public File DescriptorIndice;
    public File ArchivoMaestro;
    public File DescriptorMaestro;
    public int UltimoIndice = 1;
    public int Inicial = 1;
    
    public ManejoArchivosIndizado(File archivoIndice, File descriptorIndice, File archivoMaestro, File descriptorMaestro) throws IOException
    {
        ArchivoIndice = archivoIndice;
        DescriptorIndice = descriptorIndice;
        ArchivoMaestro = archivoMaestro;
        DescriptorMaestro = descriptorMaestro;
        Indice = new ManejoArchivosApilo(ArchivoIndice, DescriptorIndice);
        Master = new ManejoArchivosApilo(ArchivoMaestro, DescriptorMaestro);
        if (DescriptorIndice.exists())
        {
            UltimoIndice = Integer.parseInt(obtenerDescriptor(8, DescriptorIndice));
            Inicial = Integer.parseInt(obtenerDescriptor(9, DescriptorIndice));
        }
    }
    
    public void eliminar(ObjetoBase objeto) throws Exception
    {
        List<String> datosDesordenados = new ArrayList();
        List<String> datosOrdenados = new ArrayList();
        int cantidadDatos = Indice.obtenerCantidad();
        int i = 1;
        String dato = "";
        while (i <= cantidadDatos)
        {
            dato = Indice.obtener(i);
            if (!dato.split("\\|")[1].equals(objeto.toString().split("\\|")[0]) && !dato.split("\\|")[2].equals(objeto.toString().split("\\|")[1]) && 
                    !dato.split("\\|")[3].equals(objeto.toString().split("\\|")[2]))
            {
                datosDesordenados.add(Indice.obtener(i));
                datosOrdenados.add(Indice.obtener(i));
            }
            i++;
        }
        cantidadDatos = datosDesordenados.size();
        datosOrdenados = ordenar(datosOrdenados);
        List<IndiceListaUsuario> datosEnListaDesordenados = new ArrayList();
        List<IndiceListaUsuario> datosEnListaOrdenados = new ArrayList();
        i = 0;
        while (i < cantidadDatos)
        {
            datosEnListaDesordenados.add(new IndiceListaUsuario(datosDesordenados.get(i).split("\\|")));
            datosEnListaOrdenados.add(new IndiceListaUsuario(datosOrdenados.get(i).split("\\|")));
            i++;
        }
        i = 0;
        IndiceListaUsuario indiceTemporal = null;
        int j = 0;
        while (i < cantidadDatos)
        {
            indiceTemporal = datosEnListaDesordenados.get(i);
            if (indiceTemporal.ID == datosEnListaOrdenados.get(cantidadDatos - 1).ID)
            {
                indiceTemporal.IDSiguiente = 0;
                indiceTemporal.Linea = i + 1;
            }
            else
            {
                j = 0;
                while (j < cantidadDatos)
                {
                    if (indiceTemporal.ID == datosEnListaOrdenados.get(j).ID)
                    {
                        indiceTemporal.IDSiguiente = datosEnListaOrdenados.get(j + 1).ID;
                        indiceTemporal.Linea = i + 1;
                        j = cantidadDatos;
                    }
                    j++;
                }
            }
            datosEnListaDesordenados.get(i).llenarLista();
            datosEnListaDesordenados.set(i, indiceTemporal);
            i++;
        }
        Inicial = datosEnListaOrdenados.get(0).ID;
        FileWriter temporal = new FileWriter(ArchivoIndice);
        BufferedWriter escritorTemporal = new BufferedWriter(temporal);
        escritorTemporal.close();
        temporal.close();
        i = 1;
        while (i <= cantidadDatos)
        {
            Indice.guardarEnApilo(datosEnListaDesordenados.get(i - 1));
            i++;
        }
        i = 1;
        List<String> datosMaestro = new ArrayList();
        String obtenido = "";
        while (i <= cantidadDatos)
        {
            obtenido = Master.obtener(i);
            if (!objeto.toString().split("\\|")[0].equals(obtenido.split("\\|")[0]) && !objeto.toString().split("\\|")[1].equals(obtenido.split("\\|")[1]) && 
                    !objeto.toString().split("\\|")[2].equals(obtenido.split("\\|")[2]))
            {
                datosMaestro.add(obtenido);
            }
            i++;
        }
        temporal = new FileWriter(ArchivoMaestro);
        escritorTemporal = new BufferedWriter(temporal);
        escritorTemporal.close();
        temporal.close();
        i = 0;
        while (i < cantidadDatos)
        {
            Master.guardarEnApilo(new ListaUsuario(datosMaestro.get(i).split("\\|")));
            i++;
        }
    }
    
    public List<String> obtener(String clave1, String clave2) throws Exception
    {
        try
        {
            List<String> retorno = new ArrayList();
            FileReader leer = new FileReader(ArchivoMaestro);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && !"".equals(linea) && !linea.equals(Utilidades.Separador))
            {
                split = linea.split("\\|");
                if (split[0].equals(clave1) && split[1].equals(clave2))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (Exception ex)
        {
            throw new Exception("No se han podido obtener los datos");
        }
    }
    
    public String obtener(String clave1, String clave2, String clave3) throws Exception
    {
        try
        {
            String retorno = "";
            FileReader leer = new FileReader(ArchivoMaestro);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            int i = 1;
            while (linea != null && !"".equals(linea) && !linea.equals(Utilidades.Separador))
            {
                split = linea.split("\\|");
                if (split[0].equals(clave1) && split[1].equals(clave2) && split[2].equals(clave3))
                {
                    retorno = linea;
                }
                i++;
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (Exception ex)
        {
            throw new Exception("No se han podido obtener los datos");
        }
    }
    
    public List<String> obtener(String clave1) throws Exception
    {
        try
        {
            List<String> retorno = new ArrayList();
            FileReader leer = new FileReader(ArchivoMaestro);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && !"".equals(linea) && !linea.equals(Utilidades.Separador))
            {
                split = linea.split("\\|");
                if (split[0].equals(clave1))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (Exception ex)
        {
            throw new Exception("No se han podido obtener los datos");
        }
    }
    
    public void insertar(ObjetoBase objeto) throws Exception
    {
        Master.guardarEnApilo(objeto);
        IndiceListaUsuario indice = new IndiceListaUsuario((ListaUsuario) objeto, 1, UltimoIndice, 1);
        UltimoIndice++;
        Indice.guardarEnApilo(indice);
        apuntadoresIndice();
    }
    
    public void apuntadoresIndice() throws IOException, Exception
    {
        List<String> datosDesordenados = new ArrayList();
        List<String> datosOrdenados = new ArrayList();
        int cantidadDatos = Indice.obtenerCantidad();
        int i = 1;
        while (i <= cantidadDatos)
        {
            datosDesordenados.add(Indice.obtener(i));
            datosOrdenados.add(Indice.obtener(i));
            i++;
        }
        datosOrdenados = ordenar(datosOrdenados);
        List<IndiceListaUsuario> datosEnListaDesordenados = new ArrayList();
        List<IndiceListaUsuario> datosEnListaOrdenados = new ArrayList();
        i = 0;
        while (i < cantidadDatos)
        {
            datosEnListaDesordenados.add(new IndiceListaUsuario(datosDesordenados.get(i).split("\\|")));
            datosEnListaOrdenados.add(new IndiceListaUsuario(datosOrdenados.get(i).split("\\|")));
            i++;
        }
        i = 0;
        IndiceListaUsuario indiceTemporal = null;
        int j = 0;
        while (i < cantidadDatos)
        {
            indiceTemporal = datosEnListaDesordenados.get(i);
            if (indiceTemporal.ID == datosEnListaOrdenados.get(cantidadDatos - 1).ID)
            {
                indiceTemporal.IDSiguiente = 0;
                indiceTemporal.Linea = i + 1;
            }
            else
            {
                j = 0;
                while (j < cantidadDatos)
                {
                    if (indiceTemporal.ID == datosEnListaOrdenados.get(j).ID)
                    {
                        indiceTemporal.IDSiguiente = datosEnListaOrdenados.get(j + 1).ID;
                        indiceTemporal.Linea = i + 1;
                        j = cantidadDatos;
                    }
                    j++;
                }
            }
            datosEnListaDesordenados.get(i).llenarLista();
            datosEnListaDesordenados.set(i, indiceTemporal);
            i++;
        }
        Inicial = datosEnListaOrdenados.get(0).ID;
        FileWriter temporal = new FileWriter(ArchivoIndice);
        BufferedWriter escritorTemporal = new BufferedWriter(temporal);
        escritorTemporal.close();
        temporal.close();
        i = 0;
        while (i < cantidadDatos)
        {
            if ("true".equals(datosEnListaDesordenados.get(i).toString().split("\\|")[4]))
                Indice.guardarEnApilo(datosEnListaDesordenados.get(i));
            else
            {
                // Implementar la eliminacion del archivo maestro
                // Solo borrar la linea del dato
            }
            i++;
        }
    }
    
    public void actualizarDescriptor(int activo, int inactivo, String estructura) throws IOException
    {
        if (!DescriptorIndice.exists())
        {
            DescriptorIndice.createNewFile();
            String usuario = Utilidades.usuarioActual.NombreUsuario;
            Date fechaActual = new Date();
            String fecha = Utilidades.formato.format(fechaActual);
            FileWriter escribir = new FileWriter(DescriptorIndice);
            BufferedWriter escritor = new BufferedWriter(escribir);
            escribir.write(estructura + Utilidades.SaltoLinea);
            escribir.write(usuario + Utilidades.SaltoLinea);
            escribir.write(fecha + Utilidades.SaltoLinea);
            escribir.write(usuario + Utilidades.SaltoLinea);
            escribir.write(fecha + Utilidades.SaltoLinea);
            escribir.write(activo + Utilidades.SaltoLinea);
            escribir.write(inactivo + Utilidades.SaltoLinea);
            escribir.write(UltimoIndice + Utilidades.SaltoLinea);
            escribir.write(Inicial + Utilidades.SaltoLinea);
            escritor.close();
            escribir.close();
        }
        else
        {
            String usuario = Utilidades.usuarioActual.NombreUsuario;
            Date fechaActual = new Date();
            String fecha = Utilidades.formato.format(fechaActual);
            String usuarioCreador = obtenerDescriptor(2, DescriptorIndice);
            String fechaCreacion = obtenerDescriptor(3, DescriptorIndice);
            int registrosActivos = Integer.parseInt(obtenerDescriptor(6, DescriptorIndice)) + activo - inactivo; 
            int registrosInactivos = Integer.parseInt(obtenerDescriptor(7, DescriptorIndice)) + inactivo;
            FileWriter escribir = new FileWriter(DescriptorIndice);
            BufferedWriter escritor = new BufferedWriter(escribir);
            escribir.write(estructura + Utilidades.SaltoLinea);
            escribir.write(usuarioCreador + Utilidades.SaltoLinea);
            escribir.write(fechaCreacion + Utilidades.SaltoLinea);
            escribir.write(usuario + Utilidades.SaltoLinea);
            escribir.write(fecha + Utilidades.SaltoLinea);
            escribir.write(registrosActivos + Utilidades.SaltoLinea);
            escribir.write(registrosInactivos + Utilidades.SaltoLinea);
            escribir.write(UltimoIndice + Utilidades.SaltoLinea);
            escribir.write(Inicial + Utilidades.SaltoLinea);
            escritor.close();
            escribir.close();
        }
    }
    
    public String obtenerDescriptor(int numero, File ArchivoDescriptor) throws IOException
    {
        if (!ArchivoDescriptor.exists())
        {
            throw new IOException("El archivo descriptor no existe");
        }
        try
        {
            String retorno = "";
            FileReader leer = new FileReader(ArchivoDescriptor);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            int contador = 1;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                if (contador == numero)
                {
                    retorno = linea;
                }
                linea = lector.readLine();
                contador++;
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (IOException ex)
        {
            throw new IOException("Hubo un error en actualizar el descriptor");
        }
    }
    
    public List<String> ordenar(List<String> datos) throws IOException
    {
        String cadena1 = "";
        String cadena2 = "";
        int i = 1; int j = 0;
        int n = datos.size();
        String aux = "";
        while (i < n)
        {
            j = 0;
            cadena1 = datos.get(i).split("\\|")[1] + "|" + datos.get(i).split("\\|")[2] + "|" + datos.get(i).split("\\|")[3];
            while (j < n)
            {
                cadena2 = datos.get(j).split("\\|")[1] + "|" + datos.get(j).split("\\|")[2] + "|" + datos.get(j).split("\\|")[3];
                if (cadena1.compareTo(cadena2) <= 0)
                {
                    aux = datos.get(i);
                    datos.set(i, datos.get(j));
                    datos.set(j, aux);
                }
                j++;
            }
            i++;
        }
        return datos;
    }
}

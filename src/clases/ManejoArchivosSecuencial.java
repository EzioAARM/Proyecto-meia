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
public class ManejoArchivosSecuencial {
    public File ArchivoApilo;
    public File ArchivoDescriptorApilo;
    public File ArchivoSecuencial;
    public File ArchivoDescriptorSecuencial;
    private ManejoArchivosApilo manejoArchivoApilo = null;
    private ManejoArchivosApilo manejoArchivoSecuencial = null;
    private boolean escribirDescriptorSecuencial = false;
    
    public ManejoArchivosSecuencial(File archivoApilo, File archivoDescriptorApilo, File archivoSecuencial, File archivoDescriptorSecuencial) throws IOException
    {
        ArchivoApilo = archivoApilo;
        ArchivoDescriptorApilo = archivoDescriptorApilo;
        ArchivoSecuencial = archivoSecuencial;
        ArchivoDescriptorSecuencial = archivoDescriptorSecuencial;
        manejoArchivoApilo = new ManejoArchivosApilo(ArchivoApilo, ArchivoDescriptorApilo);
        manejoArchivoSecuencial = new ManejoArchivosApilo(ArchivoSecuencial, ArchivoDescriptorSecuencial);
        try
        {
            Utilidades.CantidadActualSecuencial = Integer.parseInt(manejoArchivoApilo.obtenerDescriptor(6)) + Integer.parseInt(manejoArchivoApilo.obtenerDescriptor(7));
        }
        catch (Exception ex)
        {
            Utilidades.CantidadActualSecuencial = 0;
        }
    }
    
    public boolean insertar(ObjetoBase objeto) throws Exception
    {
        if (Utilidades.CantidadActualSecuencial == Utilidades.MaximoArchivoSecuencial - 1)
        {
            manejoArchivoApilo.guardarEnApilo(objeto);
            Utilidades.CantidadActualSecuencial = 0;
            return reorganizar();
        }
        else
        {
            Utilidades.CantidadActualSecuencial++;
            return manejoArchivoApilo.guardarEnApilo(objeto);
        }
    }
    
    public String obtener(String clave) throws IOException
    {
        String retorno = manejoArchivoSecuencial.obtener(clave);
        if (retorno .equals(""))
        {
            retorno = manejoArchivoApilo.obtener(clave);
        }
        return retorno;
    }
    
    public String obtener(String clave, String clave2) throws IOException
    {
        if (!ArchivoApilo.exists() || !ArchivoSecuencial.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        try
        {
            String retorno = "";
            FileReader leer = new FileReader(ArchivoSecuencial);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[0].equals(clave) && split[1].equals(clave2))
                {
                    retorno = linea;
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            
            leer = new FileReader(ArchivoApilo);
            lector = new BufferedReader(leer);
            linea = lector.readLine();
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[0].equals(clave) && split[1].equals(clave2))
                {
                    retorno = linea;
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudieron obtener los datos");
        }
    }
    
    public List<String> obtenerLista(String clave) throws IOException
    {
        if (!ArchivoApilo.exists() || !ArchivoSecuencial.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        try
        {
            List<String> retorno = new ArrayList();
            FileReader leer = new FileReader(ArchivoSecuencial);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[0].equals(clave))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            
            leer = new FileReader(ArchivoApilo);
            lector = new BufferedReader(leer);
            linea = lector.readLine();
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[0].equals(clave))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudieron obtener los datos");
        }
    }
    
    public List<String> obtenerListaPorUsuario(String clave) throws IOException
    {
        if (!ArchivoApilo.exists() || !ArchivoSecuencial.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        try
        {
            List<String> retorno = new ArrayList();
            FileReader leer = new FileReader(ArchivoSecuencial);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[1].equals(clave))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            
            leer = new FileReader(ArchivoApilo);
            lector = new BufferedReader(leer);
            linea = lector.readLine();
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[1].equals(clave))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudieron obtener los datos");
        }
    }
    
    public List<String> obtenerListaUsuario(String clave) throws IOException
    {
        if (!ArchivoApilo.exists() || !ArchivoSecuencial.exists())
        {
            ArchivoApilo.createNewFile();
            ArchivoSecuencial.createNewFile();
        }
        try
        {
            List<String> retorno = new ArrayList();
            FileReader leer = new FileReader(ArchivoSecuencial);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && !"".equals(linea) && !linea.equals(Utilidades.Separador))
            {
                split = linea.split("\\|");
                if (split[1].equals(clave))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            
            leer = new FileReader(ArchivoApilo);
            lector = new BufferedReader(leer);
            linea = lector.readLine();
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[1].equals(clave))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudieron obtener los datos");
        }
    }
    
    public List<String> obtenerLista(String clave, String clave2) throws IOException
    {
        if (!ArchivoApilo.exists() || !ArchivoSecuencial.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        try
        {
            List<String> retorno = new ArrayList();
            FileReader leer = new FileReader(ArchivoSecuencial);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[0].equals(clave) && split[1].equals(clave2))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            
            leer = new FileReader(ArchivoApilo);
            lector = new BufferedReader(leer);
            linea = lector.readLine();
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[0].equals(clave) && split[1].equals(clave2))
                {
                    retorno.add(linea);
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return retorno;
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudieron obtener los datos");
        }
    }
    
    public void actualizarDescriptor(int activo, int inactivo, String estructura) throws IOException
    {
        manejoArchivoApilo.actualizarDescriptor(activo, inactivo, estructura);
        if (escribirDescriptorSecuencial)
        {
            activo = Integer.parseInt(manejoArchivoApilo.obtenerDescriptor(6));
            inactivo = Integer.parseInt(manejoArchivoApilo.obtenerDescriptor(7));
            manejoArchivoSecuencial.actualizarDescriptor(activo, inactivo, estructura);
            escribirDescriptorSecuencial = false;
            manejoArchivoApilo.actualizarDescriptor(activo * -1, inactivo * -1, estructura);
            FileWriter escribir = new FileWriter(ArchivoApilo);
            BufferedWriter escritor = new BufferedWriter(escribir);
            escritor.close();
            escribir.close();
        }
    }
    
    public boolean reorganizar() throws IOException
    {
        try
        {
            if (!ArchivoSecuencial.exists())
            {
                ArchivoSecuencial.createNewFile();
            }
            List<String> datos = cargarLista(manejoArchivoApilo);
            FileWriter escribirSecuencial = new FileWriter(ArchivoSecuencial, true);
            BufferedWriter escritorSecuencial = new BufferedWriter(escribirSecuencial);
            int i = 0;
            while (i < datos.size())
            {
                escritorSecuencial.write(datos.get(i) + Utilidades.SaltoLinea);
                i++;
            }
            escritorSecuencial.close();
            escribirSecuencial.close();
            ordenar();
            escribirDescriptorSecuencial = true;
            return true;
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudo realizar la reorganizacion");
        }
    }
    
    public List<String> cargarLista(ManejoArchivosApilo archivo) throws IOException
    {
        try
        {
            List<String> datos = new ArrayList();
            int i = 1;
            int tamanio = archivo.obtenerCantidad();
            while (i <= tamanio)
            {
                datos.add(archivo.obtener(i));
                i++;
            }
            return eliminarInactivos(datos);
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudo obtener la longitud del archivo");
        }
    }
    
    public List<String> eliminarInactivos(List<String> datos)
    {
        int i = 0;
        String[] split;
        while (i < datos.size())
        {
            split = datos.get(i).split("\\|");
            if ("false".equals(split[split.length - 1]))
            {
                datos.remove(i);
            }
            i++;
        }
        return datos;
    }
    
    public boolean ordenar() throws IOException
    {
        try
        {
            List<String> datos = cargarLista(manejoArchivoSecuencial);
            int i = 1; int j = 0;
            int n = datos.size();
            String aux = "";
            while (i < n)
            {
                j = 0;
                while (j < n)
                {
                    if (datos.get(i).compareTo(datos.get(j)) < 0)
                    {
                        aux = datos.get(i);
                        datos.set(i, datos.get(j));
                        datos.set(j, aux);
                    }
                    j++;
                }
                i++;
            }
            System.out.println("ordenado");
            filtrarArchivo(datos);
            return true;
        }
        catch (IOException ex)
        {
            throw new IOException("No se ha podido ordenar el archivo.");
        }
    }
    
    public boolean filtrarArchivo(List<String> datos)
    {
        try
        {
            FileWriter escribir = new FileWriter(ArchivoSecuencial, false);
            BufferedWriter escritor = new BufferedWriter(escribir);
            int i = 0;
            while (i < datos.size())
            {
                escritor.write(datos.get(i) + Utilidades.SaltoLinea);
                i++;
            }
            escritor.close();
            escribir.close();
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }
}

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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AxelAlejandro
 */
public class ManejoArchivosApilo {
    public File ArchivoPrincipal;
    public File ArchivoDescriptor;
    
    public ManejoArchivosApilo(File archivoPrincipal, File archivoDescriptor) throws IOException
    {
        ArchivoPrincipal = archivoPrincipal;
        ArchivoDescriptor = archivoDescriptor;
    }
    
    public boolean guardarEnApilo(ObjetoBase objeto) throws Exception
    {
        if (!(objeto instanceof ObjetoBase))
        {
            throw new Exception("No se está recibiendo el objeto correcto.");
        }
        if (!ArchivoPrincipal.exists())
            ArchivoPrincipal.createNewFile();
        try
        {
            FileWriter escribir = new FileWriter(ArchivoPrincipal, true);
            BufferedWriter escritor = new BufferedWriter(escribir);
            escritor.write(objeto.toString() + Utilidades.SaltoLinea);
            escritor.close();
            escribir.close();
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }
    
    public String obtener(String clave) throws IOException
    {
        if (!ArchivoPrincipal.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        try
        {
            String retorno = "";
            FileReader leer = new FileReader(ArchivoPrincipal);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[0].equals(clave))
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
    
    public boolean obtenerExistencia(String clave) throws IOException
    {
        if (!ArchivoPrincipal.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        try
        {
            String retorno = "";
            FileReader leer = new FileReader(ArchivoPrincipal);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            String[] split;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                split = linea.split("\\|");
                if (split[0].equals(clave))
                {
                    retorno = linea;
                }
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            if (retorno == "")
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudieron obtener los datos");
        }
    }
    
    public String obtener(int numero) throws IOException
    {
        if (!ArchivoPrincipal.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        try
        {
            String retorno = "";
            FileReader leer = new FileReader(ArchivoPrincipal);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            int i = 1;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                if (i == numero)
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
        catch (IOException ex)
        {
            throw new IOException("No se pudieron obtener los datos");
        }
    }
    
    public int obtenerCantidad() throws IOException
    {
        if (!ArchivoPrincipal.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        try
        {
            FileReader leer = new FileReader(ArchivoPrincipal);
            BufferedReader lector = new BufferedReader(leer);
            String linea = lector.readLine();
            int i = 0;
            while (linea != null && linea != "" && linea != Utilidades.Separador)
            {
                i++;
                linea = lector.readLine();
            }
            leer.close();
            lector.close();
            return i;
        }
        catch (IOException ex)
        {
            throw new IOException("No se pudieron obtener los datos");
        }
    }
    
    public void actualizarDescriptor(int activo, int inactivo, String estructura) throws IOException
    {
        if (!ArchivoDescriptor.exists())
        {
            ArchivoDescriptor.createNewFile();
            String usuario = Utilidades.usuarioActual.NombreUsuario;
            Date fechaActual = new Date();
            String fecha = Utilidades.formato.format(fechaActual);
            FileWriter escribir = new FileWriter(ArchivoDescriptor);
            BufferedWriter escritor = new BufferedWriter(escribir);
            escribir.write(estructura + Utilidades.SaltoLinea);
            escribir.write(usuario + Utilidades.SaltoLinea);
            escribir.write(fecha + Utilidades.SaltoLinea);
            escribir.write(usuario + Utilidades.SaltoLinea);
            escribir.write(fecha + Utilidades.SaltoLinea);
            escribir.write(activo + Utilidades.SaltoLinea);
            escribir.write(inactivo + Utilidades.SaltoLinea);
            escritor.close();
            escribir.close();
        }
        else
        {
            String usuario = Utilidades.usuarioActual.NombreUsuario;
            Date fechaActual = new Date();
            String fecha = Utilidades.formato.format(fechaActual);
            String usuarioCreador = obtenerDescriptor(2);
            String fechaCreacion = obtenerDescriptor(3);
            int registrosActivos = Integer.parseInt(obtenerDescriptor(6)) + activo - inactivo; 
            int registrosInactivos = Integer.parseInt(obtenerDescriptor(7)) + inactivo;
            FileWriter escribir = new FileWriter(ArchivoDescriptor);
            BufferedWriter escritor = new BufferedWriter(escribir);
            escribir.write(estructura + Utilidades.SaltoLinea);
            escribir.write(usuarioCreador + Utilidades.SaltoLinea);
            escribir.write(fechaCreacion + Utilidades.SaltoLinea);
            escribir.write(usuario + Utilidades.SaltoLinea);
            escribir.write(fecha + Utilidades.SaltoLinea);
            escribir.write(registrosActivos + Utilidades.SaltoLinea);
            escribir.write(registrosInactivos + Utilidades.SaltoLinea);
            escritor.close();
            escribir.close();
        }
    }
    
    public String obtenerDescriptor(int numero) throws IOException
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
}

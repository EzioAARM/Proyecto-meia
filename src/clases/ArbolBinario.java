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
public final class ArbolBinario {
    private Nodo _raiz = null;
    private final File ArchivoPrincipal;
    private final File ArchivoDescriptor;
    private int Tamanio = 0;
    
    public ArbolBinario(File archivoPrincipal, File archivoDescriptor) throws Exception
    {
        ArchivoPrincipal = archivoPrincipal;
        ArchivoDescriptor = archivoDescriptor;
        if (ArchivoPrincipal.exists())
        {
            cargarArbol();
        }
        else
        {
            ArchivoPrincipal.createNewFile();
        }
    }
    
    public void agregar(DatoArbol dato, String llave) throws Exception
    {
        Nodo nodoNuevo = new Nodo(dato, llave);
        _raiz = agregarRecursivo(_raiz, nodoNuevo);
        Tamanio++;
    }
    
    public void agregarFisico(DatoArbol dato, String llave) throws Exception
    {
        agregar(dato, llave);
        guardarEnArchivo();
    }
    
    public Nodo agregarRecursivo(Nodo nodoActual, Nodo nodoNuevo) throws Exception
    {
        if (nodoActual == null)
        {
            return nodoNuevo;
        }
        if (nodoNuevo.Llave.compareTo(nodoActual.Llave) == 0)
        {
            throw new Exception("El dato ya existe en el arbol");
        }
        if (nodoNuevo.Llave.compareTo(nodoActual.Llave) < 0)
        {
            nodoActual.Izquierda = agregarRecursivo(nodoActual.Izquierda, nodoNuevo);
            nodoActual.Izquierda.Padre = nodoActual;
        }
        else
        {
            nodoActual.Derecha = agregarRecursivo(nodoActual.Derecha, nodoNuevo);
            nodoActual.Derecha.Padre = nodoActual;
        }
        return nodoActual;
    }
    
    /*
    * Elimina un nodo del arbol
    */
    public void EliminarArbol(String llave) throws Exception
    {
        Nodo nodoPorBorrar = obtenerRecursivo(_raiz, llave);
        Nodo nodoPorSubir;
        
        if (nodoPorBorrar == null)
        {
            throw new Exception("No se pudo completar la operación");
        }
        if (nodoPorBorrar.Izquierda == null && nodoPorBorrar.Derecha == null)
        {
            if (nodoPorBorrar == _raiz)
            {
                _raiz = null;
            }
            else if (nodoPorBorrar == nodoPorBorrar.Padre.Izquierda)
            {
                nodoPorBorrar.Padre.Izquierda = null;
            }
            else
            {
                nodoPorBorrar.Padre.Derecha = null;
            }
            return;
        }
        if (nodoPorBorrar.Izquierda == null || nodoPorBorrar.Derecha == null)
        {
            if (nodoPorBorrar == _raiz)
            {
                if (_raiz.Izquierda != null)
                {
                    _raiz = _raiz.Izquierda;
                    _raiz.Padre = null;
                }
                else
                {
                    _raiz = _raiz.Derecha;
                    _raiz.Padre = null;
                }
            }
            else if (nodoPorBorrar.Izquierda != null)
            {
                if (nodoPorBorrar == nodoPorBorrar.Padre.Izquierda)
                {
                    nodoPorBorrar.Padre.Izquierda = nodoPorBorrar.Izquierda;
                    nodoPorBorrar.Izquierda.Padre = nodoPorBorrar.Padre;
                }
                else
                {
                    nodoPorBorrar.Padre.Derecha = nodoPorBorrar.Izquierda;
                    nodoPorBorrar.Izquierda.Padre = nodoPorBorrar.Padre;
                } 
            }
            else if(nodoPorBorrar.Derecha != null)
            {
                if (nodoPorBorrar == nodoPorBorrar.Padre.Izquierda)
                {
                    nodoPorBorrar.Padre.Izquierda = nodoPorBorrar.Derecha;
                    nodoPorBorrar.Derecha.Padre = nodoPorBorrar.Padre;
                }
                else
                {
                    nodoPorBorrar.Padre.Derecha = nodoPorBorrar.Derecha;
                    nodoPorBorrar.Derecha.Padre = nodoPorBorrar.Padre;
                }
            }

            return;
        }
        nodoPorSubir = nodoPorBorrar.Izquierda;

        EliminarArbol(nodoPorSubir.Llave);

        nodoPorBorrar.Llave = nodoPorSubir.Llave;
        nodoPorBorrar.Dato = nodoPorSubir.Dato;
    }
    
    public void Eliminar(String llave) throws Exception
    {
        EliminarArbol(llave);
        guardarEnArchivo();
    }
    
    /*
    * Cargar nodos del arbol a la RAM
    * Usa el recorrido PreOrden para recorrer el arbol
    */
    public List<DatoArbol> buscarDatos()
    {
        List<Nodo> nodos = new ArrayList();
        List<DatoArbol> datos = new ArrayList();
        recorrerPreOrdenRecursivo(_raiz, nodos);
        for (int i = 0; i < nodos.size(); i++)
        {
            for (int j = 0; j < nodos.size(); j++)
            {
                if (nodos.get(i).Llave.compareTo(nodos.get(j).Llave) == 0 && i != j)
                {
                    nodos.remove(j);
                }
            }
        }
        for (int i = 0; i < nodos.size(); i++)
        {
            datos.add(nodos.get(i).Dato);
        }
        return datos;
    }
    
    public List<Nodo> buscarNodos()
    {
        List<Nodo> nodos = new ArrayList();
        List<DatoArbol> datos = new ArrayList();
        recorrerPreOrdenRecursivo(_raiz, nodos);
        for (int i = 0; i < nodos.size(); i++)
        {
            for (int j = 0; j < nodos.size(); j++)
            {
                if (nodos.get(i).Llave.compareTo(nodos.get(j).Llave) == 0 && i != j)
                {
                    nodos.remove(j);
                }
            }
        }
        return nodos;
    }
    
    public void recorrerPreOrdenRecursivo(Nodo nodoActual, List<Nodo> nodos)
    {
        if (nodoActual == null)
        {
            return;
        }
        nodos.add(nodoActual);
        recorrerPreOrdenRecursivo(nodoActual.Izquierda, nodos);
        recorrerPreOrdenRecursivo(nodoActual.Derecha, nodos);
    }
    
    /*
    * Métodos para obtener un dato del arbol
    * Retornan un objeto DatoArbol que es el mensaje que queremos
    */
    public DatoArbol obtener(String receptor)
    {
        Nodo nodoObtenido = obtenerRecursivo(_raiz, receptor);
        return nodoObtenido.Dato;
    }
    
    public DatoArbol obtener(String emisor, String receptor)
    {
        Nodo nodoObtenido = obtenerRecursivo(_raiz, emisor, receptor);
        return nodoObtenido.Dato;
    }
    
    public Nodo obtenerRecursivo(Nodo nodoActual, String receptor)
    {
        if (nodoActual == null)
        {
            return null;
        }
        if (receptor.compareTo(nodoActual.Llave.split("\\|")[1]) > 0)
        {
            return obtenerRecursivo(nodoActual.Derecha, receptor);
        }
        if (receptor.compareTo(nodoActual.Llave.split("\\|")[1]) < 0)
        {
            return obtenerRecursivo(nodoActual.Izquierda, receptor);
        }
        return nodoActual;
    }
    
    public Nodo obtenerRecursivo(Nodo nodoActual, String emisor, String receptor)
    {
        if (nodoActual == null)
        {
            return null;
        }
        if (receptor.compareTo(nodoActual.Llave.split("\\|")[1]) > 0 && emisor.compareTo(nodoActual.Llave.split("\\|")[0]) > 0)
        {
            return obtenerRecursivo(nodoActual.Derecha, emisor, receptor);
        }
        if (receptor.compareTo(nodoActual.Llave.split("\\|")[1]) < 0 && emisor.compareTo(nodoActual.Llave.split("\\|")[0]) < 0)
        {
            return obtenerRecursivo(nodoActual.Izquierda, emisor, receptor);
        }
        return nodoActual;
    }
    
    public Nodo obtenerRecursivoEliminacion(Nodo nodoActual, String llave)
    {
        if (nodoActual == null)
        {
            return null;
        }
        if (llave.compareTo(nodoActual.Llave) > 0)
        {
            return obtenerRecursivo(nodoActual.Derecha, llave);
        }
        if (llave.compareTo(nodoActual.Llave) < 0)
        {
            return obtenerRecursivo(nodoActual.Izquierda, llave);
        }
        return nodoActual;
    }
    
    /*
    * Carga del arbol a la RAM
    * Escritura del arbol a un archivo
    */
    public void cargarArbol() throws IOException, Exception
    {
        if (!ArchivoPrincipal.exists())
        {
            throw new IOException("El archivo donde se almacenan los datos no existe.");
        }
        BufferedReader lector;
        try (FileReader leer = new FileReader(ArchivoPrincipal)) {
            lector = new BufferedReader(leer);
            String linea = lector.readLine();
            while (linea != null && !"".equals(linea) && !linea.equals(Utilidades.SaltoLinea))
            {
                agregar(new DatoArbol(linea.split("\\^")[4]), linea.split("\\^")[3]);
                linea = lector.readLine();
            }
        }
        lector.close();
    }
    
    public void guardarEnArchivo() throws IOException
    {
        if (!ArchivoPrincipal.exists())
            ArchivoPrincipal.createNewFile();
        try
        (FileWriter escribir = new FileWriter(ArchivoPrincipal); BufferedWriter escritor = new BufferedWriter(escribir)) {
            List<Nodo> nodos = buscarNodos();
            int i = 0;
            while (i < nodos.size())
            {
                escritor.write(nodos.get(i).toString() + Utilidades.SaltoLinea);
                i++;
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    /*
    * Métodos para actualizar los datos del descriptor
    * Arreglarlos, aún les falta
    */
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

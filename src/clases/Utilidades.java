/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author AxelAlejandro
 */
public class Utilidades {
    public static String SaltoLinea = System.getProperty("line.separator");
    public static Usuario usuarioActual = null;
    public static DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static int MaximoArchivoSecuencial = 5;
    public static int CantidadActualSecuencial = 0;
    public static String Separador = "|";
    public static String SeparadorArbol = "^";
    public static FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagenes", "png", "jpg", "jpeg");
    public static String RegexCorreo = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public static String datoUsuarioLista = "";
    // Colores para el diseño de los formularios
    public static Color Principal = Color.decode("#eeeeee");
    public static Color Secundario = Color.decode("#cccccc");
    public static Color Terciario = Color.decode("#1d1d1d");
    public static Color Azul = Color.decode("#73B8FF");
    public static Color AzulClaro = Color.decode("#BFDFFF");
    public static Color Rojo = Color.decode("#FB404B");
    public static Color RojoClaro = Color.decode("#FFDADC");
    public static Color Amarillo = Color.decode("#ffbc67");
    public static Color AmarilloClaro = Color.decode("#FFDEB3");
    public static Color Verde = Color.decode("#22995d");
    public static Color VerdeClaro = Color.decode("#D1FFE8");
    public static Color Negro = Color.decode("#1D1D1D");
    // Variables de la ubicacion de los archivos en el disco
    public static String DirectorioPrincipal = "C:\\MEIA\\";
    public static String ArchivoUsuarios = "C:\\MEIA\\usuarios.txt";
    public static String ArchivoDescriptorUsuarios = "C:\\MEIA\\desc_usuarios.txt";
    public static String ArchivoListasDeDistribucion = "C:\\MEIA\\temp_lista.txt";
    public static String ArchivoDescriptorListasDeDistribucion = "C:\\MEIA\\desc_temp_lista.txt";
    public static String ArchivoListasDeDistribucionSecuencial = "C:\\MEIA\\lista.txt";
    public static String ArchivoDescriptorListasDeDistribucionSecuencial = "C:\\MEIA\\desc_lista.txt";
    public static String ArchivoBitacoraBackup = "C:\\MEIA\\bitacora_backup.txt";
    public static String ArchivoDescriptorBitacoraBackup = "C:\\MEIA\\desc_bitacora_backup.txt";
    public static String RutaFotografias = "C:\\MEIA\\fotografia\\";
    public static String ArchivoPuntuacion = "C:\\MEIA\\puntuacion.txt";
    public static String ArchivoResultados = "C:\\MEIA\\restultados.txt";
    public static String ArchivoIndice = "C:\\MEIA\\lista_usuario_indice.txt";
    public static String ArchivoDescriptorIndice = "C:\\MEIA\\desc_lista_usuario_indice.txt";
    public static String ArchivoMaestro = "C:\\MEIA\\lista_usuario.txt";
    public static String ArchivoDescriptorMaestro = "C:\\MEIA\\desc_lista_usuario.txt";
    public static String ArchivoArbol = "C:\\MEIA\\correos.txt";
    public static String ArchivoDescriptorArbol = "C:\\MEIA\\desc_correo.txt";
    
    public static String fechaNormal(JDateChooser dateChooser) throws ParseException
    {
        Date date = new Date();
        String fechaString = String.valueOf(dateChooser.getDate().getDate()) + "-" +
                String.valueOf(dateChooser.getDate().getMonth() + 1) + "-" +
                String.valueOf(dateChooser.getDate().getYear() + 1900);
        SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        date = parseador.parse(fechaString);
        return formateador.format(date);
    }
    
    public static int comprobarCaracteres(String cadena, String caracteresVerificar)
    {
        int cantidad = 0;
        int i = 0;
        int j = 0;
        while (i < cadena.length())
        {
            j = 0;
            while (j < caracteresVerificar.length())
            {
                if (cadena.charAt(i) == caracteresVerificar.charAt(j))
                {
                    cantidad++;
                }
                j++;
            }
            i++;
        }
        return cantidad;
    }
    
    public static List obtenerDeArchivoContrasenia(String path) throws FileNotFoundException, IOException
    {
        String linea = "";
        List<String> lineas = new ArrayList<String>();
        // Para leer un archivo en java es necesario usar el FileReader y este se le manda de parametro al
        // BufferedReader para que funcione bien
        FileReader fileReader = new FileReader(path);
        BufferedReader buffer = new BufferedReader(fileReader);
        // Recorre las lineas del archivo y las agrega a la lista
        while ((linea = buffer.readLine()) != null)
        {
            lineas.add(linea);
        }
        // Cuando termina de leer hay que cerrar el buffer
        buffer.close();
        return lineas;
    }
    
    public static void Copia_archivos(File original, File copia){
        try{
            InputStream entrada = new FileInputStream(original);
            OutputStream salida = new FileOutputStream(copia);
            byte[] buffer = new byte[1024];
            int datos;
            while ((datos = entrada.read(buffer))> 0){
                salida.write(buffer, 0, datos);
            }
            entrada.close();
            salida.close();     
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void copiar_Directorios(File original, File copia){
        if (original.isDirectory())
        {
            if (!copia.exists())
            {
                copia.mkdir();
                System.out.println("Crando directorio" + copia.toString());
            }
            String [] archivos = original.list();
            for(int i = 0; i < archivos.length; i++)
            {
                copiar_Directorios(new File(original,archivos[i]), new File(copia,archivos[i]));
            } 
        } else {
            Copia_archivos(original,copia);   
        }
    }
    
    public static boolean comprobarCorreo(String correo)
    {
        Pattern patron = Pattern.compile(Utilidades.RegexCorreo);
        Matcher encaja = patron.matcher(correo);
        return encaja.matches();
    }
    
    public static boolean comprobarUsuario(String nombre)
    {
        ManejoArchivosApilo manejoUsuario;
        try
        {
            manejoUsuario = new ManejoArchivosApilo(new File(Utilidades.ArchivoUsuarios), new File(Utilidades.ArchivoDescriptorUsuarios));
            String dato = manejoUsuario.obtener(nombre);
            if (dato != null && !"".equals(dato))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            return false;
        }
    }
    
    public static int ContraseñaComprobar(String password, List<String> puntuacion, List<String> Resultados){
        int puntos = 0;
        if (password.length() == Utilidades.comprobarCaracteres(password, "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz"))
        {
            puntos = Integer.parseInt(Resultados.get(6));
            return 0;
        }
        else
        {
            puntos = Integer.parseInt(puntuacion.get(1)) * password.length();
            puntos = puntos + (Integer.parseInt(puntuacion.get(2)) * Utilidades.comprobarCaracteres(password, "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"));
            puntos = puntos + (Integer.parseInt(puntuacion.get(3)) + Utilidades.comprobarCaracteres(password, "ABCDEFGHIJKLMÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz"));
            puntos = puntos + (Integer.parseInt(puntuacion.get(4)) + Utilidades.comprobarCaracteres(password, "0123456789"));
            puntos = puntos + ((Integer.parseInt(puntuacion.get(5)) + password.length()) * Utilidades.comprobarCaracteres(password, "[/¿?%$#]"));   
        }
        if (puntos >= Integer.parseInt(Resultados.get(0).split(",")[0]) && puntos <= Integer.parseInt(Resultados.get(0).split(",")[1]))
        {
            return 0;
        }
        else if (puntos >= Integer.parseInt(Resultados.get(1).split(",")[0]) && puntos <= Integer.parseInt(Resultados.get(1).split(",")[1]))
        {
            return 1;
        }
        else if (puntos >= Integer.parseInt(Resultados.get(2).split(",")[0]) && puntos <= Integer.parseInt(Resultados.get(2).split(",")[1]))
        {
            return 2;
        }
        else if (puntos >= Integer.parseInt(Resultados.get(3).split(",")[0]) && puntos <= Integer.parseInt(Resultados.get(3).split(",")[1]))
        {
            return 3;
        }
        return 0;
    }
}


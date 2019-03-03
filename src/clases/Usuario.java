/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author AxelAlejandro
 */
public class Usuario extends ObjetoBase {
    public String NombreUsuario;
    public String Nombre;
    public String Apellido;
    public String Password;
    public String Rol;
    public String FechaNacimiento;
    public String CorreoAlterno;
    public String Telefono;
    public String FotoURL;
    public boolean Estatus;
    
    public Usuario(String nombreUsuario, String nombre, String apellido, String password, String rol, String fechaNacimiento,
            String correoAlterno, String telefono, String fotoURL, boolean estatus) throws NoSuchAlgorithmException, Exception
    {
        super.CantidadCampos = 10;
        NombreUsuario = nombreUsuario;
        Nombre = nombre;
        Apellido = apellido;
        Password = sha1(password);
        Rol = rol;
        FechaNacimiento = fechaNacimiento;
        CorreoAlterno = correoAlterno;
        Telefono = telefono;
        FotoURL = fotoURL;
        Estatus = estatus;
        llenarLista();
    }
    
    public Usuario(String[] datos) throws NoSuchAlgorithmException, Exception
    {
        super.CantidadCampos = 10;
        NombreUsuario = datos[0];
        Nombre = datos[1];
        Apellido = datos[2];
        Password = datos[3];
        Rol = datos[4];
        FechaNacimiento = datos[5];
        CorreoAlterno = datos[6];
        Telefono = datos[7];
        FotoURL = datos[8];
        if (Boolean.valueOf(datos[9]))
            Estatus = true;
        else
            Estatus = false;
        llenarLista();
    }
    
    public Usuario(String cadena) throws NoSuchAlgorithmException, Exception
    {
        String[] datos = cadena.split("\\|");
        super.CantidadCampos = 10;
        NombreUsuario = datos[0];
        Nombre = datos[1];
        Apellido = datos[2];
        Password = datos[3];
        Rol = datos[4];
        FechaNacimiento = datos[5];
        CorreoAlterno = datos[6];
        Telefono = datos[7];
        FotoURL = datos[8];
        if (Boolean.valueOf(datos[9]))
            Estatus = true;
        else
            Estatus = false;
        llenarLista();
    }
    
    public static String sha1(String input) throws NoSuchAlgorithmException {
        String passwordToHash = input;
        String generatedPassword = null;
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        //Add password bytes to digest
        md.update(passwordToHash.getBytes());
        //Get the hash's bytes 
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        generatedPassword = sb.toString();
        return generatedPassword;
    }
    
    @Override
    public void llenarLista() throws Exception
    {
            super.Campos = new ArrayList();
            super.Campos.add(NombreUsuario);
            super.Campos.add(Nombre);
            super.Campos.add(Apellido);
            super.Campos.add(Password);
            super.Campos.add(Rol);
            super.Campos.add(String.valueOf(FechaNacimiento));
            super.Campos.add(CorreoAlterno);
            super.Campos.add(Telefono);
            super.Campos.add(FotoURL);
            super.Campos.add(String.valueOf(Estatus));
    }
}

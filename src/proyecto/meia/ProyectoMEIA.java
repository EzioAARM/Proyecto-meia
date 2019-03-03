/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.meia;

import clases.Utilidades;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author AxelAlejandro
 */
public class ProyectoMEIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedWriter bw;
        File directorio =  new File(Utilidades.DirectorioPrincipal);
        if (!directorio.exists())
            directorio.mkdirs();
        directorio =  new File(Utilidades.RutaFotografias);
        if (!directorio.exists())
            directorio.mkdirs();
        
        directorio = new File(Utilidades.ArchivoPuntuacion);
        if (!directorio.exists())
        {
            bw = new BufferedWriter(new FileWriter(directorio));
            bw.write("6\r\n" +
                    "3\r\n" +
                    "2\r\n" +
                    "1\r\n" +
                    "2\r\n" +
                    "4\r\n" +
                    "6\r\n" +
                    "3");
            bw.close();
        }
        directorio = new File(Utilidades.ArchivoResultados);
        if (!directorio.exists())
        {
            bw = new BufferedWriter(new FileWriter(directorio));
            bw.write("0,25\r\n" +
                    "26,35\r\n" +
                    "36,50\r\n" +
                    "51,100");
            bw.close();
        }
        LoginForm login = new LoginForm();
        login.show();
    }
    
}

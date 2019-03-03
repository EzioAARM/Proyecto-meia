
package clases;

//Cada uno debe importar la clase en la que se encuentra su metodo de busqueda
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Carlos
 */
public class HiloEstatus extends Thread {
    
    //Constructor del hilo
    String Ruta;
    String Grupo;
    
    public HiloEstatus (String pRuta, String pGrupo){
        this.Ruta = pRuta;
        this.Grupo = pGrupo;
    }
    
    
    /*
    * Verifica la tabla del grupo indicado y actuiliza el estatus
    * - Cada grupo es responsable de modificar el metodo de busqueda (linea comentada)
    * - El estatus es modificada a traves de la llamada a la query actuilzarEstatus
    */
    public void run(){

        try {
            ConexionBaseDatos con = new ConexionBaseDatos();
            List<DatosBD> datos = con.obtener(this.Grupo);
            ManejoArchivosApilo apilo = new ManejoArchivosApilo(new File(Utilidades.ArchivoUsuarios), new File(Utilidades.ArchivoDescriptorUsuarios));
            boolean encontrado;
            
            for(int i=0; i<datos.size();i++){
                encontrado = apilo.obtenerExistencia(datos.get(i).Receptor); //Cada uno debe implementar el metodo de busqueda de usuario que posee
                if(encontrado){
                    con.actualizarEstatus(this.Grupo, "si", datos.get(i).Emisor, datos.get(i).Receptor, datos.get(i).FechaTransaccion);
                    Date fecha = new Date();
                    ArbolBinario arbol = new ArbolBinario(new File(Utilidades.ArchivoArbol), new File(Utilidades.ArchivoDescriptorArbol));
                    DatoArbol dato = new DatoArbol(datos.get(i).Emisor, datos.get(i).Receptor, Utilidades.formato.format(fecha), datos.get(i).Asunto,
                    datos.get(i).Asunto, datos.get(i).Adjunto, true);
                    arbol.agregarFisico(dato, datos.get(i).Emisor + "|" + datos.get(i).Receptor + "|" +Utilidades.formato.format(fecha));
                    arbol.actualizarDescriptor(1, 0, "padre^izquierda^derecha^llave^dato");
                }
                else{
                    con.actualizarEstatus(this.Grupo, "no", datos.get(i).Emisor, datos.get(i).Receptor, datos.get(i).FechaTransaccion);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloEstatus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(HiloEstatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}

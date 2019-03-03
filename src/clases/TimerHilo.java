

package clases;

import java.util.Timer;
import java.util.TimerTask;

   

/**
 *
 * @author Carlos
 */

public class TimerHilo 
{
    Thread hiloStatus;
    Timer timer = new Timer();
    boolean frozen = false;
    
    public TimerHilo(String ruta, String grupo)
    {
        hiloStatus = new HiloEstatus(ruta, grupo);
    }
    class task extends TimerTask
    {
        @Override
        public void run()
        {         
            if(!frozen)
            {
                hiloStatus.start();
            }
            
        }
    };

     /**
     * Inicializa el timer segun la periodicidad de la tarea asignada
     * los parametros recibidos llamados son timer.schedule(tarea, inicio, periodicidad)
     * -De esta forma empezamos en 1000ms y luego lanzamos la tarea cada 60000ms
     */
    public void Start()
    {
        this.timer.schedule(new task(), 1000, 60000);
    }
    
    public void Stop() 
    {
        frozen = true;
    }
         
    public void Continue() 
    {
        frozen = false;
    }
}




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import gui.CocosFrame;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

    
    
    /**
 *
 * @author cristian
 */
public class muevePac implements Runnable{
    private int delay;
    private boolean continuar=true;
    private boolean suspendFlag=true;
    private CocosFrame frame;
    private int lvl;
    long time_start = System.currentTimeMillis()/1000;
    long referencia=100;
    long referencianivel=System.currentTimeMillis()/1000;
       
    /**
     * Constructor de la clase, que inicializa la referencia utilizadas por
     * la hebra al TetrisMidlet, establece el retardo en milisegundos
     * entre movimiento y movimiento de la Figura actual, y comienza a ejecutar
     * la hebra. 
     */
    public muevePac(CocosFrame fr,int nivel){
        frame=fr;
        lvl=nivel;
        delay= actualizaRetardo(nivel);
        Thread t=new Thread(this);
        t.start();
    }
    
    /**
     * C�digo que constituye las sentencias de la hebra. En este caso, se encarga
     * de mover al pacman cada cierto tiempo (delay), mientras tengamos vidas. Aparte
     * se incluye el código (comentado por peor funcionamiento) que permitiría hacer
     * un movimiento más uniforme del pac (en vez de ir celda a celda). 
     */
    public void run(){
           int contador2=0;
            try {
                 while(continuar){
                synchronized(this){
                    while(suspendFlag){
                        wait();
                    }
                }
                if(frame.getRejilla().lose()){
                    frame.reiniciar();
                    frame.getPac().restarVida();
                    Clip sonido;
                    try {
                        sonido = AudioSystem.getClip();
                        sonido.open(AudioSystem.getAudioInputStream(new File("pacman_death.wav")));       
                        sonido.start();
                        
                    } catch (            LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                        Logger.getLogger(muevePac.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                    
                }
                
                if(frame.getPac().getVidas()<=0) {parar();}
                Thread.sleep(delay);
                if(frame.getRejilla().levelComplete()){frame.aumentarNivel();}
                
                
                /**
                 * El siguiente código se usaría para el movimiento en cuatro, lo que se haría
                 * sería añadir una variable iteración al pac que controlara la iteración.
                 * Además repaint llamaría entonces a la función paintPac y no a la dibujaPac.
                 */
                int mov=frame.getRejilla().getMovimiento(); 
               /*while(contador2<3 && frame.getRejilla().choque(frame.getPac(), mov)){
                    
                    Thread.sleep(delay);
                   
                   frame.getPac().setIteracion(contador2);
                   frame.repaint();
                contador2++;
                }*/
                if(frame.getRejilla().choque(frame.getPac(), mov))
                frame.getRejilla().muevePack(mov,frame);
                frame.repaint();
                frame.setRecord(frame.getRejilla().getPuntuacion());
                //System.out.println(""+frame.getPac().getYOrigen());
                
                //if(!frame.getRejilla().choque(frame.getPac(),mov))Thread.sleep(delay*4);
                contador2=0;
                
               
                //Para los fantasmas a la hora de comer.
                frame.getRejilla().copiaPac(frame.getPac());
                
                /**
                 * El pacman se encarga de controlar la muestra de vidas, puntuación y tiempo.
                 */
                frame.setVidas(frame.getPac().getVidas());
                frame.setPuntuacion(frame.getRejilla().getPuntuacion());
                frame.setTime(System.currentTimeMillis()/1000-time_start);
                if(referencia-(System.currentTimeMillis()/1000-referencianivel)>0)
                frame.setRestante(referencia-(System.currentTimeMillis()/1000-referencianivel));
                else 
                frame.setRestante(0);
                
            } 
            }catch (InterruptedException ex) {
                Logger.getLogger(muevePac.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (IOException ex) {
            Logger.getLogger(muevePac.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } 
        
        

    
/**
     * Detiene momentaneamente la ejecución de la hebra, haciendo que la Figura actual
     * quede parada.
     */
    synchronized  public void suspender(){
        //frame.getPanel().repaint();
        suspendFlag=true;
    }
    
    /**
     * Reanuda el movimiento de la hebra. La Figura actual vuelve  a moverse.
     */
    public synchronized void reanudar(){
       // if(frame.getPanel()!=null)
         //   frame.getPanel().repaint();
        suspendFlag = false;
        notify();
    }
    
    /**
     * Termina la ejecución de la hebra.
     */
    public void parar(){
        continuar=false;
    }
    
    /**
     * Nos dice si la hebra está o no parada.
     * @return true si la hebra de movimiento está parada, false en otro caso
     */
    synchronized public boolean getParado(){
        return suspendFlag;
    }
    
    /**
     * La siguiente función actualiza el retardo que espera la hebra
     * para mover la Figura actual. El nivel más lento será
     * el 0 (retardo 700) y el más rápido el 10 (retardo 50)
     */
    private int actualizaRetardo(int nivel) {
        if (nivel>10) nivel=10;
        else if (nivel<0) nivel=0;
        return ( 400-(nivel*35) );
    }
    
    /**
     * Añadimos un nivel, con lo cual el retardo se hace menor.
     * Además añadimos puntos en función del tiempo restante que nos ha sobrado, y 
     * ponemos la referencia de tiempo para el siguiente nivel a 0.
     * @param nivel 
     */
    
    public void addLevel(int nivel){
        delay=actualizaRetardo(nivel);
        if(referencia-(referencianivel-System.currentTimeMillis()/1000)>0)
        frame.getRejilla().setPuntuacion(frame.getRejilla().getPuntuacion()+
                (int)(referencianivel-System.currentTimeMillis()/1000)*nivel);
        referencianivel=System.currentTimeMillis()/1000;
        
    }
    
    /**
     * Ponemos el valor del tiempo de inicio al actual.
     */
    
    public void restartTime(){
        time_start = System.currentTimeMillis()/1000;
    }
    
    public void iniciar(){
         time_start = System.currentTimeMillis()/1000;
         referencianivel=System.currentTimeMillis()/1000;
    }
}

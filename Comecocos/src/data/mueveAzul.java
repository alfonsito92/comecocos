/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import gui.CocosFrame;
import java.io.File;
import java.io.IOException;
import java.util.Random;
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
public class mueveAzul implements Runnable {
    private int delay;
    private boolean continuar=true;
    private boolean suspendFlag=true;
    private CocosFrame frame;
    private int lvl;
    long time_start = System.currentTimeMillis()/1000;
       
    /**
     * Constructor de la clase, que inicializa la referencia utilizadas por
     * la hebra al TetrisMidlet, establece el retardo en milisegundos
     * entre movimiento y movimiento de la Figura actual, y comienza a ejecutar
     * la hebra. 
     */
    public mueveAzul(CocosFrame fr,int nivel){
        frame=fr;
        lvl=nivel;
        delay= actualizaRetardo(nivel);
        Thread t=new Thread(this);
        t.start();
    }
    
    /**
     * C�digo que constituye las sentencias de la hebra. En este caso, se encarga
     * de hacer que se muevan continuamente la Serpiente
     * y los Ratones. La hebra se encarga tambi�n de ir refrescando la pantalla
     * d�nde se dibuja todo, y los puntos acumulados. Adem�s controla si
     * la Serpiente choca contra un muro o contra s� misma, para comenzar
     * el juego de nuevo. Cuando la Serpiente come un Raton aumenta su longitud
     * en una celda.
     */
    public void run(){
           int contador2=0;
           Random aleatorio =new Random();
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
                frame.getAzul().setChoque(true);
                Pac p=frame.getPac();
                
                int contador=0;
                
                if(contador2<3){
                    frame.getRejilla().mueveFantasma(3,frame.getAzul(),p);
                    contador2++;
                }else if(contador2<6){
                    frame.getRejilla().mueveFantasma(0,frame.getAzul(),p);
                    contador2++;
                } else if(frame.getRejilla().getEstado()){{
                
                while(frame.getAzul().getChoque()&&contador<10){
                if(frame.getPac().getXOrigen()-frame.getAzul().getXOrigen()<=0){
                    if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()<=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*3,frame.getAzul(),p);
                else if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()>=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*2,frame.getAzul(),p);
                }else{ if (frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()<=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*2+1,frame.getAzul(),p);
                else if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()>=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)+1,frame.getAzul(),p);
                }
                
                if(contador>5){
                    if(frame.getPac().getXOrigen()-frame.getAzul().getXOrigen()>=0){
                    if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()>=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*3,frame.getAzul(),p);
                else if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()<=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*2,frame.getAzul(),p);
                }else{ if (frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()>=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*2+1,frame.getAzul(),p);
                else if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()<=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)+1,frame.getAzul(),p);
                }
                }
                contador++;
                }}}
                
                else{
                 while(frame.getAzul().getChoque()&&contador<10){
                     if(frame.getPac().getXOrigen()-frame.getAzul().getXOrigen()>=0){
                    if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()>=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*3,frame.getAzul(),p);
                else if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()<=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*2,frame.getAzul(),p);
                }else{ if (frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()>=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*2+1,frame.getAzul(),p);
                else if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()<=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)+1,frame.getAzul(),p);
                
                }
                
                if(contador>5){
                    if(frame.getPac().getXOrigen()-frame.getAzul().getXOrigen()<=0){
                    if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()<=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*3,frame.getAzul(),p);
                else if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()>=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*2,frame.getAzul(),p);
                }else{ if (frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()<=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)*2+1,frame.getAzul(),p);
                else if(frame.getPac().getYOrigen()-frame.getAzul().getYOrigen()>=0)
                        frame.getRejilla().mueveFantasma(aleatorio.nextInt(2)+1,frame.getAzul(),p);
                }
                }
                contador++;
                 }
                }
                
                
                
            } 
            }catch (InterruptedException ex) {
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
    
    public void addLevel(int nivel){
        delay=actualizaRetardo(nivel);
    }
    
    public void restartTime(){
        time_start = System.currentTimeMillis()/1000;
    }   
}

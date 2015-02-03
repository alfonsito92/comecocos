package data;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *Lo siguiente es una hebra que se lanza y tiene como objetivo reproducir un sonido.
 * @author cristian y rafa
 */

public class Sonido implements Runnable{
    
    public Sonido(){
    Thread t=new Thread(this);
        t.start();
    }
    
    public void run(){
      
        while(true){
        try {
            
            Clip sonido=AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File("pacman_beginning.wav")));       
            sonido.start();
            Thread.sleep(4800);
            sonido.close();
            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File("pacman_chomp.wav")));
            
            // Comienza la reproducción
            sonido.start();
            Thread.sleep(900);
            sonido.close();
         }
         catch (Exception e) {
            System.out.println("" + e);
        }
    }
    }
    
}

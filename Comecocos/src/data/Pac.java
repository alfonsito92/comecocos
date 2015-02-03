package data;

/**
 *
 * @author cristian y rafa
 */
public class Pac {
    public static final int IZQUIERDA         = 0;
    public static final int DERECHA           = 1;
    public static final int ABAJO             = 2;
    public static final int ARRIBA            = 3;
   
    private int xorigen=1;
    private int yorigen=14;
    private int vidas=3;
    private boolean boca=false;
    private int iteracion=0;
    
    /**
     * La función mueve se encarga de modificar la posición del pac
     * @param direccion indica hacia donde se moverá el pac
     */
    public void mueve(int direccion){
        if(direccion==ABAJO){
            yorigen++;;
        }else if(direccion==IZQUIERDA){
            xorigen--;
        }else if(direccion==DERECHA){
            xorigen++;
        }else if(direccion==ARRIBA){
            yorigen--;
        }
    }
    
    
     /**
     * Obtiene la posición x respecto al origen de coordenadas de la Rejilla de la Figura actual
     * @return la posición x respecto al origen de coordenadas de la Rejilla de la Figura actual
     */
    public int getXOrigen(){
        return xorigen;
    }
 
    /**
     * Obtiene la posición y respecto al origen de coordenadas de la Rejilla de la Figura actual
     * @return la posición y respecto al origen de coordenadas de la Rejilla de la Figura actual
     */
    public int getYOrigen(){
        return yorigen;
    }
    
    /**
     * Mueve la posición x a una posición dada.
     * @param x nueva posición x.
     */
    
    public void setXOrigen(int x){
        xorigen=x;
    }
    
    /**
     * Mueve la posición y a una posición dada.
     * @param y nueva posición y.
     */
    
    public void setYOrigen(int y){
        yorigen=y;
    }
    
    /**
     * Se resta una vida a las disponibles para el pac
     */
    
    public void restarVida(){
        vidas--;
    }
    
    /**
     * Se elige el número de vidas, útil cuando se reinicia el juego.
     * @param v 
     */
    
    public void setVidas(int v){
        vidas=v;
    }
    
    /**
     * Obtiene el número de vidas restantes
     * @return el número de vidas restantes
     */
    
    public int getVidas(){
        return vidas;
    }
    
    /**
     * Obtiene la posición de la boca, true abierta, false cerra
     * @return la posición de la boca
     */
    
    public boolean getBoca(){
        return boca;
    }
    
    /**
     * Cambia el estado de la boca
     * @param b nuevo estado de la boca del Pac
     */
    
    public void setBoca(boolean b){
        boca=b;
    }
    
    /**
     * En caso de establecer los cuatro movimientos en 1, controlaría la iteración actual
     * @param i iteración
     */
    
    public void setIteracion(int i){
        iteracion=i;
    }
    
    /**
     * Obtiene la iteración actual
     * @return iteración
     */
    
    public int getIteracion(){
        return iteracion;
    }
    
    /**
     *Función que se usa para devolver al pac sus datos originales. 
     */
    
    public void reiniciarPac(){
     xorigen=1;
     yorigen=14;
     vidas=3;
     boca=false;
     iteracion=0;
    }
    
}


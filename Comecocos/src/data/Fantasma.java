package data;


/**
 *
 * @author cristian y rafa
 */
public class Fantasma {

    public static final int IZQUIERDA         = 0;
    public static final int DERECHA           = 1;
    public static final int ABAJO             = 2;
    public static final int ARRIBA            = 3;
    
    private static final int AZUL=6;
    private static final int ROJO=5;
    private static final int ROSA=7;
    private static final int NARANJA=8;
    
    private int tipo;
    private boolean choque=true;
    
    private int xorigen;
    private int yorigen;
    private int ant=Rejilla.V;
    
    /**
     * La función mueve se encarga de modificar la posición del fantasma
     * @param direccion indica hacia donde se moverá el fantasma
     */
    public void mueve(int direccion){
        if(direccion==ABAJO){
            yorigen++;
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
     * Selecciona el tipo de fantasma (De entre los cuatro disponibles)
     * @param t tipo de fantasma.
     */
    public void setTipo(int t){
        tipo=t;
    }
    
    /**
     * Obtiene el tipo de fanstama
     * @return el tipo de fantasma
     */
    public int getTipo(){
        return tipo;
    }
    /**
     * Función para devolver el tiepo de celda que había antes de que pasase el 
     * fantasma
     * @return el tipo de celda.
     */
    public int getAnt(){
        return ant;
    }
    /**
     * Función para almacenar el tipo de celda por el que acaba de pasar un fantasma
     * No se pueden guardar ni Pac ni bloques
     * @param a el tipo de celda anterior
     */
    public void setAnt(int a){
        if(a==2||a==1||a==4){
            ant=a;
        }
    }
    
    /**
     * Función que devuelve si un fanstama se choca
     * @return  false o true
     */
    
    public boolean getChoque(){
        return choque;
    }
    /**
     * Para almacenar si el fantasma choca o no
     * @param cho 
     */
    public void setChoque(boolean cho){
        choque=cho;
    }

    
    
}


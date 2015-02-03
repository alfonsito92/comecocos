package data;

import gui.CocosFrame;
import java.util.Random;

/**
 *
 * @author cristian y rafa
 */
public class Rejilla {
    
    public static final int B=0;
    public static final int C=1;
    public static final int V=2;
    public static final int P=3;
    public static final int E=4;
    public static final int FA=5;
    public static final int FR=6;
    public static final int FN=8;
    public static final int FP=7;
    public static final int BDI=9;
    public static final int BDR=10;
    public static final int BUI=11;
    public static final int BUR=12;
    
    private int altura;
    private int anchura;
    private int movimiento=-1;
    private int cocos=0;
    private int numCocos=100;
    private int puntuacion=0;
    private boolean fantasmas=true;
    private boolean lose=false;
    private int contador=30;
   
    private boolean primera=false;
    private boolean condicion=false;
    private int[][] celdas;
    private boolean choque=true;
    
    /**
     * Crea espacio para una rejilla con anchura igual a w  y altura
     * igual a h.
     * @param w anchura de la nueva Rejilla
     * @param h altura de la nueva Rejilla
     */
    public Rejilla(int w,int h){
        anchura=w;
        altura=h;
        celdas= new int[anchura][altura];
        initRejilla();
    }
    
    /**
     * Devuelve la anchura de la rejilla.
     * @return la anchura de la rejilla
     */
    public int getAnchura(){
        return anchura;
    }
    
    /**
     * Devuelve la altura de la rejilla.
     * @return la altura de la rejilla
     */
    public int getAltura(){
        return altura;
    }
       
    /**
     * Establece el tipo de celda en las coordenadas x e y de esta Rejilla
     *  @param x coordenada x (columna)
     * @param y coordenada y (fila)
     * @param valor el tipo de celda 
     */
    public void assignTipoCelda(int x,int y,int valor){
        celdas[x][y]= valor ;
    }
    
    /**
     * Obtiene el tipo de celda en las coordenadas x e y de esta Rejilla
     * @param x coordenada x (columna)
     * @param y coordenada y (fila)
     * @return el tipo de Celda en la coordenada x,y.
     */
    
    public int getTipoCelda(int x,int y){
        return celdas[x][y];
    }
    /**
     * Inicializa la Rejilla con los valores definidos por el usuario.
     * Por la definición para que se muestre correctamente se debe trasponer.
     * Además se añaden de forma aleatorio cuatro celdas especiales.
     * Por simplicidad se trabaja con celdas que son del tipo fantasma para comparar
     * facilmente si hay celdas con fantasmas a las que no se puedan mover estos.
     * El pac trabaja con su posición x e y
     */
    public void initRejilla(){
      int aux[][]={ 
      {B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B},
      {B,C,C,C,C,C,C,C,C,C,C,C,C,B,B,C,C,C,C,C,C,C,C,C,C,C,C,B},
      {B,C,BUI,B,B,BUR,C,BUI,B,B,B,BUR,C,B,B,C,BUI,B,B,B,BUR,C,BUI,B,B,BUR,C,B},
      {B,C,B,V,V,B,C,B,V,V,V,B,C,B,B,C,B,V,V,V,B,C,B,V,V,B,C,B},
      {B,C,BDI,B,B,BDR,C,BDI,B,B,B,BDR,C,BDI,BDR,C,BDI,B,B,B,BDR,C,BDI,B,B,BDR,C,B},
      {B,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,B},
      {B,C,BUI,B,B,BUR,C,BUI,BUR,C,BUI,B,B,B,B,B,B,BUR,C,BUI,BUR,C,BUI,B,B,BUR,C,B},
      {B,C,BDI,B,B,BDR,C,B,B,C,BDI,B,B,B,B,B,B,BDR,C,B,B,C,BDI,B,B,BDR,C,B},
      {B,C,C,C,C,C,C,B,B,C,C,C,C,B,B,C,C,C,C,B,B,C,C,C,C,C,C,B},
      {B,B,B,B,B,BUR,C,B,B,B,B,BUR,V,B,B,V,BUI,B,B,B,B,C,BUI,B,B,B,B,B},
      {V,V,V,V,V,B,C,B,B,B,B,BDR,V,BDI,BDR,V,BDI,B,B,B,B,C,B,V,V,V,V,V},
      {V,V,V,V,V,B,C,B,B,V,V,V,V,V,V,V,V,V,V,B,B,C,B,V,V,V,V,V},
      {V,V,V,V,V,B,C,B,B,V,BUI,B,V,V,V,V,B,BUR,V,B,B,C,B,V,V,V,V,V},
      {B,B,B,B,B,BDR,C,BDI,BDR,V,B,V,V,V,V,V,V,B,V,BDI,BDR,C,BDI,B,B,B,B,B},
      {V,V,V,V,V,V,C,V,V,V,B,V,FR,FA,FP,FN,V,B,V,V,V,C,V,V,V,V,V,V},
      {B,B,B,B,B,BUR,C,BUI,BUR,V,B,V,V,V,V,V,V,B,V,BUI,BUR,C,BUI,B,B,B,B,B},
      {V,V,V,V,V,B,C,B,B,V,BDI,B,B,B,B,B,B,BDR,V,B,B,C,B,V,V,V,V,V},
      {V,V,V,V,V,B,C,B,B,V,V,V,V,V,V,V,V,V,V,B,B,C,B,V,V,V,V,V},
      {V,V,V,V,V,B,C,B,B,V,BUI,B,B,B,B,B,B,BUR,V,B,B,C,B,V,V,V,V,V},
      {B,B,B,B,B,BDR,C,BDI,BDR,V,BDI,B,B,B,B,B,B,BDR,V,BDI,BDR,C,BDI,B,B,B,B,B},
      {B,C,C,C,C,C,C,C,C,C,C,C,C,B,B,C,C,C,C,C,C,C,C,C,C,C,C,B},
      {B,C,BUI,B,B,BUR,C,BUI,B,B,B,BUR,C,B,B,C,BUI,B,B,B,BUR,C,BUI,B,B,BUR,C,B},
      {B,C,BDI,B,B,B,C,BDI,B,B,B,BDR,C,BDI,BDR,C,BDI,B,B,B,BDR,C,B,B,B,BDR,C,B},
      {B,C,C,C,B,B,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,B,B,C,C,C,B},
      {B,B,BUR,C,B,B,C,BUI,BUR,C,BUI,B,B,B,B,B,B,BUR,C,BUI,BUR,C,B,B,C,BUI,B,B},
      {B,B,BDR,C,BDI,BDR,C,B,B,C,BDI,B,B,B,B,B,B,BDR,C,B,B,C,BDI,BDR,C,BDI,B,B},
      {B,C,C,C,C,C,C,B,B,C,C,C,C,B,B,C,C,C,C,B,B,C,C,C,C,C,C,B},
      {B,C,BUI,B,B,B,B,B,B,B,B,BUR,C,B,B,C,BUI,B,B,B,B,B,B,B,B,BUR,C,B},
      {B,C,BDI,B,B,B,B,B,B,B,B,BDR,C,BDI,BDR,C,BDI,B,B,B,B,B,B,B,B,BDR,C,B},
      {B,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,C,B},
      {B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B,B}
        };
      int traspuesta[][]=new int[28][31];
      for (int i=0; i< 31;i++)
      for(int j=0; j< 28;j++)
      traspuesta[j][i]=aux[i][j];
      
      int i;
      int j;
      
      
      for(int c=0;c<4;c++){
        Random aleatorio=new Random();
        i=aleatorio.nextInt(28);
        j=aleatorio.nextInt(31);
            
        while(traspuesta[i][j]==B || traspuesta[i][j]==V ||traspuesta[i][j]==E
                || traspuesta[i][j]==BUI || traspuesta[i][j]==BUR || traspuesta[i][j]==BDI || traspuesta[i][j]==BDR){
            i=aleatorio.nextInt(28);
            j=aleatorio.nextInt(31);
        }
      traspuesta[i][j]=E; 
      }
        celdas=traspuesta;
    }
    
    /**
     * Función que mueve la posición del pac, limpiando la celda anterior.
     * El pac solo se mueve a celdas vacias o con cocos, en caso de ir a una celda
     * ocupada por un fantasma se pierde una vida y se vuelve a colocar en la posición
     * inicial cuando los fantasmas te persiguen, si no los comerá.
     * @param direccion lugar de destino del pac
     * @param pac el objeto pac que contiene la xorigen e yorigen.
     */
    
    public void muevePack(int direccion, CocosFrame frame){
        
        //Condiciones para que en los primeros movimientos se cambien los fantasmas por celdas vacias
        if(!primera){
            primera=true;
            
            celdas[12][14]=V;
            celdas[13][14]=V;
            celdas[14][14]=V;
            celdas[15][14]=V;
        }
        
        if(movimiento!=-1)celdas[1][14]=V;
        
        if(!fantasmas){
            celdas[12][12]=B;
            celdas[13][12]=B;
            celdas[14][12]=B;
            celdas[15][12]=B;
            condicion=false;
        } else if(!condicion){
            celdas[12][12]=V;
            celdas[13][12]=V;
            celdas[14][12]=V;
            celdas[15][12]=V;
            condicion=true;
        }
        
        /*En las siguientes condiciones se trabaja con que el movimiento del pac
         * no se mueva a una casilla de bloque o fantasmas, además se suman las puntuaciones
         * y el número de cocos cocos comidos para subir de nivel. También se realiza 
         * el movimiento del pac si fuese posible. 
         * La variable contador se usa para controlar el número de movimientos hasta que
         * los fantasmas vuelven al estado original de persecución del pac.
         */
        Pac pac = frame.getPac();
        int x=pac.getXOrigen();
        int y=pac.getYOrigen();
        
        if(direccion==0){
           if(choque(pac,direccion)){
               if(x==1){
                   celdas[x][y]=V;                   
                    pac.setXOrigen(27);
                    pac.setYOrigen(14);
               }else{
                   if(celdas[x-1][y]!=V){cocos++;}
                   if(muere(frame) && fantasmas)
                       lose=true;
                   else if(celdas[x-1][y]==C)puntuacion+=10;
                else if (celdas[x-1][y]==E){puntuacion+=50;fantasmas=false;contador=0;}
            celdas[x][y]=V;
            pac.mueve(0);
           }
           }
        }
        if(direccion==1){
             if(choque(pac,direccion)){
           if(x==26){
                   celdas[x][y]=V;
                    pac.setXOrigen(0);
                    pac.setYOrigen(14);
                    choque=true;
               }else{
               if(celdas[x+1][y]!=V){cocos++;}
               if(muere(frame) && fantasmas)
                   lose=true;
                 else if(celdas[x+1][y]==C)puntuacion+=10;
                else if(celdas[x+1][y]==E){puntuacion+=50;fantasmas=false;contador=0;}
                                          
            celdas[x][y]=V;
            pac.mueve(1);
           }
           }
        }
        if(direccion==2){
            if(choque(pac,direccion)){
                if(celdas[x][y+1]!=V){cocos++;}
                if(muere(frame) && fantasmas)
                       lose=true;
                else if(celdas[x][y+1]==C)puntuacion+=10;
                else if (celdas[x][y+1]==E){puntuacion+=50;fantasmas=false;contador=0;}
                
            celdas[x][y]=V;
            pac.mueve(2);
        }
        }
        if(direccion==3){
            if(choque(pac,direccion)){
                if(celdas[x][y-1]!=V){cocos++;}
                if(muere(frame) && fantasmas)
                       lose=true;
                else if(celdas[x][y-1]==C)puntuacion+=10;
                else if(celdas[x][y-1]==E) {puntuacion+=50;fantasmas=false;contador=0;}}
           
            celdas[x][y]=V;
            pac.mueve(3);
            
        }
        if(pac.getXOrigen()!=0 && pac.getXOrigen()!=27){
        celdas[0][14]=V;
        celdas[27][14]=V;
        }
        
        contador++;
        
        if(contador>30)fantasmas=true;
        
    }
    
    /**
     * Existe una variable que almacena el movimiento que ha realizado el pac.
     * @param mov nuevo valor de movimiento del pac
     */
    
    public void setMovimiento(int mov){
        movimiento=mov;
    }
    
    /**
     * Obtiene el movimiento del pac
     * @return movimiento del pac
     */
    
    public int getMovimiento(){
        return movimiento;
    }
    
    /**
     * Obtiene el número de cocos consumidos
     * @return número de cocos.
     */
    
    public int getCocos(){
        return cocos;
    }
    
    /**
     * Para devolver el número de cocos a su valor original.
     * @param coc el nuevo número de cocos consumidos.
     */
    
    public void setCocos(int coc){
        cocos=coc;
    }
    
    /**
     * Devuelve true si se han consumido todos los cocos necesarios.
     * @return la condición.
     */
    
    public boolean levelComplete(){
        if(cocos>=numCocos){
            cocos=0;
        return true;
        }
        else return false;
    }
    
    /**
     * Devuelve el número de puntos conseguidos
     * @return el número de puntos
     */
    
    public int getPuntuacion(){
        return puntuacion;
    }
    
    /**
     * Para inicializar la puntuación
     * @param p nueva puntuación.
     */
    
    public void setPuntuacion(int p){
        puntuacion=p;
    }
    
    /**
     * Función muy parecida a la de muevePac solo que ahora para Fantasmas. 
     * @param direccion dirección del movimiento.
     * @param fan fantasma que se ha de mover.
     */
    
    public synchronized void mueveFantasma(int direccion,Fantasma fan,Pac p){
        int x=fan.getXOrigen();
        int y=fan.getYOrigen();
        int tipo=fan.getTipo();
        
        int ant=fan.getAnt();
        if(direccion==0){
           if(celdas[x-1][y]!=B && celdas[x-1][y]!=BUI && celdas[x-1][y]!=BUR && celdas[x-1][y]!=BDI &&celdas[x-1][y]!=BDR && (celdas[x-1][y]!=FA && celdas[x-1][y]!=FR
                    && celdas[x-1][y]!=FP && celdas[x-1][y]!=FN)){
               if(x==1){
               }else{
                   
            celdas[x][y]=ant;
            if(celdas[x-1][y]==P && fantasmas){lose=true;}
            else if(celdas[x-1][y]==P && !fantasmas) {
                fan.setAnt(2);
                fan.setXOrigen(7+fan.getTipo());fan.setYOrigen(14);
            puntuacion+=200;
            
            }
            fan.setAnt(celdas[x-1][y]);
            celdas[x-1][y]=tipo;
            fan.mueve(0);
            fan.setChoque(false);}
           }
        }
        if(direccion==1){
             if(celdas[x+1][y]!=B&& celdas[x+1][y]!=BUI && celdas[x+1][y]!=BUR && celdas[x+1][y]!=BDI &&celdas[x+1][y]!=BDR 
                     && (celdas[x+1][y]!=FA && celdas[x+1][y]!=FR
                    && celdas[x+1][y]!=FP && celdas[x+1][y]!=FN)){
           if(x==26){}
           else{
               
            celdas[x][y]=ant;
            if(celdas[x+1][y]==P && fantasmas){lose=true;}
            else if(celdas[x+1][y]==P && !fantasmas){
                fan.setAnt(2);fan.setXOrigen(7+fan.getTipo());fan.setYOrigen(14);
            puntuacion+=200;
            
            }
            fan.setAnt(celdas[x+1][y]);
            celdas[x+1][y]=tipo;
            fan.mueve(1);
            fan.setChoque(false);
             }
           }
        }
        if(direccion==2){
            if(celdas[x][y+1]!=B && celdas[x][y+1]!=BUI && celdas[x][y+1]!=BUR && celdas[x][y+1]!=BDI &&celdas[x][y+1]!=BDR 
                    && (celdas[x][y+1]!=FA && celdas[x][y+1]!=FR
                    && celdas[x][y+1]!=FP && celdas[x][y+1]!=FN)){           
            celdas[x][y]=ant;
            if(celdas[x][y+1]==P && fantasmas){lose=true;}
            else if(celdas[x][y+1]==P && !fantasmas) {
                fan.setAnt(2);
                fan.setXOrigen(7+fan.getTipo());fan.setYOrigen(14);
            puntuacion+=200;
            
            }
            fan.setAnt(celdas[x][y+1]);
            celdas[x][y+1]=tipo;
            fan.mueve(2);
            fan.setChoque(false);
            }
        }
        if(direccion==3){
            if(celdas[x][y-1]!=B && celdas[x][y-1]!=BUI && celdas[x][y-1]!=BUR && celdas[x][y-1]!=BDI &&celdas[x][y-1]!=BDR
                    && (celdas[x][y-1]!=FA && celdas[x][y-1]!=FR
                    && celdas[x][y-1]!=FP && celdas[x][y-1]!=FN)){
           
            celdas[x][y]=ant;
            if(celdas[x][y-1]==P && fantasmas){lose=true;}
            else if(celdas[x][y-1]==P && !fantasmas) {
                fan.setAnt(2);
                fan.setXOrigen(7+fan.getTipo());fan.setYOrigen(14);
            puntuacion+=200;
            
            }
            fan.setAnt(celdas[x][y+1]);
            celdas[x][y-1]=tipo;
            fan.mueve(3);
            fan.setChoque(false);
            }
        }  
    }
    
    /**
     * Devuelve la condición de derrota. 
     * @return true en caso de derrota (valor que se actualiza en muevePac.
     */
    
    public boolean lose(){
        return lose;
    }
    
    /**
     * Para elegir el valor de lose.
     * @param r nuevo valor de lose.
     */
    
    public void setLose(boolean r){
        lose=false;
    }
    
    /**
     * Devuelve el estado actual. (Pac persigue a fan, o fantasmas a pac).
     * @return estado actual.
     */
    
    public boolean getEstado(){
        return fantasmas;
    }
    
    /**
     *En los primeros movimientos existen condiciones especiales. Estas condiciones
     * se cumplen gracias a la variable booleana primera.
     */
    
    public void setPrimera(){
        primera=false;
    }
    
    /**
     * Comprueba si el pacman se chocará.
     * @param p el pacman
     * @param dir dirección
     * @return  true si no se choca, false si si se choca.
     */
    
    public boolean choque(Pac p,int dir){
        boolean c=false;
        int x=p.getXOrigen();
        int y=p.getYOrigen();
        
        if(dir==0){
        if(celdas[x-1][y]!=B && celdas[x-1][y]!=BUI && celdas[x-1][y]!=BUR && celdas[x-1][y]!=BDI &&celdas[x-1][y]!=BDR)
            c=true;
        }else if(dir==1){
        if(celdas[x+1][y]!=B&& celdas[x+1][y]!=BUI && celdas[x+1][y]!=BUR && celdas[x+1][y]!=BDI &&celdas[x+1][y]!=BDR)        
            c=true;
        }else if(dir==2){
        if(celdas[x][y+1]!=B && celdas[x][y+1]!=BUI && celdas[x][y+1]!=BUR && celdas[x][y+1]!=BDI &&celdas[x][y+1]!=BDR)
            c=true;
        }else if(dir==3){
        if(celdas[x][y-1]!=B && celdas[x][y-1]!=BUI && celdas[x][y-1]!=BUR && celdas[x][y-1]!=BDI &&celdas[x][y-1]!=BDR)
            c=true;             
        }
    
        return c;
    }
    
    /**
     * Comprueba si un fantasma se chocará
     * @param f fantasma
     * @param dir dirección
     * @return true si no se choca, false para lo contrario.
     */
    
    public boolean fanChoque(Fantasma f, int dir){
     boolean c=false;
        int x=f.getXOrigen();
        int y=f.getYOrigen();
        
        if(dir==0){
        if(celdas[x-1][y]!=B && celdas[x-1][y]!=BUI && celdas[x-1][y]!=BUR && celdas[x-1][y]!=BDI &&celdas[x-1][y]!=BDR)
            c=true;
        }else if(dir==1){
        if(celdas[x+1][y]!=B&& celdas[x+1][y]!=BUI && celdas[x+1][y]!=BUR && celdas[x+1][y]!=BDI &&celdas[x+1][y]!=BDR)        
            c=true;
        }else if(dir==2){
        if(celdas[x][y+1]!=B && celdas[x][y+1]!=BUI && celdas[x][y+1]!=BUR && celdas[x][y+1]!=BDI &&celdas[x][y+1]!=BDR)
            c=true;
        }else if(dir==3){
        if(celdas[x][y-1]!=B && celdas[x][y-1]!=BUI && celdas[x][y-1]!=BUR && celdas[x][y-1]!=BDI &&celdas[x][y-1]!=BDR)
            c=true;             
        }
    
        return c;
    }
    
    /**
     * Cambia el tipo de casilla por una tipo P en las coordenadas del pac
     * @param pac pacman
     */
    
    public void copiaPac(Pac pac){
        int x=pac.getXOrigen();
        int y=pac.getYOrigen();
        
        celdas[x][y]=P;
    }
    
    private boolean muere (CocosFrame frame){
        
        int xpac=frame.getPac().getXOrigen();
        int ypac=frame.getPac().getYOrigen();
        int xrojo=frame.getRojo().getXOrigen();
        int yrojo=frame.getRojo().getYOrigen();
        int xazul=frame.getAzul().getXOrigen();
        int yazul=frame.getAzul().getYOrigen();
        int xrosa=frame.getRosa().getXOrigen();
        int yrosa=frame.getRosa().getYOrigen();
        int xnaranja=frame.getNaranja().getXOrigen();
        int ynaranja=frame.getNaranja().getYOrigen();
        
        if((xpac==xrojo && ypac==yrojo) || (xpac==xazul && ypac==yazul) || (xpac==xrosa && ypac==yrosa)||
                (xpac==xnaranja && ypac==ynaranja) ) return true;
        else return false;
    }
    
 }
    
    

    


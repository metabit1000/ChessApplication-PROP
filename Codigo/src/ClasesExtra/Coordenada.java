package ClasesExtra;

/**
 *
 * @author Àlex
 */
public class Coordenada {
    private int x;
    private int y;
    
    public Coordenada(int x,int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX (int x) {
        this.x = x;
    }
    
    public void setY (int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void printxy() {  //para pruebas de fichas
        System.out.println(x+" "+y);
    }
    
    public boolean esValid() {
        return (x >= 0 && y <= 7 && x <= 7 && y >= 0);
    }
}

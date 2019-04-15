package ClasesExtra;

/**
 *
 * @author Àlex
 */
public class Coordenada {
    private int x;
    private int y;
    
    public Coordenada() {}
   
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
    
    public void stringToCoord(String s) {
        Boolean first = true;
        if (s.length() != 2) System.out.println("No es una coordenada válida.");
        for (int i = 0; i < s.length(); i++) {
            int o;
            if (first) {
                first = false;
                switch (s.charAt(i)) {
                    case 'a':
                        o = 0;
                        this.setY(o);
                        break;
                    case 'b':
                        o = 1;
                        this.setY(o);
                        break;
                    case 'c':
                        o = 2;
                        this.setY(o);
                        break;
                    case 'd':
                        o = 3;
                        this.setY(o);
                        break;
                    case 'e':
                        o = 4;
                        this.setY(o);
                        break;
                    case 'f':
                        o = 5;
                        this.setY(o);
                        break;
                    case 'g':
                        o = 6;
                        this.setY(o);
                        break;
                    case 'h':
                        o = 7;
                        this.setY(o);
                        break;
                }
            }
            else {
                char j = s.charAt(i);
                int p = j - '0';
                int h = p+1;
                int u;
                switch (h) {
                    case 1:
                        u = 8;
                        this.setX(u);
                        break;
                    case 2:
                        u = 7;
                        this.setX(u);
                        break;
                    case 3:
                        u = 6;
                        this.setX(u);
                        break;
                    case 4:
                        u = 5;
                        this.setX(u);
                        break;
                    case 5:
                        u = 4;
                        this.setX(u);
                        break;
                    case 6:
                        u = 3;
                        this.setX(u);
                        break;
                    case 7:
                        u = 2;
                        this.setX(u);
                        break;
                    case 8:
                        u = 1;
                        this.setX(u);
                        break;
                }
            }
        }
    }
}

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
        System.out.println(coordToString());
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
    
    public String coordToString() {
        String s = "";
        switch(this.getY()) {
            case 0:
                s += "a";
                break;
            case 1:
                s += "b";
                break;
            case 2:
                s += "c";
                break;
            case 3:
                s += "d";
                break;
            case 4:
                s += "e";
                break;
            case 5:
                s += "f";
                break;
            case 6:
                s += "g";
                break;
            case 7:
                s += "h";
                break;
        }
        switch(this.getX()) {
            case 0:
                s += "8";
                break;
            case 1:
                s += "7";
                break;
            case 2:
                s += "6";
                break;
            case 3:
                s += "5";
                break;
            case 4:
                s += "4";
                break;
            case 5:
                s += "3";
                break;
            case 6:
                s += "2";
                break;
            case 7:
                s += "1";
                break;
        }
        return s;
    }
}

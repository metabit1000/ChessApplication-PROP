package ClasesExtra;

/**
 *
 * @author Joan
 */

/* Clase java pair de coordenadas implementada al no poder usar javafx */
public class Pair <fst, snd> { 
    private fst key;
    private snd value;
    
    public Pair() {}
    
    public Pair(fst key,snd value) {
        this.key = key;
        this.value = value;
    }
    
    public fst getKey() {
        return key;
    }
    
    public snd getValue() {
        return value;
    }
    
    public void setKey(fst key) {
        this.key = key;
    }
    
    public void setValue(snd value) {
        this.value = value;
    }
}

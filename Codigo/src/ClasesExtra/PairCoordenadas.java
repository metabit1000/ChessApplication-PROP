package ClasesExtra;

/**
 *
 * @author Joan
 */

/* Clase java pair de coordenadas implementada al no poder usar javafx */
public class PairCoordenadas { 
    private Coordenada key;
    private Coordenada value;
    
    public PairCoordenadas() {}
    
    public PairCoordenadas(Coordenada key,Coordenada value) {
        this.key = key;
        this.value = value;
    }
    
    public Coordenada getKey() {
        return key;
    }
    
    public Coordenada getValue() {
        return value;
    }
    
    public void setKey(Coordenada key) {
        this.key = key;
    }
    
    public void setValue(Coordenada value) {
        this.value = value;
    }
}

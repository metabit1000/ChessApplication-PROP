package ClasesExtra;

/**
 *
 * @author Joan
 */
public class PairCoordenadas { //clase java pair implementada al no poder usar javafx
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

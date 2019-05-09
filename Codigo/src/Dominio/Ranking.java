package Dominio;
import java.util.*;
/**
 *
 * @author Jordi
 */
public class Ranking {
    private Map<String,Double> rank = new HashMap<>();
    
    public Ranking() {}
        
    public void ordenar(){
        List<Map.Entry<String, Double>> list = new LinkedList<>(rank.entrySet());
        Collections.sort(list, (Map.Entry<String, Double> m1, Map.Entry<String, Double> m2) -> (m1.getValue()).compareTo(m2.getValue()));
        Map<String, Double> result = new LinkedHashMap<>();
        list.forEach((entry) -> {
            result.put(entry.getKey(), entry.getValue());
        });
        rank= result;
    }

    public void getclasificacion(){
        Iterator iterator=rank.keySet().iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
            System.out.println("Clave : " + key + "  Valor : "+ rank.get(key));
        }
    }
    public Boolean existeix(String nom ){
        if(rank.containsKey(nom))return true ;
        else return false ; 
    }
    
    public void setElemento(String nombre,Double tiempo){
        rank.put(nombre,tiempo);
        ordenar();
    }
    
    public void eliminarUsuario(String  nombre){
        rank.remove(nombre);
    }
    public void setActualizar(String nombre,double tiempo){
        //comprobar que esté dentro de Rank
      double n = rank.get(nombre);
        if (tiempo < n ){
            eliminarUsuario(nombre);
            setElemento(nombre,tiempo);  
        }
    }
    
    public ArrayList<String> toArrayDeStrings() {  //necesaria de cara a pasarlo al fichero de Problemas
        ArrayList<String> res = new ArrayList();
        Iterator iterator=rank.keySet().iterator();
        int i = 0;
        while(iterator.hasNext()){
            Object key = iterator.next();
            res.add(key + " "+ rank.get(key));
        }
        return res;
    }
}

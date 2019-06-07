package Dominio;
import java.util.*;
/**
 *
 * @author Jordi
 */
public class Ranking {
    private Map<String,Double> rank = new HashMap<>();
    
    public Ranking() {}
    
    /**
     * pre:-
     * post:Ordena el ranking segun los valores de las keys
    */
    public void ordenar(){
        List<Map.Entry<String, Double>> list = new LinkedList<>(rank.entrySet());
        Collections.sort(list, (Map.Entry<String, Double> m1, Map.Entry<String, Double> m2) -> (m1.getValue()).compareTo(m2.getValue()));
        Map<String, Double> result = new LinkedHashMap<>();
        list.forEach((entry) -> {
            result.put(entry.getKey(), entry.getValue());
        });
        rank = result;
    }

    /**
     * pre:Dado un string nom diferente a null
     * post:Devolvera true si esta esta key dentro de nuestro map sino false
     * @param nom
     * @return 
     */
    public Boolean existeix(String nom ){
        if(rank.containsKey(nom))return true ;
        else return false ; 
    }
    
    /**
     * pre:Dado un nombre y un tiempo no null
     * post:Insertaremos la key que sera la variable nombre y tiempo como su value
     * @param nombre
     * @param tiempo 
     */
    
    public void setElemento(String nombre,Double tiempo){
      if (tiempo >= 0)  rank.put(nombre,tiempo);
        ordenar();
    }
    
    /**
     * pre:Dado una variable string nombre diferente a null y existente en el rank
     * post:Eliminaremos la key que representa el string nom y su valor asociado
     * @param nombre 
     */
    public void eliminarUsuario(String  nombre){
        rank.remove(nombre);
    }
    
    /**
     * pre: Dado un tiempo no negativo y un nombre existente en el ranking
     * post: Actualizaremos el tiempo en resolver el problema de la key que representa nombre si este tiempo es menor que el anterior
     * @param nombre
     * @param tiempo 
     */
    public void setActualizar(String nombre,double tiempo){
        //comprobar que esté dentro de Rank
      double n = rank.get(nombre);
        if (tiempo < n  && tiempo >= 0){
            eliminarUsuario(nombre);
            setElemento(nombre,tiempo);  
        }
    }
    
    /**
     * pre:-
     * post: Devuelve el map de la variable rank
     * @return 
     */
    public Map<String,Double> getmap( ){
        return rank; 
    }
    
    /**
     * pre:-
     * post:Devolvera una arraylist con el ranking 
     * @return 
     */
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

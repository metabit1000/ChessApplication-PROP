/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;
import java.util.*;
/**
 *
 * @author Jordi
 */
public class Ranking {
    private Hashtable<String,double> rank = new Hashtable<String,double>();
    
    public Ranking() {}
    
        public void setimprimir(){
              Enumeration e = rank.keys();
              string  clave;
            double valor;
            while( e.hasMoreElements() ){
            clave = e.nextElement();
            valor = rank.get( clave );
             System.out.println( "Clave : " + clave + " - Valor : " + valor );
}
        }

    public void getclasificacion(){
        Hashtable<String,double> ran = new Hashtable<String,double>();
        ran = orderForValues(rank);
        rank=ran ; 
        setimprimir();
        
        
    }
    
    public static Hashtable orderForValues(Hastable map)
{
Hashtable newMap = new Hashtable();
ArrayList values = new ArrayList(map.values());
Collections.sort(values);
Iterator it = values.iterator();
double tmp=0;
while(it.hasNext())
{
tmp = it.next();
for(Map.Entry k : map.entrySet())
{
if(tmp==k.getValue())
{newMap.put(k.getKey(), k.getValue());}
}
}

return newMap;
}
    public void setelemento(string nombre , double tiempo){
          Rank.put(nombre , tiempo );
    }
    
    public void seteliminarUsuario(string nombre ){
        Rank.remove(nombre);
    }
    public void setactualizar(string nombre , double tiempo ){
        //comprobar que esté dentro de Rank
           double  n = Rank.get(nombre);
           if (n <  tiempo ) {
               
           }
           
        seteliminarUsuario(nombre);
        setelemento(nombre ,tiempo);
    }
    
}

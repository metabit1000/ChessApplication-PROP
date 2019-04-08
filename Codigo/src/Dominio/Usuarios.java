package Dominio;
import java.util.*;
//import Dominio.Usuario;

/**
 *
 * @author joan
 */
public class Usuarios {
    
    private ArrayList<Usuario> Users = new ArrayList<Usuario>();
    
    public Usuarios() {}
         
    public void addUser(Usuario u) {
        Users.add(u);
    }
    
    
    
    public Boolean existUser(String nom) {
        for (int i = 0; i < Users.size(); i++) {
            Usuario u = Users.get(i);
            String n = u.getNombre();
            if (nom == n) return true;
        }
        return false;
    }
    
//    public void changePass(String nom, String newpassword) {
//        if (existUser(nom) && correctPass(newpassword)) {
//            Users.put(nom, newpassword);
//        }
//        else if (existUser(nom)) System.out.println("El usuario con nombre " + nom + " ya existe. Prueba con otro.");
//        else if (!correctPass(newpassword)) System.out.println("La nueva contraseña necesita como mínimo 6 carácteres y tener como mínimo una letra minúscula, una mayúscula y un número.");
//    }
    
    public void printMap() {
//    Iterator it = Users.entrySet().iterator();
//    while (it.hasNext()) {
//        Map.Entry pair = (Map.Entry)it.next();
//        System.out.println(pair.getKey() + " = " + pair.getValue());
//        it.remove(); // avoids a ConcurrentModificationException
//        for ( String key : Users.keySet() ) {
//            System.out.println( key );
//        }
    }

    
    public Boolean isEmpty() {
        return Users.isEmpty();
    }
}

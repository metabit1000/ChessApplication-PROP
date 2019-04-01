package Dominio;
import java.util.*;
import Dominio.fichas.Ficha;
import ClasesExtra.Coordenada;
/**
 *
 * @author joan
 */
public class Usuarios {
     private Map<String, String> Users = new HashMap<String, String>();
     
     public Usuarios() {}
          
     public void addUser(String nom, String password) {
         if (!existUser(nom) && correctPass(password)) {
             Users.put("nom", "password");
         }
         else if (existUser(nom)) System.out.println("El usuario con nombre " + nom + " ya existe. Prueba con otro.");
         else if (!correctPass(password)) System.out.println("La contraseña necesita como mínimo 6 carácteres, y tener, como mínimo una letra minúscula, una mayúscula y un número.");
     }
     
     public Boolean correctPass(String pass) {
         boolean n = false;
         boolean m = false;
         boolean M = false;
         for (int j = 0; j < pass.length(); ++j) {
             String mayu ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
             for(int i = 0; i < mayu.length(); ++i) {
                 if (mayu.charAt(i) == pass.charAt(j)) M = true; // Es una letra mayuscula
             }
             String minu = "abcdefghijklmnopqrstuvwxyz";
             for(int i = 0; i < minu.length(); ++i) {
                 if (minu.charAt(i) == pass.charAt(j)) M = true; // Es una letra mayuscula
             }
             String num = "0123456789";
             for(int i = 0; i < num.length(); ++i)
             if (num.charAt(i) == pass.charAt(j)) n = true; // Es una letra minuscula
             }
         return pass.length() > 5 && n && m && M;
     }
     
     public Boolean existUser(String nom) {
         return Users.containsKey(nom);
     }
     
     public void changePass(String nom, String newpassword) {
         if (existUser(nom)) {
             Users.put("nom", "password");
         }
     }
}

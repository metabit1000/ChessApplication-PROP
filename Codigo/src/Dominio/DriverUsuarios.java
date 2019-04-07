package Dominio;
import Dominio.Usuarios;
//import java.Excepciones.*;
import java.util.*;

/**
 *
 * @author joan
 */
public class DriverUsuarios {
//    public void testCofnstructor() {}
//    public void testGetCoordenada() {}
//    public void testGetColor() {}
//    public void testsetPosicion() {}
//    testPosiblesMovimientos en cada subclase
    public static void main (String [] args) throws Exception {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Usuarios prueba = new Usuarios();
        while (estado != fin) {
            System.out.println("Usuarios:");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Login");
            System.out.println("3. Cambiar contraseña");
            System.out.println("4. Salir");
            //faltaria logout y hacer booleano en usuarios que diga si un usuario está logeado
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    String nom;
                    String pass;
                    Boolean existeNom;
                    Boolean passwordCorrect;
                    System.out.println("Ha elegido: Crear Usuario");
                    System.out.println("Introduzca un nombre: ");
                    nom = sc.nextLine();
                    sc.nextLine();
                    existeNom = prueba.existUser(nom);
                    while (existeNom){
                        System.out.println("El usuario con nombre " + nom + " ya existe. Prueba con otro.");
                        nom = sc.nextLine();
                        existeNom = prueba.existUser(nom);
                    }
                    if (!existeNom) System.out.println("Nombre de usuario disponible.");
                    
                    System.out.println("Introduzca una constraseña: ");
                    pass = sc.nextLine();
                    passwordCorrect = prueba.correctPass(pass);
                    while (!passwordCorrect){
                        System.out.println("La contraseña necesita como mínimo 6 carácteres y tener como mínimo una letra minúscula, una mayúscula y un número.");
                        pass = sc.nextLine();
                        passwordCorrect = prueba.correctPass(pass);
                    }
                    if (passwordCorrect) System.out.println("Contraseña aceptada correctamente.");
                    
                    prueba.addUser(nom, pass);
                    prueba.printMap();
                    
                    break;
//                case 2:
//                    System.out.println("Ha elegido: GetPosicion");
//                    Coordenada c2 = new Coordenada();
//                    try {
//                        c2 = prueba.getPosicion();
//                        c2.printxy();
//                    } catch (NullPointerException e) {
//                        System.out.println("ERROR: Es necesario crear la clase");
//                    }
//                    System.out.println("La posicion de una ficha peon cualquiera es: ");
//                    c2.printxy();
//                    break;
//                case 3: 
//                    System.out.println("Ha elegido: GetColor");
//                    Peon prueba3 = new Peon(false,new Coordenada(1,1));
//                    Boolean c3 = prueba3.getColor();
//                    System.out.println("El color de una ficha cualquiera es: " + c3);
//                    break;
//                case 4:
//                    System.out.println("Ha elegido: SetColor");
//                    Peon prueba4 = new Peon(false,new Coordenada(1,1));
//                    System.out.println("Introduzca un color (false = negro /blanco = true) : ");
//                    Boolean c4 = sc.nextBoolean();
//                    System.out.println("Se ha obtenido correctamente");
//                    break;
//                case 5:
//                    System.out.println("Ha elegido: SetPosicion");
//                    Peon prueba5 = new Peon(false,new Coordenada(1,1));
//                    System.out.println("Introduzca un coordenada(x): ");
//                    int res5 = sc.nextInt();
//                    Coordenada c5 = new Coordenada();
//                    c5.setX(res5);
//                    System.out.println("Introduzca un coordenada(y): ");
//                    int res6 = sc.nextInt();
//                    c5.setY(res6);
//                    prueba5.setPosicion(c5);
//                    System.out.println("Se ha obtenido correctamente");
//                    break;
                case 4: 
                    fin = 4;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}

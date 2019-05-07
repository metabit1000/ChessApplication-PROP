package Dominio;


import java.util.*;

/**
 *
 * @author joan
 */
public class DriverCtrlUsuarios {
    public static void main (String [] args) throws Exception {
        int estado = 0;
        int fin = 1000;
        Scanner sc = new Scanner(System.in);
        CtrlUsuarios prueba = new CtrlUsuarios();

        while (estado != fin) {
            System.out.println("Usuarios:");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Login usuario");
            System.out.println("3. Login guest");
            System.out.println("4. Usuario logeado");
            System.out.println("5. Guest logeado");
            System.out.println("6. Cerrar sesión usuario");
            System.out.println("7. Cerrar sesión guest");
            System.out.println("8. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    String nomR;
                    String passR;
                    System.out.println("Ha elegido: Registrar Usuario");
                    System.out.println("Introduzca un nombre: ");
                    nomR = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca una constraseña: ");
                    passR = sc.next();
                    sc.nextLine();
                    prueba.registrarUsuario(nomR, passR);
                    break;
                case 2:
                    String nomLI;
                    String passLI;
                    System.out.println("Ha elegido: Logear Usuario");
                    System.out.println("Introduzca un nombre: ");
                    nomLI = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca una constraseña: ");
                    passLI = sc.next();
                    sc.nextLine();
                    prueba.loginUsuario(nomLI, passLI);
                    break;
                case 3:
                    String nomLG;
                    String passLG;
                    System.out.println("Ha elegido: Logear Usuario");
                    System.out.println("Introduzca un nombre: ");
                    nomLG = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca una constraseña: ");
                    passLG = sc.next();
                    sc.nextLine();
                    prueba.loginGuest(nomLG, passLG);
                    break;
                    
                case 4:
                    System.out.println("El usuario logeado: " + prueba.getUserLogged() +".");
                    break;
                case 5:
                    System.out.println("El guest logeado: " + prueba.getGuest() +".");
                    break;
                case 6: 
                    prueba.logoutUsuario();
                    System.out.println("Se ha cerrado la sesión del usuario");
                    break;
                case 7:
                    prueba.logoutGuest();
                    System.out.println("Se ha cerrado la sesión del guest");
                    break;
                case 8:
                    fin = 8;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}

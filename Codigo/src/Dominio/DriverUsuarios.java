package Dominio;


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
        CtrlUsuarios prueba = new CtrlUsuarios();

        while (estado != fin) {
            System.out.println("Usuarios:");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Login");
            System.out.println("3. Cerrar sesión");
            System.out.println("4. Modificar contraseña");
            System.out.println("5. Print Usuarios");
            System.out.println("6. Salir");
            //faltaria logout y hacer booleano en usuarios que diga si un usuario está logeado
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
                    System.out.println("Sesión iniciada satisfactoriamente");
                    break;
                case 3: 
                    String nomLO;
                    System.out.println("Ha elegido: Cerrar sesión");
                    System.out.println("Introduzca un nombre: ");
                    nomLO = sc.next();
                    sc.nextLine();
                    prueba.logoutUsuario(nomLO);
                    System.out.println("Se ha cerrado la sesión");
                    break;
                case 4:
                    String nomMP;
                    String passMP;
                    System.out.println("Ha elegido: Modificar contraseña");
                    System.out.println("Introduzca un nombre: ");
                    nomMP = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca una nueva constraseña: ");
                    passMP = sc.next();
                    sc.nextLine();
                    prueba.modificarPassword(nomMP, passMP);
                    System.out.println("La contraseña ha sido cambiada satisfactoriamente");
                    break;
                case 5:
                    prueba.printUsuarios();
                    break;
                case 6: 
                    fin = 5;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}

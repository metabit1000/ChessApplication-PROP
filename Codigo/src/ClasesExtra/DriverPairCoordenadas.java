package ClasesExtra;

import java.util.Scanner;

/**
 *
 * @author Joan
 */
public class DriverPairCoordenadas {
    public static void main (String [] args){
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        PairCoordenadas prueba = new PairCoordenadas();
        String cor1,cor2;
        Coordenada pr1 = new Coordenada();
        Coordenada pr2 = new Coordenada();
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructora");
            System.out.println("2. GetKey");
            System.out.println("3. SetKey");
            System.out.println("4. GetValue");
            System.out.println("5. SetValue");
            System.out.println("6. Exit");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            if (estado < 0 || estado > 6) System.out.println("Introduzca una opción valida");
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Constructora");
                    System.out.println("Introduzca una coordenada(ej e8): ");
                    cor1 = sc.next();
                    sc.nextLine();
                    pr1.stringToCoord(cor1);
                    System.out.println("Introduzca una segunda coordenada(ej e8): ");
                    cor2 = sc.next();
                    sc.nextLine();
                    pr2.stringToCoord(cor2);
                    prueba = new PairCoordenadas(pr1,pr2);
                    System.out.println("Pair de Coordenadas creado correctamente.");
                    break;
                case 2:
                    System.out.println("Ha elegido: GetKey");
                    if (prueba.getKey() != null) {
                        System.out.print("La primera coordenada es: ");
                        prueba.getKey().printxy();
                    }
                    else System.out.println("Debe crear el pair antes, vuelva a intentarlo después de hacerlo");
                    break;
                case 3: 
                    System.out.println("Ha elegido: SetKey");
                    System.out.println("Introduzca una coordenada: ");
                    cor1 = sc.next();
                    sc.nextLine();
                    pr1.stringToCoord(cor1);
                    prueba.setKey(pr1);
                    System.out.println("Coordenada introducida correctamente.");
                    break;
                case 4:
                    System.out.println("Ha elegido: GetValue");
                    if (prueba.getValue() != null) {
                        System.out.print("La primera coordenada es: ");
                        prueba.getValue().printxy();
                    } 
                    else System.out.println("Debe crear el pair antes, vuelva a intentarlo después de hacerlo");
                    break;
                case 5: 
                    System.out.println("Ha elegido: SetValue");
                    System.out.println("Introduzca una coordenada: ");
                    cor2 = sc.next();
                    sc.nextLine();
                    pr1.stringToCoord(cor2);
                    prueba.setKey(pr2);
                    System.out.println("Coordenada introducida correctamente.");
                    break;      
                case 6: 
                    estado = 1000;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}

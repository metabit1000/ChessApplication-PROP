package Persistencia;

import Dominio.Usuario;
import java.io.IOException;

/**
 *
 * @author Àlex
 */
public class DriverParaProbar {
    public static void main(String[]args){
        CtrlDatosUsuarios cj = new CtrlDatosUsuarios();
        try {
            cj.abrirArchivo();
        } catch (IOException e) {
            System.out.println("Cree antes el fichero");
        }
        
        Usuario u = new Usuario(false,"us1","asasdfa");
        try {
            cj.modificarPassword(u, "funcionaXD");
        } catch (IOException e) {
            System.out.println(e);
        }
    }  
}

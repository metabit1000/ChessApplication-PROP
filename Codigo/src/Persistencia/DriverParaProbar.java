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
        cj.abrirArchivo();
        System.out.println(cj.usuarioRegistrado("us1","funcionaXD"));
    }  
}

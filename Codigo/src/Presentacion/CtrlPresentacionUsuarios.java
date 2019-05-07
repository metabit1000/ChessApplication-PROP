package Presentacion;
import Dominio.CtrlUsuarios;
import Dominio.Usuario;
import java.io.IOException;

/**
 *
 * @author Jordi
 */
public class CtrlPresentacionUsuarios {
    CtrlUsuarios ctrlU = new CtrlUsuarios();

    public Boolean UsuarioRegistrado(Usuario u) throws IOException{
        return ctrlU.existUser(u);
    }
  
    public Boolean LogIn(String nom,String password) throws IOException{
        Usuario u = new Usuario(false,nom,password);
        if(UsuarioRegistrado(u)) {
            ctrlU.loginUsuario(nom, password);
            return true; 
        }
        else return false; 
    }
    
    public Boolean Registro(String nom,String password) throws IOException{
        Usuario u = new Usuario(false,nom,password);
        if(!UsuarioRegistrado(u)){
            ctrlU.registrarUsuario(nom, password);
            return true; 
        }
        else return false; 
    }
}

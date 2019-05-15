package Presentacion;
import Dominio.CtrlUsuarios;
import Dominio.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jordi
 */
public class CtrlPresentacionUsuarios {
    CtrlUsuarios ctrlU = new CtrlUsuarios();

    public Boolean UsuarioRegistrado(String nom,String password) {
        Usuario u = new Usuario(false,nom,password);

        try {
            return ctrlU.existUser(u);
        } catch (IOException ex) {
            Logger.getLogger(CtrlPresentacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
  
    public Boolean LogIn(String nom,String password) {
        Usuario u = new Usuario(false,nom,password);
        if(UsuarioRegistrado(nom,password)) {
            try {
                ctrlU.loginUsuario(nom, password);
            } catch (IOException ex) {
                Logger.getLogger(CtrlPresentacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true; 
        }
        else return false; 
    }
    
    public Boolean Registro(String nom,String password) {
        Usuario u = new Usuario(false,nom,password);
        if(!UsuarioRegistrado(nom,password)){
            try {
                ctrlU.registrarUsuario(nom, password);
            } catch (IOException ex) {
                Logger.getLogger(CtrlPresentacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true; 
        }
        else return false; 
    }
    
    public Boolean contraseñaok(String pasword){
        return ctrlU.correctPass(pasword);
    }
    
    public void cambiarContraseña(String user,String pass,String passwordCambiar){
        try {
            Usuario u = new Usuario();
            ctrlU.modificarPassword(user,pass,passwordCambiar);
        } catch (IOException ex) {
            Logger.getLogger(CtrlPresentacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}

package Presentacion;
import Dominio.CtrlUsuarios;
import Dominio.Usuario;
import Persistencia.CtrlDatosUsuarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jordi
 */
public class CtrlPresentacionUsuarios {
    CtrlUsuarios ctrlU = new CtrlUsuarios();
    CtrlDatosUsuarios cdu = new CtrlDatosUsuarios();

    public String getUserLogged() {
        return ctrlU.getUserLogged();
    }
    
    public String getGuest() {
        return ctrlU.getGuest();
    }
    
    public Boolean UsuarioRegistrado(String nom,String password) {
        Usuario u = new Usuario(false,nom,password);

        try {
            return ctrlU.existUser(u);
        } catch (IOException ex) {
            Logger.getLogger(CtrlPresentacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
  
    public boolean LogIn(String nom,String password) {
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
    
    public boolean LogOutUser() {
        if(ctrlU.getUserLogged() != null) {
            ctrlU.logoutUsuario();
            return true; 
        }
        else return false; 
    }
    
    public boolean LogInGuest(String nom,String password) {
        Usuario u = new Usuario(false,nom,password);
        if(UsuarioRegistrado(nom,password) && !getUserLogged().equals(nom)) {
            try {
                ctrlU.loginGuest(nom, password);
            } catch (IOException ex) {
                Logger.getLogger(CtrlPresentacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        else return false; 
    }
    
    public boolean LogOutGuest() {
        if(ctrlU.getGuest() != null) {
            ctrlU.logoutGuest();
            return true; 
        }
        else return false; 
    }
    
    public boolean Registro(String nom,String password) {
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
    
    public boolean contraseñaok(String pasword){
        return ctrlU.correctPass(pasword);
    }
    
    public String getUL(){
        return ctrlU.getUserLogged();
    }
    public String getGL(){
        return ctrlU.getGuest();
    }
    
    public void cambiarContraseña(String pass,String passwordCambiar){
        Usuario u = new Usuario();
        try {
            ctrlU.modificarPassword(ctrlU.getUserLogged(),pass,passwordCambiar);
        } catch (IOException ex) {
            Logger.getLogger(CtrlPresentacionUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void introducirProblemaCreado(String nombre, int id) {
        ctrlU.introducirProblemaCreado(nombre,id);
    }
    public ArrayList<Integer> getProblemasCreados(String nombre) {
        return ctrlU.getProblemasCreados(nombre);
    }
}

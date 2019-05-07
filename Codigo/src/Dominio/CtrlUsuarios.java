package Dominio;
import Persistencia.CtrlDatosUsuarios;
import java.io.IOException;

/**
 *
 * @author joan
 */
public class CtrlUsuarios {    
    CtrlDatosUsuarios cj = new CtrlDatosUsuarios();
    private String UserLogged;
    private String Guest;
    
    public CtrlUsuarios() {}
    
    public String getUserLogged() {
        //devuelve el nombre del usuario que está logeado si lo hay.
        if (UserLogged != null ) return UserLogged;
        else return "No hay usuario logeado";
    }
    
    public String getGuest() {
        //devuelve el nombre del usuario que ha iniciado sesión como invitado si lo hay.
        if (Guest != null ) return Guest;
        else return "No hay guest disponible";
    }
    
    public void registrarUsuario(String nom, String pass) throws IOException {
        //da de alta un usuario con nombre nom y contraseña pass. en el caso de que ya exista un usuario con nombre nom, no se dará de alta
        //y en el caso de que la contraseña no cumpla las condiciones tampoco se dará de alta.
        Usuario usIntroducido = new Usuario(false,nom,pass);
        if (!existUser(usIntroducido)) {
            if (correctPass(pass)) {
                cj.escribirUsuario(nom,pass);
                System.out.println("Usuario registrado correctamente.");
            }
            else System.out.println("La contraseña necesita como mínimo 6 carácteres y tener como mínimo una letra minúscula, una mayúscula y un número.");
        }
        else System.out.println("El usuario con nombre " + nom + " ya existe. Prueba con otro.");
    }
    
    public void modificarPassword(Usuario usRegistrado, String passCambiar) throws IOException {
        if (existUser(usRegistrado)) {
            cj.modificarPassword(usRegistrado.getNombre(),usRegistrado.getPassword(), passCambiar);
            System.out.println("Contraseña modificada correctamente");
        }
        
    }
    
    public void loginUsuario(String nom, String pass) throws IOException {
        //el usuario con nombre nom y contraseñas pass hace login en el caso de que el nombre de usuario y contraseña coincidan con un usuario
        //dado de alta previamente
        Usuario usIntroducido = new Usuario(false,nom,pass); 
        if (!existUser(usIntroducido)) System.out.println("El nombre de usuario o contraseña es incorrecto, vuelva a intentarlo");
        else {
            UserLogged = nom;
            System.out.println("Sesión iniciada satisfactoriamente");
        }
    }
    
    public void loginGuest(String nom, String pass) throws IOException {
       //el usuario con nombre nom y contraseñas pass inicia sesión como invitado en el caso de que el nombre de usuario y contraseña coincidan con un usuario
        //dado de alta previamente
        Usuario usIntroducido = new Usuario(false,nom,pass); 
        if (!existUser(usIntroducido)) System.out.println("El nombre de usuario o contraseña es incorrecto, vuelva a intentarlo");
        else {
            Guest = nom;
            System.out.println("Sesión iniciada satisfactoriamente");
        }
    }
    
    public void logoutUsuario() {
        //el usuario que habia logeado previamente pasa a no estarlo y por lo tanto el numbre del usuario loggeado pasa a ser null.
        if (UserLogged != null)UserLogged = null;
        else System.out.println("No esta logueado");
    }
    
    public void logoutGuest() {
        //el usuario que habia logeado como invitado previamente pasa a no estarlo y por lo tanto el numbre del usuario loggeado pasa a ser null.
        if (UserLogged != null) Guest = null;
        else System.out.println("No esta logueado");
    }
    
    public Boolean correctPass(String pass) {
        //devuelve true si la contraseña pass cumple los requisitos: minimo 6 caracteres, como mínimo una letra mayuscula, una minuscula y un numero
        //de no cumplir uno de estos requisitos devolverá false.
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
                if (minu.charAt(i) == pass.charAt(j)) m = true; // Es una letra minúscula
            }
            String num = "0123456789";
            for(int i = 0; i < num.length(); ++i)
                if (num.charAt(i) == pass.charAt(j)) n = true; // Es un número
            }
        return pass.length() > 5 && n && m && M;
    }
    
    public Boolean existUser(Usuario u) throws IOException {
        //devuelve true si el usuario existe en el fichero .txt, false en caso contrario
        return cj.usuarioRegistrado(u.getNombre(),u.getPassword());
    }
}


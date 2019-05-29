package Dominio;
import Persistencia.CtrlDatosUsuarios;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author joan
 */
public class CtrlUsuarios {    
    private CtrlDatosUsuarios cj = new CtrlDatosUsuarios();
    private String UserLogged;
    private String GuestLogged;
    
    public CtrlUsuarios() {}
    
    public String getUserLogged() {
        //devuelve el nombre del usuario que está logeado si lo hay.
        if (UserLogged != null ) return UserLogged;
        else return "No hay usuario logeado";
    }
    
    public String getGuest() {
        //devuelve el nombre del usuario que ha iniciado sesión como invitado si lo hay.
        if (GuestLogged != null ) return GuestLogged;
        else return "No hay guest disponible";
    }
    
    public int registrarUsuario(String nom, String pass) throws IOException {
        //da de alta un usuario con nombre nom y contraseña pass. en el caso de que ya exista un usuario con nombre nom, no se dará de alta
        //y en el caso de que la contraseña no cumpla las condiciones tampoco se dará de alta.
        Usuario usIntroducido = new Usuario(false,nom,pass);
        if (!existeNombre(nom)) {
            if (correctPass(pass)) {
                cj.escribirUsuario(nom,pass);
                return 1;//Usuario registrado correctamente.
            }
            else return 2; //La contraseña necesita como mínimo 6 carácteres y tener como mínimo una letra minúscula, una mayúscula y un número.
        }
        else return 3;//El usuario con nombre " + nom + " ya existe. Prueba con otro.
    }
    
    
    /* Introduce a los usuarios registrados en el fichero UsuariosGuardados.txt un nuevo problema */
    public void introducirProblemaCreado(String nom,int id) {
        cj.introducirProblemaCreado(nom,id);  
    }
    
    public ArrayList<Integer> getProblemasCreados(String nombre) {
        ArrayList<Integer> res = new ArrayList();
        res = cj.getProblemasCreados(nombre);
        return res;
    }
    
    public int modificarPassword(String user,String password,String passCambiar) throws IOException {
        int res = 0; //por default, todo va bien
        if (existUser(user,password)) {
            if (correctPass(passCambiar)) {
                cj.modificarPassword(user, passCambiar);
            }
            else res = -1; //en caso de que no sea correcta
        }
        return res;
    }
    
    public String getPasswordUsuarioLogged() {
        return cj.getPassword(UserLogged);
    }
    
    public void loginUsuario(String nom, String pass) throws IOException {
        UserLogged = nom;
    }
    
    public void loginGuest(String nom, String pass) throws IOException {
        GuestLogged = nom;
    }
    
    public void logoutUsuario() {
        //el usuario que habia logeado previamente pasa a no estarlo y por lo tanto el numbre del usuario loggeado pasa a ser null.
        if (UserLogged != null) UserLogged = null;
    }
    
    public void logoutGuest() {
        //el usuario que habia logeado como invitado previamente pasa a no estarlo y por lo tanto el numbre del usuario loggeado pasa a ser null.
        if (UserLogged != null) GuestLogged = null;
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
    
    public Boolean existUser(String nombre , String password) throws IOException {
        //devuelve true si el usuario existe en el fichero .txt, false en caso contrario
        return cj.usuarioRegistrado(nombre,password);
    }
    
    public Boolean existeNombre(String u) throws IOException {
        //devuelve true si el usuario existe en el fichero .txt, false en caso contrario
        return cj.existeNombre(u);
    }
}


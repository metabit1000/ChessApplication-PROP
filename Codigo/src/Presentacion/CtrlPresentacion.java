/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import Dominio.CtrlUsuarios;
import java.util.*;
/**
 *
 * @author Jordi
 */
public class CtrlUsuariosPre {
    CtrlUsuarios u = new CtrlUsuarios();

  public Boolean UsuarioRegistrado(String nom ){
     return  u.existUser(nom);
      
  }
  
  public Boolean  LogIn(String nom , String Password){
      if(UsuarioRegistrado(nom) ) {
          u.loginUsuario(nom, Password);
          return true ; 
      }
      else return false ; 
     
  }
  public Boolean Registro(String nom , String Password){
      if(!UsuarioRegistrado(nom) ){
          u.registrarUsuario(nom, Password);
          return true ; 
      }
      else return false ; 
  }
}

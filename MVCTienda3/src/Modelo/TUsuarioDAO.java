
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;

public class TUsuarioDAO {
    Conexion conexion;
    
    public TUsuarioDAO(){
        conexion = new Conexion();
    }
    
    public String InsertarUsuario(int idTUsuarios,String NombredUsuario,String Usuario,String Contrasena, String Telefono){
        String RespuestaAccion = null;
        try{
            Connection AccesoBD = conexion.conexion();
            CallableStatement cas = AccesoBD.prepareCall("call insertarUsuario(?,?,?,?,?)"); //El nombre de mi stored prodedure
            cas.setInt(1, idTUsuarios);
            cas.setString(2, NombredUsuario);
            cas.setString(3, Usuario);
            cas.setString(4, Contrasena);
            cas.setString(5, Telefono);
            
            int Exito = cas.executeUpdate();
            
            if(Exito>0)
            {
                RespuestaAccion = "REGISTRO SE GUARDO CORRECTAMENTE";
            }
        }
        catch(Exception e){
            RespuestaAccion = "no se guardo "+e;
        }
        
        return RespuestaAccion;
    }
    
    public String ModificarUsuario(int idTUsuarios,String NombredUsuario,String Usuario,String Contrasena, String Telefono){
        String RespuestaAccion = null;
        try{
            Connection AccesoBD = conexion.conexion();
            CallableStatement cas = AccesoBD.prepareCall("call actualizarUsuario(?,?,?,?,?)"); //El nombre de mi stored prodedure
            cas.setInt(1, idTUsuarios);
            cas.setString(2, NombredUsuario);
            cas.setString(3, Usuario);
            cas.setString(4, Contrasena);
            cas.setString(5, Telefono);
            
            int Exito = cas.executeUpdate();
            
            if(Exito>0)
            {
                RespuestaAccion = "SE ACTUALIZO CORRECTAMENTE";
            }
        }
        catch(Exception e){
            RespuestaAccion = "no se actualizo "+e;
        }
        
        return RespuestaAccion;
    }
    
    public String BorrarUsuario(int idTUsuarios){
        String RespuestaAccion = null;
        try{
            Connection AccesoBD = conexion.conexion();
            CallableStatement cas = AccesoBD.prepareCall("call deletUsuario(?)"); //El nombre de mi stored prodedure
            cas.setInt(1, idTUsuarios);
            
            int Exito = cas.executeUpdate();
            
            if(Exito>0)
            {
                RespuestaAccion = "SE BORRO CORRECTAMENTE";
            }
        }
        catch(Exception e){
            RespuestaAccion = "no se borro "+e;
        }
        
        return RespuestaAccion;
    }
    
    
}


package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TUProductoDAO {
    Conexion conexion;
    
    public TUProductoDAO(){
        conexion = new Conexion();
    }
    
    public String InsertarProducto(int idTUproducto,String nombre,String descripcion,int precio,int cantidad,int idProveedor){
        String RespuestaAccion = null;
        try{
            Connection AccesoBD = conexion.conexion();
            CallableStatement cas = AccesoBD.prepareCall("call insertarProducto(?,?,?,?,?)"); //El nombre de mi stored prodedure
            cas.setInt(1, idTUproducto);
            cas.setString(2, nombre);
            cas.setString(3, descripcion);
            cas.setInt(4, precio);
            cas.setInt(5, cantidad);
            
            int Exito = cas.executeUpdate();
            
            CallableStatement cas2 = AccesoBD.prepareCall("call insertarRelacion_Productos_proveedores(?,?)"); //El nombre de mi stored prodedure
            cas2.setInt(1, idTUproducto);
            cas2.setInt(2, idProveedor);
            int Exito2 = cas2.executeUpdate();
            
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
    
    public String ModificarProducto(int idTUproducto,String nombre,String descripcion,int precio,int cantidad,int idProveedor){
        String RespuestaAccion = null;
        try{
            Connection AccesoBD = conexion.conexion();
            CallableStatement cas = AccesoBD.prepareCall("call actualizarProducto(?,?,?,?,?)"); //El nombre de mi stored prodedure
            cas.setInt(1, idTUproducto);
            cas.setString(2, nombre);
            cas.setString(3, descripcion);
            cas.setInt(4, precio);
            cas.setInt(5, cantidad);
            
            int Exito = cas.executeUpdate();
            
            String a = String.valueOf(idTUproducto);
            String b = String.valueOf(idProveedor);
            String claveUnica = a+b; // Clave "unica" de la tabla productos_proveedores
            
            CallableStatement cas2 = AccesoBD.prepareCall("call insertarRelacion_Productos_proveedores(?,?)"); //El nombre de mi stored prodedure
            cas2.setInt(1, idTUproducto);
            cas2.setInt(2, idProveedor);
            int Exito2 = cas2.executeUpdate();
            
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
    
    public String BorrarProducto(int idTUproducto){
        String RespuestaAccion = null;
        try{
            Connection AccesoBD = conexion.conexion();
            CallableStatement cas = AccesoBD.prepareCall("call deletProducto(?)"); //El nombre de mi stored prodedure
            cas.setInt(1, idTUproducto);
            
            int Exito = cas.executeUpdate();
            
            CallableStatement cas2 = AccesoBD.prepareCall("call borrarRelacion_Productos_proveedores(?,?,?)"); //El nombre de mi stored prodedure
            cas2.setInt(1, idTUproducto);
            int Exito2 = cas2.executeUpdate();
            
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


package Modelo;

public class TUsuario {
 
    int idTUsuarios;
    String NombredUsuario;
    String Usuario;
    String Contrasena;
    String Telefono;
    
    public TUsuario(){
        idTUsuarios=0;
        NombredUsuario="";
        Usuario="";
        Contrasena="";
        Telefono="";
    }

    public String getNombredUsuario() {
        return NombredUsuario;
    }

    public void setNombredUsuario(String NombredUsuario) {
        this.NombredUsuario = NombredUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    public int getIdTUsuarios(){
        return idTUsuarios;
    }
    
    public void setIdTUsuarios(int idTUsuarios){
        
    }
}





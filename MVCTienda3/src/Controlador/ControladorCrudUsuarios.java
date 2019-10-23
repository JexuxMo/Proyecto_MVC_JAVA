
package Controlador;

import Modelo.Conexion;
import Modelo.TUsuarioDAO;
import Vistas.JFCrudUsuarios;

import java.awt.event.ActionEvent; //Click a un boton
import java.awt.event.ActionListener; // Manipular la informacion
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorCrudUsuarios implements ActionListener{
    
    JFCrudUsuarios vistaCrudUsu = new JFCrudUsuarios();
    TUsuarioDAO modeloCrud = new TUsuarioDAO();

    public ControladorCrudUsuarios(JFCrudUsuarios vistaCrudUsu, TUsuarioDAO modeloCrud) {
        this.modeloCrud = modeloCrud;
        this.vistaCrudUsu = vistaCrudUsu;
        this.vistaCrudUsu.btnGuardar.addActionListener(this);
        this.vistaCrudUsu.btnConsultar.addActionListener(this);
        this.vistaCrudUsu.btnEliminar.addActionListener(this);
        this.vistaCrudUsu.btnModificar.addActionListener(this);
        
    }
    
    public void incializarControladorCrudUsuarios(){
        
    }
    
    public void actionPerformed(ActionEvent e){
        int codigo = 0;
        String nombre = "";
        String telefono = "";
        String usuario = "";
        String contrase = "";
        String RespuestaAccion = "";

        if(e.getSource()==vistaCrudUsu.btnGuardar || 
         e.getSource()==vistaCrudUsu.btnModificar ||
         e.getSource()==vistaCrudUsu.btnEliminar){
            
            codigo = Integer.parseInt(vistaCrudUsu.txfId.getText());
            nombre = vistaCrudUsu.txfNombreCompleto.getText();
            telefono = vistaCrudUsu.txfTelefono.getText();
            usuario = vistaCrudUsu.txfUsuario.getText();
            contrase = vistaCrudUsu.pwfContrasena.getText();
            if(e.getSource()==vistaCrudUsu.btnGuardar){
                RespuestaAccion = modeloCrud.InsertarUsuario(codigo, nombre, usuario, 
                        contrase, telefono);
            }
            else if(e.getSource()==vistaCrudUsu.btnModificar){
                RespuestaAccion = modeloCrud.ModificarUsuario(codigo, nombre, usuario, 
                        contrase, telefono);
            }
            else{
                RespuestaAccion = modeloCrud.BorrarUsuario(codigo);
            }
            incializarControladorCrudUsuarios();
            
            if(RespuestaAccion==null){
                JOptionPane.showMessageDialog(null, RespuestaAccion);
            }
            else{
                JOptionPane.showMessageDialog(null, RespuestaAccion);
            }
        }
        
        if(e.getSource()==vistaCrudUsu.btnConsultar){
            
            Conexion con = new Conexion();
            Connection conex = con.conexion();
            
            DefaultTableModel tablaUsuarios = new DefaultTableModel();
            
            tablaUsuarios.addColumn("CODIGO");
            tablaUsuarios.addColumn("NOMBRE");
            tablaUsuarios.addColumn("USUARIO");
            tablaUsuarios.addColumn("CONTRASENA");
            tablaUsuarios.addColumn("TELEFONO");
        
            vistaCrudUsu.jTable1.setModel(tablaUsuarios);
            
            String [] datos = new String[5];
            try{
                Statement st = conex.createStatement();
                ResultSet rs = st.executeQuery("select * from tusuarios");

                while(rs.next())
                {
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);

                    tablaUsuarios.addRow(datos);
                }
                vistaCrudUsu.jTable1.setModel(tablaUsuarios);
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null,"NO SE PUEDEN MOSTRAR LOS DATOS");
            }
        }      
    }
    
    
}



package Controlador;

import Modelo.Conexion;
import Modelo.TUProductoDAO;
import Vistas.JFCrudProductos;

import java.awt.event.ActionEvent; //Click a un boton
import java.awt.event.ActionListener; // Manipular la informacion
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorCrudProductos implements ActionListener{
    JFCrudProductos vistaCrudPro = new JFCrudProductos();
    TUProductoDAO modeloCrud = new TUProductoDAO();

    public ControladorCrudProductos(JFCrudProductos vistaCrudPro, TUProductoDAO modeloCrud){
        this.modeloCrud = modeloCrud;
        this.vistaCrudPro = vistaCrudPro;
        this.vistaCrudPro.btnGuardar.addActionListener(this);
        this.vistaCrudPro.btnConsultar.addActionListener(this);
        this.vistaCrudPro.btnEliminar.addActionListener(this);
        this.vistaCrudPro.btnModificar.addActionListener(this);
        
    }
    
    public void incializarControladorCrudProductos(){
        
    }
    
    public void actionPerformed(ActionEvent e){
        int codigo = 0;
        String nombre = "";
        String descripcion = "";
        int precio = 0;
        int cantidad = 0;
        int idProveedor = 0;
        String RespuestaAccion = "";

        if(e.getSource()==vistaCrudPro.btnGuardar || 
         e.getSource()==vistaCrudPro.btnModificar ||
         e.getSource()==vistaCrudPro.btnEliminar){
            
            codigo = Integer.parseInt(vistaCrudPro.txfId.getText());
            nombre = vistaCrudPro.txfNombreCompleto.getText();
            descripcion = vistaCrudPro.txfNombre.getText();
            precio = Integer.parseInt(vistaCrudPro.txfPrecio.getText());
            cantidad = Integer.parseInt(vistaCrudPro.txfCantidad.getText());
            idProveedor = Integer.parseInt(vistaCrudPro.txfIdProveedor.getText());
            if(e.getSource()==vistaCrudPro.btnGuardar){
                RespuestaAccion = modeloCrud.InsertarProducto(codigo, nombre, descripcion, 
                        precio, cantidad,idProveedor);
            }
            else if(e.getSource()==vistaCrudPro.btnModificar){
                RespuestaAccion = modeloCrud.ModificarProducto(codigo,nombre, descripcion, 
                        precio, cantidad,idProveedor);
            }
            else{
                RespuestaAccion = modeloCrud.BorrarProducto(codigo);
            }
            incializarControladorCrudProductos();
            
            if(RespuestaAccion==null){
                JOptionPane.showMessageDialog(null, RespuestaAccion);
            }
            else{
                JOptionPane.showMessageDialog(null, RespuestaAccion);
            }
        }
        
        if(e.getSource()==vistaCrudPro.btnConsultar){
            
            Conexion con = new Conexion();
            Connection conex = con.conexion();
            
            DefaultTableModel tablaProdcutos = new DefaultTableModel();
            
            tablaProdcutos.addColumn("CODIGO");
            tablaProdcutos.addColumn("NOMBRE");
            tablaProdcutos.addColumn("DESCRIPCION");
            tablaProdcutos.addColumn("PRECIO");
            tablaProdcutos.addColumn("CANTIDAD");
            tablaProdcutos.addColumn("PROVEEDOR");
        
            vistaCrudPro.jTable1.setModel(tablaProdcutos);
            
            String [] datos = new String[6];
            try{
                Statement st = conex.createStatement();
                ResultSet rs = st.executeQuery("select tusproductos.*,tusproveedores.Empresa from tusproductos \n" +
"                inner join producto_proveedor on tusproductos.id_productos = producto_proveedor.idProducto\n" +
"                inner join tusproveedores on tusproveedores.id_proveedor = producto_proveedor.idProveedor");

                while(rs.next())
                {
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    datos[4] = rs.getString(5);
                    datos[5] = rs.getString(7);

                    tablaProdcutos.addRow(datos);
                }
                vistaCrudPro.jTable1.setModel(tablaProdcutos);
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null,"NO SE PUEDEN MOSTRAR LOS DATOS");
            }
        }      
    }
}

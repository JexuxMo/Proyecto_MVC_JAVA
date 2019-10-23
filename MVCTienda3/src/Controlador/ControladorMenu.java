
package Controlador;

import Modelo.TUProductoDAO;
import Modelo.TUsuarioDAO;
import Vistas.JFCrudUsuarios;
import Vistas.JFCrudProductos;
import java.awt.event.ActionEvent;
import Vistas.MenuTienda;
import java.awt.event.ActionListener;

public class ControladorMenu implements ActionListener{
 
    // FRAME MENU TIENDA
    MenuTienda vistaMenu = new MenuTienda();
 
    
    public ControladorMenu(MenuTienda vistaMenu)
    { 
        // INICIALIZANDO EL MISMO MENU (el primero y orignial)
        this.vistaMenu = vistaMenu;
        
        // agregando el ActionListener a los componentes
        this.vistaMenu.btnFrameUsuarios.addActionListener(this);
        this.vistaMenu.btnFrameProductos.addActionListener(this);
        this.vistaMenu.btnFrameProveedores.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == vistaMenu.btnFrameUsuarios){
            
            JFCrudUsuarios vistaJFCU = new JFCrudUsuarios();
            TUsuarioDAO modeloTUDAO = new TUsuarioDAO();
            ControladorCrudUsuarios ControlCrudUsu = new ControladorCrudUsuarios(vistaJFCU,modeloTUDAO);
            vistaJFCU.setVisible(true);
            vistaJFCU.setLocationRelativeTo(null);
        }
        else if(e.getSource() == vistaMenu.btnFrameProductos){
            
            JFCrudProductos vistaJFCP = new JFCrudProductos();
            TUProductoDAO modeloTUDAO = new TUProductoDAO();
            ControladorCrudProductos ControlCrudUsu = new ControladorCrudProductos(vistaJFCP,modeloTUDAO);
            vistaJFCP.setVisible(true);
            vistaJFCP.setLocationRelativeTo(null);
        }
    }
}

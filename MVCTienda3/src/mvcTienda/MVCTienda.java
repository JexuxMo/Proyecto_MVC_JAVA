
package mvcTienda;

import Controlador.ControladorCrudUsuarios;
import Controlador.ControladorMenu;
import Modelo.TUsuarioDAO;
import Vistas.JFCrudUsuarios;
import Vistas.MenuTienda;


public class MVCTienda {
    public static void main(String[] args){

        // INSTANCIAR EL MENU
        MenuTienda vistaMenu = new MenuTienda();
        
        // CARGAR LA INTERFACE ACTIONLISTENER
        ControladorMenu ControlMenu = new ControladorMenu(vistaMenu);
        
        // MOSTRAR LA VENTANA Y CRENTRARLA EN LA PANTALLA
        vistaMenu.setVisible(true);
        vistaMenu.setLocationRelativeTo(null);
    }
}

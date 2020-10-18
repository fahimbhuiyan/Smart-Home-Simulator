package sample.SmartHomeView;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class SmartHomeMenu {



    public MenuBar createSmartHomeMenu(){

        Menu home = new Menu("Home");
        Menu dashboard = new Menu("Dashboard");
        Menu modules = new Menu("Modules");


        // create menuitems
        MenuItem shc = new MenuItem("SHC");
        MenuItem shp = new MenuItem("SHP");
        MenuItem shh = new MenuItem("SHH");

        // add menu items to menu
        modules.getItems().add(shc);
        modules.getItems().add(shp);
        modules.getItems().add(shh);

        // create a menubar
        MenuBar mb = new MenuBar();

        // add menu to menubar
        mb.getMenus().add(home);
        mb.getMenus().add(dashboard);
        mb.getMenus().add(modules);

        return mb;
    }
}

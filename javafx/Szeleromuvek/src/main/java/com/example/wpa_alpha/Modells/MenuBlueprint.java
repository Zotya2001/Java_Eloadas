package com.example.wpa_alpha.Modells;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.HashMap;

public class MenuBlueprint {

    private final MenuBar menuBar;

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuBlueprint(HashMap<String,HashMap<String,String>> Menu){
        menuBar = new MenuBar();
        Menu menu;
        MenuItem menuItem;

        for(String menuPoints : Menu.keySet()){
            menu = createMenus(menuPoints);
            for(String menuOptions : Menu.get(menuPoints).keySet()){
                menuItem = createMenuItem(menuOptions,Menu.get(menuPoints).get(menuOptions));
                menu.getItems().addAll(menuItem);
            }
            this.menuBar.getMenus().add(menu);
        }

    }

    public Menu createMenus(String menu){
        Menu menuPoint = new Menu();
        menuPoint.setText(menu);
        menuPoint.setId(menu);
        return menuPoint;
    }

    public MenuItem createMenuItem(String text, String id){
        MenuItem subMenu = new MenuItem();
        subMenu.setText(text);
        subMenu.setId(id);
        return subMenu;
    }
}

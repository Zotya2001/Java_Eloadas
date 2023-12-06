package com.example.wpa_alpha.Views;

import com.example.wpa_alpha.Modells.Empty;
import com.example.wpa_alpha.Modells.FirstPage;
import com.example.wpa_alpha.Modells.MenuBlueprint;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;

public class MainLayoutView {
    private BorderPane mainFrameBP;
    private final MenuBar menuBar;
    private final Parent view;

    public MenuBar getMenuBar() {
        return menuBar;
    }

    //Getters
    public BorderPane getMainFrameBP() {
        return mainFrameBP;
    }

    public Parent getView() {
        return view;
    }

    //Constructor
    public MainLayoutView(){
        this.menuBar = createMenuBar();
        this.view = createBorderPane();
    }

    public BorderPane createBorderPane(){
        String firstPageTitle = "Java alkalmazások előadás beadandó!";
        String firstPageDescription = "Az alkalmazás funkcióit a menüpontok segítségvel érheti el.";
        String firstPageDescription2 = "Az el nem készített menüpontoknak egy külön oldal készült, ami tartalmaz egy kis leírást";
        FirstPage firstPage = new FirstPage(firstPageTitle,firstPageDescription, firstPageDescription2);
        this.mainFrameBP = new BorderPane();
        this.mainFrameBP.setTop(this.menuBar);
        Label copyright = new Label("© 2023 - Bálint Gergely & Kádár Zoltán mérnökinformatikus hallgatók - All Rights Reserved!");
        copyright.setPadding(new Insets(10, 20,10,20));
        BorderPane.setAlignment(copyright, Pos.BOTTOM_CENTER);
        this.mainFrameBP.setBottom(copyright);
        this.mainFrameBP.setCenter(firstPage.getContainer());
        this.mainFrameBP.setMinWidth(300);
        return this.mainFrameBP;
    }

    public MenuBar createMenuBar(){
        HashMap<String,HashMap<String,String>> menu = new HashMap<>();
        HashMap<String,String> dbMenuItem = new HashMap<>();
        HashMap<String,String> rest1MenuItem = new HashMap<>();
        HashMap<String,String> rest2MenuItem = new HashMap<>();
        HashMap<String,String> soapKliensMenuItem = new HashMap<>();
        HashMap<String,String> dataCollectionMenuItem = new HashMap<>();
        HashMap<String,String> otherMenuItem = new HashMap<>();

        //Menu - Rest1
        //Rest1 - Almenük létrehozása
        rest1MenuItem.put("Olvas","rest1_read");
        rest1MenuItem.put("Ír","rest1_write");
        rest1MenuItem.put("Módosít","rest1_modify");
        rest1MenuItem.put("Töröl","rest1_delete");
        //Rest1 - Menupont létrehozása
        menu.put("Rest1", rest1MenuItem);

        //Menu - Rest2
        //Rest2 - Almenük létrehozása
        rest2MenuItem.put("Olvas","rest2_read");
        rest2MenuItem.put("Ír","rest2_write");
        rest2MenuItem.put("Módosít","rest2_modify");
        rest2MenuItem.put("Töröl","rest2_delete");
        //Rest2 - Menupont létrehozása
        menu.put("Rest2", rest2MenuItem);

        //Menu - SoapKliens
        //SoapKliens - Almenük létrehozása
        soapKliensMenuItem.put("Letöltés","sk_download");
        soapKliensMenuItem.put("Letöltés2","sk_download2");
        soapKliensMenuItem.put("Grafikon","sk_graph");
        //SoapKliens - Menupont létrehozása
        menu.put("SoapKliens", soapKliensMenuItem);

        //Menu - Adatgyűjtés
        //Adatgyűjtés - Almenük létrehozása
        dataCollectionMenuItem.put("Döntési fa","decision_tree");
        dataCollectionMenuItem.put("Több algoritmus","more_algorithm");
        dataCollectionMenuItem.put("Több algoritmus2","more_algorithm2");
        //Adatgyűjtés - Menupont létrehozása
        menu.put("Adatgyűjtés", dataCollectionMenuItem);

        //Menu - Egyéb
        //Egyéb - Almenük létrehozása
        otherMenuItem.put("Párhuzamos","other_parallel");
        otherMenuItem.put("Stream","other_stream");
        //Egyéb - Menupont létrehozása
        menu.put("Egyéb", otherMenuItem);

        //Menu - Adatbázis
        //Adatbázis - Almenük létrehozása
        dbMenuItem.put("Olvas","db_read");
        dbMenuItem.put("Olvas2","db_read2");
        dbMenuItem.put("Ír","db_write");
        dbMenuItem.put("Módosít","db_modify");
        dbMenuItem.put("Töröl","db_delete");
        //Adatbázis - Menupont létrehozása
        menu.put("Adatbázis", dbMenuItem);

        MenuBlueprint menuBlueprint = new MenuBlueprint(menu);
        return menuBlueprint.getMenuBar();
    }

}

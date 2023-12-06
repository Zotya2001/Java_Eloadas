package com.example.wpa_alpha.Modells;

import com.example.wpa_alpha.DataAccessObjects.ToronyDAO;
import com.example.wpa_alpha.PersistenceModels.Torony;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Empty{

    private VBox tableHolder;
    private VBox container;
    private Label title;
    private Label taskDescription;

    public VBox getContainer() {return container;}

    public Empty(String title, String taskDescription){
        this.title = new Label(title);
        this.title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        this.title.setPadding(new Insets(10,0,10,20));

        Label taskTitle = new Label("Leírás");
        taskTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        taskTitle.setPadding(new Insets(0,0,5,20));

        this.taskDescription = new Label(taskDescription);
        this.taskDescription.setFont(Font.font("Helvetica", 12));
        this.taskDescription.setPadding(new Insets(0,0,20,20));


        this.tableHolder = new VBox();
        this.tableHolder.setAlignment(Pos.CENTER);
        this.container = new VBox();
        this.container.getChildren().addAll(this.title, taskTitle , this.taskDescription);
    }


}

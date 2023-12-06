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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstPage{

    private VBox tableHolder;
    private VBox container;
    private Label title;
    private Label taskDescription;

    private Label taskDescription2;

    public VBox getContainer() {return container;}

    public FirstPage(String title, String taskDescription, String taskDescription2){
        VBox vBox = new VBox();
        vBox.alignmentProperty().set(Pos.BASELINE_CENTER);
        this.title = new Label(title);
        this.title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 35));
        this.title.setPadding(new Insets(250,0,0,0));


        this.taskDescription = new Label(taskDescription);
        this.taskDescription.setFont(Font.font("Helvetica", 20));
        this.taskDescription.setPadding(new Insets(10,0,0,0));


        this.taskDescription2 = new Label(taskDescription2);
        this.taskDescription2.setFont(Font.font("Helvetica", 10));
        this.taskDescription2.setPadding(new Insets(10,0,0,0));

        vBox.getChildren().addAll(this.title,this.taskDescription, this.taskDescription2);




        this.tableHolder = new VBox();
        this.container = new VBox();
        this.container.getChildren().addAll(vBox);

    }


}

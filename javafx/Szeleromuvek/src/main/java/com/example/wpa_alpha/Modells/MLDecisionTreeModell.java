package com.example.wpa_alpha.Modells;

import com.example.wpa_alpha.MachineLearning.MachineLearningClass;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MLDecisionTreeModell {
    private VBox container;

    private VBox filerBox;
    private Label title;
    private Label taskDescription;


    public VBox getWriteContainer() {return container;}

    public MLDecisionTreeModell(String title, String taskDescription){
        this.title = new Label(title);
        this.title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        this.title.setPadding(new Insets(10,20,10,20));

        Label choosedFileTitle = new Label("Választott .arff fájl:");
        choosedFileTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        choosedFileTitle.setPadding(new Insets(10,20,10,20));
        Label choosedFileDescription = new Label("A feladat megvalósításához a labor.arff fájlt választottuk, Döntési fa készítése gombra kattintva, létehozza a Döntési fa.txt fájlt\n" +
                "A fájlt a C://MachineLearning/Döntési fa.txt - könyvtárban éri el!");
        choosedFileDescription.setPadding(new Insets(0,20,5,20));

        Label taskTitle = new Label("Feladat:");
        taskTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        taskTitle.setPadding(new Insets(0,20,5,20));

        this.taskDescription = new Label(taskDescription);
        this.taskDescription.setFont(Font.font("Helvetica", 12));
        this.taskDescription.setPadding(new Insets(0,20,20,20));

        Label alert = new Label();
        alert.setPadding(new Insets(0,10,0,20));
        VBox buttonHolder = new VBox();
        Button makeDecisionTree = new Button("Döntési fa készítése");
        buttonHolder.getChildren().add(makeDecisionTree);
        buttonHolder.setPadding(new Insets(0,0,0,20));
        makeDecisionTree.setOnAction(actionEvent -> {
            String path = "src/main/resources/com/example/wpa_alpha/labor.arff";
            try{
                MachineLearningClass machineLearningClass = new MachineLearningClass(path, 16);
                alert.setTextFill(Color.GREEN);
                alert.setText("Sikeres tanítás");
            }catch (Exception e){
                alert.setTextFill(Color.RED);
                alert.setText("Hiba történt a tanítás során!");
            }

        });

        this.container = new VBox();
        this.container.getChildren().addAll(this.title, taskTitle , this.taskDescription, choosedFileTitle, choosedFileDescription, buttonHolder,alert);
    }
}

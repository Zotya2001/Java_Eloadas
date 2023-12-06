package com.example.wpa_alpha.Modells;

import com.example.wpa_alpha.MachineLearning.MachineLearningClass;
import com.example.wpa_alpha.MachineLearning.MoreMachineLearningClass;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import weka.classifiers.Sourcable;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Utils;

import java.sql.SQLOutput;

public class MLMoreAlgorithmModell {

    private VBox container;

    private VBox filerBox;
    private Label title;
    private Label taskDescription;


    public VBox getWriteContainer() {return container;}

    public MLMoreAlgorithmModell(String title, String taskDescription){
        this.title = new Label(title);
        this.title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        this.title.setPadding(new Insets(10,20,10,20));

        Label choosedFileTitle = new Label("Választott .arff fájl:");
        choosedFileTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        choosedFileTitle.setPadding(new Insets(10,20,10,20));
        Label choosedFileDescription = new Label("A feladat megvalósításához a labor.arff fájlt választottuk, Algoritmusok futtatása gombra kattintva, létehozza a Gépitanulás.txt fájlt\n" +
                "A fájlt a C://MachineLearning/Gépitanulás.txt - könyvtárban éri el!");
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
        Button makeDecisionTree = new Button("Algoritmusok futtatása");
        buttonHolder.getChildren().add(makeDecisionTree);
        buttonHolder.setPadding(new Insets(0,0,0,20));

        Label bestAlgorithmTitle = new Label("A legjobb algoritmus:");
        bestAlgorithmTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD,14));
        bestAlgorithmTitle.setPadding(new Insets(10,0,10,20));
        Label bestAlgorithmDescription = new Label();
        bestAlgorithmDescription.setPadding(new Insets(0,0,10,20));

        makeDecisionTree.setOnAction(actionEvent -> {
            String path = "src/main/resources/com/example/wpa_alpha/labor.arff";
            IBk classifier = new IBk();
            try {
                classifier.setOptions(Utils.splitOptions("-K 10"));
                new MoreMachineLearningClass(path, 16, classifier);
                new MoreMachineLearningClass(path, 16, new RandomForest());
                new MoreMachineLearningClass(path, 16,new NaiveBayes());
                new MoreMachineLearningClass(path, 16,new SMO());
                new MoreMachineLearningClass(path, 16,new J48());
                bestAlgorithmDescription.setText("Az algoritmus neve: " + MoreMachineLearningClass.getBestAlgorithm() +", a Correctly Classified Instance: " + MoreMachineLearningClass.getPrecision());
            } catch (Exception e) {
                bestAlgorithmDescription.setText("Az algoritmus neve: Hiba, a jósági tényezője: Hiba");
                throw new RuntimeException(e);
            }
        });


        this.container = new VBox();
        this.container.getChildren().addAll(this.title, taskTitle , this.taskDescription, choosedFileTitle, choosedFileDescription, buttonHolder,alert,bestAlgorithmTitle, bestAlgorithmDescription);
    }
}

package com.example.wpa_alpha.Modells;

import com.example.wpa_alpha.MachineLearning.MachineLearningClass;
import com.example.wpa_alpha.MachineLearning.MoreMachineLearningClass;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Utils;

public class MLMoreAlgorithmListModell {
    private VBox container;

    private VBox filerBox;
    private Label title;
    private Label taskDescription;
    private String solution = "";


    public VBox getWriteContainer() {return container;}

    public MLMoreAlgorithmListModell(String title, String taskDescription){
        this.title = new Label(title);
        this.title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        this.title.setPadding(new Insets(10,20,10,20));

        Label choosedFileTitle = new Label("Választott .arff fájl:");
        choosedFileTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        choosedFileTitle.setPadding(new Insets(10,20,10,20));
        Label choosedFileDescription = new Label("A feladat megvalósításához a labor.arff fájlt választottuk, az Algoritmus futtatása gombra kattintva a listából kiválasztott algoritmus alapján elvégezzük a gépi tanulást.\n" +
                "A tanítás eredménye a Tanítás ablakban fog látszódni!");
        choosedFileDescription.setPadding(new Insets(0,20,5,20));

        ComboBox<String> algorithmComboBox = new ComboBox<>();
        algorithmComboBox.getItems().add("KNN");
        algorithmComboBox.getItems().add("Random forest");
        algorithmComboBox.getItems().add("NaiveBayes");
        algorithmComboBox.getItems().add("SMO");
        algorithmComboBox.getItems().add("Decision tree");
        algorithmComboBox.setValue("Decision tree");
        VBox comboListHolder = new VBox();
        comboListHolder.getChildren().add(algorithmComboBox);
        comboListHolder.setPadding(new Insets(10,0,0,20));


        Label taskTitle = new Label("Feladat:");
        taskTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        taskTitle.setPadding(new Insets(0,20,5,20));

        this.taskDescription = new Label(taskDescription);
        this.taskDescription.setFont(Font.font("Helvetica", 12));
        this.taskDescription.setPadding(new Insets(0,20,20,20));
        Label alert = new Label();
        alert.setPadding(new Insets(0,10,0,20));
        VBox buttonHolder = new VBox();
        Button makeDecisionTree = new Button("Algoritmus futtatása");
        buttonHolder.getChildren().add(makeDecisionTree);
        buttonHolder.setPadding(new Insets(20,0,0,20));
        String path = "src/main/resources/com/example/wpa_alpha/labor.arff";

        Label solutionTitle = new Label("A tanítás megoldása: ");
        solutionTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        solutionTitle.setPadding(new Insets(0,20,5,20));
        Label solutionDescription = new Label();
        solutionDescription.setPadding(new Insets(0,10,0,20));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setContent(solutionDescription);

        makeDecisionTree.setOnAction(actionEvent -> {
            solutionDescription.setText("");
            switch (algorithmComboBox.getValue()){
                case "Decision tree":
                    solution = MoreMachineLearningClass.showChoosedAlgorithm(path, 16, new J48());
                    solutionDescription.setText(solution);
                    break;

                case "KNN":
                    IBk classifier = new IBk();
                    try {
                        classifier.setOptions(Utils.splitOptions("-K 10"));
                        solution = MoreMachineLearningClass.showChoosedAlgorithm(path, 16, classifier);
                        solutionDescription.setText(solution);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "Random forest" :
                    solution = MoreMachineLearningClass.showChoosedAlgorithm(path, 16, new RandomForest());
                    solutionDescription.setText(solution);
                    break;

                case "NaiveBayes":
                    solution = MoreMachineLearningClass.showChoosedAlgorithm(path, 16, new NaiveBayes());
                    solutionDescription.setText(solution);
                    break;

                case "SMO":
                    solution = MoreMachineLearningClass.showChoosedAlgorithm(path, 16, new SMO());
                    solutionDescription.setText(solution);
                    break;
                default:
                    solutionDescription.setText("Hiba keletkezett a tanítás során");
                    break;
            }
        });

        this.container = new VBox();
        this.container.getChildren().addAll(this.title, taskTitle , this.taskDescription, choosedFileTitle,choosedFileDescription,  comboListHolder, buttonHolder,alert, solutionTitle, scrollPane);
    }
}

package com.example.wpa_alpha.Modells;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.security.spec.RSAOtherPrimeInfo;

public class ParalellModell {
    private VBox container;

    public VBox getWriteContainer() {
        return container;
    }

    private Label title;
    private Label taskDescription;

    private Label firstThreadLabel;
    private Label secondThreadLabel;

    private GridPane gridPane;

    public ParalellModell(String title, String taskDescription) {
        this.title = new Label(title);
        this.title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        this.title.setPadding(new Insets(10, 20, 10, 20));

        Label taskTitle = new Label("Feladat:");
        taskTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        taskTitle.setPadding(new Insets(0, 20, 5, 20));

        this.taskDescription = new Label(taskDescription);
        this.taskDescription.setFont(Font.font("Helvetica", 12));
        this.taskDescription.setPadding(new Insets(0, 20, 20, 20));

        //GridPane
        gridPane = new GridPane();
        this.firstThreadLabel = new Label("Első szál");
        this.firstThreadLabel.setPadding(new Insets(10, 20, 10, 20));
        this.secondThreadLabel = new Label("Második szál");
        this.secondThreadLabel.setPadding(new Insets(10, 20, 10, 20));
        gridPane.add(firstThreadLabel, 0, 0);
        gridPane.add(secondThreadLabel, 1, 0);
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        //gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));


        this.container = new VBox();
        //container.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        this.container.getChildren().addAll(this.title, taskTitle, this.taskDescription ,gridPane);
        make();
    }

    public void make(){
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                for(int i=0; i<10; i++){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progress += 1;
                    String thread = "FirstThread" + progress;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            firstThreadLabel.setText(thread);
                        }
                    });
                }
            }
        });

        Thread taskThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                for(int i=0; i<10; i++){

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progress += 1;
                    String thread = "FirstThread" + progress;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            secondThreadLabel.setText(thread);
                        }
                    });
                }
            }
        });

        taskThread.start();
        taskThread2.start();
    }
}

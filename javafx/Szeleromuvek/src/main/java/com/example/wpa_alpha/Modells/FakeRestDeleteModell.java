package com.example.wpa_alpha.Modells;

import com.example.wpa_alpha.RestClient.FakeRestClient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class FakeRestDeleteModell {
    private VBox tableHolder;
    private VBox container;
    private Label title;
    private Label taskDescription;

    private VBox filterBox;

    public VBox getContainer() {return container;}

    public FakeRestDeleteModell(String title, String taskDescription){
        createFilterBox();

        this.title = new Label(title);
        this.title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
        this.title.setPadding(new Insets(10,0,10,20));

        Label taskTitle = new Label("Feladat:");
        taskTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
        taskTitle.setPadding(new Insets(0,0,5,20));

        this.taskDescription = new Label(taskDescription);
        this.taskDescription.setFont(Font.font("Helvetica", 12));
        this.taskDescription.setPadding(new Insets(0,0,20,20));


        this.tableHolder = new VBox();
        this.tableHolder.setAlignment(Pos.CENTER);
        this.container = new VBox();
        this.container.getChildren().addAll(this.title, taskTitle , this.taskDescription, this.filterBox);
    }

    public void createFilterBox(){
        Label mainTitle = new Label("Delete kérés küldése:");
        Label description = new Label("A kérés elküdéséhez egy azonosító számot kell megadni! Ha nem megfelelő azonosítót adunk meg, hiba keletkezik, mivel egy olyan erőforrást akarunk elérni, ami nem létezik!");
        Label description2 = new Label("A könnyebb tesztelhetőség érdekében itt van néhány azonosító, amin lehet tesztelni: 100, 300, 3369, 6139");
        Label description3 = new Label("Nem létező azonosítót is meg lehet adni, de akkor hibaüzenetet kapunk, az erőforrás adatai helyett!");
        Label mainTitle2 = new Label("Válasz a kérésre:");
        Label mainTitle3 = new Label("A adat tartalma:");

        Label userIdLabel = new Label("Adjon meg egy azonosítót");
        Label alertIdError = new Label();
        Label response = new Label();
        Label getResponse = new Label();
        alertIdError.setTextFill(Color.RED);
        alertIdError.setPadding(new Insets(5,0,5,0));
        description3.setPadding(new Insets(0,0,10,0));
        mainTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        mainTitle2.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
        mainTitle2.setPadding(new Insets(10,0,0,0));
        mainTitle3.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
        mainTitle3.setPadding(new Insets(10,0,0,0));
        userIdLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));

        VBox getRequestBox = new VBox();
        TextField userIdTextfield = new TextField();
        userIdTextfield.setMaxWidth(200);
        this.filterBox = new VBox();
        Button sendRequestButton = new Button("Kérés küldése");
        sendRequestButton.setOnAction(actionEvent -> {
            alertIdError.setText("");
            if(isNumeric(userIdTextfield.getText())){
                try {
                    FakeRestClient.GET(userIdTextfield.getText());
                    getResponse.setText(FakeRestClient.getGetResponseValue());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    FakeRestClient.DELETE(userIdTextfield.getText());
                    response.setText(FakeRestClient.getGetResponseValue() + "method: Delete");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else{
                alertIdError.setText("Számot kell megadni!");
            }
        });
        filterBox.getChildren().addAll(mainTitle, description, description2, description3, userIdLabel, userIdTextfield, alertIdError,sendRequestButton, mainTitle3, getResponse ,mainTitle2, response);
        filterBox.setPadding(new Insets(10,10,10,20));
        filterBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
        filterBox.setBorder(new Border((new BorderStroke(Color.DARKGRAY,BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT))));
    }

    public boolean isNumeric(String value){
        try {
            int number = Integer.parseInt(value);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

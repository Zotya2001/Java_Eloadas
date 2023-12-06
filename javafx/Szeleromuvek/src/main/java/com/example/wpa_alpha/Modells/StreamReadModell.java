package com.example.wpa_alpha.Modells;
import com.example.wpa_alpha.Modells.Stream.Database;
import com.example.wpa_alpha.Modells.Stream.Helyszin;
import com.example.wpa_alpha.Modells.Stream.Megye;
import com.example.wpa_alpha.Modells.Stream.Torony;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamReadModell {
        private VBox tableHolder;
        private VBox container;

        private VBox filerBox;
        private Label title;
        private Label taskDescription;

        private Button button;
        private Button clearButton;

        private TextField townTextField;
        private  Label seged;

        private ComboBox<Integer> idComboBox;

        private ComboBox<String> countyComboBox;

        private ToggleGroup toggleGroup;

        public VBox getFilteredContainer() {return container;}
        Database database;

        public StreamReadModell(String title, String taskDescription, Database database){
            this.database = database;
            createFilterBox();

            this.title = new Label(title);
            this.title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 25));
            this.title.setPadding(new Insets(10,20,10,20));

            Label taskTitle = new Label("Feladat:");
            taskTitle.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
            taskTitle.setPadding(new Insets(0,20,5,20));

            this.taskDescription = new Label(taskDescription);
            this.taskDescription.setFont(Font.font("Helvetica", 12));
            this.taskDescription.setPadding(new Insets(0,20,20,20));

            Label tableTitle = new Label("Táblázat adatai:");
            tableTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
            tableTitle.setPadding(new Insets(20,0,10,20));

            this.tableHolder = new VBox();
            this.tableHolder.setAlignment(Pos.CENTER);
            this.container = new VBox();
            this.tableHolder.getChildren().addAll(createTableView());
            StackPane stackPane = new StackPane();
            this.seged = new Label();
            this.seged.setText("Segéd");
            this.container.getChildren().addAll(this.title, taskTitle , this.taskDescription, filerBox, tableHolder,stackPane);
        }

        public TableView createTableView(){
            TableView<Torony> tableView = new TableView<Torony>();

            TableColumn<Torony,Integer> toronyIdColumn = new TableColumn<Torony,Integer>("Torony azonosító");
            toronyIdColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("id"));

            TableColumn<Torony,Integer> darabColumn = new TableColumn<Torony,Integer>("Darabszám");
            darabColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("darab"));

            TableColumn<Torony,Integer> teljesitmenyColumn = new TableColumn<Torony,Integer>("Teljesítmény");
            teljesitmenyColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("teljesítmény"));

            TableColumn<Torony,Integer> kezdevColumn = new TableColumn<Torony,Integer>("Üzembehelyezés éve");
            kezdevColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("kezdev"));

            TableColumn<Torony,Integer> helyszinAzonositoColumn = new TableColumn<Torony,Integer>("Helyszín azonosító");
            helyszinAzonositoColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("helyszinId"));

            tableView.getColumns().addAll(toronyIdColumn, darabColumn,teljesitmenyColumn,kezdevColumn,helyszinAzonositoColumn);

            List<Torony> toronyList = database.getToronyArrayList().stream().collect(Collectors.toList());

            for(Torony torony : toronyList){
                tableView.getItems().add(torony);
            }

            tableView.getItems();
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableView.setMaxWidth(710);



            return tableView;
        }

        public void createFilterBox(){
            this.filerBox = new VBox();
            Label mainTitle = new Label("Szűrés mező");
            mainTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));

            //Település nevére szűréshez
            VBox townVbox = new VBox();
            Label townLabel = new Label("Szűrés településnév alapján!");
            this.townTextField = new TextField();
            townTextField.setMaxWidth(200);
            townLabel.setPadding(new Insets(0,0,5,0));
            townTextField.promptTextProperty().set("Adjon meg egy település nevet");
            townVbox.getChildren().addAll(townLabel, townTextField);
            townVbox.setPadding(new Insets(0,0,10,0));
            //Hbox a lenyiló listáknak
            HBox comboHBox = new HBox();

            //Vbox az id lenyíló listának
            VBox idPickerBox = new VBox();
            Label idTitle = new Label("Szűrés azonosító alapján");
            idTitle.setPadding(new Insets(0,0,5,0));
            this.idComboBox = new ComboBox<>();
            idComboBox.setPrefWidth(200);
            for(Torony torony : this.database.getToronyArrayList())
            {
                idComboBox.getItems().add(torony.getId());
            }
            idPickerBox.getChildren().addAll(idTitle,idComboBox);
            idPickerBox.setPadding(new Insets(0,20,0,0));

            //Vbox megye lenyíló listának
            VBox countyPickerBox = new VBox();
            Label countyTitle = new Label("Szűrés megyenév alapján");
            countyTitle.setPadding(new Insets(0,0,5,0));
            this.countyComboBox = new ComboBox<>();
            countyComboBox.setPrefWidth(200);
            for(Megye megye : this.database.getMegyeArrayList())
            {
                countyComboBox.getItems().add(megye.getNev());
            }
            countyPickerBox.getChildren().addAll(countyTitle,countyComboBox);

            comboHBox.getChildren().addAll(idPickerBox,countyPickerBox);

            //Szűrés teljesítmény alapján
            Label radioTitle = new Label("Szűrés a teljesítményre intervallumok alapján");
            radioTitle.setPadding(new Insets(10,0,5,0));
            VBox radioBox = new VBox();
            this.toggleGroup = new ToggleGroup();
            RadioButton r0 = new RadioButton("Összes");
            RadioButton r1 = new RadioButton("0-500");
            RadioButton r2 = new RadioButton("500-1500");
            RadioButton r3 = new RadioButton("1500-2000");
            RadioButton r4 = new RadioButton(">2000");
            r0.setToggleGroup(toggleGroup);
            r1.setToggleGroup(toggleGroup);
            r2.setToggleGroup(toggleGroup);
            r3.setToggleGroup(toggleGroup);
            r4.setToggleGroup(toggleGroup);
            r0.setPadding(new Insets(0,0,5,0));
            r1.setPadding(new Insets(0,0,5,0));
            r2.setPadding(new Insets(0,0,5,0));
            r3.setPadding(new Insets(0,0,5,0));
            r4.setPadding(new Insets(0,0,5,0));
            radioBox.getChildren().addAll(r0,r1,r2,r3,r4);
            toggleGroup.selectToggle(r0);

            //ITT VAGYOK NEM MENTEM EL, CSAK VAK VAGY
            this.button = new Button("Szűrés");
            this.button.setOnAction(actionEvent -> {
                this.tableHolder.getChildren().clear();
                this.tableHolder.getChildren().add(createFilteredTableView());
            });

            this.clearButton = new Button("Alaphelyzet");
            this.clearButton.setOnAction(actionEvent -> {
                townTextField.clear();
                idComboBox.valueProperty().set(null);
                countyComboBox.valueProperty().set(null);
                toggleGroup.selectToggle(toggleGroup.getToggles().get(0));
            });

            filerBox.getChildren().addAll(mainTitle ,townVbox, comboHBox, radioTitle, radioBox, this.button, this.clearButton);
            filerBox.setPadding(new Insets(10,10,10,20));
            filerBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
            filerBox.setBorder(new Border((new BorderStroke(Color.DARKGRAY,BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT))));
        }

    public TableView createFilteredTableView(){
        TableView<Torony> tableView = new TableView<Torony>();

        TableColumn<Torony,Integer> toronyIdColumn = new TableColumn<Torony,Integer>("Torony azonosító");
        toronyIdColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("id"));

        TableColumn<Torony,Integer> darabColumn = new TableColumn<Torony,Integer>("Darabszám");
        darabColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("darab"));

        TableColumn<Torony,Integer> teljesitmenyColumn = new TableColumn<Torony,Integer>("Teljesítmény");
        teljesitmenyColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("teljesítmény"));

        TableColumn<Torony,Integer> kezdevColumn = new TableColumn<Torony,Integer>("Üzembehelyezés éve");
        kezdevColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("kezdev"));

        TableColumn<Torony,Integer> helyszinAzonositoColumn = new TableColumn<Torony,Integer>("Helyszín azonosító");
        helyszinAzonositoColumn.setCellValueFactory(new PropertyValueFactory<Torony,Integer>("helyszinId"));

        tableView.getColumns().addAll(toronyIdColumn, darabColumn,teljesitmenyColumn,kezdevColumn,helyszinAzonositoColumn);

        List<Torony> toronyList = collectData();
        for(Torony torony : toronyList){
            tableView.getItems().add(torony);
        }
        tableView.getItems();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setMaxWidth(710);


        return tableView;
    }

        public List<Torony> collectData(){
            RadioButton rb;
            List<Megye> megyeResult = new ArrayList<>();
            rb = (RadioButton)toggleGroup.getSelectedToggle();
            seged.setText(townTextField.getText() + "-" + this.idComboBox.getValue() + "-" + this.countyComboBox.getValue() + "-" + rb.getText());
            if(this.countyComboBox.getValue() != null){
                megyeResult = database.getMegyeArrayList().stream().filter(megye -> this.countyComboBox.getValue().equals(megye.getNev())).collect(Collectors.toList());
            }else{
                megyeResult = database.getMegyeArrayList().stream().collect(Collectors.toList());
            }


            List<Helyszin> helyszinResult = new ArrayList<>();
            String town = townTextField.getText();
            if(town==""){
                for (Megye megye : megyeResult){
                    int id = megye.getId();
                    List<Helyszin> helyszinData = database.getHelyszinArrayList().stream().filter(h2 -> h2.getMegyeid() == id).collect(Collectors.toList());
                    helyszinResult.addAll(helyszinData);
                }

            }else{
                for (Megye megye : megyeResult){
                    int id = megye.getId();
                    List<Helyszin> helyszinData = database.getHelyszinArrayList().stream().filter(h1 -> town.equals(h1.getNev())).filter(h2 -> h2.getMegyeid() == id).collect(Collectors.toList());
                    helyszinResult.addAll(helyszinData);
                }
            }

            String selectedRadioValue = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
            List<Torony> toronyResult = new ArrayList<>();
            int openIntervall;
            int closeIntervall;
            if(selectedRadioValue.contains("-")){
                String intervalls[] = selectedRadioValue.split("-");
                openIntervall = Integer.parseInt(intervalls[0]);
                closeIntervall = Integer.parseInt(intervalls[1]);

                if(this.idComboBox.getValue() != null && helyszinResult.size() != 0){
                    int toronyId = this.idComboBox.getValue();
                    for (Helyszin helyszin : helyszinResult){
                        int id = helyszin.getId();
                        List<Torony> toronyData = database.getToronyArrayList().stream().filter(t1 -> t1.getId() == toronyId).filter(t2 -> t2.getHelyszinId() == id).filter(t3 -> t3.getTeljesítmény() > openIntervall).filter(t4 -> t4.getTeljesítmény() < closeIntervall).collect(Collectors.toList());
                        toronyResult.addAll(toronyData);
                    }
                }else{

                    for (Helyszin helyszin : helyszinResult){
                        int id = helyszin.getId();
                        List<Torony> toronyData = database.getToronyArrayList().stream().filter(t2 -> t2.getHelyszinId() == id).filter(t3 -> t3.getTeljesítmény() > openIntervall).filter(t4 -> t4.getTeljesítmény() < closeIntervall).collect(Collectors.toList());
                        toronyResult.addAll(toronyData);
                    }
                }
            }else if(selectedRadioValue.contains(">")){
                String intervalls[] = selectedRadioValue.split(">");
                openIntervall = Integer.parseInt(intervalls[1]);
                if(this.idComboBox.getValue() != null && helyszinResult.size() != 0){
                    int toronyId = this.idComboBox.getValue();
                    for (Helyszin helyszin : helyszinResult){
                        int id = helyszin.getId();
                        List<Torony> toronyData = database.getToronyArrayList().stream().filter(t1 -> t1.getId() == toronyId).filter(t2 -> t2.getHelyszinId() == id).filter(t3 -> t3.getTeljesítmény() > openIntervall).collect(Collectors.toList());
                        toronyResult.addAll(toronyData);
                    }
                }else{

                    for (Helyszin helyszin : helyszinResult){
                        int id = helyszin.getId();
                        List<Torony> toronyData = database.getToronyArrayList().stream().filter(t2 -> t2.getHelyszinId() == id).filter(t3 -> t3.getTeljesítmény() > openIntervall).collect(Collectors.toList());
                        toronyResult.addAll(toronyData);
                    }
                }
            }else{
                if(this.idComboBox.getValue() != null && helyszinResult.size() != 0){
                    int toronyId = this.idComboBox.getValue();
                    for (Helyszin helyszin : helyszinResult){
                        int id = helyszin.getId();
                        List<Torony> toronyData = database.getToronyArrayList().stream().filter(t1 -> t1.getId() == toronyId).filter(t2 -> t2.getHelyszinId() == id).collect(Collectors.toList());
                        toronyResult.addAll(toronyData);
                    }
                }else{

                    for (Helyszin helyszin : helyszinResult){
                        int id = helyszin.getId();
                        List<Torony> toronyData = database.getToronyArrayList().stream().filter(t2 -> t2.getHelyszinId() == id).collect(Collectors.toList());
                        toronyResult.addAll(toronyData);
                    }
                }
            }
            return  toronyResult;
        }
}

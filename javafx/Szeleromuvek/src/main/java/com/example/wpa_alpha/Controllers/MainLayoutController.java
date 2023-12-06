package com.example.wpa_alpha.Controllers;

import com.example.wpa_alpha.MachineLearning.MachineLearningClass;
import com.example.wpa_alpha.MachineLearning.MoreMachineLearningClass;
import com.example.wpa_alpha.Modells.*;
import com.example.wpa_alpha.Modells.Stream.Database;
import com.example.wpa_alpha.RestClient.FakeRestClient;
import com.example.wpa_alpha.Views.MainLayoutView;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.RandomForest;
import weka.core.Utils;

import java.util.ArrayList;

public class MainLayoutController {
    public MainLayoutController(MainLayoutView view) {
        setView(view);
    }

    public void setView(MainLayoutView view) {
        String helyszinURL = "src/main/resources/com/example/wpa_alpha/helyszin.txt";
        String megyeURL = "src/main/resources/com/example/wpa_alpha/megye.txt";
        String toronyURL = "src/main/resources/com/example/wpa_alpha/torony.txt";
        Database streamDatabase = new Database(helyszinURL, megyeURL, toronyURL);
        FakeRestClient fakeRestClient = new FakeRestClient();

        for (int i = 0; i < view.getMenuBar().getMenus().size(); i++) {
            int finalI = i;
            for (int j = 0; j < view.getMenuBar().getMenus().get(i).getItems().size(); j++) {
                int finalJ = j;
                view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).setOnAction(actionEvent ->
                {
                    switch (view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getId()){


                        case "rest1_read":
                            String fakeRestReadTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String fakeRestReadTaskDescription = "Készítsen egy Restful klienst az eladáson bemutatott https://gorest.co.in Rest szerverhez, amely megvalósítja a Read műveletet(GET metódus)";
                            FakeRestReadModell fakeRestReadModell = new FakeRestReadModell(fakeRestReadTitle, fakeRestReadTaskDescription);
                            view.getMainFrameBP().setCenter(fakeRestReadModell.getContainer());
                            break;

                        case "rest1_write":
                            String fakeRestWriteTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String fakeRestWriteTaskDescription = "Készítsen egy Restful klienst az eladáson bemutatott https://gorest.co.in Rest szerverhez, amely megvalósítja a Write műveletet(POST metódus)";
                            FakeRestWriteModell fakeRestWriteModell = new FakeRestWriteModell(fakeRestWriteTitle, fakeRestWriteTaskDescription);
                            view.getMainFrameBP().setCenter(fakeRestWriteModell.getContainer());
                            break;

                        case "rest1_modify":
                            String fakeRestModifyTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String fakeRestModifyTaskDescription = "Készítsen egy Restful klienst az eladáson bemutatott https://gorest.co.in Rest szerverhez, amely megvalósítja a Modify műveletet(PUT metódus)";
                            FakeRestModifyModell fakeRestModifyModell = new FakeRestModifyModell(fakeRestModifyTitle, fakeRestModifyTaskDescription);
                            view.getMainFrameBP().setCenter(fakeRestModifyModell.getContainer());
                            break;

                        case "rest1_delete":
                            String fakeRestDeleteTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String fakeRestDeleteTaskDescription = "Készítsen egy Restful klienst az eladáson bemutatott https://gorest.co.in Rest szerverhez, amely megvalósítja a Delete műveletet(DELETE metódus)";
                            FakeRestDeleteModell fakeRestDeleteModell = new FakeRestDeleteModell(fakeRestDeleteTitle, fakeRestDeleteTaskDescription);
                            view.getMainFrameBP().setCenter(fakeRestDeleteModell.getContainer());
                            break;


                            //Adatgyűjtés menü

                        case "decision_tree":
                            String decisionTreeTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String decisionTreeDescription = "Készítsenek döntési fával gépi tanuló algoritmus a kiválasztott attribútum előrejelzésére. Eredményül írassák ki a Döntési fa.txt fájlba a következő adatokat: \n" +
                                    "Tanító és kiértékelő halmaz mérete, \n" +
                                    "TP, TN, FP, FN, Correctly Classified Instances, Incorrectly Classified Instances\n" +
                                    "A döntési fa kiíratása\n" +
                                    "A kiértékelő halmaz minden egyedére a prediktált és a valós érték\n";
                            MLDecisionTreeModell mlDecisionTree = new MLDecisionTreeModell(decisionTreeTitle, decisionTreeDescription);
                            view.getMainFrameBP().setCenter(mlDecisionTree.getWriteContainer());
                            break;

                        case "more_algorithm":
                            String moreAlgorithmTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String moreAlgorithmDescription = "A választott adathalmazra futtassa le a tanult algoritmusokat. A Gépi tanulás.txt fájlba írassa ki mindegyikre: \n" +
                                    "TP, TN, FP, FN, Correctly Classified Instances, Incorrectly Classified Instances\n" +
                                    "Válassza ki az algoritmusok közül azt, amelyiknek a legjobb lett a Correctly Classified Instances eredménye és írassa ki az algoritmus nevét az alkalmazás ablakába.\n";
                            MLMoreAlgorithmModell mlMoreAlgorithmModell = new MLMoreAlgorithmModell(moreAlgorithmTitle, moreAlgorithmDescription);
                            view.getMainFrameBP().setCenter(mlMoreAlgorithmModell.getWriteContainer());
                            break;

                        case "more_algorithm2":
                            String moreAlgorithmListTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String moreAlgorithmListDescription = "Lenyíló listából lehessen választani a tanult algoritmusok közül. Az algoritmust lefuttatva írassa ki az alkalmazás ablakába a következő adatokat:\n" +
                                    "TP, TN, FP, FN, Correctly Classified Instances, Incorrectly Classified Instances\n";
                            MLMoreAlgorithmListModell mlMoreAlgorithmListModell = new MLMoreAlgorithmListModell(moreAlgorithmListTitle, moreAlgorithmListDescription);
                            view.getMainFrameBP().setCenter(mlMoreAlgorithmListModell.getWriteContainer());
                            break;

                            //Egyéb menü

                        case "other_parallel":
                            String parallelTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String parallelTaskDescription = "Mutassa be a párhuzamos programvégrehajtást egy oldalon. pl. egy gombra való kattintás után egy Label-ben 1 másodpercenként, a másik Labelbe 2 másodpercenként jelenjen meg egy változó szöveg. 10x fog végrehajtódni utánna megszűnik a párhuzamosság";
                            ParalellModell paralellModell = new ParalellModell(parallelTitle, parallelTaskDescription);
                            view.getMainFrameBP().setCenter(paralellModell.getWriteContainer());
                            break;

                        case "other_stream":
                            String streamTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String streamTaskDescription = "Olvassa ki a válsztott adatbázis minden adatát egy Stream-be. Késztsen egy űrlapot (beviteli mezők, lenyíló lista, radio gomb, jelölő négyzet), ahol több szűrőfeltételt is beállíthat az adatokra. Jelenítse meg egy táblázatban a szűrt adatokat.";
                            StreamReadModell streamReadModell = new StreamReadModell(streamTitle,streamTaskDescription,streamDatabase);
                            view.getMainFrameBP().setCenter(streamReadModell.getFilteredContainer());
                            break;

                        default:
                            String emptyTitle = view.getMenuBar().getMenus().get(finalI).getText() + " - " + view.getMenuBar().getMenus().get(finalI).getItems().get(finalJ).getText() + " almenü";
                            String emptyTaskDescription = "A feladatnak ezen része az idő hiánya miatt nem készült el.";
                            Empty empty = new Empty(emptyTitle,emptyTaskDescription);
                            view.getMainFrameBP().setCenter(empty.getContainer());
                            break;
                    }

                });
            }
        }
    }
}

package com.example.wpa_alpha.MachineLearning;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.io.*;
import java.util.Random;

public class MachineLearningClass {
    public MachineLearningClass(String fileName, int classIndex) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Instances instances = new Instances(bufferedReader);
            //System.out.println("instances.size():" + instances.size());
            instances.setClassIndex(classIndex);
            Instances tanító, kiértékelő;
            J48 classifier;
            Evaluation evaluation;
            classifier = new J48();

            //System.out.println("\nGépiTanulás1: Randomize után vagy anélkül: tanító: első 90%, kiértékelő: utolsó 10%");
            if (false) instances.randomize(new Random());
            tanító = new Instances(instances, 0, 9 * instances.size() / 10);
            //System.out.println("tanító.size():" + tanító.size());
            kiértékelő = new Instances(instances, 9 * instances.size() / 10, instances.size() / 10);
            //System.out.println("kiértékelő.size():" + kiértékelő.size());
            classifier.buildClassifier(tanító);
            evaluation = new Evaluation(kiértékelő);
            double[] eredmeny = evaluation.evaluateModel(classifier, kiértékelő);
            /*
            System.out.println(evaluation.toSummaryString("\nResults", false));
            System.out.println("Correctly Classified Instances:" + (int) evaluation.correct());
            System.out.println("Incorrectly Classified Instances:" + (kiértékelő.size() - (int) evaluation.correct()));
            System.out.println(classifier.toString());
             */

            //System.out.println("\nGépi tanulás vizsgálata részletesen:");
            int TP=0, TN=0, FP=0, FN=0;
            String solution = "";
            //  TP:TtruePositive, TN:TrueNegative, FP:FalsePositive, FN:FalseNegative
            for(int i=0;i<kiértékelő.size();i++){
                solution += (i+1)+"\t"+(((Instances)kiértékelő).get(i)).classValue()+"\t"+eredmeny[i]+"\n";
                //System.out.println(i+"\t"+(((Instances)kiértékelő).get(i)).classValue()+"\t"+eredmeny[i]);
                if((((Instances)kiértékelő).get(i)).classValue()==1 && eredmeny[i]==1)
                    TP++;
                if((((Instances)kiértékelő).get(i)).classValue()==1 && eredmeny[i]==0)
                    FN++;
                if((((Instances)kiértékelő).get(i)).classValue()==0 && eredmeny[i]==1)
                    FP++;
                if((((Instances)kiértékelő).get(i)).classValue()==0 && eredmeny[i]==0)
                    TN++;
            }

            String allSolution = "";
            allSolution += "A feladat megoldása döntési fával!\n";
            allSolution += "Tanító halmaz mérete:" + tanító.size() +"\n";
            allSolution +="Kiértékelő halmaz mérete:" + kiértékelő.size()+"\n";
            allSolution +="A gépi tanulás vizsgálata részletesen: "+"\n";
            allSolution +="TP="+TP+", "+"TN="+TN+", "+"FP="+FP+", "+"FN="+FN+"\n";
            allSolution +="Correctly Classified Instances:" + (int) evaluation.correct()+"\n";
            allSolution +="Incorrectly Classified Instances:" + (kiértékelő.size() - (int) evaluation.correct())+"\n";
            allSolution +="Predikált értékek: <vizsgált elem száma> <predikált értéke> <valós értéke>"+"\n";
            allSolution +=solution+"\n";
            allSolution +=classifier.toString()+"\n";
            allSolution +="\n"+"\n";


            writeToFile(allSolution);


        } catch (Exception e) {
            System.out.println("Hiba lépett fel a feldolgozás közben!" + e.getMessage());
        }
    }

    void writeToFile(String input) throws IOException {
        File file = new File("C:\\MachineLearning\\Döntési fa.txt");
        if (!file.getParentFile().mkdirs()){
            BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
            out.write(input);
            out.newLine();
            out.close();
        }else{
            BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
            out.write(input);
            out.newLine();
            out.close();
        }
    }
}

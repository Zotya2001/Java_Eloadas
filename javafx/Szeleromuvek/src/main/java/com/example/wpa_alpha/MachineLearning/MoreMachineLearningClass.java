package com.example.wpa_alpha.MachineLearning;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.io.*;
import java.util.Random;

public class MoreMachineLearningClass {
    public static Double getPrecision() {
        return precision;
    }

    public static String getBestAlgorithm() {
        return bestAlgorithm;
    }

    private static Double precision = 0.0;
    private static String bestAlgorithm;

    public static Classifier getBestClassifier() {
        return bestClassifier;
    }

    private static Classifier bestClassifier;

    public MoreMachineLearningClass(String fileName, int classIndex, Classifier classifier) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Instances instances = new Instances(bufferedReader);

            instances.setClassIndex(classIndex);
            Instances tanító, kiértékelő;
            Evaluation evaluation;
            Classifier newClassifier = classifier;

            if (false) instances.randomize(new Random());
            tanító = new Instances(instances, 0, 9 * instances.size() / 10);
            //System.out.println("tanító.size():" + tanító.size());
            kiértékelő = new Instances(instances, 9 * instances.size() / 10, instances.size() / 10);
            //System.out.println("kiértékelő.size():" + kiértékelő.size());
            newClassifier.buildClassifier(tanító);
            evaluation = new Evaluation(kiértékelő);
            double[] eredmeny = evaluation.evaluateModel(newClassifier, kiértékelő);

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
            allSolution += "Tanító halmaz mérete:" + tanító.size() +"\n";
            allSolution +="Kiértékelő halmaz mérete:" + kiértékelő.size()+"\n";
            allSolution +="A gépi tanulás vizsgálata részletesen: "+"\n";
            allSolution +="TP="+TP+", "+"TN="+TN+", "+"FP="+FP+", "+"FN="+FN+"\n";
            allSolution +="Correctly Classified Instances:" + (int) evaluation.correct()+"\n";
            allSolution +="Incorrectly Classified Instances:" + (kiértékelő.size() - (int) evaluation.correct())+"\n";
            allSolution +="\n"+"\n";

            writeToFile(allSolution);

            if((int) evaluation.correct() > precision){
                precision = (double) evaluation.correct();
                bestAlgorithm = classifier.getClass().toString();
                bestClassifier = classifier;
            }

        } catch (Exception e) {
            System.out.println("Error Occurred!!!! \n" + e.getMessage());
        }
    }

    public static String showChoosedAlgorithm(String fileName, int classIndex, Classifier classifier) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            Instances instances = new Instances(bufferedReader);

            instances.setClassIndex(classIndex);
            Instances tanító, kiértékelő;
            Evaluation evaluation;
            Classifier newClassifier = classifier;

            if (false) instances.randomize(new Random());
            tanító = new Instances(instances, 0, 9 * instances.size() / 10);
            //System.out.println("tanító.size():" + tanító.size());
            kiértékelő = new Instances(instances, 9 * instances.size() / 10, instances.size() / 10);
            //System.out.println("kiértékelő.size():" + kiértékelő.size());
            newClassifier.buildClassifier(tanító);
            evaluation = new Evaluation(kiértékelő);
            double[] eredmeny = evaluation.evaluateModel(newClassifier, kiértékelő);

            int TP = 0, TN = 0, FP = 0, FN = 0;
            String solution = "";
            //  TP:TtruePositive, TN:TrueNegative, FP:FalsePositive, FN:FalseNegative
            for (int i = 0; i < kiértékelő.size(); i++) {
                solution += (i + 1) + "\t" + (((Instances) kiértékelő).get(i)).classValue() + "\t" + eredmeny[i] + "\n";
                //System.out.println(i+"\t"+(((Instances)kiértékelő).get(i)).classValue()+"\t"+eredmeny[i]);
                if ((((Instances) kiértékelő).get(i)).classValue() == 1 && eredmeny[i] == 1)
                    TP++;
                if ((((Instances) kiértékelő).get(i)).classValue() == 1 && eredmeny[i] == 0)
                    FN++;
                if ((((Instances) kiértékelő).get(i)).classValue() == 0 && eredmeny[i] == 1)
                    FP++;
                if ((((Instances) kiértékelő).get(i)).classValue() == 0 && eredmeny[i] == 0)
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
            return allSolution;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        void writeToFile(String input) throws IOException {
        File file = new File("C:\\MachineLearning\\Gépitanulás.txt");
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

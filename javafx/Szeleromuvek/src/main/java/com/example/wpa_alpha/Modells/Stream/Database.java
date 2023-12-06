package com.example.wpa_alpha.Modells.Stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private ArrayList<Helyszin> helyszinArrayList;
    private ArrayList<Megye> megyeArrayList;
    private ArrayList<Torony> toronyArrayList;

    public Database(String helyszinURL, String megyeURL, String toronyURL) {
        this.helyszinArrayList = new ArrayList<>();
        this.megyeArrayList = new ArrayList<>();
        this.toronyArrayList = new ArrayList<>();
        try {
            //Helyszin beolvas
            File myObj = new File(helyszinURL);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] helyszinArray = data.split("\t");
                Helyszin helyszin = new Helyszin(Integer.parseInt(helyszinArray[0]), helyszinArray[1], Integer.parseInt(helyszinArray[2]));
                this.helyszinArrayList.add(helyszin);
                //System.out.println(data);
            }
            myReader.close();

            //Megye beolvas
            File myObj2 = new File(megyeURL);
            Scanner myReader2 = new Scanner(myObj2);
            myReader2.nextLine();
            while (myReader2.hasNextLine()) {
                String data = myReader2.nextLine();
                String[] megyeArray = data.split("\t");
                Megye megye = new Megye(Integer.parseInt(megyeArray[0]), megyeArray[1], megyeArray[2]);
                this.megyeArrayList.add(megye);
                //System.out.println(data);
            }
            myReader2.close();

            //Torony beolvas
            File myObj3 = new File(toronyURL);
            Scanner myReader3 = new Scanner(myObj3);
            myReader3.nextLine();
            while (myReader3.hasNextLine()) {
                String data = myReader3.nextLine();
                String[] toronyArray = data.split("\t");
                Torony torony = new Torony(Integer.parseInt(toronyArray[0]), Integer.parseInt(toronyArray[1]), Integer.parseInt(toronyArray[2]), Integer.parseInt(toronyArray[3]), Integer.parseInt(toronyArray[4]));
                this.toronyArrayList.add(torony);
                //System.out.println(data);
            }
            myReader3.close();


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public ArrayList<Helyszin> getHelyszinArrayList() {
        return helyszinArrayList;
    }

    public ArrayList<Megye> getMegyeArrayList() {
        return megyeArrayList;
    }

    public ArrayList<Torony> getToronyArrayList() {
        return toronyArrayList;
    }
}

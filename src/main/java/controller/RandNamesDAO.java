package controller;

import model.RandNames;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.*;


public class RandNamesDAO {
    List<RandNames> randNamesList = new ArrayList<>();

    public String time = "";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final String PATH = "save.csv";

    private final String SEPARATOR = ", ";

    public void addNameList(String randName) {
        try {
            randNamesList.add(new RandNames(randName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getNameOnList(int randNr){
        String name = randNamesList.get(randNr).getName();
        return name;
    }
    // Gibt die vollständige Namensliste zurück
    public void setAllNames(List<RandNames> list) {
        randNamesList.clear();
        randNamesList.addAll(list);
    }

    // Optional: Größe der Liste zurückgeben
    public int getListSize() {
        return randNamesList.size();
    }

    public void save(){

        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(PATH);
            String liste;
            liste= now().format(formatter)+SEPARATOR+System.lineSeparator();
            fileWriter.write(liste);
            for (RandNames r : randNamesList) {
                String randName = r.getName();
                liste = randName + SEPARATOR + System.lineSeparator();
                fileWriter.write(liste);
            }
        }catch (IOException e){
            System.out.println("Fehler: "+ e.getMessage());
        }finally {
            if (fileWriter != null) {
                try{
                    fileWriter.close();
                }catch (IOException e){
                    System.out.println("Fehler: "+ e.getMessage());
                }
            }
        }
    }

    ArrayList<RandNames> load() {
        ArrayList<RandNames> list = new ArrayList<>();
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(PATH);
            BufferedReader bReader = new BufferedReader(fileReader);

            // Erstes Lesen = Zeitstempelzeile
            String ersteZeile = bReader.readLine();
            if (ersteZeile != null && !ersteZeile.isEmpty()) {
                String[] zeitLine = ersteZeile.split(SEPARATOR);
                if (zeitLine.length > 0) {
                    time = LocalDateTime.parse(zeitLine[0], formatter).format(formatter);
                }
            }

            // Dann Teilnehmer einlesen
            String readLine;
            while ((readLine = bReader.readLine()) != null) {
                String[] line = readLine.split(SEPARATOR);
                list.add(new RandNames(line[0]));
            }

        } catch (IOException e) {
            System.err.println("Fehler: " + e.getMessage());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.err.println("Fehler: " + e.getMessage());
                }
            }
        }

        return list;
    }
}
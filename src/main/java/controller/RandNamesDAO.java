package controller;

import model.RandNames;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static java.time.LocalDateTime.*;

/**
 * Diese Klasse verwaltet die Teilnehmerliste für das Gewinnspiel.
 * Sie bietet Funktionen zum Hinzufügen, Speichern und Laden der Namen,
 * einschließlich Zeitstempelverwaltung und Dateiorganisation.
 */

public class RandNamesDAO {
    List<RandNames> randNamesList = new ArrayList<>();
    // Variablen zum Speichern der Zeit vor dem Dateinamen und in der Liste
    public String time = "";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private final DateTimeFormatter saveDateFormat = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_");

    private final String SEPARATOR = ", ";

    public void addNameList(String randName) {
        try {
            randNamesList.add(new RandNames(randName));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getNameOnList(int randNr){
        return randNamesList.get(randNr).getName();
    }

    public int getListSize() {
        return randNamesList.size();
    }

    public void saveData() {
        String liste = now().format(formatter) + SEPARATOR + System.lineSeparator();
        String date = now().format(saveDateFormat);
        File saveDir = new File("saves");
        // Anlegen des Ordners für die Save-Dateien
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        File file = new File(saveDir, "save_" + date + ".csv");

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(liste);
            for (RandNames r : randNamesList) {
                fileWriter.write(r.getName() + SEPARATOR + System.lineSeparator());
            }
            System.out.println("Gespeichert unter: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public void loadData(File file) {
        randNamesList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String ersteZeile = reader.readLine(); // Zeit
            if (ersteZeile != null) {
                String[] parts = ersteZeile.split(SEPARATOR);
                if (parts.length > 0) {
                    time = LocalDateTime.parse(parts[0], formatter).format(formatter);
                }
            }
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                randNamesList.add(new RandNames(parts[0]));
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    public void resetList(){
        randNamesList.clear();
    }
}
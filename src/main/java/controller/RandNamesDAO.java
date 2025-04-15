package controller;

import model.RandNames;

import java.util.ArrayList;
import java.util.List;

public class RandNamesDAO {
    List<RandNames> randNamesList = new ArrayList<>();

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
    // Gibt die vollständige Namensliste zurück (z. B. für Anzeige oder Logik)
    public List<RandNames> getAllNames() {
        return randNamesList;
    }

    // Optional: Größe der Liste zurückgeben
    public int getListSize() {
        return randNamesList.size();
    }
}

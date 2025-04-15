package controller;

import model.RandNames;

import java.util.ArrayList;

public class RandNamesDAO {
    ArrayList<RandNames> randNamesList = new ArrayList<>();

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
}

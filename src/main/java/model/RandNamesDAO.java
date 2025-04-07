package model;

import java.util.ArrayList;

public class RandNamesDAO {
    ArrayList<RandNames> randNamesList;

    public void addNameList(String randName) {
        randNamesList.add(new RandNames(randName));
    }
}

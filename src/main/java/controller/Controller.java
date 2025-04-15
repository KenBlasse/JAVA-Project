package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;
import java.util.stream.IntStream;

public class Controller {

    static String SEPERATOR = ", ";
    private RandNamesDAO dao = new RandNamesDAO();
    private RandNamesDAO tempDao = new RandNamesDAO();

    @FXML
    private Label winnerLabel, teilnehmerLabel;

    @FXML
    private Button onClickStartBtn, onClickAddBtn;

    @FXML
    private TextField teilnehmer;

    @FXML
    private TextArea showWinner, showTeilnehmer;

    @FXML
    protected void onClickAddBtn() {
        String addTeilnehmer = teilnehmer.getText();
        if (showTeilnehmer.getText().isEmpty()) {
            showTeilnehmer.setText(addTeilnehmer+SEPERATOR);
        }else {
            String setTeilnehmer = showTeilnehmer.getText();
            showTeilnehmer.setText(setTeilnehmer + addTeilnehmer+SEPERATOR);
        }

        if (showWinner.getText().isEmpty()) dao.addNameList(addTeilnehmer);
        else dao.addNameList(addTeilnehmer);

        teilnehmer.setText("");
        teilnehmer.requestFocus();
    }

    @FXML
    protected void onClickStartBtn() {
            int rand = dao.randNamesList.size();
            System.out.println(rand);
            int winnerNr = getRandomNumberUsingInts(1, rand);
            System.out.println(winnerNr);
            dao.getNameOnList(winnerNr);
    }

    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
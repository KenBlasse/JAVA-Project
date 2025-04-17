package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Random;

public class Controller {

    static String SEPERATOR = ", ";
    final RandNamesDAO dao = new RandNamesDAO();

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
        int rand = dao.getListSize();
        System.out.println(rand);
            int winnerNr = getRandomNumber(rand);
            // System.out.println(winnerNr);
            String winner = dao.getNameOnList(winnerNr);
            // System.out.println(winner);
        if (showWinner.getText().contains(winner)){
            Alert found = new Alert(Alert.AlertType.INFORMATION);
            found.setTitle("Schade");
            found.setHeaderText(winner + " wurde bereits gezogen!");
            found.showAndWait();
        }else {
            if (showWinner.getText().isEmpty()) {
                showWinner.setText(winner + SEPERATOR);
            } else {
                String setWinner = showWinner.getText();
                showWinner.setText(winner + SEPERATOR + setWinner);
            }
            Alert winInfo = new Alert(Alert.AlertType.INFORMATION);
            winInfo.setTitle("Winner");
            winInfo.setHeaderText("Gewonnen hat: ");
            winInfo.setContentText(winner);
            winInfo.showAndWait();
        }
    }

    public int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

}
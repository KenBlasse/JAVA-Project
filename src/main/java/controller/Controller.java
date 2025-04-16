package controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.util.Random;

import static java.util.Arrays.compare;

public class Controller {

    static String SEPERATOR = ", ";
    private RandNamesDAO dao = new RandNamesDAO();
    private RandNamesDAO tempDao = new RandNamesDAO();

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
        if (showWinner.getText().isEmpty()) {
            showWinner.setText(winner+ SEPERATOR);
        }else {
            String setWinner = showWinner.getText();
            showWinner.setText(winner + SEPERATOR + setWinner);
        }



        Alert winInfo = new Alert(Alert.AlertType.INFORMATION);
        winInfo.setTitle("\uD83C\uDF89 Winner");
        winInfo.setHeaderText(null);

        Label content = new Label("Gewonnen hat:\n"+winner);
        content.setStyle("-fx-font-size: 25px; -fx-text-fill: blue;");
        content.setWrapText(true);
        winInfo.getDialogPane().setContent(content);
        winInfo.showAndWait();
    }

    public int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

}
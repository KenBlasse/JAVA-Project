package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import model.RandNames;

import java.util.List;
import java.util.Random;

public class Controller {

    static String SEPARATOR = ", ";
    final RandNamesDAO dao = new RandNamesDAO();

    @FXML
    private TextField teilnehmer;

    @FXML
    private TextArea showWinner, showTeilnehmer;

    @FXML
    private Label loaded;

    @FXML
    protected void onClickAddBtn() {
        String addTeilnehmer = teilnehmer.getText();
        if (showTeilnehmer.getText().isEmpty()) {
            showTeilnehmer.setText(addTeilnehmer + SEPARATOR);
        } else {
            String setTeilnehmer = showTeilnehmer.getText();
            showTeilnehmer.setText(setTeilnehmer + addTeilnehmer + SEPARATOR);
        }

        if (showWinner.getText().isEmpty()) dao.addNameList(addTeilnehmer);
        else dao.addNameList(addTeilnehmer);

        teilnehmer.setText("");
        teilnehmer.requestFocus();
    }

    @FXML
    protected void onClickStartBtn() {
        int rand = dao.getListSize();
        int winnerNr = getRandomNumber(rand);
        String winner = dao.getNameOnList(winnerNr);

        if (showWinner.getText().contains(winner)) {
            Alert found = new Alert(Alert.AlertType.INFORMATION);
            found.setTitle("Schade");
            found.setHeaderText(null);
            Label alreadyWon = new Label(winner + " hat bereits gewonnen!");
            alreadyWon.setStyle("-fx-font-size: 25px; -fx-text-fill: red;");
            alreadyWon.setWrapText(true);
            found.getDialogPane().setContent(alreadyWon);
            found.showAndWait();
        } else {
            if (showWinner.getText().isEmpty()) {
                showWinner.setText(winner + SEPARATOR);
            } else {
                String setWinner = showWinner.getText();
                showWinner.setText(winner + SEPARATOR + setWinner);
            }
            Alert winInfo = new Alert(Alert.AlertType.INFORMATION);
            winInfo.setTitle("\uD83C\uDF89Winner");
            winInfo.setHeaderText(null);

            Label content = new Label("Gewonnen hat:\n" + winner);
            content.setStyle("-fx-font-size: 25px; -fx-text-fill: green;");
            content.setWrapText(true);
            winInfo.getDialogPane().setContent(content);
            winInfo.showAndWait();
        }
    }

    @FXML
    protected void onClickSaveBtn() {
        dao.save();
    }

    @FXML
    protected void onClickLoadBtn() {
        List<RandNames> geladeneNamen = dao.load();
        loaded.setText(dao.time);
        showTeilnehmer.clear();
        showWinner.clear();
        dao.setAllNames(geladeneNamen);
        for (int j = 0; j < dao.getListSize(); j++) {
            String name = dao.getNameOnList(j);
            if (showTeilnehmer.getText().isEmpty()) {
                showTeilnehmer.setText(name + SEPARATOR);
            } else {
                String setTeilnehmer = showTeilnehmer.getText();
                showTeilnehmer.setText(setTeilnehmer + name + SEPARATOR);
            }
        }
    }

    @FXML
    protected void onClickResetBtn() {
        showTeilnehmer.clear();
        showWinner.clear();
        loaded.setText(null);
    }

    public int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
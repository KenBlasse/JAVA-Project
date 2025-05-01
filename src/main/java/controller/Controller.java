package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

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
        // Teilnehmer hinzufügen
        String addParticipant = teilnehmer.getText();
        if (showTeilnehmer.getText().isEmpty()) {
            showTeilnehmer.setText(addParticipant + SEPARATOR);
        } else {
            String setParticipant = showTeilnehmer.getText();
            showTeilnehmer.setText(setParticipant + addParticipant + SEPARATOR);
        }

        if (showWinner.getText().isEmpty()) dao.addNameList(addParticipant);
        else dao.addNameList(addParticipant);

        teilnehmer.setText("");
        // nach Drücken des Buttons, Fokus wieder auf Eingabefeld, um direkt weiter eingeben zu können
        teilnehmer.requestFocus();
    }

    @FXML
    protected void onClickStartBtn() {
        // Wenn Liste leer, dann Hinweise darauf
        if (dao.getListSize() == 0 ) {
            Alert gameOver = new Alert(Alert.AlertType.INFORMATION);
            gameOver.setTitle("Keine Teilnehmer vorhanden");
            gameOver.setHeaderText(null);
            Label msg = new Label("Die Liste ist leer");
            msg.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");
            msg.setWrapText(true);
            gameOver.getDialogPane().setContent(msg);
            gameOver.showAndWait();
            return;
        // Wenn alle Teilnehmer gezogen wurde, Hinweis darauf
        } else if (showWinner.getText().split(SEPARATOR).length >= dao.getListSize()) {
            Alert gameOver = new Alert(Alert.AlertType.INFORMATION);
            gameOver.setTitle("Game Over");
            gameOver.setHeaderText(null);
            Label msg = new Label("Es wurden bereits alle Teilnehmer gezogen!");
            msg.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");
            msg.setWrapText(true);
            gameOver.getDialogPane().setContent(msg);
            gameOver.showAndWait();
            return;
        }

        String winner;
        do {
            int rand = dao.getListSize();
            int winnerNr = getRandomNumber(rand);
            winner = dao.getNameOnList(winnerNr);
        }
        while (showWinner.getText().contains(winner));

        if (showWinner.getText().isEmpty()){
            showWinner.setText(winner + SEPARATOR);
        }else{
            String alreadyWon = showWinner.getText();
            showWinner.setText(alreadyWon + winner + SEPARATOR);
        }

        Alert winInfo = new Alert(Alert.AlertType.INFORMATION);
        winInfo.setTitle("\uD83C\uDF89 Winner");
        winInfo.setHeaderText(null);
        Label content = new Label("Gewonnen hat:\n" + winner);
        content.setStyle("-fx-font-size: 25px; -fx-text-fill: green;");
        content.setWrapText(true);
        winInfo.getDialogPane().setContent(content);
        winInfo.showAndWait();
    }

    @FXML // speichern mit Datum
    protected void onClickSaveBtn() {
        dao.saveData();
    }

    @FXML // laden einer save File über Filechooser
    protected void onClickLoadBtn() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("CSV-Datei laden");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV-Dateien", "*.csv"));
        // Filechooser öffnet direkt den saves Ordner
        File saveDir = new File("saves");
        if (saveDir.exists()) {
            fileChooser.setInitialDirectory(saveDir);
        }

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null){
            dao.loadData(selectedFile);
            loaded.setText(dao.time);
            for (int j = 0; j < dao.getListSize(); j++){
                String name = dao.getNameOnList(j);
                if (showTeilnehmer.getText().isEmpty()) {
                showTeilnehmer.setText(name + SEPARATOR);
                    } else {
                        String setParticipant = showTeilnehmer.getText();
                        showTeilnehmer.setText(setParticipant + name + SEPARATOR);
                    }
            }
        }
    }

    @FXML // löscht alle Daten
    protected void onClickResetBtn() {
        showTeilnehmer.clear();
        showWinner.clear();
        loaded.setText(null);
        dao.resetList();
    }

    public int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
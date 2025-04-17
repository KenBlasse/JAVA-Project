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
    private Label loaded;

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
            found.setHeaderText(null);
            Label alreadyWon = new Label(winner + " hat bereits gewonnen!");
            alreadyWon.setStyle("-fx-font-size: 25px; -fx-text-fill: red;");
            alreadyWon.setWrapText(true);
            found.getDialogPane().setContent(alreadyWon);
            found.showAndWait();
        }else {
            if (showWinner.getText().isEmpty()) {
                showWinner.setText(winner + SEPERATOR);
            } else {
                String setWinner = showWinner.getText();
                showWinner.setText(winner + SEPERATOR + setWinner);
            }
            Alert winInfo = new Alert(Alert.AlertType.INFORMATION);
            winInfo.setTitle("\uD83C\uDF89Winner");
            winInfo.setHeaderText(null);

            Label content = new Label("Gewonnen hat:\n"+winner);
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
        dao.load();
        loaded.setText(dao.getNameOnList(0));
        System.out.println(dao.getNameOnList(0));
        showTeilnehmer.setText("");
        for (int j = 1; j < dao.getListSize(); j++) {
            String name = dao.getNameOnList(j);
            if (showTeilnehmer.getText().isEmpty()) {
                showTeilnehmer.setText(name+SEPERATOR);
            }else {
                String setTeilnehmer = showTeilnehmer.getText();
                showTeilnehmer.setText(setTeilnehmer + name+SEPERATOR);
            }
        }
    }

    @FXML
    protected void onClickResetBtn() {}

    public int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

}
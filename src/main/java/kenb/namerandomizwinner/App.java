package kenb.namerandomizwinner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Einstiegspunkt der JavaFX-Anwendung.
 * Diese Klasse lädt die Hauptoberfläche aus der FXML-Datei
 * und startet die Benutzeroberfläche des Programms.
 */

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 480);
        stage.setTitle("Winner Winner Chickendinner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package orange;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Orange using FXML.
 */
public class Main extends Application {
    private Orange orange = new Orange("data/orange.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Orange Task Manager");
            fxmlLoader.<MainWindow>getController().setOrange(orange);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
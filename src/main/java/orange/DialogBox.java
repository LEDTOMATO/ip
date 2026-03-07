package orange;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Custom control for dialog box.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a user dialog box (right-aligned).
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // User messages - blue/gray background
        db.dialog.setStyle("-fx-background-color: #E3F2FD; -fx-background-radius: 15; -fx-padding: 12; -fx-font-size: 13px; -fx-border-color: #BBDEFB; -fx-border-radius: 15; -fx-border-width: 1;");
        return db;
    }

    /**
     * Creates an Orange dialog box (left-aligned, orange theme).
     */
    public static DialogBox getOrangeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        // Orange messages - orange background
        db.dialog.setStyle("-fx-background-color: #FFE5CC; -fx-background-radius: 15; -fx-padding: 12; -fx-font-size: 13px; -fx-border-color: #FF8C00; -fx-border-radius: 15; -fx-border-width: 2;");
        return db;
    }

    /**
     * Creates an error dialog with red styling.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        // Error messages - red background
        db.dialog.setStyle("-fx-background-color: #FFEBEE; -fx-background-radius: 15; -fx-padding: 12; -fx-font-size: 13px; -fx-border-color: #F44336; -fx-border-radius: 15; -fx-border-width: 2; -fx-text-fill: #C62828; -fx-font-weight: bold;");
        return db;
    }
}
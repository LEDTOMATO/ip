package orange;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Orange orange;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image orangeImage = new Image(this.getClass().getResourceAsStream("/images/DaOrange.png"));

    @FXML
    public void initialize() {
        // Bind scroll to always show latest messages
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Show welcome message
        dialogContainer.getChildren().add(
                DialogBox.getOrangeDialog("Hello! I'm Orange\nWhat can I do for you?", orangeImage)
        );
    }

    public void setOrange(Orange o) {
        orange = o;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }

        String response = orange.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOrangeDialog(response, orangeImage)
        );
        userInput.clear();

        // Force scroll to bottom
        scrollPane.setVvalue(1.0);
    }
}
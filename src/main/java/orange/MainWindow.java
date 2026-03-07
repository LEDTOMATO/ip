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

        // Show welcome message with orange theme
        String welcomeMessage = "🍊 Hello! I'm Orange!\n\n" +
                "Your friendly task manager is ready to help.\n" +
                "What would you like to do today? 😊";

        dialogContainer.getChildren().add(
                DialogBox.getOrangeDialog(welcomeMessage, orangeImage)
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

        // Add user message
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        // Add Orange response (with error styling if needed)
        if (response.startsWith("Error:") || response.contains("Oops!") || response.contains("⚠")) {
            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(response, orangeImage)
            );
        } else {
            dialogContainer.getChildren().add(
                    DialogBox.getOrangeDialog(response, orangeImage)
            );
        }

        userInput.clear();
        scrollPane.setVvalue(1.0);
    }
}
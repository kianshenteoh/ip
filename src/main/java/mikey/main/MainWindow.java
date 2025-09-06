package mikey.main;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import mikey.ui.DialogBox;


/**
 * Controller for the main GUI.
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

    private Mikey mikey;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image mikeyImage = new Image(this.getClass().getResourceAsStream("/images/mikey.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setMikey(Mikey d) {
        mikey = d;

        String greeting = mikey.getUi().greet();
        dialogContainer.getChildren().add(
                DialogBox.getMikeyDialog(greeting, mikeyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mikey.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMikeyDialog(response, mikeyImage)
        );
        userInput.clear();

        if (mikey.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.millis(300));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}

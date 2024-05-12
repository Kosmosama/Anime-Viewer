package animelist.main;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class LogInController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView buttonBackSignUp;

    @FXML
    private ImageView exitButton;

    @FXML
    private Button buttonLogIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    private Button buttonSignUpRedirection;

    @FXML
    private PasswordField inputLogInPassword;

    @FXML
    private TextField inputLogInUsername;

    @FXML
    private PasswordField inputSignUpPassword;

    @FXML
    private TextField inputSignUpUsername;

    @FXML
    private Pane paneLogIn;

    @FXML
    private Pane paneSignUp;

    @FXML
    void exitProgram(MouseEvent event) {
        fadeOutAndClose();
    }

    @FXML
    void showSignUpPane(MouseEvent event) {
        fadeInPane(paneSignUp);
        fadeOutPane(paneLogIn);
        paneSignUp.toFront();
    }

    @FXML
    void backToLogIn(MouseEvent event) {
        fadeInPane(paneLogIn);
        fadeOutPane(paneSignUp);
        paneLogIn.toFront();
    }

    /* Fade animations */

    private void fadeInPane(Pane pane) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void fadeOutPane(Pane pane) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), pane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    private void fadeOutAndClose() {
        Rectangle overlay = new Rectangle(borderPane.getWidth(), borderPane.getHeight(), Color.BLACK);
        overlay.setOpacity(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(400), overlay);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished((ActionEvent event) -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });

        borderPane.getChildren().add(overlay);
        fadeTransition.play();
    }
}
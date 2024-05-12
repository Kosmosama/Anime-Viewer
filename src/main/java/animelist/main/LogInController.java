package animelist.main;

import animelist.common.Utils;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import java.io.IOException;

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
    void logIn(MouseEvent event) {
        Utils.loadSceneUponButtonPress(event,"/animelist/main/animeList.fxml");
    }

    @FXML
    void exitProgram(MouseEvent event) {
        Utils.fadeOutAndClose(borderPane, exitButton);
    }

    @FXML
    void showSignUpPane(MouseEvent event) {
        Utils.fadeInNode(paneSignUp);
        Utils.fadeOutNode(paneLogIn);
        paneSignUp.toFront();
    }

    @FXML
    void backToLogIn(MouseEvent event) {
        Utils.fadeInNode(paneLogIn);
        Utils.fadeOutNode(paneSignUp);
        paneLogIn.toFront();
    }
}
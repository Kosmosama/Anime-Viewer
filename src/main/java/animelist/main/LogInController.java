package animelist.main;

import animelist.common.other.*;
import animelist.common.user_related.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

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
    private ImageView loginShowPasswordButton;

    @FXML
    private ImageView signupShowPasswordButton;

    @FXML
    private TextField loginVisiblePasswordTextField;

    @FXML
    private TextField signupVisiblePasswordTextField;

    Image notVisibleEye = new Image("animelist/icons/eye-icons/not-visible/black/24x24.png");
    Image visibleEye = new Image("animelist/icons/eye-icons/visible/black/24x24.png");

    @FXML
    void logIn(MouseEvent event) {
        String username = inputLogInUsername.getText();
        String password = inputLogInPassword.getText();

        // Check if username and password are not empty
        if (username.isEmpty() || password.isEmpty()) {
            Utils.showAlert("Please enter both username and password.");
            return;
        }

        // Authenticate user
        User user = new User(username, password);
        int userExists = user.exists();
        if (userExists > 0) {
            boolean isAdmin = userExists == 2;
            Utils.loadAnimeContollerSceneUponButtonPress(event, "/animelist/main/animeList.fxml", username, isAdmin);
        } else {
            Utils.showAlert("Invalid username or password.");
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        String username = inputSignUpUsername.getText();
        String password = inputSignUpPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Utils.showAlert("Please enter both username and password.");
            return;
        }

        User newUser = new User(username.toLowerCase(), password);
        if (newUser.exists() == 0) {
            Utils.showAlert("Username already exists. Please choose a different username.");
            return;
        }

        newUser.saveUser();

        Utils.showFeedbackAlert("User signed up successfully!");
    }

    @FXML
    void exitProgram(MouseEvent event) {
        Utils.fadeOutAndClose(borderPane, exitButton);
    }

    @FXML
    void showSignUpPane(MouseEvent event) {
        inputLogInUsername.setText("");
        inputLogInPassword.setText("");

        Utils.fadeInNode(paneSignUp);
        Utils.fadeOutNode(paneLogIn);
        paneSignUp.toFront();
    }

    @FXML
    void backToLogIn(MouseEvent event) {
        inputSignUpUsername.setText("");
        inputSignUpPassword.setText("");

        Utils.fadeInNode(paneLogIn);
        Utils.fadeOutNode(paneSignUp);
        paneLogIn.toFront();
    }

    @FXML
    void loginShowPassword(MouseEvent event) {
        inputLogInPassword.setVisible(false);
        loginVisiblePasswordTextField.setVisible(true);
        loginShowPasswordButton.setImage(visibleEye);
        loginVisiblePasswordTextField.setText(inputLogInPassword.getText());
    }

    @FXML
    void loginHidePassword(MouseEvent event) {
        loginVisiblePasswordTextField.setVisible(false);
        inputLogInPassword.setVisible(true);
        loginShowPasswordButton.setImage(notVisibleEye);
        inputLogInPassword.setText(loginVisiblePasswordTextField.getText());
    }

    @FXML
    void signupShowPassword(MouseEvent event) {
        inputSignUpPassword.setVisible(false);
        signupVisiblePasswordTextField.setVisible(true);
        signupShowPasswordButton.setImage(visibleEye);
        signupVisiblePasswordTextField.setText(inputSignUpPassword.getText());
    }

    @FXML
    void signupHidePassword(MouseEvent event) {
        signupVisiblePasswordTextField.setVisible(false);
        inputSignUpPassword.setVisible(true);
        signupShowPasswordButton.setImage(notVisibleEye);
        inputSignUpPassword.setText(signupVisiblePasswordTextField.getText());
    }
}

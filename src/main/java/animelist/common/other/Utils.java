package animelist.common.other;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

/**
 * Utility class providing common functions for working with JavaFX applications.
 */
public class Utils {
    /**
     * Fades out a {@link BorderPane} and closes the stage containing the specified button.
     *
     * @param borderPane The BorderPane to fade out.
     * @param button     The ImageView button used to locate the stage to close.
     */
    public static void fadeOutAndClose(BorderPane borderPane, ImageView button) {
        Rectangle overlay = new Rectangle(borderPane.getWidth(), borderPane.getHeight(), Color.BLACK);
        overlay.setOpacity(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(400), overlay);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished((ActionEvent event) -> {
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        });

        borderPane.getChildren().add(overlay);
        fadeTransition.play();
    }

    /**
     * Loads a new scene specified by the scenePath upon a button press event.
     *
     * @param event     The MouseEvent triggering the scene change.
     * @param scenePath The path to the FXML file of the scene to load.
     */
    public static void loadSceneUponButtonPress(MouseEvent event, String scenePath) {
        try {
            FXMLLoader loader = new FXMLLoader(Utils.class.getResource(scenePath));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Stage newStage = new Stage();

            Utils.stageTinkerer(newStage, new Scene(root));

            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configures the provided stage with specified attributes and shows it.
     *
     * @param stage The stage to configure.
     * @param scene The scene to set for the stage.
     */
    public static void stageTinkerer(Stage stage, Scene scene)
    {
        stage.setTitle("Anime Watchlist");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.getIcons().add(new Image(Objects.requireNonNull(Utils.class.getResourceAsStream("/animelist/icons/icon.png"))));

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        stage.setX((screenWidth - 1016) / 2);
        stage.setY((screenHeight - 576) / 2);

        stage.show();
    }

    /**
     * Displays an alert dialog with the specified message.
     *
     * @param message The message to display in the alert dialog.
     */
    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Displays an alert dialog for feedback with the specified message.
     *
     * @param message The message to display in the alert dialog.
     */
    public static void showFeedbackAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feedback");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Fades in a JavaFX Node.
     *
     * @param node The node to fade in.
     */
    public static void fadeInNode(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    /**
     * Fades out a JavaFX Node.
     *
     * @param node The node to fade out.
     */
    public static void fadeOutNode(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), node);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }
}

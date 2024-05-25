package animelist.main;

import animelist.common.list_related.Anime;
import animelist.common.other.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class AnimeListController {

    @FXML
    private ImageView alExitButton;

    @FXML
    private Button alFilterButton;

    @FXML
    private ImageView alLeft;

    @FXML
    private ImageView alLogOutButton;

    @FXML
    private ImageView alRight;

    @FXML
    private TextField alSearchTextField;

    @FXML
    private BorderPane animeListBorderPane;

    @FXML
    private Button animeListRedirection;

    @FXML
    private BorderPane watchListBorderPane;

    @FXML
    private Button watchListRedirection;

    @FXML
    private ImageView wlExitButton;

    @FXML
    private Button wlFilterButton;

    @FXML
    private ImageView wlLeft;

    @FXML
    private ImageView wlLogOutButton;

    @FXML
    private ImageView wlRight;

    @FXML
    private TextField wlSearchTextField;

    @FXML
    private TableView<Anime> wlTable;

    @FXML
    private TableView<Anime> alTable;

    @FXML
    private TableColumn<Anime, String> alImageColumn;

    @FXML
    private TableColumn<Anime, String> alNameColumn;

    @FXML
    private TableColumn<Anime, Integer> alEpisodesColumn;

    @FXML
    private TableColumn<Anime, Integer> alYearColumn;

    @FXML
    private TableColumn<Anime, Float> alScoreColumn;

    @FXML
    private TableColumn<Anime, String> alGenresColumn;

    ObservableList<Anime> data;

    @FXML
    public void initialize() {
        alImageColumn.setCellValueFactory(new PropertyValueFactory("image"));
        alNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        alEpisodesColumn.setCellValueFactory(new PropertyValueFactory("episodes"));
        alYearColumn.setCellValueFactory(new PropertyValueFactory("year"));
        alScoreColumn.setCellValueFactory(new PropertyValueFactory("score"));
        alGenresColumn.setCellValueFactory(new PropertyValueFactory("genre"));

        data = FXCollections.observableArrayList();

        for (int i = 1; i <= 30; i++) {
            data.add(new Anime(
                    "imageURL" + i,
                    "Anime " + i,
                    "Genre " + i,
                    12 + (i % 24),
                    2000 + (i % 24),
                    7.0f + (i % 3)
            ));
        }

        alTable.setItems(data);
    }

    @FXML
    void exitProgramWl(MouseEvent event) {
        Utils.fadeOutAndClose(watchListBorderPane, wlExitButton);
    }

    @FXML
    void exitProgramAl(MouseEvent event) {
        Utils.fadeOutAndClose(animeListBorderPane, alExitButton);
    }

    @FXML
    void showWatchList(MouseEvent event)
    {
        Utils.fadeInNode(watchListBorderPane);
        Utils.fadeOutNode(animeListBorderPane);
        watchListBorderPane.toFront();
    }

    @FXML
    void showAnimeList(MouseEvent event)
    {
        Utils.fadeInNode(animeListBorderPane);
        Utils.fadeOutNode(watchListBorderPane);
        animeListBorderPane.toFront();
    }

    @FXML
    void logOutAl(MouseEvent event)
    {
        Utils.loadSceneUponButtonPress(event,"/animelist/main/login.fxml");
    }

    @FXML
    void logOutWl(MouseEvent event)
    {
        Utils.loadSceneUponButtonPress(event,"/animelist/main/login.fxml");
    }
}

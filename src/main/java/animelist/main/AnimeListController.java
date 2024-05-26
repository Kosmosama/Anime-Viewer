package animelist.main;

import animelist.common.list_related.Anime;
import animelist.common.list_related.Animelist;
import animelist.common.other.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;

public class AnimeListController {
    private static final int ROWS_PER_PAGE = 14;
    private int currentPage = 0;

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

    private Animelist animelist;

    private void updateTable() {
        int fromIndex = currentPage * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, animelist.getAnimelist().size());
        alTable.setItems(FXCollections.observableArrayList(animelist.getAnimelist().subList(fromIndex, toIndex)));

        alLeft.setVisible(currentPage > 0);
        alRight.setVisible((currentPage + 1) * ROWS_PER_PAGE < animelist.getAnimelist().size());
    }

    @FXML
    void handleLeftButton(MouseEvent event) {
        if (currentPage > 0) {
            currentPage--;
            updateTable();
        }
    }

    @FXML
    void handleRightButton(MouseEvent event) {
        if ((currentPage + 1) * ROWS_PER_PAGE < animelist.getAnimelist().size()) {
            currentPage++;
            updateTable();
        }
    }

    @FXML
    public void initialize() {
        alImageColumn.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        alNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        alEpisodesColumn.setCellValueFactory(new PropertyValueFactory<>("episodes"));
        alYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        alScoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        alGenresColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        alImageColumn.setCellFactory(param -> new TableCell<>() {
            private final ImageView imageView = new ImageView();
            {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    Image image = new Image(item);
                    imageView.setImage(image);
                    imageView.setFitWidth(80);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });

        animelist = new Animelist();
        updateTable();
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
    void showWatchList(MouseEvent event) {
        Utils.fadeInNode(watchListBorderPane);
        Utils.fadeOutNode(animeListBorderPane);
        watchListBorderPane.toFront();
    }

    @FXML
    void showAnimeList(MouseEvent event) {
        Utils.fadeInNode(animeListBorderPane);
        Utils.fadeOutNode(watchListBorderPane);
        animeListBorderPane.toFront();
    }

    @FXML
    void logOutAl(MouseEvent event) {
        Utils.loadSceneUponButtonPress(event, "/animelist/main/login.fxml");
    }

    @FXML
    void logOutWl(MouseEvent event) {
        Utils.loadSceneUponButtonPress(event, "/animelist/main/login.fxml");
    }
}

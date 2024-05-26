package animelist.main;

import animelist.common.list_related.Anime;
import animelist.common.list_related.Animelist;
import animelist.common.list_related.Watchlist;
import animelist.common.other.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class AnimeListController {
    private static final int ROWS_PER_PAGE = 14;
    private int currentPageAl = 0;
    private int currentPageWl = 0;

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
    private TableColumn<Anime, String> wlImageColumn;

    @FXML
    private TableColumn<Anime, String> wlNameColumn;

    @FXML
    private TableColumn<Anime, Integer> wlEpisodesColumn;

    @FXML
    private TableColumn<Anime, Integer> wlYearColumn;

    @FXML
    private TableColumn<Anime, Float> wlScoreColumn;

    @FXML
    private TableColumn<Anime, String> wlGenresColumn;

    @FXML
    private TableColumn<Anime, String> wlActionColumn;

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

    @FXML
    private TableColumn<Anime, String> alActionColumn;

    @FXML
    private Label alAdminPrivilegesLabel;

    @FXML
    private Label alUsernameLabel;

    @FXML
    private Label wlAdminPrivilegesLabel;

    @FXML
    private Label wlUsernameLabel;

    private Animelist animelist;
    private Set<Integer> bannedAnimeIds = new HashSet<>();

    private String username;
    private boolean isAdmin;
    private Watchlist watchlist;

    public void setUserInfo(String username, boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;

        alUsernameLabel.setText("User: " + username);
        wlUsernameLabel.setText("User: " + username);

        alAdminPrivilegesLabel.setVisible(isAdmin);
        wlAdminPrivilegesLabel.setVisible(isAdmin);
    }

    private void loadBannedAnimeIds() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/animelist/data/banned/banned.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bannedAnimeIds.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLeftButtonWl(MouseEvent event) {
        if (currentPageWl > 0) {
            currentPageWl--;
            updateWatchlistTable();
        }
    }

    @FXML
    void handleRightButtonWl(MouseEvent event) {
        ObservableList<Anime> watchList = watchlist.getWatchlist();
        if ((currentPageWl + 1) * ROWS_PER_PAGE < watchList.size()) {
            currentPageWl++;
            updateWatchlistTable();
        }
    }

    private void updateWatchlistTable() {
        if (watchlist == null) {
            return;
        }

        ObservableList<Anime> watchList = watchlist.getWatchlist();

        int fromIndex = currentPageWl * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, watchList.size());

        ObservableList<Anime> subList = FXCollections.observableArrayList(watchList.subList(fromIndex, toIndex));
        wlTable.setItems(subList);

        wlLeft.setVisible(currentPageWl > 0);
        wlRight.setVisible((currentPageWl + 1) * ROWS_PER_PAGE < watchList.size());
    }

    private void updateTable() {
        Set<Integer> addedAnimeIds = new HashSet<>();

        ObservableList<Anime> animeList = FXCollections.observableArrayList();
        for (Anime anime : animelist.getAnimelist()) {
            if (!bannedAnimeIds.contains(anime.getId()) && addedAnimeIds.add(anime.getId())) {
                animeList.add(anime);
            }
        }

        int fromIndex = currentPageAl * ROWS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ROWS_PER_PAGE, animeList.size());

        ObservableList<Anime> subList = FXCollections.observableArrayList(animeList.subList(fromIndex, toIndex));
        alTable.setItems(subList);

        alLeft.setVisible(currentPageAl > 0);
        alRight.setVisible((currentPageAl + 1) * ROWS_PER_PAGE < animeList.size());
    }

    @FXML
    void handleLeftButtonAl(MouseEvent event) {
        if (currentPageAl > 0) {
            currentPageAl--;
            updateTable();
        }
    }

    @FXML
    void handleRightButtonAl(MouseEvent event) {
        if ((currentPageAl + 1) * ROWS_PER_PAGE < animelist.getAnimelist().size()) {
            currentPageAl++;
            updateTable();
        }
    }

    @FXML
    public void initialize() {
        loadBannedAnimeIds();
        setupColumns();

        animelist = new Animelist();
        watchlist = new Watchlist(username);

        updateTable();
        updateWatchlistTable();
    }

    private void setupColumns() {
        alImageColumn.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        alNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        alEpisodesColumn.setCellValueFactory(new PropertyValueFactory<>("episodes"));
        alYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        alScoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        alGenresColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        wlImageColumn.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        wlNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        wlEpisodesColumn.setCellValueFactory(new PropertyValueFactory<>("episodes"));
        wlYearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        wlScoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        wlGenresColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        // Set up cell factories for image and action columns
        setupImageColumn(alImageColumn);
        setupImageColumn(wlImageColumn);
        setupActionColumn(alActionColumn);
        setupActionColumn(wlActionColumn);
    }

    private void setupImageColumn(TableColumn<Anime, String> column) {
        column.setCellFactory(param -> new TableCell<>() {
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
    }

    private void setupActionColumn(TableColumn<Anime, String> column) {
        column.setCellFactory(param -> new TableCell<>() {
            private final Button banButton = new Button("Ban");
            private final Button watchlistButton = new Button("Add to Watchlist");

            {
                banButton.setOnAction(event -> {
                    Anime anime = getTableView().getItems().get(getIndex());
                    banAnime(anime);
                });
                watchlistButton.setOnAction(event -> {
                    Anime anime = getTableView().getItems().get(getIndex());
                    addToWatchlist(anime);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    VBox vBox = new VBox(5);
                    if (isAdmin) {
                        vBox.getChildren().addAll(banButton, watchlistButton);
                    } else {
                        vBox.getChildren().add(watchlistButton);
                    }
                    setGraphic(vBox);
                }
            }
        });
    }

    private void banAnime(Anime anime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/animelist/data/banned/banned.txt", true))) {
            writer.write(String.valueOf(anime.getId()));
            writer.newLine();
            bannedAnimeIds.add(anime.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        animelist.removeAnime(anime);
        updateTable();
    }

    private void addToWatchlist(Anime anime) {
        watchlist.addAnime(anime);
        updateWatchlistTable();
    }

    private void removeFromWatchlist(Anime anime) {
        watchlist.removeAnime(anime);
        updateWatchlistTable();
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

package views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Callback;
import commen.Player;
import tic_tac_toe_server.Tic_tac_toe_server;

public class ServerView extends AnchorPane {

    protected final ImageView imageView;
    protected final ListView PlayerListView;
    protected final ImageView imageView0;
    protected final Label label;
    protected final Label label0;
    protected final ToggleButton toggleButton;
    protected final Label label1;
    protected final Label playerNumbers;
    protected final Label label3;
    protected final Line line;
    protected Tic_tac_toe_server conroller;

    public ServerView(Tic_tac_toe_server conroller) {

        this.conroller = conroller;

        imageView = new ImageView();
        imageView0 = new ImageView();
        label = new Label();
        label0 = new Label();
        toggleButton = new ToggleButton();
        label1 = new Label();
        playerNumbers = new Label();
        label3 = new Label();
        line = new Line();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(500.0);
        setStyle("-fx-background-color: #000000;");

        imageView.setFitHeight(296.0);
        imageView.setFitWidth(304.0);
        imageView.setLayoutX(-1.0);
        imageView.setLayoutY(43.0);
        imageView.setOpacity(0.13);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        try {
            imageView.setImage(new Image(new FileInputStream("C:\\Users\\Ashraf_R\\Documents\\NetBeansProjects\\tic_tac_toe_server\\src\\images/background.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerView.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Player> list = FXCollections.observableArrayList(conroller.getPlayers());
        PlayerListView = new ListView(list);
        PlayerListView.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>() {
            @Override
            public ListCell<Player> call(ListView<Player> param) {
                return new PlayerCell();
            }
        });
        PlayerListView.setLayoutX(280.0);
        PlayerListView.setPrefHeight(400.0);
        PlayerListView.setPrefWidth(230.0);
        PlayerListView.setStyle("-fx-background-color: #000000;");
        PlayerListView.setCenterShape(true);

        imageView0.setFitHeight(224.0);
        imageView0.setFitWidth(240.0);
        imageView0.setLayoutX(283.0);
        imageView0.setLayoutY(76.0);
        imageView0.setOpacity(0.13);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
//        try {
//            imageView0.setImage(new Image(new FileInputStream("C:\\Users\\Ashraf_R\\Documents\\NetBeansProjects\\tic_tac_toe_server\\src\\images/background.png")));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ServerView.class.getName()).log(Level.SEVERE, null, ex);
//        }

        label.setLayoutX(66.0);
        label.setLayoutY(68.0);
        label.setText("Tic Tac Toe");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#eecf56"));
        label.setFont(new Font(32.0));

        label0.setLayoutX(95.0);
        label0.setLayoutY(121.0);
        label0.setText("Server");
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#eecf56"));
        label0.setFont(new Font("System Bold", 32.0));

        toggleButton.setLayoutX(112.0);
        toggleButton.setLayoutY(210.0);
        toggleButton.setMnemonicParsing(false);
        toggleButton.setPrefHeight(36.0);
        toggleButton.setPrefWidth(69.0);
        toggleButton.setStyle("-fx-background-color: #eecf56;");
        toggleButton.setText("off");
        toggleButton.setFont(new Font("System Bold", 16.0));

        label1.setLayoutX(85.0);
        label1.setLayoutY(313.0);
        label1.setText("we are serve for ");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#eecf56"));
        label1.setFont(new Font(17.0));

        playerNumbers.setLayoutX(105.0);
        playerNumbers.setLayoutY(346.0);
        playerNumbers.setText(conroller.playerCount() + "");
        playerNumbers.setTextFill(javafx.scene.paint.Color.valueOf("#eecf56"));
        playerNumbers.setFont(new Font(17.0));

        label3.setLayoutX(129.0);
        label3.setLayoutY(345.0);
        label3.setText("players");
        label3.setTextFill(javafx.scene.paint.Color.valueOf("#eecf56"));
        label3.setFont(new Font(17.0));

        line.setEndY(230.0);
        line.setFill(javafx.scene.paint.Color.valueOf("#edcd58"));
        line.setLayoutX(280.0);
        line.setLayoutY(168.0);
        line.setStartY(-170.0);
        line.setStroke(javafx.scene.paint.Color.valueOf("#eecf56"));
        line.setStrokeWidth(3.0);

        getChildren().add(imageView);
        getChildren().add(PlayerListView);
        getChildren().add(imageView0);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(toggleButton);
        getChildren().add(label1);
        getChildren().add(playerNumbers);
        getChildren().add(label3);
        getChildren().add(line);

    }

    static class PlayerCell extends ListCell<Player> {

        HBox hbox = new HBox();
        Label username = new Label("(empty)");
        Label points = new Label("(empty)");
        Label active = new Label("(empty)");

        public PlayerCell() {
            super();
            hbox.getChildren().addAll(active, points, username);
            HBox.setHgrow(username, Priority.ALWAYS);
        }

        @Override
        public void updateItem(Player player, boolean empty) {
            super.updateItem(player, empty);

            if (player != null) {
                if (player.isIsActive()) {
                    active.setText("*\t");
                } else {
                    active.setText(" \t");
                }

                points.setText(player.getPoints() + "\t");
                username.setText(player.getUsername());
                setGraphic(hbox);
            }
        }
    }
}

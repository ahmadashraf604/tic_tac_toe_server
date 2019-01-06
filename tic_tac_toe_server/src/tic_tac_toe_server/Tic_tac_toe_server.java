/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe_server;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataBaseConnection;
import commen.Player;
import views.ServerView;

/**
 *
 * @author Ashraf_R
 */
public class Tic_tac_toe_server extends Application {

    ServerView serverView;
    DataBaseConnection dataBaseConnection;

    public Tic_tac_toe_server() {
        dataBaseConnection = new DataBaseConnection(this);
    }

    @Override
    public void start(Stage primaryStage) {

        serverView = new ServerView(this);

        Scene scene = new Scene(serverView, 500, 390);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public List<Player> getPlayers() {
        return dataBaseConnection.getAllPlayers();
    }

    public int playerCount() {
        return dataBaseConnection.getAllPlayers().size();
    }

}

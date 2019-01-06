/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import commen.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tic_tac_toe_server.Tic_tac_toe_server;

/**
 *
 * @author ashraf
 */
public class DataBaseConnection {

    Statement statement;
    Tic_tac_toe_server controller;

    public DataBaseConnection(Tic_tac_toe_server controller) {

        this.controller = controller;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tic-tac-toe", "root", "1529");
            statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean registerPlayer(String username, String email, String password) {
        try {
            return !(statement.execute(
                    "INSERT INTO players (username, email, points, active ,password) VALUES ('" + username
                    + "', '" + email + "', '0', '0', '" + password + "')"));
        } catch (SQLException ex) {
            return false;
        }
    }

    public Player login(String username, String password) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM players where username='" + username + "' and password='" + password + "'");
            if (setActive(username)) {
                return getPlayer(username);
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    private boolean setActive(String username) {
        try {
            return !(statement.execute("UPDATE players SET active = 1 WHERE (username = '" + username + "')"));
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Player getPlayer(String username) {
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM players where username='" + username + "'");
            if (resultSet.first()) {
                Player player = new Player();
                player.setId(resultSet.getInt("idplayer"));
                player.setUsername(resultSet.getString("username"));
                player.setEmail(resultSet.getString("email"));
                player.setPoints(resultSet.getInt("points"));
                if (resultSet.getInt("active") == 0) {
                    player.setIsActive(false);
                } else {
                    player.setIsActive(true);
                }
                return player;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    public List<Player> getActivePlayers() {
        List<Player> players = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM players where active = 1 order by points DESC");
            while (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getInt("idplayer"));
                player.setUsername(resultSet.getString("username"));
                player.setEmail(resultSet.getString("email"));
                player.setPoints(resultSet.getInt("points"));
                player.setIsActive(true);

                players.add(player);
            }
        } catch (SQLException ex) {
            return players;
        }
        return players;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM players order by points DESC");
            while (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getInt("idplayer"));
                player.setUsername(resultSet.getString("username"));
                player.setEmail(resultSet.getString("email"));
                player.setPoints(resultSet.getInt("points"));
                if (resultSet.getInt("active") == 0) {
                    player.setIsActive(false);
                } else {
                    player.setIsActive(true);
                }
                players.add(player);
            }
        } catch (SQLException ex) {
            return players;
        }
        return players;
    }

    public boolean incrementPoints(Player player) {
        try {
            int points = player.getPoints();
            points++;
            return !(statement.execute("UPDATE players SET points = " + points + " WHERE (username = '" + player.getUsername() + "')"));
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

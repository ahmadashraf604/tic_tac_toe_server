/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic.tac.toe;

import java.util.List;

/**
 *
 * @author ashraf
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean registerPlayer = dataBaseConnection.registerPlayer("ayrahlh", "aeyahlh@gmail.com", "12345");
        System.out.println("register : "+registerPlayer);
        Player login = dataBaseConnection.login("ashraf", "1234");
        System.out.println("resurlt : "+login.email);
        Player player = dataBaseConnection.getPlayer("ashraf");
        String username = player.getUsername();
        System.out.println(username);
        System.out.println(player.getEmail());
        
      
        
        Player anas = dataBaseConnection.login("anas", "1234");
        dataBaseConnection.incrementPoints(anas);
        List<Player> activePlayers = dataBaseConnection.getActivePlayers();
        if (activePlayers.size()>0) {   
            System.out.println(activePlayers.size());
            for(int i=0;i<activePlayers.size();i++){
                System.out.println(activePlayers.get(i).username);
            }
        }
        
//        List<Player> allPlayers = dataBaseConnection.getAllPlayers();
//        if (allPlayers.size()>0) {   
//            System.out.println(allPlayers.size());
//            for(int i=0;i<allPlayers.size();i++){
//                System.out.println(allPlayers.get(i).username);
//            }
//        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Map.Map;
import Map.Map.TokenPlayer;
import Map.Position;
import Player.PlayerHuman;
import Player.PlayerIAIterative;
import Player.PlayerIARecursive;
import java.util.Scanner;

/**
 *
 * @author Darkos
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Map map = new Map();
        boolean endOfGame = false;
        PlayerHuman zePlayer = new PlayerHuman(TokenPlayer.PLAYER1);
        PlayerIAIterative zeIAIterative = new PlayerIAIterative(TokenPlayer.PLAYER2);
        PlayerIARecursive zeIARecursive = new PlayerIARecursive(TokenPlayer.PLAYER2);
        
        while(!endOfGame) {
            
            map.show();

            //PLAYER TURN ---------------------------
            map.setValueAt(zePlayer.getActionPosition(map), zePlayer.getToken());

            //IA TURN ---------------------------
            //Itérative
//            map.setValueAt(zeIAIterative.getActionPosition(map), zeIAIterative.getToken());

            //Récursive
//            map.setValueAt(zeIARecursive.getActionPosition(map), zeIARecursive.getToken());
            
        }
    }
    
}

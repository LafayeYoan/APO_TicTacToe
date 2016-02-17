/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Map.Map;
import Map.Map.TokenPlayer;
import Player.PlayerHuman;
import Player.PlayerIAIterative;
import Player.PlayerIARecursive;

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
            
            if(map.getWinner() != null) {
                break;
            }

            //IA TURN ---------------------------
            //Itérative
//            map.setValueAt(zeIAIterative.getActionPosition(map), zeIAIterative.getToken());

            //Récursive (decomment to use and comment IA iterative)
            map.setValueAt(zeIARecursive.getActionPosition(map), zeIARecursive.getToken());
            
            if(map.getWinner() != null) {
                endOfGame = true;
            }
            
        }
        
        System.out.println("FIN DU JEU :");
        map.show();
    }
    
}

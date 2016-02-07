package Map;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darkos
 */
public class Map {
    
    private TokenPlayer [][] map;
    private TokenPlayer winner;
    public Position lastPlay;
    
    public final static int SIZE =3;

    public enum TokenPlayer{
        EMPTY,
        PLAYER1,
        PLAYER2
    }
    
    public Map(){
        map = new TokenPlayer[SIZE][SIZE];
        for(int i = 0 ; i < SIZE;i++){
            for (int j = 0 ; j < SIZE; j++){
                map[i][j]=TokenPlayer.EMPTY;
            }
        }
        
        winner = null;
        lastPlay = null;
    }
    
    public TokenPlayer getValueAt(Position p){
        if(p.getX()<0 ||p.getY()<0 || p.getX()>=SIZE || p.getY()>=SIZE){
            return null;
        }
        else{
            return map[p.getX()][p.getY()];
        }
    }
    
    /***
     * Get an empty cell
     * @return a random empty cell
     */
    public Position getAnEmptyCell() {
        
        List<Position> emptyCells = new ArrayList();        
        for(int i = 0 ; i < SIZE; i++){
            for (int j = 0 ; j < SIZE; j++){
                if(map[i][j] == TokenPlayer.EMPTY) {
                    emptyCells.add(new Position(i, j));
                }
            }
        }
        
        return emptyCells.get((int)(Math.random() * (emptyCells.size() - 0)) + 0);
    }
    
    public TokenPlayer setValueAt(Position p, TokenPlayer token){
        if(p.getX()<0 ||p.getY()<0 || p.getX()>=SIZE || p.getY()>=SIZE){
            return null;
        }
        if(this.getValueAt(p)!= TokenPlayer.EMPTY){
            return null;
        }
        map[p.getX()][p.getY()] = token;
        checkWinner(token);    
        lastPlay = p;
      
        return token;
    }
    
    public void show(){
        String toShow = "-------";
        for(int x = 0; x< SIZE; x++){
            toShow += "\n|";
            for(int y = 0 ; y< SIZE; y++){
                toShow +=getSign(x,y);
                toShow +="|";
            }
            toShow += "\n";
            toShow +="-------";
        }
        
        System.out.println(toShow);
        
    }
    
    private String getSign(int x, int y){
        if(this.map[x][y]==TokenPlayer.PLAYER1){
            return "O";
        }
        
        if(this.map[x][y]==TokenPlayer.PLAYER2){
            return "X";
        }
        return " ";
    }
    
    public TokenPlayer getWinner(){
        return this.winner;
    }
    
    /***
     * Check if there is a winner
     * @param token 
     */
    private void checkWinner(TokenPlayer token) {
        //vertical
        if ((map[0][0].equals(map[0][1]) && (map[0][1].equals(map[0][2])) && (map[0][0] != TokenPlayer.EMPTY))) {winner = token;}
        if ((map[1][0].equals(map[1][1]) && (map[1][1].equals(map[1][2])) && (map[1][0] != TokenPlayer.EMPTY))) {winner = token;}
        if ((map[2][0].equals(map[2][1]) && (map[2][1].equals(map[2][2])) && (map[2][0] != TokenPlayer.EMPTY))) {winner = token;}
        
        //horizontal
        if ((map[0][0].equals(map[1][0]) && (map[1][0].equals(map[2][0])) && (map[0][0] != TokenPlayer.EMPTY))) {winner = token;}
        if ((map[0][2].equals(map[1][2]) && (map[1][2].equals(map[2][2])) && (map[0][2] != TokenPlayer.EMPTY))) {winner = token;}
        if ((map[0][1].equals(map[1][1]) && (map[1][1].equals(map[2][1])) && (map[0][1] != TokenPlayer.EMPTY))) {winner = token;}
        
        //diagonal
        if ((map[0][0].equals(map[1][1]) && (map[1][1].equals(map[2][2])) && (map[0][0] != TokenPlayer.EMPTY))) {winner = token;}
        if ((map[0][2].equals(map[1][1]) && (map[1][1].equals(map[2][0])) && (map[0][2] != TokenPlayer.EMPTY))) {winner = token;}
    }
    
    /***
     * ITARATIVE METHOD
     * Check if the player can win.  
     * @return the position to fill to block the player. Return null if the player is not ready to win
     */
    public Position playerCanWin() {
        
        int x, y;
        
        //vertical
        for(x = 0; x < SIZE; x++) {
            y = 0;
            if((map[x][y].equals(map[x][y+1]) && map[x][y+2] == TokenPlayer.EMPTY)) {
                return new Position(x, y+2);
            }
            if(map[x][y].equals(map[x][y+2]) && map[x][y+1] == TokenPlayer.EMPTY) {
                return new Position(x, y+1);
            }
            if(map[x][y+1].equals(map[x][y+2]) && map[x][y] == TokenPlayer.EMPTY) {
                return new Position(x, y);
            }
        }
        
        //horizontal
        for(y = 0; y < SIZE; y++) {
            x = 0;
            if((map[x][y].equals(map[x+1][y]) && map[x+2][y] == TokenPlayer.EMPTY)) {
                return new Position(x+2, y);
            }
            if(map[x][y].equals(map[x+2][y]) && map[x+1][y] == TokenPlayer.EMPTY) {
                return new Position(x+1, y);
            }
            if(map[x+1][y].equals(map[x+2][y]) && map[x][y] == TokenPlayer.EMPTY) {
                return new Position(x, y);
            }
        }
        
        //diagonal
        if ((map[0][0].equals(map[1][1]) && map[2][2] == TokenPlayer.EMPTY)) {
            return new Position(2, 2);
        }
        if((map[1][1].equals(map[2][2]) && map[0][0] == TokenPlayer.EMPTY)) {
            return new Position(0, 0);
        }
        if((map[0][0].equals(map[2][2]) && map[1][1] == TokenPlayer.EMPTY)) {
            return new Position(1, 1);
        }
        if ((map[0][2].equals(map[1][1]) && map[2][0] == TokenPlayer.EMPTY)) {
            return new Position(2, 0);
        }
        if((map[1][1].equals(map[2][0]) && map[0][2] == TokenPlayer.EMPTY)) {
            return new Position(0, 2);
        }
        if((map[0][2].equals(map[2][0]) && map[1][1] == TokenPlayer.EMPTY))  {
            return new Position(1, 1);
        }
        
        return null;
    }
}

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
            System.err.println("SET VALUE AT NULL I");
            return null;
        }
        if(this.getValueAt(p)!= TokenPlayer.EMPTY){
            System.err.println("SET VALUE AT NULL II");
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
    
    /** RECURSIVE METHOD 
     * minimax at level of depth for maximizing IA
       Return int[3] of {score, row, col}  */
    public int[] minimax(int depth, TokenPlayer player) {
      // Generate possible next moves in a List of int[2] of {row, col}.
      List<int[]> nextMoves = generateMoves();
 
      // PLAYER2 is maximizing (IA); while PLAYER1 is minimizing
      int bestScore = (player == TokenPlayer.PLAYER2) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      int currentScore;
      int bestRow = -1;
      int bestCol = -1;
 
      if (nextMoves.isEmpty() || depth == 0) {
         // Gameover or depth reached, evaluate score
         bestScore = evaluate();
      } else {
         for (int[] move : nextMoves) {
            // Try this move (just to simulate it)
            map[move[0]][move[1]] = player;
            if (player == TokenPlayer.PLAYER2) { 
               currentScore = minimax(depth - 1, TokenPlayer.PLAYER1)[0];
               if (currentScore > bestScore) {
                  bestScore = currentScore;
                  bestRow = move[0];
                  bestCol = move[1];
               }
            } else {  
               currentScore = minimax(depth - 1, TokenPlayer.PLAYER2)[0];
               if (currentScore < bestScore) {
                  bestScore = currentScore;
                  bestRow = move[0];
                  bestCol = move[1];
               }
            }
            // Undo move
            map[move[0]][move[1]] = TokenPlayer.EMPTY;
         }
      }
      return new int[] {bestScore, bestRow, bestCol};
   }
 
   /** Find all valid next moves.
       Return List of moves in int[2] of {row, col} or empty list if gameover */
   private List<int[]> generateMoves() {
      List<int[]> nextMoves = new ArrayList<int[]>();
 
      // Search for empty cells and add to the List
      for (int row = 0; row < SIZE; ++row) {
         for (int col = 0; col < SIZE; ++col) {
            if (map[row][col] == TokenPlayer.EMPTY) {
               nextMoves.add(new int[] {row, col});
            }
         }
      }
      return nextMoves;
   }
 
   /** The heuristic evaluation function for the current board
       @Return +100, +10, +1 for EACH 3-, 2-, 1-in-a-line for computer.
               -100, -10, -1 for EACH 3-, 2-, 1-in-a-line for opponent.
               0 otherwise   */
   private int evaluate() {
      int score = 0;
      // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
      score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
      score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
      score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
      score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
      score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
      score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
      score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
      score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
      return score;
   }
 
   /** The heuristic evaluation function for the given line of 3 cells
       @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
               -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
               0 otherwise */
   private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
      int score = 0;
 
      // First cell
      if (map[row1][col1] == TokenPlayer.PLAYER2) {
         score = 1;
      } else if (map[row1][col1] == TokenPlayer.PLAYER1) {
         score = -1;
      }
 
      // Second cell
      if (map[row2][col2] == TokenPlayer.PLAYER2) {
         if (score == 1) {   // cell1 is IA
            score = 10;
         } else if (score == -1) {  // cell1 is Player
            return 0;
         } else {  // cell1 is empty
            score = 1;
         }
      } else if (map[row2][col2] == TokenPlayer.PLAYER1) {
         if (score == -1) { // cell1 is Player
            score = -10;
         } else if (score == 1) { // cell1 is IA
            return 0;
         } else {  // cell1 is empty
            score = -1;
         }
      }
 
      // Third cell
      if (map[row3][col3] == TokenPlayer.PLAYER2) {
         if (score > 0) {  // cell1 and/or cell2 is IA
            score *= 10;
         } else if (score < 0) {  // cell1 and/or cell2 is Player
            return 0;
         } else {  // cell1 and cell2 are empty
            score = 1;
         }
      } else if (map[row3][col3] == TokenPlayer.PLAYER1) {
         if (score < 0) {  // cell1 and/or cell2 is Player
            score *= 10;
         } else if (score > 1) {  // cell1 and/or cell2 is IA
            return 0;
         } else {  // cell1 and cell2 are empty
            score = -1;
         }
      }
      return score;
   }
}

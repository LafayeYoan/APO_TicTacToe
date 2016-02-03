package Map;

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
    }
    
    public TokenPlayer getValueAt(Position p){
        if(p.getX()<0 ||p.getY()<0 || p.getX()>=SIZE || p.getY()>=SIZE){
            return null;
        }
        else{
            return map[p.getX()][p.getY()];
        }
    }
    
    public boolean setValueAt(Position p, TokenPlayer token){
        if(p.getX()<0 ||p.getY()<0 || p.getX()>=SIZE || p.getY()>=SIZE){
            return false;
        }
        if(this.getValueAt(p)!= TokenPlayer.EMPTY){
            return false;
        }
        map[p.getX()][p.getY()] = token;
        return true;
    }
}

package Player;

import Map.Position;
import Map.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darkos
 */
public class PlayerIAIterative extends Player{

    public PlayerIAIterative(Map.TokenPlayer token) {
        super(token);
    }

    @Override
    public Position getActionPosition(Map map) {
        
        Position position = map.playerCanWin();
        
        if(position == null) {
            //Attack
            return map.getAnEmptyCell();
        }
        
        //Defend
        return position;
    }    
}

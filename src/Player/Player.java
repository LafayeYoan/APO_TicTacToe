package Player;

import Map.Map;
import Map.Position;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darkos
 */
public abstract class Player {
    private Map.TokenPlayer token;
    
    public Player(Map.TokenPlayer token){
        this.token = token;
    }
    
    public Map.TokenPlayer getToken(){
        return token;
    }
    public abstract Position getActionPosition(Map map);
}

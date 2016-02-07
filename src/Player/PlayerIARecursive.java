/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Map.Map;
import Map.Position;

/**
 *
 * @author lhopital
 */
public class PlayerIARecursive extends Player {
    
    public PlayerIARecursive(Map.TokenPlayer token) {
        super(token);
    }

    @Override
    public Position getActionPosition(Map map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

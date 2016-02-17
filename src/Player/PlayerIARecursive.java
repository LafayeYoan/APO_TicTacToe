
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
        int[] minimaxResult = map.minimax(2, this.getToken());
        
        System.out.println("score : " + minimaxResult[0]);
        System.out.println(minimaxResult[1]);
        System.out.println(minimaxResult[2]);
        
        return new Position(minimaxResult[1], minimaxResult[2]);
    }        
}

package GameWorldUtility;

import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorldType.Predicate;
import GameWorld.Level;

public class WallInFrontPredicate implements Predicate {

    @Override
    public String getName() {
        return "Wall In Front";
    }

    @Override
    public boolean evaluate(GameWorld gameWorld) {
        return ((Level) gameWorld).robotHasWallInFront();
    }
}

package GameWorldUtility;

import GameWorld.Level;
import GameWorldAPI.GameWorldType.Predicate;

public abstract class LevelPredicate implements Predicate {

    protected Level level;

    public abstract String getName();

    public abstract boolean evaluate();

    protected void setLevel(Level level) {
        this.level = level;
    }
}

package GameWorldUtility;

import GameWorld.Level;
import GameWorldAPI.GameWorldType.Predicate;

public abstract class LevelPredicate implements Predicate {

    private Level level;

    public abstract String getName();

    public abstract boolean evaluate();

    protected Level getLevel() {
        return level;
    }

    protected void setLevel(Level level) {
        this.level = level;
    }
}

package GameWorldUtility;

public class WallInFrontPredicate extends LevelPredicate {

    @Override
    public String getName() {
        return "Wall In Front";
    }

    @Override
    public boolean evaluate() {
        return getLevel().robotHasWallInFront();
    }
}

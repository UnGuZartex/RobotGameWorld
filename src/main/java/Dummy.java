import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;

import java.util.List;

public class Dummy implements GameWorldType{
    @Override
    public List<Action> getAllActions() {
        return null;
    }

    @Override
    public List<Predicate> getAllPredicates() {
        return null;
    }

    @Override
    public GameWorld createNewGameworld() {
        return null;
    }
}

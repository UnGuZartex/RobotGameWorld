import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;

import java.io.File;
import java.util.List;

public class LevelInitializer implements GameWorldType{

    @Override
    public List<Action> getAllActions() {
        return null;
    }

    @Override
    public List<Predicate> getAllPredicates() {
        return null;
    }

    @Override
    public GameWorld loadFromFile(File file) {
        return null;
    }

    @Override
    public GameWorld createNewGameworld() {
        return null;
    }
}

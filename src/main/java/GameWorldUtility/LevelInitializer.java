package GameWorldUtility;

import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;
import GameWorldUtility.Actions.MoveForwardAction;
import GameWorldUtility.Actions.TurnLeftAction;
import GameWorldUtility.Actions.TurnRightAction;
import GameWorldUtility.Predicates.WallInFrontPredicate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A class to initialise levels and to get all the possible actions and
 * predicates which can be applied to the levels created.
 *
 * @author Alpha-team
 */
public class LevelInitializer implements GameWorldType {

    /**
     * Variable referring to the level loader for this level initializer.
     */
    private final LevelLoader levelLoader = new LevelLoader();

    /**
     * Variable referring to the actions possible. This is a list with
     * 3 different actions: move forward, turn left and turn right.
     */
    private final List<Action> actionList = new ArrayList<>(
            Arrays.asList(
                    new MoveForwardAction(),
                    new TurnLeftAction(),
                    new TurnRightAction()
            )
    );

    /**
     * Variable referring to the predicates possible. This is a list with
     * only one predicate: wall in front.
     */
    private final List<Predicate> predicateList = new ArrayList<>(
            Collections.singletonList(
                    new WallInFrontPredicate()
            )
    );

    /**
     * Get all the possible actions.
     *
     * @return A copy of the actions list.
     */
    @Override
    public List<Action> getAllActions() {
        return new ArrayList<>(actionList);
    }

    /**
     * Get all possible predicates.
     *
     * @return A copy of the predicates list.
     */
    @Override
    public List<Predicate> getAllPredicates() {
        return new ArrayList<>(predicateList);
    }

    /**
     * Creates a new game world. The given file is not used.
     *
     * @effect Create a new game world.
     */
    @Override
    public GameWorld loadFromFile(File file) {
        return createNewGameWorld();
    }

    /**
     * Create a new game world.
     *
     * @return The default level of the level loader.
     */
    @Override
    public GameWorld createNewGameWorld() {
        return levelLoader.loadDefaultLevel();
    }
}

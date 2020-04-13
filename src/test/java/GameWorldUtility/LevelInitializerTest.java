package GameWorldUtility;

import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorldType.Action;
import GameWorldAPI.GameWorldType.Predicate;
import GameWorld.Level;
import GameWorldUtility.Actions.MoveForwardAction;
import GameWorldUtility.Actions.TurnLeftAction;
import GameWorldUtility.Actions.TurnRightAction;
import GameWorldUtility.Predicates.WallInFrontPredicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LevelInitializerTest {

    List<Action> actions;
    List<Predicate> predicates;
    GameWorld gameWorld;
    LevelInitializer initializer;

    @BeforeEach
    void setUp() {
        initializer = new LevelInitializer();
    }

    @AfterEach
    void tearDown() {
        actions = null;
        predicates = null;
        gameWorld = null;
        initializer = null;
    }

    @Test
    void getAllActions() {
        actions = initializer.getAllActions();
        assertEquals(3, actions.size());
        assertTrue(actions.get(0) instanceof MoveForwardAction);
        assertTrue(actions.get(1) instanceof TurnLeftAction);
        assertTrue(actions.get(2) instanceof TurnRightAction);
    }

    @Test
    void getAllPredicates() {
        predicates = initializer.getAllPredicates();
        assertEquals(1, predicates.size());
        assertTrue(predicates.get(0) instanceof WallInFrontPredicate);
    }

    @Test
    void loadFromFile() {
        gameWorld = initializer.loadFromFile(null);
        assertTrue(gameWorld instanceof Level);
    }

    @Test
    void createNewGameWorld() {
        gameWorld = initializer.createNewGameWorld();
        assertTrue(gameWorld instanceof Level);
    }
}
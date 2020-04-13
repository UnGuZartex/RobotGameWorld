import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorld.Result;
import GameWorldAPI.GameWorldType.Action;
import GameWorldAPI.GameWorldType.GameWorldType;
import GameWorldAPI.GameWorldType.Predicate;
import GameWorldUtility.Actions.MoveForwardAction;
import GameWorldUtility.Actions.TurnLeftAction;
import GameWorldUtility.Actions.TurnRightAction;
import GameWorldUtility.LevelInitializer;
import GameWorldUtility.Predicates.WallInFrontPredicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    GameWorldType gameWorldType;
    GameWorld gameWorld;
    Action moveForward, turnLeft,turnRight;
    Predicate wallInFront;

    @BeforeEach
    void setUp() {
        gameWorldType = new LevelInitializer();
        moveForward = (MoveForwardAction) gameWorldType.getAllActions().get(0);
        turnLeft = (TurnLeftAction) gameWorldType.getAllActions().get(1);
        turnRight = (TurnRightAction) gameWorldType.getAllActions().get(2);
        wallInFront = (WallInFrontPredicate) gameWorldType.getAllPredicates().get(0);
        gameWorld = gameWorldType.createNewGameWorld();
    }

    @AfterEach
    void tearDown() {
        gameWorldType = null;
        moveForward = null;
        turnLeft = null;
        turnRight = null;
        wallInFront = null;
        gameWorld= null;
    }

    @Test
    void execution_success_toLeft() {
        assertTrue(gameWorld.evaluatePredicate(wallInFront));
        assertEquals(Result.SUCCESS, gameWorld.executeAction(turnLeft));
        assertFalse(gameWorld.evaluatePredicate(wallInFront));
        assertEquals(Result.END, gameWorld.executeAction(moveForward));
        assertThrows(IllegalStateException.class, () -> { gameWorld.executeAction(moveForward); });
    }

    @Test
    void execution_success_toRight() {
        assertTrue(gameWorld.evaluatePredicate(wallInFront));
        assertEquals(Result.SUCCESS, gameWorld.executeAction(turnRight));
        assertTrue(gameWorld.evaluatePredicate(wallInFront));
        assertEquals(Result.SUCCESS, gameWorld.executeAction(turnRight));
        assertTrue(gameWorld.evaluatePredicate(wallInFront));
        assertEquals(Result.SUCCESS, gameWorld.executeAction(turnRight));
        assertFalse(gameWorld.evaluatePredicate(wallInFront));
        assertEquals(Result.END, gameWorld.executeAction(moveForward));
        assertThrows(IllegalStateException.class, () -> { gameWorld.executeAction(moveForward); });
    }

    @Test
    void execution_failure() {
        assertTrue(gameWorld.evaluatePredicate(wallInFront));
        assertEquals(Result.FAILURE, gameWorld.executeAction(moveForward));
        assertThrows(IllegalStateException.class, () -> { gameWorld.executeAction(moveForward); });
    }
}
//TODO DELETE THIS CLASS

import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorldType.Action;
import GameWorldAPI.GameWorldType.GameWorldType;
import GameWorldUtility.LevelInitializer;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        GameWorldType initter = new LevelInitializer();
        GameWorld gameWorld = initter.createNewGameworld();
        List<Action> actions = initter.getAllActions();

        System.out.println(gameWorld.executeAction(actions.get(1)));
        System.out.println(gameWorld.executeAction(actions.get(0)));
    }
}

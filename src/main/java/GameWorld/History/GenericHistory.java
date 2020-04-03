package GameWorld.History;

import GameWorldAPI.History.History;
import GameWorldAPI.History.Snapshot;

import java.util.Stack;

public class GenericHistory implements History {

    private HistoryTracked originator;
    private Stack<Snapshot> undoStack = new Stack<>();
    private Stack<Snapshot> redoStack = new Stack<>();

    public GenericHistory(HistoryTracked originator) {
        this.originator = originator;
    }

    @Override
    public void add(Snapshot snapshot) {
        undoStack.push(snapshot);
    }

    @Override
    public void undo() {
        if (!undoStack.isEmpty()) {
            Snapshot currentState = originator.createSnapshot();
            Snapshot snapshot = undoStack.pop();
            System.out.println("Undoing: " + snapshot);
            redoStack.push(currentState);
            originator.loadSnapshot(snapshot);
        }
    }

    @Override
    public void redo() {
        if (!redoStack.isEmpty()) {
            Snapshot currentState = originator.createSnapshot();
            Snapshot snapshot = redoStack.pop();
            System.out.println("Redoing: " + snapshot);
            undoStack.push(currentState);
            originator.loadSnapshot(snapshot);
        }

    }

    @Override
    public void reset() {
        if (!undoStack.isEmpty()) {
            Snapshot firstElement = undoStack.firstElement();
            System.out.println("Resetting to: " + firstElement);
            originator.loadSnapshot(firstElement);
            undoStack.clear();
        }
        redoStack.clear();
    }
}

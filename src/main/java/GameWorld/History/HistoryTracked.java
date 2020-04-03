package GameWorld.History;

import GameWorldAPI.History.Snapshot;

public interface HistoryTracked {

    Snapshot createSnapshot();

    void loadSnapshot(Snapshot snapshot);
    
    void backup();
}

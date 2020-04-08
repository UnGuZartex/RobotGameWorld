package GameWorld.Painter;

import GameWorld.Grid;
import GameWorldAPI.Images.ImageLibrary;
import RobotCollection.Robot.Robot;
import RobotCollection.Utility.GridPosition;

import java.awt.*;

public class LevelPainter {

    private final ImageLibrary library;
    private GridPosition gridSize;
    private int cellSize;
    private int gridStartingPointX;
    private int gridStartingPointY;
    private static int minGridDelta = 20;

    public LevelPainter(ImageLibrary library) {
        this.library = library;
    }

    public void calculateGridProperties(GridPosition startingPosition, GridPosition visualSize, GridPosition gridSize) {
        this.gridSize = gridSize;
        cellSize = Math.min((visualSize.getX() - minGridDelta) / gridSize.getX(), (visualSize.getY() - minGridDelta) / gridSize.getY());
        gridStartingPointX = startingPosition.getX() + (visualSize.getX() - (cellSize * gridSize.getX())) / 2;
        gridStartingPointY = startingPosition.getY() + (visualSize.getY() - (cellSize * gridSize.getY())) / 2;
    }

    public void paint(Graphics g, Grid grid, Robot robot) {
        drawGrid(g, grid);
        drawRobot(g, robot);
    }

    private void drawGrid(Graphics g, Grid grid) {
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        for (int x = 0; x < gridSize.getX(); x++) {
            for (int y = 0; y < gridSize.getY(); y++) {
                g.drawImage(library.getImage(grid.getCellAt(new GridPosition(x, y)).getCellType().name()),
                        gridStartingPointX + x * cellSize, gridStartingPointY + y * cellSize, cellSize, cellSize, null);
                g.drawRect(gridStartingPointX + x * cellSize, gridStartingPointY + y * cellSize, cellSize, cellSize);
            }
        }

        g2.setStroke(new BasicStroke(1));
    }

    private void drawRobot(Graphics g, Robot robot) {
        GridPosition robotPosition = robot.getGridPosition();
        g.drawImage(library.getImage("robot" + robot.getDirection()),
                gridStartingPointX + robotPosition.getX() * cellSize, gridStartingPointY +
                        robotPosition.getY() * cellSize, cellSize, cellSize, null);
    }
}

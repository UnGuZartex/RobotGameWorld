package RobotCollection.Utility;

/**
 * An enum for managing the different directions a robot can look towards.
 *
 * @author Alpha-team
 */
public enum Direction {
    UP {
        /**
         * Turns this direction to the left.
         *
         * @return The left direction.
         */
        @Override
        public Direction turnLeft() {
            return Direction.LEFT;
        }

        /**
         * Turns this direction to the right.
         *
         * @return The right direction.
         */
        @Override
        public Direction turnRight() {
            return Direction.RIGHT;
        }

        /**
         * Get the position from moving one tile forward in the
         * current direction.
         *
         * @param position the position to start from
         * @return The position to end at
         */
        @Override
        public GridPosition getPositionForward(GridPosition position) {
            return new GridPosition(position.getX(), position.getY()-1);
        }
    },
    LEFT {
        /**
         * Turns this direction to the left.
         *
         * @return The down direction.
         */
        @Override
        public Direction turnLeft() {
            return Direction.DOWN;
        }

        /**
         * Turns this direction to the right.
         *
         * @return The up direction.
         */
        @Override
        public Direction turnRight() {
            return Direction.UP;
        }

        /**
         * Get the position from moving one tile forward in the
         * current direction.
         *
         * @param position the position to start from
         * @return The position to end at
         */
        @Override
        public GridPosition getPositionForward(GridPosition position) {
            return new GridPosition(position.getX()-1, position.getY());
        }
    },
    DOWN {
        /**
         * Turns this direction to the left.
         *
         * @return The right direction.
         */
        @Override
        public Direction turnLeft() {
            return Direction.RIGHT;
        }

        /**
         * Turns this direction to the right.
         *
         * @return The left direction.
         */
        @Override
        public Direction turnRight() {
            return Direction.LEFT;
        }

        /**
         * Get the position from moving one tile forward in the
         * current direction.
         *
         * @param position the position to start from
         * @return The position to end at
         */
        @Override
        public GridPosition getPositionForward(GridPosition position) {
            return new GridPosition(position.getX(), position.getY()+1);
        }
    },
    RIGHT {
        /**
         * Turns this direction to the left.
         *
         * @return The up direction.
         */
        @Override
        public Direction turnLeft() {
            return Direction.UP;
        }

        /**
         * Turns this direction to the right.
         *
         * @return The down direction.
         */
        @Override
        public Direction turnRight() {
            return Direction.DOWN;
        }

        /**
         * Get the position from moving one tile forward in the
         * current direction.
         *
         * @param position the position to start from
         * @return The position to end at
         */
        @Override
        public GridPosition getPositionForward(GridPosition position) {
            return new GridPosition(position.getX()+1, position.getY());
        }
    };

    /**
     * Turns this direction to the left.
     *
     * @return This direction to the left.
     */
    public abstract Direction turnLeft();

    /**
     * Turns this direction to the right.
     *
     * @return This direction to the right.
     */
    public abstract Direction turnRight();

    /**
     * Get the position from moving one tile forward in the
     * current direction.
     *
     * @param position the position to start from
     * @return The position to end at
     */

    public abstract GridPosition getPositionForward(GridPosition position);
}

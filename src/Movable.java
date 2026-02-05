public interface Movable {
    /**
     * Moves vehicle based on its speed and angle.
     * It is not guaranteed that a call to this method has any effect, even if the speed is non-zero.
     * Certain implementations may impose other restrictions on when this object can be moved.
     */
    void move();

    /**
     * Turns vehicle 90 degrees to the left
     */
    void turnLeft();

    /**
     * Turns vehicle 90 degrees to the right
     */
    void turnRight();
}

public interface Movable {
    /**
     * Moves object based on its current state, such as speed and angle.
     * It is not guaranteed that a call to this method has any effect, depending on the object's state.
     */
    void move();

    /**
     * Turns object 90 degrees to the left
     */
    void turnLeft();

    /**
     * Turns object 90 degrees to the right
     */
    void turnRight();
}

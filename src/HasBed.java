public interface HasBed {
    /**
     * Raise the bed. If {@code canBedMove()} is false, this method has no effect.
     */
    void raise();

    /**
     * Lower the bed. If {@code canBedMove()} is false, this method has no effect.
     */
    void lower();

    /**
     * Defines whether the bed is able to move. If not, {@code raise()} and {@code lower()} should have no effect.
     *
     * @return If the bed is able to move
     */
    boolean canBedMove();
}

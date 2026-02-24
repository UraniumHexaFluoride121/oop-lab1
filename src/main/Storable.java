package main;

public interface Storable extends HasPosition {
    /**
     * Register this {@code Storable} as being stored in the specified {@code IStorage}.
     * Should only be called from {@code IStorage.load()}.
     *
     * @param t The {@code IStorage} that is now storing this item
     */
    void store(IStorage<?> t);

    /**
     * Remove this {@code Storable} from the specified {@code IStorage}.
     * Should only be called from {@code IStorage.unload()}.
     *
     * @param t The {@code IStorage} that is now no longer storing this item
     */
    void remove(IStorage<?> t);

    /**
     * If registered to an {@code IStorage}, this method should return the storage's x
     * position. Otherwise, this object's world position should be returned.
     *
     * @return The current x position
     */
    @Override
    double getX();

    /**
     * If registered to an {@code IStorage}, this method should return the storage's y
     * position. Otherwise, this object's world position should be returned.
     *
     * @return The current y position
     */
    @Override
    double getY();

    /**
     * Returns the weight of this item, in tonnes. Certain {@code IStorage} implementations may restrict the items that can be inserted
     * based on their weight.
     *
     * @return This item's weight
     */
    double getWeight();

    /**
     * @return Whether this item is currently registered to an {@code IStorage}
     */
    boolean isBeingStored();

    /**
     * Returns the id for the item used to identify it when placed in a storage
     */
    String getID();

}

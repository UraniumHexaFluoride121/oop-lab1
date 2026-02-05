/**
 * This interface defines a first-in-last-out collection of {@code Storable} items, that also has a position.
 * Items that are already registered to an {@code IStorage} may not be inserted.
 * Implementations may place further restrictions the items that can be inserted, such as a maximum weight that the items
 * can have.
 *
 * @param <T> The type of {@code Storable} that can be inserted into this storage
 */
public interface IStorage<T extends Storable> {
    /**
     * Add an item to this storage and call {@code store()} on the newly inserted item.
     * In case the item cannot be added, this method should have no effect.
     *
     * @param t The item to be added
     */
    void load(T t);

    /**
     * Remove an item from this storage, while calling {@code remove()} on the newly removed item.
     */
    void unload();

    /**
     * Returns the storage's x position
     */
    double getX();

    /**
     * Returns the storage's y position
     */
    double getY();
}

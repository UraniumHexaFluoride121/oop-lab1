package main;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class Storage<T extends Storable> implements IStorage<T> {
    private final int maxStorage;
    private final double maxWeight;
    private final Supplier<Double> x, y;
    private final ArrayList<T> storage = new ArrayList<>();

    /**
     * Creates a new {@code Storage}
     *
     * @param maxStorage The maximum number of items that can be stored.
     * @param maxWeight  The maximum weight of any individual item that is allowed to be stored
     * @param x          Provides the current x position of this storage
     * @param y          Provides the current y position of this storage
     */
    public Storage(int maxStorage, double maxWeight, Supplier<Double> x, Supplier<Double> y) {
        this.x = x;
        this.y = y;
        this.maxStorage = maxStorage;
        this.maxWeight = maxWeight;
    }

    @Override
    public void load(T t) {
        if (t.isBeingStored())
            return;
        if (t.getWeight() > maxWeight)
            return;
        if (storage.size() >= maxStorage)
            return;
        storage.addFirst(t);
        t.store(this);
    }

    @Override
    public void unload() {
        if (storage.isEmpty())
            return;
        Storable t = storage.removeFirst();
        t.remove(this);
    }

    @Override
    public void unload(String id) {
        Optional<T> t = storage.stream().filter(o -> Objects.equals(o.getID(), id)).findFirst();
        if (t.isEmpty())
            return;
        storage.remove(t.get());
        t.get().remove(this);

    }


    @Override
    public double getX() {
        return x.get();
    }

    @Override
    public double getY() {
        return y.get();
    }
}

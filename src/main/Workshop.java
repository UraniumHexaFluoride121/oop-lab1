package main;

public class Workshop<T extends Vehicle> implements IStorage<T> {
    private final Storage<T> storage;
    private final int x, y;
    private final Class<T> clazz;

    public Workshop(int maxStorage, double maxWeight, int x, int y, Class<T> clazz) {
        this.clazz = clazz;
        storage = new Storage<>(maxStorage, maxWeight, this::getX, this::getY);
        this.x = x;
        this.y = y;
    }

    @Override
    public void load(T t) {
        storage.load(t);
    }

    @Override
    public T unload() {
        return storage.unload();
    }

    @Override
    public T unload(String id) {
        return storage.unload(id);
    }

    public void tryLoad(Object v) {
        if (clazz.isInstance(v))
            load((T) v);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}

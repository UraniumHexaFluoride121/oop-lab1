public class Workshop<T extends Vehicle> implements IStorage<T> {
    private final Storage<T> storage;
    private final int x, y;

    public Workshop(int maxStorage, double maxWeight, int x, int y) {
        storage = new Storage<>(maxStorage, maxWeight, this::getX, this::getY);
        this.x = x;
        this.y = y;
    }

    @Override
    public void load(T t) {
        storage.load(t);
    }

    @Override
    public void unload() {
        storage.unload();
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

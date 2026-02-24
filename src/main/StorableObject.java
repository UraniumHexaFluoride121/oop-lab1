package main;

public class StorableObject implements Storable{
    private IStorage<?> storage = null;
    protected String id;
    protected double weight;

    @Override
    public String getID() {return id;}

    @Override
    public void store(IStorage<?> t) {
        storage = t;
    }

    @Override
    public void remove(IStorage<?> t) {
        storage = null;
    }

    @Override
    public boolean isBeingStored() {
        return storage != null;
    }

    @Override
    public double getX() { return storage.getX(); }

    @Override
    public double getY() { return storage.getY(); }

    @Override
    public double getWeight() { return weight; }

}

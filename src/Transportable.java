public interface Transportable {
    void transport(CarTransport vehicle);
    void unload(VehicleShell vehicle);
    double getX();
    double getY();
}

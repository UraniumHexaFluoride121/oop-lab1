package main;

public abstract class CarFactory {
    public static Vehicle volvo240(float x, float y, String licence) {
        return new Volvo240(x, y, licence);
    }

    public static Vehicle saab95(float x, float y, String licence) {
        return new Saab95(x, y, licence);
    }

    public static Vehicle volvoFL6(float x, float y, String licence) {
        return new VolvoFL6(x, y, licence);
    }

    public static Vehicle scania(float x, float y, String licence) {
        return new Scania(x, y, licence);
    }

}

package InputData;

public enum VehicleType {
    HELICOPTER("helicopter"),
    SUBMARINE("submarine"),
    CHOPPER("chopper"),
    SPACESHIP("spaceship");
    private final String name;
    VehicleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

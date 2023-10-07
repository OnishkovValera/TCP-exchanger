package InputData;

public enum FuelType {
    KEROSENE("kerosene"),
    ALCOHOL("alcohol"),
    PLASMA("plasma");
    private final String name;

    FuelType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}

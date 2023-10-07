package InputData;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vehicle implements Serializable, Comparable<Vehicle>{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer enginePower; //Поле не может быть null, Значение поля должно быть больше 0
    private double capacity; //Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null
    private FuelType fuelType; //Поле не может быть null
    private String owner;

    public Vehicle(Integer id, String owner, String name, Coordinates coordinates, LocalDateTime time, Integer enginePower, double capacity, VehicleType vehicleType, FuelType fuelType) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = time;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.type = vehicleType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public double getCapacity() {
        return capacity;
    }

    public VehicleType getType() {
        return type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Vehicle(String name, Coordinates coordinates, Integer enginePower, double capacity, VehicleType type, FuelType fuelType) {
        this.name = name;
        this.coordinates = coordinates;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.type = type;
        this.fuelType = fuelType;
    }

    public Vehicle( String name,String owner, Coordinates coordinates, LocalDateTime creationDate, Integer enginePower, double capacity, VehicleType type, FuelType fuelType) {
        this.name = name;
        this.owner = owner;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.type = type;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return
                "Vehicle " + id + "\n" +
                        "   owner: " + owner + "\n" +
                        "   name: " + name + "\n" +
                        "   coordinates: " + coordinates + "\n" +
                        "   creationDate: " + creationDate + "\n" +
                        "   enginePower: " + enginePower + "\n" +
                        "   capacity: " + capacity + "\n" +
                        "   type: " + type + "\n" +
                        "   fuelType: " + fuelType + "\n";
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        return enginePower - vehicle.getEnginePower();
    }

}

package Managers;

import InputData.Coordinates;
import InputData.FuelType;
import InputData.Vehicle;
import InputData.VehicleType;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseHandler {
    static Connection connection;
    final private static String URL = "jdbc:postgresql://localhost:5432/studs";
    final private static String name = "s368608";
    final private static String password = "F2n7XDlHIIdX1EqS";

    public static void connectToDB(){
        try {
            connection = DriverManager.getConnection(URL, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ConcurrentHashMap<Integer, Vehicle> getHashmap(){
        ConcurrentHashMap<Integer, Vehicle> hashMap = new ConcurrentHashMap();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicles");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Integer id = result.getInt("id");
                String owner = result.getString("owner");
                String name = result.getString("name");
                float x = result.getFloat("x");
                double y = result.getDouble("y");
                LocalDateTime time = result.getTimestamp("time").toLocalDateTime();
                Integer enginePower = result.getInt("enginepower");
                double capacity = result.getDouble("capacity");
                VehicleType vehicleType = VehicleType.valueOf(result.getString("vehicletype").toUpperCase());
                FuelType fuelType = FuelType.valueOf(result.getString("fueltype").toUpperCase());
                Vehicle vehicle = new Vehicle(id, owner, name, new Coordinates(x, y), time, enginePower, capacity, vehicleType, fuelType);
                hashMap.put(id, vehicle);
                System.out.println(vehicle);
            }

        }catch (SQLException exception){
            System.out.println("Cant load hashmap");
        }
        return hashMap;
    }
    public static void addVehicle(Vehicle vehicle){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into vehicles (owner, name, x, y, time, enginepower, capacity, vehicletype, fueltype) values (?,?,?,?,?,?,?,?,?);");
            statement.setString(1, vehicle.getOwner());
            statement.setString(2, vehicle.getName());
            statement.setFloat(3, vehicle.getCoordinates().getX());
            statement.setDouble(4, vehicle.getCoordinates().getY());
            statement.setTimestamp(5, Timestamp.valueOf(vehicle.getCreationDate()));
            statement.setInt(6, vehicle.getEnginePower());
            statement.setDouble(7, vehicle.getCapacity());
            statement.setString(8, vehicle.getType().toString());
            statement.setString(9, vehicle.getFuelType().toString());
            statement.execute();
            System.out.println("Vehicle added");
        }catch (SQLException exception){
            exception.printStackTrace();
            System.out.println("Can't add vehicle");
        }
    }
    public static void removeVehicle(Integer id ){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from vehicles where id = ?;");
            statement.setInt(1, id);
            statement.executeQuery();
        }catch (SQLException exception){
            System.out.println("Cant delete vehicle");
        }

    }
    public static void addClient(String login, String password){
        try {
            PreparedStatement statement = connection.prepareStatement("insert into peoples (name, password) VALUES (?, ?);");
            statement.setString(1, login);
            statement.setString(2,hashPassword(password));
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Cant add new client");
        }

    }
    public static boolean checkUser(String name){
        try {
            PreparedStatement statement = connection.prepareStatement("select name from peoples where name = ?;");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }catch (SQLException exception){
            exception.printStackTrace();
            System.out.println("Something went wrong");
            return false;
        }
    }
    public static String getPassword(String login){
        try {
            PreparedStatement statement = connection.prepareStatement("select password from peoples where name = ?;");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("password");
        }catch (SQLException exception){
            System.out.println("Something went wrong");
            return "no such name";
        }
    }
    public static String hashPassword(String passwd)  {
        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            hash = md.digest((passwd).getBytes("UTF-8"));
        }catch (NoSuchAlgorithmException | UnsupportedEncodingException exception){
            System.out.println("No such algorithm");
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public static void updateVehicle(Vehicle vehicle){
        try {
            PreparedStatement statement = connection.prepareStatement("update vehicles" +
                    " set(id, owner, name, x, y, time, enginePower, capacity, vehicleType, fuelType) =(?,?,?,?,?,?,?,?,?,?)" +
                    "where id = ?;");
            statement.setInt(1, vehicle.getId());
            statement.setString(2, vehicle.getOwner());
            statement.setString(3, vehicle.getName());
            statement.setFloat(4, vehicle.getCoordinates().getX());
            statement.setDouble(5, vehicle.getCoordinates().getY());
            statement.setTimestamp(6, Timestamp.valueOf(vehicle.getCreationDate()));
            statement.setInt(7, vehicle.getEnginePower());
            statement.setDouble(8, vehicle.getCapacity());
            statement.setString(9, vehicle.getType().toString());
            statement.setString(10, vehicle.getFuelType().toString());
            statement.setInt(11, vehicle.getId());
            statement.executeQuery();
        }catch (SQLException exception){
            System.out.println("can't update vehicle");
        }
    }
}

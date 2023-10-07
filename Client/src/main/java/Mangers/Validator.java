package Mangers;

import InputData.Coordinates;
import InputData.FuelType;
import InputData.Vehicle;
import InputData.VehicleType;
import Managers.Container;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Validator {

    public static Container validateData(String command) throws IllegalArgumentException, InterruptedException, IOException {
        String[] commandAndArgument = command.toLowerCase().trim().split(" ");
        Container container = null;
        if (CommandManager.isArgumentExists(commandAndArgument[0].trim())) {
            if (commandAndArgument.length > 1) {
                if (!commandAndArgument[0].trim().equals("execute_script")) {
                    Integer.parseInt(commandAndArgument[1]);
                }

                if (CommandManager.isElementNeeded(commandAndArgument[0].trim())) {
                    ConcurrentHashMap<Integer, Vehicle> hashMap = new ConcurrentHashMap<>();
                    hashMap.put(0, createVehicle());
                    container = new Container(CommandManager.getCommand(commandAndArgument[0]), commandAndArgument[1], hashMap);

                } else {
                    container = new Container(CommandManager.getCommand(commandAndArgument[0]), commandAndArgument[1]);

                }
            } else {
                throw new InvalidParameterException();
            }
        } else {
            if (CommandManager.isElementNeeded(commandAndArgument[0].trim())) {
                ConcurrentHashMap<Integer, Vehicle> hashMap = new ConcurrentHashMap<>();
                hashMap.put(0, createVehicle());
                container = new Container(CommandManager.getCommand(commandAndArgument[0]), null, hashMap);
            } else {
                container = new Container(CommandManager.getCommand(commandAndArgument[0]));
            }
        }
        return container;
    }


    public static Vehicle createVehicle() throws InterruptedException, IOException {

        Scanner scn = new Scanner(System.in);
        String name;
        String input;
        while (true) {
            System.out.print("Enter name\n$");
            name = scn.nextLine().trim();

            if (!(name.length() <= 0 || name.equals(null))) {
                Thread.sleep(100);
                break;

            } else {
                System.err.println("Name mustn't be null or empty");

            }
        }

        float x;
        while (true) {

            System.out.print("Enter coordinate x\n$");

            try {

                input = scn.nextLine();
                x = Float.parseFloat(input.trim().toLowerCase());
                Thread.sleep(100);
                break;

            } catch (NumberFormatException exception) {
                System.err.println("Coordinate x must be float");

            }
        }

        double y;
        while (true) {
            System.out.print("Enter coordinate y\n$");
            try {
                input = scn.nextLine();
                y = Double.parseDouble(input.toLowerCase().trim());
                Thread.sleep(100);
                break;

            } catch (NumberFormatException exception) {
                System.err.println("Coordinate y must be double");
            }
        }

        int enginePower;
        while (true) {
            System.out.print("Enter engin power\n$");
            try {


                input = scn.nextLine();

                enginePower = Integer.parseInt(input.trim().toLowerCase());
                Thread.sleep(100);
                if (enginePower > 0) {
                    break;
                } else {
                    System.err.println("Engine power must be bigger than zero");
                }
            } catch (NumberFormatException exception) {
                System.err.println("Engine power must be integer");
            }
        }

        double capacity;
        while (true) {
            System.out.print("Enter capacity \n$");
            try {
                input = scn.nextLine();
                capacity = Double.parseDouble(input);

                if (capacity > 0) {
                    break;
                } else {
                    System.err.println("Capacity must be bigger that zero");
                }
            } catch (NumberFormatException exception) {
                System.err.println("Capacity must be double");
                Thread.sleep(100);
            }
        }

        VehicleType type;
        while (true) {
            System.out.print("Enter vehicle type (helicopter / submarine / chopper / spaceship)\n$");
            try {
                input = scn.nextLine();
                type = VehicleType.valueOf(input.toUpperCase().trim());
                break;

            } catch (IllegalArgumentException exception) {
                System.err.println("No such type");
                Thread.sleep(100);
            }
        }

        FuelType fuelType;
        while (true) {
            System.out.print("Enter fuel type (kerosene / alcohol / plasma)\n$");
            try {
                input = scn.nextLine();
                fuelType = FuelType.valueOf(input.trim().toUpperCase());
                Thread.sleep(100);
                break;
            } catch (IllegalArgumentException exception) {
                System.err.print("No such fuel type");

            }
        }

        Vehicle vehicle = new Vehicle(name, new Coordinates(x, y), enginePower, capacity, type, fuelType);

        return vehicle;

    }
}

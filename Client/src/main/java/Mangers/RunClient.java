package Mangers;

import Managers.Container;
import Managers.ContainerHandler;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class RunClient {
    Scanner scn = new Scanner(System.in);

    public void run(ContainerHandler containerHandler) throws IOException, ClassNotFoundException, InterruptedException {

        PasswordHandler.setPassword();

        while (true){

            System.out.print("Write command\n$");
            Container container = null;

            try {
                container = Validator.validateData(scn.nextLine());
            } catch (InvalidParameterException exception) {
                System.err.println("This command requires an argument");
                Thread.sleep(100);
                continue;

            }catch (NumberFormatException exception){
                System.err.println("Argument must be number");
                Thread.sleep(100);
                continue;

            } catch (IllegalArgumentException exception){
                System.err.println("No such command");
                Thread.sleep(100);
                continue;

            }

            afterValidationStage(containerHandler, container);

        }
    }

    public static void  afterValidationStage(ContainerHandler containerHandler, Container container)
            throws IOException, ClassNotFoundException, InterruptedException {

        if (container.getCommand().toString().equals("executescript")) {
            ScriptHandler.handle(containerHandler, container.getArgument());

        } else if (container.getCommand().toString().equals("exit")) {
            containerHandler.sendContainer(new Container(true));

            container = containerHandler.readContainer();

            if (container.error) {
                System.out.println("Something went wrong");
                System.out.println("Do you want to close application?[Y/N]\n$");
                Scanner scn = new Scanner(System.in);

                if (scn.nextLine().trim().equalsIgnoreCase("y")) {
                    System.out.println("App is closing");
                    System.exit(1);

                }

            } else {
                System.out.println(container.getArgument());
                System.out.println("App is closing");
                System.exit(1);
            }

        } else {
            containerHandler.sendContainer(container);
            container = containerHandler.readContainer();

            if (container.error) {
                System.err.println(container.getArgument());
                Thread.sleep(100);

            } else if (container.getHashMap() == null) {

                System.out.println(container.getArgument());

            } else {
                for (Integer key : container.getHashMap().keySet()) {
                    System.out.println("Key:" + key);
                    System.out.println(container.getHashMap().get(key).toString());
                }

                System.out.println(container.getArgument());

            }
        }
    }
}


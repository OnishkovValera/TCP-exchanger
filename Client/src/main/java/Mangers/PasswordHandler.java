package Mangers;

import Managers.Container;
import Managers.ContainerHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class PasswordHandler {
    private static ContainerHandler containerHandler;

    public static void setContainerHandler(ContainerHandler containerHandler) {
        PasswordHandler.containerHandler = containerHandler;
    }

    public static void setPassword() throws IOException, ClassNotFoundException, InterruptedException {
        String password;
        String login;
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.print("Do you have account?[Y/N]\n$");
            Container container;

            if (scn.nextLine().trim().equalsIgnoreCase("n")) {

                System.out.print("Enter login\n$");
                login = scn.nextLine().trim();

                containerHandler.sendContainer(Container.registerNewClient(login));

                container = containerHandler.readContainer();

                if (container.error) {
                    System.out.println(container.getArgument());

                } else {
                    System.out.print("Enter password\n$");

                    password = scn.nextLine().trim();
                    containerHandler.sendContainer(Container.checkPassword(password));
                    container = containerHandler.readContainer();
                    if(container.error) {
                        System.out.println(container.getArgument());
                    }else {
                        System.out.println(container.getArgument());
                        break;
                    }
                }

            } else {

                System.out.print("Enter login\n$");
                login = scn.nextLine().trim();
                containerHandler.sendContainer(new Container(login));
                container = containerHandler.readContainer();

                if (container.error) {
                    System.err.println(container.getArgument());
                    Thread.sleep(50);

                } else {
                    System.out.print("Enter password\n$");
                    password = scn.nextLine().trim();
                    containerHandler.sendContainer(Container.checkPassword(password));
                    container = containerHandler.readContainer();
                    if (container.error) {
                        System.out.println(container.getArgument());
                    } else {
                        System.out.println("Password is right");
                        break;
                    }
                }
            }
        }
    }
}
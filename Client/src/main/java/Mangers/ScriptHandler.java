package Mangers;

import Managers.Container;
import Managers.ContainerHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScriptHandler {

    static ArrayList<String> files = new ArrayList<>();


    public static void handle(ContainerHandler containerHandler, String path) throws InterruptedException, IOException, ClassNotFoundException {

        Scanner scn;

        try {
            for (String str: files) {
                if(str.equals(path)){
                    System.err.println("This file create infinity cycle");
                    Thread.sleep(100);
                    files.clear();
                    return;
                }
            }

            scn = new Scanner(new File(path));
            files.add(path);

        } catch (FileNotFoundException e) {
            System.err.println("No such file");
            Thread.sleep(100);
            return;

        }

        while (scn.hasNext()) {
            Container container;
            try {
                container = Validator.validateData(scn.nextLine());
                RunClient.afterValidationStage(containerHandler, container);

            } catch (InvalidParameterException exception) {
                System.err.println("This command requires an argument");
                Thread.sleep(100);
                return;

            } catch (NumberFormatException exception) {
                System.err.println("Argument must be number");
                Thread.sleep(100);
                return;

            } catch (IllegalArgumentException exception) {
                System.err.println("No such command");
                Thread.sleep(100);
                return;

            }

        }

        files.remove(path);

    }
}
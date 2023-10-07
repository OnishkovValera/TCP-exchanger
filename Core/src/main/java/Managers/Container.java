package Managers;

import Commands.Command;
import InputData.Vehicle;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class Container implements Serializable {
    public boolean checkPassword = false;
    public boolean setNewClient = false;
    public boolean endConnection = false;
    public boolean error = false;

    private String env;
    private String login;
    private String password;
    private Command command;
    private String argument;
    private ConcurrentHashMap<Integer, Vehicle> hashMap;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public ConcurrentHashMap<Integer, Vehicle> getHashMap() {
        return hashMap;
    }

    public void setHashMap(ConcurrentHashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Container() {
    }

    public static Container checkPassword(String password){
        Container cnt = new Container();
        cnt.password = password;
        cnt.checkPassword = true;
        return cnt;
    }
    public static Container registerNewClient(String login){
        Container cnt = new Container();
        cnt.setNewClient = true;
        cnt.login = login;
        return cnt;
    }

    public Container(String login){

        this.login = login;
    }


    public Container(boolean endConnection){
        this.endConnection = endConnection;
    }

    public Container(Command command){
        this.command = command;
    }

    public Container(Command command, String argument){
        this.command = command;
        this.argument = argument;
    }
    public Container(Command command, String argument, ConcurrentHashMap<Integer, Vehicle> hashMap){
        this.command = command;
        this.argument = argument;
        this.hashMap = hashMap;
    }
    public Container(boolean error, String argument){
        this.error = error;
        this.argument = argument;
    }
    public Container(boolean error, String argument, ConcurrentHashMap<Integer, Vehicle> hashMap){
        this.error = error;
        this.argument = argument;
        this.hashMap = hashMap;

    }

}

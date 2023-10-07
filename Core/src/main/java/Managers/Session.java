package Managers;

import InputData.Vehicle;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class Session {


    private String login;

    boolean authorized = false;

    private ConcurrentHashMap<Integer, Vehicle> hashMap = new ConcurrentHashMap<>();
    private Date startSession;

    public Date getStartSession() {
        return startSession;
    }

    public ConcurrentHashMap <Integer, Vehicle> getHashMap() {
        return hashMap;
    }

    public void setHashMap(ConcurrentHashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Session() {
        this.startSession = new Date();
        initializeSession();
    }


    public void initializeSession() {
        hashMap = DatabaseHandler.getHashmap();

    }
}

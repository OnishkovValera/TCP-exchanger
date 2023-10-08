package Managers;

import InputData.Vehicle;

import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class CollectionManager {

    private static ConcurrentHashMap<SocketChannel, Session> sessions = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<SocketChannel, Session> getSessions() {
        return sessions;
    }

    public static ConcurrentHashMap<Integer, Vehicle> getSessionHashMap(SocketChannel socketChannel) {
        return sessions.get(socketChannel).getHashMap();
    }
    public static void openSession(SocketChannel socketChannel){
        sessions.put(socketChannel, new Session());
    }

    public static void closeSession(SocketChannel socketChannel){
        sessions.remove(socketChannel);
    }


    public static Session getSession(SocketChannel socketChannel){
        return sessions.get(socketChannel);
    }

    public static Integer idGenerator(ConcurrentHashMap<Integer, Vehicle> hashMap){
        Set<Integer> set = new HashSet<>();

        for(Vehicle vehicle : hashMap.values()) {
            set.add(vehicle.getId());
        }

        int[] numbers = IntStream.range(1, 2000000).filter(i -> !set.contains(i)).toArray();
        int randomNumber = new Random().nextInt(numbers.length);

        return numbers[randomNumber];
    }


    public static void updateOtherCollections(SocketChannel socketChannel){
        for(SocketChannel channel: CollectionManager.getSessions().keySet()){
            sessions.get(channel).setHashMap(DatabaseHandler.getHashmap());
        }
    }
}

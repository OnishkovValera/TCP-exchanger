package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;
import Managers.DatabaseHandler;

import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;


public class Insert extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        ConcurrentHashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        if(!hashMap.containsKey(container.getArgument())) {
            Vehicle vehicle = container.getHashMap().get(0);
            Integer key = Integer.parseInt(container.getArgument());
            DatabaseHandler.addVehicle(key, new Vehicle(vehicle.getName(), CollectionManager.getSession(socketChannel).getLogin(), vehicle.getCoordinates(),
                    LocalDateTime.now(), vehicle.getEnginePower(), vehicle.getCapacity(),
                    vehicle.getType(), vehicle.getFuelType()));
            hashMap = DatabaseHandler.getHashmap();
            CollectionManager.updateOtherCollections(socketChannel);
            return new Container(false, "Added");
        }else {
            return new Container(true, "Element with such key is already exists");
        }
    }
}


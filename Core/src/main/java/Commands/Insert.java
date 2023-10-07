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
        Vehicle vehicle = container.getHashMap().get(0);
        DatabaseHandler.addVehicle(new Vehicle(vehicle.getName(),CollectionManager.getSession(socketChannel).getLogin(), vehicle.getCoordinates(),
                LocalDateTime.now(), vehicle.getEnginePower(), vehicle.getCapacity(),
                vehicle.getType(), vehicle.getFuelType()));
        hashMap = DatabaseHandler.getHashmap();
        CollectionManager.updateOtherCollections(socketChannel);
        return new Container(false, "Added");

    }
}

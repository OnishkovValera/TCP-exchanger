package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;
import Managers.DatabaseHandler;

import java.nio.channels.SocketChannel;

public class Update extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        Vehicle vehicle = container.getHashMap().get(0);
        if (vehicle.getOwner().equals(CollectionManager.getSessionHashMap(socketChannel).get(Integer.parseInt(container.getArgument())).getOwner())){
            CollectionManager.getSessionHashMap(socketChannel).put(Integer.parseInt(container.getArgument()),
                    new Vehicle(Integer.parseInt(container.getArgument()), vehicle.getName(),CollectionManager.getSession(socketChannel).getLogin(), vehicle.getCoordinates(),
                            vehicle.getCreationDate(), vehicle.getEnginePower(),
                            vehicle.getCapacity(), vehicle.getType(), vehicle.getFuelType()));

            DatabaseHandler.updateVehicle(new Vehicle(Integer.parseInt(container.getArgument()),vehicle.getName(),CollectionManager.getSession(socketChannel).getLogin(), vehicle.getCoordinates(),
                    vehicle.getCreationDate(), vehicle.getEnginePower(),
                    vehicle.getCapacity(), vehicle.getType(), vehicle.getFuelType()));
            CollectionManager.updateOtherCollections(socketChannel);
            return new Container(false,"Vehicle has been updated");
        }else{
            return new Container(true, "you arent owner ");
        }
    }
}

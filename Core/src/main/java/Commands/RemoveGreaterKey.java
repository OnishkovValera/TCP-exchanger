package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;
import Managers.DatabaseHandler;

import java.nio.channels.SocketChannel;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RemoveGreaterKey extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        ConcurrentHashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        Set<Integer> keys = new HashSet<>();

        hashMap.keySet()
                .stream()
                .filter(integer -> Integer.parseInt(container.getArgument()) < integer)
                .forEach(keys::add);

        for(Integer key : keys){
            if(CollectionManager.getSession(socketChannel).getLogin().equals(CollectionManager.getSessionHashMap(socketChannel).get(key).getOwner())){
                DatabaseHandler.removeVehicle(CollectionManager.getSessionHashMap(socketChannel).get(key).getId());
                CollectionManager.getSessionHashMap(socketChannel).remove(key);
            }
        }

        CollectionManager.updateOtherCollections(socketChannel);
        return new Container(false, "All elements which have key more than the given has been deleted");

    }
}

package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;
import Managers.DatabaseHandler;

import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;


public class RemoveGreater extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        ConcurrentHashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        Object[] keys = hashMap.keySet()
                .stream()
                .filter(integer -> hashMap.get(integer).getEnginePower() < container.getHashMap().get(0).getEnginePower())
                .toArray();
        int[] intKeys = Arrays.stream(keys).mapToInt(key -> Integer.parseInt(key.toString())).toArray();
        for(Integer key : intKeys){
            if(CollectionManager.getSession(socketChannel).getLogin().equals(CollectionManager.getSessionHashMap(socketChannel).get(key).getOwner())){
                DatabaseHandler.removeVehicle(CollectionManager.getSessionHashMap(socketChannel).get(key).getId());
                CollectionManager.getSessionHashMap(socketChannel).remove(key);
            }
        }

        CollectionManager.updateOtherCollections(socketChannel);
        return new Container(false, "All elements less than the given one are removed");
    }
}

package Commands;

import Managers.CollectionManager;
import Managers.Container;
import Managers.DatabaseHandler;

import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Set;

public class RemoveAllByCapacity extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        Set<Integer> keySet = CollectionManager.getSessionHashMap(socketChannel).keySet();

        Object[] keys = CollectionManager.getSessionHashMap(socketChannel).keySet()
                .stream()
                .filter(integer -> CollectionManager.getSessionHashMap(socketChannel)
                    .get(integer)
                    .getCapacity() == Double.parseDouble(container.getArgument())).toArray();

        int[] intKeys = Arrays.stream(keys).mapToInt(key -> Integer.parseInt(key.toString())).toArray();

        for(int i = 0; i < intKeys.length; i++){
            if(CollectionManager.getSession(socketChannel).getLogin().equals(CollectionManager.getSessionHashMap(socketChannel).get(intKeys[i]).getOwner())){
                DatabaseHandler.removeVehicle(CollectionManager.getSessionHashMap(socketChannel).get(intKeys[i]).getId());
                CollectionManager.getSessionHashMap(socketChannel).remove(intKeys[i]);

            }
        }

        CollectionManager.updateOtherCollections(socketChannel);
        return new Container(false, "All vehicles with capacity = " + container.getArgument() + " have been deleted");
    }
}

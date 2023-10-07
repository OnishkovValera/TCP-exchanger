package Commands;

import Managers.CollectionManager;
import Managers.Container;
import Managers.DatabaseHandler;

import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Clear extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        Iterator<Integer> iterator = CollectionManager.getSessionHashMap(socketChannel).keySet().iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if(CollectionManager.getSession(socketChannel).getLogin().equals(CollectionManager.getSessionHashMap(socketChannel).get(i).getOwner())){
                DatabaseHandler.removeVehicle(CollectionManager.getSessionHashMap(socketChannel).get(i).getId());
                CollectionManager.getSessionHashMap(socketChannel).remove(i);

            }
        }
        CollectionManager.updateOtherCollections(socketChannel);
        return new Container(false, "Collection has been cleaned");
    }
}

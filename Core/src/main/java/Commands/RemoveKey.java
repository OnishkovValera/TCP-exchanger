package Commands;

import Managers.CollectionManager;
import Managers.Container;
import Managers.DatabaseHandler;

import java.nio.channels.SocketChannel;

public class RemoveKey extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        if(CollectionManager.getSession(socketChannel).getLogin().equals(CollectionManager.getSessionHashMap(socketChannel).get(Integer.parseInt(container.getArgument())).getOwner())){
            DatabaseHandler.removeVehicle(CollectionManager.getSessionHashMap(socketChannel).get(Integer.parseInt(container.getArgument())).getId());
            CollectionManager.getSessionHashMap(socketChannel).remove(Integer.parseInt(container.getArgument()));
            CollectionManager.updateOtherCollections(socketChannel);
            return new Container(false, "Element with key=" + container.getArgument() + " has been deleted");
        }else {
            return new Container(true, "You arent owner of this vehicle");
        }
    }
}

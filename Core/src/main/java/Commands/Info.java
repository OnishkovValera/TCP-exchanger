package Commands;

import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;


public class Info extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        return new Container(false,
                "Type: " + CollectionManager.getSessionHashMap(socketChannel).getClass() +
                        "\nSize: " + CollectionManager.getSessionHashMap(socketChannel).size()
        );
    }
}

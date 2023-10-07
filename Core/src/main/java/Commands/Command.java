package Commands;

import Managers.CollectionManager;
import Managers.Container;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface Command {

    CollectionManager collectionManager = new CollectionManager();
    Container execute(Container container, SocketChannel socketChannel);
    String toString();
}
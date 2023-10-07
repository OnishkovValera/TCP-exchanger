package Commands;

import Managers.CollectionManager;
import Managers.Container;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Save extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        return new Container(false, "Collection has been saved");
    }
}

package Commands;

import Managers.Container;

import java.nio.channels.SocketChannel;

public class Exit extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        System.exit(101);
        return new Container(false, "Server closed");
    }

}


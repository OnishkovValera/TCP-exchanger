package Managers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
    private static Selector staticSelector;
    Logger logger = Logger.getLogger(Connection.class.getName());
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public static Selector getStaticSelector() {
        return staticSelector;
    }

    public static void setStaticSelector(Selector staticSelector) {
        Connection.staticSelector = staticSelector;
    }

    public Connection(int port) throws IOException {

        serverSocketInitialization(port);
        selectorInitialization();
    }

    public void serverSocketInitialization(int port) throws IOException {

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        InetSocketAddress serverPort = new InetSocketAddress(port);
        serverSocketChannel.bind(serverPort);
        logger.log(Level.INFO, "Server socket initialized");
    }

    public Selector selectorInitialization() throws IOException {
        selector = Selector.open();
        staticSelector = selector;
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        logger.log(Level.INFO, "Selector initialized");
        return selector;
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public void setServerSocketChannel(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }
}

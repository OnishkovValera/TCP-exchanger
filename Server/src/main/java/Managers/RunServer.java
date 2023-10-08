package Managers;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunServer {
    Logger logger = Logger.getLogger(RunServer.class.getName());
    public void run(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        MessageHandler messageHandler = new MessageHandler();
        logger.log(Level.INFO, "Selector waiting");

        while (true){
            logger.log(Level.INFO, "Waiting messages or connections");
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeySet.iterator();

            if(iter.hasNext()) {
                SelectionKey handlingKey = iter.next();
                if (!handlingKey.isValid()) {
                    continue;
                }
                if (handlingKey.isAcceptable()) {
                    messageHandler.connectClient(serverSocketChannel.accept());
                }
                iter.remove();
            }
        }
    }
}

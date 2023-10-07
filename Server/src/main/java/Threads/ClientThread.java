package Threads;

import Managers.CollectionManager;
import Managers.MessageHandler;
import Managers.RunServer;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

public class ClientThread extends Thread{
    Selector selector;
    private SocketChannel socketChannel;

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        Logger logger = Logger.getLogger(RunServer.class.getName());
        CollectionManager.openSession(socketChannel);
        MessageHandler messageHandler = new MessageHandler(selector);
        while (true){
            try {
                messageHandler.handleMessage(socketChannel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

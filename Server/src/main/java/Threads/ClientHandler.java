package Threads;

import Managers.Container;
import Managers.MessageHandler;

import java.nio.channels.SocketChannel;
import java.util.concurrent.RecursiveAction;

public class ClientHandler extends RecursiveAction {
    private SocketChannel socketChannel;

    private Container container;

    public ClientHandler(SocketChannel socketChannel, Container container) {
        this.socketChannel = socketChannel;
        this.container = container;
    }

    @Override
    protected void compute() {
        Container sendContainer  = container.getCommand().execute(container, socketChannel);
        MessageHandler.threadPool.execute(new ThreadSender(sendContainer,socketChannel));
    }
}

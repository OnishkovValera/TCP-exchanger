package Threads;

import Managers.Container;
import Managers.ContainerHandler;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class ThreadSender implements Runnable{

    Container container;

    SocketChannel socketChannel;

    public ThreadSender(Container container, SocketChannel socketChannel) {
        this.container = container;
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        try {
            ContainerHandler.sendContainer(container, socketChannel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

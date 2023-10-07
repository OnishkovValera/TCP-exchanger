package Managers;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ContainerHandler {
    private SocketChannel socketChannel;

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public ContainerHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public static void sendContainer(Container container, SocketChannel socketChannel) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(container);
        socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
    }

    public static Container readContainer(SocketChannel socketChannel) throws IOException, ClassNotFoundException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(8000);

        socketChannel.read(byteBuffer);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Container container = (Container) objectInputStream.readObject();

        return container;
    }
    public void sendContainer(Container container) throws IOException {
        sendContainer(container, socketChannel);
    }

    public Container readContainer() throws IOException, ClassNotFoundException {
        return readContainer(socketChannel);
    }
}

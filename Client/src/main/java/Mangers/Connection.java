package Mangers;


import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Connection {
    static public SocketChannel connect(String hostName, int port) throws IOException {
        SocketChannel socketChannel = null;
        InetSocketAddress inetSocketAddress = new InetSocketAddress(hostName, port);
        System.out.println("Connecting, please wait...");
        while (true) {
            try {
                socketChannel = SocketChannel.open();
                socketChannel.connect(inetSocketAddress);
                if(socketChannel.isConnected()){
                    System.out.println(socketChannel.getRemoteAddress());
                    break;
                }
            }catch (ConnectException exception){
                socketChannel.close();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return socketChannel;
    }
}

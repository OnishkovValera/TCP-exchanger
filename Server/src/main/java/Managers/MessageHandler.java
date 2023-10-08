package Managers;

import Threads.ClientThread;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageHandler {
    Logger logger = Logger.getLogger(MessageHandler.class.getName());



    public void connectClient(SocketChannel socketChannel) {
        ClientThread clientThread = new ClientThread();
        clientThread.setSocketChannel(socketChannel);
        clientThread.start();

    }

    public void handleMessage(SocketChannel handlingChannel) throws IOException, ClassNotFoundException {
        Container container = ContainerHandler.readContainer(handlingChannel);
        System.out.println(container.getLogin() + container.setNewClient);
        if(container.setNewClient) {
            addClient(container.getLogin(), handlingChannel);
            CollectionManager.getSession(handlingChannel).authorized = true;
            logger.log(Level.INFO, "Creating new account " + handlingChannel.getRemoteAddress());
        }else if (!container.setNewClient & !CollectionManager.getSession(handlingChannel).authorized){
            if(DatabaseHandler.checkUser(container.getLogin())) {
                String login = container.getLogin();
                ContainerHandler.sendContainer(new Container(false, "Enter password"), handlingChannel);
                container = ContainerHandler.readContainer(handlingChannel);
                if(DatabaseHandler.getPassword(login).equals(DatabaseHandler.hashPassword(container.getPassword()))){
                    logger.log(Level.INFO, "user " + login + "has been authorized");
                    CollectionManager.getSession(handlingChannel).authorized = true;
                    CollectionManager.getSession(handlingChannel).setLogin(login);
                    ContainerHandler.sendContainer(new Container(false, "You has been authorized"), handlingChannel);

                }else {
                    ContainerHandler.sendContainer(new Container(true, "password is wrong"), handlingChannel);

                }
            }else{
                ContainerHandler.sendContainer(new Container(true, "No such loggin"), handlingChannel);
            }

        } else if(container.endConnection){
            disconnectClient(handlingChannel);

        }else{
            ContainerHandler.sendContainer(container.getCommand().execute(container, handlingChannel), handlingChannel);
            logger.log(Level.INFO, "Sending response to client with address " + handlingChannel.getRemoteAddress() );

        }
    }

    public void disconnectClient(SocketChannel socketChannel) throws IOException {
        logger.log(Level.INFO, "Disconnect client with address " +
                socketChannel.getRemoteAddress() +
                ", session was started in " +
                CollectionManager.getSession(socketChannel).getStartSession());
        CollectionManager.closeSession(socketChannel);
        ContainerHandler.sendContainer(new Container(false, "Session closed"), socketChannel);

    }

    public void addClient(String login, SocketChannel socketChannel) throws IOException {

        if(DatabaseHandler.checkUser(login)){
            ContainerHandler.sendContainer(new Container(true, "this login already taken" ), socketChannel);
        }else {
            try {
                ContainerHandler.sendContainer(new Container(false, "Enter password"), socketChannel);
                Container container = ContainerHandler.readContainer(socketChannel);
                DatabaseHandler.addClient(login, container.getPassword());
                ContainerHandler.sendContainer(new Container(false, "your account's login:" + login + ", you account password: " + container.getPassword()),socketChannel);
                System.out.println("Client added");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        logger.log(Level.INFO, "Sending a response");
    }

    public MessageHandler() {
    }
}

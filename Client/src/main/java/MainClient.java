import Managers.ContainerHandler;
import Mangers.Connection;
import Mangers.PasswordHandler;
import Mangers.RunClient;

import java.io.IOException;


public class MainClient {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException {

        ContainerHandler containerHandler = new ContainerHandler(Connection.connect("localhost", 8018));
        PasswordHandler.setContainerHandler(containerHandler);

        while(true) {
            try {
                RunClient client = new RunClient();
                client.run(containerHandler);

            } catch (IOException exception) {
                System.err.println("Server reset connection");
                Thread.sleep(100);
                containerHandler = new ContainerHandler(Connection.connect("localhost", 8018));
                PasswordHandler.setContainerHandler(containerHandler);

            }
        }
    }
}


import Managers.Connection;
import Managers.DatabaseHandler;
import Managers.RunServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Class.forName("org.postgresql.Driver");
        DatabaseHandler.connectToDB();
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.log(Level.INFO, "Server started");
        Connection connection = new Connection(8018);
        RunServer server = new RunServer();
        server.run(connection.getSelector(), connection.getServerSocketChannel());

    }
}
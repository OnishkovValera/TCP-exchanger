package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;


public class AverageOfEnginePower extends AbstractCommand {
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        ConcurrentHashMap<Integer, Vehicle> hashMap = CollectionManager.getSessionHashMap(socketChannel);
        int commonPower = (int) hashMap.values().stream().mapToInt(Vehicle::getEnginePower).summaryStatistics().getAverage();
        return new Container(false, Integer.toString(commonPower));

    }
}

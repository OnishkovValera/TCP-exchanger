package Commands;

import InputData.Vehicle;
import Managers.CollectionManager;
import Managers.Container;

import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;

public class FilterLessThanEnginePower extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        ConcurrentHashMap<Integer, Vehicle> newHashMap = new ConcurrentHashMap<>();
        Integer enginePower = Integer.parseInt(container.getArgument());
        CollectionManager.getSessionHashMap(socketChannel)
                .keySet()
                .stream()
                .filter(integer -> CollectionManager.getSessionHashMap(socketChannel)
                        .get(integer)
                        .getEnginePower() < enginePower)
                .forEach(integer -> newHashMap.put(integer,
                        CollectionManager.getSessionHashMap(socketChannel).get(integer)));

        return new Container(false, "All vehicles which have less than " + enginePower, newHashMap);

    }
}

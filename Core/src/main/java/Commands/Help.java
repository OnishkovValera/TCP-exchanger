package Commands;

import Managers.Container;

import java.nio.channels.SocketChannel;

public class Help extends AbstractCommand{
    @Override
    public Container execute(Container container, SocketChannel socketChannel) {
        return  new Container(false, """
                help : display help on available commands
                
                info : print information about the collection to standard output (type, initialization date, number of elements, etc.)
                
                show : print to standard output all elements of the collection in string representation
                
                insert null {element} : add a new element with the given key

                update id {element} : update the value of the collection element whose id is equal to the given one

                remove_key null : remove an element from the collection by its key

                clear : clear the collection

                save : save the collection to a file
                               
                execute_script file_name : read and execute script from specified file.
                The script contains commands in the same form in which they are entered by the user in interactive mode.
                               
                remove_greater {element} : remove from the collection all elements greater than the given one
                               
                replace_if_greater null {element} : replace value by key if new value is greater than old
                               
                remove_greater_key null : remove from the collection all elements whose key is greater than the given one
                               
                remove_all_by_capacity capacity : remove all elements from the collection,
                the value of the capacity field of which is equivalent to the given one
                               
                average_of_engine_power : Display the average value of the enginePower field for all elements in the collection

                filter_less_than_engine_power enginePower : output items, field value enginePower which is less than the specified
                """);
    }

}

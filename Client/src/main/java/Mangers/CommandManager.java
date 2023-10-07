package Mangers;

import Commands.Command;
import Commands.*;

import java.util.concurrent.ConcurrentHashMap;


public class CommandManager {
    public static ConcurrentHashMap<String, Command> commands = new ConcurrentHashMap<>();

    static{
        commands.put("help", new Help());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("clear", new Clear());
        commands.put("average_of_engine_power", new AverageOfEnginePower());
        commands.put("insert", new Insert());
        commands.put("update", new Update());
        commands.put("remove_key", new RemoveKey());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("replace_if_greater", new ReplaceIfGreater());
        commands.put("remove_greater_key", new RemoveGreaterKey());
        commands.put("remove_all_by_capacity", new RemoveAllByCapacity());
        commands.put("filter_less_than_engine_power", new FilterLessThanEnginePower());
        commands.put("execute_script", new ExecuteScript());
        commands.put("exit", new Exit());
    }
    public CommandManager() {
        commands.put("help", new Help());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("clear", new Clear());
        commands.put("average_of_engine_power", new AverageOfEnginePower());
        commands.put("insert", new Insert());
        commands.put("update", new Update());
        commands.put("remove_key", new RemoveKey());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("replace_if_greater", new ReplaceIfGreater());
        commands.put("remove_greater_key", new RemoveGreaterKey());
        commands.put("remove_all_by_capacity", new RemoveAllByCapacity());
        commands.put("filter_less_than_engine_power", new FilterLessThanEnginePower());
        commands.put("execute_script", new ExecuteScript());
        commands.put("exit", new Save());

    }
    public static boolean isArgumentExists(String name){
        return switch (name) {
            case  "help", "info", "show", "clear", "exit", "average_of_engine_power", "remove_greater" -> false;
            case "insert","update", "remove_key", "execute_script", "replace_if_greater", "remove_greater_key", "remove_all_by_capacity", "filter_less_than_engine_power" ->
                    true;
            default -> throw new IllegalArgumentException();
        };
    }

    public static boolean isElementNeeded(String name){
        return switch (name){
            case "insert", "update", "remove_greater", "replace_if_greater" -> true;
            default -> false;
        };
    }
    public static Command getCommand(String name){
        return commands.get(name);
    }

    public static void setCommand(ConcurrentHashMap<String, Command> commands) {
        CommandManager.commands = commands;
    }
}

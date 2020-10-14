package Server;

import Commands.Command;
import This.OrganizationData;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static Map<String, Command> commandMap = new HashMap<>();
    private static Map<String, Command> getCommandMap(){
        return commandMap;
    }
    public static void addCommand(String name, Command command){
        commandMap.put(name, command);
    }
    public static String execute(String action, OrganizationData orgData, Socket userSocket, String user) {
        String[] actionParts = action.split(" ");
        if (action.isEmpty()) {
            return "";
        }
        if (actionParts.length == 1) {
            Command command = commandMap.get(actionParts[0]);
            if (command != null) {
                return command.execute(null, orgData, userSocket, user);
            } else {
                return ("commands.Command doesn't exist.");
            }
        } else if (actionParts.length == 2) {
            Command command = commandMap.get(actionParts[0]);
            String arg = actionParts[1];
            if (command != null) {
                return command.execute(arg, orgData,  userSocket, user);
            } else {
                return ("commandMap.Command doesn't exist.");
            }
        } else {
            return ("Wrong command input.");
        }
    }

}

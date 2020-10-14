package Commands;

import Server.CommandExecutor;
import This.OrganizationData;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ExecuteScriptFileName implements Command {
    public ExecuteScriptFileName() {
        CommandExecutor.addCommand("execute_script", this);
    }
    static ArrayList<String> exScrHistory = new ArrayList<>();
    @Override
    public String execute(String s, OrganizationData data, Socket userSocket, String user) {
        try {
            File file = new File((String) s);
            Scanner in = new Scanner(file);

            String result = "";
            String exFileName= "";
            while (in.hasNextLine()) {
                String command=in.nextLine();
                String[] exArg = command.split(" ");
                if(exArg.length == 2) {
                    exFileName = exArg[1];
                }
                if (!(command.equals(""))) {
                    if (!(command.equals("execute_script " + exFileName))) {
                        result+=("Command \"" + command + "\":"+"\n");
                        result+=CommandExecutor.execute(command,data,userSocket, user);
                    }
                    else {
                        if (exScrHistory.contains("execute_script " + exFileName)) {
                            result+=("Command \"" + command + "\": is unavailable .");
                        }
                        else {
                            exScrHistory.add("execute_script " + exFileName);
                            result+=("Command \"" + command + "\":"+"\n");
                            result+=CommandExecutor.execute(command,data,userSocket,user);
                        }
                    }
                }
            }
            exScrHistory.clear();
            return result;
        } catch (NullPointerException | FileNotFoundException e) {
            return ("File not found.");
        }
    }
}

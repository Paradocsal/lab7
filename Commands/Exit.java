package Commands;

import Server.CommandExecutor;
import This.OrganizationData;

import java.net.Socket;

public class Exit implements Command {
    public Exit() {
        CommandExecutor.addCommand("exit", this);
    }



    public String execute(String arg, OrganizationData data, Socket userSocket, String user) {
        System.exit(0); return null;
    }

}

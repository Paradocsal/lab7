package Commands;

import Server.CommandExecutor;
import Server.DbConnector;
import This.OrganizationData;

import java.net.Socket;

public class Clear implements Command{
    public Clear() {
        CommandExecutor.addCommand("clear",this);
    }
    public String execute(String s, OrganizationData org, Socket userSocket, String user){
        org.clearCollection(user);
        DbConnector.uploadAllOrg();
        return ("Collection Cleared from your orgs.");
    }
}

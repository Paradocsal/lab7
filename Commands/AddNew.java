package Commands;

import Server.CommandExecutor;
import Server.DbConnector;
import Server.ServerReceiver;
import Server.ServerSender;
import This.Organization;
import This.OrganizationData;

import java.net.Socket;

public class AddNew implements Command {
    public AddNew(){
        CommandExecutor.addCommand("add",this);
    }
    public String execute(String s, OrganizationData orgData, Socket userSocket, String user){

       new ServerSender().send(userSocket,"",1);
        Organization organization =(Organization) new ServerReceiver().receive(userSocket);
        organization.setUser(user);
        organization.setId(DbConnector.getNewId());
        orgData.addOrganization(organization);
        DbConnector.uploadAllOrg();
        return (organization.getName() + " was added");

    }
}

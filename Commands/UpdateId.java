package Commands;

import Server.CommandExecutor;
import Server.DbConnector;
import Server.ServerReceiver;
import Server.ServerSender;
import This.Organization;
import This.OrganizationData;

import java.net.Socket;

public class UpdateId implements Command {
    public UpdateId() {
        CommandExecutor.addCommand("update_id", this);
    }

    @Override
    public String execute(String s, OrganizationData data, Socket userSocket, String user) {
        try {
            long id = Long.parseLong(s);
            if (data.getListOfIds().contains(id)) {
                if (data.getElementById(id).getUser().equals(user)) {
                    new ServerSender().send(userSocket, "", 1);
                    Organization organization = (Organization) new ServerReceiver().receive(userSocket);
                    data.updateOrganization(id, organization);
                    Organization newOrganization = data.getElementById(id);
                    DbConnector.uploadAllOrg();
                    return ("Element with id " + id + " was updated, new one:\n" + newOrganization);
                } else return "Element does not belong to you.";
            } else {
                return ("Band with id " + id + " doesn't exist");
            }

        } catch (NumberFormatException e) {
            return ("Wrong id number format");
        }

    }
}


package Commands;

import Server.CommandExecutor;
import Server.DbConnector;
import This.OrganizationData;

import java.net.Socket;

public class RemoveById implements Command {
    public  RemoveById(){
        CommandExecutor.addCommand("remove_id",this);
    }
    @Override
    public String execute(String arg, OrganizationData data, Socket userSocket, String user) {
        try {
            Long id = Long.parseLong(arg);
            int size = OrganizationData.getOrganizationArrayDeque().size();
            if (data.getListOfIds().contains(id)) {
                data.remove( data.getOrganizationArrayDeque().stream().filter(o ->o.getId()==id&&o.getUser().equals(user)).findFirst().get());
                DbConnector.uploadAllOrg();
                if (size>OrganizationData.getOrganizationArrayDeque().size()) return ("Organizartion with id " + id + " was removed");
                else return "Organizartion with id " + id + " does not belong to you";
            } else {
                return ("Organization with id " + id + " not found");
            }


        } catch (NumberFormatException e) {
            return ("Id's number format error");
        }
    }
}

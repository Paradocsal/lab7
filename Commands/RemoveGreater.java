package Commands;

import Server.CommandExecutor;
import Server.DbConnector;
import Server.ServerReceiver;
import Server.ServerSender;
import This.Organization;
import This.OrganizationData;

import java.net.Socket;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;

public class RemoveGreater implements Command{
    public RemoveGreater() {
        CommandExecutor.addCommand("remove_greater", this);
    }
    public String execute(String arg, OrganizationData data, Socket userSocket, String user) {
        long oldSize = data.getListOfIds().size();
        new ServerSender().send(userSocket, "", 1);
        Organization organization = (Organization) new ServerReceiver().receive(userSocket);
        try {
            ArrayDeque<Organization>arrayToSave = new ArrayDeque<>();
           data.getOrganizationArrayDeque().stream()
                    .filter(o -> organization.compareTo(o) < 0&&o.getUser().equals(user))
                   .forEach(x->arrayToSave.add(x));
           OrganizationData.setQ(arrayToSave);
            DbConnector.uploadAllOrg();
            long newSize = data.getListOfIds().size();
            return ((oldSize - newSize) + " elements greater than " + organization + "\nwere removed");
        }
        catch (NoSuchElementException e){
            return ( "0 elements greater than " + organization + "\nwere removed");
        }
    }
}

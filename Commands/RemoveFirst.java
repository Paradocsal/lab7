package Commands;

import Server.CommandExecutor;
import Server.DbConnector;
import This.OrganizationData;

import java.net.Socket;
import java.util.NoSuchElementException;

public class RemoveFirst implements Command{
    public  RemoveFirst(){
        CommandExecutor.addCommand("remove_first_element",this);
    }
    public String execute(String arg, OrganizationData data, Socket userSocket, String user) {
            try{
               String s= data.removeFirst(user);
                DbConnector.uploadAllOrg();
                return s;
            }
            catch (NoSuchElementException e){
                return ("Collection is empty.");
            }
    }
}

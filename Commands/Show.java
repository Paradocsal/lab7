package Commands;

import Server.CommandExecutor;
import This.Organization;
import This.OrganizationData;

import java.net.Socket;

public class Show implements Command {
    public Show(){
        CommandExecutor.addCommand("show",this  );
    }
    @Override
    public String execute(String s, OrganizationData data, Socket userSocket, String user){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue elements:\n");
        for (Organization org : data.getOrganizationArrayDeque()){
            stringBuilder.append(org+"\n");
        }
        return stringBuilder.toString();
    }
}

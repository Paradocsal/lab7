package Commands;

import Server.CommandExecutor;
import This.*;

import java.net.Socket;


public class Info implements Command {
    public Info(){
        CommandExecutor.addCommand("info",this);

    }

    @Override
    public String execute(String s, OrganizationData orgData, Socket userSocket, String user) {
        return ("Тип: ArrayDequeue\n"  +
                " Количество элементов: " + orgData.getOrganizationArrayDeque().size() + '\n');
    }
}

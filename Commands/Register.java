package Commands;

import Server.*;
import This.OrganizationData;

import java.net.Socket;

public class Register implements Command{
    public  Register(){
        CommandExecutor.addCommand("register",this);
    }
    @Override
    public String execute(String s, OrganizationData data, Socket socket, String user) {
        ServerSender serverSender = new ServerSender();
        ServerReceiver serverReceiver = new ServerReceiver();
        serverSender.send(socket, "Enter login", 2);
        String login = (String) (serverReceiver.receive(socket));
        serverSender.send(socket, "EnterPassword", 2);
        String password = ServerWorking.PasswordCoder((String) (serverReceiver.receive(socket)));
        if (DbConnector.addNewUser(login, password)) {
            return login;
        }
        else  return "false";
    }
}

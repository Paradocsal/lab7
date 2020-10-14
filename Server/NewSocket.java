package Server;

import Commands.Command;
import This.OrganizationData;

import java.net.Socket;
import java.util.Map;

public class NewSocket implements Runnable {
    private Socket clientSocket;
    private String newuser = "";
    private OrganizationData data;
    public NewSocket(Socket client,OrganizationData data) {
        this.clientSocket = client;this.data = data;
    }

    @Override
    public void run() {
        ServerSender serverSender = new ServerSender();
        ServerReceiver serverReceiver = new ServerReceiver();
        try {
            Map<Command, String> commandStringMap;
            while (newuser.equals("")) {
                Object o = serverReceiver.receive(clientSocket);
                commandStringMap = (Map<Command, String>) o;
                System.out.println("Executing the registration / logging command from the client with the address: " + clientSocket.getLocalAddress() + clientSocket.getPort()+"...");
                String result = commandStringMap.entrySet().iterator().next().getKey().execute(commandStringMap.entrySet().iterator().next().getValue(),data, clientSocket, null);
                if (result.equals("false"))
                    serverSender.send(clientSocket, "\n" +
                            "Login failed, please try again.", 0);
                else {
                    serverSender.send(clientSocket, "true", 0);
                    newuser = result;
                }

            }

            while (true) {

                Object o = serverReceiver.receive(clientSocket);
                commandStringMap = (Map<Command, String>) o;
                System.out.println("Executing command " + commandStringMap.entrySet().iterator().next().getKey().getClass().getName() + " from <username:" + newuser+"> with address: " + clientSocket.getLocalAddress() + clientSocket.getPort()+"...");
                DbConnector.loadAllOrg();
                serverSender.send(clientSocket, commandStringMap.entrySet().iterator().next().getKey().execute(commandStringMap.entrySet().iterator().next().getValue(),data, clientSocket, newuser), 0);
            }
        } catch (NullPointerException e) {
            System.out.println("Client with address:" + clientSocket.getLocalAddress() + clientSocket.getPort() + " passed out...");
        }
    }
}
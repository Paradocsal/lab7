import Commands.*;
import Server.NewSocket;
import Server.ServerReceiver;
import Server.ServerSender;
import Server.ServerWorking;
import This.OrganizationData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter port for server");
            System.out.print("> ");
            String line = bufferedReader.readLine();
            int port = Integer.parseInt(line);
        ServerWorking.create(port);
        OrganizationData organizationData = new OrganizationData();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (!ServerWorking.server.isClosed()) {
            Socket socket = ServerWorking.server.accept();
            executorService.execute(new NewSocket(socket,organizationData));
            System.out.println("Got a new connection from: "+socket.getLocalAddress()+socket.getPort());

        }
    }
}


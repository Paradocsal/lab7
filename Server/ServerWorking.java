package Server;

import Commands.*;
import This.OrganizationData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.BindException;
import java.net.ServerSocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ServerWorking {
    public static ServerSocket server;

    public static void create(int port) {
        try {
            DbConnector dbConnector = new DbConnector();
            DbConnector.ConnectionToDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        Help helpCommand = new Help();
        Info infoCommand = new Info();
        Exit exitCommand = new Exit();
        Show showCommand = new Show();
        AddNew addNewCommand = new AddNew();
        UpdateId updateCommand = new UpdateId();
        RemoveById removeByIdCommand = new RemoveById();
        Clear clearCommand = new Clear();
        ExecuteScriptFileName executeScriptCommand = new ExecuteScriptFileName();
        Head headCommand = new Head();
        RemoveFirst removeFirstCommand = new RemoveFirst();
        RemoveGreater removeGreaterCommand = new RemoveGreater();
        AverageOfEmployeesCount averageOfEmployeesCountCommand = new AverageOfEmployeesCount();
        PrintFieldDescendingPostalAddress printFieldDescendingPostalAddress = new PrintFieldDescendingPostalAddress();
        FilterStartsWithName filterStartsWithName = new FilterStartsWithName();
        Login login = new Login();
        Register register = new Register();
        try {
            server = new ServerSocket(port);
            System.out.println("Server has started...");
        } catch (BindException e) {

            System.out.println("This port is already occupied, the server may already be running!\n" +"Forcibly turn off..");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        checkForSaveCommand();
    }

    public static void checkForSaveCommand()  {
        Thread backgroundReaderThread = new Thread(() -> {
            System.out.println("Check for \"exit\" commands started");
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                while (!Thread.interrupted()) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    if (line.equalsIgnoreCase("exit")) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        backgroundReaderThread.start();
    }

    public static String PasswordCoder(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }
}

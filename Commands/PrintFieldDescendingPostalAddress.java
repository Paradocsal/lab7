package Commands;

import Server.CommandExecutor;
import This.OrganizationData;

import java.net.Socket;

public class PrintFieldDescendingPostalAddress implements Command {
    public PrintFieldDescendingPostalAddress() {
        CommandExecutor.addCommand("print_field_descending_postal_address", this);
    }

    @Override
    public String execute(String arg, OrganizationData data, Socket userSocket, String user) {
       StringBuilder s  = new StringBuilder("All postal addresses in decreasing order: ");
        data.getOrganizationArrayDeque().stream()
                .filter(o ->o.compareTo(o)<0)
                .forEach(o ->s.append("sss"));
        return s.toString();

    }
}

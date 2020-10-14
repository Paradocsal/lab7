package Commands;

import Server.CommandExecutor;
import This.OrganizationData;

import java.net.Socket;

public class FilterStartsWithName implements Command {
    public FilterStartsWithName() {
        CommandExecutor.addCommand("filter_starts_with_name", this);
    }

    @Override
    public String execute(String arg, OrganizationData data, Socket userSocket, String user) {
        if (arg != null) {
            StringBuilder stringBuilder = new StringBuilder();
            data.getOrganizationArrayDeque().stream()
                    .filter(o -> o.getName().startsWith(arg))
                    .forEach(o -> stringBuilder.append(o.getName()+"\n"));
            return stringBuilder.toString();
        } else {
            return ("Wrong input format");
        }
    }
}

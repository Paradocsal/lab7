package Commands;

import Server.CommandExecutor;
import This.OrganizationData;

import java.net.Socket;

public class AverageOfEmployeesCount implements Command{
    public AverageOfEmployeesCount() {
        CommandExecutor.addCommand("Average_of_employees_count", this);
    }

    @Override
    public String execute(String arg, OrganizationData data, Socket userSocket, String user) {
        return ("Average of employees count in the organization: " + data.getOrganizationArrayDeque().stream()
                .mapToInt(o -> o.getEmployeesCount()).average());
    }
}

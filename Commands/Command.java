package Commands;

import This.Organization;
import This.OrganizationData;

import java.io.Serializable;
import java.net.Socket;

public interface Command extends Serializable {
    String execute(String s, OrganizationData data, Socket userSocket,String user);
}

package Server;

import This.*;

import java.sql.*;
import java.util.ArrayDeque;

public class DbConnector {
        private static final String url = "jdbc:postgresql://pg:5432/studs";
    private static final String user = "s285683";
    private static final String password = "mmp071";
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    public static Boolean ConnectionToDB() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            throw e;

        }
    }

    public static Boolean addNewUser(String user, String password) {
        try {
            preparedStatement = connection.prepareStatement("insert into userdb values (?,?)");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean userExist(String user, String password) {

        try {
            preparedStatement = connection.prepareStatement("select *  from userdb d where exists( select * from userdb where d.login = ? and d.password= ?)");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void uploadAllOrg() {
        try {
            stmt = connection.createStatement();
            stmt.execute("TRUNCATE organizations");
            ArrayDeque<Organization> organizations = OrganizationData.getOrganizationArrayDeque();
            organizations.stream().forEach(x -> {
                try {
                    preparedStatement = connection.prepareStatement("INSERT into organizations values(?,?,?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setLong(1, x.getId());
                    preparedStatement.setString(2, x.getName());
                    preparedStatement.setFloat(3, x.getCoordinates().getX());
                    preparedStatement.setInt(4, x.getCoordinates().getY());
                    preparedStatement.setTimestamp(5, x.getCreationDate());
                    preparedStatement.setDouble(6, x.getAnnualTurnover());
                    preparedStatement.setString(7, x.getFullName());
                    preparedStatement.setInt(8, x.getEmployeesCount());
                    preparedStatement.setString(9, x.getType().toString());
                    preparedStatement.setString(10, x.getPostalAddress().getStreet());
                    preparedStatement.setString(11, x.getUser());
                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadAllOrg() {
        try {
            try {
                OrganizationData.getOrganizationArrayDeque().clear();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select * from organizations");
            while (rs.next()) {
                Organization organization = new Organization();
                organization.setId((long) rs.getInt(1));
                organization.setName(rs.getString(2));
                Coordinates coordinates = new Coordinates();
                coordinates.setX(rs.getInt(3));
                coordinates.setY(rs.getInt(4));
                organization.setCoordinates(coordinates);
                organization.setCreationDate(rs.getTimestamp(5));
                organization.setAnnualTurnover(rs.getDouble(6));
                organization.setFullName(rs.getString(7));
                organization.setEmployeesCount(rs.getInt(8));
                organization.setType(OrganizationType.valueOf(rs.getString(9)));
                Address address = new Address();
                address.setStreet(rs.getString(10));
                organization.setPostalAddress(address);
                organization.setUser(rs.getString(11));
                OrganizationData.getOrganizationArrayDeque().add(organization);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Long getNewId() {
        try {
            stmt = connection.createStatement();

            rs = stmt.executeQuery("SELECT nextval('id')");
            if (rs.next()) {
                return rs.getLong(1);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

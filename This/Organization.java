package This;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;


public class Organization implements Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Timestamp creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double annualTurnover; //Значение поля должно быть больше 0
    private String user;
    private String fullName; //Поле не может быть null

    private Integer employeesCount; //Поле не может быть null, Значение поля должно быть больше 0

    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле может быть null

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public double getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(double annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Integer employeesCount) {
        this.employeesCount = employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    public int compareTo(Organization org) {
        return this.getName().compareTo(org.getName());
    }

    @Override
    public String toString() {
        return "Organization" +
                "{id=" + id +
                "}\n\tname= '" + name + '\'' +
                "\n\tcoordinates= " + coordinates +
                "\n\tcreationDate= " + creationDate +
                "\n\tannualTurnover= " + annualTurnover +
                "\n\tfullName= '" + fullName + '\'' +
                "\n\temployeesCount= " + employeesCount +
                "\n\ttype= " + type +
                "\n\tpostalAddress= " + postalAddress+
                "\n\towner= " + user;
    }
}



package hu.elte.feladatnyilvantarto.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@NamedEntityGraph(
        name = "graph.authorBooks",
        attributeNodes = @NamedAttributeNode("groups")
)
@Entity
@Table(name = "USERS")
public class User
{
    @Id
    @GeneratedValue
    private int id;
    private String name;

    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Group> groups;
    @OneToMany(mappedBy = "leader", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Group> groupsLed;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<TimeMeasure> userTimeMeasures;

    @OneToOne
    private Ticket currentTicket;

    @ManyToMany(mappedBy = "assignees", fetch = FetchType.EAGER)
    private List<Ticket> assignedTickets;
    public Ticket getCurrentTicket() {
        return currentTicket;
    }

    public void setCurrentTicket(Ticket currentTicket) {
        this.currentTicket = currentTicket;
    }

    public User(String name, String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.groups = new ArrayList<>();
        this.assignedTickets = new ArrayList<>();
    }

    public User() {
        this.groups = new ArrayList<>();
        this.assignedTickets = new ArrayList<>();
    }

    public List<Group> getGroupsLed() {
        return groupsLed;
    }

    public void setGroupsLed(List<Group> groupsLed) {
        this.groupsLed = groupsLed;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public String getName()
    {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    public List<Group> getGroups()
    {
        return groups;
    }
    public void setGroups(List<Group> groups) {this.groups=groups;}

    public List<Ticket> getAssignedTickets()
    {
        return assignedTickets;
    }
    public void setAssignedTickets(List<Ticket> assignedTickets) {this.assignedTickets = assignedTickets;}

    public List<TimeMeasure> getUserTimeMeasures() {
        return userTimeMeasures;
    }

    public void setUserTimeMeasures(List<TimeMeasure> userTimeMeasures) {
        this.userTimeMeasures = userTimeMeasures;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof User user)
        {
            return  id == user.id ;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return  id +
                username.hashCode() +
                password.hashCode() +
                name.hashCode() +
                groups.hashCode() +
                groupsLed.hashCode();
    }
}
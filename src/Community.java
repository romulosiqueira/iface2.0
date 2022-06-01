import java.util.ArrayList;
import java.util.List;

public class Community{

    private String name;
    private String description;
    private User owner;

    private List<User> members;
    private List<CommunityMessage> messages;

    public Community(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;

        this.members = new ArrayList<User>();
        this.messages = new ArrayList<CommunityMessage>();
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }
    

    public void displayCommunity() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Owner: " + owner.getUsername());
        System.out.println("Members: ");
        for (User member : members) {
            System.out.println("\t" + member.getUsername());
        }
        System.out.println("Messages:");
        for (CommunityMessage message : messages) {
            message.displayMessage();
        }
    }

    public void addMessage(CommunityMessage message) {
        messages.add(message);
    }
}
public class Message {

    private User sender;
    private String message;

    Message(User sender, String message) {
        this.sender = sender;
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }
    
    public void displayMessage() {
        System.out.println("From: " + sender.getUsername());
        System.out.println("Message: " + message + "\n");
    }
 
}

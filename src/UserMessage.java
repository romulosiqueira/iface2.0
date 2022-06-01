public class UserMessage extends Message {

  private User reciever;

  public UserMessage(User sender, User receiver, String message) {
    super(sender, message);
    this.reciever = receiver;
  }
  
}

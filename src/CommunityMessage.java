public class CommunityMessage extends Message {

    private Community community;

    CommunityMessage(User sender, Community community,  String message) {
        super(sender, message);
        this.community = community;
        //TODO Auto-generated constructor stub
    }
    
}

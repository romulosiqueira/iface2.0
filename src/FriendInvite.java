public class FriendInvite {
    private User inviter;
    private User invitee;
    private String message;


    public FriendInvite(User inviter, User invitee, String message) {
        this.inviter = inviter;
        this.invitee = invitee;
        this.message = message;
    }

    public User getInvitee() {
        return invitee;
    }

    public User getInviter() {
        return inviter;
    }

    public String getMessage(){
        return message;
    }
}

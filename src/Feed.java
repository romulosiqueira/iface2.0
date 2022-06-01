import java.util.ArrayList;
import java.util.List;

public class Feed {

    private List<FeedMessage> feedMessages;

    Feed() {
        feedMessages = new ArrayList<FeedMessage>();
    }

    public void addFeedMessage(FeedMessage feedMessage) {
        feedMessages.add(feedMessage);
    }

    public void displayFeed(User current_user) {
        for (FeedMessage feedMessage : feedMessages) {
            if(feedMessage.getSender().getFeedControl() == FeedControl.PUBLIC || feedMessage.getSender().getFeedControl() == FeedControl.FRIENDS_ONLY && current_user.getFriends().contains(feedMessage.getSender()) ) {
                feedMessage.displayMessage();
            }
        }
    }

}

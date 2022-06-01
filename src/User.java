import java.util.ArrayList;
import java.util.List;

class User extends Profile {

  private String login;
  private String password;
  private String username;

  private List<FriendInvite> friendInvites;
  private List<User> friends;

  private List<Community> communities;
  private List<UserMessage> messages;

  private FeedControl feedControl;


  User(String login, String password, String username) {
    this.login = login;
    this.password = password;
    this.username = username;

    this.friendInvites = new ArrayList<>();
    this.friends = new ArrayList<>();
    this.communities = new ArrayList<>();
    this.messages = new ArrayList<>();
    this.feedControl = FeedControl.PUBLIC;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public void displayProfile() {
    System.out.println("Login: " + login);
    System.out.println("Username: " + username);

    super.displayProfile();
  }

  public String getUsername() {
    return username;
  }

  public void sendFriendInvite() {
    System.out.print("Enter your friend's username: ");
    String friendUsername = System.console().readLine();

    User friend = null;

    try {
      for (User user : IFace.getUsers()) {
        if (user.getUsername().equals(friendUsername)) {
          friend = user;
          break;
        }
      }

      if (friend == this) {
        throw new Exception("You can't add yourself as a friend");
      }

      if (friend == null) {
        throw new Exception("User not found");
      }

      System.out.print("Enter your message: ");
      String message = System.console().readLine();

      FriendInvite friendInvite = new FriendInvite(this, friend, message);
      friendInvites.add(friendInvite);
      friend.friendInvites.add(friendInvite);
      System.out.println("Friend invite sent");
      IFace.displayMainMenu();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      sendFriendInvite();
    }
  }

  public void displayFriendRequests() {
    try {
      if (friendInvites.size() == 0) {
        throw new Exception("No friend requests");
      }
      for (FriendInvite friendInvite : friendInvites) {
        if (friendInvite.getInviter() == this) {
          System.out.println("To: " + friendInvite.getInvitee().getUsername());
          System.out.println("Message: " + friendInvite.getMessage());
        } else {
          System.out.println(
            "From: " + friendInvite.getInviter().getUsername()
          );
          System.out.println("Message: " + friendInvite.getMessage());

          System.out.println("Accept friend request? (y/n)");
          String answer = System.console().readLine();

          if (answer.equals("y")) {
            acceptFriendInvite(friendInvite);
          } else if (answer.equals("n")) {
            declineFriendInvite(friendInvite);
          } else {
            throw new Exception("Invalid answer");
          }
        }
      }
      IFace.displayMainMenu();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      IFace.displayMainMenu();
      return;
    }
  }

  public void acceptFriendInvite(FriendInvite invite) {
    friends.add(invite.getInviter());
    invite.getInviter().friends.add(this);

    invite.getInvitee().friendInvites.remove(invite);
    invite.getInviter().friendInvites.remove(invite);

    System.out.println("Friend invite accepted");
  }

  void declineFriendInvite(FriendInvite invite) {
    invite.getInvitee().friendInvites.remove(invite);
    invite.getInviter().friendInvites.remove(invite);

    System.out.println("Friend invite declined");
  }

  public void displayFriends() {
    try {
      if (friends.size() == 0) {
        throw new Exception("No friends");
      }
      for (User friend : friends) {
        System.out.println(friend.getUsername());
      }
      IFace.displayMainMenu();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      IFace.displayMainMenu();
      return;
    }
  }

  public void addCommunity(Community community) {
    communities.add(community);
  }

  public void displayCommunities() {
    try {
      if (communities.size() == 0) {
        throw new Exception("No communities");
      }
      for (Community community : communities) {
        System.out.println(community.getName());
      }

      System.out.println("1 - Select Community");
      System.out.println("2 - Back");

      int choice = Integer.parseInt(System.console().readLine());

      if (choice == 1) {
        System.out.println("Enter the community name: ");
        String communityName = System.console().readLine();

        Community community = null;

        for (Community c : communities) {
          if (c.getName().equals(communityName)) {
            community = c;
            break;
          }
        }

        if (community == null) {
          throw new Exception("Community not found");
        }

        community.displayCommunity();
      } else if (choice == 2) {
        IFace.displayMainMenu();
      } else {
        throw new Exception("Invalid choice");
      }

      IFace.displayMainMenu();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      IFace.displayMainMenu();
      return;
    }
  }

  public void joinCommunity() {
    System.out.println("Enter the community name: ");
    String communityName = System.console().readLine();

    Community community = null;
    try {
      for (Community c : IFace.getCommunities()) {
        if (c.getName().equals(communityName)) {
          community = c;
          break;
        }
      }

      if (community == null) {
        throw new Exception("Community not found");
      }

      community.addMember(this);
      this.communities.add(community);
      System.out.println("Joined community");
      IFace.displayMainMenu();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      IFace.displayMainMenu();
      return;
    }
  }

  public void sendMessageToFriend() {
    System.out.print("Enter your friend's username: ");
    String friendUsername = System.console().readLine();

    User friend = null;

    try {
      for (User user : this.friends) {
        if (user.getUsername().equals(friendUsername)) {
          friend = user;
          break;
        }
      }

      if (friend == this) {
        throw new Exception("You can't send a message to yourself");
      }

      if (friend == null) {
        throw new Exception("Friend not found");
      }

      System.out.print("Enter your message: ");
      String message = System.console().readLine();

      UserMessage userMessage = new UserMessage(this, friend, message);
      friend.messages.add(userMessage);
      System.out.println("Message sent");
      IFace.displayMainMenu();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      sendMessageToFriend();
    }
  }

  public void sendMessageToCommunity() {
    System.out.print("Enter the community name: ");
    String communityName = System.console().readLine();

    Community community = null;

    try {
      for (Community c : this.communities) {
        if (c.getName().equals(communityName)) {
          community = c;
          break;
        }
      }

      if (community == null) {
        throw new Exception("You are not a member of this community");
      }

      System.out.print("Enter your message: ");
      String message = System.console().readLine();

      CommunityMessage communityMessage = new CommunityMessage(
        this,
        community,
        message
      );
      community.addMessage(communityMessage);
      System.out.println("Message sent");
      IFace.displayMainMenu();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      IFace.displayMainMenu();
    }
  }

  public void displayMessages() {
    try {
      if (messages.size() == 0) {
        throw new Exception("No messages");
      }
      for (UserMessage message : messages) {
        message.displayMessage();
      }
      IFace.displayMainMenu();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      IFace.displayMainMenu();
      return;
    }
  }


  public List<User> getFriends() {
    return friends;
  }

  public FeedControl getFeedControl() {
    return feedControl;
  }

  public void setFeedControl() {

    System.out.println("Choose who can see your feed:");

    System.out.println("1 - Friends");
    System.out.println("2 - Everyone");
    System.out.println("3 - Back");

    int choice = Integer.parseInt(System.console().readLine());

    switch(choice) {
      case 1:
        this.feedControl = FeedControl.FRIENDS_ONLY;
        break;
      case 2:
       this.feedControl = FeedControl.PUBLIC;
        break;
      case 3:
        IFace.displayMainMenu();
        break;
      default:
        System.out.println("Invalid choice");
        setFeedControl();
    }



  }
}

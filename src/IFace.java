import java.util.ArrayList;
import java.util.List;

class IFace {

  private static List<User> users = new ArrayList<User>();
  private static List<Community> communities = new ArrayList<Community>();
  private static User current_user;

  private static Feed feed = new Feed();

  public static void displayLoginMenu() {
    System.out.println("\n -------------------------------- \n");

    System.out.println("1. Login");
    System.out.println("2. Register");
    System.out.println("3. Exit");

    System.out.println("\n -------------------------------- \n");

    System.out.print("Enter your choice: ");

    try {
      int choice = Integer.parseInt(System.console().readLine());

      switch (choice) {
        case 1:
          login();
          break;
        case 2:
          createUser();
          break;
        case 3:
          System.exit(0);
          break;
        default:
          System.out.println("Invalid choice");
          displayLoginMenu();
          break;
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid choice");
      displayLoginMenu();
    }
  }

  public static void createUser() {

    System.out.print("Enter your username: ");
    String username = System.console().readLine();

    System.out.print("Enter your login: ");
    String login = System.console().readLine();

    try {
      for (User user : users) {
        if (user.getLogin().equals(login)) {
          throw new Exception("Login already exists");
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      createUser();
    }

    System.out.print("Enter your password: ");
    String password = System.console().readLine();

    User user = new User(login, password, username);
    users.add(user);
    current_user = user;

    System.out.println("User created successfully");

    displayLoginMenu();
  }

  public static void login() {
    System.out.print("Enter your login: ");
    String login = System.console().readLine();

    System.out.print("Enter your password: ");
    String password = System.console().readLine();

    try {
      for (User user : users) {
        if (user.getLogin().equals(login)) {
          if (user.getPassword().equals(password)) {
            current_user = user;
            System.out.println("Login successful");
            displayMainMenu();
            return;
          }
          throw new Exception("Wrong password !");
        }
      }
      throw new Exception("User not found");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      displayLoginMenu();
    }
  }

  public static List<User> getUsers() {
    return users;
  }

  public static void displayMainMenu() {
    System.out.println("\n -------------------------------- \n");

    System.out.println("1. Edit Profile");

    System.out.println("2. Add Friend");

    System.out.println("3. Display Friend Requests");

    System.out.println("4. Display Friends");

    System.out.println("5. Create a Community");

    System.out.println("6. Display Communities");

    System.out.println("7. Join a Community");

    System.out.println("8. Send a Message");

    System.out.println("9. Display Messages");

    System.out.println("10. Display profile");

    System.out.println("11. Delete Account");

    System.out.println("12. View Feed");

    System.out.println("13. Post Feed");

    System.out.println("14. Set Feed Control");

    System.out.println("15. Logout");

    System.out.print("Enter your choice: ");

    System.out.println("\n -------------------------------- \n");

    int choice = Integer.parseInt(System.console().readLine());

    switch (choice) {
      case 1:
        current_user.editProfile();
        break;
      case 2:
        current_user.sendFriendInvite();
        break;
      case 3:
        current_user.displayFriendRequests();
        break;
      case 4:
        current_user.displayFriends();
        break;
      case 5:
        createCommunity();
        break;
      case 6:
        current_user.displayCommunities();
        break;
      case 7:
        current_user.joinCommunity();
        break;
      case 8:
        sendMessage();
        break;
      case 9:
        current_user.displayMessages();
        break;
      case 10:
        current_user.displayProfile();
        break;
      case 11:
        deleteAccount();
        break;
      case 12:
        viewFeed();
        break;
      case 13:
        postFeed();
        break;
      case 14:
        current_user.setFeedControl();
        break;
      case 15:
        displayLoginMenu();
        break;
      default:
        System.out.println("Invalid choice");
        displayMainMenu();
        break;
    }
  }

  public static void createCommunity() {
    System.out.print("Enter your community name: ");
    String name = System.console().readLine();

    System.out.print("Enter your community description: ");
    String description = System.console().readLine();

    Community community = new Community(name, description, current_user);
    current_user.addCommunity(community);
    community.addMember(current_user);

    communities.add(community);

    System.out.println("Community created successfully");

    displayMainMenu();
  }

  public static void addCommunity(Community community) {
    communities.add(community);
  }

  public static List<Community> getCommunities() {
    return communities;
  }

  public static void sendMessage() {
    System.out.println("\n -------------------------------- \n");

    System.out.println(
        "Do you want to send a message to a friend or a community ?");

    System.out.println("1. Friend");
    System.out.println("2. Community");

    System.out.println("\n -------------------------------- \n");

    System.out.print("Enter your choice: ");

    int choice = Integer.parseInt(System.console().readLine());

    switch (choice) {
      case 1:
        current_user.sendMessageToFriend();
        break;
      case 2:
        current_user.sendMessageToCommunity();
        break;
      default:
        System.out.println("Invalid choice");
        displayMainMenu();
        break;
    }
  }

  public static void deleteAccount() {

    System.out.println("Are you sure you want to delete your account ?");

    System.out.println("1. Yes");
    System.out.println("2. No");

    System.out.print("Enter your choice: ");

    int choice = Integer.parseInt(System.console().readLine());

    switch (choice) {

      case 1:
        users.remove(current_user);
        System.out.println("Account deleted successfully");
        displayLoginMenu();
        break;

      case 2:
        displayMainMenu();
        break;

      default:
        System.out.println("Invalid choice");
        displayMainMenu();
        break;

    }

  }

  public static void viewFeed() {
    feed.displayFeed(current_user);
    displayMainMenu();
  }

  public static void postFeed() {
    System.out.print("Enter your post: ");
    String post = System.console().readLine();

    FeedMessage feedMessage = new FeedMessage(current_user, post);

    feed.addFeedMessage(feedMessage);
    displayMainMenu();
  }
}

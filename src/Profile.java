public class Profile {

  private String firstname;
  private String lastname;
  private String phone;
  private String birthDate;
  private String email;

  public void displayProfile() {
    System.out.println("Firstname: " + firstname);
    System.out.println("Lastname: " + lastname);
    System.out.println("Email: " + email);
    System.out.println("Phone: " + phone);
    System.out.println("Birthdate: " + birthDate);

    IFace.displayMainMenu();
  }

  public void editProfile() {
    System.out.println("Edit profile");

    //ask what user wants to edit

    System.out.println("What do you want to edit?");
    System.out.println("1. Firstname");
    System.out.println("2. Lastname");
    System.out.println("3. Email");
    System.out.println("4. Phone");
    System.out.println("5. Birthdate");
    System.out.println("6. Back");
    System.out.print("Enter your choice: ");

    int choice = Integer.parseInt(System.console().readLine());

    try {
      switch (choice) {
        case 1:
          System.out.print("Enter your new firstname: ");
          firstname = System.console().readLine();
          break;
        case 2:
          System.out.print("Enter your new lastname: ");
          lastname = System.console().readLine();
          break;
        case 3:
          System.out.print("Enter your new email: ");
          email = System.console().readLine();
          break;
        case 4:
          System.out.print("Enter your new phone: ");
          phone = System.console().readLine();
          break;
        case 5:
          System.out.print("Enter your new birthdate: ");
          birthDate = System.console().readLine();
          break;
        case 6:
          IFace.displayMainMenu();
          break;
        default:
          System.out.println("Invalid choice");
          editProfile();
          break;
      }
       editProfile();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      editProfile();
    }
  }

}

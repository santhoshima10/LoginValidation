import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LoginValidationApplication {
    static User[] userArray = new User[20];
    static User currentUserObj;
    static int userIndex;
    static User anotherUserObj;
    static int anotherUserIndex;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int noOfLoginAttempts = 5;
        boolean loginSuccessful = false;
        String inputEmailId = "";
        String inputPassword = "";

        FileService.processUserDataFromFile();

        //This check is added to proceed only if the array of user objects are loaded from file.
        if (userArray[0] != null) {

            LoginValidationApplication userValidation = new LoginValidationApplication();
            Scanner scanner = new Scanner(System.in);

            while (noOfLoginAttempts > 0) {
                System.out.print("Enter your email:");
                inputEmailId = scanner.nextLine();
                System.out.print("Enter your password:");
                inputPassword = scanner.nextLine();
                if (userValidation.validateUser(inputEmailId, inputPassword)) {
                    loginSuccessful = true;
                    break;
                } else {
                    System.out.println("Invalid login, please try again.");
                    noOfLoginAttempts--;
                }

            }
            if (loginSuccessful) {
                currentUserObj.welcomeUser();
                currentUserObj.displayUserUpdateOptions();
                String updateOptions = scanner.nextLine();

                while (!updateOptions.equals("4")) {

                    if (updateOptions.equals("1")) {

                        System.out.println("Please type in your new username:");
                        String newEmail = scanner.nextLine();
                        userArray[userIndex].setLoginEmail(newEmail);
                        currentUserObj.setLoginEmail(newEmail);
                        System.out.println("Username Changed!!");
                    } else if (updateOptions.equals("2")) {

                        System.out.println("Please type in your new password:");
                        String newPwd = scanner.nextLine();
                        userArray[userIndex].setLoginPassword(newPwd);
                        currentUserObj.setLoginPassword(newPwd);
                        System.out.println("Password Changed!!");

                    } else if (updateOptions.equals("3")) {

                        System.out.println("Please type in your new name:");
                        String newUsername = scanner.nextLine();
                        userArray[userIndex].setLoginUsername(newUsername);
                        currentUserObj.setLoginUsername(newUsername);
                        currentUserObj.welcomeUser();

                    }
                    else if (updateOptions.equals("0")) {

                        System.out.println("Which user would you like to login as? (Type in a valid username)");
                        String loginAsAnotherUser = scanner.nextLine();

                         anotherUserObj = userValidation.getAnotherUserInfo(loginAsAnotherUser);
                         anotherUserObj.welcomeUser();
                        anotherUserObj.displayUserUpdateOptions();
                        //Same cycle as above
                        String anotherUserUpdateOptions = scanner.nextLine();



                        while (!anotherUserUpdateOptions.equals("4")) {

                            if (anotherUserUpdateOptions.equals("1")) {

                                System.out.println("Please type in your new username:");
                                String newEmail = scanner.nextLine();
                                userArray[anotherUserIndex].setLoginEmail(newEmail);
                                anotherUserObj.setLoginUsername(newEmail);
                                System.out.println("Username Changed!!");
                            } else if (anotherUserUpdateOptions.equals("2")) {

                                System.out.println("Please type in your new password:");
                                String newPwd = scanner.nextLine();
                                userArray[anotherUserIndex].setLoginPassword(newPwd);
                                anotherUserObj.setLoginPassword(newPwd);
                                System.out.println("Password Changed!!");

                            } else if (anotherUserUpdateOptions.equals("3")) {

                                System.out.println("Please type in your new name:");
                                String newUsername = scanner.nextLine();
                                userArray[anotherUserIndex].setLoginUsername(newUsername);
                                anotherUserObj.setLoginUsername(newUsername);
                                anotherUserObj.welcomeUser();

                            }
                            anotherUserObj.displayUserUpdateOptions();
                            anotherUserUpdateOptions = scanner.nextLine();
                        }






                        }

                    currentUserObj.displayUserUpdateOptions();
                    updateOptions = scanner.nextLine();


                }

                //Update the users Array with the new data and sort
                Arrays.sort(userArray);
                //Update the file with new data
                // userValidation.displayUsers();
                FileService.updateFile();


            } else {
                System.out.println("Too many failed login attempts, you are now locked out.");
            }
            scanner.close();
        }

    }


    // function to check if the user name and password entered from console matches
    // the user data objects
    public boolean validateUser(String emailId, String password) {

        boolean isValid = false;

        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i] != null) {
                User user = userArray[i];
                userIndex = i;
                if (user.getLoginEmail().equalsIgnoreCase(emailId) && user.getLoginPassword().equals(password)) {
                    currentUserObj = user;
                    isValid = true;
                    break;
                }
            } else {
                break;
            }
        }
        return isValid;
    }

    public void displayUsers() {
        for (int i = 0; i < userArray.length; i++) {
            System.out.println(userArray[i].getLoginEmail());
            System.out.println(userArray[i].getLoginUserRole());
            System.out.println(userArray[i].getLoginUsername());
            System.out.println(userArray[i].getLoginPassword());
        }
    }

    private User getAnotherUserInfo(String userEmail){

        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i] != null) {
                User user = userArray[i];
                anotherUserIndex = i;
                if (user.getLoginEmail().equalsIgnoreCase(userEmail)) {
                    anotherUserObj = user;
                    break;
                }
            } else {
                break;
            }
        }

        return anotherUserObj;

    }




}

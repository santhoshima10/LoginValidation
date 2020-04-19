public class SuperUser extends  User {

    public SuperUser(String loginEmail, String loginPassword, String loginUsername, String loginUserRole){
        super( loginEmail,  loginPassword,  loginUsername,  loginUserRole);
    }

    public void displayUserUpdateOptions (){

        System.out.println("-------------------------");
        System.out.println("Please choose from the following options:");
        System.out.println("(0) Log in as another user");
        System.out.println("(1) Update username");
        System.out.println("(2) Update password");
        System.out.println("(3) Update name");
        System.out.println("(4) Exit");

    }



}

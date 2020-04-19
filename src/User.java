public class User implements Comparable<User>{

    private String loginEmail;
    private String loginPassword;
    private String loginUsername;
    private String loginUserRole;

    public User(String loginEmail, String loginPassword, String loginUsername, String loginUserRole){
        this.loginEmail = loginEmail;
        this.loginPassword = loginPassword;
        this.loginUsername = loginUsername;
        this.loginUserRole = loginUserRole;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getLoginUserRole(){
        return loginUserRole;
    }


    public void setLoginUserRole(String loginUserRole){
         this.loginUserRole = loginUserRole;
    }

    public void displayUserUpdateOptions (){

        System.out.println("-------------------------");
        System.out.println("Please choose from the following options:");
      //  System.out.println("(0) Log in as another user");
        System.out.println("(1) Update username");
        System.out.println("(2) Update password");
        System.out.println("(3) Update name");
        System.out.println("(4) Exit");

    }

    public void welcomeUser(){
        System.out.println("Welcome " + this.loginUsername);
    }


    @Override
    public int compareTo(User nextObject) {
       if(nextObject.getLoginUserRole().compareTo(this.getLoginUserRole()) == 0){
           return this.getLoginEmail().compareTo(nextObject.getLoginEmail());
       }
       else{
           return nextObject.getLoginUserRole().compareTo(this.getLoginUserRole());
       }


    }

    @Override
    public String toString() {

        return this.getLoginEmail()+", "+this.getLoginPassword()+", "+this.getLoginUsername()+", "+this.getLoginUserRole();


    }
}

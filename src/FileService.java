import java.io.*;


public class FileService {

    // function to process the users data from text file and create an array of user
    // data objects
    public static void processUserDataFromFile() {

        int index = 0;
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader("users.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                User user = createUserObject(line);
                LoginValidationApplication.userArray[index] = user;
                index++;
                line = bufferedReader.readLine();
            }

        } catch (IOException ex) {
            System.out.println("Error in Reading the file using the BufferedReader");
            ex.printStackTrace();
        }
        finally {
            if(bufferedReader!=null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Error closing the BufferedReader");
                    e.printStackTrace();
                }
            }
        }

    }


    private static User createUserObject(String input) {

        String[] userDetails = input.split(",");

        if(userDetails[3].trim().equals("super_user")){
            SuperUser superUser = new SuperUser(userDetails[0].trim(),userDetails[1].trim(),userDetails[2].trim(),userDetails[3].trim());
            return superUser;
        }
        else {
            NormalUser normalUser = new NormalUser(userDetails[0].trim(),userDetails[1].trim(),userDetails[2].trim(),userDetails[3].trim());
            return normalUser;
        }


    }

    //When the user updates the details the user file should reflect the changes.
    public static void updateFile(){

        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter("output.txt"));
            for(int i = 0; i < LoginValidationApplication.userArray.length; i++){
                writer.write(LoginValidationApplication.userArray[i].toString());
                if(i < LoginValidationApplication.userArray.length-1){
                    writer.newLine();
                }

            }



            //Delete Old users.txt file
            File oldFile = new File("users.txt");
            if(oldFile.delete()){
                System.out.println("Old users file is deleted successfully");

                File newFile = new File("output.txt");

                if(newFile.renameTo(oldFile)){
                    System.out.println("New users file is renamed successfully");
                }

            }





        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            if(writer!= null){
                try {
                    writer.close();
                }
                catch (IOException wr){
                    wr.printStackTrace();
                }

            }
        }

    }



}

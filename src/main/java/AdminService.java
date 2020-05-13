import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class AdminService {



    public static String encrypt(String passwordToEncrypt) throws NoSuchAlgorithmException {


        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(passwordToEncrypt.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        //System.out.println(hashtext);

        return hashtext;
    }
   /* public static void writeDataInJsonFile()
    {

    ObjectMapper objectMapper = new ObjectMapper();
    //String testJson = "{ "username" : "Fyodr", "password" : "Amali" }";

        try {

            File file1 = new File("users.json");

            List<JsonUser> listUsr = objectMapper.readValue(file1, new TypeReference<List<JsonUser>>() {});
            for (JsonUser x : listUsr) {

                File file = new File("Users/" + x.getUsername() + "/testJson3.json");
                file.getParentFile().mkdirs();
                objectMapper.writeValue(file, listUsr);
                System.out.println(x.getUsername());
                System.out.println(x.getPassword());
                System.out.println();
            }

        } catch (IOException err) {
            err.printStackTrace();
        }
    }*/


    public static boolean userAlraedyExists(String username) {
        /*Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Acest username deja exista!");*/
        //if (username != null)
        {
            for (JsonUser usr : SignUp.obj) {

                    if (usr != null) {
                        if (usr.getUsername().equals(username))
                            return true;
                    }
            }
        }
            return false;

    }

    public static boolean verificareParafa(String cod)
    {
        for(Parafa el : Parafa.listaCoduri)
        {
            if(cod.equals(el.getCod()))
            {
                if(el.getAvaible().equals("1"))
                    return true;
            }

        }
        return false;

    }





}








import java.util.HashMap;

public class BankUsers extends HashMap<String, User>{

    /*This class extends the HashMap class, and we are using it a way so that when we create a BankUsers object
     * it creates a HashMap with Strings as the Key and User objects as the values(Ex. HashMap<String, User>).
     * This is a lot simpler then having nested maps:
     * (Ex. HashMap<String, HashMap<"password", String password, "balance", double balance>>)
     * it provides for quick O(1) lookup of users and then within the user class we can get information about each user
     * in O(1) time as well. 
     */

}

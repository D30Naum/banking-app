public class User {
    
    //Class to create new users
    private String username;
    private String password;
    private Double checkingBalance;
    private Double savingBalance;

    public User(String username, String password, Double checkingBalance, Double savingBalance){
        this.username = username;
        this.password = password;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

//get and set method for User objects. 
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public Double getCheckingBalance(){
        return checkingBalance;
    }
    public Double getSavingBalance(){
        return savingBalance;
    }
    public void setCheckingBalance(Double balance){
        this.checkingBalance = balance;
    }
    public void setSavingBalance(Double balance){
        this.savingBalance = balance;
    }

//This method turns a user object into a string version so that we can store it in a text file. 
    public String toString() {
        return "User{" +
                "username=" + username +
                ", password=" + password + 
                ", checkingBalance=" + checkingBalance +
                ", savingBalance=" + savingBalance +
                '}';
    }

//This method turns a string of a user back into a user object. 
    public static User fromString(String userString) {
        String[] parts = userString.split(", ");
        if (parts.length == 4) {
            String username = parts[0].substring(parts[0].indexOf('=') + 1).replaceAll("'", "").trim();
            String password = parts[1].substring(parts[1].indexOf('=') + 1).replaceAll("'", "").trim();
            Double checkingBalance = Double.parseDouble(parts[2].substring(parts[2].indexOf('=') + 1).trim());
            Double savingBalance = Double.parseDouble(parts[3].substring(parts[3].indexOf('=') + 1, parts[3].length() - 1).trim());

            return new User(username, password, checkingBalance, savingBalance);
        } else {
            // Handle invalid input format
            throw new IllegalArgumentException("Invalid input format for User string representation: " + userString);
        }
    }

}

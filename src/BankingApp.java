import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

/*Final Code: Still some bugs/unhandled cases(See ReadMe file) */

public class BankingApp {

    private CanvasWindow mainCanvas;

    //Greeting
    private GraphicsText welcome;
    private GraphicsText welcome2;
    private GraphicsText welcome3;
    private GraphicsText welcome4;

    //Sign in
    private TextField usernameInput;
    private TextField passwordInput;
    private GraphicsText usernameLabel;
    private GraphicsText passwordLabel;
    private Button signIn;
    
    //new user button
    private Button newUser;

    //OTHER CLASSES
    //Bank users information
    private BankUsers members;

    //New User
    private NewUserUI newUserUI;

    //User 
    private User user;

    //Exit Button
    private Button exitButton;

    private InfoUI userAccount;

    public BankingApp(){
        mainCanvas = new CanvasWindow("Safe Vault Bank", 1200, 1200);
        mainCanvas.setBackground(new Color(255, 255, 240));
        welcomeGreeting(mainCanvas);
        signIn(mainCanvas);
        newUser(mainCanvas);
        
        // Creates the map of all the users. 
        members = new BankUsers();
        members = loadMembers();

        // we dont want to initialize InfoUI or New UserUI here because then they open up right away. 
    }

/*PART 1 of Banking App Class
 * 1. Adding text to the screen
 * 2. Setting the color of that text and the font style
 * 3. Adding the exit button(Which must be used to save the member information)
 */
    public void welcomeGreeting(CanvasWindow canvas){
        welcome = new GraphicsText();
        welcome2 = new GraphicsText();
        welcome3 = new GraphicsText();
        welcome4 = new GraphicsText();
        
        //Set text
        welcome.setText("Welcome to Safe Vault Bank");
        welcome2.setText( "We are here to supply you with easy access to your banking information!");
        welcome3.setText("Have an account? Sign in below");
        welcome4.setText("Click here to create a new account!");

        //Set font and color
        welcome.setFont(FontStyle.BOLD,30);
        welcome2.setFont(FontStyle.BOLD,15);
        welcome3.setFontSize(20);
        welcome4.setFontSize(15);
        welcome.setFillColor(new Color(0, 0, 139));
        welcome2.setFillColor(new Color(0, 0, 139));
        welcome3.setFillColor(new Color(0, 0, 139));
        welcome4.setFillColor(new Color(0, 0, 139));

        welcome.setCenter(600,100);
        welcome2.setCenter(600, 130);
        welcome3.setCenter(600, 200);
        welcome4.setCenter(1050, 20);

        canvas.add(welcome);
        canvas.add(welcome2);
        canvas.add(welcome3);
        canvas.add(welcome4);

        exitButton = new Button("Exit");
        exitButton.setCenter(50,20);
        canvas.add(exitButton);
        exitButton.onClick(()-> {
            saveMembers();
            mainCanvas.closeWindow();
        });
        
    }

/*PART 2 of Banking App Class
 * 1. Adding the sign in elements(Text fields, labels etc.)
 * 2. Handling the event of the sign in button being clicked
 * 3. Then within that it handles the event that the deposit and withdraw buttons are clicked. 
 */
    public void signIn(CanvasWindow canvas){
        usernameInput = new TextField();
        passwordInput = new TextField();
        usernameLabel = new GraphicsText();
        usernameLabel.setText("Username");
        passwordLabel = new GraphicsText();
        passwordLabel.setText("Password");

        //Sizing the label/inputs
        passwordInput.setScale(15);
        usernameLabel.setFontSize(15.0);
        passwordLabel.setFontSize(15.0);

        //Setting the position
        usernameInput.setCenter(600, 250); //0
        passwordInput.setCenter(600, 300);  //+50
        usernameLabel.setCenter(600, 270);  //+20
        passwordLabel.setCenter(600, 320); //+70

        //Changing color
        usernameLabel.setFillColor(new Color(0, 0, 139));
        passwordLabel.setFillColor(new Color(0, 0, 139));

        //Sign in button
        signIn = new Button("Sign In");
        signIn.setCenter(600,360);

        //Adding to canvas
        canvas.add(passwordInput);
        canvas.add(usernameInput);
        canvas.add(passwordLabel);
        canvas.add(usernameLabel);
        canvas.add(signIn);

        signIn.onClick(() -> {
            if(members.containsKey(usernameInput.getText())){
                // Check to see if the inputed password equals users password
                if(passwordInput.getText().equals(members.get(usernameInput.getText()).getPassword())){
                    //Open their account
                    userAccount = new InfoUI();

                    //Display the users information
                    userAccount.setUsername(members.get(usernameInput.getText()).getUsername());
                    userAccount.setCheckingBalance(members.get(usernameInput.getText()).getCheckingBalance());
                    userAccount.setSavingBalance(members.get(usernameInput.getText()).getSavingBalance());
                    //==========================================================

                    //Still need to work on items below(in the future):
                    //NEED TO CHECK TO MAKE SURE THAT INPUTS ARE NUMBERS
                    //Make sure that the amount withdrawn is not more then amount in the account
                    userAccount.depositCheckingButton.onClick(()->{
                        if(!userAccount.depositChecking.getText().isEmpty()){
                            Double currentBalance = members.get(usernameInput.getText()).getCheckingBalance();
                            Double newBalance = currentBalance + Double.parseDouble(userAccount.depositChecking.getText());
                            members.get(usernameInput.getText()).setCheckingBalance(newBalance);
                            userAccount.setCheckingBalance(members.get(usernameInput.getText()).getCheckingBalance());
                        }
                    });
                    userAccount.depositSavingButton.onClick(()->{
                        if(!userAccount.depositSaving.getText().isEmpty()){
                            Double currentBalance = members.get(usernameInput.getText()).getSavingBalance();
                            Double newBalance = currentBalance + Double.parseDouble(userAccount.depositSaving.getText());
                            members.get(usernameInput.getText()).setSavingBalance(newBalance);
                            userAccount.setSavingBalance(members.get(usernameInput.getText()).getSavingBalance());
                        }
                    });
                    userAccount.withdrawCheckingButton.onClick(()->{
                        if(!userAccount.withdrawChecking.getText().isEmpty() && Double.parseDouble(userAccount.withdrawChecking.getText()) <= members.get(usernameInput.getText()).getCheckingBalance()){
                            Double currentBalance = members.get(usernameInput.getText()).getCheckingBalance();
                            Double newBalance = currentBalance - Double.parseDouble(userAccount.withdrawChecking.getText());
                            members.get(usernameInput.getText()).setCheckingBalance(newBalance);
                            userAccount.setCheckingBalance(members.get(usernameInput.getText()).getCheckingBalance());
                        }
                    });
                    userAccount.withdrawSavingButton.onClick(()->{
                        if(!userAccount.withdrawSaving.getText().isEmpty() && Double.parseDouble(userAccount.withdrawSaving.getText()) <= members.get(usernameInput.getText()).getSavingBalance()){
                            Double currentBalance = members.get(usernameInput.getText()).getSavingBalance();
                            Double newBalance = currentBalance - Double.parseDouble(userAccount.withdrawSaving.getText());
                            members.get(usernameInput.getText()).setSavingBalance(newBalance);
                            userAccount.setSavingBalance(members.get(usernameInput.getText()).getSavingBalance());
                        }
                        
                    });
                    //==========================================================
                    userAccount.send.onClick(()->{
                        //TODO: if numbers are not inputed for the amount. 
                        //If we have time: Compress this and the other instance below into one method. 
                        if(members.containsKey(userAccount.receiver.getText())){
                            //Sender
                            Double currentBalanceSender = members.get(usernameInput.getText()).getCheckingBalance();
                            Double newBalanceSender = currentBalanceSender - Double.parseDouble(userAccount.amount.getText());
                            members.get(usernameInput.getText()).setCheckingBalance(newBalanceSender);
                            userAccount.setCheckingBalance(members.get(usernameInput.getText()).getCheckingBalance());
                            //Receiver
                            Double currentBalanceReceiver = members.get(userAccount.receiver.getText()).getCheckingBalance();
                            Double newBalanceReceiver = currentBalanceReceiver + Double.parseDouble(userAccount.amount.getText());
                            members.get(userAccount.receiver.getText()).setCheckingBalance(newBalanceReceiver);
                            userAccount.sendComplete();
                        }
                        if(!members.containsKey(userAccount.receiver.getText())){
                            userAccount.sendFailed();
                        }
                    });

                    userAccount.saveButton.onClick(()-> {
                        saveMembers();
                    });
                }
            }

            passwordIncorrect(canvas);
        });
    }

    //Incorrect password or username method
    public void passwordIncorrect(CanvasWindow canvas){
        GraphicsText message = new GraphicsText("Incorrect Username or Password");
        message.setCenter(600, 400);
        message.setFillColor(Color.red);
        canvas.add(message);
    }

/*PART 3 of Banking App Class
 * 1. Adding the new user interface which allows someone to create a new account. 
 * 2. Handling the event of the "create new account" button being clicked.
 * 3. Then within that it handles the event that the deposit and withdraw buttons are clicked. 
 */
    public void newUser(CanvasWindow canvas) {
        newUser = new Button("New User");
        newUser.setCenter(1050, 50);
        canvas.add(newUser);

        // New user creation command
        newUser.onClick(() -> {
            // Implement NewUserUI
            newUserUI = new NewUserUI();

            // When createNewAcct is clicked in the NewUserUI, add the user to the BankUsers
            newUserUI.createNewAcct.onClick(() -> {

                //TODO: FIX THIS(Check to make sure that the initial balances are numbers.)
                if(members.containsKey(newUserUI.usernameInput.getText())){
                    newUserUI.usernameError();
                }
                if(newUserUI.startingChecking.getText().isEmpty() || newUserUI.startingSaving.getText().isEmpty() || newUserUI.usernameInput.getText().isEmpty() || newUserUI.passwordInput.getText().isEmpty()){
                    newUserUI.emptyInputs();
                }else{
                    // Retrieve user information from the NewUserUI
                    String username = newUserUI.usernameInput.getText();
                    String password = newUserUI.passwordInput.getText();
                    double initialCheckingBalance = Double.parseDouble(newUserUI.startingChecking.getText());
                    double initialSavingsBalance = Double.parseDouble(newUserUI.startingSaving.getText());

                    
                    user = new User(username, password, initialCheckingBalance, initialSavingsBalance);

                    // Add the user to the map of BankUsers
                    members.put(username, user);

                    // Set the text field's to empty
                    newUserUI.usernameInput.setText("");
                    newUserUI.passwordInput.setText("");
                    newUserUI.startingChecking.setText("");
                    newUserUI.startingSaving.setText("");
                    //Open there account
                    userAccount = new InfoUI();

                    //Display the users information
                    userAccount.setUsername(members.get(username).getUsername());
                    userAccount.setCheckingBalance(members.get(username).getCheckingBalance());
                    userAccount.setSavingBalance(members.get(username).getSavingBalance());

                    newUserUI.closeWindow();
                    
                    //==========================================================
                    //In the future: NEED TO CHECK TO MAKE SURE THAT INPUTS ARE NUMBERS
                    userAccount.depositCheckingButton.onClick(()->{
                        if(!userAccount.depositChecking.getText().isEmpty()){
                            Double currentBalance = user.getCheckingBalance();
                            Double newBalance = currentBalance + Double.parseDouble(userAccount.depositChecking.getText());
                            members.get(username).setCheckingBalance(newBalance);
                            userAccount.setCheckingBalance(members.get(username).getCheckingBalance());
                        }
                        
                    });
                    userAccount.depositSavingButton.onClick(()->{
                        if(!userAccount.depositSaving.getText().isEmpty()){
                            Double currentBalance = user.getSavingBalance();
                            Double newBalance = currentBalance + Double.parseDouble(userAccount.depositSaving.getText());
                            members.get(username).setSavingBalance(newBalance);
                            userAccount.setSavingBalance(members.get(username).getSavingBalance());
                        }
                    });
                    userAccount.withdrawCheckingButton.onClick(()->{
                        if(!userAccount.withdrawChecking.getText().isEmpty() && Double.parseDouble(userAccount.withdrawChecking.getText()) <= user.getCheckingBalance()){
                            Double currentBalance = user.getCheckingBalance();
                            Double newBalance = currentBalance - Double.parseDouble(userAccount.withdrawChecking.getText());
                            members.get(username).setCheckingBalance(newBalance);
                            userAccount.setCheckingBalance(members.get(username).getCheckingBalance());
                        }
                    });
                    userAccount.withdrawSavingButton.onClick(()->{
                        if(!userAccount.withdrawSaving.getText().isEmpty() && Double.parseDouble(userAccount.withdrawSaving.getText()) <= user.getSavingBalance()){
                            Double currentBalance = user.getSavingBalance();
                            Double newBalance = currentBalance - Double.parseDouble(userAccount.withdrawSaving.getText());
                            members.get(username).setSavingBalance(newBalance);
                            userAccount.setSavingBalance(members.get(username).getSavingBalance());
                        }
                    });
                    //==========================================================
                    //In the future: Check to make sure that the amount being sent is a number. 
                    userAccount.send.onClick(()->{
                        //Sender
                        if(members.containsKey(userAccount.receiver.getText())){
                            Double currentBalanceSender = user.getCheckingBalance();
                            Double newBalanceSender = currentBalanceSender - Double.parseDouble(userAccount.amount.getText());
                            members.get(username).setCheckingBalance(newBalanceSender);
                            userAccount.setCheckingBalance(members.get(username).getCheckingBalance());
                            //Receiver
                            Double currentBalanceReceiver = members.get(userAccount.receiver.getText()).getCheckingBalance();
                            Double newBalanceReceiver = currentBalanceReceiver + Double.parseDouble(userAccount.amount.getText());
                            members.get(userAccount.receiver.getText()).setCheckingBalance(newBalanceReceiver);
                            userAccount.sendComplete();
                        }if(!members.containsKey(userAccount.receiver.getText())){
                            userAccount.sendFailed();
                        }
                    });
                    userAccount.saveButton.onClick(()-> {
                        saveMembers();
                    });
                }
            });
        });
                //========================================================
    }

    


/*PART 4 of Banking App Class
 * THESE METHODS ARE FOR SAVING AND LOADING THE MEMBERS DATA. DONT TOUCH!
 */
    private void saveMembers() {
        String fileName = "members.txt";

        try (Writer writer = new FileWriter(fileName)) {
            // Iterate over the map
            for (User currentUser : members.values()) {
                // Write user information to the file
                writer.write(currentUser.toString() + "\n");
            }

            System.out.println("Members data saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving members data to file: " + e.getMessage());
        }

    }

    private BankUsers loadMembers(){
        String fileName = "members.txt";

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String userString = scanner.nextLine();
                User user = User.fromString(userString);

                // Add the user to the map
                members.put(user.getUsername(), user);
            }

            System.out.println("Members data loaded from: " + fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Error loading members data from file: " + e.getMessage());
        }
        return members;
    }

    public static void main(String[] args) {
        new BankingApp();

    }
}

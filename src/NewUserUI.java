import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

public class NewUserUI {
    private CanvasWindow canvas;

    private GraphicsText joinMessage;

    //User Information
    public TextField usernameInput; //public
    public TextField passwordInput; //public
    private GraphicsText usernameLabel;
    private GraphicsText passwordLabel;
    public TextField startingSaving; //public
    public TextField startingChecking; //public
    private GraphicsText savingLabel;
    private GraphicsText checkingLabel;
    private GraphicsText instructions;

    public Button createNewAcct;

    private GraphicsText balanceError;

    public NewUserUI(){
        canvas = new CanvasWindow("Create a new account", 1200, 1200);
        canvas.setBackground(new Color(255, 255, 240));
        newUserConstruct(canvas);
    }
    public void newUserConstruct(CanvasWindow canvas){
        //Graphic text's
        joinMessage = new GraphicsText();
        joinMessage.setText("We are glad that you have decided to join SafeVault Banking.");
        joinMessage.setFillColor(new Color(0, 0, 139));
        joinMessage.setCenter(430, 150);
        joinMessage.setFontSize(30);
        canvas.add(joinMessage);

        instructions = new GraphicsText();
        instructions.setText("Please follow the directions below to create a new account.");
        instructions.setFillColor(new Color(0, 0, 139));
        instructions.setCenter(500,200);
        instructions.setFontSize(25);
        canvas.add(instructions);

        //Creating graphics text and text fields for creating an account. 
        usernameInput = new TextField();
        passwordInput = new TextField();
        usernameLabel = new GraphicsText();
        passwordLabel = new GraphicsText();
        startingChecking = new TextField();
        startingSaving = new TextField();
        savingLabel = new GraphicsText();
        checkingLabel = new GraphicsText();

        //Setting text
        usernameLabel.setText("Username");
        passwordLabel.setText("Password");
        savingLabel.setText("Enter your current savings balance");
        checkingLabel.setText("Enter your current checking balance");

        //Sizing the label/inputs
        passwordInput.setScale(15);
        usernameLabel.setFontSize(13.0);
        passwordLabel.setFontSize(13.0);

        //Setting the position of inputs and labels
        usernameInput.setCenter(600, 260); //0
        passwordInput.setCenter(600, 310);  //+50
        usernameLabel.setCenter(600, 280);  //+20
        passwordLabel.setCenter(600, 330); //+70

        startingChecking.setCenter(600, 350); //+100
        startingSaving.setCenter(600, 400); //+150

        checkingLabel.setCenter(600, 370); //+170
        savingLabel.setCenter(600, 420); //+120

        //Changing the color of labels
        usernameLabel.setFillColor(new Color(0, 0, 139));
        passwordLabel.setFillColor(new Color(0, 0, 139));
        savingLabel.setFillColor(new Color(0, 0, 139));
        checkingLabel.setFillColor(new Color(0, 0, 139));


        //Sign in button
        createNewAcct = new Button("Create New Account");
        createNewAcct.setCenter(600,450);

        //Adding to canvas
        canvas.add(passwordInput);
        canvas.add(usernameInput);
        canvas.add(passwordLabel);
        canvas.add(usernameLabel);
        canvas.add(createNewAcct);
        canvas.add(checkingLabel);
        canvas.add(savingLabel);
        canvas.add(startingChecking);
        canvas.add(startingSaving);

        balanceError = new GraphicsText();
    }

//Display an error if Balances are wrong. 
    public void inputError(){
        balanceError.setText("Invalid Balance Input(s)");
        balanceError.setCenter(600, 500);
        balanceError.setFillColor(Color.RED);
        canvas.add(balanceError);
    }

//Display an error if there are empty inputs. 
    public void emptyInputs(){
        balanceError.setText("Empty Inputs");
        balanceError.setCenter(600, 500);
        balanceError.setFillColor(Color.RED);
        canvas.add(balanceError);
    }

//Display an error if the username does not exist. 
    public void usernameError(){
        balanceError.setText("Invalid Username");
        balanceError.setCenter(600, 500);
        balanceError.setFillColor(Color.RED);
        canvas.add(balanceError);
    }
    
    

    public void closeWindow(){
        canvas.closeWindow();
    }
}

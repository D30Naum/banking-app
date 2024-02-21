import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

public class InfoUI {
    private CanvasWindow canvas;
    private GraphicsText usernameLabel;
    private GraphicsText savingLabel;
    private GraphicsText checkingLabel;

    //withdraw information
    public TextField withdrawChecking;
    private GraphicsText withdrawCheckingLabel;
    public Button withdrawCheckingButton;

    //deposit information
    public TextField depositChecking;
    private GraphicsText depositCheckingLabel;
    public Button depositCheckingButton;


    //withdraw information
    public TextField withdrawSaving;
    private GraphicsText withdrawSavingLabel;
    public Button withdrawSavingButton;

    //deposit information
    public TextField depositSaving;
    private GraphicsText depositSavingLabel;
    public Button depositSavingButton;

    //sending information
    private GraphicsText sendLabel;
    public TextField receiver;
    public TextField amount;
    private GraphicsText receiverText;
    private GraphicsText amountText;
    public Button send;

    //button is used to send money to and from user. 
    private GraphicsText sendMoney;

    //this button is used to save user information
    public Button saveButton;


    public InfoUI(){
        canvas = new CanvasWindow("Welcome to your account!", 1200, 1200);
        canvas.setBackground(new Color(255, 255, 240));
        userUI();
        canvas.draw();
    }

    public void userUI(){
        saveButton = new Button("Save");
        saveButton.setCenter(40, 30);
        canvas.add(saveButton);

        usernameLabel = new GraphicsText();
        usernameLabel.setCenter(300, 100);
        usernameLabel.setFillColor(new Color(0, 0, 139));
        usernameLabel.setFontSize(25);
        canvas.add(usernameLabel);

        savingLabel = new GraphicsText();
        savingLabel.setCenter(150, 250);
        savingLabel.setFillColor(new Color(0, 0, 139));
        savingLabel.setFontSize(25);
        canvas.add(savingLabel);

        checkingLabel = new GraphicsText();
        checkingLabel.setCenter(150, 350);
        checkingLabel.setFillColor(new Color(0, 0, 139));
        checkingLabel.setFontSize(25);
        canvas.add(checkingLabel);

/*------------------------- */
        //adding checking withdraw items
        withdrawChecking = new TextField();
        withdrawChecking.setCenter(700, 340);
        canvas.add(withdrawChecking);

        withdrawCheckingLabel = new GraphicsText("Enter the amount you hope to withdraw. " + "\n" + "Then click the withdraw button.");
        withdrawCheckingLabel.setCenter(700, 370);
        withdrawCheckingLabel.setFillColor(new Color(0, 0, 139));
        withdrawCheckingLabel.setFontSize(13);
        canvas.add(withdrawCheckingLabel);

        withdrawCheckingButton = new Button("Withdraw From Checking");
        withdrawCheckingButton.setCenter(700, 400);
        canvas.add(withdrawCheckingButton);

        //adding checking deposit items
        depositChecking = new TextField();
        depositChecking.setCenter(980, 340);
        canvas.add(depositChecking);

        depositCheckingLabel = new GraphicsText("Enter the amount you hope to deposit. " + "\n" + "Then click the deposit button.");
        depositCheckingLabel.setCenter(980, 370);
        depositCheckingLabel.setFillColor(new Color(0, 0, 139));
        depositCheckingLabel.setFontSize(13);
        canvas.add(depositCheckingLabel);

        depositCheckingButton = new Button("Deposit To Checking");
        depositCheckingButton.setCenter(980, 400);
        canvas.add(depositCheckingButton);

 /*------------------------- */
 
        //adding saving withdraw items
        withdrawSaving = new TextField();
        withdrawSaving.setCenter(700, 190);
        canvas.add(withdrawSaving);

        withdrawSavingLabel = new GraphicsText("Enter the amount you hope to withdraw. " + "\n" + "Then click the withdraw button.");
        withdrawSavingLabel.setCenter(700, 220);
        withdrawSavingLabel.setFillColor(new Color(0, 0, 139));
        withdrawSavingLabel.setFontSize(13);
        canvas.add(withdrawSavingLabel);

        withdrawSavingButton = new Button("Withdraw From Savings");
        withdrawSavingButton.setCenter(700, 250);
        canvas.add(withdrawSavingButton);

        //adding deposit items
        depositSaving = new TextField();
        depositSaving.setCenter(980, 190);
        canvas.add(depositSaving);

        depositSavingLabel = new GraphicsText("Enter the amount you hope to deposit. " + "\n" + "Then click the deposit button.");
        depositSavingLabel.setCenter(980, 220);
        depositSavingLabel.setFillColor(new Color(0, 0, 139));
        depositSavingLabel.setFontSize(13);
        canvas.add(depositSavingLabel);

        depositSavingButton = new Button("Deposit To Savings");
        depositSavingButton.setCenter(980, 250);
        canvas.add(depositSavingButton);

        // ==================================
        //Sending section label
        sendLabel = new GraphicsText("Send Money");
        sendLabel.setFont(FontStyle.BOLD, 18);
        sendLabel.setFillColor(new Color(0, 0, 139));
        sendLabel.setCenter(300, 560);
        canvas.add(sendLabel);
        
        //amount text field
        amount = new TextField();
        amount.setCenter(300, 600);
        canvas.add(amount);

        //amount label
        amountText = new GraphicsText("Input the AMOUNT you want to send");
        amountText.setFillColor(new Color(0, 0, 139));
        amountText.setCenter(300, 620);
        canvas.add(amountText);

        //receiver text field
        receiver = new TextField();
        receiver.setCenter(300, 650);
        canvas.add(receiver);

        //receiver label
        receiverText = new GraphicsText("Input the USER you want to send to");
        receiverText.setFillColor(new Color(0, 0, 139));
        receiverText.setCenter(300, 670);
        canvas.add(receiverText);

        //send button
        send = new Button("Send");
        send.setCenter(300, 700);
        canvas.add(send);

        sendMoney = new GraphicsText();
    }

//Display text if the send failed
    public void sendFailed(){
        sendMoney.setText("Transaction failed. Please enter a valid username.");
        sendMoney.setFillColor(Color.red);
        sendMoney.setCenter(300, 730);
        canvas.add(sendMoney);
    }
//Display text if the send is complete
    public void sendComplete(){
        sendMoney.setText("Transaction Complete");
        sendMoney.setFillColor(Color.green);
        sendMoney.setCenter(300, 730);
        canvas.add(sendMoney);
    }
//Set the username graphicText
    public void setUsername(String name){
        usernameLabel.setText("Hello " + name + "! Your banking information is displayed below.");
    }
//Set the checking balance graphicText
    public void setCheckingBalance(Double balance){
        checkingLabel.setText("Checking Balance:  " + balance);
    }
//Set the saving balance graphicText
    public void setSavingBalance(Double balance){
        savingLabel.setText("Saving Balance:  " + balance);
    }
    
}

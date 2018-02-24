/* 
 * Name: Beini Fang
 * Program name: [AddressBook]
 * Program Description: Adderss Book GUI
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class FINALAddressBook extends JFrame implements ActionListener { 

  String[]name = new String[100];
  String[]address = new String[100];
  String[]phone = new String[100];
  String[]email = new String[100];
  
  String[]lastName = new String[100];
  int spaceIndex;
  
  int indexPosition = 0;  //Keeps track of which index the user is currently in
  int nameCount = 0;      //Counts the total number of contacts added
  
  String temp;
  int changed;
  int search = 0;
  int characterCount = 0;
  int duplicate = 0;
  
  JPanel pan0 = new JPanel();
  JPanel pan1 = new JPanel();
  JPanel pan2 = new JPanel();
  JPanel pan3 = new JPanel();

  JButton okButton1 = new JButton("<<"); 
  JButton okButton2 = new JButton(">>"); 
  JButton okButton3 = new JButton("Add"); 
  JButton okButton4 = new JButton("Delete"); 
  JButton okButton5 = new JButton("Update"); 
  JButton okButton6 = new JButton("Clear"); 
  JButton okButton7 = new JButton("Search"); 
  
  JLabel infoLabel1 = new JLabel("Name       ", JLabel.RIGHT);
  JLabel infoLabel2 = new JLabel("Address    ", JLabel.RIGHT); 
  JLabel infoLabel3 = new JLabel("Phone      ", JLabel.RIGHT); 
  JLabel infoLabel4 = new JLabel("Email      ", JLabel.RIGHT); //create a label
  
  JLabel errorLabel = new JLabel("", JLabel.RIGHT);
  
  JTextField nameField1 = new JTextField("");   //Create text fields for the contact information                
  JTextField nameField2 = new JTextField("");
  JTextField nameField3 = new JTextField("");
  JTextField nameField4 = new JTextField("");
  
  JTextField searchField = new JTextField("Search by full name...",25);
  
// **************** CONSTRUCTOR ****************************
  public FINALAddressBook() { 
    setTitle("Address Book");  //Set the window title
    setSize(350, 350);     //Set window size
    
    FlowLayout flow = new FlowLayout(); //Create layouts
    
    GridLayout grid = new GridLayout(2,0);
    GridLayout grid1 = new GridLayout(0,2);
    GridLayout grid2 = new GridLayout(3,2);
    
    BoxLayout box1 = new BoxLayout(pan0,BoxLayout.Y_AXIS);
    BoxLayout box2 = new BoxLayout(pan1,BoxLayout.Y_AXIS);
    BoxLayout box3 = new BoxLayout(pan3, BoxLayout.Y_AXIS);
    setLayout(grid);                    
    
// ********** Action Listeners *************    
    nameField1.addActionListener(this);  //Adds an action listener to every button and text field
    nameField2.addActionListener(this);
    nameField3.addActionListener(this);
    nameField4.addActionListener(this);
    
    okButton1.addActionListener(this);
    okButton2.addActionListener(this);
    okButton3.addActionListener(this);
    okButton4.addActionListener(this);
    okButton5.addActionListener(this);
    okButton6.addActionListener(this);
    okButton7.addActionListener(this);
    
// ***** Panel for entering info and search *****
    pan0.setLayout (grid1);
    
// ******** Enter User Info **********
    pan1.setLayout(box2);
    pan1.add(infoLabel1);  
    pan1.add(nameField1);    
    pan1.add(infoLabel2);
    pan1.add(nameField2);   
    pan1.add(infoLabel3);
    pan1.add(nameField3);
    pan1.add(infoLabel4);
    pan1.add(nameField4);
    
// ************ Buttons ***************
    pan2.setLayout(flow);
    pan2.add(okButton1);
    pan2.add(okButton2);
    pan2.add(okButton3);
    pan2.add(okButton5);
    pan2.add(okButton4);
    pan2.add(okButton6);
    
    errorLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 50));
    pan2.add(errorLabel);
    
// ********** Search Panel ***********
    pan3.setLayout(box3);
    pan3.add(searchField);
    pan3.add(okButton7);
    
// ************* Borders *************  
    pan0.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    pan2.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); 
    pan3.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20)); 
    
    pan0.add(pan1);
    pan0.add(pan3);
    
    add(pan0);
    add(pan2);

    setResizable(false);
    setVisible(true);       // display the GUI on the screen
    
  }
  
//*****************************  Listener  ********************************************  
  public void actionPerformed(ActionEvent event) {
    String buttonCommand = event.getActionCommand();
    
    errorLabel.setText("");
        
    if (indexPosition >= nameCount-1) {
      okButton2.setEnabled(false); //Disable button if the user is at the end of the index
    }
    
    if (buttonCommand.equals ("Add")) {  // Validate user input before adding the contact to the array
      if (indexPosition != nameCount) {
        indexPosition = nameCount;
      }
      duplicate = 0; //Check address book for any duplicate contacts
      for (int count = 0; count <= nameCount; count++) {
        if (nameField1.getText().trim().equals (name[count]) && nameField2.getText().trim().equals (address[count]) && nameField3.getText().trim().equals (phone[count]) && nameField4.getText().trim().equals (email[count])) {  // Check to see if the entry is a duplicate
          duplicate++;
        }
      }
      
      if (nameField1.getText().trim().equals ("")) { //Checks to make sure name field isn't empty
        errorLabel.setText("Please enter a valid name.");
      }
      else if (nameField2.getText().trim().equals ("") && nameField3.getText().trim().equals ("") && nameField4.getText().trim().equals ("")){ //Checks to see if at least one other field is filled in
        errorLabel.setText("Please enter additional contact information.");
      }
      else if (nameField3.getText().trim().length() != 0 && nameField3.getText().trim().length() != 10 && nameField3.getText().trim().length() != 12) { //Checks to see if the phone number is the correct length (10 digits without spaces, 12 digits with spaces)
        errorLabel.setText("Please enter a valid phone number.");
      }
      else if (!nameField4.getText().trim().equals ("") && (nameField4.getText().trim().indexOf("@") == -1 || nameField4.getText().trim().indexOf("@") == 0 || nameField4.getText().trim().indexOf("@") == nameField4.getText().length()-1)) { //Makes sure that there is an '@' symbol, and that it isn't at the beginning or end of the string
        errorLabel.setText("Please enter a valid email address.");
      }
      else if (duplicate > 0){ //Duplicate entries
      errorLabel.setText("Duplicate entry.");
      }
      else { //Store contact info in arrays
      name[indexPosition] = nameField1.getText().trim();
      spaceIndex = name[indexPosition].indexOf(" ");
      lastName[indexPosition] = name[indexPosition].substring (spaceIndex+1, name[indexPosition].trim().length()); //Make a separate string from the space to the end of the string   
      address[indexPosition] = nameField2.getText().trim();
      phone[indexPosition] = nameField3.getText().trim();
      email[indexPosition] = nameField4.getText().trim();
      
      nameField1.setText("");  //Clear text fields
      nameField2.setText("");
      nameField3.setText("");
      nameField4.setText("");
      
      System.out.println();      
      
// ***************************** Sort (by last name) ********************************
      if (nameCount > 0) {     
        for (int j = 0; j < nameCount; j++) {
          changed = 0;
          
          for (int i = 0; i < nameCount-1; i++) {  
            if ((lastName[i+1].compareToIgnoreCase(lastName[i]) < 0)) {   
              temp = lastName[i];                  //Swap the last names
              lastName[i] = lastName[i+1];
              lastName[i+1] = temp;
              
              temp = name[i];                  //Swap the names
              name[i] = name[i+1];
              name[i+1] = temp;
              
              temp = address[i];                  //Swap the addresses
              address[i] = address[i+1];
              address[i+1] = temp;
              
              temp = phone[i];                  //Swap the phone numbers
              phone[i] = phone[i+1];
              phone[i+1] = temp;
              
              temp = email[i];                  //Swap the emails
              email[i] = email[i+1];
              email[i+1] = temp;
              
              changed = 1; // Check to see whether any values were swapped
            }
          }
          
          if (changed == 0) {  //If no values were swapped, break the loop
            break;
          }
        }
        for(int count=0; count <= nameCount; count++) {
          System.out.println(name[count]);
        }
      }
// *******************************************************************
      nameCount++; //Counts the total number of contacts stored
      indexPosition = nameCount; //indexPosition is where the user is currently at in the index
      errorLabel.setText("Contact has been added");
      okButton1.setEnabled(true); //Enable scroll button
      }
    }
    
    else if (buttonCommand.equals ("<<")) {    //Display the contact information stored in the previous index   
      if (indexPosition > 0) {
        okButton1.setEnabled(true);
        okButton2.setEnabled(true);  
        indexPosition--; 
        
        nameField1.setText(name[indexPosition]);  //Display contact information
        nameField2.setText(address[indexPosition]);
        nameField3.setText(phone[indexPosition]);
        nameField4.setText(email[indexPosition]);
        System.out.println ("Index Position: " + indexPosition);
        
      }
      else {
        okButton1.setEnabled(false); //Does not allow the user to scroll any further
        okButton2.setEnabled(true);
        nameField1.setText(name[indexPosition]);
        nameField2.setText(address[indexPosition]);
        nameField3.setText(phone[indexPosition]);
        nameField4.setText(email[indexPosition]);
        errorLabel.setText("You have reached the end of the list");
        System.out.println ("Index Position: " + indexPosition);
      }
    }
    
    else if (buttonCommand.equals (">>")) {       //Display the contact information stored in the next index   
      indexPosition++;
      if (indexPosition < nameCount) {
        okButton1.setEnabled(true);
        okButton2.setEnabled(true);
        nameField1.setText(name[indexPosition]);
        nameField2.setText(address[indexPosition]);
        nameField3.setText(phone[indexPosition]);
        nameField4.setText(email[indexPosition]);
        System.out.println ("Index Position: " + indexPosition);
      }
      else {
        okButton1.setEnabled(true);
        okButton2.setEnabled(false);
        nameField1.setText(name[indexPosition]);
        nameField2.setText(address[indexPosition]);
        nameField3.setText(phone[indexPosition]);
        nameField4.setText(email[indexPosition]);
        errorLabel.setText("You have reached the end of the list");
        System.out.println ("Index Position: " + indexPosition);
      }
    }
    
    if (buttonCommand.equals ("Search")) {                   
      searchField.getText();
      search = 0;
      for (int count = 0; count < nameCount; count++) { //Check to see if the name entered matches any of the contacts stored
        if (searchField.getText().equals (name[count])){
          errorLabel.setText("");
          indexPosition = count;
          nameField1.setText(name[count]);
          nameField2.setText(address[count]);
          nameField3.setText(phone[count]);
          nameField4.setText(email[count]); 
          search++;  //Check to see how many matches were found
        }
        if (search == 1) {                //Display the number of matches that were found
          errorLabel.setText("1 contact was found.");
        }
        else if (search > 1) {
          errorLabel.setText(search + " contacts were found.");
        }
        else if (search == 0) {
          errorLabel.setText("This contact does not exist.");
        }
      }
    }
    
    else if (buttonCommand.equals ("Delete")) { 
      if (nameCount > 0) {
        nameField1.setText("");  //Clear text fields
        nameField2.setText("");
        nameField3.setText("");
        nameField4.setText("");
        
        name[indexPosition] = nameField1.getText();  //Clear the info stored in the index
        address[indexPosition] = nameField2.getText();
        phone[indexPosition] = nameField3.getText();
        email[indexPosition] = nameField4.getText(); 
        
        for (int count = indexPosition; count < nameCount; count++) {  //Move all the indexes up one space
          name[count] = name[count + 1];
          lastName[count] = lastName[count + 1];
          address[count] = address[count + 1];
          phone[count] = phone[count + 1];
          email[count] = email[count + 1];
        }
        if (nameCount != indexPosition) {
          nameCount--;
          indexPosition = nameCount;
          errorLabel.setText("The contact has been deleted.");
        }
        else {
          errorLabel.setText("Nothing to delete.");
        }
      }
      else {
        errorLabel.setText("No contacts to delete.");
      }
    }
    
    else if (buttonCommand.equals ("Update")){ //Updating an existing contact
      if (nameCount == 0) {
        errorLabel.setText("No contacts to update.");
      }
      else if (nameField1.getText().trim().equals ("")) { 
        errorLabel.setText("Please enter a valid name.");
      }
      else if (nameField2.getText().trim().equals ("") && nameField3.getText().trim().equals ("") && nameField4.getText().trim().equals ("")){
        errorLabel.setText("Please enter additional contact information.");
      }
      else if (nameField3.getText().trim().length() != 0 && nameField3.getText().trim().length() != 10 && nameField3.getText().trim().length() != 12) { 
        errorLabel.setText("Please enter a valid phone number.");
      }
      else if (!nameField4.getText().trim().equals ("") && (nameField4.getText().trim().indexOf("@") == -1 || nameField4.getText().trim().indexOf("@") == 0 || nameField4.getText().trim().indexOf("@") == nameField4.getText().length()-1)) { //Makes sure that there is an '@' symbol, and that it isn't at the beginning or end of the string
        errorLabel.setText("Please enter a valid email address.");
      }
      else { //Store contact info in arrays
      name[indexPosition] = nameField1.getText().trim();
      spaceIndex = name[indexPosition].indexOf(" ");
      lastName[indexPosition] = name[indexPosition].substring (spaceIndex+1, name[indexPosition].trim().length()); //Make a separate string from the space to the end of the string   
      address[indexPosition] = nameField2.getText().trim();
      phone[indexPosition] = nameField3.getText().trim();
      email[indexPosition] = nameField4.getText().trim();
      
      nameField1.setText("");  //Clear text fields
      nameField2.setText("");
      nameField3.setText("");
      nameField4.setText("");
      
      System.out.println();        
      
      errorLabel.setText("Your changes have been saved.");
      }
    }
    
    
    else if (buttonCommand.equals ("Clear")) {  //Clear all 4 text fields
      nameField1.setText("");
      nameField2.setText("");
      nameField3.setText("");
      nameField4.setText("");
      errorLabel.setText("Text fields have been cleared");
    }
    
    
    
  }
  
//*****************************  Main Method  *****************************************
  public static void main(String[] args) {    
    FINALAddressBook frame1 = new FINALAddressBook();  
  }
}


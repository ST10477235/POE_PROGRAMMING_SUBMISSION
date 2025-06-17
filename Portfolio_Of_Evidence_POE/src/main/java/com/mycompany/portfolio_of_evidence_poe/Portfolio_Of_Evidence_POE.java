/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//cleaning and building to see if the zip file will work now 
package com.mycompany.portfolio_of_evidence_poe;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author VUNINI
 */
public class Portfolio_Of_Evidence_POE {
//DECLARED LIST AT CLASS LEVEL TO BE ABLE TO ACESS IT GLOBALLY AND MANIPULATE THE LIST    
static List<String> messageSentList = new ArrayList<>();
static List<String> messageDiscardedList = new ArrayList<>();
static List<String> messageIdList = new ArrayList<>();
static List<String> messageHashList = new ArrayList<>();
static List<String> storedMessagesList = new ArrayList<>(); 
static List<String> recipients = new ArrayList<>();
private static List<String> masterMessageList = new ArrayList<>();
private static Map<String, List<String>> recipientMessageMap = new HashMap<>();
//MAIN METHOD EXECUTES MY LOGIN JFRAME(REGISTRATION SYSTEM)ALLOWS NEW USERS TO REGISTER AND ALLOWS ALL USERS TO LOGIN AND ACCESS QUICKCHAT SYSTEM 
    public static void main(String[] args) {
      new Login();//REGISTRATION AND LOGIN SYSTEM 
      //ALLOWS USERS WHO HAVE LOGGED IN TO ACESS QUICKCHAT     
      while (!Login.isAuthenticated) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            }
             if (Login.firstName != null && Login.lastName != null) {
        JOptionPane.showMessageDialog(null, "Welcome, " + Login.firstName + " " + Login.lastName + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
    } else {
        JOptionPane.showMessageDialog(null, "User data not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }
            int choice = 0;
            while (choice!= 3) {
            String choiceStr = JOptionPane.showInputDialog("QuickChat menu::\n1. Send Messages\n2. Show recently sent messages\n3. Quit");
            if (choiceStr == null || choiceStr.trim().isEmpty()){ 
                continue;
            }
            try {
                choice = Integer.parseInt(choiceStr.trim());
                switch (choice) {
                    case 1:
                        //METHOD THAT HANDLES WHAT HAPPENS AFTER USER HAS SELECTED SEND MESSAGES 
                        handleCaseOne();
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "COMING SOON!!!!!.");
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice.Please enter a number between 1 and 3.");
                        break;
                }
            } catch (NumberFormatException e) {
                //THIS METHOD FURTHER VALIDATES THE INPUT 
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }
    }
 
//METHOD IS CALLED INSIDE MAIN METHOD
public static void handleCaseOne() {
String recipient;
    if (recipients.isEmpty()) {
        while (true) {
            recipient = JOptionPane.showInputDialog("No recipients. Add recipient number (+27 or 0 followed by 9 digits):");

            if (recipient == null) {
                return; // Cancelled
            } else if (recipient.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Recipient number cannot be empty. Please enter a valid number.");
                continue;
            }

            if (recipients.contains(recipient)) {
                JOptionPane.showMessageDialog(null, "Recipient already exists. Redirecting to selection.");
                break;
            }

            Message messageCheck = new Message(recipient);
            if (messageCheck.checkRecipientCell() == 1) {
                recipients.add(recipient);//IMMEDIATELY ADDS RECIPIENT TO THE LIST AS USER ADDS CONTACTS 
                recipientMessageMap.putIfAbsent(recipient, new ArrayList<>());//CORRECTLY ADDS ALLL KEYS (RECIPIENTS) TO THE MAP BEFORE ADDING MESSAGES TIED TO IT 
                System.out.println("DEBUG: Added recipient -> " + recipient);
                System.out.println("DEBUG: Updated recipient-message map -> " + recipientMessageMap);
                break;
            }
            JOptionPane.showMessageDialog(null, "Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
        }
    }
    while (true) {
        JList<String> list = new JList<>(new DefaultListModel<>() {{
            recipients.forEach(this::addElement);
        }});
        //USERS ADDS AS MANY CONTACTS AS POSSIBILE UNLESS THEY SELECT A RECIPIENT AND DECIDE TO START CHATTING (CHOOSE RECIPIENT AND HOW MANY MESSAGES THEY WILL BE SENDING TO A PARTICULAR RECIPIENT)
        int option = JOptionPane.showOptionDialog(null, new JScrollPane(list), "Choose recipient",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Start Chatting", "Add Contacts"}, "Start Chatting");

        if (option == 0 && list.getSelectedValue() != null) {
            recipient = list.getSelectedValue(); // capture selection
            JOptionPane.showMessageDialog(null, "Starting a conversation with " + recipient);
            break; 
        } else if (option == 1) {
            while (true) {
                //ENABLING USER TO ENTER AS MANY CONTACT AS THEY WANT IN THE RECIPIENT LIST FORE MORE FLEXIBILY
                recipient = JOptionPane.showInputDialog("Enter another recipient number (+27 or 0 followed by 9 digits):");

                if (recipient == null || recipient.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Recipient number cannot be empty");
                    continue;
                }
                //AVOIDING TO OVERLOAD THE RECIPIENT SO ONLY NEW CONTACTS GETS ADDED TO THE LIST AND INFORMS IF CONTACTS EXIST HENCE I INTRODUCED THE MAP SINCE THE RECIPIENT CAN NOT BE RETRIVE USING INDEXES WHICH LEAD TO INDEX OUT OF BOUNDS ERROR
                if (recipients.contains(recipient)) {
                    JOptionPane.showMessageDialog(null, "Recipient already exists. Redirecting to selection.");
                    break;
                }

                Message newMessageCheck = new Message(recipient);
                if (newMessageCheck.checkRecipientCell() == 1) {
                    recipients.add(recipient);
                    recipientMessageMap.putIfAbsent(recipient, new ArrayList<>()); 
                    System.out.println("DEBUG: Added another recipient -> " + recipient);
                    System.out.println("DEBUG: Updated recipient-message map -> " + recipientMessageMap);
                    break;
                }
                JOptionPane.showMessageDialog(null, "Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
            }
        }
    }
                        Message.resetTotalMessages();//RESETS TO COUNT TOTAL MESSAGES THAT WILL BE DISPLAYED IN PRINTFULLDETAILS
                        int totalMessagesCount = 0;
                        int msgCount = 0;//MSGCOUNT IS WHAT USERS ENTERS "ABCD"
                        do{//IF USER ENTERS 4 THEN COUNTINPUTMSG IS 4 AND THE LOOP WILL ITERATE 4 TIMES AS PER USER REQUEST
                        String countInputMsg = JOptionPane.showInputDialog(null, "How many messages would you like to send?"); 
                        if (countInputMsg != null && countInputMsg.matches("\\d+")) 
                           {
                                msgCount = Integer.parseInt(countInputMsg);
                                       if (msgCount <= 0)//ENSURES THAT USER DOESNT CONTINUE UNNESSESSARILY IF THEY ENTER THEY WANT TO SEND -2 MESSAGES AT THE END IT WILL BE 0 
                                    {
                                        JOptionPane.showMessageDialog(null, "Please enter a number greater than zero.");
                                    }
                            } 
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Please enter a valid positive number.");//ALSO ACCOUNT FOR STRING INPUTS WHEN ASKED HOW MANY MESSAGES THEY WANT TO SEND 
                            }
                            } while (msgCount <= 0);
                                for (int countMessages = 0; countMessages < msgCount; countMessages++) 
                            {
                                String messageText = JOptionPane.showInputDialog(null, "Enter message text for message " + (countMessages+1) + ":");//INCREMENATES MESSAGE NUMBERS "ENTER MESSAGE 1" WHEN USER INPUTS A MESSAGE
                                while (messageText == null || messageText.trim().isEmpty()) 
                                {
                                   JOptionPane.showMessageDialog(null, "Message cannot be empty. Please enter a valid message.");
                                    messageText = JOptionPane.showInputDialog(null, "Enter message text for message " + (countMessages+1) + ":");
                                }
                                //ADDS ALL MESSAGES AS USER ENTER A MESSAGE TO MAINTAIN THE SEQUENCE
                                masterMessageList.add(messageText);
                                System.out.println("DEBUG: Updated Master Message List -> " + masterMessageList);
                                recipientMessageMap.putIfAbsent(recipient, new ArrayList<>());
                                //TRACKS AND ADDS ALL MESSAGES TIED TO THE RECIPIENT REGARLESS OF THE STATUS
                                recipientMessageMap.get(recipient).add(messageText);
                                
                                System.out.println("DEBUG: Added message for recipient -> " + recipient);
                                System.out.println("DEBUG: Updated recipient-message map -> " + recipientMessageMap);

                                Message message = new Message(recipient);
                                String result =  message.sendMessage();

                                while (result == null)
                                {
                                 result = message.sendMessage();
                                }
 
                switch (result) {
   
case "Message sent successfully":
    //MIMICKING THE WHATAPP STYLE OF WHEN MESSAGES ARE SENT > RECIEVED > READ
     JOptionPane.showMessageDialog(null, messageText + " ✔");
     JOptionPane.showMessageDialog(null, messageText + " ✔✔");
     JOptionPane.showMessageDialog(null, messageText + " ✔✔ Read");
     messageSentList.add(messageText);//ONLY TRACKS ALL MESSAGES SUCCESFULLY SENT AND ADDS THEM TO THIS LIST 
     Message.incrementTotalMessages();//ONLY TRACKS HOW MANY MESSAGES WHERE SENT BASED ON USERS INITIAL SELECTION MEANING IF THEY WANT 3 MESSAGES BUT SENT1 > STORED 1 > DISREGARDED 1 THEN TOTAL MESSSAGES SENT IS 1 NOT 3
     break;


case "Message stored sucessfully to JSON file":
    try {
        if (result.equals("Message stored sucessfully to JSON file")) { 
            JOptionPane.showMessageDialog(null, result); 
            message.storeMessageToFile(messageText);//STORES MESSAGE TEXT ON A JSON FILE 
            storedMessagesList.add(messageText);//ONLY TRACKS ALL MESSAGES SUCCESFULLY STORED IN JSON FILE AND ADDS THEM TO THIS LIST
            message.readStoredMessagesFromFile();//ENABLES ME TO READ MESSAGES STORED IN JSON FILE ON THE CONSOLE  
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    break;

       default: 
                     if (result.equals("Message discarded")) { 
        JOptionPane.showMessageDialog(null, result);
        messageDiscardedList.add(messageText);//ONLY TRACKS MESSAGES SUCCESFULLY DISREGARDED ON THE LIST AND ADD THEM ON THIS LIST
    }
    break;
                }//WHEN THE USER HAS SELECTED WHAT TO DO WITH THE MESSAGE 
                    message.setMessageText(messageText);
                    Message msg = new Message(recipient);
                    messageIdList.add(message.getMessageId());
                 
                int count = countMessages + 1; 
                message.createMessageHash(count); 
                message.setCreatedMessageHash(message.getCreatedMessageHash()); 

                System.out.println("DEBUG: Retrieved Hash Before Adding to List: " + message.getCreatedMessageHash());
                messageHashList.add(message.getCreatedMessageHash());
                System.out.println("DEBUG: Current Hash List After Addition: " + messageHashList);
                JOptionPane.showMessageDialog(null, message.printFullDetails(countMessages));//COUNTS ONLY SENT MESSAGE
                            } //THE PROGRAM STOPS COUNTS THE TOTAL NUMBER OF MESSAGE HERE
                    
                    JOptionPane.showMessageDialog(null, "You have sent all the messages");
                    JOptionPane.showMessageDialog(null, "Total number of messages sent: " + Message.returnTotalMessages());//RESETS TOTAL NUMBER TO AVOID MISCALCULATIONS
                   
                    List<String> sentMessages = Message.printSentMessages();//TO PRINT ONLY SENT MESSAGES TO THE LIST
                    JList<String> listComponent = new JList<>(messageSentList.toArray(new String[0]));//WHEN THE LIST IS LONG THE SCROOL IS ACTIVATED
                    JOptionPane.showMessageDialog(null, new JScrollPane(listComponent), "A list of messages you sent", JOptionPane.PLAIN_MESSAGE);
                    //CONVERT MY DISCARDEDMESSAGESARRAY INTO A JLIST TO VISUALLY TRACK IF MESSAGES IN THIS LIST FALL UNDER ITS APPRIPRIATE CATEGORY WHICH IS DISCARDED MESSAGES 
                    String[] discardedMessagesArray = messageDiscardedList.toArray(new String[0]);
                    JList<String> discardedListComponent = new JList<>(discardedMessagesArray);
                    JOptionPane.showMessageDialog(null, new JScrollPane(discardedListComponent), "Discarded Messages", JOptionPane.PLAIN_MESSAGE);
                    //CONVERT  MY MESSAGEIDARRAY INTO A JLIST TO VISUALLY TRACK IF MESSAGE IDS IN THIS LIST FALL UNDER ITS APPRIPRIATE CATEGORY WHICHT IS MESSAGE IDS
                    String[] messageIdArray = messageIdList.toArray(new String[0]);
                    JList<String> messageIdComponent = new JList<>(messageIdArray);
                    JOptionPane.showMessageDialog(null, new JScrollPane(messageIdComponent), "All Stored Message IDs", JOptionPane.PLAIN_MESSAGE);
                    //CONVERT MY MESSAGEHASHARRAY INTO A JLIST TO VISUALLY TRACK IF MESSAGE HASHES IN THIS LIST FALL UNDER ITS APPRIPRIATE CATEGORY WHICH IS MESSAGE HASHES
                    String[] messageHashArray = messageHashList.toArray(new String[0]); 
                    JList<String> messageHashComponent = new JList<>(messageHashArray);
                    JOptionPane.showMessageDialog(null, new JScrollPane(messageHashComponent), "All Stored Message Hashes", JOptionPane.PLAIN_MESSAGE);
                    //PRINT ALL LIST TO SEE THE BEHAVIOUR OF EACH LIST 
                    System.out.println("DEBUG: Message Sent List ->"+messageSentList);
                    System.out.println("DEBUG: Message Stored List -> " +storedMessagesList);
                    System.out.println("DEBUG: Message Discarded List -> "+messageDiscardedList);
                    System.out.println("DEBUG: Message ID List -> " + messageIdList);
                    System.out.println("DEBUG: Recipients -> " + recipients);
    //DISPLAY THE SENDER AND RECIPIENT OF ALL SENT MESSAGES 
    Message.displayMessages(Login.cellPhoneNumber, recipientMessageMap, messageSentList, storedMessagesList, messageDiscardedList);
    //DISPAY THE LONGEST SENT MESSAGE
    Message.displayLongestSentMessage(messageSentList, storedMessagesList, messageDiscardedList);
    //SEARCH FOR A MESSAGE ID AND DISPLAY THE CORRESPONDING RECIPIENT AND MESSAGE
   boolean searchAgain = true;

while (searchAgain) {
    String searchId = JOptionPane.showInputDialog("Enter Message ID to search:");

    if (searchId == null || searchId.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Search cancelled.", "Exit", JOptionPane.INFORMATION_MESSAGE);
        break;
    }

    searchId = searchId.trim();
    //TO TRACK IF THE MESSAGE ID ENTERED IS FOUND IN THE INDEX 
    System.out.println("DEBUG: User entered ID -> " + searchId);
    //UPDATES THE MASTER LIST TO TRACK ITS BEHAVIOUR BEFORE AND AFTER 
    Message.updateMasterList(messageIdList, storedMessagesList, messageSentList, messageDiscardedList);
    System.out.println("DEBUG: Master Message List Updated Before Search -> " + masterMessageList);
    //LOCATE MESSAGE ID INDEX PROPERLY SINCE THEY ARE STORED IN ORDER 
    int foundIndex = messageIdList.indexOf(searchId);
    System.out.println("DEBUG: Found Index in messageIdList -> " + foundIndex);
    //DISPLAYS MESSAGE NOT FOUND IF IT LOOPS THROUGHOUT THE INDEXES
    if (foundIndex == -1) {
        System.out.println("DEBUG: Message ID not found!");
        JOptionPane.showMessageDialog(null, "Message ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        continue;
    }

    //USED MASTER LIST TO RETRIVE MESSAGE INDEXES SINCE THEY ARE STORED IN ORDER 
    String messageText = (foundIndex < masterMessageList.size())  
                         ? masterMessageList.get(foundIndex)  
                         : "Unknown Message";

    System.out.println("DEBUG: Retrieved Message -> " + messageText);

    System.out.println("DEBUG: Passed Recipient -> " + recipient);

   
    Message.searchMessageById(searchId, recipient, messageIdList, masterMessageList);

    int continueSearch = JOptionPane.showConfirmDialog(null, 
        "Would you like to enter another Message ID?", 
        "Continue Searching?", 
        JOptionPane.YES_NO_OPTION);

    searchAgain = (continueSearch == JOptionPane.YES_OPTION); 
}
//SEARCH FOR ALL THE MESSAGES SENT TO A PARTICULAR RECIPIENT
boolean searchMessagesAgain = true;

while (searchMessagesAgain) {
    System.out.println("DEBUG: Current recipient-message map -> " + recipientMessageMap);
    String searchedRecipient = JOptionPane.showInputDialog("Enter a recipient to search messages:");

    if (searchedRecipient != null && !searchedRecipient.trim().isEmpty()) {
        System.out.println("DEBUG: Searching for recipient -> " + searchedRecipient);
        Message.searchMessagesForRecipient(recipientMessageMap, searchedRecipient);
    }

    int choice = JOptionPane.showConfirmDialog(null, "Do you want to search again?", "Search Again", JOptionPane.YES_NO_OPTION);
    searchMessagesAgain = (choice == JOptionPane.YES_OPTION);
}
 
//DELETE A MESSAGE USING THE MESSAGE HASH
boolean deleteAgain = true;

while (deleteAgain) {
    String messageHash = JOptionPane.showInputDialog("Enter Message Hash to delete:");

    if (messageHash == null || messageHash.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Deletion cancelled.", "Exit", JOptionPane.INFORMATION_MESSAGE);
        break;
    }

    messageHash = messageHash.trim(); 
    System.out.println("DEBUG: User entered Hash -> " + messageHash);

    // FIND MESSAGE THAT CORRESPONDS WITH MESSAGE HASH
    int index = messageHashList.indexOf(messageHash);
    if (index == -1) {
        JOptionPane.showMessageDialog(null, "Message Hash not found.", "Error", JOptionPane.ERROR_MESSAGE);
        continue;
    }

    String messageToDelete = masterMessageList.get(index);

    //DELETE ALL CORRECPONDING ELEMENTS TO AVOID MISMATCH OF INDEXES
    Message.deleteMessageByHash(messageHash, messageIdList, messageSentList, storedMessagesList, 
                                messageDiscardedList, messageHashList, masterMessageList);

    //ALSO REMOVE IN LIST WHEN NECESSARY TO KEEP EVERY LIST UP TO DATE 
    messageSentList.remove(messageToDelete);
    storedMessagesList.remove(messageToDelete);
    messageDiscardedList.remove(messageToDelete);
    masterMessageList.remove(messageToDelete);

    System.out.println("DEBUG: Completed deletion across all lists.");

    int continueDeletion = JOptionPane.showConfirmDialog(null, 
        "Would you like to delete another message?", 
        "Continue Deleting?", 
        JOptionPane.YES_NO_OPTION);

    deleteAgain = (continueDeletion == JOptionPane.YES_OPTION);
}
    //DISPLAYS A REPORT THAT THE LIST LISTS THE FULL DETAILS OF ALL SENT MESSAGES 
    Message.displayFullDetails(recipient, messageIdList, messageHashList, masterMessageList, recipientMessageMap);
}
}


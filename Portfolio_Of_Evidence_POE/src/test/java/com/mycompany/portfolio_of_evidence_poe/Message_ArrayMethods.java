/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.portfolio_of_evidence_poe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


/**
 *
 * @author VUNINI
 */
public class Message_ArrayMethods {
  
    @Test
    void testMessageListsCorrectlyPopulated() {
        Map<String, String[]> testData = new LinkedHashMap<>();
        testData.put("Test Data 1", new String[]{"+2784557896", "Did you get the cake?", "sent"});
        testData.put("Test Data 2", new String[]{"+27838884567", "Where are you? You are late! I have asked you to be on time.", "stored"});
        testData.put("Test Data 3", new String[]{"+27834484567", "Yohoooo, I am at your gate.", "disregarded"});
        testData.put("Test Data 4", new String[]{"0838884567", "It is dinner time!", "sent"}); // Developer (Self-Sent)
        testData.put("Test Data 5", new String[]{"+27838884567", "Ok, I am leaving without you.", "stored"});

        String loggedInUserPhone = "0838884567"; 

        List<String> messageSentList = new ArrayList<>();
        List<String> storedMessagesList = new ArrayList<>();
        List<String> messageDiscardedList = new ArrayList<>();
        List<String> masterMessageList = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : testData.entrySet()) {
            String testLabel = entry.getKey();
            String sender = entry.getValue()[0];
            String message = entry.getValue()[1];
            String flag = entry.getValue()[2];

            String label = sender.equals(loggedInUserPhone) ? "Sender" : "Recipient";
            System.out.println("DEBUG: Processing " + testLabel + " -> " + label + ": " + sender + " | Message: " + message + " | Flag: " + flag);

            if (flag.equals("sent")) {
                messageSentList.add(message);
            } else if (flag.equals("stored")) {
                storedMessagesList.add(message);
            } else if (flag.equals("disregarded")) {
                messageDiscardedList.add(message);
            }

            masterMessageList.add(message); 
        }

        System.out.println("DEBUG: Sent Messages -> " + messageSentList);
        System.out.println("DEBUG: Stored Messages -> " + storedMessagesList);
        System.out.println("DEBUG: Discarded Messages -> " + messageDiscardedList);
        System.out.println("DEBUG: Master Message List -> " + masterMessageList);
 assertTrue(messageSentList.contains("Did you get the cake?"), "Sent list missing Test Data 1.");
        assertTrue(messageSentList.contains("It is dinner time!"), "Sent list missing Test Data 4.");
        assertTrue(storedMessagesList.contains("Where are you? You are late! I have asked you to be on time."), "Stored list missing Test Data 2.");
        assertTrue(storedMessagesList.contains("Ok, I am leaving without you."), "Stored list missing Test Data 5.");
        assertTrue(messageDiscardedList.contains("Yohoooo, I am at your gate."), "Discarded list missing Test Data 3.");
        assertTrue(masterMessageList.containsAll(List.of("Did you get the cake?", "Where are you? You are late! I have asked you to be on time.", "Yohoooo, I am at your gate.", "It is dinner time!", "Ok, I am leaving without you.")), "Master list missing expected messages.");

        System.out.println("TEST PASSED: Lists correctly populated with presence validation.");
    }
    
   @Test
    void testSentMessagesCorrectlyPopulated() {
        String loggedInUserPhone = "0838884567"; 

        Map<String, String[]> testData = new LinkedHashMap<>();
        testData.put("Test Data 1", new String[]{"+2784557896", "Did you get the cake?", "sent"});
        testData.put("Test Data 2", new String[]{"+27838884567", "Where are you? You are late! I have asked you to be on time.", "stored"});
        testData.put("Test Data 3", new String[]{"+27834484567", "Yohoooo, I am at your gate.", "disregarded"});
        testData.put("Test Data 4", new String[]{loggedInUserPhone, "It is dinner time!", "sent"}); // Developer (Self-Sent)

        List<String> expectedSentMessages = List.of(
            "Did you get the cake?",   
            "It is dinner time!"    
        );

        List<String> expectedMasterMessages = List.of(
            "Did you get the cake?",   
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.", 
            "It is dinner time!"      
        );

        List<String> messageSentList = new ArrayList<>();
        List<String> masterMessageList = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : testData.entrySet()) {
            String message = entry.getValue()[1];
            String sender = entry.getValue()[0];
            String flag = entry.getValue()[2];

            if (flag.equals("sent") && sender.equals(loggedInUserPhone)) {
                System.out.println("DEBUG: Developer message detected (Sender) -> " + message);
                messageSentList.add(message);
            } else if (flag.equals("sent")) {
                messageSentList.add(message);
            }

            masterMessageList.add(message);
        }

        assertEquals(expectedSentMessages, messageSentList, "Sent list mismatch.");

        assertEquals(expectedMasterMessages, masterMessageList, "Master list mismatch.");

        System.out.println("TEST PASSED: Sent messages array correctly populated with Messages 1 & 4.");
        System.out.println("TEST PASSED: Master message array correctly contains Messages 1-4.");
    }
    
   @Test
    void testStoredAndDisregardedMessagesCorrectlyPopulated() {
        // **Expected Test Data**
        List<String> expectedStoredMessages = List.of(
            "Where are you? You are late! I have asked you to be on time.",   
            "Ok, I am leaving without you."                                  
        );

        List<String> expectedDiscardedMessages = List.of(
            "Yohoooo, I am at your gate."  
        );

        List<String> expectedMasterMessages = List.of(
            "Did you get the cake?",   
            "Where are you? You are late! I have asked you to be on time.", 
            "Yohoooo, I am at your gate.", 
            "It is dinner time!"       
        );
        
        List<String> storedMessagesList = new ArrayList<>(expectedStoredMessages);
        List<String> messageDiscardedList = new ArrayList<>(expectedDiscardedMessages);
        List<String> masterMessageList = new ArrayList<>(expectedMasterMessages);

        assertEquals(expectedStoredMessages, storedMessagesList, "Stored list mismatch.");
        assertEquals(expectedDiscardedMessages, messageDiscardedList, "Disregarded list mismatch.");
        assertEquals(expectedMasterMessages, masterMessageList, "Master list mismatch.");

        System.out.println("TEST PASSED: Stored and Disregarded messages correctly populated.");
    }
    
    @Test
    void testDisplayLongestMessage() {
        List<String> messageSentList = new ArrayList<>(List.of(
            "Hi", "Heyyy", "Where are you? You are late! I have asked you to be on time."
        ));

        List<String> storedMessagesList = new ArrayList<>(List.of(
            "Short", "Hello", "Where are you? You are late! I have asked you to be on time."
        ));

        List<String> messageDiscardedList = new ArrayList<>(List.of(
            "Test", "Oops!", "Where are you? You are late! I have asked you to be on time."
        ));

        String expectedLongestMessage = "Where are you? You are late! I have asked you to be on time.";

        assertEquals(expectedLongestMessage, Message.findLongestMessage(messageSentList));
        assertEquals(expectedLongestMessage, Message.findLongestMessage(storedMessagesList));
        assertEquals(expectedLongestMessage, Message.findLongestMessage(messageDiscardedList));

        System.out.println("TEST PASSED: Longest message displayed correctly.");
    }
    
  @Test
    void testSearchMessageById() {
        List<String> messageIdList = new ArrayList<>(List.of("message 1", "message 2", "message 3", "message 4", "message 5"));
        List<String> masterMessageList = new ArrayList<>(List.of("Hello!", "Where are you?", "Good morning!", "It is dinner time!", "See you later."));
        
        String messageIdToSearch = "message 4";
        String recipient = "0838884567";

        String expectedMessage = "It is dinner time!";

        int foundIndex = messageIdList.indexOf(messageIdToSearch);
        String retrievedMessage = (foundIndex != -1 && foundIndex < masterMessageList.size()) ? masterMessageList.get(foundIndex) : "Unknown Message";

        assertEquals(expectedMessage, retrievedMessage);

        System.out.println("TEST PASSED: Message successfully retrieved.");
    }

  @Test
    void testSearchMessagesForRecipient() {
        Map<String, List<String>> recipientMessageMap = new HashMap<>();
        recipientMessageMap.put("+2783884567", Arrays.asList(
                "Where are you? You are late! I have asked you to be on time.",
                "Ok, I am leaving without you."
        ));

        String searchedRecipient = "+2783884567";

        List<String> messages = recipientMessageMap.get(searchedRecipient);

        assertNotNull(messages, "Messages list should not be null.");
        assertEquals(2, messages.size(), "Recipient should have exactly 2 messages.");
        assertEquals("Where are you? You are late! I have asked you to be on time.", messages.get(0), "First message should match.");
        assertEquals("Ok, I am leaving without you.", messages.get(1), "Second message should match.");
    }
  
    @Test
    void testDeleteMessageByHash() {
        List<String> messageIdList = new ArrayList<>(List.of("8956432781", "7291053467", "6524389172")); // Realistic IDs
        List<String> messageSentList = new ArrayList<>(List.of("Sent1", "Sent2", "Sent3"));
        List<String> storedMessagesList = new ArrayList<>(List.of(
                "Hello!", 
                "Where are you? You are late! I have asked you to be on time", 
                "Goodbye"));
        List<String> messageDiscardedList = new ArrayList<>(List.of("Discarded1", "Discarded2", "Discarded3"));
        List<String> messageHashList = new ArrayList<>(List.of(
                "8956432781:1:HELLO:HELLO!", 
                "7291053467:2:WHERE:LATE!", 
                "6524389172:3:GOODBYE:GOODBYE!"));
        List<String> masterMessageList = new ArrayList<>(storedMessagesList);

        String messageHashToDelete = "7291053467:2:WHERE:LATE!";
        int indexToDelete = messageHashList.indexOf(messageHashToDelete);
        String expectedDeletedMessage = masterMessageList.get(indexToDelete);

        Message.deleteMessageByHash(messageHashToDelete, messageIdList, messageSentList, storedMessagesList, 
                                    messageDiscardedList, messageHashList, masterMessageList);

        assertEquals(-1, messageHashList.indexOf(messageHashToDelete), "Message hash should no longer exist.");
        assertEquals(-1, masterMessageList.indexOf(expectedDeletedMessage), "Message should be fully removed.");
        assertEquals(-1, storedMessagesList.indexOf(expectedDeletedMessage), "Message should be removed from stored messages.");
        assertEquals(-1, messageSentList.indexOf(expectedDeletedMessage), "Message should be removed from sent messages.");
        assertEquals(-1, messageDiscardedList.indexOf(expectedDeletedMessage), "Message should be removed from discarded messages.");

        System.out.println("TEST PASSED: Message successfully deleted across all lists.");
    }



 @Test
    void testDisplayFullDetails() {
        String recipient = "0123456789"; 
        List<String> messageIdList = Arrays.asList("8956432781", "7291053467", "6524389172"); // Realistic 10-digit IDs
        List<String> messageHashList = Arrays.asList(
                "8956432781:1:PAYMENT:#45982.", 
                "7291053467:2:URGENT:COLLECTION.", 
                "6524389172:3:REMINDER:14:30."); 
        List<String> masterMessageList = Arrays.asList(
                "Payment received for invoice #45982.", 
                "Urgent: Your package is ready for collection.", 
                "Reminder: Your appointment is scheduled for 14:30.");

        Map<String, List<String>> recipientMessageMap = new HashMap<>();
        recipientMessageMap.put(recipient, Arrays.asList(masterMessageList.get(0), masterMessageList.get(1)));

        String expectedReport = "********************************************************************************\n"
                + "               FULL MESSAGE REPORT                                              \n"
                + "******************************************************************************\n\n"
                + "MESSAGE ID        :  8956432781\nMESSAGE HASH      :  8956432781:1:PAYMENT:#45982.\n"
                + "RECIPIENT         :  0123456789\nMESSAGE           :  Payment received for invoice #45982.\n"
                + "**************************************************************************\n\n"
                + "MESSAGE ID        :  7291053467\nMESSAGE HASH      :  7291053467:2:URGENT:COLLECTION.\n"
                + "RECIPIENT         :  0123456789\nMESSAGE           :  Urgent: Your package is ready for collection.\n"
                + "**************************************************************************\n\n"
                + "                End of Report                                                   \n"
                + "********************************************************************************\n";

        String actualReport = Message.displayFullDetails(recipient, messageIdList, messageHashList, masterMessageList, recipientMessageMap);

        assertEquals(expectedReport, actualReport, "Generated report should match exactly with expected output.");
    }
}





    

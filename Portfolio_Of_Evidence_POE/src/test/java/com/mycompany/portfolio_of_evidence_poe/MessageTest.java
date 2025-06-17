/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.portfolio_of_evidence_poe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author VUNINI
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
     
    @Test 
    public void TaskSendMessages() {
        //TEST TASK 1 (FIRST MESSAGE SENT)
        int numMessages1 = 2;
        String recipient1 = "+27718693002";
        String message1 = "Hi mike, can you join us for dinner tonight";
        ArrayList<String> messageIds1 = new ArrayList<>();
        Random random = new Random();

        System.out.println("=== Sending Message 1 ===");
        for (int i = 0; i < numMessages1; i++) {
            // GENERATIN A RANDOM MESSAGE ID FOR TASK 1
            StringBuilder messageId = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                messageId.append(random.nextInt(10));
            }
            messageIds1.add(messageId.toString());

            //
            System.out.println("Sending message to " + recipient1);
            System.out.println("Message ID: " + messageId);
            System.out.println("Message Number: " + message1);
            System.out.println("Send messages: Sent\n");
        }
        System.out.println("Total number of messages sent (message 1): " + numMessages1);
        System.out.println("All message IDs (message 1): " + messageIds1);

        // TEST DATE MESSAGE 2
        int numMessages2 = 1;
        String recipient2 = "08575975889";
        String message2 = "Hi keegan did you recieve the payment?";
        ArrayList<String> messageIds2 = new ArrayList<>();

        System.out.println("\n=== Sending Message 2 ===");
        for (int i = 0; i < numMessages2; i++) {
            StringBuilder messageId = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                messageId.append(random.nextInt(10));
            }
            messageIds2.add(messageId.toString());

            //CHECKING PATTERN
            System.out.println("Sending message to " + recipient2);
            System.out.println("Message ID: " + messageId);
            System.out.println("Message Number: " + message2);
            System.out.println("Send messages: Sent\n");
        }
        System.out.println("Total number of messages sent (message 2): " + numMessages2);
        System.out.println("All message IDs (message 2): " + messageIds2);

        // Grand total
        int totalMessages = numMessages1 + numMessages2;
        System.out.println("\nTotal number of messages sent (overall): " + totalMessages);
    }
    
      /**
     * Test of validateMessageLength method, of class Message.
     */
    @Test
    public void testValidateMessageLength() 
    {
        System.out.println("Testing validateMessageLength...");
        Message message = new Message(""); 
        //Valid message
        String validText = "A".repeat(250);
        String expectedValid = "Message sent successfully";
        String actualValid = message.validateMessageLength(validText);
        assertEquals(expectedValid, actualValid, "Expected successful validation for 250 characters.");
        //Invalid message
        String longText = "A".repeat(251);
        String expectedInvalid = "Please enter message of less than 250 characters";
        String actualInvalid = message.validateMessageLength(longText);
        assertEquals(expectedInvalid, actualInvalid, "Expected validation failure for 251 characters.");
    }
    
         /**
     * Test of checkReciepientCell method, of class Message.
     */
    @org.junit.jupiter.api.Test
    public void testCheckRecipientCell() 
    {
        System.out.println("checkReciepientCell");
        //valid 
        Message validMessage = new Message("0987654321");
        validMessage.setMessageText("hey");
        assertEquals(1,validMessage.checkRecipientCell());
        assertEquals(10,validMessage.getMessageId().length());
        System.out.println("Entered Recipient: " + validMessage.getRecipient());
        System.out.println("Cell phone successfully captured");
        System.out.println("Message ID generated: " + validMessage.getMessageId());
        //invalid
        Message invalidMessage = new Message("1234567890");
        invalidMessage.setMessageText("hey");
        assertEquals(0, invalidMessage.checkRecipientCell());
        System.out.println("Entered Recipient: " + invalidMessage.getRecipient());
        System.out.println("Cell phone number incorrectly formatted or does not contain an international code. Please correct the number and try again.");

        Message nullMessage = new Message(null);
        nullMessage.setMessageText("hey");
        assertEquals(0, nullMessage.checkRecipientCell());
        System.out.println("Entered Recipient: null");
        System.out.println("Recipient number cannot be empty");
    }
    
     /**
     * Test of createMessageHash method, of class Message.
     */
    @org.junit.jupiter.api.Test
    public void testCreateMessageHash() 
    {
        System.out.println("createMessageHash");
        Message message = new Message("0987654321");
        // Message ID Uniqueness
        Message message1 = new Message("0987654321");
        Message message2 = new Message("0987654321");
        assertNotEquals(message1.generateMessageId(), message2.generateMessageId());

        // Multi-word message
        message.setMessageText("Prog is due on Monday");
        message.setMessageId("1234567890");
        String expectedHashMulti = message.getMessageId().substring(0, 2) + ":2:PROGMONDAY";
        assertEquals(expectedHashMulti, message.createMessageHash(2));
        assertEquals(expectedHashMulti, message.getCreatedMessageHash());

        // Single-word message
        message.setMessageText("Assignment");
        message.setMessageId("1234567890");
        String expectedHashSingle = message.getMessageId().substring(0, 2) + ":3:ASSIGNMENTASSIGNMENT";
        assertEquals(expectedHashSingle, message.createMessageHash(3));
        assertEquals(expectedHashSingle, message.getCreatedMessageHash());

        // Valid multi-word message
        message.setMessageText("Try GitHub");
        message.setMessageId("1234567890");
        String expectedHashValidMulti = message.getMessageId().substring(0, 2) + ":4:TRYGITHUB";
        assertEquals(expectedHashValidMulti, message.createMessageHash(4));
        assertEquals(expectedHashValidMulti, message.getCreatedMessageHash());
    }


    /**
     * Test of generateMessageId method, of class Message.
     */
     @org.junit.jupiter.api.Test
     public void testGenerateMessageId ()
    {
       System.out.println("generateMessageId");
       Message message = new Message("0647492574");
       message.setMessageText("hey");
       String expectedOutput = "Message ID generated:"+message.getMessageId();
       assertTrue(expectedOutput.matches("Message ID generated:\\d{10}"));
     
    }
    
    /**
     * Test of sendMessage method, of class Message.
     */
    @org.junit.jupiter.api.Test
    public void testSendMessage() 
    {
         System.out.println("sendMessage");
         Message message = new Message("+27987654321");
         message.setMessageText("PROG5121!");
         String[] responseMessages = {
            "Message sent successfully",
            "Message discarded",
            "Message stored sucessfully to JSON file"
        };

        String result;
        //Loop as user selects "X"
        while (true) 
        { 
            result = message.sendMessage(); 
            if (result != null) 
            {
                break; 
            }

        }
        assertTrue(Arrays.asList(responseMessages).contains(result), "User should have selected a valid message option");
        int optionIndex = Arrays.asList(responseMessages).indexOf(result);
        assertTrue(optionIndex >= 0 && optionIndex < responseMessages.length, "Option index should correspond to a valid selection");
    }

}

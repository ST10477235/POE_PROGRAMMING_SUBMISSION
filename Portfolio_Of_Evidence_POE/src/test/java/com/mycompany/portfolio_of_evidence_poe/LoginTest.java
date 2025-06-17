/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.portfolio_of_evidence_poe;

import java.util.Arrays;
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
public class LoginTest {
    
    public LoginTest() {
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

    /**
     * Test of checkUsername method, of class Login.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
    // Test valid username
    String validUsername = "kyl_1";
    Login instance = new Login();
    boolean expectedValidResult = true; // Since "kyl_1" matches the current validation rules
    boolean validResult = instance.checkUsername(validUsername);
    assertEquals(expectedValidResult, validResult);
    
    // Test invalid username
    String invalidUsername = "kyle!!!";
    boolean expectedInvalidResult = false; // Special characters should be rejected
    boolean invalidResult = instance.checkUsername(invalidUsername);
    assertEquals(expectedInvalidResult, invalidResult);
}

    /**
     * Test of checkPassword method, of class Login.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        Login instance = new Login();

    // Test case: Valid password
    String validPassword = "Ch&&sec@ke99!";
    boolean expectedValidResult = true;  // Meets regex criteria
    boolean validResult = instance.checkPassword(validPassword);
    assertEquals(expectedValidResult, validResult);

    // Test case: Invalid password (too simple)
    String invalidPassword = "password";
    boolean expectedInvalidResult = false; // Lacks special chars, uppercase, and numbers
    boolean invalidResult = instance.checkPassword(invalidPassword);
    assertEquals(expectedInvalidResult, invalidResult);
}

      /**
     * Test of getCellPhoneNumber method, of class Login.
     */
    @Test
    public void testGetCellPhoneNumber() {
    System.out.println("getCellPhoneNumber");
    Login instance = new Login();

    String validCellNumber = "+27838968976";
    instance.setCellPhoneNumber(validCellNumber);
    assertEquals(validCellNumber, instance.getCellPhoneNumber()); 
    assertEquals(true, instance.checkCellphoneNumber(validCellNumber)); 

   
    String invalidCellNumber = "123456"; 
    instance.setCellPhoneNumber(invalidCellNumber);
    assertEquals(invalidCellNumber, instance.getCellPhoneNumber()); 
    assertEquals(false, instance.checkCellphoneNumber(invalidCellNumber)); 
}

 /**
     * Test of ReturnLogStatus method, of class Login.
     */
    @Test
public void testLoginStatus() {
    System.out.println("LoginStatus");

    // Load users from file before testing
    Login.loadUsersFromFile();

    // Test case: Successful login
    String logUsernameSuccess = "nu_";
    String logPasswordSuccess = "@#Vun3n3";
    String[] userInfoSuccess = Login.users.get(logUsernameSuccess);

    if (userInfoSuccess != null && userInfoSuccess.length > 1) {
        System.out.println("User found: " + Arrays.toString(userInfoSuccess));
        System.out.println("Entered password: " + logPasswordSuccess);
    } else {
        System.out.println("User not found in map! Login attempt failed.");
    }

    boolean validLoginSuccess = Login.LoggingUser(logUsernameSuccess, logPasswordSuccess, Login.users);
    assertEquals("ACCESS GRANTED", new Login().ReturnLogStatus(validLoginSuccess));

    // Test case: Failed login
    String logUsernameFail = "m_u";
    String logPasswordFail = "wrongPassword";
    String[] userInfoFail = Login.users.get(logUsernameFail);

    if (userInfoFail != null && userInfoFail.length > 1) {
        System.out.println("User found: " + Arrays.toString(userInfoFail));
        System.out.println("Entered password: " + logPasswordFail);
    } else {
        System.out.println("User not found in map! Login attempt failed.");
    }

    boolean validLoginFail = Login.LoggingUser(logUsernameFail, logPasswordFail, Login.users);
    assertEquals("ACCESS DENIED", new Login().ReturnLogStatus(validLoginFail));

    // Additional validation using assertTrue/assertFalse
    assertTrue(new Login().ReturnLogStatus(validLoginSuccess).equals("ACCESS GRANTED"));
    assertFalse(new Login().ReturnLogStatus(validLoginFail).equals("ACCESS GRANTED"));
}

@Test
public void testCheckUsernameFormat() {
    System.out.println("UsernameFormatValidation");

    // Valid usernames (contain "_" and are <= 5 characters)
    assertTrue(new Login().checkUsername("nu_"));
    assertTrue(new Login().checkUsername("_abc")); 
    assertTrue(new Login().checkUsername("_1234")); 

    // Invalid usernames (no underscore or too long)
    assertFalse(new Login().checkUsername("nu")); 
    assertFalse(new Login().checkUsername("long_user")); 
    assertFalse(new Login().checkUsername("12345")); 

    // Debugging output
    System.out.println("Valid case '_abc': " + new Login().checkUsername("_abc"));
    System.out.println("Too long case 'long_user': " + new Login().checkUsername("long_user"));
}

@Test
public void testCheckPasswordFormat() {
    System.out.println("PasswordFormatValidation");

    // Valid password cases (contain at least one number, one uppercase letter, one special character, and are ≥ 8 characters)
    assertTrue(new Login().checkPassword("ValidPass1@")); 
    assertTrue(new Login().checkPassword("StrongP@ss9")); 

    // Invalid password cases
    assertFalse(new Login().checkPassword("weakpass")); 
    assertFalse(new Login().checkPassword("WeakPass")); 
    assertFalse(new Login().checkPassword("WeakPass9")); 
    assertFalse(new Login().checkPassword("Short1@")); 

    // Debugging output
    System.out.println("Valid password: " + new Login().checkPassword("ValidPass1@"));
    System.out.println("Weak password (missing special char & number): " + new Login().checkPassword("weakpass"));
    System.out.println("No number: " + new Login().checkPassword("WeakPass"));
    System.out.println("No special character: " + new Login().checkPassword("WeakPass9"));
    System.out.println("Too short: " + new Login().checkPassword("Short1@"));
}

@Test
public void testCheckCellphoneNumber() {
    System.out.println("CellphoneNumberValidation");

    // Valid cellphone numbers (South African format)
    assertTrue(new Login().checkCellphoneNumber("+27123456789")); 
    assertTrue(new Login().checkCellphoneNumber("0123456789")); 

    // Invalid cellphone numbers (wrong format)
    assertFalse(new Login().checkCellphoneNumber("+28123456789")); 
    assertFalse(new Login().checkCellphoneNumber("123456789")); 
    assertFalse(new Login().checkCellphoneNumber("+2712345678")); 
    assertFalse(new Login().checkCellphoneNumber("012345678"));
    assertFalse(new Login().checkCellphoneNumber("+27ABCDEF789")); 

    // Debugging output
    System.out.println("Valid number (+27123456789): " + new Login().checkCellphoneNumber("+27123456789"));
    System.out.println("Valid number (0123456789): " + new Login().checkCellphoneNumber("0123456789"));
    System.out.println("Wrong country code (+28123456789): " + new Login().checkCellphoneNumber("+28123456789"));
    System.out.println("Missing leading '0' or '+27' (123456789): " + new Login().checkCellphoneNumber("123456789"));
    System.out.println("Too few digits (+2712345678): " + new Login().checkCellphoneNumber("+2712345678"));
}    
}

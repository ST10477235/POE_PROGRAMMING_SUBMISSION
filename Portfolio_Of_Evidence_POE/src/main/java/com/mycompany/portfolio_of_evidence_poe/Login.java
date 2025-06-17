/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.portfolio_of_evidence_poe;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author VUNINI
 */
public class Login extends javax.swing.JFrame{

    private boolean validUsername = false;
    private boolean validPassword = false;
    private boolean validCellphone = false;
    private boolean validFirstName = false;
    private boolean validLastName = false;
    public static boolean isAuthenticated = false;
    public static Map<String, String[]> users = new HashMap<>();
    private String username;
    private String password;
    public static String firstName;
    public static String lastName;
    public static String cellPhoneNumber;

    private Login(String username, String password, String firstName, String lastName, String cellPhoneNumber) {  
    this.username = username;  
    this.password = password;  
    this.firstName = firstName;  
    this.lastName = lastName;  
    this.cellPhoneNumber = cellPhoneNumber;  
}

    
    
    
    private void attachDocumentListeners() {
        
        firstNameField.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e) { firstNameFieldActionPerformed(null); }
    public void removeUpdate(DocumentEvent e) { firstNameFieldActionPerformed(null); }
    public void changedUpdate(DocumentEvent e) { firstNameFieldActionPerformed(null); }
});

usernameField.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e) { usernameFieldActionPerformed(null); }
    public void removeUpdate(DocumentEvent e) { usernameFieldActionPerformed(null); }
    public void changedUpdate(DocumentEvent e) { usernameFieldActionPerformed(null); }
});
passwordField.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e) { passwordFieldActionPerformed(null); }
    public void removeUpdate(DocumentEvent e) { passwordFieldActionPerformed(null); }
    public void changedUpdate(DocumentEvent e) { passwordFieldActionPerformed(null); }
});
cellPhoneNumberField.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e) { cellPhoneNumberFieldActionPerformed(null); }
    public void removeUpdate(DocumentEvent e) { cellPhoneNumberFieldActionPerformed(null); }
    public void changedUpdate(DocumentEvent e) { cellPhoneNumberFieldActionPerformed(null); }
});
lastNameField.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e) { lastNameFieldActionPerformed(null); }
    public void removeUpdate(DocumentEvent e) { lastNameFieldActionPerformed(null); }
    public void changedUpdate(DocumentEvent e) { lastNameFieldActionPerformed(null); }
});

    }
    
  
    
    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
     
     boolean checkPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@!$#^%&()+_{}\\-=\\[\\]|:;'\",.<>/?]).{8,}$");
    }
    
    boolean checkCellphoneNumber(String cellnumber) {
    return cellnumber.matches("^\\+27\\d{9}$") || cellnumber.matches("^0\\d{9}$");
}
    public String ReturnRegisteredUser(boolean validUsername, boolean validPassword, boolean validFirstName, boolean validLastName, boolean validCellphone) {
    return (!validUsername || !validPassword) 
        ? "REGISTRATION FAILED."
        : (!validFirstName || !validLastName || !validCellphone) 
        ? "CORRECTLY FIIL IN ALL REQUIRED FIELDS"
        : "REGISTRATION SUCCESSFULL";
}
public static boolean LoggingUser(String logUsername, String logPassword, Map<String, String[]> users) {  
    System.out.println("Checking user: " + logUsername);
    System.out.println("All stored usernames: " + users.keySet()); // Print all keys for debugging

    if (!users.containsKey(logUsername)) {
        System.out.println("User not found in map!");
        return false;
    }

    String[] userInfo = users.get(logUsername);

    System.out.println("Retrieved user info: " + Arrays.toString(userInfo));


    if (userInfo[1].equals(logPassword)) {
        firstName = userInfo[2]; 
        lastName = userInfo[3]; 
        cellPhoneNumber = userInfo[4];
        return true;
    }

    return false;
}
 

    public String ReturnLogStatus(boolean isLoggedIn) {
        return isLoggedIn ? "ACCESS GRANTED" : "ACCESS DENIED";
    }
    
    public Login() {
        initComponents();
        loadUsersFromFile();
        setTitle("REGISTRATION SYSTEM");
        usernameError.setVisible(false);
        passwordError.setVisible(false);
        phoneError.setVisible(false);
        lastNameError.setVisible(false);
        firstNameError.setVisible(false);
        attachDocumentListeners();
        setVisible(true);
    }
    
    public static void loadUsersFromFile() {
    try (Scanner scanner = new Scanner(new File("users.txt"))) {
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            if (data.length == 5) { 
                System.out.println("Loading user: " + Arrays.toString(data));
                users.put(data[0], new String[]{data[0], data[1], data[2], data[3], data[4]});
            }
        }
         for (Map.Entry<String, String[]> entry : users.entrySet()) {
    String[] userData = entry.getValue(); 
    System.out.println("Loaded user: " + entry.getKey() + " -> " + userData[2] + " " + userData[3]);
}

    } catch (FileNotFoundException e) {
        System.out.println("No previous users found, starting fresh.");
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        cellPhoneNumberLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        lastNameField = new javax.swing.JTextField();
        cellPhoneNumberField = new javax.swing.JTextField();
        usernameField = new javax.swing.JTextField();
        firstNameField = new javax.swing.JTextField();
        usernameError = new javax.swing.JLabel();
        passwordError = new javax.swing.JLabel();
        phoneError = new javax.swing.JLabel();
        lastNameError = new javax.swing.JLabel();
        firstNameError = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        exitButton.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        exitButton.setText("EXIT");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        registerButton.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        registerButton.setText("REGISTER");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 36)); // NOI18N
        jLabel1.setText("R e g i s t r a t i o n   s y s t e  m");

        firstNameLabel.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        firstNameLabel.setText("First name :");

        lastNameLabel.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        lastNameLabel.setText("Last name :");

        cellPhoneNumberLabel.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        cellPhoneNumberLabel.setText("Cell phone number :");

        usernameLabel.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        usernameLabel.setText("Username :");

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Algerian", 0, 12)); // NOI18N
        passwordLabel.setText("Password :");

        lastNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameFieldActionPerformed(evt);
            }
        });

        cellPhoneNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cellPhoneNumberFieldActionPerformed(evt);
            }
        });

        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });

        usernameError.setText("\" \"");

        passwordError.setText("\" \"");

        phoneError.setText("\" \"");

        lastNameError.setText("\" \"");

        firstNameError.setText("\" \"");

        jCheckBox1.setFont(new java.awt.Font("Viner Hand ITC", 0, 12)); // NOI18N
        jCheckBox1.setText("show password");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exitButton)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cellPhoneNumberLabel)
                                    .addComponent(usernameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(usernameError))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(passwordError))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox1)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(registerButton)))
                                        .addGap(74, 74, 74)
                                        .addComponent(loginButton))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cellPhoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lastNameError)
                                            .addComponent(firstNameError)
                                            .addComponent(phoneError)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(firstNameLabel)
                                .addComponent(lastNameLabel))
                            .addComponent(passwordLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameError))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameError))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cellPhoneNumberLabel)
                    .addComponent(cellPhoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneError))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameError, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordError))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(passwordLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exitButton)
                            .addComponent(registerButton)
                            .addComponent(loginButton))
                        .addGap(33, 33, 33))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        dispose();
        new Log_in();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed

        usernameFieldActionPerformed(null);

        passwordFieldActionPerformed(null);
        
        cellPhoneNumberFieldActionPerformed(null);

        firstNameFieldActionPerformed(null);
    
        lastNameFieldActionPerformed(null);
        


System.out.println("First Name entered: " + firstNameField.getText());
System.out.println("Last Name entered: " + lastNameField.getText());
System.out.println("Cellphone entered: " + cellPhoneNumberField.getText());
System.out.println("Username entered: " + usernameField.getText());      
System.out.println("Password entered: " + passwordField.getText());

      
 String registrationMessage = ReturnRegisteredUser(validUsername, validPassword, validFirstName, validLastName, validCellphone);  

   
    JOptionPane.showMessageDialog(null, registrationMessage);  
this.dispose();

    Login newUser = new Login();  
    newUser.setUsername(usernameField.getText());  
    newUser.setPassword(passwordField.getText());  
    newUser.setFirstName(firstNameField.getText());  
    newUser.setLastName(lastNameField.getText());  
    newUser.setCellPhoneNumber(cellPhoneNumberField.getText());  
Login.users.put(usernameField.getText(), new String[]{
    usernameField.getText(), passwordField.getText(), 
    firstNameField.getText(), lastNameField.getText(), 
    cellPhoneNumberField.getText()
});
  //System.out.println("User stored in HashMap: " + usernameField.getText() + " -> " + Login.users.get(usernameField.getText()));
    try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt", true))) {  
        writer.println(usernameField.getText() + "," + passwordField.getText() + "," +  
                       firstNameField.getText() + "," + lastNameField.getText() + "," +  
                       cellPhoneNumberField.getText());  
    } catch (Exception e) {  
        JOptionPane.showMessageDialog(null, "Error saving user data!", "Error", JOptionPane.ERROR_MESSAGE);  
    } 



    }//GEN-LAST:event_registerButtonActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
String username = usernameField.getText();
    if (username.isEmpty()) {
        usernameError.setText("Username cannot be empty.");
        usernameError.setForeground(Color.RED);
        validUsername = false;
    }else if (users.containsKey(username)) {
        usernameError.setText("Username already exists!");
        usernameError.setForeground(Color.RED);
    } 
    else if (checkUsername(username)) { // Uses your method directly
        usernameError.setText("✔ Username successfully captured.");
        usernameError.setForeground(Color.GREEN);
        validUsername = true;
    } else {
        usernameError.setText("✖ Username must contain '_' and be at most 5 characters.");
        usernameError.setForeground(Color.RED);
        validUsername = false;
    }

    usernameError.setVisible(true);

    }//GEN-LAST:event_usernameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
String password = passwordField.getText();

    if (password.isEmpty()) {
        passwordError.setText("Password cannot be empty.");
        passwordError.setForeground(Color.RED);
        validPassword = false;
    } else if (password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@!$#^%&()+_{}\\-=\\[\\]|:;'\",.<>/?]).{8,}$")) {
        passwordError.setText("✔ P@ssw0rd successfully captured.");
        passwordError.setForeground(Color.GREEN);
        validPassword = true;
    } else {
        passwordError.setText("✖ Password does not meet complexity requirements.\n"
                + "* Contains at least 8 characters.\n"
                + "* Includes at least 1 uppercase letter.\n"
                + "* Includes at least 1 number.\n"
                + "* Includes at least 1 special character.");
        passwordError.setForeground(Color.RED);
        validPassword = false;
    }

    passwordError.setVisible(true);


    }//GEN-LAST:event_passwordFieldActionPerformed

    private void cellPhoneNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cellPhoneNumberFieldActionPerformed
  String cellnumber = cellPhoneNumberField.getText();

    if (cellnumber.isEmpty()) {
        phoneError.setText("Cellphone number cannot be empty.");
        phoneError.setForeground(Color.RED);
        validCellphone = false;
    } else if (cellnumber.matches("^\\+27\\d{9}$")) {
        phoneError.setText("✔ Cellphone number successfully added.");
        phoneError.setForeground(Color.GREEN);
        validCellphone = true;
    } else if (cellnumber.matches("^0\\d{9}$")) {
        phoneError.setText("✔ Local cellphone number successfully added.");
        phoneError.setForeground(Color.GREEN);
        validCellphone = true;
    } else {
        phoneError.setText("✖ Invalid cellphone number format.\n"
                + "* Must start with +27 or 0.\n"
                + "* Must be followed by exactly 9 digits.");
        phoneError.setForeground(Color.RED);
        validCellphone = false;
    }

    phoneError.setVisible(true);

    }//GEN-LAST:event_cellPhoneNumberFieldActionPerformed

    private void firstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameFieldActionPerformed
       String firstName = firstNameField.getText();

    if (firstName.isEmpty()) {
        firstNameError.setText("First name cannot be empty.");
        firstNameError.setForeground(Color.RED);
        validFirstName = false;
    } else if (!firstName.matches("[a-zA-Z]+")) {
        firstNameError.setText("Only letters allowed.");
        firstNameError.setForeground(Color.RED);
        validFirstName = false;
    } else {
        firstNameError.setText("✔ First name successfully captured.");
        firstNameError.setForeground(Color.GREEN);
         validFirstName = true;
    }

    firstNameError.setVisible(true);


    }//GEN-LAST:event_firstNameFieldActionPerformed

    private void lastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameFieldActionPerformed
      String lastName = lastNameField.getText();

    if (lastName.isEmpty()) {
        lastNameError.setText("Last name cannot be empty.");
        lastNameError.setForeground(Color.RED);
         validLastName = false;
    } else if (!lastName.matches("[a-zA-Z]+")) {
        lastNameError.setText("Only letters allowed.");
        lastNameError.setForeground(Color.RED);
         validLastName = false;
    } else {
        lastNameError.setText("✔ Last name successfully captured.");
        lastNameError.setForeground(Color.GREEN);
         validLastName = true;
    }

    lastNameError.setVisible(true);

 
    }//GEN-LAST:event_lastNameFieldActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
              if (jCheckBox1.isSelected()) {
        passwordField.setEchoChar((char) 0); 
    } else {
        passwordField.setEchoChar('•'); 
    }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cellPhoneNumberField;
    private javax.swing.JLabel cellPhoneNumberLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel firstNameError;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lastNameError;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passwordError;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel phoneError;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel usernameError;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables

    private void setUsername(String username) {
    this.username = username;
    }


    private void setPassword(String  password) {
     this.password = password;
 }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public void setCellPhoneNumber(String cellPhoneNumber) {
    this.cellPhoneNumber = cellPhoneNumber;
}

public String getUsername() {
    return firstName;
}

public String getPassword() {
    return password;
}

public String getFirstName() {
    return firstName;
}

public String getLastName() {
    return lastName;
}

public String getCellPhoneNumber() {
    return cellPhoneNumber;
}
  

 
}

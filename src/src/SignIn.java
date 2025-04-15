
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SignIn extends javax.swing.JFrame {

    private static String usname, pass;

    private static final String FILE_PATH = "src/Database.json";
    private static final JSONParser jsonParser = new JSONParser();
    private static JSONObject record = new JSONObject();
    private static JSONArray userlist = new JSONArray();

    public SignIn() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        Username = new javax.swing.JTextField();
        SignIn = new javax.swing.JButton();
        SignUpButton = new javax.swing.JButton();
        Password = new javax.swing.JPasswordField();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel3.setText("Sign In");

        Username.setText("Username");
        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });

        SignIn.setText("Sign In");
        SignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignInActionPerformed(evt);
            }
        });

        SignUpButton.setText("Sign Up");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });

        Password.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Username, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(Password))
                        .addGap(99, 99, 99)
                        .addComponent(SignUpButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(SignIn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SignUpButton)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(SignIn)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameActionPerformed

    private void SignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignInActionPerformed
        String username = Username.getText().trim();
        String password = Password.getText().trim();
        //These lines of code retrieve user input from text fields and remove any spaces, declaring and initializing to store the input.

        if (username.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an existing Username and Password.", "Input needed", JOptionPane.ERROR_MESSAGE);
            return;
            // These lines of code prompts and checks if both username and password fields are empty.
        }

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an existing Username.", "Input needed", JOptionPane.ERROR_MESSAGE);
            return;
            // These lines of code prompts and checks if the username field is empty, asking the user to enter an existing username.
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Password.", "Input needed", JOptionPane.ERROR_MESSAGE);
            return;
            // These lines of code prompts and checks if the password field is empty, asking the user to enter an existing username's password.
        }

        try {
            usname = Username.getText();
            pass = Password.getText();
            // These lines of code retrieves the entered username and password from the text fields.

            filecheck(); //Calls the filecheck method to read the data in the json file.

            boolean userFound = false; //This checks if there is an existing user data that matches in the json file.
            String foundType = null; //This variable is used to store the type found in the json file(Admin or Member).

            for (Object obj : userlist) {
                JSONObject userobject = (JSONObject) obj;
                String foundusname = (String) userobject.get("username");
                String foundpass = (String) userobject.get("password");
                foundType = (String) userobject.get("type");
                //These lines of code gets the stored data in the json file.

                if (usname.equals(foundusname) && pass.equals(foundpass)) {
                    userFound = true;
                    break;
                    // These lines of code compares input username and password with the stored data and sets to true if it matches.
                }
            }

            if (!userFound) {
                JOptionPane.showMessageDialog(null, "No user found!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                //If nothing was found, it will prompt and error message.
            } else {
                if (foundType != null && foundType.equalsIgnoreCase("Game Master")) {
                    GameMaster x = new GameMaster();
                    x.setVisible(true);
                    setVisible(false);
                    //If the user has an Admin type, it will open the Admin JFrame then it will close the Login JFrame.
                    
                } else if (foundType != null && foundType.equalsIgnoreCase("Player")) {
                    Player p = new Player();
                    p.setVisible(true);
                    setVisible(false);
                    //If the user has a Member type, it will open the Member JFrame then it will close the Login JFrame.
                }
            }

        } catch (HeadlessException | IOException | ParseException e) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "An error occurred while logging in.", "Error!", JOptionPane.ERROR_MESSAGE);
            // These lines of code catches any errors that happen during the login process and shows an error message.
        }
    }//GEN-LAST:event_SignInActionPerformed

    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
        SignUp a = new SignUp();
        a.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_SignUpButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new SignIn().setVisible(true);
        });
    }

    public void filecheck() throws FileNotFoundException, IOException, ParseException {
        FileReader reader = new FileReader(FILE_PATH);

        if (reader.ready()) {
            Scanner scan = new Scanner(reader);
            String line = "";

            while (scan.hasNext()) {
                line = line + scan.nextLine();
            }
            reader.close();
            if (!line.equals("")) {
                reader.close();
                try (FileReader reader2 = new FileReader(FILE_PATH)) {
                    record = (JSONObject) jsonParser.parse(reader2);
                    userlist = (JSONArray) record.get("users");
                } catch (IOException a) {
                    System.out.println("error");
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Password;
    private javax.swing.JButton SignIn;
    private javax.swing.JButton SignUpButton;
    private javax.swing.JTextField Username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

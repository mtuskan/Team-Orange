import javax.swing.*;

/**
 @Author Ian Liston
 @Version 1.0
 Date  : 02/16/2023
**/

public class GUI {
    private JPanel mainPanel;

    private JButton startProfileButton;
    private JButton startAboutButton;
    private JPanel startPanel;

    private JPanel profilePanel;
    private JTextField profileNameTextField;
    private JTextPane profileAlertTextPane;

    private JPanel aboutPanel;
    private JTextPane aboutTextPane;
    private JButton aboutProfileButton;
    private JPasswordField passwordPasswordField;

    private JPanel newOrUserPanel;
    private JButton newUserButton;
    private JButton existingUserButton;
    private JCheckBox profileCheckBox;

    // Main panel GUI method.
    public JPanel getMainPanel() {return mainPanel;}

    // Start page panel GUI methods.
    public JPanel getStartPanel() {return startPanel;}
    public JButton getStartAboutButton() {return startAboutButton;}
    public JButton getStartProfileButton() {return startProfileButton;}

    // newOrUserPanel GUI methods
    public JPanel getNewOrUserPanel() {return newOrUserPanel;}
    public JButton getExistingUserButton() {return existingUserButton;}
    public JButton getNewUserButton() {return newUserButton;}

    // Profile page panel GUI methods.
    public JPanel getProfilePanel() {return profilePanel;}
    public JPasswordField getPasswordPasswordField() {return passwordPasswordField;}
    public JTextField getProfileNameTextField() {return profileNameTextField;}
    public JTextPane getProfileAlertTextPane() {return profileAlertTextPane;}
    public JCheckBox getProfileCheckBox() {return profileCheckBox;}

    // About page panel GUI methods
    public JPanel getAboutPanel() {return aboutPanel;}
    public JTextPane getAboutTextPane() {return aboutTextPane;}
    public JButton getAboutProfileButton() {return aboutProfileButton;}
}
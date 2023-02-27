import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MyApp Class
 * @Author Michael Tuskan and Ian Liston
 * @Version 2.0
 */

public class MyApp extends JFrame {
    private GUI gui;
    private About about;
    private User user;
    private boolean newUser;

    // Constructor
    public MyApp() {
        gui = new GUI();
        about = new About();
    }

    /**
     * Author Michael Tuskan and Ian Liston
     */
    public void runApplication() {

        // Main Homepage JFrame settings
        setContentPane(gui.getMainPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(500, 200);
        setSize(500, 500);
        setTitle("Iteration 1");

        // About Button Action Listener
        gui.getStartAboutButton().addActionListener(new aboutButtonListener());
        gui.getStartProfileButton().addActionListener(new profileButtonListener());
        gui.getAboutProfileButton().addActionListener(new aboutProfileButtonListener());
        gui.getPasswordPasswordField().addActionListener(new profilePasswordEnter());
        gui.getNewUserButton().addActionListener(new newOrUserNewUserListener());
        gui.getExistingUserButton().addActionListener(new newOrUserExistingButtonListener());
        gui.getProfileCheckBox().addActionListener(new profileCheckBoxListener());
    }

    /**
     * Author Ian Liston
     */
    class profileCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(gui.getProfileCheckBox().isSelected())
                gui.getPasswordPasswordField().setEchoChar((char)0);
            else
                gui.getPasswordPasswordField().setEchoChar('*');

        }
    }

    /**
     * Author Ian Liston
     */
    class profilePasswordEnter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            user = new User(gui.getProfileNameTextField().getText(),gui.getPasswordPasswordField(),newUser);
        }
    }

    /**
     * Author Ian Liston
     */
    class newOrUserExistingButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.getNewOrUserPanel().setVisible(false);
            gui.getProfilePanel().setVisible(true);
        }
    }

    /**
     * Author Ian Liston
     */
    class newOrUserNewUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.getNewOrUserPanel().setVisible(false);
            newUser = true;
            gui.getProfilePanel().setVisible(true);
        }
    }

    /**
     * Author Michael Tuskan and Ian Liston
     */
    class profileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.getStartPanel().setVisible(false);
            gui.getNewOrUserPanel().setVisible(true);
        }
    }

        /**
         * Author Michael Tuskan and Ian Liston
         */
    class aboutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.getStartPanel().setVisible(false);
            gui.getAboutTextPane().setText(about.displayString());
            gui.getAboutPanel().setVisible(true);
        }
    }

    /**
     * @Author Ian Liston
     */
    class aboutProfileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.getAboutPanel().setVisible(false);
            gui.getNewOrUserPanel().setVisible(true);
        }
    }
}
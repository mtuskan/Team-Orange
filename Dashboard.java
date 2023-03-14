import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Michael Tuskan, Gregory Yi, Ian Liston
 *
 * Dashboard Class
 */
public class Dashboard extends JDialog{
    private JPanel mainDashboardPanel;
    private JPanel dashboardPanel;
    private JButton exportButton;
    private JButton importButton;
    private JButton homePageButton;
    private JPanel resourcesPanel;
    private JTextField exportFile;
    private JPanel exportName;
    private JButton FINISHButton;
    private JComboBox receiptComboBox;
    private JComboBox buildingMaterialsComboBox;
    private JComboBox plansComboBox;
    private JComboBox clientListComboBox;
    private JButton exportBackDashboardButton;
    private JButton addReceiptButton;
    private JButton addMaterialButton;
    private JButton addPlanButton;
    private JButton addClientButton;
    private JPanel toDoPanel;
    private JPanel taskDropPanel;
    private JPanel task1;
    private JButton task1Button;
    private JTextArea task1TextPane;
    private JPanel task2;
    private JButton task2Button;
    private JTextArea task2TextPane;
    private JPanel task3;
    private JButton task3Button;
    private JTextArea task3TextPane;
    private JPanel task4;
    private JButton task4Button;
    private JTextArea task4TextPane;
    private JPanel task5;
    private JButton task5Button;
    private JTextArea task5TextPane;
    private JPanel task6;
    private JButton task6Button;
    private JTextArea task6TextPane;
    private JPanel task7;
    private JButton task7Button;
    private JTextArea task7TextPane;
    private JPanel task8;
    private JButton task8Button;
    private JTextArea task8TextPane;
    private JPanel task9;
    private JButton task9Button;
    private JTextArea task9TextPane;
    private JPanel task10;
    private JButton task10Button;
    private JTextArea task10TextPane;
    private JPanel task11;
    private JButton task11Button;
    private JTextArea task11TextPane;
    private JTextPane toDoListTextPane;
    private JButton toDoListPlusButton;
    private JPanel taskWinPanel;
    private JButton taskWinPlusButton;
    private JTextField toDoLWinTextField;
    private JTextField toDoLWinDateField;
    private JPanel todoOrangeTitlePanel;
    private JTextPane ResourcesOrangeTitleLabel;
    private JPanel menuPanel;
    private JButton projectsButton;
    private JPanel projectPanel;
    private JButton createButton;
    private JButton deleteButton;
    private JComboBox comboBox1;

    private static User currUser;
    private Project project;
    private AddClient addClient;
    private AddMaterial addMaterial;
    private AddPlan addPlan;
    private AddReceipt addReceipt;
    private Resource resource;
    private Queue<JTextArea> taskTextQue;
    private Queue<JPanel> taskPanelQue;
    private Queue<JButton> taskButtonQue;

    /**
     * Michael Tuskan, Gregory Yi, Ian Liston
     * @param parent
     */
    public Dashboard(JFrame parent) {
        super(parent);

        //Create new Project Object
        project = new Project();

        //To-Do list queues
        taskTextQue = new LinkedList<>();
        taskPanelQue = new LinkedList<>();
        taskButtonQue = new LinkedList<>();
        createTaskQueues();

        //Main Dashboard Panel Options
        setTitle("Dashboard");
        setContentPane(mainDashboardPanel);
        setMinimumSize(new Dimension(1250, 820));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getProjectPanel().setVisible(false);

        //Resources Objects
        resource = new Resource(null);
        addClient = new AddClient(null);
        addMaterial = new AddMaterial(null);
        addPlan = new AddPlan(null);
        addReceipt = new AddReceipt(null);

        // Adding Resources - Listeners
        addClient.getAddClientButton().addActionListener(new addClientNameTextFieldListener());
        addMaterial.getAddMaterialButton().addActionListener(new addMaterialNameTextFieldListener());
        addReceipt.getAddReceiptButton().addActionListener(new addReceiptNameTextFieldListener());
        addPlan.getAddPlanButton().addActionListener(new addPlanNameTextFieldListener());
        resource.getRemoveButton().addActionListener(new removeResourceButtonListener());



        /** @author Michael Tuskan */
        receiptComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cbReceipt = (JComboBox)e.getSource();
                String receiptName = (String) cbReceipt.getSelectedItem();
                String strTemp = "";
                for (int idx = 0; idx < project.getReceipts().length; idx++) {
                    if (receiptName.matches(project.getReceipts()[idx].getType())) {
                        strTemp += project.getReceipts()[idx].toString();
                        resource.setReceiptIndexForPostRemoval(idx);
                    }
                }
                resource.getResourceLabel().setText(strTemp);
                resource.setVisible(true);
            }
        });

        /** @author Michael Tuskan */
        buildingMaterialsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cbMaterial = (JComboBox)e.getSource();
                String materialType = (String) cbMaterial.getSelectedItem();
                String strTemp = "";
                for (int idx = 0; idx < project.getMaterials().length; idx++) {
                    if (materialType.matches(project.getMaterials()[idx].getMaterial())) {
                        strTemp += project.getMaterials()[idx].toString();
                        resource.setMaterialIndexForPostRemoval(idx);
                    }
                }
                resource.getResourceLabel().setText(strTemp);
                resource.setVisible(true);
            }
        });

        /** @author Michael Tuskan */
        clientListComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cbClient = (JComboBox)e.getSource();
                String clientName = (String) cbClient.getSelectedItem();
                String strTemp = "";
                for (int idx = 0; idx < project.getClients().length; idx++) {
                    if (clientName.matches(project.getClients()[idx].getName())) {
                        strTemp += project.getClients()[idx].toString();
                        resource.setClientIndexForPostRemoval(idx);
                    }
                }
                resource.getResourceLabel().setText(strTemp);
                resource.setVisible(true);
            }
        });

        /** @author Michael Tuskan */
        plansComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cbPlan = (JComboBox)e.getSource();
                String planFileName = (String) cbPlan.getSelectedItem();
                String strTemp = "";
                for (int idx = 0; idx < project.getPlans().length; idx++) {
                    if (planFileName.matches(project.getPlans()[idx].getName())) {
                        strTemp += project.getPlans()[idx].toString();
                        resource.setPlanIndexForPostRemoval(idx);
                    }
                }
                resource.getResourceLabel().setText(strTemp);
                resource.setVisible(true);
            }
        });

        /** @author Michael Tuskan */
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClient.setVisible(true);

            }
        });

        /** @author Michael Tuskan */
        addMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMaterial.setVisible(true);

            }
        });

        /** @author Michael Tuskan */
        addReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReceipt.setVisible(true);
            }
        });

        /** @author Michael Tuskan */
        addPlanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlan.setVisible(true);
            }
        });


        exportBackDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboardPanel.setVisible(true);
                exportName.setVisible(false);
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getExportName().setVisible(true);
                dashboardPanel.setVisible(false);
            }
        });

        projectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getProjectPanel().setVisible(true);
                dashboardPanel.setVisible(false);
            }
        });

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String myPath = ".";
                File selected = null;
                JFileChooser JFile = new JFileChooser(new File(myPath));
                int returnVal = JFile.showOpenDialog(null);
                if (returnVal == JFile.APPROVE_OPTION) {
                    selected = JFile.getSelectedFile();
                }
                String filePath = selected.getAbsolutePath();

                if(currUser.importData(filePath, currUser)){
                    JOptionPane.showMessageDialog(dashboardPanel,
                            "Successfully exported file " + filePath,
                            "Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(dashboardPanel,
                            "Failed to create file, please try again",
                            "Failed",
                            JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        FINISHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = exportFile.getText();
                String message = currUser.export(fileName, currUser);
                if (message.contains("Successfully")) {
                    JOptionPane.showMessageDialog(dashboardPanel,
                            message,
                            "Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(dashboardPanel,
                            message,
                            "Failed",
                            JOptionPane.ERROR_MESSAGE);
                }

                System.out.println("Successful for " + currUser.getName());
                getExportName().setVisible(false);
                dashboardPanel.setVisible(true);

            }
        });

        /**
         * Author Ian Liston
         */
        // Creates round boarder of task bars {
        task1.setBorder(new RoundedBorder(10));
        task2.setBorder(new RoundedBorder(10));
        task3.setBorder(new RoundedBorder(10));
        task4.setBorder(new RoundedBorder(10));
        task5.setBorder(new RoundedBorder(10));
        task6.setBorder(new RoundedBorder(10));
        task7.setBorder(new RoundedBorder(10));
        task8.setBorder(new RoundedBorder(10));
        task9.setBorder(new RoundedBorder(10));
        task10.setBorder(new RoundedBorder(10));
        task11.setBorder(new RoundedBorder(10));
        // }

        /**
         * Author Ian Liston
         */
        // The To-Do-List plus button action listener opens the new task bar window
        toDoListPlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskDropPanel.setVisible(false);
                taskWinPanel.setVisible(true);
            }
        });

        /**
         * Author Ian Liston
         */
        // New task popup window plus button. This Window adds a new task to the to-dolist.
        taskWinPlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!taskPanelQue.isEmpty()) {
                    Task task = new Task(toDoLWinTextField.getText(), toDoLWinDateField.getText(), taskPanelQue.remove(),
                            taskButtonQue.remove(), taskTextQue.remove(), project);
                    project.addTask(task);
                }
                project.rePopulateTasks();
                taskWinPanel.setVisible(false);
                taskDropPanel.setVisible(true);

            }
        });
    }

    public static void setCurrUser(User theUser) {
        currUser = theUser;
    }

    public User getCurrUser() {
        return currUser;
    }

    public JButton getHomepageButton() {
        return homePageButton;
    }

    public JPanel getExportName() {
        return exportName;
    }

    public JPanel getProjectPanel(){return projectPanel;}

    /** @author Michael Tuskan */
    public void setReceiptJComboBox(Receipt receipt) {
        receiptComboBox.addItem(receipt.getType());
    }

    /** @author Michael Tuskan */
    public void setMaterialJComboBox(Material material) {
        buildingMaterialsComboBox.addItem(material.getMaterial());
    }

    /** @author Michael Tuskan */
    public void setPlanJComboBox(Plan plan) {
        plansComboBox.addItem(plan.getName());
    }

    /** @author Michael Tuskan */
    public void setClientJComboBox(Client client) {
        clientListComboBox.addItem(client.getName());
    }

    /** @author Michael Tuskan */
    public void removeReceiptJComboBox(int compareIndex) {
        for (int idx = 0; idx < receiptComboBox.getItemCount(); idx++) {
            if (idx == compareIndex) {
                receiptComboBox.removeItemAt(idx);
            }
        }

    }

    /** @author Michael Tuskan */
    public void removeMaterialJComboBox(int compareIndex) {
        for (int idx = 0; idx < buildingMaterialsComboBox.getItemCount(); idx++) {
            if (idx == compareIndex) {
                buildingMaterialsComboBox.removeItemAt(idx);
            }
        }

    }

    /** @author Michael Tuskan */
    public void removePlanJComboBox(int compareIndex) {
        for (int idx = 0; idx < plansComboBox.getItemCount(); idx++) {
            if (idx == compareIndex) {
                plansComboBox.removeItemAt(idx);
            }
        }

    }

    /** @author Michael Tuskan */
    public void removeClientJComboBox(int compareIndex) {
        for (int idx = 0; idx < clientListComboBox.getItemCount(); idx++) {
            if (idx == compareIndex) {
                clientListComboBox.removeItemAt(idx);
            }
        }

    }

    /** @author Michael Tuskan */
    class addClientNameTextFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Client client = new Client(addClient.getNametTextField().getText(),
                                       addClient.getCompanyTextField().getText()
                                     , addClient.getContactTextField().getText());
            project.addClient(client);
            setClientJComboBox(client);
        }
    }

    /** @author Michael Tuskan */
    class addMaterialNameTextFieldListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Material material = new Material(addMaterial.getMaterialTextField().getText(),
                           Integer.parseInt(addMaterial.getQuantityTextField().getText()));
            project.addMaterials(material);
            setMaterialJComboBox(material);
        }
    }

    /** @author Michael Tuskan */
    class addReceiptNameTextFieldListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Receipt receipt = new Receipt(Integer.parseInt(addReceipt.getQuantityTextField().getText()),
                                  addReceipt.getTypeTextField().getText(),
                                  Double.parseDouble(addReceipt.getPriceTextField().getText()));
            project.addReceipt(receipt);
            setReceiptJComboBox(receipt);
        }
    }

    /** @author Michael Tuskan */
    class addPlanNameTextFieldListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File newFile = new File("src/" +addPlan.getFilePathTextField().getText());
            Plan plan = new Plan(addPlan.getFileNameTextField().getText(),newFile);
            project.addPlan(plan);
            setPlanJComboBox(plan);
        }
    }

    /** @author Michael Tuskan */
    class removeResourceButtonListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(resource.getResourceLabel().getText().contains("Client List:")) {
                project.removeClient(resource.getClientIndexForPostRemoval());
                removeClientJComboBox(resource.getClientIndexForPostRemoval());
            }
            if (resource.getResourceLabel().getText().contains("Receipt:")) {
                project.removeReceipt(resource.getReceiptIndexForPostRemoval());
                removeReceiptJComboBox(resource.getReceiptIndexForPostRemoval());
            }
            if (resource.getResourceLabel().getText().contains("Building Material:")) {
                project.removeMaterial(resource.getMaterialIndexForPostRemoval());
                removeMaterialJComboBox(resource.getMaterialIndexForPostRemoval());
            }
            if (resource.getResourceLabel().getText().contains("Plan:")){
                project.removePlan(resource.getPlanIndexForPostRemoval());
                removePlanJComboBox(resource.getPlanIndexForPostRemoval());
            }
            resource.setVisible(false);

        }
    }


    /**
     * Author Ian Liston
     */
    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }

    /** @author Ian Liston */
    private void createTaskQueues(){
        this.taskTextQue.add(task1TextPane);
        this.taskTextQue.add(task2TextPane);
        this.taskTextQue.add(task3TextPane);
        this.taskTextQue.add(task4TextPane);
        this.taskTextQue.add(task5TextPane);
        this.taskTextQue.add(task6TextPane);
        this.taskTextQue.add(task7TextPane);
        this.taskTextQue.add(task8TextPane);
        this.taskTextQue.add(task9TextPane);
        this.taskTextQue.add(task10TextPane);
        this.taskTextQue.add(task11TextPane);

        this.taskPanelQue.add(task1);
        this.taskPanelQue.add(task2);
        this.taskPanelQue.add(task3);
        this.taskPanelQue.add(task4);
        this.taskPanelQue.add(task5);
        this.taskPanelQue.add(task6);
        this.taskPanelQue.add(task7);
        this.taskPanelQue.add(task8);
        this.taskPanelQue.add(task9);
        this.taskPanelQue.add(task10);
        this.taskPanelQue.add(task11);

        this.taskButtonQue.add(task1Button);
        this.taskButtonQue.add(task2Button);
        this.taskButtonQue.add(task3Button);
        this.taskButtonQue.add(task4Button);
        this.taskButtonQue.add(task5Button);
        this.taskButtonQue.add(task6Button);
        this.taskButtonQue.add(task7Button);
        this.taskButtonQue.add(task8Button);
        this.taskButtonQue.add(task9Button);
        this.taskButtonQue.add(task10Button);
        this.taskButtonQue.add(task11Button);
    }


}

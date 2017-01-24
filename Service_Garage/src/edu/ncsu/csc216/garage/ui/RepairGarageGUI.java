package edu.ncsu.csc216.garage.ui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import edu.ncsu.csc216.garage.model.dealer.ServiceManager;
import edu.ncsu.csc216.garage.model.vehicle.Tiered;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Project 3: RepairGarageGUI
 * 
 * Description: The graphical user interface for the program.
 * 
 * @author Bryan LeBlanc
 *
 */
@SuppressWarnings("serial")
public class RepairGarageGUI extends JFrame implements ActionListener {

     /** Panels */
    private JPanel vehiclePanel;
    private JPanel servicePanel;
    private JPanel vehicleListPanel;
    private JPanel bayListPanel;
    
    private Container c = null;
    private ServiceManager serviceManager;
    private String filter = "";
    
    
    
    /** Button */
    private JButton removeVehicleButton;
    private JButton addVehicleButton;
    private JButton addBayButton;
    private JButton fillBayButton;
    private JButton finishButton;
    private JButton quitButton;
    private JButton editButton;
    
    /** Labels */
    private JLabel filterLabel;
    
    /** Text Field */
    private TextField textField1;
    
    /** Scroll Panel */
    private JScrollPane vehicleScroll;
    private JScrollPane bayScroll;
    
    /** List */
    private JList<String> bayList;
    private JList<String> vehicleList;
    
    /**
     * constructor for a new GUI to be displayed
     */
    public RepairGarageGUI() {
        serviceManager = new ServiceManager();
        final JFileChooser chooser = new JFileChooser();
        int x = chooser.showOpenDialog(c);
         if (x == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                new RepairGarageGUI(file.getName());
            } else {
                 new RepairGarageGUI("");
            }
    }
    

    /**
     * constructor for a new GUI with an existing file
     * @param filename the filename to read from
     */
    public RepairGarageGUI(String filename) {
        Scanner s;
        try {
            s = new Scanner(new File(filename));
            serviceManager = new ServiceManager(s);
        } catch (FileNotFoundException e) {
            serviceManager = new ServiceManager();
            
        }
        createGUI();
        this.setVisible(true);
    }
    
    private void createGUI() {
        // sets up frame
        this.setTitle("Service Garage Manager");
        this.setSize(800, 600);
        this.setLocation(50, 50);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        c = this.getContentPane();
        c.setLayout(new GridLayout(2, 2));
        
        vehiclePanel();
        servicePanel();
        vehicleListPanel();
        serviceBayList();
        
        setVisible(true);
        updateList();
 
    }
    
    /**
     * Creates vehicle panel, buttons, and text field 
     */
    private void vehiclePanel() {
        // vehicle panel
        vehiclePanel = new JPanel();
        
        vehiclePanel.setBorder(new TitledBorder("Vehicles Awaiting Service"));
        c.add(vehiclePanel, BorderLayout.WEST);
        addVehicleButton = new JButton("Add New Vehicle");
        addVehicleButton.addActionListener(this);
        removeVehicleButton = new JButton("Remove Selected Vehicle");
        removeVehicleButton.addActionListener(this);
        editButton = new JButton("Edit Selected Vehicle");
        editButton.addActionListener(this);
        filterLabel = new JLabel("Display Filter:");
        textField1 = new TextField(15);
        textField1.addActionListener(this);
        vehiclePanel.add(addVehicleButton);
        vehiclePanel.add(removeVehicleButton);
        
        vehiclePanel.add(filterLabel);
        vehiclePanel.add(textField1);
        vehiclePanel.add(editButton);
    }
    
    /**
     * Creates service panel and buttons 
     */
    private void servicePanel() {
        // Service Panel 
        servicePanel = new JPanel();
        
        servicePanel.setBorder(new TitledBorder("Service Bays"));
        c.add(servicePanel, BorderLayout.NORTH);
        addBayButton = new JButton("Add Service Bay");
        addBayButton.addActionListener(this);
        fillBayButton = new JButton("Fill Service Bays");
        fillBayButton.addActionListener(this);
        finishButton = new JButton("Finish Repair of Selected Vehicle");
        finishButton.addActionListener(this);
        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);

        servicePanel.add(addBayButton);
        servicePanel.add(fillBayButton);
        servicePanel.add(finishButton);
        servicePanel.add(quitButton);
    }
        
    /**
     * Creates vehicle list panel and scroll pane 
     */
    private void vehicleListPanel() {
        // Vehicle List Panel 
        vehicleListPanel = new JPanel();
        
        c.add(vehicleListPanel, BorderLayout.EAST);
        vehicleList = new JList<String>();
        vehicleScroll = new JScrollPane(vehicleList);
        
        vehicleScroll.setPreferredSize(new Dimension(390, 280));
        vehicleListPanel.add(vehicleScroll);
    }
        
    /**
     * Creates service bay list panel and scroll pane 
     */
    private void serviceBayList() {
        //Service Bay List Panel
        bayListPanel = new JPanel();
        
        c.add(bayListPanel, BorderLayout.SOUTH);
        bayList = new JList<String>();
        bayScroll = new JScrollPane(bayList);
        bayScroll.setPreferredSize(new Dimension(390, 280));
        bayListPanel.add(bayScroll);
        
    }

    /**
     * Method takes care of the action listeners
     * @param e ActionListener event
     */
    public void actionPerformed(ActionEvent e) {
        // Add Vehicle
        if (e.getSource().equals(addVehicleButton)) {
            UserDialog addPane = new UserDialog();
            addPane.setVisible(true);
            Vehicle vehicle = addPane.getNewVehicle();
            if (vehicle != null) {
                serviceManager.putOnWaitingList(vehicle);
            }
        }
        
        // Edit Vehicle
        if (e.getSource().equals(editButton)) {
            int listIndex = vehicleList.getSelectedIndex();
            if (listIndex >= 0) {
                Tiered tier = serviceManager.getWaitingItem(null, listIndex);
                @SuppressWarnings("resource")
                Scanner scan = new Scanner(tier.toString());
                
                String type = scan.next(); 
                @SuppressWarnings("unused")
                String tierType = scan.next(); 
                String license = scan.next();
                String name = scan.next();
                
                Edit edit = new Edit();
                edit.setVisible(true);
                int returned = edit.getNewTier();
                if (returned >= 0) {
                    serviceManager.remove(null, listIndex);
                    serviceManager.putOnWaitingList(type, license, name, returned);
                } 

            }

        }
        
        // Filter waiting list 
        if (e.getSource().equals(textField1)) {
            filter = textField1.getText();
        }
        
        // Remove Vehicle
        if(e.getSource().equals(removeVehicleButton)) {
            int listIndex = vehicleList.getSelectedIndex();
            if (listIndex >= 0) { 
                serviceManager.remove(filter, listIndex);
                
            }
        }
        
        // Add Bay
        if (e.getSource().equals(addBayButton)) {
            serviceManager.addNewBay();
        }
        
        // Fills Service Bays 
        if (e.getSource().equals(fillBayButton)) {
            serviceManager.fillServiceBays();
        }
        
        // "Finishes" Vehicle From Service Bay
        if (e.getSource().equals(finishButton)) {
            int index = bayList.getSelectedIndex();
            if (index >= 0) {
                serviceManager.releaseFromService(index);
            }
        }
        if (e.getSource().equals(quitButton)) {
            System.exit(0);
        }
        //updates the list 
        updateList();
    }

    /**
     * Method updates the waiting and service bay list 
     */
    private void updateList() {
        String[] wList = serviceManager.printWaitList(filter).split("\n");
        vehicleList.setListData(wList);
        vehicleListPanel.revalidate();
        vehicleListPanel.repaint();
        
        String[] bList = serviceManager.printServiceBays().split("\n");
        bayList.setListData(bList);
        bayListPanel.revalidate();
        bayListPanel.repaint();
    }

    /**
     * main method to be run when the program starts, starts the entire program
     * @param args command line arguments, not used
     */
    public static void main(String args[]) {
        System.out.println("Please Enter File Name: ");
        if (args.length > 0) {
            new RepairGarageGUI(args[0]);
        } else {
            new RepairGarageGUI();
        }
    }

}
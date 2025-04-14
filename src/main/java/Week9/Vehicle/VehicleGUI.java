package Week9.Vehicle;

import javax.swing.*;
import java.awt.*;

public class VehicleGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField nameText;
    private JTextField makeText;
    private JTextField modelText;
    private JCheckBox electricCheckBox;
    private JButton addButton;
    private JList<Vehicle> vehicleList;
    private JButton deleteButton;

    // Create list model
    private final DefaultListModel<Vehicle> ListModel;

    // Constructor
    VehicleGUI() {
        setTitle("Vehicle List");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set the Add button as default button
        rootPane.setDefaultButton(addButton);

        ListModel = new DefaultListModel<>();
        vehicleList.setModel(ListModel);
        vehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add actions to the add button
        addButton.addActionListener(e -> {
            // Create variables for the data
            String name = nameText.getText();
            String make = makeText.getText();
            String model = modelText.getText();
            boolean isElectric = electricCheckBox.isSelected();

            // make sure the text fields aren't empty
            if (name.isBlank() || make.isBlank() || model.isBlank()) {
                JOptionPane.showMessageDialog(VehicleGUI.this, "Fill in all fields");
                return;
            }

            // Make a new vehicle with the data
            Vehicle vehicle = new Vehicle(name, make, model, isElectric);
            // Add the vehicle to the list
            ListModel.addElement(vehicle);

            // Clear the text fields and unselect checkbox
            nameText.setText("");
            makeText.setText("");
            modelText.setText("");
            electricCheckBox.setSelected(false);


        });

        // Add actions to the delete button
        deleteButton.addActionListener(e -> {
            // Create Variable for selected vehicle
            Vehicle selectedVehicle = vehicleList.getSelectedValue();
            // Delete vehicle if it's selected and if the user confirms
            if (selectedVehicle != null) {

                if(JOptionPane.showConfirmDialog(VehicleGUI.this, "Delete " +
                        selectedVehicle.getName() + "?", "Delete Vehicle", JOptionPane.OK_CANCEL_OPTION)
                        == JOptionPane.OK_OPTION) {
                ListModel.removeElement(selectedVehicle);
                }

            // Show a message if nothing is selected
            } else {
                JOptionPane.showMessageDialog(VehicleGUI.this, "No vehicle selected");
            }
        });

    }
}

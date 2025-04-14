package Week9.Tshirt;

import javax.swing.*;
import java.awt.*;

public class TShirtGUI extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> sizeComboBox;
    private JLabel selectedSizeLabel;
    private JComboBox<String> colorComboBox;
    private JLabel selectedColorLabel;


    TShirtGUI() {
        setTitle("T-Shirt Order Form");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 200));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add Strings to size combo box
        sizeComboBox.addItem("Small");
        sizeComboBox.addItem("Medium");
        sizeComboBox.addItem("Large");

        // Make the size Combo Box unselected from the start
        sizeComboBox.setSelectedIndex(-1);

        // Add actions to the size combo box
        sizeComboBox.addActionListener(event -> {
            // Create variable for the selected size
            String size = (String) sizeComboBox.getSelectedItem();
            // Set the text for the size label
            selectedSizeLabel.setText("Thanks, you chose " + size);
        });

        // Create a list to hold the different color options
        String[] colors = { "Red","Orange", "Yellow", "Green" ,"Blue", "Purple" };
        // new Combo Box model with array of colors
        DefaultComboBoxModel<String> colorModel = new DefaultComboBoxModel<>(colors);
        // Set the color Combo Box to use the new model
        colorComboBox.setModel(colorModel);

        // Make the color Combo Box unselected from the start
        colorComboBox.setSelectedIndex(-1);

        // Add actions to the color combo box
        colorComboBox.addActionListener(event -> {
            // Create variable for the selected color
            String color = (String) colorComboBox.getSelectedItem();
            // Set the text for the color label
            selectedColorLabel.setText("Thanks, you chose " + color);

        });
    }
}

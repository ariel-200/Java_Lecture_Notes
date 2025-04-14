package Week8.Movie_GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieGUI extends JFrame {
    private JSlider ratingSlider;
    private JCheckBox seeAgainCheckBox;
    private JTextField movieText;
    private JPanel mainPanel;
    private JLabel sliderLabel;
    private JLabel opinionLabel;
    private JButton quitButton;

    // Constructor
    MovieGUI() {
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        configureEventHandlers();

    }

    // Method to work with Event Handling
    private void configureEventHandlers() {
        // Add actions to the slider
        ratingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // create a string to hold the slider label
                String rating = ratingSlider.getValue() + " stars";
                // set the label to the string
                sliderLabel.setText(rating);
                // update the opinion line when changed
                updateOpinion();
            }
        });

        // Add actions to the checkbox
        seeAgainCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // update the opinion line when changed
                updateOpinion();
            }
        });

        // Add actions to the movie text box
        movieText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // update the opinion line when you inset data
                updateOpinion();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // update the opinion line when you remove data
                updateOpinion();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // ignore this one
            }
        });

        // Add actions to the quit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // End the program if the user agrees
                if (JOptionPane.showConfirmDialog(MovieGUI.this, "Are you sure you want to quit?", "Quit",
                        JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION ) {
                    dispose();
                }
            }
        });
    }

    // method to update the opinion line
    private void updateOpinion() {

        // Create variables for the message
        String movieName = movieText.getText().strip();

        // Return the program back to start if movie name is empty
        if (movieName.isEmpty()) {
            opinionLabel.setText("Movie opinion");
            return;
        }

        int rating = ratingSlider.getValue();
        boolean seeAgain = seeAgainCheckBox.isSelected();
        String seeAgainText = (seeAgain) ? "would" : "would not";

        // Opinion message template
        String template = "You rated %s %d stars. You %s see again.";

        // Create the string and set the opinion label
        String opinion = String.format(template, movieName, rating, seeAgainText);
        opinionLabel.setText(opinion);
    }
}

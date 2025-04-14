package Week8.Hello_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloGUI extends JFrame {
    private JPanel mainPanel;
    private JButton clickMeButton;
    private JLabel myFirstLabel;

    HelloGUI() {
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 500)); // sets the size of the main panel
        pack();
        setVisible(true); // makes it visible
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // default close when exit

        // Set actions for the button
        clickMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // code here - this will run when the button is clicked.
                myFirstLabel.setText("Hello GUI Programmers!!");
            }
        });

    }

}

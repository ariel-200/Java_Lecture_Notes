package Week9.ToDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToDoList extends JFrame{
    private JPanel mainPanel;
    private JTextField newToDoText;
    private JButton addToDoButton;
    private JList<String> toDoList;
    private JButton deleteButton;
    // Create JList model
    private final DefaultListModel<String> listModel;
    private final JMenuItem deleteItem;

    // Constructor
    ToDoList(){
        setTitle("To Do List");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 500));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set the add button as the default button
        getRootPane().setDefaultButton(addToDoButton);

        // Create and set the List Model
        listModel = new DefaultListModel<>();
        toDoList.setModel(listModel);
        // Set the selection model to only select 1 list item at a time
        toDoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create a Popup Menu
        // Create Popup Menu and menu items
        JPopupMenu rightClickMenu = new JPopupMenu();
        // Create and add Delete menu item
        deleteItem = new JMenuItem("Delete");
        rightClickMenu.add(deleteItem);

        toDoList.setComponentPopupMenu(rightClickMenu);

        addListeners();
    }

    // Method for Action Listeners
    private void addListeners(){

        // Add actions to the add button
        addToDoButton.addActionListener(e -> {
            // Create variable for new To Do Strings
            String newTodo = newToDoText.getText().trim(); // .trim removes whitespace
            // If there's nothing in the textbox then show a message
            if (newTodo.isEmpty()){
                JOptionPane.showMessageDialog(ToDoList.this, "Enter a ToDo item");
            // Else add the String to the list
            } else {
                listModel.addElement(newTodo);
                // Clear the text box
                newToDoText.setText("");
            }
        });

        // Add actions to Delete Menu item
        deleteItem.addActionListener(e -> {
            // Create variable for what was selected
            int selectedIndex = toDoList.getSelectedIndex(); // -1 if nothing selected
            // If an item is selected remove it from the list
            if (selectedIndex != -1){
                listModel.remove(selectedIndex);
                // Show a message if nothing is selected
            } else {
                JOptionPane.showMessageDialog(ToDoList.this, "Select an item to delete");
            }
        });

        // Add a mouse listener to add actions when to the mouse
        toDoList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Create variable to find which list item is under the mouse
                int selection = toDoList.locationToIndex(e.getPoint());
                toDoList.setSelectedIndex(selection);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });


        // Add actions to the delete button
        deleteButton.addActionListener(e -> {
            // Create variable for what was selected
            int selectedIndex = toDoList.getSelectedIndex(); // -1 if nothing selected
            // If an item is selected remove it from the list
            if (selectedIndex != -1){
                listModel.remove(selectedIndex);
            // Show a message if nothing is selected
            } else {
                JOptionPane.showMessageDialog(ToDoList.this, "Select an item to delete");
            }

        });

         //Add actions when you select a list item
          /* toDoList.addListSelectionListener(e -> {
            // Create variable for what was selected
            int selectedIndex = toDoList.getSelectedIndex(); // -1 if nothing selected
            // If an item is selected remove it from the list if the user selects ok
            if (selectedIndex != -1){
                String item = toDoList.getSelectedValue();
                if (JOptionPane.showConfirmDialog(ToDoList.this, "Delete " + item + "?", "Delete?", JOptionPane.OK_CANCEL_OPTION)
                        == JOptionPane.OK_OPTION) {
                    listModel.remove(selectedIndex);
                }
            }
        });*/
    }
}

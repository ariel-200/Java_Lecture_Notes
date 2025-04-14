package Week8.Currency_Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CurrencyConverter extends JFrame {
    private JTextField dollarsText;
    private JButton convertButton;
    private JLabel resultLabel;
    private JPanel mainPanel;
    private JComboBox<String> currencyBox;

    // Exchange rates to multiply
    private final String EUROS = "Euros";
    private final String POUNDS = "Pounds";

    private final Map<String, Double> exchangeRates = Map.of(EUROS, 0.84, POUNDS, 0.75);

    // Constructor
    CurrencyConverter() {
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //
        getRootPane().setDefaultButton(convertButton);

        // Add String options to the Combo Box
        currencyBox.addItem(EUROS);
        currencyBox.addItem(POUNDS);

        // Add actions to the button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get the text from the dollars text field
                String dollarString = dollarsText.getText();
                // Convert string to a number
                try {
                    double dollars = Double.parseDouble(dollarString);
                    // Find the currency to convert to
                    String toCurrency = (String) currencyBox.getSelectedItem();
                    // Find the exchange rate
                    double exchangeRate = exchangeRates.get(toCurrency);
                    // Calculate the result
                    double converted = exchangeRate * dollars;
                    // display the result in the resultLabel
                    String results = String.format("%.2f dollars is equivalent to %.2f %s.", dollars, converted, toCurrency);
                    resultLabel.setText(results);

                    // Exception Handling
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CurrencyConverter.this,
                            "Please enter a number without any $ or other characters.");
                }
            }
        });
    }
}

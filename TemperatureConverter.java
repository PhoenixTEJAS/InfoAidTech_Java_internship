import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {

    private JTextField inputTextField;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Convert from label
        JLabel convertFromLabel = new JLabel("Convert from:");
        add(convertFromLabel);

        // Radio button panel for selecting temperature scale
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout());

        JRadioButton celsiusRadioButton = new JRadioButton("Celsius");
        celsiusRadioButton.setSelected(true);
        radioButtonPanel.add(celsiusRadioButton);

        JRadioButton fahrenheitRadioButton = new JRadioButton("Fahrenheit");
        radioButtonPanel.add(fahrenheitRadioButton);

        JRadioButton kelvinRadioButton = new JRadioButton("Kelvin");
        radioButtonPanel.add(kelvinRadioButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(celsiusRadioButton);
        buttonGroup.add(fahrenheitRadioButton);
        buttonGroup.add(kelvinRadioButton);

        add(radioButtonPanel);

        // Enter temperature label
        JLabel enterTempLabel = new JLabel("Enter temperature:");
        add(enterTempLabel);

        // Text field for user input
        inputTextField = new JTextField();
        add(inputTextField);

        // Convert button
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature(celsiusRadioButton.isSelected(), fahrenheitRadioButton.isSelected(),
                        kelvinRadioButton.isSelected());
            }
        });
        add(convertButton);

        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        add(clearButton);

        // Result label
        resultLabel = new JLabel();
        add(resultLabel);

        setVisible(true);
    }

    private void convertTemperature(boolean isCelsiusSelected, boolean isFahrenheitSelected, boolean isKelvinSelected) {
        try {
            // Parse temperature input
            double temperature = Double.parseDouble(inputTextField.getText());

            // Perform temperature conversion based on the selected scale
            if (isCelsiusSelected) {
                convertCelsius(temperature);
            } else if (isFahrenheitSelected) {
                convertFahrenheit(temperature);
            } else if (isKelvinSelected) {
                convertKelvin(temperature);
            }
        } catch (NumberFormatException e) {
            // Handle invalid input
            resultLabel.setText("Invalid input! Enter a valid temperature.");
        }
    }

    private void convertCelsius(double temperature) {
        double convertedTemperature;
        String unit;
        if (resultLabel.getText().contains("Fahrenheit")) {
            // Convert Fahrenheit to Celsius
            convertedTemperature = temperature * 9 / 5 + 32;
            unit = "Fahrenheit";
        } else {
            // Convert Kelvin to Celsius
            convertedTemperature = temperature + 273.15;
            unit = "Kelvin";
        }
        displayResult(convertedTemperature, unit);
    }

    private void convertFahrenheit(double temperature) {
        double convertedTemperature;
        String unit;
        if (resultLabel.getText().contains("Celsius")) {
            // Convert Celsius to Fahrenheit
            convertedTemperature = (temperature - 32) * 5 / 9;
            unit = "Celsius";
        } else {
            // Convert Kelvin to Fahrenheit
            convertedTemperature = (temperature + 459.67) * 5 / 9;
            unit = "Kelvin";
        }
        displayResult(convertedTemperature, unit);
    }

    private void convertKelvin(double temperature) {
        double convertedTemperature;
        String unit;
        if (resultLabel.getText().contains("Celsius")) {
            // Convert Celsius to Kelvin
            convertedTemperature = temperature - 273.15;
            unit = "Celsius";
        } else {
            // Convert Fahrenheit to Kelvin
            convertedTemperature = temperature * 9 / 5 - 459.67;
            unit = "Fahrenheit";
        }
        displayResult(convertedTemperature, unit);
    }

    private void displayResult(double temperature, String unit) {
        // Display the converted temperature and unit
        resultLabel.setText("Result: " + temperature + " " + unit);
    }

    private void clear() {
        // Clear the input and result labels
        inputTextField.setText("");
        resultLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}

package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWindow implements ActionListener {
    JFrame frame = new JFrame();
    JTextField display = new JTextField();
    JButton[] buttons = new JButton[20];
    
    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean startNewNumber = true;
    
    NewWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout()); 
        
        // display 
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setText("0");
        display.setPreferredSize(new Dimension(0, 80)); 
        
        // Panel for calculator buttons 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4)); 
        
        // buttons
        String[] buttonLabels = {
            "Del", "AC", "%", "\u00F7",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
        };
        
        // buttons and  action listeners
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
        

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        // Number buttons (0-9)
        if (command.matches("[0-9]")) {
            if (startNewNumber) {
                display.setText(command);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        }
        
        // Decimal point
        else if (command.equals(".")) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
                startNewNumber = false;
            }
        }
        
        // Operators
        else if (command.equals("+")) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            startNewNumber = true;
        }
        else if (command.equals("-")) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            startNewNumber = true;
        }
        else if (command.equals("x")) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            startNewNumber = true;
        }
        else if (command.equals("\u00F7")) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            startNewNumber = true;
        }
        
        // Equals button
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 != 0) result = num1 / num2;
                    else display.setText("Error");
                    break;
            }
            
            if (result == (int) result) {
                display.setText(String.valueOf((int) result));
            } else {
                display.setText(String.valueOf(result));
            }
            startNewNumber = true;
        }
        
        // Clear button (AC)
        else if (command.equals("AC")) {
            display.setText("0");
            num1 = num2 = result = 0;
            startNewNumber = true;
        }
        
        // Delete button (Del)
        else if (command.equals("Del")) {
            String text = display.getText();
            if (text.length() > 1) {
                display.setText(text.substring(0, text.length() - 1));
            } else {
                display.setText("0");
                startNewNumber = true;
            }
        }
        
        // Percentage button
        else if (command.equals("%")) {
            double val = Double.parseDouble(display.getText());
            val = val / 100;
            if (val == (int) val) {
                display.setText(String.valueOf((int) val));
            } else {
                display.setText(String.valueOf(val));
            }
            startNewNumber = true;
        }
        
        // +/- button
        else if (command.equals("+/-")) {
            double val = Double.parseDouble(display.getText());
            val = val * -1;
            if (val == (int) val) {
                display.setText(String.valueOf((int) val));
            } else {
                display.setText(String.valueOf(val));
            }
        }
    }
}
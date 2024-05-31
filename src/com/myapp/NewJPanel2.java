package com.myapp;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class NewJPanel2 extends JPanel implements ActionListener {
    JButton[] buttons;
    String[] label = {"%", "CE", "C", "DEL", "7", "8", "9", "X", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "="};
    private JTextField display; // Champ pour l'affichage
    private String operator;
    private double firstOperand;
    private boolean isOperatorPressed;
            
    public NewJPanel2(JTextField display) {
        this.display = display; // Initialisation de l'affichage
        initComponents();
        buttons = new JButton[label.length];
        setLayout(new GridLayout(5, 4));

        for (int i = 0; i < label.length; i++) {
            buttons[i] = new JButton(label[i]);
            add(buttons[i]);
            buttons[i].addActionListener(this);
        }
        
        operator = "";
        firstOperand = 0;
        isOperatorPressed = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String command = source.getActionCommand();
        
        if (command.matches("\\d") || command.equals(".")) {
            if (isOperatorPressed) {
                display.setText(command);
                isOperatorPressed = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.matches("[+\\-*/X]")) {
            firstOperand = Double.parseDouble(display.getText());
            operator = command;
            isOperatorPressed = true;
        } else {
            switch (command) {
                case "=" -> {
                    double secondOperand = Double.parseDouble(display.getText());
                    double result = 0;
                    switch (operator) {
                        case "+" -> result = firstOperand + secondOperand;
                        case "-" -> result = firstOperand - secondOperand;
                        case "*" -> result = firstOperand * secondOperand;
                        case "/" -> result = firstOperand / secondOperand;
                        case "X" -> result = firstOperand * secondOperand;
                    }
                    display.setText(String.valueOf(result));
                    operator = "";
                    isOperatorPressed = false;
                }
                case "C" -> {
                    display.setText("");
                    firstOperand = 0;
                    operator = "";
                    isOperatorPressed = false;
                }
                case "DEL" -> {
                    String currentText = display.getText();
                    if (currentText.length() > 0) {
                        display.setText(currentText.substring(0, currentText.length() - 1));
                    }
                }
                case "CE" -> display.setText("");
                case "%" -> {
                    double currentValue = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(currentValue / 100));
                }
            }
        }
    }

    /**
     * Cette méthode est appelée depuis le constructeur pour initialiser le formulaire.
     * AVERTISSEMENT : Ne modifiez pas ce code. Le contenu de cette méthode est toujours
     * régénéré par l'éditeur de formulaire.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>

    // Variables declaration - ne pas modifier
    // Fin de la déclaration des variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nicolas
 */

import view.LoginScreen;
import view.MainScreen;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        UIManager.put("Panel.background",             new java.awt.Color(250, 250, 252));
        UIManager.put("OptionPane.background",        java.awt.Color.WHITE);
        UIManager.put("OptionPane.messageForeground", new java.awt.Color(30, 30, 35));
        UIManager.put("Button.focus",                 new java.awt.Color(0, 0, 0, 0));
        UIManager.put("ComboBox.background",          java.awt.Color.WHITE);
        UIManager.put("ComboBox.foreground",          new java.awt.Color(30, 30, 35));
        UIManager.put("ComboBox.selectionBackground", new java.awt.Color(230, 230, 240));
        UIManager.put("ComboBox.selectionForeground", new java.awt.Color(30, 30, 35));
        UIManager.put("TextField.caretForeground",    new java.awt.Color(30, 30, 35));
        UIManager.put("PasswordField.caretForeground",new java.awt.Color(30, 30, 35));

        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
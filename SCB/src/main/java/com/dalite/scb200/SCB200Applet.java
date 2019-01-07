package com.dalite.scb200;

import javax.swing.*;
import java.awt.*;


public class SCB200Applet extends JApplet {
    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception localException) {
            System.out.println("Look and feel setup failed");
            localException.printStackTrace(System.out);
        }
        Font localFont = new Font("SanSerif", Font.PLAIN, 11);
        UIManager.put("Button.font", localFont);
        UIManager.put("ToggleButton.font", localFont);
        UIManager.put("RadioButton.font", localFont);
        UIManager.put("CheckBox.font", localFont);
        UIManager.put("ColorChooser.font", localFont);
        UIManager.put("ComboBox.font", localFont);
        UIManager.put("Label.font", localFont);
        UIManager.put("List.font", localFont);
        UIManager.put("MenuBar.font", localFont);
        UIManager.put("MenuItem.font", localFont);
        UIManager.put("RadioButtonMenuItem.font", localFont);
        UIManager.put("CheckBoxMenuItem.font", localFont);
        UIManager.put("Menu.font", localFont);
        UIManager.put("PopupMenu.font", localFont);
        UIManager.put("OptionPane.font", localFont);
        UIManager.put("Panel.font", localFont);
        UIManager.put("ProgressBar.font", localFont);
        UIManager.put("ScrollPane.font", localFont);
        UIManager.put("Viewport.font", localFont);
        UIManager.put("TabbedPane.font", localFont);
        UIManager.put("Table.font", localFont);
        UIManager.put("TableHeader.font", localFont);
        UIManager.put("TextField.font", localFont);
        UIManager.put("PasswordField.font", localFont);
        UIManager.put("TextArea.font", localFont);
        UIManager.put("TextPane.font", localFont);
        UIManager.put("EditorPane.font", localFont);
        UIManager.put("TitledBorder.font", localFont);
        UIManager.put("ToolBar.font", localFont);
        UIManager.put("ToolTip.font", localFont);
        UIManager.put("Tree.font", localFont);
        new SCB200Frame(this);
    }
}
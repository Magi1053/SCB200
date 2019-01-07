package com.dalite.scb200;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SCB200MessageBox extends JDialog implements ActionListener {
    public SCB200MessageBox(Frame paramFrame, String paramString1, String paramString2) {
        super(paramFrame, paramString1, true);


        JButton localJButton = new JButton("OK");
        localJButton.addActionListener(this);
        JPanel localJPanel = new JPanel();
        localJPanel.add(localJButton);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new Label(paramString2), "Center");
        getContentPane().add(localJPanel, "South");


        setBackground(Color.lightGray);
        pack();
        setResizable(false);

        Dimension localDimension1 = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension localDimension2 = getSize();
        if (localDimension2.height > localDimension1.height)
            localDimension2.height = localDimension1.height;
        if (localDimension2.width > localDimension1.width)
            localDimension2.width = localDimension1.width;
        setLocation((localDimension1.width - localDimension2.width) / 2, (localDimension1.height - localDimension2.height) / 2);

        localJButton.requestFocus();
        show();
    }

    public void actionPerformed(ActionEvent paramActionEvent) {
        dispose();
    }
}

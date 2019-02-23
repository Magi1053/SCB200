package com.dalite.scb200;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SCB200NetworkSettings extends JDialog implements ActionListener {
    private JFrame fFrame;
    private SCB200Server fServer;
    private JLabel fLabelLocation;
    private SCB200LimitedTextField fTextLocation;
    private JRadioButton fRadioDHCP;
    private JRadioButton fRadioExplicit;
    private ButtonGroup fButtonGroup;
    private JLabel fLabelIPAddress;
    private JLabel fLabelSubnetMask;
    private SCB200LimitedTextField fTextIPAddress;
    private SCB200LimitedTextField fTextSubnetMask;
    private JButton fButtonOK;
    private JButton fButtonCancel;

    public SCB200NetworkSettings(JFrame paramJFrame, SCB200Server paramSCB200Server) {
        super(paramJFrame, "Network Settings", false);


        this.fFrame = paramJFrame;
        this.fServer = paramSCB200Server;


        this.fLabelLocation = new JLabel("Location");
        this.fTextLocation = new SCB200LimitedTextField(20, 20);
        this.fRadioDHCP = new JRadioButton("Obtain network settings from DHCP server");
        this.fRadioDHCP.addActionListener(this);
        this.fRadioExplicit = new JRadioButton("Specify explicit network settings");
        this.fRadioExplicit.addActionListener(this);
        this.fButtonGroup = new ButtonGroup();
        this.fButtonGroup.add(this.fRadioDHCP);
        this.fButtonGroup.add(this.fRadioExplicit);
        this.fLabelIPAddress = new JLabel("IP Address");
        this.fLabelSubnetMask = new JLabel("Subnet Mask");
        this.fTextIPAddress = new SCB200LimitedTextField(15, 20);
        this.fTextSubnetMask = new SCB200LimitedTextField(15, 20);
        this.fButtonOK = new JButton("OK");
        this.fButtonOK.addActionListener(this);
        this.fButtonCancel = new JButton("Cancel");
        this.fButtonCancel.addActionListener(this);


        GridBagConstraints localGridBagConstraints = new GridBagConstraints();
        localGridBagConstraints.weightx = 1.0D;
        localGridBagConstraints.weighty = 1.0D;
        localGridBagConstraints.anchor = 17;
        localGridBagConstraints.fill = 0;
        localGridBagConstraints.insets = new Insets(1, 1, 1, 1);

        JPanel localJPanel1 = new JPanel(new GridLayout(1, 2));
        localJPanel1.add(this.fButtonOK);
        localJPanel1.add(this.fButtonCancel);

        JPanel localJPanel2 = new JPanel(new GridBagLayout());
        int i = 0;
        addDialogItem(localJPanel2, localGridBagConstraints, this.fLabelLocation, 0, i, 1, 1);
        addDialogItem(localJPanel2, localGridBagConstraints, this.fTextLocation, 1, i, 1, 1);
        i++;
        addDialogItem(localJPanel2, localGridBagConstraints, this.fRadioDHCP, 0, i, 2, 1);
        i++;
        addDialogItem(localJPanel2, localGridBagConstraints, this.fRadioExplicit, 0, i, 2, 1);
        i++;
        addDialogItem(localJPanel2, localGridBagConstraints, this.fLabelIPAddress, 0, i, 1, 1);
        addDialogItem(localJPanel2, localGridBagConstraints, this.fTextIPAddress, 1, i, 1, 1);
        i++;
        addDialogItem(localJPanel2, localGridBagConstraints, this.fLabelSubnetMask, 0, i, 1, 1);
        addDialogItem(localJPanel2, localGridBagConstraints, this.fTextSubnetMask, 1, i, 1, 1);
        i++;
        addDialogItem(localJPanel2, localGridBagConstraints, new JLabel(" "), 0, i, 1, 1);
        i++;
        localGridBagConstraints.anchor = 13;
        addDialogItem(localJPanel2, localGridBagConstraints, localJPanel1, 1, i, 1, 1);
        getContentPane().add(localJPanel2);


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
    }


    private void addDialogItem(JPanel paramJPanel, GridBagConstraints paramGridBagConstraints, Component paramComponent, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        paramGridBagConstraints.gridx = paramInt1;
        paramGridBagConstraints.gridy = paramInt2;
        paramGridBagConstraints.gridwidth = paramInt3;
        paramGridBagConstraints.gridheight = paramInt4;
        paramJPanel.add(paramComponent, paramGridBagConstraints);
    }


    public void show() {
        super.show();
        this.fTextLocation.setText(this.fServer.getLocation());
        boolean bool = this.fServer.isDHCP();
        this.fRadioDHCP.setSelected(bool);
        this.fRadioExplicit.setSelected(!bool);
        this.fTextIPAddress.setText(this.fServer.getIPAddress());
        this.fTextSubnetMask.setText(this.fServer.getSubnetMask());
        refreshControls();
    }


    private void refreshControls() {
        boolean bool = this.fRadioExplicit.isSelected();
        this.fLabelIPAddress.setEnabled(bool);
        this.fLabelSubnetMask.setEnabled(bool);
        this.fTextIPAddress.setEnabled(bool);
        this.fTextSubnetMask.setEnabled(bool);
    }

    public void actionPerformed(ActionEvent paramActionEvent) {
        Object localObject = paramActionEvent.getSource();
        if (localObject == this.fRadioDHCP) {
            refreshControls();
        } else if (localObject == this.fRadioExplicit) {
            refreshControls();
        } else if (localObject == this.fButtonOK) {
            this.fServer.setLocation(this.fTextLocation.getText());
            if (this.fRadioDHCP.isSelected()) {
                this.fServer.setIPAddress("0.0.0.0");
                this.fServer.setSubnetMask("255.255.255.0");
            } else {
                this.fServer.setIPAddress(this.fTextIPAddress.getText());
                this.fServer.setSubnetMask(this.fTextSubnetMask.getText());
            }
            this.fServer.setSerialFlash();
            this.fServer.setReset();
            hide();
        } else if (localObject == this.fButtonCancel) {
            hide();
        }
    }
}

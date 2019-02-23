package com.dalite.scb200;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class SCB200DetailStatus extends JDialog implements ActionListener {
    private SCB200Server fServer;
    private int fDeviceCount;
    private JLabel fLabelDeviceID;
    private JLabel fLabelOnline;
    private JLabel fLabelTargetDensity;
    private JLabel fLabelRollerDiameter;
    private JLabel fLabelSlackWrap;
    private JLabel fLabelScreenThickness;
    private JLabel fLabelSensorStatus;
    private JLabel fLabelRelayStatus;
    private JLabel fLabelCurrentTarget;
    private JLabel[] fLabelDevice;
    private SCB200Indicator[] fIndicatorOnline;
    private JTextField[] fTextTargetDensity;
    private JTextField[] fTextRollerDiameter;
    private JTextField[] fTextSlackWrap;
    private JTextField[] fTextScreenThickness;
    private JTextField[] fTextSensorStatus;
    private JTextField[] fTextRelayStatus;
    private JTextField[] fTextCurrentTarget;
    private JButton fButtonClose;

    public SCB200DetailStatus(JFrame paramJFrame, SCB200Server paramSCB200Server, int paramInt1, Image paramImage1, Image paramImage2, int paramInt2) {
        super(paramJFrame, "Detail Status", false);

        this.fServer = paramSCB200Server;
        this.fDeviceCount = paramInt1;


        Font localFont = new Font("SansSerif", Font.BOLD, 11);
        this.fLabelDeviceID = new JLabel("Device ID");
        this.fLabelDeviceID.setFont(localFont);
        this.fLabelOnline = new JLabel("Online");
        this.fLabelTargetDensity = new JLabel("Target Density");
        this.fLabelRollerDiameter = new JLabel("Roller Diameter");
        this.fLabelSlackWrap = new JLabel("Slack Length");
        this.fLabelScreenThickness = new JLabel("Screen Thickness");
        this.fLabelSensorStatus = new JLabel("Sensor Status");
        this.fLabelRelayStatus = new JLabel("Relay Status");
        this.fLabelCurrentTarget = new JLabel("Current Target");

        this.fLabelDevice = new JLabel[this.fDeviceCount];
        this.fIndicatorOnline = new SCB200Indicator[this.fDeviceCount];
        this.fTextTargetDensity = new JTextField[this.fDeviceCount];
        this.fTextRollerDiameter = new JTextField[this.fDeviceCount];
        this.fTextSlackWrap = new JTextField[this.fDeviceCount];
        this.fTextScreenThickness = new JTextField[this.fDeviceCount];
        this.fTextSensorStatus = new JTextField[this.fDeviceCount];
        this.fTextRelayStatus = new JTextField[this.fDeviceCount];
        this.fTextCurrentTarget = new JTextField[this.fDeviceCount];

        for (int i = 0; i < this.fDeviceCount; i++) {
            if (i == 0) {
                this.fLabelDevice[i] = new JLabel("M");
            } else
                this.fLabelDevice[i] = new JLabel("S" + i);
            this.fLabelDevice[i].setFont(localFont);
            this.fLabelDevice[i].setHorizontalAlignment(SwingConstants.CENTER);
            this.fLabelDevice[i].setFont(localFont);
            this.fIndicatorOnline[i] = new SCB200Indicator(paramImage1, paramImage2, true);
            this.fTextTargetDensity[i] = new JTextField(6);
            this.fTextTargetDensity[i].setEditable(false);
            this.fTextTargetDensity[i].setHorizontalAlignment(0);
            this.fTextRollerDiameter[i] = new JTextField(6);
            this.fTextRollerDiameter[i].setEditable(false);
            this.fTextRollerDiameter[i].setHorizontalAlignment(0);
            this.fTextSlackWrap[i] = new JTextField(6);
            this.fTextSlackWrap[i].setEditable(false);
            this.fTextSlackWrap[i].setHorizontalAlignment(0);
            this.fTextScreenThickness[i] = new JTextField(6);
            this.fTextScreenThickness[i].setEditable(false);
            this.fTextScreenThickness[i].setHorizontalAlignment(0);
            this.fTextSensorStatus[i] = new JTextField(6);
            this.fTextSensorStatus[i].setEditable(false);
            this.fTextSensorStatus[i].setHorizontalAlignment(0);
            this.fTextRelayStatus[i] = new JTextField(6);
            this.fTextRelayStatus[i].setEditable(false);
            this.fTextRelayStatus[i].setHorizontalAlignment(0);
            this.fTextCurrentTarget[i] = new JTextField(6);
            this.fTextCurrentTarget[i].setEditable(false);
            this.fTextCurrentTarget[i].setHorizontalAlignment(0);
        }
        this.fButtonClose = new JButton("Close");
        this.fButtonClose.addActionListener(this);


        GridBagConstraints localGridBagConstraints = new GridBagConstraints();
        localGridBagConstraints.weightx = 1.0D;
        localGridBagConstraints.weighty = 1.0D;
        localGridBagConstraints.anchor = 17;
        localGridBagConstraints.fill = 0;
        localGridBagConstraints.insets = new Insets(1, 1, 1, 1);

        JPanel localJPanel1 = new JPanel(new GridBagLayout());
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelDeviceID, 1, 1, 1, 1);
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelOnline, 1, 2, 1, 1);
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelTargetDensity, 1, 3, 1, 1);
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelRollerDiameter, 1, 4, 1, 1);
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelSlackWrap, 1, 5, 1, 1);
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelScreenThickness, 1, 6, 1, 1);
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelSensorStatus, 1, 7, 1, 1);
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelRelayStatus, 1, 8, 1, 1);
        addDialogItem(localJPanel1, localGridBagConstraints, this.fLabelCurrentTarget, 1, 9, 1, 1);

        JPanel localJPanel2 = new JPanel(new GridLayout(9, this.fDeviceCount, 1, 1));
        localGridBagConstraints.anchor = 10;
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fLabelDevice[j]);
        localGridBagConstraints.anchor = 17;
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fIndicatorOnline[j]);
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fTextTargetDensity[j]);
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fTextRollerDiameter[j]);
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fTextSlackWrap[j]);
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fTextScreenThickness[j]);
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fTextSensorStatus[j]);
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fTextRelayStatus[j]);
        for (int j = 0; j < this.fDeviceCount; j++)
            localJPanel2.add(this.fTextCurrentTarget[j]);
        addDialogItem(localJPanel1, localGridBagConstraints, localJPanel2, 2, 1, this.fDeviceCount, 9);
        addDialogItem(localJPanel1, localGridBagConstraints, new JLabel(" "), 1, 10, 1, 1);
        localGridBagConstraints.anchor = 13;
        addDialogItem(localJPanel1, localGridBagConstraints, this.fButtonClose, this.fDeviceCount, 11, 2, 1);

        getContentPane().add(localJPanel1);


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
        refreshDeviceData();
    }


    public void refreshDeviceData() {
        for (int i = 0; i < this.fDeviceCount; i++) {
            boolean bool = this.fServer.isDeviceEnabled(i);
            this.fIndicatorOnline[i].setStatus(bool);
            if (bool) {
                this.fTextTargetDensity[i].setBackground(Color.WHITE);
                this.fTextTargetDensity[i].setText(new DecimalFormat("#0").format(this.fServer.getTargetDensity(i)));
                this.fTextRollerDiameter[i].setBackground(Color.WHITE);
                this.fTextRollerDiameter[i].setText(new DecimalFormat("#0").format(this.fServer.getRollerDiameter(i)));
                this.fTextSlackWrap[i].setBackground(Color.WHITE);
                this.fTextSlackWrap[i].setText(new DecimalFormat("#0").format(this.fServer.getSlackWrap(i)));
                this.fTextScreenThickness[i].setBackground(Color.WHITE);
                this.fTextScreenThickness[i].setText(new DecimalFormat("#0.0000").format(this.fServer.getScreenThickness(i)));
                this.fTextSensorStatus[i].setBackground(Color.WHITE);
                this.fTextSensorStatus[i].setText(this.fServer.getSensorStatus(i));
                this.fTextRelayStatus[i].setBackground(Color.WHITE);
                this.fTextRelayStatus[i].setText(this.fServer.getRelayStatus(i));
                this.fTextCurrentTarget[i].setBackground(Color.WHITE);
                this.fTextCurrentTarget[i].setText(new DecimalFormat("#0").format(this.fServer.getTargetPosition(i)));

            } else {

                this.fTextTargetDensity[i].setBackground(Color.LIGHT_GRAY);
                this.fTextTargetDensity[i].setText("");
                this.fTextRollerDiameter[i].setBackground(Color.LIGHT_GRAY);
                this.fTextRollerDiameter[i].setText("");
                this.fTextSlackWrap[i].setBackground(Color.LIGHT_GRAY);
                this.fTextSlackWrap[i].setText("");
                this.fTextScreenThickness[i].setBackground(Color.LIGHT_GRAY);
                this.fTextScreenThickness[i].setText("");
                this.fTextSensorStatus[i].setBackground(Color.LIGHT_GRAY);
                this.fTextSensorStatus[i].setText("");
                this.fTextRelayStatus[i].setBackground(Color.LIGHT_GRAY);
                this.fTextRelayStatus[i].setText("");
                this.fTextCurrentTarget[i].setBackground(Color.LIGHT_GRAY);
                this.fTextCurrentTarget[i].setText("");
            }
        }
    }

    public void actionPerformed(ActionEvent paramActionEvent) {
        Object localObject = paramActionEvent.getSource();
        if (localObject == this.fButtonClose) {
            hide();
        }
    }
}

package com.dalite.scb200;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class SCB200Frame extends JFrame implements ActionListener, SCB200Refreshable, WindowListener {
    private static final int DEVICE_COUNT = 8;
    private static final long REFRESH_DELAY = 1000L;
    private static final long COMMAND_TIMEOUT = 10000L;
    private JApplet fApplet;
    private SCB200Server fServer;
    private SCB200RefreshTimer fTimer;
    private String fFirmwareVersion;
    private int fSelectedDevice;
    private SCB200DetailStatus fDetailStatus;
    private SCB200NetworkSettings fNetworkSettings;
    private JLabel fLabelProduct;
    private JLabel fLabelProgram;
    private JLabel fLabelCompany;
    private JLabel fLabelAddress;
    private JLabel fLabelCityStateZip;
    private JLabel fLabelPhone;
    private JLabel fLabelWebURL;
    private JLabel fLabelEmailURL;
    private JLabel fLabelNetworkSettings;
    private JLabel fLabelSystemTime;
    private JLabel fLabelLocation;
    private JLabel fLabelMACAddress;
    private JLabel fLabelDHCP;
    private JLabel fLabelIPAddress;
    private JLabel fLabelSubnetMask;
    private JLabel fLabelSCB200Settings;
    private JLabel fLabelDeviceID;
    private JLabel[] fLabelDevice;
    private JLabel fLabelOnline;
    private JLabel fLabelMotionDetection;
    private JLabel fLabelErrorCode;
    private JLabel fLabelACCurrent;
    private JLabel fLabelScreenHeight;
    private JLabel fLabelScreenWidth;
    private JLabel fLabelUpperLimit;
    private JLabel fLabelPosition;
    private JLabel fLabelLowerLimit;
    private JLabel fLabelControlPanel;
    private JLabel fLabelDeviceSelect;
    private JLabel fLabelBasicPosition;
    private JLabel fLabelAspectRatio;
    private JLabel fLabelCustomPosition;
    private JLabel fLabelDirectPositioning;
    private JTextField[] fTextErrorCode;
    private JTextField[] fTextACCurrent;
    private JTextField[] fTextScreenHeight;
    private JTextField[] fTextScreenWidth;
    private JTextField[] fTextUpperLimit;
    private JTextField[] fTextPosition;
    private JTextField[] fTextLowerLimit;
    private JTextField fTextSystemTime;
    private JTextField fTextLocation;
    private JTextField fTextMACAddress;
    private JTextField fTextDHCP;
    private JTextField fTextIPAddress;
    private JTextField fTextSubnetMask;
    private SCB200LimitedNumberField fTextDistance;
    private Image fImageLogo;
    private Image fImageOn;
    private Image fImageOff;
    private SCB200ImagePanel fImagePanelLogo;
    private SCB200Indicator[] fIndicatorOnline;
    private SCB200Indicator[] fIndicatorMotionDetection;
    private JButton fButtonChangeNetwork;
    private JButton fButtonUp;
    private JButton fButtonStop;
    private JButton fButtonDown;
    private JButton fButtonGet;
    private JButton fButtonSet;
    private JButton fButtonGo;
    private JButton fButtonDetailedStatus;
    private JComboBox fComboBoxDevice;
    private JComboBox fComboBoxAspectRatio;
    private JComboBox fComboBoxPositionType;
    private JComboBox fComboBoxPositionUnits;
    private JPanel fPanelMain;
    private JPanel fPanelIdentification;
    private JPanel fPanelAddress;
    private JPanel fPanelSCB200;

    public SCB200Frame(JApplet paramJApplet) {
        this.fApplet = paramJApplet;

        try {
            try {
                this.fImageLogo = ImageIO.read(new URL(this.fApplet.getDocumentBase(), "logo.gif"));
                this.fImageOn = ImageIO.read(new URL(this.fApplet.getDocumentBase(), "on.gif"));
                this.fImageOff = ImageIO.read(new URL(this.fApplet.getDocumentBase(), "off.gif"));
            } catch (MalformedURLException localMalformedURLException) {
                localMalformedURLException.printStackTrace(System.out);
            }
            MediaTracker localMediaTracker = new MediaTracker(this);
            localMediaTracker.addImage(this.fImageLogo, 0);
            localMediaTracker.addImage(this.fImageOn, 1);
            localMediaTracker.addImage(this.fImageOff, 2);
            try {
                localMediaTracker.waitForID(0);
                localMediaTracker.waitForID(1);
                localMediaTracker.waitForID(2);
            } catch (InterruptedException localInterruptedException) {
                System.out.println("Error loading image");
            }
            this.fServer = new SCB200Server(this.fApplet.getCodeBase().getHost());
            createUserInterface();
            this.fTimer = new SCB200RefreshTimer(this, 1000L);
            this.fTimer.start();
        } catch (Exception localException) {
            System.out.println("Unable to initialize server");
            localException.printStackTrace(System.out);
        }
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(this);
        refreshControls();
    }


    public void destroy() {
        this.fTimer.terminate();
        try {
            this.fServer.close();
        } catch (Exception localException) {
            System.out.println(localException.toString());
        }
    }


    private void createUserInterface() {
        this.fDetailStatus = new SCB200DetailStatus(this, this.fServer, 8, this.fImageOn, this.fImageOff, 17);
        this.fNetworkSettings = new SCB200NetworkSettings(this, this.fServer);


        Font localFont = new Font("SansSerif", Font.BOLD, 11);
        this.fLabelProduct = new JLabel("NET-200");
        this.fLabelProduct.setFont(localFont);
        this.fLabelProduct.setHorizontalAlignment(SwingConstants.CENTER);
        this.fLabelProgram = new JLabel("Screen Controller");
        this.fLabelProgram.setFont(localFont);
        this.fLabelProgram.setHorizontalAlignment(SwingConstants.CENTER);
        this.fLabelCompany = new JLabel("Da-Lite Screen Company");
        this.fLabelCompany.setFont(localFont);
        this.fLabelCompany.setHorizontalAlignment(SwingConstants.RIGHT);
        this.fLabelAddress = new JLabel("3100 North Detroit Street");
        this.fLabelAddress.setFont(localFont);
        this.fLabelAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        this.fLabelCityStateZip = new JLabel("Warsaw, IN 46581");
        this.fLabelCityStateZip.setFont(localFont);
        this.fLabelCityStateZip.setHorizontalAlignment(SwingConstants.RIGHT);
        this.fLabelPhone = new JLabel("(219) 267-8101");
        this.fLabelPhone.setFont(localFont);
        this.fLabelPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        this.fLabelWebURL = new JLabel("www.dalite.com");
        this.fLabelWebURL.setFont(localFont);
        this.fLabelWebURL.setForeground(Color.BLUE);
        this.fLabelWebURL.setHorizontalAlignment(SwingConstants.RIGHT);
        this.fLabelEmailURL = new JLabel("support@dalite.com");
        this.fLabelEmailURL.setFont(localFont);
        this.fLabelEmailURL.setForeground(Color.BLUE);
        this.fLabelEmailURL.setHorizontalAlignment(SwingConstants.RIGHT);
        this.fLabelNetworkSettings = new JLabel("NETWORK SETTINGS");
        this.fLabelNetworkSettings.setFont(localFont);
        this.fLabelSystemTime = new JLabel("System Time");
        this.fLabelLocation = new JLabel("Location");
        this.fLabelMACAddress = new JLabel("MAC Address");
        this.fLabelDHCP = new JLabel("DHCP");
        this.fLabelIPAddress = new JLabel("IP Address");
        this.fLabelSubnetMask = new JLabel("Subnet Mask");
        this.fLabelSCB200Settings = new JLabel("SCB-200 DEVICES");
        this.fLabelSCB200Settings.setFont(localFont);
        this.fLabelDeviceID = new JLabel("Device ID");
        this.fLabelDeviceID.setFont(localFont);
        this.fLabelDevice = new JLabel[8];
        this.fLabelDevice[0] = new JLabel("M");
        this.fLabelDevice[0].setFont(localFont);
        this.fLabelDevice[0].setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < 8; i++) {
            this.fLabelDevice[i] = new JLabel("S" + i);
            this.fLabelDevice[i].setFont(localFont);
            this.fLabelDevice[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
        this.fLabelOnline = new JLabel("Online");
        this.fLabelMotionDetection = new JLabel("Motion Detection");
        this.fLabelErrorCode = new JLabel("Error Code");
        this.fLabelACCurrent = new JLabel("AC Current (Amp)");
        this.fLabelScreenHeight = new JLabel("Screen Height (Inch)");
        this.fLabelScreenWidth = new JLabel("Screen Width (Inch)");
        this.fLabelUpperLimit = new JLabel("Upper Limit (Inch)");
        this.fLabelPosition = new JLabel("Position (Inch)");
        this.fLabelLowerLimit = new JLabel("Lower Limit (Inch)");
        this.fLabelControlPanel = new JLabel("CONTROL PANEL");
        this.fLabelControlPanel.setFont(localFont);
        this.fLabelDeviceSelect = new JLabel("Device");
        this.fLabelBasicPosition = new JLabel("Basic Position");
        this.fLabelAspectRatio = new JLabel("Aspect Ratio");
        this.fLabelCustomPosition = new JLabel("Custom Position");
        this.fLabelDirectPositioning = new JLabel("Direct Positioning");


        this.fTextErrorCode = new JTextField[8];
        this.fTextACCurrent = new JTextField[8];
        this.fTextScreenHeight = new JTextField[8];
        this.fTextScreenWidth = new JTextField[8];
        this.fTextUpperLimit = new JTextField[8];
        this.fTextPosition = new JTextField[8];
        this.fTextLowerLimit = new JTextField[8];
        for (int i = 0; i < 8; i++) {
            this.fTextErrorCode[i] = new JTextField(6);
            this.fTextErrorCode[i].setEditable(false);
            this.fTextErrorCode[i].setHorizontalAlignment(0);
            this.fTextACCurrent[i] = new JTextField(6);
            this.fTextACCurrent[i].setEditable(false);
            this.fTextACCurrent[i].setHorizontalAlignment(0);
            this.fTextScreenHeight[i] = new JTextField(6);
            this.fTextScreenHeight[i].setEditable(false);
            this.fTextScreenHeight[i].setHorizontalAlignment(0);
            this.fTextScreenWidth[i] = new JTextField(6);
            this.fTextScreenWidth[i].setEditable(false);
            this.fTextScreenWidth[i].setHorizontalAlignment(0);
            this.fTextUpperLimit[i] = new JTextField(6);
            this.fTextUpperLimit[i].setEditable(false);
            this.fTextUpperLimit[i].setHorizontalAlignment(0);
            this.fTextPosition[i] = new JTextField(6);
            this.fTextPosition[i].setEditable(false);
            this.fTextPosition[i].setHorizontalAlignment(0);
            this.fTextLowerLimit[i] = new JTextField(6);
            this.fTextLowerLimit[i].setEditable(false);
            this.fTextLowerLimit[i].setHorizontalAlignment(0);
        }
        this.fTextSystemTime = new JTextField();
        this.fTextSystemTime.setEditable(false);
        this.fTextSystemTime.setBackground(Color.WHITE);
        this.fTextLocation = new JTextField();
        this.fTextLocation.setEditable(false);
        this.fTextLocation.setBackground(Color.WHITE);
        this.fTextMACAddress = new JTextField();
        this.fTextMACAddress.setEditable(false);
        this.fTextMACAddress.setBackground(Color.WHITE);
        this.fTextDHCP = new JTextField();
        this.fTextDHCP.setEditable(false);
        this.fTextDHCP.setBackground(Color.WHITE);
        this.fTextIPAddress = new JTextField();
        this.fTextIPAddress.setEditable(false);
        this.fTextIPAddress.setBackground(Color.WHITE);
        this.fTextSubnetMask = new JTextField();
        this.fTextSubnetMask.setEditable(false);
        this.fTextSubnetMask.setBackground(Color.WHITE);
        this.fTextDistance = new SCB200LimitedNumberField(4, 4);
        this.fTextDistance.setBackground(Color.WHITE);


        this.fImagePanelLogo = new SCB200ImagePanel(this.fImageLogo);


        this.fIndicatorOnline = new SCB200Indicator[8];
        this.fIndicatorMotionDetection = new SCB200Indicator[8];
        for (int i = 0; i < 8; i++) {
            this.fIndicatorOnline[i] = new SCB200Indicator(this.fImageOn, this.fImageOff, true);
            this.fIndicatorMotionDetection[i] = new SCB200Indicator(this.fImageOn, this.fImageOff, false);
        }


        this.fButtonChangeNetwork = new JButton("Change Settings");
        this.fButtonChangeNetwork.addActionListener(this);
        this.fButtonUp = new JButton("Up");
        this.fButtonUp.addActionListener(this);
        this.fButtonStop = new JButton("Stop");
        this.fButtonStop.addActionListener(this);
        this.fButtonDown = new JButton("Down");
        this.fButtonDown.addActionListener(this);
        this.fButtonGet = new JButton("Go");
        this.fButtonGet.addActionListener(this);
        this.fButtonSet = new JButton("Set");
        this.fButtonSet.addActionListener(this);
        this.fButtonGo = new JButton("Go");
        this.fButtonGo.addActionListener(this);
        this.fButtonDetailedStatus = new JButton("Show Detailed Status");
        this.fButtonDetailedStatus.addActionListener(this);


        this.fComboBoxDevice = new JComboBox();
        this.fComboBoxDevice.addItem("Master");
        for (int i = 1; i < 8; i++) {
            this.fComboBoxDevice.addItem("Slave " + i);
        }

        this.fComboBoxAspectRatio = new JComboBox();
        this.fComboBoxAspectRatio.addItem("1:1");
        this.fComboBoxAspectRatio.addItem("1.25:1");
        this.fComboBoxAspectRatio.addItem("1.33:1 (4x3)");
        this.fComboBoxAspectRatio.addItem("1.66:1 (5x4)");
        this.fComboBoxAspectRatio.addItem("1.78:1 (16x9)");
        this.fComboBoxAspectRatio.addItem("Custom 1");
        this.fComboBoxAspectRatio.addItem("Custom 2");
        this.fComboBoxAspectRatio.addItem("Custom 3");
        this.fComboBoxAspectRatio.addItem("Custom 4");
        this.fComboBoxAspectRatio.addItem("Custom 5");

        this.fComboBoxPositionType = new JComboBox();
        this.fComboBoxPositionType.addItem("Go to position");
        this.fComboBoxPositionType.addItem("Raise by");
        this.fComboBoxPositionType.addItem("Lower by");

        this.fComboBoxPositionUnits = new JComboBox();
        this.fComboBoxPositionUnits.addItem("Inch");
        this.fComboBoxPositionUnits.addItem("Cm");
        this.fComboBoxPositionUnits.addItem("Mm");


        GridBagConstraints localGridBagConstraints = new GridBagConstraints();
        localGridBagConstraints.fill = 2;
        localGridBagConstraints.insets = new Insets(1, 1, 1, 1);


        this.fPanelIdentification = new JPanel(new GridBagLayout());
        localGridBagConstraints.weightx = 1.0D;
        localGridBagConstraints.weighty = 1.0D;
        localGridBagConstraints.anchor = 10;
        addDialogItem(this.fPanelIdentification, localGridBagConstraints, this.fImagePanelLogo, 1, 1, 1, 1);
        localGridBagConstraints.anchor = 17;
        addDialogItem(this.fPanelIdentification, localGridBagConstraints, this.fLabelProduct, 1, 2, 1, 1);
        addDialogItem(this.fPanelIdentification, localGridBagConstraints, this.fLabelProgram, 1, 3, 1, 1);


        localGridBagConstraints.anchor = 13;
        this.fPanelAddress = new JPanel(new GridBagLayout());
        localGridBagConstraints.weightx = 1.0D;
        localGridBagConstraints.weighty = 1.0D;
        addDialogItem(this.fPanelAddress, localGridBagConstraints, this.fLabelCompany, 1, 1, 1, 1);
        addDialogItem(this.fPanelAddress, localGridBagConstraints, this.fLabelAddress, 1, 2, 1, 1);
        addDialogItem(this.fPanelAddress, localGridBagConstraints, this.fLabelCityStateZip, 1, 3, 1, 1);
        addDialogItem(this.fPanelAddress, localGridBagConstraints, this.fLabelPhone, 1, 4, 1, 1);
        addDialogItem(this.fPanelAddress, localGridBagConstraints, this.fLabelWebURL, 1, 5, 1, 1);
        addDialogItem(this.fPanelAddress, localGridBagConstraints, this.fLabelEmailURL, 1, 6, 1, 1);


        this.fPanelSCB200 = new JPanel(new GridBagLayout());
        localGridBagConstraints.anchor = 17;
        localGridBagConstraints.fill = 2;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelSCB200Settings, 1, 0, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelOnline, 1, 1, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelMotionDetection, 1, 2, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelErrorCode, 1, 3, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelACCurrent, 1, 4, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelScreenHeight, 1, 5, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelScreenWidth, 1, 6, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelUpperLimit, 1, 7, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelPosition, 1, 8, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelLowerLimit, 1, 9, 1, 1);
        JPanel localJPanel1 = new JPanel(new GridLayout(10, 8, 1, 1));
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fLabelDevice[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fIndicatorOnline[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fIndicatorMotionDetection[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fTextErrorCode[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fTextACCurrent[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fTextScreenHeight[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fTextScreenWidth[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fTextUpperLimit[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fTextPosition[j]);
        for (int j = 0; j < 8; j++)
            localJPanel1.add(this.fTextLowerLimit[j]);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, localJPanel1, 2, 0, 8, 10);

        int j = 12;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, new JLabel(" "), 1, j, 1, 1);
        j++;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelNetworkSettings, 1, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fButtonChangeNetwork, 2, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelControlPanel, 5, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelDeviceSelect, 7, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fComboBoxDevice, 8, j, 2, 1);
        j++;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelSystemTime, 1, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fTextSystemTime, 2, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelBasicPosition, 5, j, 2, 1);
        JPanel localJPanel2 = new JPanel(new GridLayout(1, 3));
        localJPanel2.add(this.fButtonUp);
        localJPanel2.add(this.fButtonStop);
        localJPanel2.add(this.fButtonDown);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, localJPanel2, 8, j, 2, 1);
        j++;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelLocation, 1, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fTextLocation, 2, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelAspectRatio, 5, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fComboBoxAspectRatio, 7, j, 2, 1);


        JPanel localJPanel3 = new JPanel(new GridLayout(1, 2));
        localJPanel3.add(this.fButtonGet);
        localJPanel3.add(this.fButtonSet);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, localJPanel3, 9, j, 1, 1);
        j++;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelMACAddress, 1, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fTextMACAddress, 2, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelCustomPosition, 5, j, 2, 1);
        j++;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelDHCP, 1, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fTextDHCP, 2, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fComboBoxPositionType, 5, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fTextDistance, 7, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fComboBoxPositionUnits, 8, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fButtonGo, 9, j, 1, 1);
        j++;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelIPAddress, 1, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fTextIPAddress, 2, j, 2, 1);
        j++;
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fLabelSubnetMask, 1, j, 1, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fTextSubnetMask, 2, j, 2, 1);
        addDialogItem(this.fPanelSCB200, localGridBagConstraints, this.fButtonDetailedStatus, 7, j, 3, 1);


        this.fPanelMain = new JPanel(new GridBagLayout());
        j = 0;
        localGridBagConstraints.anchor = 17;
        localGridBagConstraints.fill = 0;
        localGridBagConstraints.weightx = 0.0D;
        addDialogItem(this.fPanelMain, localGridBagConstraints, this.fPanelIdentification, 1, j, 1, 1);
        localGridBagConstraints.anchor = 13;
        localGridBagConstraints.fill = 2;
        localGridBagConstraints.weightx = 1.0D;
        addDialogItem(this.fPanelMain, localGridBagConstraints, this.fPanelAddress, 2, j, 1, 1);
        j++;
        addDialogItem(this.fPanelMain, localGridBagConstraints, new JLabel(" "), 1, j, 2, 1);
        j++;
        localGridBagConstraints.anchor = 17;
        addDialogItem(this.fPanelMain, localGridBagConstraints, this.fPanelSCB200, 1, j, 2, 1);


        getContentPane().add(this.fPanelMain);
        pack();
        setTitle("Da-Lite NET-200 Screen Controller");
        setVisible(true);
    }


    private void addDialogItem(JPanel paramJPanel, GridBagConstraints paramGridBagConstraints, Component paramComponent, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        paramGridBagConstraints.gridx = paramInt1;
        paramGridBagConstraints.gridy = paramInt2;
        paramGridBagConstraints.gridwidth = paramInt3;
        paramGridBagConstraints.gridheight = paramInt4;
        paramJPanel.add(paramComponent, paramGridBagConstraints);
    }


    public void refreshControls() {
        boolean bool = false;

        for (int i = 0; i < 8; i++) {
            bool = this.fServer.isDeviceEnabled(i);
            this.fIndicatorOnline[i].setStatus(bool);
            int j = (this.fServer.getRelayStatus(i).trim().compareTo("UP") == 0) || (this.fServer.getRelayStatus(i).trim().compareTo("DN") == 0) ? 1 : 0;


            this.fIndicatorMotionDetection[i].setStatus((bool) && (j != 0));
        }
        bool = this.fServer.isDeviceEnabled(this.fComboBoxDevice.getSelectedIndex());
        this.fTextDistance.setEnabled(bool);
        this.fButtonUp.setEnabled(bool);
        this.fButtonStop.setEnabled(bool);
        this.fButtonDown.setEnabled(bool);
        this.fButtonGet.setEnabled(bool);
        this.fButtonSet.setEnabled((bool) && (this.fComboBoxAspectRatio.getSelectedIndex() >= 5));
        this.fButtonGo.setEnabled(bool);
        this.fButtonDetailedStatus.setEnabled(bool);
        this.fComboBoxAspectRatio.setEnabled(bool);
        this.fComboBoxPositionType.setEnabled(bool);
        this.fComboBoxPositionUnits.setEnabled(bool);
    }


    public void refreshDeviceData() {
        if (!this.fServer.isConnected()) {
            new SCB200MessageBox(this, "Error", "Network connection lost").show();
            hide();
            dispose();
        }
        for (int i = 0; i < 8; i++) {
            boolean bool = this.fServer.isDeviceEnabled(i);
            if (bool) {
                this.fTextErrorCode[i].setBackground(Color.WHITE);
                this.fTextACCurrent[i].setBackground(Color.WHITE);
                this.fTextScreenHeight[i].setBackground(Color.WHITE);
                this.fTextScreenWidth[i].setBackground(Color.WHITE);
                this.fTextUpperLimit[i].setBackground(Color.WHITE);
                this.fTextPosition[i].setBackground(Color.WHITE);
                this.fTextLowerLimit[i].setBackground(Color.WHITE);
                double d = this.fServer.getAC(i);
                this.fTextACCurrent[i].setText(d == -1.0D ? "0.0" : new DecimalFormat("#0.0").format(d));
                this.fTextErrorCode[i].setText(this.fServer.getACErrorCode(i));
                this.fTextScreenHeight[i].setText(new DecimalFormat("#0.00").format(this.fServer.getScreenHeight(i) / 25.4D));
                this.fTextScreenWidth[i].setText(new DecimalFormat("#0.00").format(this.fServer.getScreenWidth(i) / 25.4D));
                this.fTextUpperLimit[i].setText(new DecimalFormat("#0.00").format(this.fServer.getUpperLimit(i) / 25.4D));
                this.fTextPosition[i].setText(new DecimalFormat("#0.00").format(this.fServer.getScreenPosition(i) / 25.4D));
                this.fTextLowerLimit[i].setText(new DecimalFormat("#0.00").format(this.fServer.getLowerLimit(i) / 25.4D));
            } else {
                this.fTextErrorCode[i].setBackground(Color.LIGHT_GRAY);
                this.fTextErrorCode[i].setText("");
                this.fTextACCurrent[i].setBackground(Color.LIGHT_GRAY);
                this.fTextACCurrent[i].setText("");
                this.fTextScreenHeight[i].setBackground(Color.LIGHT_GRAY);
                this.fTextScreenHeight[i].setText("");
                this.fTextScreenWidth[i].setBackground(Color.LIGHT_GRAY);
                this.fTextScreenWidth[i].setText("");
                this.fTextUpperLimit[i].setBackground(Color.LIGHT_GRAY);
                this.fTextUpperLimit[i].setText("");
                this.fTextPosition[i].setBackground(Color.LIGHT_GRAY);
                this.fTextPosition[i].setText("");
                this.fTextLowerLimit[i].setBackground(Color.LIGHT_GRAY);
                this.fTextLowerLimit[i].setText("");
            }
        }
        this.fTextSystemTime.setText(new SimpleDateFormat("MM/dd/yyyy hh:mm aa").format(this.fServer.getSystemTime()));
        this.fTextLocation.setText(this.fServer.getLocation());
        this.fTextMACAddress.setText(this.fServer.getMACAddress());
        this.fTextDHCP.setText(this.fServer.isDHCP() ? "Enabled" : "Disabled");
        this.fTextIPAddress.setText(this.fServer.getIPAddress());
        this.fTextSubnetMask.setText(this.fServer.getSubnetMask());
        refreshControls();
        if (this.fDetailStatus.isVisible()) {
            this.fDetailStatus.refreshDeviceData();
        }
    }


    public void windowActivated(WindowEvent paramWindowEvent) {
    }


    public void windowClosed(WindowEvent paramWindowEvent) {
    }


    public void actionPerformed(ActionEvent paramActionEvent) {
        this.fTimer.hold();
        Object localObject = paramActionEvent.getSource();
        int i = this.fComboBoxDevice.getSelectedIndex();
        if (localObject == this.fButtonChangeNetwork) {
            this.fNetworkSettings.show();
        } else if (localObject == this.fButtonUp) {
            this.fServer.setRelayStatus(i, "UP");
        } else if (localObject == this.fButtonStop) {
            this.fServer.setRelayStatus(i, "ST");
        } else if (localObject == this.fButtonDown) {
            this.fServer.setRelayStatus(i, "DN");
        } else if (localObject == this.fButtonGet) {
            this.fServer.applyCustomAspectRatio(i, this.fComboBoxAspectRatio.getSelectedIndex());
        } else if (localObject == this.fButtonSet) {
            this.fServer.setCustomAspectRatio(i, this.fComboBoxAspectRatio.getSelectedIndex());
        } else if (localObject == this.fButtonGo) {
            double d1;
            switch (this.fComboBoxPositionUnits.getSelectedIndex()) {
                case 0:
                    d1 = 25.4D;
                    break;
                case 1:
                    d1 = 10.0D;
                    break;
                case 2:
                    d1 = 1.0D;
                    break;
                default:
                    d1 = 0.0D;
            }
            double d2;
            try {
                d2 = Double.parseDouble(this.fTextDistance.getText());
            } catch (NumberFormatException localNumberFormatException) {
                d2 = 0.0D;
            }
            int j = (int) (d2 * d1);
            switch (this.fComboBoxPositionType.getSelectedIndex()) {
                case 0:
                    this.fServer.setScreenPosition(i, j);
                    break;
                case 1:
                    this.fServer.decrementScreenPosition(i, j);
                    break;
                case 2:
                    this.fServer.incrementScreenPosition(i, j);
            }
        } else if (localObject == this.fButtonDetailedStatus) {
            this.fDetailStatus.show();
        } else if (localObject == this.fComboBoxDevice) {
            refreshControls();
        } else if (localObject == this.fComboBoxAspectRatio) {
            refreshControls();
        }
        this.fTimer.unhold();
    }


    public void windowClosing(WindowEvent paramWindowEvent) {
        if (paramWindowEvent.getSource() == this) {
            this.fTimer.terminate();
            try {
                this.fServer.close();
            } catch (Exception localException) {
            }


            hide();
            dispose();
        }
    }

    public void windowDeactivated(WindowEvent paramWindowEvent) {
    }

    public void windowDeiconified(WindowEvent paramWindowEvent) {
    }

    public void windowIconified(WindowEvent paramWindowEvent) {
    }

    public void windowOpened(WindowEvent paramWindowEvent) {
    }
}

package com.dalite.scb200;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;


public class SCB200Server {
    private static final int TIMEOUT = 5000;
    private static final int PORT = 3001;
    private static final int BUFFER_SIZE = 512;
    private byte[] fBuffer;
    private Socket fSocket;
    private BufferedInputStream fInputStream;
    private OutputStream fOutputStream;
    private String[] fACErrorCode = new String[8];


    public SCB200Server(String paramString) throws Exception {
        this.fBuffer = new byte['\u0200'];
        this.fSocket = new Socket(paramString, 3001);
        this.fInputStream = new BufferedInputStream(this.fSocket.getInputStream());
        this.fOutputStream = this.fSocket.getOutputStream();
    }


    public boolean isConnected() {
        return this.fSocket.isConnected();
    }


    public void close() {
        try {
            this.fOutputStream.close();
            this.fInputStream.close();
            this.fSocket.close();
        } catch (Exception localException) {
        }
    }


    public void getAll(int paramInt) {
        try {
            String str = getResponse("$ " + paramInt + " GE AL");
        } catch (SCB200Exception localSCB200Exception) {
        }
    }


    public Date getSystemTime() {
        return new GregorianCalendar().getTime();
    }


    public boolean isDeviceEnabled(int paramInt) {
        boolean bool = getIntegerProperty(paramInt, "EN") == 1;
        return bool;
    }


    public String getLocation() {
        return getStringProperty(0, "LO");
    }


    public void setLocation(String paramString) {
        try {
            this.fOutputStream.write(new String("$ 0 SE LO " + paramString + "\r").getBytes());
        } catch (IOException localIOException) {
        }
    }


    public String getSoftwareVersion(int paramInt) {
        return getStringProperty(paramInt, "SV");
    }


    public int getTargetDensity(int paramInt) {
        return getIntegerProperty(paramInt, "TD");
    }


    public int getRollerDiameter(int paramInt) {
        return getIntegerProperty(paramInt, "RD");
    }


    public int getSlackWrap(int paramInt) {
        return getIntegerProperty(paramInt, "SL");
    }


    public double getScreenThickness(int paramInt) {
        return getDoubleProperty(paramInt, "ST");
    }


    public int getScreenWidth(int paramInt) {
        return getIntegerProperty(paramInt, "SW");
    }


    public int getScreenHeight(int paramInt) {
        return getIntegerProperty(paramInt, "SH");
    }


    public String getMACAddress() {
        return getStringProperty(0, "MA");
    }


    public String getSensorStatus(int paramInt) {
        return getStringProperty(paramInt, "SE");
    }


    public String getMasterSlaveStatus(int paramInt) {
        return getStringProperty(paramInt, "MS");
    }


    public String getRelayStatus(int paramInt) {
        return getStringProperty(paramInt, "RE");
    }


    public String getACErrorCode(int paramInt) {
        return this.fACErrorCode[paramInt];
    }


    public double getAC(int paramInt) {
        StringTokenizer localStringTokenizer = new StringTokenizer(getStringProperty(paramInt, "AC"), " ");
        double d;
        if (localStringTokenizer.hasMoreTokens()) {
            String str = localStringTokenizer.nextToken();
            try {
                d = Double.parseDouble(str);
            } catch (NumberFormatException localNumberFormatException) {
                d = -1.0D;
            }
            if (localStringTokenizer.hasMoreTokens()) {
                this.fACErrorCode[paramInt] = localStringTokenizer.nextToken();
            } else {
                this.fACErrorCode[paramInt] = "COMM";
            }
        } else {
            d = -1.0D;
            this.fACErrorCode[paramInt] = "COMM";
        }
        return d;
    }


    public int getUpperLimit(int paramInt) {
        return getIntegerProperty(paramInt, "UL");
    }


    public int getLowerLimit(int paramInt) {
        return getIntegerProperty(paramInt, "LM");
    }


    public int getScreenPosition(int paramInt) {
        return getIntegerProperty(paramInt, "MM");
    }


    public void setScreenPosition(int paramInt1, int paramInt2) {
        setIntegerProperty(paramInt1, "MM FIX", paramInt2);
    }


    public void incrementScreenPosition(int paramInt1, int paramInt2) {
        setIntegerProperty(paramInt1, "MM INC", paramInt2);
    }


    public void decrementScreenPosition(int paramInt1, int paramInt2) {
        setIntegerProperty(paramInt1, "MM DEC", paramInt2);
    }


    public int getTargetPosition(int paramInt) {
        return getIntegerProperty(paramInt, "TA");
    }


    public void setTargetPosition(int paramInt1, int paramInt2) {
        setIntegerProperty(paramInt1, "TA FIX", paramInt2);
    }


    public void incrementTargetPosition(int paramInt1, int paramInt2) {
        setIntegerProperty(paramInt1, "TA INC", paramInt2);
    }


    public void decrementTargetPosition(int paramInt1, int paramInt2) {
        setIntegerProperty(paramInt1, "TA DEC", paramInt2);
    }


    public int getAspectRatio(int paramInt1, int paramInt2) {
        return getIntegerProperty(paramInt1, "A" + paramInt2 % 10);
    }


    public String getIPAddress() {
        return getStringProperty(0, "IP");
    }


    public void setIPAddress(String paramString) {
        try {
            this.fOutputStream.write(new String("$ 0 SE IP " + paramString + "\r").getBytes());
        } catch (IOException localIOException) {
        }
    }


    public String getSubnetMask() {
        return getStringProperty(0, "SN");
    }


    public void setSubnetMask(String paramString) {
        try {
            this.fOutputStream.write(new String("$ 0 SE SN " + paramString + "\r").getBytes());
        } catch (IOException localIOException) {
        }
    }


    public boolean isDHCP() {
        return getStringProperty(0, "DH").compareTo("ON") == 0;
    }


    public void setSerialFlash() {
        try {
            this.fOutputStream.write(new String("$ 0 SE SF\r").getBytes());
        } catch (IOException localIOException) {
        }
    }


    public void setRelayStatus(int paramInt, String paramString) {
        setStringProperty(paramInt, "RE", paramString);
    }


    public double getCustomAspectRatio(int paramInt1, int paramInt2) {
        return getDoubleProperty(paramInt1, "A" + (paramInt2 + 1) % 10);
    }


    public void applyCustomAspectRatio(int paramInt1, int paramInt2) {
        try {
            this.fOutputStream.write(new String("# " + paramInt1 + " SE TA A" + (paramInt2 + 1) % 10 + "\r").getBytes());
        } catch (IOException localIOException) {
        }
    }


    public void setCustomAspectRatio(int paramInt1, int paramInt2) {
        setStringProperty(paramInt1, "A" + (paramInt2 + 1) % 10, "");
    }


    public void setReset() {
        try {
            this.fOutputStream.write(new String("# 0 SE RS\r").getBytes());
        } catch (IOException localIOException) {
        }
    }


    private int getIntegerProperty(int paramInt, String paramString) {
        String str1 = "";
        int i = 0;

        String str2 = "$";
        String str3 = "GE";
        String str4 = str2 + " " + paramInt + " " + str3 + " " + paramString;
        try {
            str1 = getResponse(str4);
            StringTokenizer localStringTokenizer = new StringTokenizer(str1, " ");
            String str5 = localStringTokenizer.nextToken();
            String str6 = localStringTokenizer.nextToken();
            String str7 = localStringTokenizer.nextToken();
            String str8 = localStringTokenizer.nextToken();
            String str9 = localStringTokenizer.nextToken();
            i = Integer.parseInt(str9);
        } catch (SCB200Exception localSCB200Exception) {
            i = 0;
        } catch (NumberFormatException localNumberFormatException) {
            i = 0;
        }
        return i;
    }


    private double getDoubleProperty(int paramInt, String paramString) {
        String str1 = "";
        double d = 0.0D;

        String str2 = "$";
        String str3 = "GE";
        String str4 = str2 + " " + paramInt + " " + str3 + " " + paramString;
        try {
            str1 = getResponse(str4);
            StringTokenizer localStringTokenizer = new StringTokenizer(str1, " ");
            String str5 = localStringTokenizer.nextToken();
            String str6 = localStringTokenizer.nextToken();
            String str7 = localStringTokenizer.nextToken();
            String str8 = localStringTokenizer.nextToken();
            String str9 = localStringTokenizer.nextToken();
            d = Double.parseDouble(str9);
        } catch (SCB200Exception localSCB200Exception) {
            d = 0.0D;
        } catch (NumberFormatException localNumberFormatException) {
            d = 0.0D;
        }
        return d;
    }


    private void setIntegerProperty(int paramInt1, String paramString, int paramInt2) {
        String str1 = "#";
        String str2 = "SE";
        String str3 = str1 + " " + paramInt1 + " " + str2 + " " + paramString + " " + paramInt2 + "\r";

        try {
            this.fOutputStream.write(str3.getBytes());
        } catch (IOException localIOException) {
        }
    }


    private String getStringProperty(int paramInt, String paramString) {
        String str1 = "";


        String str3 = "$";
        String str4 = "GE";
        String str5 = str3 + " " + paramInt + " " + str4 + " " + paramString;
        String str2;
        try {
            str1 = getResponse(str5);
            StringTokenizer localStringTokenizer = new StringTokenizer(str1, " ");
            String str6 = localStringTokenizer.nextToken();
            String str7 = localStringTokenizer.nextToken();
            String str8 = localStringTokenizer.nextToken();
            String str9 = localStringTokenizer.nextToken();
            String str10 = localStringTokenizer.nextToken("\r");
            str2 = str10;
        } catch (SCB200Exception localSCB200Exception) {
            str2 = "";
        }
        return str2;
    }


    private void setStringProperty(int paramInt, String paramString1, String paramString2) {
        String str1 = "#";
        String str2 = "SE";
        String str3 = str1 + " " + paramInt + " " + str2 + " " + paramString1 + " " + paramString2 + "\r";

        try {
            this.fOutputStream.write(str3.getBytes());
        } catch (IOException localIOException) {
        }
    }


    private synchronized String getResponse(String paramString) throws SCB200Exception {
        byte[] arrayOfByte2 = new byte['\u0200'];


        try {
            while (this.fInputStream.available() > 0) {
                this.fInputStream.read(arrayOfByte2, 0, 1);
            }

            String str1 = paramString + "\r";
            byte[] arrayOfByte1 = str1.getBytes();
            this.fOutputStream.write(arrayOfByte1);


            int i = 0;
            long l = System.currentTimeMillis() + 5000L;
            while (System.currentTimeMillis() < l) {
                if (this.fInputStream.available() > 0) {
                    this.fInputStream.read(arrayOfByte2, i, 1);
                    if (arrayOfByte2[i] == 13) {
                        return new String(arrayOfByte2, 0, i);
                    }

                    i++;
                }
            }
            throw new SCB200Exception("Timeout waiting for response");
        } catch (IOException localIOException) {
            throw new SCB200Exception("Error reading input");
        }
    }
}
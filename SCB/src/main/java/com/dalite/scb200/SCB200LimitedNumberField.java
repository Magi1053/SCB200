package com.dalite.scb200;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;


public class SCB200LimitedNumberField extends JTextField implements KeyListener {
    private int fLength;
    private char fDecimalSeparator;
    private char fMinusSign;

    public SCB200LimitedNumberField(int paramInt) {
        this.fLength = paramInt;
        DecimalFormat localDecimalFormat = new DecimalFormat();
        this.fDecimalSeparator = localDecimalFormat.getDecimalFormatSymbols().getDecimalSeparator();
        addKeyListener(this);
    }


    public SCB200LimitedNumberField(int paramInt1, int paramInt2) {
        super(paramInt1);
        this.fLength = paramInt2;
        DecimalFormat localDecimalFormat = new DecimalFormat();
        this.fDecimalSeparator = localDecimalFormat.getDecimalFormatSymbols().getDecimalSeparator();
        this.fMinusSign = localDecimalFormat.getDecimalFormatSymbols().getMinusSign();
        addKeyListener(this);
    }


    public double getValue() {
        double d = 0.0D;
        try {
            d = Double.parseDouble(getText());
        } catch (NumberFormatException localNumberFormatException) {
        }


        return d;
    }


    public void keyPressed(KeyEvent paramKeyEvent) {
    }


    public void keyReleased(KeyEvent paramKeyEvent) {
    }

    public void keyTyped(KeyEvent paramKeyEvent) {
        String str1 = getText();
        String str2 = getSelectedText();
        if (str2 == null)
            str2 = "";
        int i = str1.length();
        char c = paramKeyEvent.getKeyChar();


        if ((str2.length() == 0) && (i >= this.fLength) && (!Character.isISOControl(c))) {
            paramKeyEvent.consume();


        } else if ((!Character.isISOControl(c)) && (!Character.isDigit(c)) && (c != this.fDecimalSeparator) && (c != this.fMinusSign)) {

            paramKeyEvent.consume();
        } else {
            if ((c == this.fMinusSign) && (((i >= 1) && (str1.charAt(0) == this.fMinusSign)) || (getCaretPosition() != 0))) {
                paramKeyEvent.consume();
            }
            int j = 0;
            for (int k = 0; k < i; k++) {
                if (str1.charAt(k) == this.fDecimalSeparator) {
                    j++;
                    if ((j >= 1) && (c == this.fDecimalSeparator)) {
                        paramKeyEvent.consume();
                        break;
                    }
                }
            }
        }
    }
}
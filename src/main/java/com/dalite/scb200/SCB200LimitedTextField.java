package com.dalite.scb200;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SCB200LimitedTextField extends JTextField implements KeyListener {
    protected int fLength;

    public SCB200LimitedTextField(int paramInt) {
        this.fLength = paramInt;
        addKeyListener(this);
    }


    public SCB200LimitedTextField(int paramInt1, int paramInt2) {
        super(paramInt2);
        this.fLength = paramInt1;
        addKeyListener(this);
    }


    public void keyPressed(KeyEvent paramKeyEvent) {
    }


    public void keyReleased(KeyEvent paramKeyEvent) {
    }

    public void keyTyped(KeyEvent paramKeyEvent) {
        String str1 = getSelectedText();
        String str2 = getText();
        if ((str1 == null) || (str1.length() == 0)) {
            if (str2.length() >= this.fLength) {
                if (!Character.isISOControl(paramKeyEvent.getKeyChar())) {
                    paramKeyEvent.consume();
                }
            }
        }
    }
}

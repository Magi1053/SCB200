package com.dalite.scb200;

import javax.swing.*;
import java.awt.*;


public class SCB200Indicator extends JPanel {
    private Image fImageTrue;
    private Image fImageFalse;
    private boolean fStatus;

    public SCB200Indicator(Image paramImage1, Image paramImage2, boolean paramBoolean) {
        this.fImageTrue = paramImage1;
        this.fImageFalse = paramImage2;
        this.fStatus = paramBoolean;
    }


    public boolean getStatus() {
        return this.fStatus;
    }


    public void setStatus(boolean paramBoolean) {
        this.fStatus = paramBoolean;
        repaint();
    }


    public void paint(Graphics paramGraphics) {
        paramGraphics.setColor(getBackground());
        paramGraphics.fillRect(0, 0, getWidth(), getHeight());
        Image localImage = this.fStatus ? this.fImageTrue : this.fImageFalse;
        int i = (getWidth() - localImage.getWidth(this)) / 2;
        int j = (getHeight() - localImage.getHeight(this)) / 2;
        paramGraphics.drawImage(this.fStatus ? this.fImageTrue : this.fImageFalse, i, j, this);
    }
}
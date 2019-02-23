package com.dalite.scb200;

import javax.swing.*;
import java.awt.*;


public class SCB200ImagePanel extends JPanel {
    protected Image fImage;

    public SCB200ImagePanel(Image paramImage) {
        this.fImage = paramImage;
    }


    public void paint(Graphics paramGraphics) {
        paramGraphics.drawImage(this.fImage, 0, 0, this);
    }


    public Dimension getPreferredSize() {
        return new Dimension(this.fImage.getWidth(this), this.fImage.getHeight(this));
    }
}


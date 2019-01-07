package com.dalite.scb200;


public class SCB200RefreshTimer extends Thread {
    private SCB200Refreshable fFrame;


    private long fDelay;


    private boolean fRunning;


    private boolean fHolding;


    public SCB200RefreshTimer(SCB200Refreshable paramSCB200Refreshable, long paramLong) {
        this.fFrame = paramSCB200Refreshable;
        this.fDelay = paramLong;
        this.fRunning = true;
        this.fHolding = false;
    }


    public void run() {
        while (this.fRunning) {
            if (!this.fHolding) {
                this.fFrame.refreshDeviceData();
            }
            try {
                Thread.sleep(this.fDelay);
            } catch (InterruptedException localInterruptedException) {
            }
        }
    }


    public void terminate() {
        this.fRunning = false;
    }


    public void hold() {
        this.fHolding = true;
    }


    public void unhold() {
        this.fHolding = false;
    }
}

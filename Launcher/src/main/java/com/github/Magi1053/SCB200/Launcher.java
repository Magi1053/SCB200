package com.github.Magi1053.SCB200;

import com.dalite.scb200.SCB200Applet;

import java.net.MalformedURLException;
import java.net.URL;

public class Launcher {
    public static void main(String[] args) throws MalformedURLException {
        // TODO add interface to enter SCB address
        new SCB200Applet(new URL("")).init();
    }
}

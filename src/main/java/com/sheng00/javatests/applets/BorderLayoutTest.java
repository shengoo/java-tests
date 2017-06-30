package com.sheng00.javatests.applets;

import java.applet.Applet;
import java.awt.*;

/**
 * Created by UC206612 on 6/30/2017.
 */
public class BorderLayoutTest extends Applet {
    public void init() {
        setLayout(new BorderLayout());
        Panel panel= new Panel();
        panel.add(new Button("n1"),FlowLayout.LEFT);
        panel.add(new Button("n2"),FlowLayout.LEFT);
//	     add(new Button("North"), BorderLayout.NORTH);
//	     add(new Button("North2"), BorderLayout.NORTH);
        add(panel,BorderLayout.NORTH);
        add(new Button("South"), BorderLayout.SOUTH);
        add(new Button("East"), BorderLayout.EAST);
        add(new Button("West"), BorderLayout.WEST);
        add(new Button("Center"), BorderLayout.CENTER);
    }
}

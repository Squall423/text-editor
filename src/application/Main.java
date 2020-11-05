package application;


import application.gui.Window;

import java.awt.*;

public class Main {


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    application.gui.Window window = new Window("Application");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


     
    }

}






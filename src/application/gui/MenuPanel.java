package application.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuPanel extends JMenuBar {

    protected JMenuBar blueMenuBar = new JMenuBar();
    protected ArrayList<JMenu> menu = new ArrayList<JMenu>(5);
    protected ArrayList<JMenuItem> submenu = new ArrayList<JMenuItem>(12);

    public MenuPanel(int width, int height) {

        //set menubar properties
        blueMenuBar.setOpaque(true);
        blueMenuBar.setBackground(new Color(13, 25, 165));
        blueMenuBar.setPreferredSize(new Dimension(width, height));

        //create menu objects 0,1,2,3
        menu.add(new JMenu("File"));
        menu.add(new JMenu("Edit"));
        menu.add(new JMenu("View"));
        menu.add(new JMenu("Help"));

        System.out.print("menuItems: ");
        for (JMenuItem menuItem : menu) {
            menuItem.setForeground(Color.WHITE);
            System.out.print(menu.indexOf(menuItem) + "." + menuItem.getText());
            System.out.print(" ");
        }
        System.out.println();

        //add to menubar
        for (JMenuItem menu : menu) {
            blueMenuBar.add(menu);
        }

        //expand menu by more buttons
        submenu.add(new JMenuItem("New"));
        submenu.add(new JMenuItem("New window"));
        submenu.add(new JMenuItem("Open"));
        submenu.add(new JMenuItem("Save"));
        submenu.add(new JMenuItem("Save as"));

        submenu.add(new JMenuItem("Settings"));
        submenu.add(new JMenuItem("Print"));

        submenu.add(new JMenuItem("Cut"));
        submenu.add(new JMenuItem("Copy"));
        submenu.add(new JMenuItem("Paste"));

        submenu.add(new JMenuItem("Max"));
        submenu.add(new JMenuItem("Min"));

        submenu.add(new JMenuItem("FAQ"));

        //
        menu.get(0).add(submenu.get(0));
        menu.get(0).add(submenu.get(1));
        menu.get(0).add(submenu.get(2));
        menu.get(0).add(submenu.get(3));
        menu.get(0).add(submenu.get(4));

        menu.get(0).add(submenu.get(5));
        menu.get(0).add(submenu.get(6));

        menu.get(1).add(submenu.get(7));
        menu.get(1).add(submenu.get(8));
        menu.get(1).add(submenu.get(9));

        menu.get(2).add(submenu.get(10));
        menu.get(2).add(submenu.get(11));

        menu.get(3).add(submenu.get(12));

        System.out.print("submenuItems : ");
        for (JMenuItem submenuItem : submenu) {
            System.out.print(submenu.indexOf(submenuItem) + "." + submenuItem.getText());
            System.out.print(" ");
        }

        //add objects to constructor
        this.add(blueMenuBar);

    }
}

package application.gui;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panels extends JPanel {

    protected ArrayList<JButton> buttonlist = new ArrayList<JButton>();
    protected JTextArea textArea = new JTextArea(30, 78);
    protected JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public Panels() {

        JPanel centralPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centralPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        centralPanel.setBackground(new Color(13, 25, 165));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        rightPanel.setBackground(new Color(248, 227, 167));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        leftPanel.setBackground(new Color(248, 229, 221));

        //add components to constructor
        this.setLayout(new BorderLayout());
        this.add(centralPanel, BorderLayout.SOUTH);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.CENTER);

        // text area preferences
        textArea.setLineWrap(true);
        textArea.setEditable(true);
        textArea.setVisible(true);

        //create and add buttons to arraylist

        buttonlist.add(new JButton("1"));
        buttonlist.add(new JButton("2"));


        //add components to panels

        rightPanel.add((buttonlist.get(0)));
        rightPanel.add((buttonlist.get(1)));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        ////
        buttonlist.get(0).addActionListener(e -> System.out.println("Create new File"));
        buttonlist.get(1).addActionListener(e -> System.out.println("Create new File1"));



        /////////////

    }
}







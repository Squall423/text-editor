//Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
package application.gui;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Window extends JFrame {


    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    protected Panels panels = new Panels();
    protected MenuPanel menupanel;
    private ImageIcon image = new ImageIcon("res/Super-Lion-Pig-icon (1).png");
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private Dimension windowSize = new Dimension(width / 2, height / 2);

    //    private String textSource = panels.textArea.getText();
    private String fileExtension = ".txt";
    private File newFile;
    private File savedFile;
    private JFileChooser fileChooser = new JFileChooser();


    // making constructor with window for application
    public Window(String name) {

        //Create a window with frame
        super(name);
        // setProperties
        setPreferredSize(windowSize);
        setSize(windowSize);
        setLocationRelativeTo(null);


//        centerScreen(this, height, width);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setIconImage(image.getImage());
        setResizable(true);
        setFocusable(true);

        //add objects
        menupanel = new MenuPanel(width, this.height / 40);

        this.setJMenuBar(menupanel);
        this.setContentPane(panels);

        Actions actions = new Actions(this);
        pack();
    }


    // inner class implementing all button action listeners
    private class Actions {

        private Actions(Window window) {

            System.out.println();
            System.out.println("actions messages:");

            menupanel.submenu.get(0).addActionListener(e -> panels.textArea.setText(""));
            menupanel.submenu.get(1).addActionListener(e -> {
                Window window2 = new Window("new");
                window2.setVisible(true);
            });
            menupanel.submenu.get(2).addActionListener(e -> {

                int actionDialog = fileChooser.showOpenDialog(window);
                fileChooser.setApproveButtonText("Open");

                // If the user selects a file
                if (actionDialog == JFileChooser.APPROVE_OPTION) {
                    // Set the label to the path of the selected directory
                    newFile = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    try {
                        // String
                        String textLine = "", ntextline = "";
                        // File reader
                        FileReader fileReader = new FileReader(newFile);
                        // Buffered reader
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        // Initilize ntextline
                        ntextline = bufferedReader.readLine();

                        // Take the input from the file
                        while ((textLine = bufferedReader.readLine()) != null) {
                            ntextline = ntextline + "\n" + textLine;
                        }
                        // Set the text
                        panels.textArea.setText(ntextline);
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(panels, evt.getMessage());
                    }
                }
                // If the user cancelled the operation
                else
                    JOptionPane.showMessageDialog(panels, "the user cancelled the operation");
            });

            menupanel.submenu.get(3).addActionListener(e -> {

                if (savedFile != null) {
                    System.out.println("file exists");
                    try {
                        FileWriter fw = new FileWriter(savedFile, false);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(panels.textArea.getText());
                        bw.close();
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                } else {
                    System.out.println("file not exist");
                    fileChooser.setApproveButtonText("Save");
                    int actionDialog = fileChooser.showOpenDialog(window);
                    if (actionDialog == JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(panels, "the user cancelled the operation");
                    }
                    BufferedWriter bw = null;
                    if (actionDialog == JFileChooser.APPROVE_OPTION) {
                        try {
                            newFile = new File(fileChooser.getSelectedFile() + fileExtension);
                            savedFile = newFile;
                            FileWriter fw = new FileWriter(savedFile, false);
                            bw = new BufferedWriter(fw);
                            bw.write(panels.textArea.getText());
                            System.out.println("done");
                        } catch (IOException ex) {
                            ex.printStackTrace();

                        } finally {
                            if (bw != null) {
                                try {
                                    bw.close();
                                } catch (IOException f) {
                                    System.out.println("bw != null");
                                }
                            }
                        }
                    }
                }
            });

            menupanel.submenu.get(4).addActionListener(e -> {

                fileChooser.setApproveButtonText("Save");
                int actionDialog = fileChooser.showOpenDialog(window);
                if (actionDialog == JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(panels, "the user cancelled the operation");
                }

                BufferedWriter bw = null;
                if (actionDialog == JFileChooser.APPROVE_OPTION) {
                    try {
                        if (fileChooser.getSelectedFile().getAbsolutePath().contains(".txt")) {
                            newFile = new File(fileChooser.getSelectedFile() + "");
                        } else {
                            newFile = new File(fileChooser.getSelectedFile() + fileExtension);
                        }
                        savedFile = newFile;
                        FileWriter fw = new FileWriter(savedFile, false);
                        bw = new BufferedWriter(fw);
                        bw.write(panels.textArea.getText());
                        System.out.println("done");

                    } catch (IOException ex) {
                        ex.printStackTrace();

                    } finally {
                        if (bw != null) {
                            try {
                                bw.close();
                            } catch (IOException f) {
                                System.out.println("bw -!= null");
                            }
                        }
                    }
                }

            });
//zrobic test
            menupanel.submenu.get(6).addActionListener(e -> {

                String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
                System.out.println("Default printer: " + defaultPrinter);

                PrintService service = PrintServiceLookup.lookupDefaultPrintService();

                try {
                    if (newFile == null) {
                        JOptionPane.showMessageDialog(panels, "Please save file first.");

                    } else {
                        FileInputStream in = new FileInputStream(newFile.getAbsoluteFile());


                        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                        pras.add(new Copies(2));


                        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                        Doc doc = new SimpleDoc(in, flavor, null);

                        DocPrintJob job = service.createPrintJob();
                        PrintJobWatcher pjw = new PrintJobWatcher(job);
                        job.print(doc, pras);
                        pjw.waitForDone();
                        in.close();

                        // send FF to eject the page
//                        InputStream ff = new ByteArrayInputStream("\f".getBytes());
//                        Doc docff = new SimpleDoc(ff, flavor, null);
//                        DocPrintJob jobff = service.createPrintJob();
//                        pjw = new PrintJobWatcher(jobff);
//                        jobff.print(docff, null);
//                        pjw.waitForDone();
                    }
                } catch (PrintException | IOException ef) {
                    ef.printStackTrace();

                }

            });

            menupanel.submenu.get(7).addActionListener(e -> {
                panels.textArea.cut();
            });
            menupanel.submenu.get(8).addActionListener(e -> {
                panels.textArea.copy();
            });
            menupanel.submenu.get(9).addActionListener(e -> {
                panels.textArea.paste();
            });

            menupanel.submenu.get(10).addActionListener(e -> {
                window.setExtendedState(MAXIMIZED_BOTH);

            });
            menupanel.submenu.get(11).addActionListener(e -> {
                centerScreen(window, height, width);
                window.setSize(getPreferredSize());
                window.setLocationRelativeTo(null);
            });

        }
    }

    class PrintJobWatcher {
        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            job.addPrintJobListener(new PrintJobAdapter() {
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }

                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        System.out.println("Printing done ...");
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }

        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
                System.out.println("thread interrupted");
            }
        }
    }

    //    method gets screenSize and sets center
    public void centerScreen(JFrame Window, int height, int width) {
        this.height = height;
        this.width = width;
        System.out.println();
        System.out.println("Screen size: " + height + "x" + width);

        int iCoordX = (width - Window.getWidth()) / 2;
        int iCoordY = (height - Window.getHeight()) / 2;
        Window.setLocation(iCoordX, iCoordY);
    }
}


















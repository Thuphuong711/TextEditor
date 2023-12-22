import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem openItem = new JMenuItem("Open");
    JMenuItem saveItem = new JMenuItem("Save");
    JMenuItem exitItem = new JMenuItem("Exit");

    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    JComboBox fontBox = new JComboBox(fonts);

    JButton ColorButton = new JButton("Color");
    JLabel sizeLabel = new JLabel("Size: ");

    JLabel colorLabel = new JLabel("Color");

    JLabel familyLabel = new JLabel("Font");

    JTextArea area = new JTextArea();

    JScrollPane scrollPane = new JScrollPane(area);

    JSpinner fontSize = new JSpinner();


    TextEditor() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text Editor");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Arial", Font.PLAIN, 20));


        scrollPane.setPreferredSize(new Dimension(420, 420));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        fontSize.setPreferredSize(new Dimension(50, 25));
        fontSize.setValue(20);
        fontSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                area.setFont(new Font(area.getFont().getFamily(), Font.PLAIN, (Integer) fontSize.getValue()));
            }
        });

        ColorButton.addActionListener(this);

        fontBox.addActionListener(this);
        fontBox.setSelectedItem("Arial");

        // menuBar - start//
        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        saveItem.addActionListener(this);
        openItem.addActionListener(this);
        exitItem.addActionListener(this);

        //menuBar- end//

        // font size//
        this.add(sizeLabel);
        this.add(fontSize);

        //font color//
//        this.add(colorLabel);
        this.add(ColorButton);

        //font family//
        this.add(familyLabel);
        this.add(fontBox);


        this.add(scrollPane);


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ColorButton) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
            area.setForeground(color);
        }

        if (e.getSource() == fontBox) {
            area.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, area.getFont().getSize()));
        }

        if (e.getSource() == saveItem) {
            JFileChooser saveChooser = new JFileChooser();
            saveChooser.setCurrentDirectory(new File("."));
            int res = saveChooser.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {

//                PrintWriter fileOut = null;
//
//                File file = new File(saveChooser.getSelectedFile().getAbsolutePath());
//                try {
//                    fileOut = new PrintWriter(file);
//                    fileOut.println(area.getText());
//                } catch (FileNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                } finally {
//                    fileOut.close();
//                }

                FileWriter fileOut = null;
                File file = new File(saveChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileOut = new FileWriter(file);
                    fileOut.write(area.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    try {
                        fileOut.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }


            }
        }

        if (e.getSource() == openItem) {
            JFileChooser openChooser = new JFileChooser();
            openChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("text files: ", "txt", "docx");
            openChooser.setFileFilter(filter);
            int res = openChooser.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = new File(openChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()) {
                        while (fileIn.hasNextLine()) {
                            String line = fileIn.nextLine() + "\n";
                            area.append(line);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    fileIn.close();
                }

            }
        }

        if (e.getSource() == exitItem) {
            System.exit(0);
        }

    }
}
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Text_Editor_try_second_time extends JFrame implements ActionListener, ChangeListener {
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);

    JMenuBar menuBar = new JMenuBar();
    JMenu FileMenu = new JMenu("File");

    //Open Item
    JMenuItem openItem = new JMenuItem("Open");

    //Save Item
    JMenuItem saveItem = new JMenuItem("Save");

    //Exit item
    JMenuItem exitItem = new JMenuItem("Exit");

    // fontSize
    JLabel fontSizeLabel = new JLabel("Size: ");
    JSpinner fontSize = new JSpinner();

    // fontColor
    JButton textColorButton = new JButton("Text Color");

    JButton backgroundColorButton = new JButton("Background Color");

    String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    JComboBox fontFamily = new JComboBox(fonts);

    Text_Editor_try_second_time() {
        /*
        set up for the JFrame
         */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("Text Editor");
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        //add fontSize to the frame
        this.add(fontSizeLabel);
        this.add(fontSize);
        fontSize.setValue(20);
        fontSize.addChangeListener(this);

        //add textColorButton to the frame
        this.add(textColorButton);
        textColorButton.addActionListener(this);

        //add backgroundColorButton to the frame
        this.add(backgroundColorButton);
        backgroundColorButton.addActionListener(this);

        //add Font Family to the frame
        this.add(fontFamily);
        fontFamily.addActionListener(this);

        scrollPane.setPreferredSize(new Dimension(540, 480));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);


        menuBar.add(FileMenu);
        FileMenu.add(saveItem);
        FileMenu.add(openItem);
        FileMenu.add(exitItem);

        /*
        set the shortcut for the button in the JMenuBar
         */
        FileMenu.setMnemonic(KeyEvent.VK_F); // Alt+F to open File Menu
        saveItem.setMnemonic(KeyEvent.VK_S); // s to execute save
        openItem.setMnemonic(KeyEvent.VK_O); // o to execute open
        exitItem.setMnemonic(KeyEvent.VK_E); // e to execute exit


        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);


        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //change the text color
        if (e.getSource() == textColorButton) {
            Color color = JColorChooser.showDialog(null, "Choose a color for your text", Color.BLACK);
            textArea.setForeground(color);
        }

        //change the textArea background color
        if (e.getSource() == backgroundColorButton) {
            Color color = JColorChooser.showDialog(null, "Choose a color for your background", Color.WHITE);
            textArea.setBackground(color);
            textArea.setOpaque(true);
        }

        //change the fontFamily
        if (e.getSource() == fontFamily) {
            textArea.setFont(new Font((String) fontFamily.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
        }

        //make saveItem save the file
        if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            //the show..Dialog need null passed in
            int res = fileChooser.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                FileWriter fileWriter = null;
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileWriter = new FileWriter(file);
                    //write the text content in the textArea to the file
                    fileWriter.write(textArea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    try {
                        fileWriter.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

        if (e.getSource() == openItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            //the show..Dialog need null passed in
            int res = fileChooser.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                if (file.isFile()) {
                    Scanner openFile = null;
                    try {
                        openFile = new Scanner(file);
                        while (openFile.hasNextLine()) {
                            String line = openFile.nextLine() + "\n";
                            /*
                            append - build-in method.
                            Appends the given text to the end of the document.
                            Does nothing if the model is null or the string is null or empty.
                             */
                            textArea.append(line);
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } finally {
                        openFile.close();
                    }
                }


            }
        }

        //TextEditor closes when the exit is clicked
        if (e.getSource() == exitItem) {
            System.exit(0);
        }

    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == fontSize) {
            textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (Integer) fontSize.getValue()));
        }
    }
}

(21/12/2023)
This is my practice for creating a simple text editor in java. In this project, I learn these following things:
1. FileChooser (relearn) (open,save)

2. JComboBox (relearn) -> create a JComboBox to select the Font Family

3. GraphicsEnvironment.getLocalGraphicEnvironment().getAvailableFontFamilyNames(); -> this is to create a String of the available font family in java and pass this String as an argument  to JComboBox

4. create a menuBar (relearn)

5. JButton (relearn/old) -> create a button to change the Font Color

6. JTextArea (new) -> create a area to type in sth in the Text Editor Frame

7. JLabel (relearn/old) -> to add label to know the font, color, size

8. JScrollPane (new) -> create a scroll pane to scroll up and down

9. setLocationRelativeTo(null) -> the frame will appear in the center of the screen, not in the top left hand corner

10. setLineWrap(true) (new) -> create a border for the textArea so that the text typed in will only appear in the textArea size( width) -> no horizontal scroll in the bottom

11. setWrapStyleWord(true) (new) -> make the word goes to the next line when it reach the width border and the word will goes to the next line when there is a space ( I mean the word will not goes to the next line in the middle of the word, the full word will goes to the next line
Note: setLineWrap(true) must be first, and followed by setWrapStyleWord

12. setVerticalScrollBarPolicy(ScrollPanelConstants.VERTICLE_SCROLLBAR_ALWAYS) -> create a verticle bar

13. JSpinner (new) -> create a JSpinner to change the Font Size



// Below are what I learn about the statement in the methods in addChangeListener and addActionListener ( I will clarify what are the differences between these two below)

14. to change to font size according to what we choose in the JSpinner fontSize

area.setFont(new Font(area.getFont().getFamily(), Font.PLAIN, (Integer) fontSize.getValue()));
-> so it has getValue() build-in method, and because his method return object, so we have to cast it to Integer/ int


15. to change color of the text in textArea use JColorChooser 

-> use showDialog(null, "Choose a color", Color.BLACK); -> the color dialog will open
-> set the font color:  setForeground();


16. to change the font family name of text in textArea

area.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, area.getFont().getSize()));
-> use getSelectedItem() to get the font family name and area.getFont().getSize() to get the current size

17. saveItem with FileChooser 
setCurrentDirectory(new File(".")) -> set the current directory by default is what directory store the text editor

-> create a variable to store the return value of showSaveDialog(null) method. This method return int
int res = saveChooser.showSaveDialog(null);

if (res == JFileChooser.APPROVE_OPTION) -> if this is chosen
-> then we can use PrintWriter or FileWriter -> I learn these two
-> .close() method must be added to save memory

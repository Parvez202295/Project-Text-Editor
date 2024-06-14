import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring properties of the text editor
    JFrame frame;
    JMenuBar menubar;
    JMenu file;
    JMenu edit;
    JMenuItem newfile, openfile, savefile;
    JMenuItem cut, copy, paste, selectall, close;
    JTextArea textarea;
    JPanel panel;

    public TextEditor() {
        frame = new JFrame();

        // Initialize menubar
        menubar = new JMenuBar();
        textarea = new JTextArea();
        // Initialize panel and add textarea to it
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        // Create scroll pane and add textarea to it
        JScrollPane scrollPane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add panel to frame
        frame.add(panel);
        //add title
        frame.setTitle("TEXT EDITOR");



        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file menu items
        newfile = new JMenuItem("New File");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        // Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

        menubar.add(file);
        menubar.add(edit);
        frame.setJMenuBar(menubar);

        // Set dimensions of frame
        frame.setBounds(100, 100, 800, 800);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Added to close the application properly
        textarea.setBounds(0, 0, 400, 400);  // Added to position the textarea
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            textarea.cut();
        }
        if (actionEvent.getSource() == copy) {
            textarea.copy();
        }
        if (actionEvent.getSource() == paste) {
            textarea.paste();
        }
        if (actionEvent.getSource() == selectall) {
            textarea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            frame.dispose();  // Close the frame
        }
        if (actionEvent.getSource() == openfile) {
            // Open file chooser
            JFileChooser fileChooser = new JFileChooser("c:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // If we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // Getting selected file
                File file = fileChooser.getSelectedFile();
                // Get the path of selected file
                String filepath = file.getPath();
                try {
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filepath);
                    // Initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // Read the content of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    // Set the output string to textarea
                    textarea.setText(output);
                    bufferedReader.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == savefile) {
            // Initialize the picker
            JFileChooser fileChooser = new JFileChooser("c:");
            // Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // Check if clicked on save button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // Create a new file with chosen directory path and file
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    // Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize the buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // Write content to file
                    bufferedWriter.write(textarea.getText());
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
            if(actionEvent.getSource()==newfile){
                TextEditor nte=new TextEditor();
            }

    }

    public static void main(String[] args) {
        TextEditor te = new TextEditor();
    }
}
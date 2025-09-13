import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

    public class SimpleNotepad extends JFrame {

        private JTextArea textArea ;
        private JFileChooser fileChooser;

        public SimpleNotepad() {

            setTitle("Simple Notepad");
            setSize(800, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);


            textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane, BorderLayout.CENTER);

            fileChooser = new JFileChooser();

            JMenuBar menuBar = new JMenuBar();

            JMenu fileMenu = new JMenu("File");

            JMenuItem newItem = new JMenuItem("New");
            newItem.addActionListener(e -> textArea.setText(""));

            JMenuItem openItem = new JMenuItem("Open");
            openItem.addActionListener(e -> openFile());

            JMenuItem saveItem = new JMenuItem("Save");
            saveItem.addActionListener(e -> saveFile());

            JMenuItem exitItem = new JMenuItem("Exit");
            exitItem.addActionListener(e -> System.exit(0));

            fileMenu.add(newItem);
            fileMenu.add(openItem);
            fileMenu.add(saveItem);
            fileMenu.addSeparator();
            fileMenu.add(exitItem);

            JMenu editMenu = new JMenu("Edit");

            JMenuItem cutItem = new JMenuItem("Cut");
            cutItem.addActionListener(e -> textArea.cut());

            JMenuItem copyItem = new JMenuItem("Copy");
            copyItem.addActionListener(e -> textArea.copy());

            JMenuItem pasteItem = new JMenuItem("Paste");
            pasteItem.addActionListener(e -> textArea.paste());

            editMenu.add(cutItem);
            editMenu.add(copyItem);
            editMenu.add(pasteItem);

            JMenu helpMenu = new JMenu("Help");

            JMenuItem aboutItem = new JMenuItem("About");
            aboutItem.addActionListener(e ->
                    JOptionPane.showMessageDialog(this,
                            "Simple Notepad\nDeveloped by: K.A.N.S Kariyawasam \nID: [2022s19220]",
                            "About",
                            JOptionPane.INFORMATION_MESSAGE)
            );

            helpMenu.add(aboutItem);

            JMenu formatMenu = new JMenu("Format");

            JMenuItem fontColorItem = new JMenuItem("Font Color");
            fontColorItem.addActionListener(e -> {
                Color color = JColorChooser.showDialog(this, "Choose Font Color", textArea.getForeground());
                if (color != null) {
                    textArea.setForeground(color);
                }
            });

            JMenuItem bgColorItem = new JMenuItem("Background Color");
            bgColorItem.addActionListener(e -> {
                Color color = JColorChooser.showDialog(this, "Choose Background Color", textArea.getBackground());
                if (color != null) {
                    textArea.setBackground(color);
                }
            });

            formatMenu.add(fontColorItem);
            formatMenu.add(bgColorItem);

            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(helpMenu);
            menuBar.add(formatMenu);

            setJMenuBar(menuBar);
        }

        private void openFile() {
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    textArea.read(reader, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private void saveFile() {
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(writer);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new SimpleNotepad().setVisible(true);
            });
        }
    }


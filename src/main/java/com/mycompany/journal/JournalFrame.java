/*
 * Class: JournalFrame
 * Description: This class describes the main GUI of the application
 * Author: Theodore B
 */
package com.mycompany.journal;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class JournalFrame extends JFrame {
    /* Fonts */
    final private Font titleFont = new Font("Arial", Font.BOLD, 30);
    final private Font dateFont = new Font("Arial", Font.BOLD, 20);

    /* Journal that the frame will display */
    private Journal journal;

    /* Instance GUI variables */
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    /* Initialize the frame */
    public void initialize(Journal j) {
        //set journal
        this.journal = j;

        /* Main Panel */
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.darkGray);
        mainPanel.setLayout(new BorderLayout());

        /* Top Panel */
        topPanel = new JPanel();
        topPanel.setBackground(Color.black);
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        /* Title Label */
        JLabel titleLabel = new JLabel("Journal App");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.white);
        topPanel.add(titleLabel);

        /* Spacing */
        topPanel.add(Box.createHorizontalStrut(190));

        /* New Entry Button */
        JButton newButton = new JButton("New Entry");
        newButton.setPreferredSize(new Dimension(100, 40));
        newButton.addActionListener(e -> newButtonPressed());
        topPanel.add(newButton);

        /* Bottom Panel */
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.darkGray);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));

        /* Add Entry Panels */
        addEntryPanels();

        /* Scroll Pane */
        JScrollPane scrollPane = new JScrollPane(bottomPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        /* Add Panels to Main Panel */
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        /* Set Frame Details */
        add(mainPanel);
        setTitle("Journal");
        setSize(450, 600);
        setMinimumSize(new Dimension(500,300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /* Adds entry panel for every entry in journal */
    private void addEntryPanels() {
        for (JournalEntry entry : journal.getEntries()) {
            JPanel entryPanel = createEntryPanel(entry);

            //add at index 0 so it's at the top of the window
            bottomPanel.add(entryPanel, 0);
        }
    }

    /* Create entry panel from JournalEntry object */
    private JPanel createEntryPanel(JournalEntry entry) {
        /* Entry Panel */
        JPanel entryPanel = new JPanel();
        entryPanel.setBackground(Color.gray);

        //configure GridBagLayout
        entryPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        /* Date Label */
        JLabel dateLabel = new JLabel(entry.getDateString());
        dateLabel.setFont(dateFont);

        /* View and Delete buttons */
        JButton viewButton = new JButton("View");
        JButton deleteButton = new JButton("Delete");

        /* Action Listeners */
        viewButton.addActionListener(e -> viewButtonPressed(entry));
        deleteButton.addActionListener(e -> {
            try {
                deleteButtonPressed(entry, entryPanel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //add components to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        entryPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        entryPanel.add(viewButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        entryPanel.add(deleteButton, gbc);

        entryPanel.setPreferredSize(new Dimension(450, 50));
        entryPanel.setMaximumSize(new Dimension(450, 50));

        //return panel
        return entryPanel;
    }

    /* Views the selected journal entry */
    private void viewButtonPressed(JournalEntry entry) {
        //create ViewFrame object and initialize it with the selected entry
        ViewFrame viewFrame = new ViewFrame();
        viewFrame.initialize(entry);
    }

    /* Deletes the selected journal entry */
    private void deleteButtonPressed(JournalEntry entry, JPanel entryPanel) throws IOException {
        /* Get user confirmation */
        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?", "Select an Option", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            //delete journal entry and associated panel
            journal.deleteEntry(entry);
            bottomPanel.remove(entryPanel);

            //refresh GUI
            revalidate();
            repaint();
        }
    }

    /* Open window to add a new journal entry */
    private void newButtonPressed() {
        AddFrame addFrame = new AddFrame();
        addFrame.initialize(this);
    }

    /* Adds a new entry to the journal and GUI */
    public void addNewEntry(String text) throws IOException {
        //add entry to Journal object
        journal.addEntry(text);
        //create panel for newly added entry, and add it to the top of bottomPanel
        JPanel entryPanel = createEntryPanel(journal.getEntries().get(journal.getEntries().size()-1));
        bottomPanel.add(entryPanel, 0);

        //refresh GUI
        revalidate();
        repaint();
    }
}

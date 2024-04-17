package com.example;

import javax.swing.*;
import java.awt.*;
public class JournalFrame extends JFrame {
    final private Font titleFont = new Font("Arial", Font.BOLD, 30);
    final private Font dateFont = new Font("Arial", Font.BOLD, 20);

    private Journal journal;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    public void initialize(Journal j) {
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
        //pack();
    }

    private void addEntryPanels() {
        for (JournalEntry entry : journal.getEntries()) {
            JPanel entryPanel = createEntryPanel(entry);

            bottomPanel.add(entryPanel, 0);
        }
    }

    private JPanel createEntryPanel(JournalEntry entry) {
        JPanel entryPanel = new JPanel();
        entryPanel.setBackground(Color.gray);

        // Using GridBagLayout for each entry panel
        entryPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel dateLabel = new JLabel(entry.getDateString());
        dateLabel.setFont(dateFont);

        JButton viewButton = new JButton("View");
        JButton deleteButton = new JButton("Delete");

        /* Action Listeners */
        viewButton.addActionListener(e -> viewButtonPressed(entry));
        deleteButton.addActionListener(e -> deleteButtonPressed(entry, entryPanel));

        // Add components to entryPanel
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

        return entryPanel;
    }

    private void viewButtonPressed(JournalEntry entry) {
        ViewFrame viewFrame = new ViewFrame();
        viewFrame.initialize(entry);
    }

    private void deleteButtonPressed(JournalEntry entry, JPanel entryPanel) {
        /* Get user confirmation */
        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this entry?", "Select an Option", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            journal.deleteEntry(entry);
            bottomPanel.remove(entryPanel);

            revalidate();
            repaint();
        }
    }

    private void newButtonPressed() {
        AddFrame addFrame = new AddFrame();
        addFrame.initialize(this);
    }

    public void addNewEntry(String text) {
        journal.addEntry(text);
        JPanel entryPanel = createEntryPanel(journal.getEntries().get(journal.getEntries().size()-1));
        bottomPanel.add(entryPanel, 0);

        revalidate();
        repaint();
    }
}

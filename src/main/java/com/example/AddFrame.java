package com.example;

import javax.swing.*;
import java.awt.*;
public class AddFrame extends JFrame {
    final private Font titleFont = new Font("Arial", Font.BOLD, 30);
    final private Font textFont = new Font("Arial", Font.PLAIN, 18);

    private JTextArea textArea;
    private JournalFrame journalFrame;

    public void initialize(JournalFrame frame) {
        journalFrame = frame;

        /* Main Panel */
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.black);
        mainPanel.setLayout(new BorderLayout());

        /* Top Panel */
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.gray);
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        //topPanel.setLayout(new BorderLayout());

        /* Title Label */
        JLabel titleLabel = new JLabel("Add an Entry");
        titleLabel.setFont(titleFont);
        topPanel.add(titleLabel);

        /* Spacing */
        topPanel.add(Box.createHorizontalStrut(100));

        /* Done Button */
        JButton doneButton = new JButton("Done");
        doneButton.setPreferredSize(new Dimension(80, 40));
        doneButton.addActionListener(e -> doneButtonPressed());
        topPanel.add(doneButton);

        /* Cancel Button */
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 40));
        cancelButton.addActionListener(e -> cancelButtonPressed());
        topPanel.add(cancelButton);

        /* Bottom Panel */
        JPanel bottomPanel = new JPanel();
        bottomPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
        bottomPanel.setLayout(new BorderLayout());

        /* Text Area */
        textArea = new JTextArea("");
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(true);
        textArea.setOpaque(false);
        textArea.setFont(textFont);
        textArea.setForeground(Color.white);
        bottomPanel.add(textArea, BorderLayout.NORTH);

        /* Scroll Pane */
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getViewport().setBackground(Color.black);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        /* Add Panels to Main Panel */
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        /* Set Frame Details */
        add(mainPanel);
        setTitle("New Entry");
        setSize(800, 500);
        setMinimumSize(new Dimension(500,300));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        textArea.requestFocus();
        //pack();
    }

    private void doneButtonPressed() {
        if (textArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cannot create an empty entry!");
        }
        else {
            journalFrame.addNewEntry(textArea.getText());
            dispose();
        }
    }

    private void cancelButtonPressed() {
        /* Get user confirmation */
        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Select an Option", JOptionPane.YES_NO_OPTION);
        if (input == 0) {
            dispose();
        }
    }
}

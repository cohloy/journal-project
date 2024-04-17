package com.mycompany.journal;

import javax.swing.*;
import java.awt.*;
public class ViewFrame extends JFrame {
    final private Font dateFont = new Font("Arial", Font.BOLD, 30);
    final private Font textFont = new Font("Arial", Font.PLAIN, 18);

    public void initialize(JournalEntry entry) {
        /* Main Panel */
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.black);
        mainPanel.setLayout(new BorderLayout());

        /* Top Panel */
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.gray);
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        topPanel.setLayout(new BorderLayout());

        /* Date Label */
        JLabel dateLabel = new JLabel(entry.getDateString());
        dateLabel.setFont(dateFont);
        topPanel.add(dateLabel);

        /* Bottom Panel */
        JPanel bottomPanel = new JPanel();
        bottomPanel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
        bottomPanel.setLayout(new BorderLayout());

        /* Text Area */
        JTextArea textArea = new JTextArea(entry.getText());
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
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
        setTitle(entry.getDateString());
        setSize(800, 500);
        setMinimumSize(new Dimension(500,300));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }
}

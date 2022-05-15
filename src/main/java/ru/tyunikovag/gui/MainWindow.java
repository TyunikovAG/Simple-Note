package ru.tyunikovag.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainWindow extends JFrame{

    private JPanel mainPanel;
    private JPanel notesPanel;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private Button addButton;
    private Button saveButton;
    private Button loadButton;


    public MainWindow(){
        initGui();
    }

    private void initGui() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);
        this.setTitle("Simple Note");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        notesPanel = new JPanel();
        notesPanel.setBackground(Color.CYAN);
        scrollPane = new JScrollPane(notesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        addButton = new Button("Add note");
        addButton.setPreferredSize(new Dimension(100, 50));
        addButton.addActionListener(e -> addClicked(e));
        saveButton = new Button("Save notes");
        saveButton.setPreferredSize(new Dimension(100, 50));
        saveButton.addActionListener(e -> saveClicked(e));
        loadButton = new Button("Load notes");
        loadButton.setPreferredSize(new Dimension(100, 50));
        loadButton.addActionListener(e -> loadClicked(e));
        buttonPanel = new JPanel();
        LayoutManager btnPanelLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(btnPanelLayout);
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        mainPanel.add(notesPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        this.setMinimumSize(new Dimension(400, 400));
        this.pack();
    }

    private void addClicked(ActionEvent e) {

    }

    private void saveClicked(ActionEvent e) {

    }

    private void loadClicked(ActionEvent e) {

    }


}
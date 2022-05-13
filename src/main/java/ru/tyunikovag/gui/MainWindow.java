package ru.tyunikovag.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame{

    private JPanel mainPanel;

    public MainWindow(){
        initGui();
    }

    private void initGui() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);
        this.setTitle("Simple Note");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setMinimumSize(new Dimension(400, 400));
        this.pack();
    }
}
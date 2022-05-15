package ru.tyunikovag.gui;

import ru.tyunikovag.model.Note;
import ru.tyunikovag.providers.SimpleNotesStoreProvider;
import ru.tyunikovag.providers.StoreProvider;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel notesPanel;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private Button btnAdd;
    private Button btnSave;
    private Button btnLoad;

    private static List<Note> notes = new LinkedList<>();
    private static final StoreProvider storeProvider = new SimpleNotesStoreProvider();

    public MainWindow() {
        initGui();
        loadNotes();
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

        btnAdd = new Button("Add note");
        btnAdd.setPreferredSize(new Dimension(100, 50));
        btnAdd.addActionListener(e -> addClicked(e));
        btnSave = new Button("Save notes");
        btnSave.setPreferredSize(new Dimension(100, 50));
        btnSave.addActionListener(e -> saveNotes());
        btnLoad = new Button("Load notes");
        btnLoad.setPreferredSize(new Dimension(100, 50));
        btnLoad.addActionListener(e -> loadNotes());
        buttonPanel = new JPanel();
        LayoutManager btnPanelLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(btnPanelLayout);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnLoad);

        mainPanel.add(notesPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        this.setMinimumSize(new Dimension(600, 400));
        this.pack();
    }

    private void addClicked(ActionEvent e) {

    }

    private void saveNotes() {
        storeProvider.saveNotes(notes);
    }

    private void loadNotes() {
        notes = storeProvider.loadNotes();
        for (Note note : notes) {
            addNoteToPanel(note);
        }
        mainPanel.repaint();
    }

    private void addNoteToPanel(Note note) {

        JPanel oneNotePanel = new JPanel();
        oneNotePanel.setMinimumSize(new Dimension(200, 100));
        oneNotePanel.setLayout(new BorderLayout());

        JTextArea noteTextArea = new JTextArea(note.getText());
        noteTextArea.setLineWrap(true);
        noteTextArea.setColumns(24);
        if(note.getFont() != null){
            noteTextArea.setFont(note.getFont());
        } else {
            noteTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        }

        JButton btnEdit = new JButton("Edit");
        // TODO: 15.05.2022 implement edit listener
//        btnEdit.addActionListener();

        oneNotePanel.add(noteTextArea, BorderLayout.CENTER);
        oneNotePanel.add(btnEdit, BorderLayout.EAST);

        notesPanel.add(oneNotePanel);
    }


}
package ru.tyunikovag.gui;

import ru.tyunikovag.model.Note;
import ru.tyunikovag.model.SimpleNote;
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
        SimpleNote note = new SimpleNote("");
        notes.add(note);
        addNoteToPanel(note, true);
        Component[] items = notesPanel.getComponents();
        repaintFrame();
    }

    private void saveNotes() {
        storeProvider.saveNotes(notes);
    }

    private void loadNotes() {
        notes = storeProvider.loadNotes();
        for (Note note : notes) {
            addNoteToPanel(note, false);
        }
        repaintFrame();
    }

    private void addNoteToPanel(Note note, boolean editable) {

        JPanel oneNotePanel = new JPanel();
        oneNotePanel.setMinimumSize(new Dimension(200, 100));
        oneNotePanel.setLayout(new BorderLayout());

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(e -> editButtonAction(e));

        JTextArea noteTextArea = new JTextArea(note.getText());
        noteTextArea.setLineWrap(true);
        noteTextArea.setColumns(24);
        noteTextArea.setBackground(Color.LIGHT_GRAY);
        if (note.getFont() != null) {
            noteTextArea.setFont(note.getFont());
        } else {
            noteTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        }
        noteTextArea.setEditable(editable);
        if (editable) {
            noteTextArea.setBackground(Color.WHITE);
            btnEdit.setText("Save");
        }

        oneNotePanel.add(noteTextArea, BorderLayout.CENTER);
        oneNotePanel.add(btnEdit, BorderLayout.EAST);

        notesPanel.add(oneNotePanel);
    }

    private void editButtonAction(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        JPanel oneNotePanel = (JPanel) btn.getParent();
        JPanel notesPanel = (JPanel) oneNotePanel.getParent();
        Component[] components = notesPanel.getComponents();
        int index = 0;
        for (int i = 0; i < components.length; i++) {
            if (components[i] == oneNotePanel) {
                index = i;
                break;
            }
        }
        Note currentNote = notes.get(index);
        JTextArea textArea = (JTextArea) btn.getParent().getComponents()[0];
        if (btn.getText().equals("Save")) {
            currentNote.setText(textArea.getText());
            btn.setText("Edit");
            textArea.setEditable(false);
            textArea.setBackground(Color.LIGHT_GRAY);
            saveNotes();
        } else {
            btn.setText("Save");
            textArea.setEditable(true);
            textArea.setBackground(Color.WHITE);
        }
    }

    private void repaintFrame() {
        mainPanel.invalidate();
        mainPanel.validate();
        mainPanel.repaint();
    }

}
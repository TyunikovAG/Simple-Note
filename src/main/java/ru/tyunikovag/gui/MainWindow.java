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
        btnAdd.addActionListener(this::addClicked);
        btnSave = new Button("Save notes");
        btnSave.setPreferredSize(new Dimension(100, 50));
        btnSave.addActionListener(e -> saveNotes());
        buttonPanel = new JPanel();
        LayoutManager btnPanelLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
        buttonPanel.setLayout(btnPanelLayout);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSave);

        mainPanel.add(notesPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        this.setMinimumSize(new Dimension(600, 400));
        this.pack();
    }

    private void addClicked(ActionEvent e) {
        SimpleNote note = new SimpleNote("");
        notes.add(note);
        addNoteToPanel(note, true);
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
        oneNotePanel.setLayout(new FlowLayout());

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(this::editButtonAction);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this::deleteButtonAction);

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

        oneNotePanel.add(noteTextArea);
        oneNotePanel.add(btnEdit);
        oneNotePanel.add(btnDelete);

        notesPanel.add(oneNotePanel);
    }

    private void deleteButtonAction(ActionEvent e) {
        int input = JOptionPane.showConfirmDialog(null,
                "Are you sure to delete?", "Select an Option...",JOptionPane.YES_NO_OPTION);

        if (input == 0) {
            int index = getNoteIndex(e);
            notes.remove(index);
            saveNotes();
            notesPanel.remove(index);
            repaintFrame();
        }
    }

    private void editButtonAction(ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        Note currentNote = notes.get(getNoteIndex(e));
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

    private int getNoteIndex(ActionEvent e){
        JButton btn = (JButton) e.getSource();
        JPanel oneNotePanel = (JPanel) btn.getParent();
        JPanel notesPanel = (JPanel) oneNotePanel.getParent();
        Component[] components = notesPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] == oneNotePanel) {
                return i;
            }
        }
        return 0;
    }

    private void repaintFrame() {
        mainPanel.invalidate();
        mainPanel.validate();
        mainPanel.repaint();
    }

}
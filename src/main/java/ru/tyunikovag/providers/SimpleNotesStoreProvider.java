package ru.tyunikovag.providers;

import ru.tyunikovag.model.Note;
import ru.tyunikovag.model.SimpleNote;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SimpleNotesStoreProvider implements StoreProvider{

    private static final File dataFile = new File("data");
    private List<Note> notes = new LinkedList<>();

    @Override
    public void saveNotes(List<Note> notes) {
        try {
            this.notes = notes;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataFile));
            objectOutputStream.writeObject(this.notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Note> loadNotes() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data"));
            this.notes = (List<Note>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            createDefaultNotesList();
        } finally {
            return this.notes;
        }
    }

    private void createDefaultNotesList() {
        this.notes = Collections.emptyList();
        this.notes.add(new SimpleNote("Default simple note"));
    }
}

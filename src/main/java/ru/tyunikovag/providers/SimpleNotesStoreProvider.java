package ru.tyunikovag.providers;

import ru.tyunikovag.model.Note;

import java.util.List;

public class SimpleNotesStoreProvider implements StoreProvider{

    @Override
    public void saveNotes(List<Note> notes) {

    }

    @Override
    public List<Note> loadNotes() {
        return null;
    }
}

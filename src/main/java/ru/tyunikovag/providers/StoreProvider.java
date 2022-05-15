package ru.tyunikovag.providers;

import ru.tyunikovag.model.Note;

import java.util.List;

public interface StoreProvider {

    void saveNotes(List<Note> notes);
    List<Note> loadNotes();
}

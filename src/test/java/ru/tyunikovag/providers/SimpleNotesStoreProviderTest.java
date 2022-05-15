package ru.tyunikovag.providers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.tyunikovag.model.Note;
import ru.tyunikovag.model.SimpleNote;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleNotesStoreProviderTest {

    private List<Note> notes;

    @Before
    public void init() {
        File file = new File("data");
        if (file.exists()) {
            file.delete();
        }
        notes = Arrays.asList(new SimpleNote[]{
                new SimpleNote("Note #1"),
                new SimpleNote("Note #2"),
                new SimpleNote("Note #3")
        });
    }

    @Test
    public void checkCreatingDataFile() {
        try {
            prepareDataFile();
            assertTrue(new File("data").exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadNotesTest() {
        try {
            prepareDataFile();
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data"));
            List<Note> object = (List<Note>) objectInputStream.readObject();
            Assert.assertEquals(object, notes);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void prepareDataFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("data"));
            objectOutputStream.writeObject(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
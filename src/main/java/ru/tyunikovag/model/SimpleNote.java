package ru.tyunikovag.model;

import java.awt.*;
import java.io.Serializable;

public class SimpleNote implements Note, Serializable {

    public SimpleNote() {

    }

    public SimpleNote(String noteText) {
        this.noteText = noteText;
    }

    private static final long serialVersionUID = 1L;

    private String noteText;
    private Font font;

    @Override
    public String getText() {
        return noteText;
    }

    @Override
    public void setText(String text) {
        this.noteText = text;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleNote that = (SimpleNote) o;

        if (noteText != null ? !noteText.equals(that.noteText) : that.noteText != null) return false;
        return font != null ? font.equals(that.font) : that.font == null;
    }

    @Override
    public int hashCode() {
        int result = noteText != null ? noteText.hashCode() : 0;
        result = 31 * result + (font != null ? font.hashCode() : 0);
        return result;
    }
}

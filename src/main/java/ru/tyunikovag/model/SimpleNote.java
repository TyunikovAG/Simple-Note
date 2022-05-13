package ru.tyunikovag.model;

import java.awt.*;

public class SimpleNote implements Note{

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
}

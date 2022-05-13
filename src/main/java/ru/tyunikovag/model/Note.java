package ru.tyunikovag.model;

import java.awt.*;

public interface Note {

    String getText();
    void setText(String text);

    Font getFont();
    void setFont(Font font);
}

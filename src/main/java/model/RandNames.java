package model;

import org.jetbrains.annotations.NotNull;

/**
 * Repräsentiert einen einzelnen Teilnehmer des Gewinnspiels.
 * Diese Klasse enthält ausschließlich den Namen und bietet Zugriffsmethoden,
 * um den Namen zu setzen oder auszulesen.
 */

public class RandNames implements CharSequence {

    private String name;

    public RandNames(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }
        this.name = name;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
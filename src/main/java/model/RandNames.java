package model;

public class RandNames {

    private String name;

    public RandNames(String name) {
        setName(name); // nutzt direkt den Setter mit Validierung
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
    public String toString() {
        return name;
    }
}

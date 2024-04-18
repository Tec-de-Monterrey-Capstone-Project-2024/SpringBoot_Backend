package com.springboot.connectmate.models;

public class Recommendation {
    private String ID;
    private String Description;

    public Recommendation(String ID, String description) {
        this.ID = ID;
        this.Description = description;
    }

    // Getters y setters (pueden ser generados automáticamente por tu IDE)
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

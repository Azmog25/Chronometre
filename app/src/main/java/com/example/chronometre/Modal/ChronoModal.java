package com.example.chronometre.Modal;

public class ChronoModal {
    private String chronoName;
    private String chronoTimer;
    private int id;

    public ChronoModal(String chronoName, String chronoTimer) {
        this.chronoName = chronoName;
        this.chronoTimer = chronoTimer;
    }

    public String getChronoName() {
        return chronoName;
    }

    public void setChronoName(String chronoName) {
        this.chronoName = chronoName;
    }

    public String getChronoTimer() {
        return chronoTimer;
    }

    public void setChronoTimer(String chronoTimer) {
        this.chronoTimer = chronoTimer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

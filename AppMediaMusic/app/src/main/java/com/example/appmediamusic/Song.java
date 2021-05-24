package com.example.appmediamusic;

public class Song {
    private String Titile;
    private int File;

    public Song(String titile, int file) {
        Titile = titile;
        File = file;
    }

    public String getTitile() {
        return Titile;
    }

    public void setTitile(String titile) {
        Titile = titile;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }
}

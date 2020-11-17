package com.amworks.miwokapp;

public class Word {
    public String english;
    public String miwok;
    public int resID;
    public int soundID;

    public Word(String english, String miwok) {
        this.english = english;
        this.miwok = miwok;
    }

    public Word(String english, String miwok, int resID,int soundID) {
        this.english = english;
        this.miwok = miwok;
        this.resID=resID;
        this.soundID=soundID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "english='" + english + '\'' +
                ", miwok='" + miwok + '\'' +
                ", resID=" + resID +
                ", soundID=" + soundID +
                '}';
    }
}

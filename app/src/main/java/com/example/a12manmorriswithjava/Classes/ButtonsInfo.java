package com.example.a12manmorriswithjava.Classes;

import android.widget.Button;

public class ButtonsInfo {
    private boolean clicked;
    private Button btn;
    private String btnsState;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBtnsState() {
        return btnsState;
    }

    public void setBtnsState(String btnsState) {
        this.btnsState = btnsState;
    }

    public ButtonsInfo(Button btn) {
        this.btn = btn;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}

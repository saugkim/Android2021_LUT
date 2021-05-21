package org.lut.simplenotepad;

import java.util.ArrayList;

public class Note {

    private String title, content;

    public Note(){

    }
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}

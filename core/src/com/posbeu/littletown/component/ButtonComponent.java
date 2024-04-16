package com.posbeu.littletown.component;

import com.badlogic.ashley.core.Component;

public class ButtonComponent implements Component {
    private final String title;

    public ButtonComponent(String title){
        this.title=title;
    }
}

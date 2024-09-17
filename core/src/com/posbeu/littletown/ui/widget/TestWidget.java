package com.posbeu.littletown.ui.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class TestWidget extends Actor {
    Label label;

    public TestWidget() {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("default.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.RED;

        label = new Label("ddddd", label1Style);
    }

    @Override
    public void act(float delta) {
        label.act(delta);
        label.setText("ddddd : ");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        label.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        label.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        label.setSize(width, height);
    }
}

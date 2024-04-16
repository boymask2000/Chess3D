package com.posbeu.littletown.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.posbeu.littletown.GameWorld;
import com.posbeu.littletown.component.ButtonComponent;
import com.posbeu.littletown.component.StatusComponent;

import java.util.Iterator;

public class ButtonSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private GameWorld gameWorld;

    public ButtonSystem(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void addedToEngine(Engine engine) {

        entities = engine.getEntitiesFor(Family.all(ButtonComponent.class).get());
    }


}
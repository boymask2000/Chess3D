package com.posbeu.littletown.action;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MoveAction {


    public static MoveVector3ToAction moveVector3To (Vector3 target, float x, float y, float z, float duration){
        return moveVector3To(target, x, y, z, duration, null);
    }

    public static MoveVector3ToAction moveVector3To (Vector3 target, float x, float y, float z, float duration, Interpolation interpolation){
        MoveVector3ToAction action = Actions.action(MoveVector3ToAction.class);
        action.setTarget(target);
        action.setPosition(x, y, z);
        action.setDuration(duration);
        action.setInterpolation(interpolation);
        return action;
    }

}
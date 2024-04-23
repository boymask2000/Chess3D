package com.posbeu.littletown.action;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class MoveVector3ToAction extends TemporalAction {

    private Vector3 target;
    private float startX, startY, startZ, endX, endY, endZ;

    protected void begin(){
        startX = target.x;
        startY = target.y;
        startZ = target.z;
    }

    protected void update (float percent) {
        target.set(startX + (endX - startX) * percent, startY + (endY - startY) * percent, startZ + (endZ - startZ) * percent);
    }

    public void reset(){
        super.reset();
        target = null; //must clear reference for pooling purposes
    }

    public void setTarget(Vector3 target){
        this.target = target;
    }

    public void setPosition(float x, float y, float z){
        endX = x;
        endY = y;
        endZ = z;
    }
}
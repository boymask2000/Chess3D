package com.posbeu.littletown;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector3;

public class Terrain {
    ImmediateModeRenderer20 lineRenderer = new ImmediateModeRenderer20(false, true, 0);
    private PerspectiveCamera camera;

    public void render(float delta) {
        Vector3 vec = Util.getPoint(0, 0, camera);
        Vector3 vecEnd = Util.getPoint((int) LittleTown.VIRTUAL_WIDTH, (int) LittleTown.VIRTUAL_HEIGHT, camera);

        for (float x = vec.x; x < vec.x + 500; x += 10)
            for (float z = vec.z-500; z < vec.z + 500; z += 10) {
               line(x, 0, z, x, 0, z+10);
                line(x, 0, z, x+10, 0 , z);
            }
       /* for (float x = vec.x; x < vecEnd.x ; x += 5)
            for (float z = vec.z; z < vecEnd.z ; z += 5) {
                line(x, 0, z, x, 0, z+10);
                line(x, 0, z, x+10, 0 , z);
            }*/

    }

    private void line(float x1, float y1, float z1,
                      float x2, float y2, float z2) {
        float r = 128;
        float g = 128;
        float b = 128;
        float a = 0;  lineRenderer.begin(camera.combined, GL30.GL_LINES);
        lineRenderer.color(r, g, b, a);
        lineRenderer.vertex(x1, y1, z1);
        lineRenderer.color(r, g, b, a);
        lineRenderer.vertex(x2, y2, z2);    lineRenderer.end();
    }

    public void setCamera(PerspectiveCamera camera) {
        this.camera = camera;
    }
}

package com.example.grafikakomputer_individu1;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CreatePoints {
    public float X1, Y1, X2, Y2;
    /** * Constructo */
    public CreatePoints() {
        X1 = 0;
        X2 = 0;
        Y2 = 0;
        Y1 = 0;
    }
    // Point to our vertex buffer, return buffer holding    the vertices

    public static FloatBuffer makeFloatBuffer(float[] arr) {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);

        return fb;
    }

    /**
     * The draw method for the primitive
     * object with
     * the
     * GL context
     */

    public void draw_points(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //     set the global colour for all the points
        gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
        // List Point to our vertex buffer with manually
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new
                float[]{15.0f, 15.0f, 0.0f,  // V1 -  first vertex (x,y,z)
                1.0f, 0.8f, 0.0f,  // V2
                1.0f, 0.6f, 0.0f,  // V3
                1.0f, 0.4f, 0.0f,  // V4
                1.0f, 0.2f, 0.0f,  // V5
                1.0f, 0.0f, 0.0f,  // V6
                1.0f, -0.2f, 0.0f,  // V7
                1.0f, -0.4f, 0.0f,  // V8
                1.0f, -0.6f, 0.0f,  // V9
                1.0f, -0.8f, 0.0f,  // V10
                1.0f, -1.0f, 0.0f,  // V11
                0.8f, -1.0f, 0.0f,  // V12
                0.6f, -1.0f, 0.0f,  // V13
                0.4f, -1.0f, 0.0f,  // V14
                0.2f, -1.0f, 0.0f,  // V15
                0.0f, -1.0f, 0.0f,  // V16
                -0.2f, -1.0f, 0.0f, // V17
                -0.4f, -1.0f, 0.0f, // V18
                -0.6f, -1.0f, 0.0f, // V19
                -0.8f, -1.0f, 0.0f, // V20
                -15.0f, -15.0f, 0.0f, // V21
                0.0f, 0.0f, 0.0f,//V22 di lokasi 0.0
        }));


        // Draw the vertices as points
        gl.glDrawArrays(GL10.GL_POINTS, 0, 22); // 0 is start index, 22 is length of list points
        //Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    public void draw_points2(GL10 gl, float coord_x, float coord_y, float coord_z) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // set the global colour for all the points
        gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
        // List Point to our vertex buffer with manually
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new
                        float[]{
                        coord_x,
                        coord_y,
                        coord_z
                        // V1 - first vertex (x,y,z)
                }
        ));
        // Draw the vertices as points
        gl.glDrawArrays(GL10.GL_POINTS, 0, 1); // 0 is start index, 22 is length of list points

        //Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        Log.d(TAG, "point x = " + coord_x + " y = " + coord_y + " z =");
        Log.d(TAG, " y = " + coord_x + " y = " + coord_y + " z =");
    }

} 
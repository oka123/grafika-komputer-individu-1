package com.example.grafikakomputer_individu1;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ESRender implements GLSurfaceView.Renderer {
    private CreatePoints points_object; // the object to be drawn
   /* private Line line_object;
    private garisDDA garisDDA;
    private Bressenham bressenham;
    private final Bidang bidang_objetc;*/
    public ESRender(){
        /* code for Create Points Class*/
        this.points_object = new CreatePoints();
       /* this.garisDDA = new garisDDA();
        this.bressenham = new Bressenham();

        this.line_object= new Line();
        //============ set parameter to generate vertices  to create dynamic point  ==========================
        this.bidang_objetc = new Bidang();*/
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        points_object = new CreatePoints();
        /*line_object= new Line();
        garisDDA = new garisDDA();
        bressenham = new Bressenham();*/
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0)
            height = 1; // To prevent divide by zero
        float aspect = (float) width / height;
        // Set the viewport (display area) to cover the  entire window
        gl.glViewport(0, 0, width, height);
        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION);// Select  projection      matrix
        gl.glLoadIdentity();// Reset projection matrix
        //  Use perspective projection
        GLU.gluPerspective(gl,  45, aspect, 0.1f, 100.f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);// Select    model-view matrix
        gl.glLoadIdentity();// Reset
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        /*fungsi untuk menampilkan isi dari kontainer*/
        //gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // set        background with white color
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); //        set background with black color
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // clear Screen and Depth Buffer
        // display drawing points

        // Menggambar titik  dengan Opengl (koordinat titik telah tersedia)===========================================
        gl.glPushMatrix();  // start freeze state/event to each object
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glPointSize(20);
        gl.glEnable(GL10.GL_POINT_SMOOTH);
            points_object.draw_points(gl);// draw all points from the vertex list
        gl.glPopMatrix();  // end freeze state/event to each object

        // Menggambar titik  dengan Opengl (koordinat titik telah tersedia)===========================================
        gl.glPushMatrix();  // start freeze state/event to each object
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glPointSize(30);
        gl.glEnable(GL10.GL_POINT_SMOOTH);
            points_object.draw_points2 (gl, -1.0f,-1.0f,0.0f);// draw all points from the vertex list
            points_object.draw_points2 (gl, -1.0f,1.0f,0.0f);// draw all points from the vertex list
            points_object.draw_points2 (gl,  1.0f,1.0f,0.0f);// draw all points from the vertex list
            points_object.draw_points2 (gl,  1f,1.5f,0.0f);// draw all points from the vertex list
            points_object.draw_points2 (gl,  1f,2f,0.0f);// draw all points from the vertex list

        gl.glPopMatrix();  // end freeze state/event to each object

    }
}

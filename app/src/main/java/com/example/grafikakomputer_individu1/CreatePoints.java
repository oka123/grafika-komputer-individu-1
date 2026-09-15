package com.example.grafikakomputer_individu1;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CreatePoints {
    public float X1, Y1, X2, Y2;

    /**
     * Constructor
     */
    public CreatePoints() {
        X1 = 0;
        X2 = 0;
        Y2 = 0;
        Y1 = 0;
    }

    // Point to our vertex buffer, return buffer holding the vertices
    public static FloatBuffer makeFloatBuffer(float[] arr) {
        ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);

        return fb;
    }

    /**
     * The draw method for the primitive object with the GL context
     *
     * ================= TUGAS 1 =================
     * 1) Lokasi titik ke-1  : (1.0f, 1.0f, 0.0f)   -> pojok kanan atas
     *    Lokasi titik ke-10 : (1.0f, -0.8f, 0.0f)  -> sisi kanan, dekat bawah
     *    Lokasi titik ke-22 : (0.0f, 0.0f, 0.0f)   -> pusat/tengah layar
     * 2) Warna titik diubah menjadi MERAH -> glColor4f(1.0f, 0.0f, 0.0f, 1.0f)
     * 3) Jumlah titik ditambah dari 22 menjadi 24. Dua titik baru:
     *    Titik ke-23 : (-1.0f, -0.8f, 0.0f) -> melanjutkan sisi kiri persegi, naik dari V21
     *    Titik ke-24 : (-1.0f, -0.6f, 0.0f) -> melanjutkan sisi kiri persegi, naik lagi
     * =============================================
     */
    public void draw_points(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // set the global colour for all the points -> MERAH (R=1, G=0, B=0)
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        // List Point to our vertex buffer with manually
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new
                float[]{1.0f, 1.0f, 0.0f,   // V1  - lokasi titik ke-1  (pojok kanan atas)
                1.0f, 0.8f, 0.0f,   // V2
                1.0f, 0.6f, 0.0f,   // V3
                1.0f, 0.4f, 0.0f,   // V4
                1.0f, 0.2f, 0.0f,   // V5
                1.0f, 0.0f, 0.0f,   // V6
                1.0f, -0.2f, 0.0f,  // V7
                1.0f, -0.4f, 0.0f,  // V8
                1.0f, -0.6f, 0.0f,  // V9
                1.0f, -0.8f, 0.0f,  // V10 - lokasi titik ke-10 (sisi kanan, dekat bawah)
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
                -1.0f, -1.0f, 0.0f, // V21
                0.0f, 0.0f, 0.0f,   // V22 - lokasi titik ke-22 (pusat/tengah layar)
                -1.0f, -0.8f, 0.0f, // V23 - titik baru, melanjutkan sisi kiri persegi
                -1.0f, -0.6f, 0.0f, // V24 - titik baru, melanjutkan sisi kiri persegi
        }));

        // Draw the vertices as points
        gl.glDrawArrays(GL10.GL_POINTS, 0, 24); // 0 is start index, 24 is jumlah titik (setelah ditambah)
        //Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    /**
     * Menggambar ulang 3 titik yang diminta di Tugas 1 (titik ke-1, ke-10, ke-22)
     * dengan ukuran lebih besar dari titik lainnya, supaya mudah dikenali.
     * Warna tetap MERAH, hanya glPointSize yang dibedakan (diatur dari ESRender
     * sebelum method ini dipanggil).
     *
     * Lokasi titik ke-1  : (1.0f, 1.0f, 0.0f)
     * Lokasi titik ke-10 : (1.0f, -0.8f, 0.0f)
     * Lokasi titik ke-22 : (0.0f, 0.0f, 0.0f)
     */
    public void draw_highlight_points(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // warna tetap MERAH
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new
                float[]{
                1.0f, 1.0f, 0.0f,   // titik ke-1
                1.0f, -0.8f, 0.0f,  // titik ke-10
                0.0f, 0.0f, 0.0f,   // titik ke-22
        }));
        gl.glDrawArrays(GL10.GL_POINTS, 0, 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    public void draw_points2(GL10 gl, float coord_x, float coord_y, float coord_z) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // set the global colour for all the points -> MERAH (R=1, G=0, B=0)
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
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
        gl.glDrawArrays(GL10.GL_POINTS, 0, 1);

        //Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        Log.d(TAG, "point x = " + coord_x + " y = " + coord_y + " z = " + coord_z);
    }

}
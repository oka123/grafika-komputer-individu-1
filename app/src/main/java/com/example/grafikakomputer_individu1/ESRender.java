package com.example.grafikakomputer_individu1;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ESRender implements GLSurfaceView.Renderer {
    private CreatePoints points_object; // Objek titik yang akan digambar

    // FOV vertikal dalam derajat
    private static final float FOV_Y = 45.0f;

    // Batas koordinat objek (bounding box) agar seluruh titik muat di layar
    // X dari -1.0f hingga 1.0f (lebar = 2.0f)
    // Y dari -1.0f hingga 2.0f (tinggi = 3.0f)
    private static final float BOUNDS_WIDTH = 2.0f;
    private static final float BOUNDS_HEIGHT = 3.0f;
    private static final float CENTER_Y = 0.5f; // Titik tengah vertikal: (-1.0 + 2.0) / 2
    private static final float PADDING = 0.5f;   // Margin ekstra agar titik dengan pointSize besar tidak terpotong

    // Jarak kamera (Z) yang dihitung secara adaptif
    private float cameraDistance = 5.0f;

    public ESRender() {
        /* Inisialisasi objek titik */
        this.points_object = new CreatePoints();
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        points_object = new CreatePoints();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) {
            height = 1; // Mencegah pembagian dengan nol
        }
        float aspect = (float) width / height;

        // Atur viewport sesuai ukuran layar penuh
        gl.glViewport(0, 0, width, height);

        // Setup matriks proyeksi perspektif
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, FOV_Y, aspect, 0.1f, 100.0f);

        // Hitung jarak kamera Z adaptif agar semua titik masuk ke dalam frustum layar
        // Rumus: visible_half_size = distance * tan(fov / 2)
        float halfFovRad = (float) Math.toRadians(FOV_Y / 2.0);
        float tanHalfFov = (float) Math.tan(halfFovRad);

        float requiredHeight = BOUNDS_HEIGHT + PADDING;
        float requiredWidth = BOUNDS_WIDTH + PADDING;

        // Jarak yang dibutuhkan untuk dimensi tinggi (Y)
        float distForHeight = (requiredHeight / 2.0f) / tanHalfFov;

        // Jarak yang dibutuhkan untuk dimensi lebar (X) berdasarkan aspect ratio
        float distForWidth = (requiredWidth / 2.0f) / (aspect * tanHalfFov);

        // Ambil jarak terbesar agar baik dimensi X maupun Y tidak terpotong pada device manapun
        cameraDistance = Math.max(distForHeight, distForWidth);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Bersihkan layar dengan warna hitam
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // 1) Menggambar 24 titik utama (warna merah, ukuran 20) =========================
        gl.glPushMatrix();
        // Pusatkan kamera ke titik tengah objek (CENTER_Y) dan jarak Z adaptif
        gl.glTranslatef(0.0f, -CENTER_Y, -cameraDistance);
        gl.glPointSize(20);
        gl.glEnable(GL10.GL_POINT_SMOOTH);
        points_object.draw_points(gl); // Gambar 24 titik
        gl.glPopMatrix();

        // 2) Menggambar ulang titik ke-1, ke-10, ke-22 dengan ukuran lebih besar (ukuran 50) ===
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -CENTER_Y, -cameraDistance);
        gl.glPointSize(50); // Ukuran lebih besar agar menjadi highlight
        gl.glEnable(GL10.GL_POINT_SMOOTH);
        points_object.draw_highlight_points(gl); // Titik 1, 10, 22 diperbesar
        gl.glPopMatrix();

        // 3) Menggambar titik tambahan dengan draw_points2 (ukuran 30) ==================
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -CENTER_Y, -cameraDistance);
        gl.glPointSize(30);
        gl.glEnable(GL10.GL_POINT_SMOOTH);
        points_object.draw_points2(gl, -1.0f, -1.0f, 0.0f);
        points_object.draw_points2(gl, -1.0f, 1.0f, 0.0f);
        points_object.draw_points2(gl, 1.0f, 1.0f, 0.0f);
        points_object.draw_points2(gl, 1.0f, 1.5f, 0.0f);
        points_object.draw_points2(gl, 1.0f, 2.0f, 0.0f);
        gl.glPopMatrix();
    }
}
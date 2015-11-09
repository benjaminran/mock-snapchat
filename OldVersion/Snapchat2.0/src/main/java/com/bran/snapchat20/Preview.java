package com.bran.snapchat20;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.IOException;

/** * Surface on which the camera projects it's capture results. */
class Preview extends SurfaceView implements SurfaceHolder.Callback {
    private static Context context;
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mCamera = getCameraInstance();
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if(mCamera==null)  {
            Toast.makeText(context, "Failed to open camera.", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            mCamera.setPreviewDisplay(holder); // null?
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        if(mCamera==null) {
            Toast.makeText(context, "Failed to open camera.", Toast.LENGTH_LONG).show();
            return;
        }
        if (mHolder.getSurface() == null){ return; }
        // stop preview before making changes
        try {  mCamera.stopPreview(); }
        catch (Exception e){}
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mCamera == null) return;
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try { c = Camera.open(); }
        catch (Exception e){
            Toast.makeText(context, "Failed to open camera.", Toast.LENGTH_LONG).show();
        }
        return c;
    }
}

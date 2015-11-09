package com.bran.snapchat20;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by beni on 2/5/15.
 */
public class CameraFragment extends Fragment {
    private Preview preview;
    private ImageView flipCameraButton, toggleFlashButton, captureImageButton, cameraToInbox, cameraToFriends;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).goFullScreen();
        View layout = inflater.inflate(R.layout.fragment_camera, container, false);
        initUi(layout);

        return layout;
    }

    private void initUi(View layout) {
        preview = (Preview) getActivity().findViewById(R.id.camera_preview);
        flipCameraButton = (ImageView) layout.findViewById(R.id.camera_flip);
        flipCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateUpperButton(view);
                // Flip camera
            }
        });
        toggleFlashButton = (ImageView) layout.findViewById(R.id.camera_toggle_flash);
        toggleFlashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateUpperButton(view);
                // Toggle flash
            }
        });
        captureImageButton = (ImageView) layout.findViewById(R.id.camera_capture_image_button);
        captureImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Image Captured", Toast.LENGTH_LONG).show();
            }
        });
        cameraToInbox = (ImageView) layout.findViewById(R.id.camera_to_inbox);
        cameraToInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateLowerButton(view);
                // scroll to inbox
                ((MainActivity) getActivity()).goToPage(MainActivity.INBOX_POSITION);
            }
        });
        cameraToFriends = (ImageView) layout.findViewById(R.id.camera_to_friends);
        cameraToFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateLowerButton(view);
                // scroll to friends
                ((MainActivity) getActivity()).goToPage(MainActivity.FRIENDS_POSITION);
            }
        });
    }

    private void animateUpperButton(View view) {
        Animation scale = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(500);
        scale.setInterpolator(new CycleInterpolator(1));
        view.startAnimation(scale);
    }

    private void animateLowerButton(View view) {
        Animation scale = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(500);
        view.startAnimation(scale);
    }

}

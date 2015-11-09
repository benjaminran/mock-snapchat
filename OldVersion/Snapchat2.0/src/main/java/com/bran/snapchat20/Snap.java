package com.bran.snapchat20;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by beni on 2/6/15.
 */
public class Snap {
    private static final Random generator = new Random();

    private static final int PIC_OPENED = R.drawable.snap_pic_opened;
    private static final int PIC_UNOPENED = R.drawable.snap_pic_unopened;
    private static final int VIDEO_OPENED = R.drawable.snap_video_opened;
    private static final int VIDEO_UNOPENED = R.drawable.snap_video_unopened;
    private static final int CHAT_OPENED = R.drawable.snap_chat_opened;
    private static final int CHAT_UNOPENED = R.drawable.snap_chat_unopened;

    private int indicatorResource;

    // Type of most recent communication
    public enum ContentType {
        PICTURE, VIDEO, CHAT;
    }

    ContentType recentComm;
    private String sender;
    private Boolean opened;
    private String dateReceived;

    public Snap(String sender, String dateReceived) {
        this.sender = sender;
        this.opened = false;
        this.dateReceived = dateReceived;

        // Randomly pick content type
        switch(generator.nextInt(3)) {
            case 0:
                recentComm = ContentType.PICTURE;
                indicatorResource = PIC_UNOPENED;
                break;
            case 1:
                recentComm = ContentType.VIDEO;
                indicatorResource = VIDEO_UNOPENED;
                break;
            case 2:
                recentComm = ContentType.CHAT;
                indicatorResource = CHAT_UNOPENED;
                break;
            default:
                break;
        }
    }

    public void open() {
        opened = true;

        switch(indicatorResource) {
            case PIC_UNOPENED:
                indicatorResource = PIC_OPENED;
                break;
            case VIDEO_UNOPENED:
                indicatorResource = VIDEO_OPENED;
                break;
            case CHAT_UNOPENED:
                indicatorResource = CHAT_OPENED;
                break;
            default:
                break;
        }
    }

    public int getIndicatorResource() { return indicatorResource; }

    public void setSender(String sender) { this.sender = sender; }
    public void setOpened(Boolean opened) { this.opened = opened; }
    public void setDateReceived(String dateOpened) { this.dateReceived = dateOpened; }
    public String getSender() { return sender; }
    public Boolean getOpened() { return opened; }
    public String getDateOpened() { return dateReceived; }


}

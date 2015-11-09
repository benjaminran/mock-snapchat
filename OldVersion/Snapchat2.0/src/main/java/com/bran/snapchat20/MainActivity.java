package com.bran.snapchat20;

import java.util.Locale;


import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    private static final Fragment[] FRAGMENTS = new Fragment[5];
    private static final int DEFAULT_POSITION = 2;
    public static final int CHAT_POSITION = 0;
    public static final int INBOX_POSITION = 1;
    public static final int CAMERA_POSITION = 2;
    public static final int FRIENDS_POSITION = 3;
    public static final int ADD_CONTACTS_POSITION = 4;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(DEFAULT_POSITION);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FRAGMENTS[position];
            if(fragment==null) {
                switch(position) {
                    case CHAT_POSITION:
                        fragment = new ChatFragment();
                        break;
                    case INBOX_POSITION:
                        fragment =  new InboxFragment();
                        break;
                    case CAMERA_POSITION:
                        fragment = new CameraFragment();
                        break;
                    case FRIENDS_POSITION:
                        fragment = new FriendsFragment();
                        break;
                    case ADD_CONTACTS_POSITION:
                        fragment = new AddContactsFragment();
                        break;
                    default:
                        fragment =  new InboxFragment();
                        break;
                }
                FRAGMENTS[position] = fragment;
            }
//            Bundle args = new Bundle();
//            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
//            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return FRAGMENTS.length;
        }
    }

    public void exitFullScreen() {
        getActionBar().hide();
        //if (Build.VERSION.SDK_INT < 16) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //}
        //else getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void goFullScreen() {
        //if(Build.VERSION.SDK_INT < 16) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        //}
        //else getWindow().getDecorView().setSystemUiVisibility(0);
        getActionBar().show();
    }

    public void goToPage(int position) {
        if(position>=0 && position<FRAGMENTS.length) {
            mViewPager.setCurrentItem(position);
        }
    }
}

package com.bran.snapchat20;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bran.snapchat20.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by beni on 2/6/15.
 */
    public class InboxFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).exitFullScreen();
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_inbox, container, false);

        final ListView listview = (ListView) layout.findViewById(R.id.inbox_list);


        String[] names = new String[] {"Jackie Chan", "John Smith", "Alice", "Bob", "Carol", "Chuck Norris", "Dan Firnough" };
        String[] dates = new String[] {"01/31/2015", "01/29/2015", "01/15/2015", "12/31/2014"};
        final ArrayList<Snap> snaps = new ArrayList<Snap>();
        for (int i = 0; i < 20; ++i) {
            snaps.add(new Snap(names[i%names.length], dates[i/5]));
        }
        final InboxAdapter adapter = new InboxAdapter(snaps);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    final int position, long id) {
                final Snap snap = snaps.get(position);
                snap.open();
                final ImageView indicator = (ImageView) view.findViewById(R.id.inbox_item_indicator);
                indicator.setImageResource(snap.getIndicatorResource());

            }

        });
        return layout;
    }

    private class InboxAdapter extends BaseAdapter {
        private ArrayList<Snap> snaps;

        public InboxAdapter(ArrayList<Snap> snaps) {
            this.snaps = snaps;
        }

        @Override
        public int getCount() {
            return snaps.size();
        }

        @Override
        public Snap getItem(int position) {
            return snaps.get(position);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Snap snap = snaps.get(position);
            String sender = snap.getSender();
            String dateOpened = snap.getDateOpened();
            View rowView;
            if(convertView!=null) {
                rowView = convertView;
            }
            else {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.inbox_item, parent, false);
            }
            TextView textView = (TextView) rowView.findViewById(R.id.inbox_item_sender);
            textView.setText(sender);
            TextView dateOpenedView = (TextView) rowView.findViewById(R.id.inbox_item_date_opened);
            dateOpenedView.setText(dateOpened);
            ImageView indicator = (ImageView) rowView.findViewById(R.id.inbox_item_indicator);
            indicator.setImageResource(snap.getIndicatorResource());

            return rowView;
        }
    }
}

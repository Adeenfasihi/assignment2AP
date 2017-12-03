package com.adeen.ap;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class days extends AppCompatActivity {

    String pickedDay;

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        Intent intent = getIntent();
        pickedDay = intent.getStringExtra("pickedDay");


        setupUIviews();
        initToolbar();
        setupListView();
    }

    private void setupUIviews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarWeek);
        listView = (ListView)findViewById(R.id.lvWeek);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Week");
    }

    private void setupListView(){
        String[] day = null;
        String[] desc = null;
        switch(pickedDay) {
            case "Monday":
                day = getResources().getStringArray(R.array.Monday);
                desc = getResources().getStringArray(R.array.MondayD);
                break;
            case "Tuesday":
                day = getResources().getStringArray(R.array.Tuesday);
                desc = getResources().getStringArray(R.array.TuesdayD);
                break;
            case "Wednesday":
                day = getResources().getStringArray(R.array.Wednesday);
                desc = getResources().getStringArray(R.array.WednesdayD);
                break;
            case "Thursday":
                day = getResources().getStringArray(R.array.Thursday);
                desc = getResources().getStringArray(R.array.ThursdayD);
                break;
            case "Friday":
                day = getResources().getStringArray(R.array.Friday);
                desc = getResources().getStringArray(R.array.FridayD);
                break;
            default:
                break;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, day, desc);
        listView.setAdapter(simpleAdapter);

    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title;
        private String[] titleArray;
        private TextView description;
        private String[] descriptionArray;

        public SimpleAdapter(Context context, String[] title, String[] description) {
            mContext = context;
            titleArray = title;
            descriptionArray  = description;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int i) {
            return titleArray[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = layoutInflater.inflate(R.layout.activity_week_single_item, null);
            }

            title = (TextView) view.findViewById(R.id.tvWeek);
            description = (TextView) view.findViewById(R.id.tvDescription);

            title.setText(titleArray[i]);
            description.setText(descriptionArray[i]);
            return view;
        }
    }
}

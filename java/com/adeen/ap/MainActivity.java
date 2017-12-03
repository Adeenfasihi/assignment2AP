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
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    String[] week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        getSupportActionBar().setTitle("Timetable");
    }

    private void setupListView(){
        week = getResources().getStringArray(R.array.week);
        String[] day = getResources().getStringArray(R.array.Monday);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, week, day);
        listView.setAdapter(simpleAdapter);


        // CALLS THE OTHER INTENT THAT SHOWS THE TIME TABLE FOR THAT DAY
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pickedDay = week[i];

                Intent myIntent = new Intent(MainActivity.this, days.class);
                myIntent.putExtra("pickedDay", pickedDay);
                startActivity(myIntent);
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title;
        private TextView click;
        private String[] titleArray;

        public SimpleAdapter(Context context, String[] title, String[] description) {
            mContext = context;
            titleArray = title;
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
            title.setText(titleArray[i]);
            click = (TextView) view.findViewById(R.id.tvClick);
            click.setText("Click to view");
            return view;
        }
    }
}


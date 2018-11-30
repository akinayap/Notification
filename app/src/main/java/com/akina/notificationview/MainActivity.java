package com.akina.notificationview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity  implements CustomRecyclerViewAdapter.ItemClickListener{

    public static final String POSITION = "Position";
    CustomRecyclerViewAdapter adapter;
    public static ArrayList<ListItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();
        Calendar cal4 = Calendar.getInstance();
        Calendar cal5 = Calendar.getInstance();
        itemList.add(new ListItem("hi0", 0, cal));
        cal1.add(Calendar.HOUR, 2);
        itemList.add(new ListItem("hi1", 1, cal1));
        cal2.add(Calendar.DAY_OF_YEAR, 10);
        itemList.add(new ListItem("hi2", 2, cal2));
        cal3.add(Calendar.DAY_OF_YEAR, 10);
        cal3.add(Calendar.MONTH, 1);
        itemList.add(new ListItem("hi3", 3, cal3));
        cal4.add(Calendar.HOUR, 14);
        cal4.add(Calendar.DAY_OF_YEAR, 10);
        itemList.add(new ListItem("hi4", 4, cal4));
        cal5.add(Calendar.DAY_OF_YEAR, 21);
        cal5.add(Calendar.MONTH, 11);
        itemList.add(new ListItem("hi5", 5, cal5));

        Collections.sort(itemList);

        RecyclerView recyclerView = findViewById(R.id.rvElements);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomRecyclerViewAdapter(this, itemList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        // Mark as read
        itemList.get(position).m_is_seen = true;

        // Refresh RecyclerView
        adapter.notifyDataSetChanged();

        // Go to next activity depending on type
        Intent intent = new Intent(this, NotificationDetails.class);

        Bundle extras = new Bundle();

        extras.putInt(POSITION, position);
        intent.putExtras(extras);
        startActivity(intent);
    }
}

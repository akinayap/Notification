package com.akina.notificationview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class NotificationDetails extends AppCompatActivity {

    final Context context = this;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    Button saveButton;
    boolean canSave;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Integer pos = extras.getInt(MainActivity.POSITION);
        ListItem obj = MainActivity.itemList.get(pos);

        TextView textView = findViewById(R.id.tv1);
        textView.setText(obj.m_content);

        TextView dateTV = findViewById(R.id.dateTxt);
        DateFormat dateFormat = new SimpleDateFormat("- MMMM d, YYYY", Locale.ENGLISH);
        dateTV.setText(dateFormat.format(obj.m_cal.getTimeInMillis()));

        canSave = false;

        // Set up UI according to type
        ConstraintLayout box = findViewById(R.id.bounding);
        RadioGroup yn = findViewById(R.id.yes_no_type);
        RadioGroup abc = findViewById(R.id.abc_type);
        EditText edit = findViewById(R.id.edit_text_type);
        ConstraintLayout m = findViewById(R.id.m_rate_type);
        ConstraintLayout h = findViewById(R.id.h_rate_type);
        Button save_btn = findViewById(R.id.savebtn);

        save_btn.setEnabled(true);
        box.setVisibility(View.VISIBLE);
        yn.setVisibility(View.INVISIBLE);
        abc.setVisibility(View.INVISIBLE);
        edit.setVisibility(View.INVISIBLE);
        m.setVisibility(View.INVISIBLE);
        h.setVisibility(View.INVISIBLE);
        switch (obj.m_item_type)
        {
            case 0:
                save_btn.setEnabled(false);
                box.setVisibility(View.GONE);
                break;
            case 1:
                yn.setVisibility(View.VISIBLE);
                break;
            case 2:
                abc.setVisibility(View.VISIBLE);
                break;
            case 3:
                edit.setVisibility(View.VISIBLE);
                break;
            case 4:
                m.setVisibility(View.VISIBLE);
                break;
            case 5:
                h.setVisibility(View.VISIBLE);
                break;
        }

        Button skip_btn = findViewById(R.id.skipbtn);
        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.popup_dialog);

                checkBox1 = dialog.findViewById(R.id.rp);
                checkBox2 = dialog.findViewById(R.id.p);
                checkBox3 = dialog.findViewById(R.id.t);
                checkBox4 = dialog.findViewById(R.id.m);

                Button backButton = dialog.findViewById(R.id.back_btn);
                // if button is clicked, close the custom dialog
                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                saveButton = dialog.findViewById(R.id.save_btn);
                // if button is clicked, close the custom dialog
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(canSave)
                        {
                            dialog.dismiss();
                            NotificationDetails.super.finish();
                        }
                        else
                        {
                            Toast.makeText(NotificationDetails.this, "Please select an option before saving", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });

        Button like_btn = findViewById(R.id.likebtn);
        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationDetails.super.finish();
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DO SAVE HERE
                NotificationDetails.super.finish();
            }
        });

        Button esc_btn = findViewById(R.id.esc);
        esc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationDetails.super.finish();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        switch(view.getId()) {
            case R.id.rp:
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                break;

            case R.id.p:
                checkBox1.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                break;

            case R.id.t:
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox4.setChecked(false);
                break;

            case R.id.m:
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                break;
        }

        if(checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked())
        {
            canSave = true;
        }
        else
        {
            canSave = false;
        }
    }
}

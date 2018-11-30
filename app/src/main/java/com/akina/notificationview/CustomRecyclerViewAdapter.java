package com.akina.notificationview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static android.view.View.GONE;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {
    private List<ListItem> m_data;
    private LayoutInflater m_inflater;
    private ItemClickListener m_click_listener;

    CustomRecyclerViewAdapter(Context context, List<ListItem> list) {
        this.m_inflater = LayoutInflater.from(context);
        this.m_data = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = m_inflater.inflate(R.layout.item_label, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ListItem elem = m_data.get(position);
        if (position > 0)
        {

            ListItem prevElem = m_data.get(position - 1);
            if(elem.SameDate(prevElem))
            {
                holder.myTextDate.setVisibility(GONE);
            }
            else
            {
                holder.myTextDate.setVisibility(View.VISIBLE);
                DateFormat dateFormat = new SimpleDateFormat("MMMM d, YYYY", Locale.ENGLISH);
                holder.myTextDate.setText(dateFormat.format(elem.m_cal.getTimeInMillis()));
            }
        }
        else
        {
            holder.myTextDate.setVisibility(View.VISIBLE);
            DateFormat dateFormat = new SimpleDateFormat("MMMM d, YYYY", Locale.ENGLISH);
            holder.myTextDate.setText(dateFormat.format(elem.m_cal.getTimeInMillis()));
        }

        //String noLegs = mDataContent.get(position);
        holder.myTextView.setText(elem.m_title);
        holder.myTextDesc.setText(elem.m_content);

        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        holder.myTextTime.setText(timeFormat.format(elem.m_cal.getTimeInMillis()));

        if(elem.m_is_seen == true) {
            holder.myNotImg.setVisibility(View.INVISIBLE);
            holder.myTextView.setTextColor(Color.GRAY);
            holder.myTextDesc.setTextColor(Color.GRAY);
        }
        else
        {
            holder.myNotImg.setVisibility(View.VISIBLE);
            holder.myTextDesc.setTextColor(Color.BLACK);
            holder.myTextView.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return m_data.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myTextView;
        TextView myTextDesc;
        TextView myTextDate;
        TextView myTextTime;
        ImageView myNotImg;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.textTitle);
            myTextDesc = itemView.findViewById(R.id.textDesc);
            myTextDate = itemView.findViewById(R.id.textDate);
            myTextTime = itemView.findViewById(R.id.textTime);
            myNotImg = itemView.findViewById(R.id.notifyImg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (m_click_listener != null) m_click_listener.onItemClick(v, getAdapterPosition());
        }
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.m_click_listener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {

        void onItemClick(View view, int position);
    }

}
